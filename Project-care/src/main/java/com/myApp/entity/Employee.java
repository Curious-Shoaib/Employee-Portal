package com.myApp.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer employeeId;
	private String employeeName;
	private String mobileNo;
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="emp_address")
	private Address address;
	@OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
	private List<Attendance> attendance;
	private String designation;  // Manager , System Engineer,
	private String dcName; 
	private Integer age;
	private String gender;
	private LocalDate joiningDate;
	 @ManyToOne
	 @JoinColumn(name = "project_id")
	private Project project;
	@ManyToOne
	@JoinColumn(name="manager_id")
	private Employee manager;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<WorkFromHomeApplication> myWorkFromHomeApplications;
	@OneToMany(cascade = CascadeType.ALL)
	private List<WorkFromHomeApplication> WorkFromHomAappliedToMe;;
	private Integer unit;
	private String skills;  // skills separated by comma
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Attendance> getAttendance() {
		return attendance;
	}
	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDcName() {
		return dcName;
	}
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public List<WorkFromHomeApplication> getMyWorkFromHomeApplications() {
		return myWorkFromHomeApplications;
	}
	public void setMyWorkFromHomeApplications(List<WorkFromHomeApplication> myWorkFromHomeApplications) {
		this.myWorkFromHomeApplications = myWorkFromHomeApplications;
	}
	public List<WorkFromHomeApplication> getWorkFromHomeappliedToMe() {
		return WorkFromHomAappliedToMe;
	}
	public void setWorkFromHomeappliedToMe(List<WorkFromHomeApplication> workFromHomeappliedToMe) {
		WorkFromHomAappliedToMe = workFromHomeappliedToMe;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", address=" + address + ", attendance=" + attendance + ", designation="
				+ designation + ", dcName=" + dcName + ", age=" + age + ", gender=" + gender + ", joiningDate="
				+ joiningDate + ", project=" + project + ", manager=" + manager + ", myWorkFromHomeApplications="
				+ myWorkFromHomeApplications + ", WorkFromHomAappliedToMe=" + WorkFromHomAappliedToMe + ", unit=" + unit
				+ ", skills=" + skills + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(WorkFromHomAappliedToMe, address, age, attendance, dcName, designation, email, employeeId,
				employeeName, gender, joiningDate, manager, mobileNo, myWorkFromHomeApplications, project, skills,
				unit);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return this.employeeId==other.getEmployeeId();
	}
	
	
	
	
	
	
}
