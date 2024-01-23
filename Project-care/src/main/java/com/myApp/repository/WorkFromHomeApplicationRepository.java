package com.myApp.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myApp.entity.Employee;
import com.myApp.entity.WorkFromHomeApplication;
import com.myApp.exception.AttendanceException;

import jakarta.transaction.Transactional;

public interface WorkFromHomeApplicationRepository extends JpaRepository<WorkFromHomeApplication, Integer> {

	
	@Transactional
	@Modifying
	@Query("delete from WorkFromHomeApplication w where w.applicant=:applicant and w.dateOfAttendance=:date")
	public void deleteByApplicantAndDateOfApplication(@Param("applicant") Employee employee,@Param("date") LocalDate date) throws AttendanceException;

	
}
