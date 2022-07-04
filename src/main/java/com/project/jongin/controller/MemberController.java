package com.project.jongin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.jongin.domain.dto.member.memberInsertDTO;
import com.project.jongin.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@PostMapping("/customer/newMember")
	public String signup(memberInsertDTO dto,HttpServletRequest request) {
		return service.save(dto,request);
	}
}
