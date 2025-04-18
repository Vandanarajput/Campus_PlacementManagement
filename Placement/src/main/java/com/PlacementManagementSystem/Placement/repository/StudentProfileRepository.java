package com.PlacementManagementSystem.Placement.repository;

import com.PlacementManagementSystem.Placement.model.StudentProfile;
import com.PlacementManagementSystem.Placement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
	StudentProfile findByUser(User user);
}
