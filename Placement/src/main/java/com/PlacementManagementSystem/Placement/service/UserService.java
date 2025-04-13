package com.PlacementManagementSystem.Placement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlacementManagementSystem.Placement.model.User;
import com.PlacementManagementSystem.Placement.repository.UserRepositry;

@Service
public class UserService {

	@Autowired
	private UserRepositry userRepositry;

	public void saveUser(User user) {
		userRepositry.save(user);
	}

	public User loginUser(String email, String password) {
		return userRepositry.LoginUser(email, password);
	}

	public User getUserByEmail(String email) {
		return userRepositry.findByEmail(email);
	}
	
	
}
