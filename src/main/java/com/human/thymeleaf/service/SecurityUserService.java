package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.SecurityUser;

public interface SecurityUserService {
	
	SecurityUser findById(int suid);
	
	SecurityUser findByName(String suname);
	
	SecurityUser findByEmail(String email);
	
	List<SecurityUser> getSecurityUserList();
	
	void insertSecurityUser(SecurityUser su);
	
	void updateSecurityUser(SecurityUser su);
	
	void deleteSecurityUser(int suid);
	
}
