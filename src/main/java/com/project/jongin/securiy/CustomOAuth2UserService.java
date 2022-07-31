package com.project.jongin.securiy;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.jongin.domain.entity.MemberEntity;
import com.project.jongin.domain.repository.MemberRepository;
import com.project.jongin.utils.ClientIP;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final HttpServletRequest httpServletRequest;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User=super.loadUser(userRequest);
		//소셜로그인 인증완료상
		String registrationId=userRequest.getClientRegistration().getRegistrationId();
		System.out.println(registrationId);
		//DB저장
		return saveSocialUser(oAuth2User,registrationId);
	}

	private OAuth2User saveSocialUser(OAuth2User oAuth2User, String registrationId) {
		String name=null;
		String email=null;
		String pass=null;
		if(registrationId.equals("google")) {//구글로그인
			name=oAuth2User.getAttribute("name");
			email=oAuth2User.getAttribute("email");
			pass=oAuth2User.getAttribute("sub");
			
		}else if(registrationId.equals("naver")) {//네이버로그인
			Map<String, Object> response = oAuth2User.getAttribute("response");
			name=(String) response.get("name");
			email=(String) response.get("email");
			pass=(String) response.get("id");
		}else if(registrationId.equals("kakao")) {//카카오로그인
			Map<String, Object> response = oAuth2User.getAttribute("properties")	;
			Map<String, Object> response2 = oAuth2User.getAttribute("kakao_account");
			name=(String) response.get("nickname");
			email=(String) "(kakao)"+response2.get("email");
			pass=oAuth2User.getAttribute("connected_at");
		}
		//중복회원체크
		Optional<CustomUserDetails> result=memberRepository.findByMemberEmail(email).map(CustomUserDetails::new);
		if(result.isPresent()) {
			//이미 가입된 회원
			return result.get();
		}
		//가입안된 회원 소셜정보로 회원가입
		MemberEntity entity=memberRepository.save(MemberEntity.builder()
				.memberEmail(email)
				.memberName(name)
				.memberPass(passwordEncoder.encode(pass))
				.memberSocial(true)
				.memberIp(ClientIP.getClientIP(httpServletRequest))
				.build().addMemberRole(MemberRole.USER));
		
		return new CustomUserDetails(entity);
	}

	
}
