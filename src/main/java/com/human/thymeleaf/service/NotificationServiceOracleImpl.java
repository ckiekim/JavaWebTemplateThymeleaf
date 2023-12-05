package com.human.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.NotificationDaoOracle;
import com.human.thymeleaf.entity.Notification;

@Service
public class NotificationServiceOracleImpl implements NotificationService {
	@Autowired private NotificationDaoOracle notiDao;
	
	@Override
	public Notification getNotification(int nid) {
		Notification notification = notiDao.getNotification(nid);
		return notification;
	}

	@Override
	public List<Notification> getNotificationListAll() {
		List<Notification> list = notiDao.getNotificationListAll();
		return list;
	}

	@Override
	public List<Notification> getNotificationList(String nto, int status) {
		List<Notification> list = notiDao.getNotificationList(nto, status);
		return list;
	}

	@Override
	public void insertNotification(Notification notification) {
		notiDao.insertNotification(notification);
	}

	@Override
	public void updateNotification(Notification notification) {
		notiDao.updateNotification(notification);
	}

	@Override
	public void deleteNotification(int nid) {
		notiDao.deleteNotification(nid);
	}

}
