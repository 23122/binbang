package com.project.jongin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.jongin.securiy.CustomAuthenticationFailureHandler;
import com.project.jongin.securiy.CustomAuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecuriyConfig{
	
//	private final CustomAuthenticationFailureHandler failureHandler;
//	private final CustomAuthenticationSuccessHandler successHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	};
	
	@Bean
	CustomAuthenticationFailureHandler failurlHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	
	@Bean
	CustomAuthenticationSuccessHandler successHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	
	/*
	 * @Bean public UserDetailsService userDetailsService() { String
	 * test=passwordEncoder().encode("1111"); System.out.println(">>>>>>>>>>>>>>>");
	 * System.out.println(test); System.out.println(">>>>>>>>>>>>>>>"); String
	 * pass="$2a$10$rQarhZnfK.v9eMqBUu05m.l0EAaJ1XR1gEKHsYjCRDBlC1qbLSHoi";
	 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 * manager.createUser(User.withDefaultPasswordEncoder().username("user").
	 * password(pass).roles("USER").build()); return manager; }
	 */
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            		.antMatchers("/","/customer/**").permitAll()
            		.anyRequest().permitAll()
            		);
        http
            .formLogin(form -> form
            		.usernameParameter("memberEmail")
            		.passwordParameter("memberPass")
            		.loginPage("/customer/login")
            		.loginProcessingUrl("/customer/login")
            		.failureUrl("/customer/login?errorMsg")
            		.defaultSuccessUrl("/",true)
//            		.successHandler(successHandler())
//            		.failureHandler(failurlHandler())
            		.permitAll()
            		);
        http.csrf();
        return http.build();
    }

	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**","/iamges/**","/favicon.ico*");
    }
	
}
