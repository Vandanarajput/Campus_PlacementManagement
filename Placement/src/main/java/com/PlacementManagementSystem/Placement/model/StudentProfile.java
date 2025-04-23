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

    private String linkedinProfile; // Optional field for LinkedIn

    private String resume; // Field to store resume link or file path

    private String experience; // Field to store job experience details

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public StudentProfile() {
    }

    public StudentProfile(String phone, String address, String education, String skills, 
                          String linkedinProfile, String resume, String experience, User user) {
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.linkedinProfile = linkedinProfile;
        this.resume = resume;
        this.experience = experience;
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

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
