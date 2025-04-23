package com.PlacementManagementSystem.Placement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne
//	@JoinColumn(name = "student_id")
//	private Student student;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;

	private String status; // "Pending", "Accepted", "Rejected"

	private LocalDate appliedDate;

	// Constructors
	public Application() {
		this.status = "Pending"; // default
		this.appliedDate = LocalDate.now();
	}
//
//	public Application(Student student, Job job) {
//		this.student = student;
//		this.job = job;
//		this.status = "Pending";
//		this.appliedDate = LocalDate.now();
//	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

//	public Student getStudent() {
//		return student;
//	}
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}
}
