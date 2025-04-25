package com.PlacementManagementSystem.Placement.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String companyName;
    private String location;
    private String jobType;       // ✅ New field
    @Lob
    private String description;
    private LocalDate deadline;
    private String salary;

    public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Job() {}

    public Job(String title, String companyName, String location, String jobType, String description, LocalDate deadline) {
        this.title = title;
        this.companyName = companyName;
        this.location = location;
        this.jobType = jobType;
        this.description = description;
        this.deadline = deadline;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
