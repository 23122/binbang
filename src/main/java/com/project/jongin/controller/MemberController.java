package com.project.jongin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.jongin.domain.dto.member.memberInsertDTO;
import com.project.jongin.service.MailService;
import com.project.jongin.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@Autowired
	MailService mailService;
	
	@PostMapping("/customer/newMember")
	public String signup(memberInsertDTO dto,HttpServletRequest request) {
		return service.save(dto,request);
	}
	
	@ResponseBody
	@PostMapping("/customer/emailCheck")
	public boolean emailCheck(String memberEmail) {
		return service.emailCheck(memberEmail);
	}
	
	@ResponseBody
	@PostMapping("/request-key/mail")
	public long requestMailKey(String memberEmail) {
		System.out.println(memberEmail);
		return mailService.mailSend(memberEmail);
	}
	
	@ResponseBody
	@GetMapping("/request-key/getKey")
	public String requestMailKey(HttpSession httpSession) {
		return (String) httpSession.getAttribute("mailKey");
	}
	
	@ResponseBody
	@GetMapping("/request-key/remove")
	public void requestRemove(HttpSession httpSession) {
		System.out.println("requestRemove 실행");
		httpSession.removeAttribute("mailKey");
	}
}
