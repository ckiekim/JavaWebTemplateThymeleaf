package com.human.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.AnniversaryDaoOracle;
import com.human.thymeleaf.entity.Anniversary;

@Service
public class AnniversaryServiceOracleImpl implements AnniversaryService {
	
	@Autowired private AnniversaryDaoOracle annivDao;

	@Override
	public List<Anniversary> getDayAnnivList(int suid, String sdate) {
		List<Anniversary> list = annivDao.getAnnivList(suid, sdate, sdate);
		return list;
	}

	@Override
	public List<Anniversary> getAnnivDays(int suid, String start, String end) {
		List<Anniversary> list = annivDao.getAnnivList(suid, start, end);
		return list;
	}

	@Override
	public void insert(Anniversary anniversary) {
		annivDao.insert(anniversary);
	}
	
}
