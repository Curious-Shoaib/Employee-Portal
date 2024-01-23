package com.myApp.service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myApp.DTO.WorkFromHomeApplicationDTO;
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
		Optional<Attendance> optAttendance=attendanceRepo.getLetestAttendanceRecordByemployeeId(employee);
		Attendance attendance=optAttendance.get();
		if(attendance.getAttendanceMode().matches("(WFH|Office)"))
		 throw new AttendanceException("AttendanceService.ATTENDANCE_MARKED_ALREDY");
		if(attendance.getAttendanceMode().equalsIgnoreCase("Applied"))
			throw new AttendanceException("AttendanceService.WFH_APPLICATION_EXISTS");	
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
		Optional<Attendance> attendanceRecord=attendanceRepo.getAttendanceByDateAndEmpId(employee, date);
		
		if(attendanceRecord.isEmpty())
			throw new AttendanceException("AttendanceService.NO_ATTENDANCE_RECORD");
		Attendance attendance=attendanceRecord.get();
		if(attendance.getAttendanceMode().equalsIgnoreCase("Applied"))
			throw new AttendanceException("AttendanceService.WFH_APPLICATION_EXISTS");
		
		if(attendance.getAttendanceMode().equalsIgnoreCase("Weekend"))
			throw new AttendanceException("AttendanceService.WEEKEND");
			if(!attendance.getAttendanceMode().equalsIgnoreCase("Status unknown"))
				throw new AttendanceException("AttendanceService.ATTENDANCE_MARKED_ALREDY");
		
		 Employee manager=employee.getManager();
		if(manager==null)
		{
			managerId=Integer.parseInt(env.getProperty("AttendanceService.DEFAULT_MANAGER")); // default manager is set in properties file
			manager=employeeRepo.findById(managerId).get();
		}
		else if(!manager.getEmployeeId().equals(managerId))
		{
			throw new AttendanceException("AttendanceService.WRONG_MANAGER");
		}
		
		Integer wfhDaysLimit=Integer.parseInt(env.getProperty("AttendanceService.WFH_APPLICATION_DAYS_ALLOWED"));
		Integer wfhWeeklyBalance=Integer.parseInt(env.getProperty("AttendanceService.WFH_BALANCE_PER_WEEK"));
		if(ChronoUnit.DAYS.between(LocalDate.now(),date) > wfhDaysLimit)
		{
			throw new AttendanceException("AttendanceService.CANT_APPLY_AFTER_DAYS_LIMIT");
		}
		Integer appliedCount=attendanceRepo.getWorkFromHomeCountOfEmployee(employee);
		if(appliedCount==wfhWeeklyBalance)
			throw new AttendanceException("AttendanceService.NO_WFH_BALANCE");
		
		WorkFromHomeApplication application=new WorkFromHomeApplication();
		application.setApplicant(employee);
		application.setDateOfApplication(LocalDate.now());
		application.setDateOfAttendance(date);
		application.setStatus("Applied");
		
		
		employee.getMyWorkFromHomeApplications().add(application);
		manager.getWorkFromHomeappliedToMe().add(application);
		attendance.setAttendanceMode("Applied");
		attendance.setPunchIn(punchIn);
		attendance.setPunchOut(punchOut);
		attendanceRepo.save(attendance);
		employeeRepo.save(employee);
		employeeRepo.save(manager);
		Long id=applicationRepo.save(application).getApplicationId();
		
		return env.getProperty("AttendanceService.WFH_APPLIED")+ id;
		
			
	}
	@Override
	public List<WorkFromHomeApplicationDTO> fetchMyAttendanceApplications(Integer empId) throws AttendanceException{
		Optional<Employee> opEmp=employeeRepo.findById(empId);
		if(opEmp.isEmpty())
			throw new AttendanceException("AttendanceService.EMP_NOT_FOUND");
		Employee employee=opEmp.get();
		List<WorkFromHomeApplication> applications=employee.getMyWorkFromHomeApplications();
		if(applications.isEmpty())
			throw new AttendanceException("AttendanceService.APPLICATIONS_NOT_FOUND");
		List<WorkFromHomeApplicationDTO> DTOs=new ArrayList<>();
		applications.forEach(application->
		{
			WorkFromHomeApplicationDTO dto=new WorkFromHomeApplicationDTO();
			dto.setApplicationId(application.getApplicationId());
			dto.setDateOfApplication(application.getDateOfApplication());
			dto.setDateOfAttendance(application.getDateOfAttendance());
			dto.setStatus(application.getStatus());
			DTOs.add(dto);
		});
		return DTOs;
	}
	
	public String withdrawWorkFromHome(Integer empId,LocalDate date) throws AttendanceException{
		
		Optional<Employee> opEmp=employeeRepo.findById(empId);
		if(opEmp.isEmpty())
			throw new AttendanceException("AttendanceService.EMP_NOT_FOUND");
		Employee employee=opEmp.get();
		
		Optional<Attendance> attendanceRecord=attendanceRepo.getAttendanceByDateAndEmpId(employee, date);
		
		if(attendanceRecord.isEmpty())
			throw new AttendanceException("AttendanceService.NO_ATTENDANCE_RECORD");
		Attendance attendance=attendanceRecord.get();
		
		if(!attendance.getAttendanceMode().equalsIgnoreCase("Approved"))
			throw new AttendanceException("AttendanceService.ATTENDANCE_MARKED_CANT_WITHDRAW");
		
		attendance.setAttendanceMode("Status unknown");
		attendance.setPunchIn(null);
		attendance.setPunchOut(null);
		attendanceRepo.save(attendance);
		WorkFromHomeApplication application=null;
		for(WorkFromHomeApplication app : employee.getMyWorkFromHomeApplications())
		{
			if(app.getDateOfAttendance().equals(date))
			{
				application=app;
				break;
			}
		}
		employee.getMyWorkFromHomeApplications().remove(application);
		Employee manager=employee.getManager();
		if(manager==null)
		{
			Integer managerId=Integer.parseInt(env.getProperty("AttendanceService.DEFAULT_MANAGER")); // default manager is set in properties file
			manager=employeeRepo.findById(managerId).get();
		}
		manager.getWorkFromHomeappliedToMe().remove(application);
		employeeRepo.save(employee);
		employeeRepo.save(manager);
		applicationRepo.deleteByApplicantAndDateOfApplication(employee, date);
		return env.getProperty("AttendanceService.WFH_WITHDRAWN");
	}
	
	public String reviewWorkFromHomeApplication(Integer managerId,Integer empId) throws AttendanceException{
		
		Optional<Employee> optManager=employeeRepo.findById(managerId);
		if(optManager.isEmpty())
			throw new AttendanceException("AttendanceService.EMP_NOT_FOUND");
		Employee manager=optManager.get();
		
		Optional<Employee> optEmployee=employeeRepo.findById(empId);
		if(optEmployee.isEmpty())
			throw new AttendanceException("AttendanceService.APPLICANT_NOT_FOUND");
		Employee employee=optEmployee.get();
		
		List<WorkFromHomeApplication> applications=manager.getWorkFromHomeappliedToMe();
		WorkFromHomeApplication application=null;	
	
		for(WorkFromHomeApplication app : applications)
		{
			
			if(app.getApplicant().equals(employee) && app.getStatus().equalsIgnoreCase("Applied"))
			{
				application=app;
				break;
			}
		}
		if(application==null)
			throw new AttendanceException("AttendanceService.APPLICANT_NOT_APPLIED_TO_MANAGER");
		attendanceRepo.updateStatusToWfhOfEmployee(employee, application.getDateOfAttendance());
		application.setStatus("Approved");
		applications.remove(application);
		employeeRepo.save(employee);
		employeeRepo.save(manager);
		
		return env.getProperty("AttendanceService.APPLICATION_APPROVED");
	}
	public String reviewAllWorkFromHomeApplication(Integer managerId) throws AttendanceException{
		Optional<Employee> optManager=employeeRepo.findById(managerId);
		if(optManager.isEmpty())
			throw new AttendanceException("AttendanceService.EMP_NOT_FOUND");
		Employee manager=optManager.get();
		
		List<WorkFromHomeApplication> applications=manager.getWorkFromHomeappliedToMe();
		
		for(WorkFromHomeApplication application : applications)
		{
			application.setStatus("Approved");
			attendanceRepo.updateStatusToWfhOfEmployee(application.getApplicant(), application.getDateOfAttendance());
		}
		applications.clear();
		employeeRepo.save(manager);
		return env.getProperty("AttendanceService.ALL_APPLICATION_APPROVED");
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
