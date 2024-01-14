package com.myApp.api.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myApp.DTO.EmployeeDTO;
import com.myApp.DTO.ProjectDTO;
import com.myApp.Service.EmployeeServiceImpl;
import com.myApp.convert.Converter;
import com.myApp.entity.Demand;
import com.myApp.entity.Employee;
import com.myApp.entity.Project;
import com.myApp.exception.ProjectException;
import com.myApp.responseData.DemandResponse;

@RestController
@RequestMapping("/employee")
public class EmployeeApi {
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	
	@PostMapping("/")
	public ResponseEntity addEmployee(@RequestBody EmployeeDTO DTO) {
		
		String msg="Employee added successfully";
		try {
			employeeService.addEmployee(DTO);
		} catch (ProjectException e) {
			msg=e.getMessage();
		}
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity getAllEmployee() {
		
		List<EmployeeDTO> employees;
		try {
			employees = employeeService.getAllEmployees();
		} catch (ProjectException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(employees,HttpStatus.OK);
		
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity getEmployee(@PathVariable("empId") Integer eId) {
		
		
		EmployeeDTO eDTO=null;
		try {
			eDTO = employeeService.getEmployee(eId);
		} catch (ProjectException e) {
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CREATED);
		}
		return new ResponseEntity<EmployeeDTO>(eDTO,HttpStatus.OK);
		
	}
	
	@PostMapping("/{managerId}/{reviewerId}/{technology}")
	public ResponseEntity<String> createDemand(@PathVariable Integer managerId,@PathVariable Integer reviewerId,@PathVariable String technology) {
		
		String msg="Demand is created with demand id :";
		try {
			Integer id=employeeService.createDemand(managerId,reviewerId ,technology);
			msg+=id;
		} catch (ProjectException e) {
			msg=e.getMessage();
		}
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/getDemands")
	public ResponseEntity getAllDemands()  {
		
		List<Demand> demand=null;
	
		try {
			demand=employeeService.getAllDemand();
		}
		catch(Exception e){
			
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(demand, HttpStatus.OK);
	}
	
	@PostMapping("/apply/{empId}/{demandId}")
	public ResponseEntity<String> applyToDemand (@PathVariable Integer empId,@PathVariable Integer demandId) {
		
		String msg="Applied Successfully";
		try {
			employeeService.applyToDemand(empId, demandId);
		} catch (ProjectException e) {
			msg=e.getMessage();
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/revSingle/{reviewerId}/{applicantId}/{demandId}")
	public ResponseEntity reviewSingleApplicant(@PathVariable Integer reviewerId,@PathVariable Integer applicantId,@PathVariable Integer demandId) {
		
		String response;
		try {
			response=employeeService.reviewApplicant(reviewerId, applicantId, demandId);
		} catch (ProjectException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		catch (Exception e) {
//			return new ResponseEntity<>("Some error occured",HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/rev/{reviewerId}/{demandId}")
	public ResponseEntity reviewApplicants(@PathVariable Integer reviewerId,@PathVariable Integer demandId) {
		
		List<String> responses;
		try {
			responses=employeeService.reviewAllApplicants(reviewerId,demandId);
		} catch (ProjectException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
		}
		return new ResponseEntity<>(responses,HttpStatus.OK);
	}

}
