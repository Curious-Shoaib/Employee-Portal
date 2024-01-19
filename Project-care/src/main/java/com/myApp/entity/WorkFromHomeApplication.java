package com.myApp.entity;

import java.time.LocalDate;

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
	Integer applicationId;
	@ManyToOne
	@JoinColumn(name="applicantId")
	Employee applicant;
	@Column(unique = true)
	LocalDate dateOfApplication;
	String status;				//approved/Rejected
	
	public Integer getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Integer applicationId) {
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
	
	
}
