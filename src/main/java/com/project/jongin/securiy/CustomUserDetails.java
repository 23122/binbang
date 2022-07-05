package com.project.jongin.securiy;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.project.jongin.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class CustomUserDetails extends User implements OAuth2User{
	
	private static final long serialVersionUID = 1L;
	private String memberEamil;
	private String membername;
	
	public CustomUserDetails(MemberEntity e) {
		super(e.getMemberEmail(), e.getMemberPass(),e.getRoleSet().stream().map(role->new SimpleGrantedAuthority(role.role))
				.collect(Collectors.toSet()));//, Collection<? extends GrantedAuthority> authorities
		
		memberEamil=e.getMemberEmail();
		membername=e.getMemberName();
				
	}

	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}
}
