package com.PlacementManagementSystem.Placement.controller;

import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/jobs")
public class JobController {

	@Autowired
	private JobService jobService;

	// Show all jobs
	@GetMapping
	public String viewJobsPage(Model model) {
		model.addAttribute("jobs", jobService.getAllJobs());
		return "dashboard/jobs"; // This is where you’ll display the list of jobs
	}

	// Show form to add a new job
	@GetMapping("/add")
	public String showAddJobForm(Model model) {
		model.addAttribute("job", new Job());
		return "dashboard/add_jobs"; // Form to add a job
	}

	// Handle saving a new job
	@PostMapping("/save")
	public String saveJob(@ModelAttribute("job") Job job) {
		jobService.saveJob(job);
		return "redirect:/admin/jobs"; // After saving, redirect to job list
	}

	// Delete a job by ID
	@GetMapping("/delete/{id}")
	public String deleteJob(@PathVariable Long id) {
		jobService.deleteJob(id);
		return "redirect:/admin/jobs"; // After deletion, redirect to job list
	}

	// Show form to edit an existing job
	@GetMapping("/edit/{id}")
	public String editJob(@PathVariable Long id, Model model) {
		Job job = jobService.getJobById(id);
		model.addAttribute("job", job);
		return "dashboard/edit_jobs"; // Form to edit a job
	}

	// Handle updating a job
	@PostMapping("/update/{id}")
	public String updateJob(@PathVariable Long id, @ModelAttribute("job") Job updatedJob) {
		Job existingJob = jobService.getJobById(id);
		existingJob.setTitle(updatedJob.getTitle());
		existingJob.setDescription(updatedJob.getDescription());
		existingJob.setCompanyName(updatedJob.getCompanyName());
		existingJob.setLocation(updatedJob.getLocation());
		jobService.saveJob(existingJob);
		return "redirect:/admin/jobs"; // After updating, redirect to job list
	}
}
