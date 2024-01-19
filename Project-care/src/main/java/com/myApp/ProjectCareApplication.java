package com.myApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCareApplication.class, args);
	}

}