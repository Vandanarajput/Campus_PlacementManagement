package com.PlacementManagementSystem.Placement.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.model.UserJob;
import com.PlacementManagementSystem.Placement.service.ApplicationService;
import com.PlacementManagementSystem.Placement.service.JobService;
import com.PlacementManagementSystem.Placement.service.SavedJobService;
import com.PlacementManagementSystem.Placement.service.StudentSkillsService;
import com.PlacementManagementSystem.Placement.service.UserJobService;
import com.PlacementManagementSystem.Placement.service.UserService;

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
	@Autowired
	private UserJobService userJobService;
	
    @Autowired
    private SavedJobService savedJobService;

	private Map<Long, User> STORED_USERS_FOR_JOB = new HashMap<Long, User>();

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

		// Get the logged-in user from session
		User sessionUser = (User) session.getAttribute("loggedInUser");

		if (sessionUser == null) {
			// Handle session expired or not logged in
			return "redirect:/login";
		}

		// Fetch the latest user from DB to ensure valid ID and data
		User mainUser = userService.getStudentById(sessionUser.getId());

		Job job = jobService.getJobById(jobId);
		model.addAttribute("job", job);

		UserJob userJob = new UserJob();
		userJob.setJob(job);
		userJob.setUser(mainUser);

		Application application = new Application();
		application.setUser(mainUser);
		application.setJob(job);
		application.setAppliedDate(LocalDate.now());
		application.setStatus("applied");
		application.setResumeFile(user.getResumeFile());

		user.setApplications(List.of(application));
		user.setUserJobs(List.of(userJob));
		user.setPassword(user.getPassword());
		user.setConfirmPassword(user.getConfirmPassword());

		// just store it so that later on we can find and save into db after taking
		// review consent from candidate
		STORED_USERS_FOR_JOB.put(user.getId(), user);

		model.addAttribute("user", user);
		return "userdashbored/reviewApp";
	}

	@PostMapping("/saveAfterReview")
	public String saveAfterreview(@RequestParam Long userId, @RequestParam(required = false) Boolean isAgreed,
			Model model, RedirectAttributes redirectAttributes) {

		User user = STORED_USERS_FOR_JOB.get(userId);
		if (isAgreed == null || !isAgreed) {
			model.addAttribute("errorMessage", "Please agree the terms and conditions");
			model.addAttribute("user", user);
			return "userdashbored/reviewApp";
		}

		// save the User
		if (isAgreed != null && isAgreed) {
			userService.saveUser(user);
		}

		redirectAttributes.addFlashAttribute("success", "Your application has been submitted successfully!");

		return "redirect:/jobsLanding";
	}

	@GetMapping("/jobs")
	public String viewAppliedJobs(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			return "redirect:/login";
		}

		List<UserJob> userJobs = userJobService.getAllUserJobsByUserId(user.getId());
		System.out.println("User ID: " + user.getId());
//	    System.out.println("Jobs fetched = " + userJobs.size()); 
		model.addAttribute("userJobs", userJobs);

		return "userdashbored/studentAppliedJob"; // Create this HTML page
	}

	@GetMapping("/applicationstatus")
	public String showApplicationStatus(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login";
		}
		// Get the applications for the user
		List<Application> userApplications = applicationService.getApplicationsByUser(user);
		model.addAttribute("userApplications", userApplications);
		return "userdashbored/studentApplicationStatus"; // Show list of applied jobs
	}

	// Profile page for Step 1 and to verify the login
	@GetMapping("/profile")
	public String showProfile(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login"; // Redirect to login if not logged in
		}
		
		User existingUser = userService.getUserByEmail(user.getEmail());

		if (existingUser == null) {
			return "redirect:/login"; // Redirect to login if not logged in
		}
		model.addAttribute("user", existingUser);
		return "userdashbored/studentProfileSec"; // Show profile page
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute("user") User user) {
		
//		User existingUser = userService.getUserByEmail(user.getEmail());
//		
//		if (existingUser!=null) {
//		}
		userService.saveUser(user);
		
		// Save the updated user details

		return "redirect:/profile"; // Redirect to the profile page after update
	}
     
	 // Save job for the user
    @PostMapping("/save-job")
    public String saveJob(@RequestParam("jobId") Long jobId, Principal principal) {
        // Get the user from the authenticated principal
        Long userId = getUserIdFromPrincipal(principal); // You need to implement this method

        // Save the job for the user
        savedJobService.saveJobForUser(userId, jobId);

        // Redirect to the user's dashboard or another appropriate page
        return "redirect:/dashboard";
    }
    
    

    private Long getUserIdFromPrincipal(Principal principal) {
        // Retrieve the user ID from the logged-in user's principal (Assuming you have UserService)
        return userService.getUserByEmail(principal.getName()).getId();
    }
	
}
