package com.PlacementManagementSystem.Placement.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.repository.ApplicationRepository;
import com.PlacementManagementSystem.Placement.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;


    @Autowired
    private ApplicationRepository applicationRepository;
    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Save a job
    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    // Get a job by ID
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    // Delete a job
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public void submitApplication(Application application) {
       applicationRepository.save(application);
    }


	
}

