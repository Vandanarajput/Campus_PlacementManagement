package com.PlacementManagementSystem.Placement.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlacementManagementSystem.Placement.model.UserProfile;
import com.PlacementManagementSystem.Placement.repository.UserProfileRepo;



@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepo userProfileRepo;

    // Method to create or update a user profile
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepo.save(userProfile);
    }

    // Method to get a user profile by ID
    public UserProfile getUserProfileById(Long id) {
        return userProfileRepo.findById(id).orElse(null);
    }

    // Method to delete a user profile by ID
    public void deleteUserProfile(Long id) {
        userProfileRepo.deleteById(id);
    }
    
    // Method to get all user profiles
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepo.findAll();
    }

    //update user
    public UserProfile updateUserProfile(Long id, UserProfile userProfile) {
        UserProfile existingUserProfile = userProfileRepo.findById(id).orElse(null);
        if (existingUserProfile != null) {
            existingUserProfile.setName(userProfile.getName());
            existingUserProfile.setEmail(userProfile.getEmail());
            existingUserProfile.setPhone(userProfile.getPhone());
            existingUserProfile.setDob(userProfile.getDob());
            existingUserProfile.setAddress(userProfile.getAddress());
            existingUserProfile.setCollegeId(userProfile.getCollegeId());
            existingUserProfile.setCourse(userProfile.getCourse());
            existingUserProfile.setBranch(userProfile.getBranch());
            existingUserProfile.setYear(userProfile.getYear());
            existingUserProfile.setSkills(userProfile.getSkills());
            return userProfileRepo.save(existingUserProfile);
        }
        return null;
    }

  }
    




    