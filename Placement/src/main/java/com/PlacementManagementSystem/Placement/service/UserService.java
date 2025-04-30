package com.PlacementManagementSystem.Placement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.repository.UserRepositry;

@Service
public class UserService {

	@Autowired
	private UserRepositry userRepositry;

	// Save or update a user (both admin and student)
	public User saveUser(User user) {
		return userRepositry.save(user);
	}

	// Login user (authenticate using email and password)
	public User loginUser(String email, String password) {
		return userRepositry.LoginUser(email, password); // Fetch user by email & password
	}

	// Get user by email (for user-specific actions like updating profile)
	public User getUserByEmail(String email) {
		return userRepositry.findByEmail(email);
	}

	// Get all students (for admin to view all students)
	public List<User> getAllStudents() {
		return userRepositry.findByRole("Student"); // Fetch all users (students in this case)
	}

	// Delete a student by their ID (for admin to delete a student)
	public void deleteStudent(Long id) {
		userRepositry.deleteById(id); // This method will remove the student from the DB
	}

	// Get a student by ID (for admin to edit a specific student)
	public User getStudentById(Long id) {
		return userRepositry.findById(id).orElse(null); // Return student by ID, or null if not found
	}

	// Get all users with a specific role (for admin to filter by role, e.g.,
	// students)
	public List<User> getUsersByRole(String role) {
		return userRepositry.findByRole(role); // You may need to implement this method in the repository if needed
	}

	public long countByRole(String role) {
		return userRepositry.countByRole(role);
	}

}
