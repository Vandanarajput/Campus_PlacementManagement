package com.PlacementManagementSystem.Placement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/landing")
	public String landingPage() {
		return "Home/index";
	}

}

