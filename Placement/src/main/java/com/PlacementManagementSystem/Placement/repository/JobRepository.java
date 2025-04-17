package com.PlacementManagementSystem.Placement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlacementManagementSystem.Placement.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	// You can add custom queries here if needed
}
