package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.SecurityUser;
import com.human.thymeleaf.entity.UserProfile;

public interface SecurityUserService {
	
	SecurityUser findById(int suid);
	
	SecurityUser findByName(String suname);
	
	SecurityUser findByEmail(String email);
	
	List<SecurityUser> getSecurityUserList();
	
	void insertSecurityUser(SecurityUser securityUser);
	
	void updateSecurityUser(SecurityUser securityUser);
	
	void deleteSecurityUser(int suid);
	
	UserProfile getUserProfile(String suname);
	
	void insertUserProfile(int suid);
	
	void updateUserProfile(UserProfile userProfile);
	
}
