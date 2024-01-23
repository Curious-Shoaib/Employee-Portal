package com.myApp.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WorkFromHomeApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long applicationId;
	@ManyToOne
	@JoinColumn(name="applicantId")
	Employee applicant;
	LocalDate dateOfApplication;
	LocalDate dateOfAttendance;
	String status;				//approved/Rejected
	
	public LocalDate getDateOfAttendance() {
		return dateOfAttendance;
	}
	public void setDateOfAttendance(LocalDate dateOfAttendance) {
		this.dateOfAttendance = dateOfAttendance;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Employee getApplicant() {
		return applicant;
	}
	public void setApplicant(Employee applicant) {
		this.applicant = applicant;
	}
	public LocalDate getDateOfApplication() {
		return dateOfApplication;
	}
	public void setDateOfApplication(LocalDate dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkFromHomeApplication other = (WorkFromHomeApplication) obj;
		return Objects.equals(applicant.getEmployeeId(), other.applicant.getEmployeeId()) && Objects.equals(applicationId, other.applicationId)
				&& Objects.equals(dateOfApplication, other.dateOfApplication)
				&& Objects.equals(dateOfAttendance, other.dateOfAttendance) && Objects.equals(status, other.status);
	}
	@Override
	public String toString() {
		return "WorkFromHomeApplication [applicationId=" + applicationId + ", applicantId=" + applicant.getEmployeeId()
				+ ", dateOfApplication=" + dateOfApplication + ", dateOfAttendance=" + dateOfAttendance + ", status="
				+ status + "]";
	}
	
	
}
