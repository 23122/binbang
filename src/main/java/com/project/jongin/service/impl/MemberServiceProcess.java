package com.project.jongin.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.jongin.domain.dto.member.memberInsertDTO;
import com.project.jongin.domain.repository.MemberRepository;
import com.project.jongin.securiy.MemberRole;
import com.project.jongin.service.MemberService;
import com.project.jongin.utils.ClientIP;

@Service
public class MemberServiceProcess implements MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public String save(memberInsertDTO dto, HttpServletRequest request) {
		//패스워드 인코딩
		dto.passEncode(encoder);
		//ip
		dto.setMemberIp(ClientIP.getClientIP(request));
		memberRepository.save(dto.toEntity().addMemberRole(MemberRole.USER));
		return "redirect:/customer/login";
	}

}
