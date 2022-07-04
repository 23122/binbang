package com.project.jongin.securiy;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.jongin.domain.entity.MemberEntity;
import com.project.jongin.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
		System.out.println(memberEmail);
		Optional<MemberEntity> result = memberRepository.findByMemberEmailAndMemberAbsence(memberEmail,false);
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않거나 탈퇴회원");
		}
		return new MyUserDetails(result.get());
	}

}
