package com.PlacementManagementSystem.Placement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Please enter a valid email")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;

	@Transient
	@NotBlank(message = "Confirm password is required")
	private String confirm_password;

	private String role = "Student";
	
//	 private String resume;
//	 
//	 // Getters and setters for all fields, including the resume
//	    public String getResume() {
//	        return resume;
//	    }
//
//	    public void setResume(String resume) {
//	        this.resume = resume;
//	    }

	public User() {

	}

	public User(String name, String email, String password, String confirm_password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirm_password = confirm_password;

	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setId(Long id) {
		
		this.id=id;
	}

	

}

//Query to Add Admin User to Database:

//INSERT INTO user (name, email, password, role)
//VALUES ('admin', 'admin@mail.com', 'admin123', 'ADMIN');
