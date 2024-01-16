package com.myApp.convert;

import com.myApp.DTO.AddressDTO;
import com.myApp.DTO.EmployeeDTO;
import com.myApp.DTO.ProjectDTO;
import com.myApp.entity.Address;
import com.myApp.entity.Employee;
import com.myApp.entity.Project;

public class Converter {

	public static Address convert(AddressDTO dto)
	{
		
		Address ad=new Address();
			ad.setCity(dto.getCity());
			ad.setCountry(dto.getCountry());
			ad.setHouseNo(dto.getHouseNo());
			ad.setSector(dto.getSector());
			ad.setState(dto.getState());
			ad.setZipCode(dto.getZipCode());
			
			return ad;
	}
	public static AddressDTO convert(Address dto)
	{
		
		AddressDTO ad=new AddressDTO();
			ad.setCity(dto.getCity());
			ad.setCountry(dto.getCountry());
			ad.setHouseNo(dto.getHouseNo());
			ad.setSector(dto.getSector());
			ad.setState(dto.getState());
			ad.setZipCode(dto.getZipCode());
			
			return ad;
	}

	public static Employee convert(EmployeeDTO dto) {
		// TODO Auto-generated method stub
		
		Employee em=new Employee();
		em.setAddress(convert(dto.getAddress()));
		em.setAge(dto.getAge());
		em.setDcName(dto.getDcName());
		em.setDesignation(dto.getDesignation());
		em.setEmail(dto.getEmail());
		em.setEmployeeName(dto.getEmployeeName());
		em.setJoiningDate(dto.getJoiningDate());
		em.setSkills(dto.getSkills());
		em.setUnit(dto.getUnit());
		em.setGender(dto.getGender());
		em.setMobileNo(dto.getMobileNo());
		return em;
	}
	
	public static EmployeeDTO convert(Employee dto) {
		// TODO Auto-generated method stub
		
		EmployeeDTO em=new EmployeeDTO();
		em.setAddress(convert(dto.getAddress()));
		em.setAge(dto.getAge());
		em.setDcName(dto.getDcName());
		em.setDesignation(dto.getDesignation());
		em.setEmail(dto.getEmail());
		em.setEmployeeName(dto.getEmployeeName());
		em.setJoiningDate(dto.getJoiningDate());
		em.setSkills(dto.getSkills());
		em.setUnit(dto.getUnit());
		em.setGender(dto.getGender());
		em.setMobileNo(dto.getMobileNo());
		return em;
	}
	
	public static Project convert(ProjectDTO dto) {
		// TODO Auto-generated method stub
		Project pr=new Project();
		pr.setBudget(dto.getBudget());
		pr.setClient(dto.getClient());
		pr.setProjectName(dto.getProjectName());
		pr.setTechnology(dto.getTechnology());
		pr.setStartingDate(dto.getStartingDate());
		return pr;
		
	}
	public static ProjectDTO convert(Project dt) {
		// TODO Auto-generated method stub
		ProjectDTO pr=new ProjectDTO();
		pr.setBudget(dt.getBudget());
		pr.setClient(dt.getClient());
		pr.setProjectName(dt.getProjectName());
		pr.setTechnology(dt.getTechnology());
		pr.setStartingDate(dt.getStartingDate());
		return pr;
		
	}
}
