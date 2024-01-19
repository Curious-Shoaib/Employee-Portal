package com.myApp.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer attendanceId;
	@Column(name="date")
	LocalDate attendanceDate;
	LocalTime punchIn;
	LocalTime punchOut;
	@Column(name="mode")		// either WFH or office
	String attendanceMode;
	@JoinColumn(name="emp_id")
	@ManyToOne
	Employee employee;
	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	
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
	public String getAttendanceMode() {
		return attendanceMode;
	}
	public void setAttendanceMode(String attendanceMode) {
		this.attendanceMode = attendanceMode;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
 }
