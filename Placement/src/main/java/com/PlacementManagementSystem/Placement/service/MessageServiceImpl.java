package com.PlacementManagementSystem.Placement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PlacementManagementSystem.Placement.model.Message;
import com.PlacementManagementSystem.Placement.repository.MessageRepository;


// 📘 This class implements the MessageService interface and provides the actual implementation.
@Service // Marks this as a Spring service class
public class MessageServiceImpl implements MessageService {

	// Inject the MessageRepository (interface to interact with the database)
	@Autowired
	private MessageRepository messageRepository;

	// 📤 Save a new message to the database
	@Override
	public void saveMessage(Message message) {
		messageRepository.save(message); // Saves the message entity to the database
	}

	// 📜 Fetch all messages from the database
	@Override
	public List<Message> getAllMessages() {
		return messageRepository.findAll(); // Retrieves all messages from the database
	}

	// 🗑️ Delete a message by ID
	@Override
	public void deleteMessage(Long id) {
		messageRepository.deleteById(id); // Deletes a message from the database by its ID
	}
}
