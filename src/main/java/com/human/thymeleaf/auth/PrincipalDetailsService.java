package com.human.thymeleaf.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.service.SecurityUserService;

// Security 설정에서 loginProcessingUrl("/security-user/login") 설정되어 있으므로
// 로그인 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는
// loadUserByUsername() method가 실행됨
@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired private SecurityUserService securityUserService;
	
	// Security Session 내에 (Authentication 내에 UserDetails)이 들어감
	// method 종료시 @AuthenticationPrincipal 이 만들어짐
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("loadUserByUsername(), username: " + username);
		SecurityUser securityUser = securityUserService.findByName(username);
		if (securityUser != null)
			return new PrincipalDetails(securityUser);
		return null;
	}

}
