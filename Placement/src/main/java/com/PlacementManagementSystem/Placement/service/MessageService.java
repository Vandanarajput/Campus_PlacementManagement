package com.PlacementManagementSystem.Placement.service;

import java.util.List;
import com.PlacementManagementSystem.Placement.model.Message;

// 📘 This interface defines what operations our service will perform.
// It will be implemented by MessageServiceImpl.
public interface MessageService {

	// Save a new message sent by the user
	void saveMessage(Message message);

	// Fetch all messages (for admin to read them)
	List<Message> getAllMessages();

	// Delete a specific message by ID
	void deleteMessage(Long id);
}
