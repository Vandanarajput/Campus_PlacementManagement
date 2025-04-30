package com.PlacementManagementSystem.Placement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.model.UserJob;
import com.PlacementManagementSystem.Placement.service.JobService;
import com.PlacementManagementSystem.Placement.service.UserJobService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private JobService jobService;
    @Autowired
    private UserJobService userJobService;

    // Landing page
    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("jobList", jobService.getAllJobs());
        return "Home/index"; // Home page view
    }

    // Profile page
    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login"; // No one logged in
        }

        model.addAttribute("user", user); // Pass user info to the view
        return "userdashbored/studentDashboard"; // Profile view
    }

    // Logout page
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/"; // Redirect to landing page
    }

    // Find Jobs page
    @GetMapping("/jobsLanding")
    public String findJobsPage(Model model, HttpSession session) {
        // If needed, you can fetch additional data related to jobs here
        model.addAttribute("jobList", jobService.getAllJobs()); // Pass job list to the view
        
        User user = (User)session.getAttribute("user");
        
        List<UserJob> userJobs = userJobService.getAllUserJobsByUserId(user.getId());
        
        List<Long> jobIds = userJobs.stream().map(uj -> uj.getJob().getId()).collect(Collectors.toList());
        model.addAttribute("jobIds", jobIds);
        
        return "Home/jobsLanding"; // Return view for Find Jobs page (jobsLanding.html)
    }
    
    
   

}
