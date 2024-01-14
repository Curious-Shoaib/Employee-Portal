package com.myApp.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Demand {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer demandId;
	@JoinColumn(name="project_id")
	@ManyToOne
	private Project project;
	Integer managerId;
	Integer reviewerId;
	
	@ManyToMany   // no cascading
	List<Employee> applicants;
	
	public Demand() {
		
	}
	
	public Demand(Integer managerId,Integer reviewer,Project project) {
		this.project = project;
		this.reviewerId = reviewer;
		this.managerId=managerId;
	}

	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Integer reviewer) {
		this.reviewerId = reviewer;
	}

	public List<Employee> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Employee> applicants) {
		this.applicants = applicants;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}	
	
}
