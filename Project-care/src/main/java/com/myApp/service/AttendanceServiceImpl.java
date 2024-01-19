package com.myApp.service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myApp.entity.Attendance;
import com.myApp.entity.Employee;
import com.myApp.entity.WorkFromHomeApplication;
import com.myApp.exception.AttendanceException;
import com.myApp.exception.ProjectException;
import com.myApp.repository.AttendanceRepository;
import com.myApp.repository.EmployeeRepository;
import com.myApp.repository.WorkFromHomeApplicationRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepo;
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	WorkFromHomeApplicationRepository applicationRepo;
	@Autowired
	Environment env;
	

	public String applyOfficeAttendance(Integer empId,LocalTime punchIn,LocalTime punchOut,LocalDate date) throws AttendanceException
	{
		Optional<Employee> opt=employeeRepo.findById(empId);
		if(opt.isEmpty())
			throw new AttendanceException("AttendanceService.EMP_NOT_FOUND");
		Employee employee=opt.get();
		List<Attendance> list=attendanceRepo.getLetestAttendanceRecordByemployeeId(employee);
		Attendance attendance=list.get(0);
		attendance.setPunchIn(punchIn);
		attendance.setPunchOut(punchOut);
		attendance.setAttendanceMode("Office");
		attendanceRepo.save(attendance);
		return env.getProperty("AttendanceService.OFFICE_ATTENDANCE_APPLIED");
	}
	
	public String applyWorkFromHome(Integer empId,Integer managerId,LocalTime punchIn,LocalTime punchOut,LocalDate date) throws AttendanceException{
		
		Optional<Employee> optEmployee=employeeRepo.findById(empId);
		if(optEmployee.isEmpty())
			throw new AttendanceException("AttendanceService.EMP_NOT_FOUND");
		Employee employee=optEmployee.get();
		Optional<Employee> optManager=employeeRepo.findById(empId);
		if(optManager.isEmpty())
			managerId=Integer.parseInt(env.getProperty("AttendanceService.DEFAULT_MANAGER")); 			// default manager is set in properties file
		Employee manager=optManager.get();
		Integer wfhDaysLimit=Integer.parseInt(env.getProperty("AttendanceService.WFH_APPLICATION_DAYS_ALLOWED"));
		Integer wfhWeeklyBalance=Integer.parseInt(env.getProperty("AttendanceService.WFH_BALANCE_PER_WEEK"));
		if(Duration.between(LocalDate.now(), date).toDays() > wfhDaysLimit)
		{
			throw new AttendanceException("AttendanceService.CANT_APPLY_AFTER_DAYS_LIMIT");
		}
		Integer appliedCount=attendanceRepo.getWorkFromHomeCountOfEmployee(employee);
		if(appliedCount==wfhWeeklyBalance)
			throw new AttendanceException("AttendanceService.NO_WFH_BALANCE");
		
		//attendanceRepo.updateStatusToWfhOfEmployee(empId, punchIn, punchOut);
		WorkFromHomeApplication application=new WorkFromHomeApplication();
		application.setApplicant(employee);
		application.setDateOfApplication(LocalDate.now());
		application.setStatus("Applied");
		
		employee.getMyWorkFromHomeApplications().add(application);
		manager.getWorkFromHomeappliedToMe().add(application);
		employeeRepo.save(employee);
		employeeRepo.save(manager);
		Integer id=applicationRepo.save(application).getApplicationId();
		
		return env.getProperty("AttendanceService.WFH_APPLIED")+ id;
		
			
	}
	public String withdrawWorkFromHome(Integer empId,LocalDate date) throws AttendanceException{
		
		return null;
	}
	public String reviewWorkFromHomeApplication(Integer empId) throws AttendanceException{
		
		return null;
	}
	public String reviewAllWorkFromHomeApplication(Integer empId) throws AttendanceException{
		
		return null;
	}
	
//************  Method to add blank attendance record for all employee Everyday *************\\\\\\\\
	@Scheduled(cron = "0 0 0 * * ?")
	public String addAttendanceToAll() throws AttendanceException
	{
		Iterable<Employee> records=employeeRepo.findAll();
		String today=LocalDate.now().getDayOfWeek().toString();
		records.forEach(employee->
		{
			Attendance att=new Attendance();
			att.setAttendanceDate(LocalDate.now());
			att.setEmployee(employee);
			if(today.equalsIgnoreCase("Sunday") || today.equalsIgnoreCase("Saturday"))
				att.setAttendanceMode("Weekend");
			else
				att.setAttendanceMode("Status unknown");
			attendanceRepo.save(att);
		});
		
		return env.getProperty("AttendanceService.ADDED_TO_ALL");
	}

}
