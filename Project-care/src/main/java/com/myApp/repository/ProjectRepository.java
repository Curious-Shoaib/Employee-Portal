package com.myApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myApp.entity.Project;


public interface ProjectRepository  extends CrudRepository<Project, Integer>{

	
//	@Query("select p from Project p where p.tech=:technology")
	public List<Project> findByTechnology(String technology);
	
}
