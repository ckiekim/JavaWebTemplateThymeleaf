package com.human.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.SecurityUserDaoOracle;
import com.human.thymeleaf.db.UserProfileDaoOracle;
import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.entity.UserProfile;

@Service
public class SecurityUserServiceOracleImpl implements SecurityUserService {
	@Autowired private SecurityUserDaoOracle securityUserDao;
	@Autowired private UserProfileDaoOracle userProfileDao;

	@Override
	public SecurityUser findById(int suid) {
		SecurityUser su = securityUserDao.findById(suid);
		return su;
	}

	@Override
	public SecurityUser findByName(String suname) {
		SecurityUser su = securityUserDao.findByName(suname);
		return su;
	}

	@Override
	public SecurityUser findByEmail(String email) {
		SecurityUser su = securityUserDao.findByEmail(email);
		return su;
	}

	@Override
	public List<SecurityUser> getSecurityUserList() {
		List<SecurityUser> list = securityUserDao.getSecurityUserList();
		return list;
	}

	@Override
	public void insertSecurityUser(SecurityUser su) {
		securityUserDao.insertSecurityUser(su);
	}

	@Override
	public void updateSecurityUser(SecurityUser su) {
		securityUserDao.updateSecurityUser(su);
	}

	@Override
	public void deleteSecurityUser(int suid) {
		securityUserDao.deleteSecurityUser(suid);
	}

	@Override
	public UserProfile getUserProfile(String suname) {
		UserProfile userProfile = userProfileDao.getUserProfile(suname);
		return userProfile;
	}

	@Override
	public void insertUserProfile(int suid) {
		userProfileDao.insertUserProfile(suid);
	}

	@Override
	public void updateUserProfile(UserProfile userProfile) {
		userProfileDao.updateUserProfile(userProfile);
	}

}
