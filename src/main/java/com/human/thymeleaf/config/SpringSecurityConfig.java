package com.human.thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.servlet.DispatcherType;

@Configuration
public class SpringSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable()
			.authorizeHttpRequests(request -> request
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/members/register", "/img/**", "/css/**", "/js/**",
						"/vendor/**", "/file/**", "/charts/**",
						"/chatbot/**", "/components/**", "/error/**", "/forms/**",
						"/icons/**", "/tables/**").permitAll()	// login 예외
				.anyRequest().authenticated()		// 어떠한 요청이라도 인증 필요
			)
			.formLogin(login -> login
				.loginPage("/members/login")		// 내가 만든 로그인 페이지
				.loginProcessingUrl("/members/login")	// 로그인 처리(post) url
				.usernameParameter("mid")
				.passwordParameter("pwd")
				.defaultSuccessUrl("/index", true) 		// 성공시 /index 로
				.permitAll() 			// 
			)
			.logout(withDefaults());
		return http.build();
	}
	
}
