package com.PlacementManagementSystem.Placement.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
import com.PlacementManagementSystem.Placement.model.UserJob;
import com.PlacementManagementSystem.Placement.repository.ApplicationRepository;
import com.PlacementManagementSystem.Placement.repository.UserJobRepository;

@Service
public class UserJobService {

    @Autowired
    private UserJobRepository userJobRepository;
    
    @Autowired
    private JobService JobService;


    @Autowired
    private ApplicationRepository applicationRepository;
    
    // getAllAppliedJobs
    public List<UserJob> getAllAppliedJobs() {
        return userJobRepository.findAll();
    }
    // Get all jobs by user
    public List<UserJob> getAllUserJobsByUserId(Long userId) {
    	return userJobRepository.findByUserId(userId);
    }

    // Save a job
    public void saveJob(UserJob userJob) {
    	userJobRepository.save(userJob);
    }

    public UserJob getUserJobById(Long id) {
        return userJobRepository.findById(id).orElse(null);
    }

    public void deleteUserJob(Long id) {
    	userJobRepository.deleteById(id);
    }


	
}

