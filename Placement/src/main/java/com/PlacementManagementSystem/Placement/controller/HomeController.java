package com.PlacementManagementSystem.Placement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.PlacementManagementSystem.Placement.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/")
	public String landingPage() {
		return "Home/index";
	}

	// Profile page
	@GetMapping("/profile")
	public String profilePage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");

		if (user == null) {
			return "redirect:/login"; // No one logged in
		}

		model.addAttribute("user", user); // 👈 Now Thymeleaf can show it
		return "users/profile";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
