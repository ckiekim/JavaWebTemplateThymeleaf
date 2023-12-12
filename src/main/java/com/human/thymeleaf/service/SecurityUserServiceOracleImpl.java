package com.human.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.SecurityUserDaoOracle;
import com.human.thymeleaf.entity.SecurityUser;

@Service
public class SecurityUserServiceOracleImpl implements SecurityUserService {
	@Autowired private SecurityUserDaoOracle securityUserDao;

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

}
