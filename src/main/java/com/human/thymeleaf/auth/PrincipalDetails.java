package com.human.thymeleaf.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.human.thymeleaf.entity.SecurityUser;

// Spring Security가 로그인 POST 요청을 낚아채서 로그인을 진행시킴.
// 로그인 진행이 완료가 되면 시큐리티 세션을 만들어줌.(Security ContextHolder)
// Object type => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 함
// User Object type => UserDetails 타입 객체

// Security Session => Authentication => UserDetails

// OAuth Login도 지원하려면 OAuth2Login도 implement 하게 만들어 줌

public class PrincipalDetails implements UserDetails, OAuth2User {
	private SecurityUser securityUser;
	private Map<String, Object> attributes;
	
	public PrincipalDetails() { }
	// 일반 로그인
	public PrincipalDetails(SecurityUser securityUser) {
		this.securityUser = securityUser;
	}
	// OAuth 로그인
	public PrincipalDetails(SecurityUser securityUser, Map<String, Object> attributes) {
		this.securityUser = securityUser;
		this.attributes = attributes;
	}

	// 해당 사용자의 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return securityUser.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return securityUser.getPwd();
	}

	@Override
	public String getUsername() {
		return securityUser.getSuname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public String getName() {
		return null;
	}
	
	public SecurityUser getSecurityUser() {
		return securityUser;
	}

}
