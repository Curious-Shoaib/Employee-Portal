package com.myApp.api.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myApp.DTO.ProjectDTO;
import com.myApp.convert.Converter;
import com.myApp.entity.Project;
import com.myApp.repository.ProjectRepository;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/project")
public class ProjectApi {

	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	Environment env;
	@PostMapping("/")
	public ResponseEntity addProject( @RequestBody ProjectDTO dto) {
			
		Project project=Converter.convert(dto);
		
		project=projectRepo.save(project);
		return new ResponseEntity(env.getProperty("Project.PROJECT_ADDED") +project.getProjectId(), HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity getAllProjects() {
		
		List<ProjectDTO> projects=new ArrayList<>();
		Iterable<Project> it=projectRepo.findAll();
		
		it.forEach((pr)->
		{
			ProjectDTO dto=Converter.convert(pr);
			projects.add(dto);
		});
		if(projects==null || projects.isEmpty())
			return new ResponseEntity(env.getProperty("EmpService.PROJECT_NF"), HttpStatus.NOT_FOUND);
		return new ResponseEntity(projects, HttpStatus.OK);
	}
}
