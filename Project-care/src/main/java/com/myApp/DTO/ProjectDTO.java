package com.myApp.DTO;

import java.time.LocalDate;

public class ProjectDTO {

	
	Integer projectId;
	String projectName;
	LocalDate startingDate;
	String technology;
	Long budget;
	String client;
	
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
