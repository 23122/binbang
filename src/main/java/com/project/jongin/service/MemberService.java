package com.project.jongin.service;

import javax.servlet.http.HttpServletRequest;

import com.project.jongin.domain.dto.member.memberInsertDTO;

public interface MemberService {

	String save(memberInsertDTO dto, HttpServletRequest request);

	boolean emailCheck(String memberEmail);

}
