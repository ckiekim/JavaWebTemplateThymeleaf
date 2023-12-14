package com.human.thymeleaf.auth;

import java.util.Map;

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
	
	// Provider(구글, github 등)로부터 받은 userRequest 데이터에 대해 후처리하는 메소드
	// method 종료시 @AuthenticationPrincipal 이 만들어짐
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		String suname, email, nickname;
		String pwd = bCryptPasswordEncoder.encode("CK World");
		String role = "ROLE_USER";
		SecurityUser securityUser = null;
		
		System.out.println("getClientRegistration(): " + userRequest.getClientRegistration());	// 어떤 OAuth로 로그인 했는지 (예, google)
//		System.out.println("getAccessToken().getTokenValue(): " + userRequest.getAccessToken().getTokenValue());
//		System.out.println("getAttributes(): " + super.loadUser(userRequest).getAttributes());
//		System.out.println("============ 구글로부터 받은 사용자 정보 ============");
//		super.loadUser(userRequest).getAttributes().forEach((k,v) -> System.out.println(k + ": " + v));
//		System.out.println("========================");
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("getAttributes(): " + oAuth2User.getAttributes());
		
		// 회원가입
		String provider = userRequest.getClientRegistration().getRegistrationId();	// google
		switch(provider) {
		case "google":
			String providerId = oAuth2User.getAttribute("sub");
			suname = provider + "_" + providerId;		// google_1096...
			securityUser = securityUserService.findByName(suname);
			if (securityUser == null) {		// 가입이 안되어있으면 가입 진행
				email = oAuth2User.getAttribute("email");
				nickname = oAuth2User.getAttribute("name");
				securityUser = new SecurityUser(email, pwd, suname, nickname, provider, providerId, role);
				securityUserService.insertSecurityUser(securityUser);
			}
			break;

		case "github":
			int id = oAuth2User.getAttribute("id");
			suname = provider + "_" + id;
			securityUser = securityUserService.findByName(suname);
			if (securityUser == null) {		// 가입이 안되어있으면 가입 진행
				email = oAuth2User.getAttribute("email");
				nickname = oAuth2User.getAttribute("name");
				securityUser = new SecurityUser(email, pwd, suname, nickname, provider, String.valueOf(id), role);
				securityUserService.insertSecurityUser(securityUser);
			}
			break;
			
		case "naver":
			Map<String, Object> response = (Map) oAuth2User.getAttribute("response");
			String nid = (String) response.get("id");
			suname = provider + "_" + nid;
			securityUser = securityUserService.findByName(suname);
			if (securityUser == null) {		// 가입이 안되어있으면 가입 진행
				email = (String) response.get("email");
				nickname = (String) response.get("nickname");
				securityUser = new SecurityUser(email, pwd, suname, nickname, provider, nid, role);
				securityUserService.insertSecurityUser(securityUser);
			}
			break;
		}
		return new PrincipalDetails(securityUser, oAuth2User.getAttributes());
	}
	
}
