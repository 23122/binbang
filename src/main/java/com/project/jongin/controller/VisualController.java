package com.project.jongin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.jongin.domain.dto.visual.VisualInsertDTO;
import com.project.jongin.service.VisualService;

@Controller
public class VisualController {
	@Autowired
	VisualService visualService;
	
	@GetMapping("/admin/visuals/write")
	public String page() {
		return "/admin/visual/write";
	}
	@GetMapping("/admin/visuals")
	public String list(Model model) {
		return visualService.list(model);
	}
	@PostMapping("/admin/visuals")
	public String save(MultipartFile visualImg,VisualInsertDTO dto) {
		return visualService.save(visualImg,dto);
	}
}
