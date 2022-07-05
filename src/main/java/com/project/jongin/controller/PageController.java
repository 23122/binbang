package com.project.jongin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/customer/login")
	public String login() {
		return "/sign/signin";
	}

	@GetMapping("/customer/newMember")
	public String signup() {
		return "/sign/signup";
	}

	@GetMapping("loginPage")
	public String loginProess() {
		return "index";
	}

}
