package com.myApp.api;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myApp.DTO.AttendanceDTO;
import com.myApp.DTO.WorkFromHomeApplicationDTO;
import com.myApp.entity.WorkFromHomeApplication;
import com.myApp.exception.AttendanceException;
import com.myApp.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/attendance")
@Validated
public class AttendanceApi {

	@Autowired
	AttendanceService attedanceService;
	
	@PostMapping("/office")
	public ResponseEntity applyOfficeAttendance(@RequestBody AttendanceDTO aDTO) throws AttendanceException{
		
		String msg=attedanceService.applyOfficeAttendance(aDTO.getEmployeeId(), aDTO.getPunchIn(), aDTO.getPunchOut(), aDTO.getDate());
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@PostMapping("/WFH")
	public ResponseEntity applyWorkFromHome(@Valid @RequestBody AttendanceDTO aDTO) throws AttendanceException
	{
		
		String msg=attedanceService.applyWorkFromHome(aDTO.getEmployeeId(),aDTO.getManagerId(), aDTO.getPunchIn(), aDTO.getPunchOut(), aDTO.getDate());
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity fetchWfhApplications(@PathVariable Integer empId) throws AttendanceException
	{
		List<WorkFromHomeApplicationDTO> applications=attedanceService.fetchMyAttendanceApplications(empId);
		return new ResponseEntity(applications,HttpStatus.OK);
	}
	
	@PutMapping("/WFH/{empId}/{date}")
	public ResponseEntity withdrawWorkFromHome(@PathVariable Integer empId,@PathVariable LocalDate date) throws AttendanceException{
		
		String msg=attedanceService.withdrawWorkFromHome(empId, date);
		return new ResponseEntity(msg,HttpStatus.OK);
	}
	
	@PutMapping("/{managerId}/{empId}")
	public ResponseEntity reviewWorkFromHomeApplication(@PathVariable Integer managerId ,@PathVariable Integer empId) throws AttendanceException{
		
		String msg=attedanceService.reviewWorkFromHomeApplication(managerId, empId);
		return new ResponseEntity(msg,HttpStatus.OK);
	}
	
	@PutMapping("/{managerId}")
	public ResponseEntity reviewAllWorkFromHomeApplication(@PathVariable Integer managerId) throws AttendanceException{
		String msg=attedanceService.reviewAllWorkFromHomeApplication(managerId);
		return new ResponseEntity(msg,HttpStatus.OK);
	}
	
	@PostMapping("/addToAll")
	public ResponseEntity addAttendanceToAll() throws AttendanceException{
		String msg=attedanceService.addAttendanceToAll();
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
}
