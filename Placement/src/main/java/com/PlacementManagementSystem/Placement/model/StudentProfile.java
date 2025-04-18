package com.PlacementManagementSystem.Placement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profile")
public class StudentProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String phone;

	private String address;

	private String education;

	private String skills;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public StudentProfile() {
	}

	public StudentProfile(String phone, String address, String education, String skills, User user) {
		this.phone = phone;
		this.address = address;
		this.education = education;
		this.skills = skills;
		this.user = user;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
