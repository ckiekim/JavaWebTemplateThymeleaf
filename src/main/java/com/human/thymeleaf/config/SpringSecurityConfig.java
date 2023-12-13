package com.human.thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.human.thymeleaf.auth.PrincipalOAuth2UserService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity		// 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
@EnableMethodSecurity(securedEnabled=true)	// @Secured 활성화
public class SpringSecurityConfig {
	
	@Autowired private PrincipalOAuth2UserService principalOAuth2UserService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/security-user/register", "/security-user/success", "/oauth2/**",
						"/img/**", "/css/**", "/js/**", "/vendor/**", "/file/**", "/charts/**",
						"/chatbot/**", "/components/**", "/error/**", "/forms/**",
						"/icons/**", "/tables/**").permitAll()	// login 예외
				.requestMatchers("/security-user/admin/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()		// 그외 어떠한 요청이라도 인증 필요
			)
			.formLogin(login -> login
				.loginPage("/security-user/login")		// 내가 만든 로그인 페이지
				.loginProcessingUrl("/security-user/login")		// 로그인 처리(post) url, spring이 낚아챔
				.usernameParameter("suname")
				.passwordParameter("pwd")
				.defaultSuccessUrl("/security-user/success", true) 	// 성공시 이동할 페이지
				.permitAll() 			// 
			)
			.oauth2Login(oauth2 -> oauth2
				.loginPage("/security-user/login")
				// 구글 로그인이 완료된 뒤의 후처리가 필요
				// 1. 코드받기(인증), 2. 액세스 토큰(권한), 3. 사용자 프로필 정보를 가져옴
				// 4-1. 그 정보를 토대로 회원가입을 자동으로 진행시키기도 함
				// 4-2. 구글이 제공하는 정보 + 추가적인 정보를 수집해야 함(예, 주소, 등급 등)
				.userInfoEndpoint(user -> user.userService(principalOAuth2UserService))
			)
			.logout(logout -> logout
				.logoutUrl("/security-user/logout").permitAll()
			);
		return http.build();
	}
	
}
