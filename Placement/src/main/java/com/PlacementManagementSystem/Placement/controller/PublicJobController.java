package com.PlacementManagementSystem.Placement.controller;

import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PublicJobController {

    @Autowired
    private JobService jobService;

    // Show all jobs for users
    @GetMapping("/jobs")
    public String showAllJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "dashboard/jobs"; // Use same HTML for displaying jobs
    }

    // View a specific job
    @GetMapping("/jobs/{id}")
    public String viewJobDetails(@PathVariable Long id, Model model) {
        Job job = jobService.getJobById(id);
        model.addAttribute("job", job);
        return "dashboard/job_details";
    }

    // Apply for a job
    @PostMapping("/user/apply/{id}")
    public String applyForJob(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        System.out.println("Student applied for Job: " + job.getTitle());
        // TODO: Save to DB later
        return "redirect:/jobs";
    }
}
