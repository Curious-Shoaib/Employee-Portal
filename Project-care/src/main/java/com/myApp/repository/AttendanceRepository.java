package com.myApp.repository;

import java.sql.Time;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myApp.entity.Attendance;
import com.myApp.entity.Employee;
import com.myApp.exception.AttendanceException;

import jakarta.transaction.Transactional;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

	@Query(value="select a from Attendance a where a.employee=:empId order by a.attendanceDate desc limit 1")
	public List<Attendance> getLetestAttendanceRecordByemployeeId(@Param("empId") Employee empId) throws AttendanceException;

	
	@Query("select count(a) from Attendance a where a.employee=:empId and a.attendanceMode ='WFH' order by a.attendanceDate desc limit 7")
	public Integer getWorkFromHomeCountOfEmployee(@Param("empId") Employee empId) throws AttendanceException;
	
	@Modifying
	@Transactional
	@Query("update Attendance a set a.attendanceMode='WFH' , a.punchIn=:punchIn , a.punchOut=:punchOut where a.employee=:empId")
	public void updateStatusToWfhOfEmployee(@Param("empId") Employee empId,@Param("punchIn") Time punchIn,@Param("punchOut") Time punchOut) throws AttendanceException;



}
