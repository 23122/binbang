package com.project.jongin.securiy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String msg="인증오류";
		if(exception instanceof UsernameNotFoundException) {
			msg="회원이 아니거나 탈퇴회원입니다.";
		}else if(exception instanceof BadCredentialsException){
			msg="비밀번호 오류";
		}
		
		setDefaultFailureUrl("/login?errorMsg");
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
