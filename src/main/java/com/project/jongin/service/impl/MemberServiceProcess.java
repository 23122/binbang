package com.project.jongin.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.jongin.domain.dto.member.memberInsertDTO;
import com.project.jongin.domain.repository.MemberRepository;
import com.project.jongin.securiy.MemberRole;
import com.project.jongin.service.MemberService;

@Service
public class MemberServiceProcess implements MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public String save(memberInsertDTO dto, HttpServletRequest request) {
		dto.passEncode(encoder);
		dto.setMemberIp(request.getRemoteAddr());
		memberRepository.save(dto.toEntity().addMemberRole(MemberRole.USER));
		return "redirect:/customer/login";
	}

}
