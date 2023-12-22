package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.Anniversary;

public interface AnniversaryService {

	List<Anniversary> getDayAnnivList(int suid, String sdate);
	
	List<Anniversary> getAnnivDays(int suid, String start, String end);
	
	void insert(Anniversary anniversary);
	
}
