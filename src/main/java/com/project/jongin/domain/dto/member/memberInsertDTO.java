package com.project.jongin.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.jongin.domain.entity.MemberEntity;

import lombok.Setter;

@Setter
public class memberInsertDTO {
	
	private String memberEmail;
	private String memberPass;
	private String memberName;
	private String memberIp;
	
	public memberInsertDTO passEncode(PasswordEncoder encoder) {
		this.memberPass=encoder.encode(memberPass);
		return this;
	}
	
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.memberEmail(memberEmail)
				.memberPass(memberPass)
				.memberName(memberName)
				.memberIp(memberIp)
				.build();
	}
}
