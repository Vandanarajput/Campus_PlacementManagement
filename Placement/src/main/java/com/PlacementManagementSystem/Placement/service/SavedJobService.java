package com.PlacementManagementSystem.Placement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.model.SaveJob;
import com.PlacementManagementSystem.Placement.repository.JobRepository;
import com.PlacementManagementSystem.Placement.repository.SavedJobRepository;

@Service
public class SavedJobService {

	@Autowired
	private SavedJobRepository savedJobRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserService userService; // Assuming you have a UserService to fetch User by email or ID

	// Method to save a job for a user
	public void saveJobForUser(Long userId, Long jobId) {
		// Check if the job exists
		Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));

		// Check if the job is already saved by the user
		Optional<SaveJob> existingSavedJob = savedJobRepository.findByUserIdAndJobId(userId, jobId);
		if (existingSavedJob.isEmpty()) {
			// Save the job if not already saved
			SaveJob savedJob = new SaveJob();
			savedJob.setUser(userService.getStudentById(userId));
			savedJob.setJob(job);
			savedJobRepository.save(savedJob);
		}
	}

	// Optional method to retrieve saved jobs for a user
	public List<SaveJob> getSavedJobs(Long userId) {
		return savedJobRepository.findByUserId(userId);
	}

}
