package com.human.thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/security-user/register", "/security-user/success",
						"/img/**", "/css/**", "/js/**", "/vendor/**", "/file/**", "/charts/**",
						"/chatbot/**", "/components/**", "/error/**", "/forms/**",
						"/icons/**", "/tables/**").permitAll()	// login 예외
				.requestMatchers("/security-user/admin/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()		// 그외 어떠한 요청이라도 인증 필요
			)
			.formLogin(login -> login
				.loginPage("/security-user/login")		// 내가 만든 로그인 페이지
				.loginProcessingUrl("/security-user/login")	// 로그인 처리(post) url
				.usernameParameter("suname")
				.passwordParameter("pwd")
				.defaultSuccessUrl("/security-user/success", true) 	// 성공시 이동할 페이지
				.permitAll() 			// 
			)
			.logout(logout -> logout
				.logoutUrl("/security-user/logout").permitAll()
			);
		return http.build();
	}
	
}
