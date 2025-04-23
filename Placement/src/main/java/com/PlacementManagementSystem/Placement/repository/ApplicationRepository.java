package com.PlacementManagementSystem.Placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlacementManagementSystem.Placement.model.Application;
import com.PlacementManagementSystem.Placement.model.Job;
//import com.PlacementManagementSystem.Placement.model.Student;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

//	List<Application> findByStudent(Student student);

	List<Application> findByJob(Job job);
}
