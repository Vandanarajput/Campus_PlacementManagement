package com.PlacementManagementSystem.Placement.controller;

import com.PlacementManagementSystem.Placement.model.StudentProfile;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.service.StudentProfileService;
import com.PlacementManagementSystem.Placement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class StudentProfileController {

	@Autowired
	private StudentProfileService studentProfileService; // Inject the service

	@Autowired
	private UserService userService; // Inject the UserService

	// STEP 1: Show Edit Profile Form
	@GetMapping("/edit")
	public String showEditProfile(@PathVariable("email") String email, Model model) {
		User user = userService.getUserByEmail(email); // Fetch the user by email

		// Fetch the profile by user (if it doesn't exist, create a new one)
		StudentProfile profile = studentProfileService.getProfileByUser(user);
		if (profile == null) {
			profile = new StudentProfile(); // Create an empty profile if none exists
			profile.setUser(user); // Link the profile to the user
		}

		model.addAttribute("profile", profile); // Add profile to the model for the view
		return "dashboard/studentProfileEdit"; // Render the editProfile view (Thymeleaf template)
	}

	// STEP 2: Save or Update Profile
	@PostMapping("/save")
	public String saveProfile(@ModelAttribute("profile") StudentProfile profile) {
		// Save or update the profile
		studentProfileService.saveProfile(profile);

		return "redirect:/dashboard"; // After saving, redirect to the dashboard (or profile page)
	}
}
