package com.myApp.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ManyToAny;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;


@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer projectId;
	String projectName;
	LocalDate startingDate;
	@Column(name="tech")
	String technology;
	Long budget;
	String client;
	@OneToMany(mappedBy = "project")
	List<Employee> members;
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public LocalDate getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public Long getBudget() {
		return budget;
	}
	public void setBudget(Long budget) {
		this.budget = budget;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
	
}
