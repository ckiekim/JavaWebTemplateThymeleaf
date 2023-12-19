package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.Notification;

public interface NotificationService {
	public static final int NOTI_NEW = 0;
	public static final int NOTI_READ = 1;
	public static final int NOTI_DELETED = 2;

	Notification getNotification(int nid);
	
	List<Notification> getNotificationListAll();
	
	List<Notification> getNotificationList(int dstSuid, int status);
	
	void insertNotification(Notification notification);
	
	void updateNotification(Notification notification);
	
	void deleteNotification(int nid);
	
}
