package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.model.UserProfile;
import com.PlacementManagementSystem.Placement.service.JobService;
import com.PlacementManagementSystem.Placement.service.UserProfileService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class StudentDashboardController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        UserProfile userProfile = userProfileService.getUserProfileById(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userProfile", userProfile);
        return "userdashbored/studentProfileSec";
    }

    @GetMapping("/jobs")
    public String showAppliedJobs(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "userdashbored/studentAppliedJob";
    }

    @GetMapping("/applyJob/{id}")
    public String applyJob(@PathVariable Long id, Model model, HttpSession session) {
        Job job = jobService.getJobById(id);
        model.addAttribute("job", job);
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        model.addAttribute("application", new Application());
        return "userdashbored/job-apply";
    }

    @GetMapping("/updateProfile")
    public String showUpdateProfileForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        UserProfile userProfile = userProfileService.getUserProfileById(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userProfile", userProfile);
        return "userdashbored/studentProfileSec";
    }

	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute UserProfile updatedUserProfile, Model model, HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			return "redirect:/login";
		}
		
		// Validate profile data (you can add custom validation here)
		if (updatedUserProfile.getPhone() == null || updatedUserProfile.getPhone().isEmpty()) {
			model.addAttribute("error", "Phone number cannot be empty");
			model.addAttribute("user", user);
			model.addAttribute("userProfile", updatedUserProfile);
			return "userdashbored/studentProfileSec";
		}
		
		userProfileService.updateUserProfile(user.getId(), updatedUserProfile);
		model.addAttribute("user", user);
		model.addAttribute("userProfile", updatedUserProfile);
		return "redirect:/user/profile";
	}
	
}
