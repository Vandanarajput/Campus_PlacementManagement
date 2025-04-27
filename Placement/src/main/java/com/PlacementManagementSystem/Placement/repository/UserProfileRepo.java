package com.PlacementManagementSystem.Placement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlacementManagementSystem.Placement.model.UserProfile;

public interface UserProfileRepo  extends JpaRepository<UserProfile, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByEmail, findByName, etc.

}
