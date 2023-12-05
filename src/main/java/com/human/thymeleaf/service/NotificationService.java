package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.Notification;

public interface NotificationService {

	Notification getNotification(int nid);
	
	List<Notification> getNotificationListAll();
	
	List<Notification> getNotificationList(String nto, int status);
	
	void insertNotification(Notification notification);
	
	void updateNotification(Notification notification);
	
	void deleteNotification(int nid);
	
}
