package com.PlacementManagementSystem.Placement.service;

import java.util.List;

import com.PlacementManagementSystem.Placement.model.Student;

/**
 * The StudentService interface is responsible for defining the methods related
 * to the management of student records in the Campus Placement Management
 * System. It includes operations to retrieve, save, and delete student
 * information, and it serves as an essential part of the admin dashboard
 * functionality for the student section.
 */
public interface StudentService {

	/**
	 * Retrieves the list of all students in the system.
	 * 
	 * @return a list of all students.
	 */
	List<Student> getAllStudents();

	/**
	 * Retrieves a specific student based on their unique ID.
	 * 
	 * @param id the unique identifier of the student.
	 * @return the student corresponding to the provided ID.
	 */
	Student getStudentById(Long id);

	/**
	 * Saves a new student or updates an existing student in the system.
	 * 
	 * @param student the student object to be saved.
	 * @return the saved or updated student.
	 */
	Student saveStudent(Student student);

	/**
	 * Deletes a student from the system based on their unique ID.
	 * 
	 * @param id the unique identifier of the student to be deleted.
	 */
	void deleteStudent(Long id);
}
