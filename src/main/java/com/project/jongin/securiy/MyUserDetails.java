package com.project.jongin.securiy;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.project.jongin.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MyUserDetails extends User{
	
	private static final long serialVersionUID = 1L;
	private String memberEamil;
	private String membername;
	
	public MyUserDetails(MemberEntity e) {
		super(e.getMemberEmail(), e.getMemberPass(),
				e.getRoleSet().stream().map(role->new SimpleGrantedAuthority(role.role)).collect(Collectors.toSet()));//, Collection<? extends GrantedAuthority> authorities
		
		memberEamil=e.getMemberEmail();
		membername=e.getMemberName();
				
	}

}
