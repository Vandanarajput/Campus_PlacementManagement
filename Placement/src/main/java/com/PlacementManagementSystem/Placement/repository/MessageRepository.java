package com.PlacementManagementSystem.Placement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PlacementManagementSystem.Placement.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	// No need to write any code here — JPA gives basic CRUD methods
}
