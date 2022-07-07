package com.project.jongin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/register")
@Controller
public class RegisterController {
	
	@GetMapping("/board")
	public String login() {
		return "/register/regBoard";
	}
}
