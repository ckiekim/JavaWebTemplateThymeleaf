package com.human.thymeleaf.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.human.thymeleaf.entity.SecurityUser;

// Spring Security가 로그인 POST 요청을 낚아채서 로그인을 진행시킴.
// 로그인 진행이 완료가 되면 시큐리티 세션을 만들어줌.(Security ContextHolder)
// Object type => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 함
// User Object type => UserDetails 타입 객체

// Security Session => Authentication => UserDetails

public class PrincipalDetails implements UserDetails {
	private SecurityUser securityUser;
	
	public PrincipalDetails() { }
	public PrincipalDetails(SecurityUser securityUser) {
		this.securityUser = securityUser;
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

}
