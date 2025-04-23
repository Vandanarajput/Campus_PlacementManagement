package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PlacementManagementSystem.Placement.model.StudentProfile;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.service.StudentProfileService;
import com.PlacementManagementSystem.Placement.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class StudentProfileController {

	@Autowired
	private StudentProfileService studentProfileService;

	@Autowired
	private UserService userService; // Injecting the UserService

	// ✅ Show the Edit Profile Page
	@GetMapping("/edit-profile")
	public String showEditProfile(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			return "redirect:/login"; // if user is not logged in, redirect to login
		}

		// Use UserService to fetch the logged-in user details by email
		User loggedInUser = userService.getUserByEmail(user.getEmail());

		// Get existing profile or create a new one
		StudentProfile profile = studentProfileService.getProfileByUser(loggedInUser);
		if (profile == null) {
			profile = new StudentProfile();
			profile.setUser(loggedInUser); // link the profile to the current user
		}

		model.addAttribute("profile", profile);
		return "userdashbored/studentProfileEdit"; // return edit form view
	}

	// ✅ Save the Profile
	@PostMapping("/save-profile")
	public String saveProfile(@ModelAttribute("profile") StudentProfile profile, HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			return "redirect:/login"; // if user is not logged in, redirect to login
		}

		// Make sure the profile is linked to the logged-in user
		profile.setUser(user);
		studentProfileService.saveProfile(profile);

		return "redirect:/user/profile"; // redirect after save
	}
}
