package edu.sjsu.cmpe275.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		System.out.println("Hello");
		SpringApplication.run(ProjectApplication.class, args);
	}

}
