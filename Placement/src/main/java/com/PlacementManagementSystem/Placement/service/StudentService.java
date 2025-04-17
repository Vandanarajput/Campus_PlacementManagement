package com.PlacementManagementSystem.Placement.service;

import java.util.List;

import com.PlacementManagementSystem.Placement.model.Student;

public interface StudentService {
	List<Student> getAllStudents();
	

	Student getStudentById(Long id);

	Student saveStudent(Student student);

	void deleteStudent(Long id);
}

