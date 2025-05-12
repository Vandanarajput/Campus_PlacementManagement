package com.PlacementManagementSystem.Placement.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlacementManagementSystem.Placement.model.Job;


@Repository
public interface JobRepository extends JpaRepository<Job, Long> {


	    List<Job> findByTitleContainingIgnoreCaseOrCompanyNameContainingIgnoreCase(String title, String companyName);
	




	

}
