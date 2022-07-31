package com.project.jongin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.jongin.domain.dto.atention.AtentionInsertDTO;
import com.project.jongin.service.AtentionService;

@RequestMapping("/atention")
@Controller
public class AtentionController {
	
	@Autowired
	AtentionService atentionService;
	@GetMapping("/list/{memberNo}")
	public String list(@PathVariable long memberNo, @RequestParam(defaultValue = "1") int pageNo,Model model) {
		return atentionService.list(memberNo,pageNo,model);
	}
	
	
	@ResponseBody
	@PostMapping("/addAtention")
	public String addAtention(AtentionInsertDTO dto) {
		return atentionService.save(dto);
	}
	@ResponseBody
	@DeleteMapping("/removeAtention")
	public void delete(long boardNo) {
		atentionService.delete(boardNo);
	}
	
}
