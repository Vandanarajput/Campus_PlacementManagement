package com.PlacementManagementSystem.Placement.controller;

import com.PlacementManagementSystem.Placement.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user") // All URLs start with /user
public class StudentDashboardController {

	// STEP 1: Show profile page
	@GetMapping("/profile")
	public String showProfile(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			return "redirect:/login";
		}

		model.addAttribute("user", user);
		return "userdashbored/studentProfile"; // 
	}

}
