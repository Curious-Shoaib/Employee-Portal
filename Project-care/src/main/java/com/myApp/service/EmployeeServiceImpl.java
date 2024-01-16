package com.myApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.myApp.DTO.EmployeeDTO;
import com.myApp.convert.Converter;
import com.myApp.entity.Demand;
import com.myApp.entity.Employee;
import com.myApp.entity.Project;
import com.myApp.exception.ProjectException;
import com.myApp.repository.DemandRepository;
import com.myApp.repository.EmployeeRepository;
import com.myApp.repository.ProjectRepository;
import com.myApp.responseData.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	DemandRepository demandRepo;
	
	@Autowired
	Environment env;
	
	public Integer addEmployee(EmployeeDTO employeeDTO) throws ProjectException {
		
		Employee employee= Converter.convert(employeeDTO);
		employee=employeeRepo.save(employee);
		return employee.getEmployeeId();
		
	}
	
    public EmployeeDTO getEmployee(Integer eId) throws ProjectException {
		
		Optional<Employee> opt=employeeRepo.findById(eId);
		if(opt.isEmpty())
			throw new ProjectException(("EmpService.EMP_NOT_FOUND"));
		
		EmployeeDTO dto=Converter.convert(opt.get());
		return dto;
	}
    public List<EmployeeDTO> getAllEmployees() throws ProjectException{
    Iterable<Employee> it=	employeeRepo.findAll();
    	
    List<EmployeeDTO> list=new ArrayList<>();
    it.forEach((emp)->
    {
    	EmployeeDTO dto=Converter.convert(emp);
    	list.add(dto);
    });
    	if(list.isEmpty())
    		throw new ProjectException(("EmpService.PROJECT_NF"));
    	return list;
    	
    }
    //Manager will create demand for a technology
    public Integer createDemand(Integer managerId,Integer reviewerId,String technology) throws ProjectException{
    	Optional<Employee> opt=employeeRepo.findById(managerId);
    	Optional<Employee> optRev=employeeRepo.findById(reviewerId);
    	if(opt.isEmpty())
    		throw new ProjectException(("EmpService.EMP_NOT_FOUND"));
    	if(optRev.isEmpty())
    		throw new ProjectException(("EmpService.REV_NF"));
    	
    	String desig=opt.get().getDesignation();
    	if(desig==null || !desig.equals("Manager"))
    		throw new ProjectException(("EmpService.NOT_A_MANAGER"));
    	
    	List<Project> projects=projectRepo.findByTechnology(technology);
    	if(projects.isEmpty())
    		throw new ProjectException(("EmpService.PROJECT_NA_FOR_THIS_TECH"));
    	
    	Demand demand=new Demand(managerId,reviewerId,projects.get(0));
    	return demandRepo.save(demand).getDemandId();
    	
    }
    
    //Anyone can check for demand available
    public List<Demand> getAllDemand() throws ProjectException{
  
    	List<Demand> demands = demandRepo.findAll();
    	if(demands.isEmpty())
    		throw new ProjectException(("EmpService.DEMAND_NF"));
    	
    	return demands;
    }
    
    // unmapped employee can apply to demand
    public void applyToDemand(Integer empId,Integer demandId) throws ProjectException
    {
    	Optional<Employee> optEmp=employeeRepo.findById(empId);
    	if(optEmp.isEmpty())
    		throw new ProjectException(("EmpService.EMP_NOT_FOUND"));
    	Employee employee=optEmp.get();
    	
    	if(employee.getProject() != null)
    		throw new ProjectException(("EmpService.ALREADY_MAPPED"));
    			
    	Optional<Demand> opt=demandRepo.findById(demandId);
    	if(opt.isEmpty())
    		throw new ProjectException(("EmpService.DEMAND_NF"));
    	
    	Demand demand=opt.get();
    	if(demand.getManagerId()==empId)
    		throw new ProjectException(("EmpService.CANT_APPLY_TO_SELF"));
    	
    	demand.getApplicants().add(employee);
    	demandRepo.save(demand);				// employee added to applicants of a demand
    }
    
    @Override
    public String reviewApplicant(Integer reviewerId,Integer applicantId,Integer demandId) throws ProjectException{
    	
    	Optional<Employee> optEmp=employeeRepo.findById(reviewerId);
    	if(optEmp.isEmpty())
    		throw new ProjectException(("EmpService.REV_NF"));
    	
    	Optional<Demand> opt=demandRepo.findById(demandId);
    	if(opt.isEmpty())
    		throw new ProjectException(("EmpService.DEMAND_NF"));
    	
    	Demand demand=opt.get();
    	if(!reviewerId.equals(demand.getReviewerId()))
    		throw new ProjectException(("EmpService.UNAUTH_NOT_A_REVIEWER"));
    	
    	Project project=demand.getProject();
    	String techNeed=project.getTechnology();
    	List<Employee> applicants=demand.getApplicants();

    	
    	Employee employee=null;
    	for(Employee e : applicants)
    	{
    		if(e.getEmployeeId().equals(applicantId))
    		{
    			employee=e;
    			break;
    		}
    		
    	}
    	if(employee==null)
    	{
    		throw new ProjectException(("EmpService.EMP_NOT_APPLIED"));
    	}
    		String response=null;
			if(employee.getSkills().equalsIgnoreCase(techNeed))
    	    {
    			 //Assigning manager
    			 employee.setManager(employeeRepo.findById(demand.getManagerId()).get());
    			 //Assigning project
    			 employee.setProject(project);
    			 employeeRepo.save(employee);
    			 response=(env.getProperty(("EmpService.PROJECT_ASSIGNED_TO_APPLICANT")) + project.getProjectId() + " & manager is :" +demand.getManagerId());
    	    }
    		 else
    			 response=env.getProperty("EmpService.APPLI_NOT_FIT")+employee.getEmployeeId();
    		 //employee is removed from applicants bcz he is assinged to a project
			applicants.remove(employee);
    		 demandRepo.save(demand);
    	
    	return response;
    
    }
    
    @Override
    public List<String> reviewAllApplicants(Integer reviewerId,Integer demandId) throws ProjectException{
    	Optional<Employee> optEmp=employeeRepo.findById(reviewerId);
    	if(optEmp.isEmpty())
    		throw new ProjectException(("EmpService.EMP_NOT_FOUND"));
    	
    	String desig=optEmp.get().getDesignation();	
    	
    	Optional<Demand> opt=demandRepo.findById(demandId);
    	if(opt.isEmpty())
    		throw new ProjectException(("EmpService.DEMAND_NF"));
    	
    	Demand demand=opt.get();
    	if(reviewerId != demand.getReviewerId())
    		throw new ProjectException(("EmpService.UNAUTH_NOT_A_REVIEWER"));
    	
    	Project project=demand.getProject();
    	String techNeed=project.getTechnology();
    	List<Employee> applicants=demand.getApplicants();
    	
    	if(applicants.isEmpty())
    		throw new ProjectException(("EmpService.APPLI_NF"));
    	
    	List<String> responses=new ArrayList<>();
    	
    	//all applicants stored in new list 'applicantsList', so that we can do concurrent operation on database entity, directly
    	// we can not traverse and modify the data base entity (applicants) , jdk throws concurrentOperation exception
    	List<Employee> applicantsList=new ArrayList<>(applicants);
    	for(Employee employee : applicantsList)
    	{
    		
			if(employee.getSkills().equalsIgnoreCase(techNeed))
    	    {
    			 // map this employee
    			 List<Employee> managers=employeeRepo.findManagerByTech(techNeed);
    			 if(managers.isEmpty())
    				 throw new ProjectException(("EmpService.MANAGER_NF"));
    			 employee.setDesignation("Mapped");
    			 //Assigning manager
    			 employee.setManager(managers.get(0));
    			 //Assigning project
    			 employee.setProject(project);
    			 employeeRepo.save(employee);
    			 responses.add(env.getProperty(("EmpService.PROJECT_ASSIGNED_TO_APPLICANT")) + project.getProjectId() + " & manager is :" +demand.getManagerId());
    	    }
    		 else
    			 responses.add(env.getProperty(("EmpService.APPLI_NOT_FIT"))+employee.getEmployeeId());
    		 //employee is removed from applicants bcz he is assinged to a project
    		 demand.getApplicants().remove(employee);
    		 demandRepo.save(demand);
    	}
    	return responses;
    }
}