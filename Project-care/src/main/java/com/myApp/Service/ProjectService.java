package com.myApp.Service;

import com.myApp.DTO.ProjectDTO;
import com.myApp.exception.ProjectException;

public interface ProjectService {

	
	public void addProject(ProjectDTO projectDTO) throws ProjectException;
}
