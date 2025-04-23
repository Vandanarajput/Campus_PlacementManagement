package com.PlacementManagementSystem.Placement.controller;

import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String showAdminDashboard(Model model , HttpSession session) {
    	
		User user = (User) session.getAttribute("loggedInUser");
		
		if (user==null) {
			return "redirect:/login";
		}

    	
        long totalStudents = userService.countByRole("STUDENT");  // Count of students
        
        model.addAttribute("totalStudents", totalStudents);       // Pass to UI
        return "dashboard/adminDashboared";
    }
}
