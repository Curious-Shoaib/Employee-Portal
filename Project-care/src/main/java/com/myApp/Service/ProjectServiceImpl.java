package com.myApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myApp.DTO.ProjectDTO;
import com.myApp.convert.Converter;
import com.myApp.entity.Project;
import com.myApp.exception.ProjectException;
import com.myApp.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepo;
	
	
	public void addProject(ProjectDTO projectDTO) throws ProjectException {
		
		Project project=Converter.convert(projectDTO);
		projectRepo.save(project);
}
}
