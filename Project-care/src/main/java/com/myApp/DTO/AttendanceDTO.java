package com.myApp.DTO;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class AttendanceDTO {

	Integer employeeId;
	Integer managerId;
	LocalTime punchIn;
	LocalTime punchOut;
	public LocalTime getPunchIn() {
		return punchIn;
	}
	public void setPunchIn(LocalTime punchIn) {
		this.punchIn = punchIn;
	}
	public LocalTime getPunchOut() {
		return punchOut;
	}
	public void setPunchOut(LocalTime punchOut) {
		this.punchOut = punchOut;
	}
	LocalDate date;
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	

}
