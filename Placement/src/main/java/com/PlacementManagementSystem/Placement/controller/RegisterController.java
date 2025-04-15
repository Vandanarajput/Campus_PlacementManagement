package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.PlacementManagementSystem.Placement.model.LoginUser;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

//	   Registration Mapping

	@GetMapping("/registerUser")
	public String showRegiterForm(Model model) {
		model.addAttribute("user", new User());

		return "users/registerForm";

	}

	@PostMapping("/Saveregister")
	public String submitRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "users/registerForm";
		}

		User existingUser = userService.getUserByEmail(user.getEmail());
		if (existingUser != null) {
			model.addAttribute("error", "Email already registered!");
			return "users/registerForm";
		}

		if (!user.getPassword().equals(user.getConfirm_password())) {
			model.addAttribute("error", "Passwords do not match!");
			return "users/registerForm";
		}

		userService.saveUser(user);
		redirectAttributes.addFlashAttribute("message", "Registration successful!");
		return "redirect:/login";
	}

//      Login Mapping

	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "users/LoginForm";

	}

	@PostMapping("/login")
	public String Savelogin(@ModelAttribute("user") @Valid LoginUser loginUser, BindingResult result, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		// Check for validation errors
		if (result.hasErrors()) {
			return "users/LoginForm"; // Form field errors
		}

		// Fetch user from DB
		User existingUser = userService.getUserByEmail(loginUser.getEmail());
		System.out.println(loginUser.getEmail()); // Check if user exists
		if (existingUser == null) {
			model.addAttribute("error", "User not found with this email");
			return "users/LoginForm";
		}

		// Check password match
		if (!existingUser.getPassword().equals(loginUser.getPassword())) {
			model.addAttribute("error", "Incorrect password");
			return "users/LoginForm";
		}

		// Set session
		session.setAttribute("loggedInUser", existingUser);

		// Check role and redirect accordingly
		// Check role and redirect accordingly
		// for normal user, send to home page
		if ("ADMIN".equalsIgnoreCase(existingUser.getRole())) {
		    return "redirect:/admin"; // for admin
		} else {
		    redirectAttributes.addFlashAttribute("message", "Login successful!");
		    return "redirect:/"; // for normal user, send to home page
		}

	}

}
