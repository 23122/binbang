package com.project.jongin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/customer/board")
	public String list() {
		return "board/list";
	}
}
