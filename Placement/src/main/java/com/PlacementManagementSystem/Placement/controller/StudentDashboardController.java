package com.PlacementManagementSystem.Placement.controller;

import java.io.File;
import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.model.UserJob;
import com.PlacementManagementSystem.Placement.service.ApplicationService;
import com.PlacementManagementSystem.Placement.service.JobService;
import com.PlacementManagementSystem.Placement.service.StudentSkillsService;
import com.PlacementManagementSystem.Placement.service.UserService;

//import com.PlacementManagementSystem.Placement.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class StudentDashboardController {

	@Autowired
	private JobService jobService;
	@Autowired
	private StudentSkillsService studentSkillsService;

	@Autowired
	private UserService userService;

	@Autowired
	ApplicationService applicationService;
//
//    @Autowired
//    private UserService userService;

	// Step 1: Show Job Application form (Personal Information)
	@GetMapping("/applyJob/{jobId}")
	public String applyJob(@PathVariable Long jobId, HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login"; // Redirect to login if not logged in
		}

		Job job = jobService.getJobById(jobId);
		model.addAttribute("job", job);
		model.addAttribute("currentStep", 1); // Step 1
		model.addAttribute("application", new Application());
		model.addAttribute("user", user);
		model.addAttribute("id", user.getId());
		model.addAttribute("allSkill", studentSkillsService.getAllSkills());
		return "userdashbored/job-apply"; // Display job application form
	}

	// Step 2: Handle Personal Information and move to Resume Upload
	@PostMapping("/applyJob/step2")
	public String step2(@ModelAttribute User user, HttpSession session, Model model, @RequestParam Long jobId) {

		System.out.println("user id : " + user.getId());

		Job job = jobService.getJobById(jobId);
		model.addAttribute("job", job);

		UserJob userJob = new UserJob();
		userJob.setJob(job);
		userJob.setUser(user);

		user.setUserJobs(List.of(userJob));
		user.setPassword(user.getPassword());
		user.setConfirmPassword(user.getConfirmPassword());
		
//		Application application = new Application();
//		application.setUser(user);
//		application.setJob(job);
//		application.setAppliedDate(LocalDate.now());
//		application.setStatus("applied");
//		application.setResumeFile(user.getResumeFile());
//
//		applicationService.applyForJob(application);

		userService.saveUser(user);

		

		return "userdashbored/job-apply"; // Display the resume upload form
	}

	// Step 3: Handle Resume Upload and move to Review Application
	@PostMapping("/applyJob/step3")
	public String step3(@RequestParam("resumeFile") MultipartFile resumeFile, HttpSession session, Model model,
			@RequestParam Long jobId) {

		// Get the logged-in user
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login"; // Redirect to login if not logged in
		}

		// Retrieve the job details
		Job job = jobService.getJobById(jobId);
		model.addAttribute("job", job);

		// Retrieve the application from the session
		Application application = (Application) session.getAttribute("application");
		if (application == null) {
			return "redirect:/login"; // Redirect if the application session is lost
		}

		// Handle the resume upload
		String uploadDir = "D:\\uploads"; // Define your upload directory path
		try {
			// Ensure the directory exists
			File dir = new File(uploadDir);
			if (!dir.exists()) {
				dir.mkdirs(); // Create the directory if it doesn't exist
			}

			// Get the original file name and save it
			String fileName = resumeFile.getOriginalFilename();
			File file = new File(uploadDir + "/" + fileName);

			// Optional: Check if the file already exists and handle it (e.g., append
			// timestamp)
			if (file.exists()) {
				String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
				file = new File(uploadDir + "/" + uniqueFileName); // Create a unique file name
			}

			// Save the file to the server
			resumeFile.transferTo(file);

			// Update the application with the resume file name
			application.setResumeFileType(file.getName());

			// Save the updated application back to the session
			session.setAttribute("application", application);
		} catch (IOException e) {
			e.printStackTrace(); // Log the error
			model.addAttribute("error", "Failed to upload resume. Please try again.");
			return "userdashbored/job-apply"; // Stay on the current page and show error
		}

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
			return "redirect:/login"; // Redirect to login if not logged in
		}

		Application application = (Application) session.getAttribute("application");
		if (application == null) {
			return "redirect:/login"; // Redirect if the application session is lost
		}

		// Submit the application (this can include saving the application to the
		// database)
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
		if (user == null)
			return "redirect:/login"; // Redirect to login if not logged in
		model.addAttribute("user", user);
		return "userdashbored/studentAppliedJob"; // Show list of applied jobs
	}

	// Profile page for Step 1 and to verify the login
	@GetMapping("/profile")
	public String showProfile(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login"; // Redirect to login if not logged in
		}
		model.addAttribute("user", user);
		return "userdashbored/studentProfileSec"; // Show profile page
	}

}
