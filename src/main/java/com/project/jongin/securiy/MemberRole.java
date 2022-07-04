package com.project.jongin.securiy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberRole {
	ADMIN("ROLE_ADMIN","관리자"),//0
	USER("ROLE_USER","회원");//1
	final String role;
	final String title;
}
