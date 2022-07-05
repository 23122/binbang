package com.project.jongin.securiy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private RequestCache requestCache=new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		clearAuthenticationAttributes(request);
		
		SavedRequest saveRequest=requestCache.getRequest(request, response);
		
		saveRequest.getHeaderNames().forEach(name->{
			System.out.print(":");
			System.out.println(saveRequest.getHeaderValues(name));
		});
		
		String redirectUrl=saveRequest.getRedirectUrl();
		
		if(saveRequest !=null && !redirectUrl.contains("error")) {
			redirectStrategy.sendRedirect(request, response, redirectUrl);
		}else {
			redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
		}
		
	}
	
}
