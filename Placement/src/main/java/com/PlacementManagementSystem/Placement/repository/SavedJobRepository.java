package com.PlacementManagementSystem.Placement.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlacementManagementSystem.Placement.model.SaveJob;

@Repository
public interface SavedJobRepository extends JpaRepository<SaveJob, Long>{
	 // Find all saved jobs for a particular user
  List<SaveJob> findByUserId(Long userId);

    // Find if a job is already saved by the user
 Optional<SaveJob> findByUserIdAndJobId(Long userId, Long jobId);
}
