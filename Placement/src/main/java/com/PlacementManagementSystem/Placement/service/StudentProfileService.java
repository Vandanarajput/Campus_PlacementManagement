package com.PlacementManagementSystem.Placement.service;

import com.PlacementManagementSystem.Placement.model.StudentProfile;
import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {

	@Autowired
	private StudentProfileRepository studentProfileRepository;

	public StudentProfile saveProfile(StudentProfile profile) {
		return studentProfileRepository.save(profile);
	}

	public StudentProfile getProfileByUser(User user) {
		return studentProfileRepository.findByUser(user);
	}

	public boolean profileExists(User user) {
		return studentProfileRepository.findByUser(user) != null;
	}
}
