package com.PlacementManagementSystem.Placement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
//import com.PlacementManagementSystem.Placement.model.Student;
import com.PlacementManagementSystem.Placement.service.ApplicationService;
import com.PlacementManagementSystem.Placement.service.JobService;
//import com.PlacementManagementSystem.Placement.service.StudentService;

@Controller
@RequestMapping("/admin/applications")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private JobService jobService;

 //  Admin: View all applications
	@GetMapping
	public String viewAllApplications(Model model) {
		List<Application> applications = applicationService.getAllApplications();
		model.addAttribute("applications", applications);
		return "dashboard/application_list"; // Create this Thymeleaf page
	}

	//  Student: Apply for a job
	@PostMapping("/apply")
	public String applyForJob(@RequestParam("jobId") Long jobId, @RequestParam("studentId") Long studentId) {

		Job job = jobService.getJobById(jobId);
//		Student student = studentService.getStudentById(studentId);

		Application application = new Application();
		application.setJob(job);
//		application.setStudent(student);
		application.setStatus("Pending");

		applicationService.applyForJob(application);

		return "redirect:/user/jobs"; // or wherever your student is redirected
	}

	//  Student: View own applications
	@GetMapping("/student/{studentId}")
	public String viewStudentApplications(@PathVariable Long studentId, Model model) {
//		Student student = studentService.getStudentById(studentId);
//		List<Application> apps = applicationService.getApplicationsByStudent(student);
//		model.addAttribute("applications", apps);
		return "student/my_applications"; // Create this page
	}

	//  Admin: Optionally update application status
	@PostMapping("/update-status")
	public String updateStatus(@RequestParam Long appId, @RequestParam String status) {
		applicationService.updateApplicationStatus(appId, status);
		return "redirect:/applications/admin";
	}
}
