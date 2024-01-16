package com.myApp.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ProjectDTO {

	
	@Pattern(regexp="[A-Z]{1}[a-z]{1,15}([\\s][A-Za-z]{1}[a-z]{1,15})*",message="{INVALID_NAME}")
	String projectName;
	@FutureOrPresent(message="{PAST_DATE}")
	LocalDate startingDate;
	@Pattern(regexp="[A-Z]+[A-Za-z]*",message="{INVALID_TECH}")
	String technology;
	Long budget;
	@NotBlank(message="{INVALID_CLIENT}")
	String client;
	
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
