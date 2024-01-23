package com.myApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.myApp.exception.AttendanceException;
import com.myApp.service.AttendanceService;

@SpringBootApplication
@EnableScheduling
public class EmployeePortal implements CommandLineRunner  {

	@Autowired
	AttendanceService att;
	public static void main(String[] args) {
		SpringApplication.run(EmployeePortal.class, args);
	}
	public void run(String[] args) throws AttendanceException
	{
		// write statements here that are needed at the time of starting of application.
	}

}