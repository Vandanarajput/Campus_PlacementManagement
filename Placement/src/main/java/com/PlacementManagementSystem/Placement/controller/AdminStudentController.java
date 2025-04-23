package com.PlacementManagementSystem.Placement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.service.UserService;

@Controller
public class AdminStudentController {

    @Autowired
    private UserService userService;  // Service to handle user data

    // Admin Dashboard - Student Section
    @GetMapping("/admin/students")  // Map to '/admin/students'
    public String showStudentList(Model model) {
        // Fetch all users with the role "STUDENT"
        List<User> users = userService.getUsersByRole("STUDENT");

        // Add the list of users to the model
        model.addAttribute("users", users);

        // Return the student section view template
        return "dashboard/students";  // This will be the view for listing students
    }
    
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        userService.deleteStudent(id);  // Calls the service to delete user
        return "redirect:/admin/students";  // Redirects to the updated list
    }

}
