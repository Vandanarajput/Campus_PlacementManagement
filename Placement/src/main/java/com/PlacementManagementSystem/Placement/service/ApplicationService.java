package com.PlacementManagementSystem.Placement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
//import com.PlacementManagementSystem.Placement.model.Student;
import com.PlacementManagementSystem.Placement.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	// Save a new application
	public Application applyForJob(Application application) {
		return applicationRepository.save(application);
	}

	// Get all applications (Admin View)
	public List<Application> getAllApplications() {
		return applicationRepository.findAll();
	}

	// Get applications by student (Student View)
//	public List<Application> getApplicationsByStudent(Student student) {
//		return applicationRepository.findByStudent(student);
//	}

	// Get applications by job (Optional for Admin filtering)
	public List<Application> getApplicationsByJob(Job job) {
		return applicationRepository.findByJob(job);
	}

	// Optional: Update application status
	public void updateApplicationStatus(Long id, String status) {
		Application app = applicationRepository.findById(id).orElse(null);
		if (app != null) {
			app.setStatus(status);
			applicationRepository.save(app);
		}
	}

	// Optional: Delete an application
	public void deleteApplication(Long id) {
		applicationRepository.deleteById(id);
	}
}