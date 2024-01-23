package com.myApp.service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.myApp.DTO.WorkFromHomeApplicationDTO;
import com.myApp.entity.WorkFromHomeApplication;
import com.myApp.exception.AttendanceException;
import com.myApp.exception.ProjectException;

public interface AttendanceService {

		public String applyOfficeAttendance(Integer empId,LocalTime punchIn,LocalTime punchOut,LocalDate date) throws AttendanceException;
		public String applyWorkFromHome(Integer empId,Integer managerId,LocalTime punchIn,LocalTime punchOut,LocalDate date) throws AttendanceException;
		public List<WorkFromHomeApplicationDTO> fetchMyAttendanceApplications(Integer empId) throws AttendanceException;
		public String withdrawWorkFromHome(Integer empId,LocalDate date) throws AttendanceException;
		public String reviewWorkFromHomeApplication(Integer managerId,Integer empId) throws AttendanceException;
		public String reviewAllWorkFromHomeApplication(Integer empId) throws AttendanceException;
		public String addAttendanceToAll() throws AttendanceException;
		
	
}
