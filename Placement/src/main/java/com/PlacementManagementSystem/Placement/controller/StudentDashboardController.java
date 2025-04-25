package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.service.JobService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user") // All URLs start with /user
public class StudentDashboardController {

	@Autowired
	private JobService jobService;

//	@Autowired
//	private UserService userService; // Assuming a service for user handling

	// STEP 1: Show profile page
	@GetMapping("/profile")
	public String showProfile(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			return "redirect:/login"; // Redirect to login if user is not logged in
		}

		model.addAttribute("user", user);
		return "userdashbored/studentProfileSec"; // Assuming your profile page
	}

	// Show applied jobs
	@GetMapping("/jobs")
	public String showAppliedJobs(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null)
			return "redirect:/login";

		model.addAttribute("user", user);
		return "userdashbored/studentAppliedJob"; // Page for showing applied jobs
	}

	// Apply for a specific job
	@GetMapping("/applyJob/{id}")
	public String applyJob(@PathVariable Long id, Model model, HttpSession session) {
		Job job = jobService.getJobById(id);
		model.addAttribute("job", job);

		User user = (User) session.getAttribute("loggedInUser");
		if (user == null)
			return "redirect:/login"; // If not logged in, redirect to login

		model.addAttribute("user", user);
		model.addAttribute("application", new Application());
		return "userdashbored/job-apply"; // Page for applying to job
	}

}
