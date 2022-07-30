package com.project.jongin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import com.project.jongin.securiy.CustomAuthenticationFailureHandler;
import com.project.jongin.securiy.CustomAuthenticationSuccessHandler;
import com.project.jongin.securiy.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecuriyConfig{
	
	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            		.antMatchers("/","/customer/**","/register/**","/request-key/*").permitAll()
            		.antMatchers("/board/**","/mypage/**","/report/**","/atention/**").hasAnyRole("USER","ADMIN")
            		.antMatchers("/admin/**").hasAnyRole("ADMIN")
            		.anyRequest().authenticated()
            		);
        http
            .formLogin(form -> form
            		.usernameParameter("memberEmail")
            		.passwordParameter("memberPass")
            		.loginPage("/customer/login")
            		.loginProcessingUrl("/customer/login")
            		.failureUrl("/customer/login?errorMsg")
            		.defaultSuccessUrl("/")
            		.permitAll()
            		);
        http
        	.oauth2Login(oauth2Login-> oauth2Login
        			.loginPage("/customer/login")
        			.defaultSuccessUrl("/")
        			.userInfoEndpoint()
        			.userService(customOAuth2UserService));
        http
        	.logout(logout -> logout
        			.logoutSuccessUrl("/"));
        http.csrf();
        return http.build();
    }

	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**","/img/**","/favicon.ico/*");
    }
	
}
