package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.service.JobService;
import com.PlacementManagementSystem.Placement.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class StudentDashboardController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    // Step 1: Show Job Application form (Personal Information)
    @GetMapping("/applyJob/{jobId}")
    public String applyJob(@PathVariable Long jobId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";  // Redirect to login if not logged in
        }

        Job job = jobService.getJobById(jobId);
        model.addAttribute("job", job);
        model.addAttribute("currentStep", 1); // Step 1
        model.addAttribute("application", new Application());
        return "userdashbored/job-apply"; // Display job application form
    }

    // Step 2: Handle Personal Information and move to Resume Upload
    @PostMapping("/applyJob/step2")
    public String step2(@ModelAttribute Application application, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";  // Redirect to login if not logged in
        }

        // Save the application details temporarily in the session
        session.setAttribute("application", application);

        // Moving to Step 2: Upload Resume
        model.addAttribute("currentStep", 2); // Step 2
        model.addAttribute("application", application);
        return "userdashbored/job-apply"; // Display the resume upload form
    }

    // Step 3: Handle Resume Upload and move to Review Application
    @PostMapping("/applyJob/step3")
    public String step3(@RequestParam("resumeFile") MultipartFile resumeFile, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";  // Redirect to login if not logged in
        }

        Application application = (Application) session.getAttribute("application");
        if (application == null) {
            return "redirect:/login"; // Redirect if the application session is lost
        }

        // Handle the resume upload (you can save the resume file to the server)
        // For now, assume we save it and add the resume file name to the application
        application.setResumeFile(resumeFile.getOriginalFilename());

        // Save the updated application with resume
        session.setAttribute("application", application);

        // Moving to Step 3: Review Application
        model.addAttribute("currentStep", 3); // Step 3
        model.addAttribute("application", application);
        return "userdashbored/job-apply"; // Display application review form
    }

    // Step 4: Review and Submit the Application
    @PostMapping("/applyJob/submit")
    public String submitApplication(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";  // Redirect to login if not logged in
        }

        Application application = (Application) session.getAttribute("application");
        if (application == null) {
            return "redirect:/login"; // Redirect if the application session is lost
        }

        // Submit the application (this can include saving the application to the database)
        // For now, assume the application is submitted and we clear it from the session
        jobService.submitApplication(application); // Submit the application to the database

        // Clear the session
        session.removeAttribute("application");

        model.addAttribute("message", "Application submitted successfully!");
        return "userdashbored/job-apply"; // Show a success message or redirect
    }

    // Get Job Listing for Step 1
    @GetMapping("/jobs")
    public String showAppliedJobs(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";  // Redirect to login if not logged in
        model.addAttribute("user", user);
        return "userdashbored/studentAppliedJob"; // Show list of applied jobs
    }

    // Profile page for Step 1 and to verify the login
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";  // Redirect to login if not logged in
        }
        model.addAttribute("user", user);
        return "userdashbored/studentProfileSec"; // Show profile page
    }
}
