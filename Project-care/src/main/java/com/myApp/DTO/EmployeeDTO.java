package com.myApp.DTO;

import java.time.LocalDate;

import com.myApp.entity.Address;
import com.myApp.entity.Employee;
import com.myApp.entity.Project;


import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class EmployeeDTO {

	
	private Integer employeeId;
	private String employeeName;
	private Integer mobileNo;
	private String email;
	private Address address;
	@Enumerated(EnumType.STRING)
	private String designation;
	@Enumerated(EnumType.STRING)
	private String dcName; 
	private Integer age;
	@Enumerated(EnumType.STRING)
	private String gender;
	private LocalDate joiningDate;
	private Integer unit;
	@Enumerated(EnumType.STRING)
	private String skills;
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDcName() {
		return dcName;
	}
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	
}
