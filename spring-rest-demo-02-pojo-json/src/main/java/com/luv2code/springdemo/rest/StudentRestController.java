package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	// define endpoint for "/student"
	// return a list of students
 
	@GetMapping("/students")
	public List<Student> getStudents() {

		List<Student> theStudents = new ArrayList<>();
		theStudents.add(new Student("Dragos", "Sandulescu"));
		theStudents.add(new Student("Vladut", "Margineanu"));
		theStudents.add(new Student("Roxana", "Scurtu"));

		return theStudents;
	}
}
