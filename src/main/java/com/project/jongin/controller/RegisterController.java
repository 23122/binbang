package com.project.jongin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class RegisterController {
	
	@GetMapping("/home")
	public String login() {
		return "/admin/default";
	}
}
