package com.PlacementManagementSystem.Placement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;

	private byte[] resumeFile;
	private String resumeFileType;

	private String status; // "Pending", "Accepted", "Rejected"

	private LocalDate appliedDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getResumeFileType() {
		return resumeFileType;
	}

	public void setResumeFileType(String resumeFileType) {
		this.resumeFileType = resumeFileType;
	}

	public void setResumeFile(byte[] resumeFile) {
		this.resumeFile = resumeFile;
	}

	public byte[] getResumeFile() {
		return resumeFile;
	}

	// Constructors
	public Application() {
		this.status = "Pending"; // default
		this.appliedDate = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
//
//	public String getResumeFile() {
//		return resumeFile;
//	}
//
//	public void setResumeFile(String resumeFile) {
//		this.resumeFile = resumeFile;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
