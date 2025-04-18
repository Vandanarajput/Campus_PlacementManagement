package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.PlacementManagementSystem.Placement.model.Message;
import com.PlacementManagementSystem.Placement.service.MessageService;

// 📘 This is the controller for handling messages related actions.
@Controller
public class MessageController {

	// Inject the MessageService to interact with business logic
	@Autowired
	private MessageService messageService;

	// 📜 Endpoint to display all messages for the admin
	@GetMapping("/admin/messages")
	public String viewMessages(Model model) {
		// Fetch all messages from the database through the service
		model.addAttribute("messages", messageService.getAllMessages());
		return "dashboard/message"; // Return the admin messages page
	}

	// 📝 Endpoint for admin to create a new message
	@PostMapping("/admin/messages")
	public String sendMessage(@RequestParam("subject") String subject, @RequestParam("content") String content) {
		// Create a new Message object
		Message message = new Message();
		message.setSubject(subject); // Set the message subject
		message.setContent(content); // Set the message content

		// Save the message via the service
		messageService.saveMessage(message);

		return "redirect:/admin/message"; // Redirect back to the admin messages page after saving
	}

	// 🗑️ Endpoint to delete a message
	@PostMapping("/admin/messages/delete")
	public String deleteMessage(@RequestParam("id") Long id) {
		// Delete the message by its ID through the service
		messageService.deleteMessage(id);
		return "redirect:/admin/message"; // Redirect back to the admin messages page after deletion
	}
}
