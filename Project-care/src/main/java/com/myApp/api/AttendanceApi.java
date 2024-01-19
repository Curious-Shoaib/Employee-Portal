package com.myApp.api;

import java.sql.Time;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myApp.DTO.AttendanceDTO;
import com.myApp.exception.AttendanceException;
import com.myApp.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceApi {

	@Autowired
	AttendanceService attedanceService;
	
	@PostMapping("/office")
	public ResponseEntity applyOfficeAttendance(@RequestBody AttendanceDTO aDTO) throws AttendanceException{
		
		String msg=attedanceService.applyOfficeAttendance(aDTO.getEmployeeId(), aDTO.getPunchIn(), aDTO.getPunchOut(), aDTO.getDate());
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@PostMapping("/WFH")
	public ResponseEntity applyWorkFromHome(@RequestBody AttendanceDTO aDTO) throws AttendanceException
	{
		
		String msg=attedanceService.applyWorkFromHome(aDTO.getEmployeeId(),aDTO.getManagerId(), aDTO.getPunchIn(), aDTO.getPunchOut(), aDTO.getDate());
		return new ResponseEntity(msg,HttpStatus.CREATED);
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
	
	@PostMapping("/addToAll")
	public ResponseEntity addAttendanceToAll() throws AttendanceException{
		
		String msg=attedanceService.addAttendanceToAll();
		return new ResponseEntity(msg,HttpStatus.CREATED);
		
	}
}
