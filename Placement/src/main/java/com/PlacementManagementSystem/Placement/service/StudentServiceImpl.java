package com.PlacementManagementSystem.Placement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlacementManagementSystem.Placement.model.Student;
import com.PlacementManagementSystem.Placement.repository.StudentRepository;

/**
 * This class implements the StudentService interface and provides concrete
 * methods to manage student data in the Campus Placement Management System. It
 * interacts with the StudentRepository to perform CRUD operations (Create,
 * Read, Update, Delete) on student records.
 */
@Service
public class StudentServiceImpl implements StudentService {

	// Injecting the StudentRepository bean to interact with the database.
	@Autowired
	private StudentRepository studentRepository;

	/**
	 * Retrieves all students from the database.
	 * 
	 * @return a list of all students.
	 */
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll(); // Fetches all students from the repository.
	}

	/**
	 * Retrieves a student by their unique ID. If the student is not found, it
	 * returns null (could also throw a custom exception).
	 * 
	 * @param id the unique identifier of the student.
	 * @return the student object if found, otherwise null.
	 */
	@Override
	public Student getStudentById(Long id) {
		Optional<Student> student = studentRepository.findById(id); // Fetches student by ID.
		return student.orElse(null); // Returns the student or null if not found.
		// Alternatively, you could throw an exception if you prefer.
	}

	/**
	 * Saves or updates a student in the database. If the student is new, it will be
	 * created; if the student exists, their data will be updated.
	 * 
	 * @param student the student object to be saved or updated.
	 * @return the saved or updated student object.
	 */
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student); // Saves or updates the student.
	}

	/**
	 * Deletes a student by their unique ID.
	 * 
	 * @param id the unique identifier of the student to be deleted.
	 */
	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id); // Deletes the student by ID from the repository.
	}
}
