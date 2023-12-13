package com.human.thymeleaf.auth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {
	
	// 구글로부터 받은 userRequest 데이터에 대해 후처리하는 메소드
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration(): " + userRequest.getClientRegistration());
		System.out.println("getAccessToken().getTokenValue(): " + userRequest.getAccessToken().getTokenValue());
		System.out.println("getAttributes(): " + super.loadUser(userRequest).getAttributes());
//		System.out.println("============ 구글로부터 받은 사용자 정보 ============");
//		super.loadUser(userRequest).getAttributes().forEach((k,v) -> System.out.println(k + ": " + v));
//		System.out.println("========================");
		return super.loadUser(userRequest);
	}
	
}
