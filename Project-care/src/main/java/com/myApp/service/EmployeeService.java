package com.myApp.service;

import java.util.List;

import com.myApp.DTO.EmployeeDTO;
import com.myApp.entity.Demand;
import com.myApp.entity.Employee;
import com.myApp.exception.ProjectException;

public interface EmployeeService {

	public Integer addEmployee(EmployeeDTO DTO) throws ProjectException;
	public EmployeeDTO getEmployee(Integer eId) throws ProjectException;
	public List<EmployeeDTO> getAllEmployees() throws ProjectException;
	public Integer createDemand(Integer managerId,Integer reviewerId,String technology) throws ProjectException; 
	public List<Demand> getAllDemand() throws ProjectException;
	public void applyToDemand(Integer empId,Integer demandId) throws ProjectException; 
	 public String reviewApplicant(Integer reviewerId,Integer applicantId,Integer demandId) throws ProjectException;
	public List<String> reviewAllApplicants(Integer demandId,Integer reviewerId) throws ProjectException;
}
