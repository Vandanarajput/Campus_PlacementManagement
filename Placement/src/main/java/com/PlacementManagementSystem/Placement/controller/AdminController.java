package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.PlacementManagementSystem.Placement.repository.JobRepository;
import com.PlacementManagementSystem.Placement.repository.StudentRepository;

@Controller
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;  // Repository for student data
    @Autowired
    private JobRepository jobRepository;          // Repository for job listings
//    @Autowired
//    private ApplicationRepository applicationRepository;  // Repository for job applications
//    @Autowired
//    private MessageRepository messageRepository;  // Repository for messages

    @GetMapping("/admin")
    public String showAdminDashboard(Model model) {
        // Step 2: Fetch the counts from the database
        long totalStudents = studentRepository.count();  // Count total students
        long jobListings = jobRepository.count();        // Count total job listings
//        long applications = applicationRepository.count();  // Count total applications
//        long messages = messageRepository.count();        // Count total messages

        // Step 3: Add the counts to the model
        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("jobListings", jobListings);
//        model.addAttribute("applications", applications);
//        model.addAttribute("messages", messages);

        // Return the name of the Thymeleaf template to render
        return "dashboard/adminDashboared";  // This is the view template to display
    }
}
