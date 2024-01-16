package com.myApp.DTO;

import java.time.LocalDate;
import com.myApp.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public class EmployeeDTO {
	@Pattern(regexp="[A-Z]{1}[a-z]{1,15}([\\s][A-Za-z]{1}[a-z]{1,15})*",message="{INVALID_NAME}")
	private String employeeName;
	@Pattern(regexp="\\d{10}",message="{INVALID_MOB_NO}")
	private String mobileNo;
	@Email(message="{INVALID_EMAIL}")
	private String email;
	@Valid							//Cascading validation
	private AddressDTO address;
	@Pattern(regexp="[A-Z]{1}[a-z]{1,15}([\\s][A-Za-z]{1}[a-z]{1,15})*",message="{INVALID_NAME}")
	private String designation;
	@Pattern(regexp="[A-Z]{1}[a-z]{1,15}",message="{INVALID_DC}")
	private String dcName; 
	@Min(value=18, message="{LESS_AGE}")
	private Integer age;
	@Pattern(regexp="(Male|Female)",message="{INVALID_GENDER}")
	private String gender;
	@PastOrPresent(message="{INVALID_JOIN_DATE}")
	private LocalDate joiningDate;
	private Integer unit;
	@Pattern(regexp="[A-Z]+[A-Za-z]*",message="{INVALID_SKILL}")
	private String skills;
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
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
