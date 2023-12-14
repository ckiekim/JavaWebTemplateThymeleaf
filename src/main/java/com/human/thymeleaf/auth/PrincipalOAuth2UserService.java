package com.human.thymeleaf.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.service.SecurityUserService;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired private SecurityUserService securityUserService;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 구글로부터 받은 userRequest 데이터에 대해 후처리하는 메소드
	// method 종료시 @AuthenticationPrincipal 이 만들어짐
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration(): " + userRequest.getClientRegistration());	// 어떤 OAuth로 로그인 했는지 (예, google)
		System.out.println("getAccessToken().getTokenValue(): " + userRequest.getAccessToken().getTokenValue());
		System.out.println("getAttributes(): " + super.loadUser(userRequest).getAttributes());
//		System.out.println("============ 구글로부터 받은 사용자 정보 ============");
//		super.loadUser(userRequest).getAttributes().forEach((k,v) -> System.out.println(k + ": " + v));
//		System.out.println("========================");
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("getAttributes(): " + oAuth2User.getAttributes());
		
		// 회원가입
		String provider = userRequest.getClientRegistration().getRegistrationId();	// google
		String providerId = oAuth2User.getAttribute("sub");
		String suname = provider + "_" + providerId;		// google_1096...
		// 가입이 안되어있으면 가입 진행
		SecurityUser securityUser = securityUserService.findByName(suname);
		if (securityUser == null) {
			String email = oAuth2User.getAttribute("email");
			String nickname = oAuth2User.getAttribute("name");
			String pwd = bCryptPasswordEncoder.encode("CK World");
			String role = "ROLE_USER";
			securityUser = new SecurityUser(email, pwd, suname, nickname, provider, providerId, role);
			securityUserService.insertSecurityUser(securityUser);
		}
		return new PrincipalDetails(securityUser, oAuth2User.getAttributes());
	}
	
}
