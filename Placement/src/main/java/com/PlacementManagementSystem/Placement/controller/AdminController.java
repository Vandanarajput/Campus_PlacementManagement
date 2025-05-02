package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.PlacementManagementSystem.Placement.model.StudentSkills;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.service.ApplicationService;
import com.PlacementManagementSystem.Placement.service.StudentSkillsService;
//import com.PlacementManagementSystem.Placement.service.UserJobService;
import com.PlacementManagementSystem.Placement.service.UserService;

import jakarta.servlet.http.HttpSession;

//import java.util.List;

@Controller

public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentSkillsService skillService;
    
//    
//    @Autowired
//    private UserJobService userJobService;
    
    @Autowired
    private   ApplicationService applicationService;

    @GetMapping("/admin")
    public String showAdminDashboard(Model model , HttpSession session) {
        User user = (User) session.getAttribute("loggedInAdmin");
        if (user == null) {
            return "redirect:/login";
        }
        long totalStudents = userService.countByRole("STUDENT");
        model.addAttribute("totalStudents", totalStudents);
        return "dashboard/adminDashboared";
    }

    @GetMapping("/admin/skills")
    public String showAdminSkills(Model model) {
        model.addAttribute("studentSkills", new StudentSkills());
        model.addAttribute("skills", skillService.getAllSkills());
        return "dashboard/skills"; // This is your Thymeleaf HTML page
    }

    @PostMapping("/admin/skills")
    public String addSkill(@ModelAttribute("skill") StudentSkills skill) {
        skillService.addSkill(skill);
        return "redirect:/admin/skills";
    }
    
    @GetMapping("/admin/skills/delete/{id}")
    public String deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id); // Call service method to delete the skill
        return "redirect:/admin/skills"; // Redirect back to the skills page
    }
    
    @GetMapping("/admin/applied-jobs")
    public String viewAllAppliedJobs(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInAdmin");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("applications", applicationService.getAllApplications());
        return "dashboard/application_list";
    }
    
   


    // NEW: Update job application status
    @PostMapping("/admin/update-status")
    public String updateApplicationStatus(@RequestParam Long appId, @RequestParam String status) {
    	applicationService.updateApplicationStatus(appId, status);
        return "redirect:/admin/applied-jobs";
    }
}
