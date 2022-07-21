package com.project.jongin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.jongin.domain.dto.sales.SalesInsertDTO;
import com.project.jongin.service.SalesService;

@Controller
public class SalesController {
	
	@Autowired
	SalesService salesService;
	
	@GetMapping("/customer/board/aptList")
	public String list(Model model) {
		return salesService.list(model);
	}
	@PostMapping("/board/aptList/write")
	public String write(SalesInsertDTO dto) {
		return salesService.save(dto);
	}
}
