package com.human.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.MemberDaoOracle;
import com.human.thymeleaf.db.ProfileDaoOracle;
import com.human.thymeleaf.entity.Member;
import com.human.thymeleaf.entity.Profile;

@Service
public class MemberServiceOracleImpl implements MemberService {
	@Autowired private MemberDaoOracle memberDao;
	@Autowired private ProfileDaoOracle profileDao;
	
	@Override
	public Member getMember(String mid) {
		Member member = memberDao.getMember(mid);
		return member;
	}

	@Override
	public List<Member> getMemberList() {
		List<Member> list = memberDao.getMemberList();
		return list;
	}

	@Override
	public void insertMember(Member member) {
		memberDao.insertMember(member);
	}

	@Override
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}

	@Override
	public void deleteMember(String mid) {
		memberDao.deleteMember(mid);
	}

	@Override
	public Profile getProfile(String mid) {
		Profile profile = profileDao.getProfile(mid);
		return profile;
	}

	@Override
	public void updateProfile(Profile profile) {
		profileDao.updateProfile(profile);
	}

	@Override
	public void insertProfile(Profile profile) {
		profileDao.insertProfile(profile);
	}

}
