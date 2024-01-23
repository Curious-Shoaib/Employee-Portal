package com.myApp.DTO;

import java.time.LocalDate;

public class WorkFromHomeApplicationDTO {

	Long applicationId;
	LocalDate dateOfApplication;
	LocalDate dateOfAttendance;
	String status;
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public LocalDate getDateOfApplication() {
		return dateOfApplication;
	}
	public void setDateOfApplication(LocalDate dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}
	public LocalDate getDateOfAttendance() {
		return dateOfAttendance;
	}
	public void setDateOfAttendance(LocalDate dateOfAttendance) {
		this.dateOfAttendance = dateOfAttendance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
