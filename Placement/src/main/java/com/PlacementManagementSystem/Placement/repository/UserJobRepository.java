package com.PlacementManagementSystem.Placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlacementManagementSystem.Placement.model.UserJob;

@Repository
public interface UserJobRepository extends JpaRepository<UserJob, Long> {
	// You can add custom queries here if needed

	public List<UserJob> findByUserId(Long userId);
	
}
