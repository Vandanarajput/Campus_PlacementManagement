package com.PlacementManagementSystem.Placement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PlacementManagementSystem.Placement.model.User;



@Repository
public interface UserRepositry extends JpaRepository<User, Long> {
	// Custom methods:check if email already exists
	
	
	@Query("from User where email = :email and password = :password")
	User LoginUser(String email,String password);

	User findByEmail(String email);
	
//	User findByEmail(String email); // Method to find a user by email
	
//	boolean existsByEmail(String email);

}
