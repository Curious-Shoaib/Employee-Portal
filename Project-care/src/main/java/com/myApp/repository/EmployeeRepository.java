package com.myApp.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myApp.entity.Employee;

import jakarta.transaction.Transactional;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	@Transactional
	@Modifying
	@Query("update Employee e set e.designation='Mapped'")
	public void updateEmployeeProject(Integer projectId);

	@Query(value="select * from employee e limit 1",nativeQuery = true)
	public List<Employee> findManagerByTech(String techNeed);
	
}
//e.project=:projectId & set 