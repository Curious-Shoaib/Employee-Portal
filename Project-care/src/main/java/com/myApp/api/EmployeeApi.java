package com.myApp.api;

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

import com.myApp.DTO.EmployeeDTO;
import com.myApp.entity.Demand;
import com.myApp.service.EmployeeServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeApi {
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	@PostMapping("/")
	public ResponseEntity addEmployee(@Valid @RequestBody EmployeeDTO DTO) throws Exception {
			Integer employeeId=employeeService.addEmployee(DTO);
		return new ResponseEntity<>("Employee added successfully with employee id: "+employeeId,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity getAllEmployee() throws Exception {
		List<EmployeeDTO> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees,HttpStatus.OK);
		
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity getEmployee(@NotNull(message="{Validation.VALUE_BLANK}") @PathVariable("empId") Integer eId) throws Exception {
		EmployeeDTO eDTO = employeeService.getEmployee(eId);
		return new ResponseEntity<EmployeeDTO>(eDTO,HttpStatus.OK);
		
	}
	
	@PostMapping("/{managerId}/{reviewerId}/{technology}")
	public ResponseEntity<String> createDemand(@NotNull(message="{Validation.VALUE_BLANK}") @PathVariable Integer managerId,@NotNull(message="Validation.VALUE_BLANK") @PathVariable Integer reviewerId,@NotNull(message="Validation.VALUE_BLANK") @PathVariable String technology) throws Exception {
		Integer id=employeeService.createDemand(managerId,reviewerId ,technology);
		String msg="Demand is created with demand id :" + id;	
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
			
	}
	
	@GetMapping("/demands")
	public ResponseEntity getAllDemands() throws Exception {
		List<Demand> demand=null;
		demand=employeeService.getAllDemand();
		return new ResponseEntity(demand, HttpStatus.OK);
	}
	
	@PostMapping("/apply/{empId}/{demandId}")
	public ResponseEntity<String> applyToDemand (@NotNull(message="{Validation.VALUE_BLANK}") @PathVariable Integer empId,@NotNull(message="{Validation.VALUE_BLANK}") @PathVariable Integer demandId) throws Exception {
		String msg="Applied Successfully";
		employeeService.applyToDemand(empId, demandId);
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/revSingle/{reviewerId}/{applicantId}/{demandId}")
	public ResponseEntity reviewSingleApplicant( @NotNull(message="{Validation.VALUE_BLANK}") @PathVariable Integer reviewerId,@NotNull(message="{Validation.VALUE_BLANK}") @PathVariable Integer applicantId,@NotNull(message="{Validation.VALUE_BLANK}") @PathVariable Integer demandId) throws Exception{
		String response=employeeService.reviewApplicant(reviewerId, applicantId, demandId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/revAll/{reviewerId}/{demandId}")
	public ResponseEntity reviewApplicants(@NotNull(message="{Validation.VALUE_BLANK}") @PathVariable Integer reviewerId,@NotNull(message="{Validation.VALUE_BLANK}") @PathVariable Integer demandId) throws Exception {
		List<String> responses=employeeService.reviewAllApplicants(reviewerId,demandId);
		return new ResponseEntity<>(responses,HttpStatus.OK);
	}

}
