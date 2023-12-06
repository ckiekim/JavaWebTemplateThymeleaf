package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.Member;
import com.human.thymeleaf.entity.Profile;

public interface MemberService {
	
	Member getMember(String mid);
	
	List<Member> getMemberList();
	
	void insertMember(Member member);
	
	void updateMember(Member member);
	
	void deleteMember(String mid);
	
	Profile getProfile(String mid);
	
	void updateProfile(Profile profile);
	
	void insertProfile(Profile profile);
	
}
