package com.PlacementManagementSystem.Placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.PlacementManagementSystem.Placement.model.Message;
import com.PlacementManagementSystem.Placement.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/admin/message")
	public String viewMessages(Model model) {
		model.addAttribute("messages", messageService.getAllMessages());
		return "dashboard/message"; // Make sure this file exists: templates/dashboard/message.html
	}

	@PostMapping("/messages")
	public String sendMessage(@RequestParam("subject") String subject, @RequestParam("content") String content) {
		Message message = new Message();
		message.setSubject(subject);
		message.setContent(content);
		messageService.saveMessage(message);
		return "redirect:/messages"; // 
	}

	@PostMapping("/admin/messages/delete")
	public String deleteMessage(@RequestParam("id") Long id) {
		messageService.deleteMessage(id);
		return "redirect:/messages"; //  
	}
}
