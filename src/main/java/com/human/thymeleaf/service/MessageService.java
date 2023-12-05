package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.Message;

public interface MessageService {
	public static final int MSG_NEW = 0;
	public static final int MSG_READ = 1;
	public static final int MSG_DELETED = 2;

	Message getMessage(int mid);
	
	List<Message> getMessageListAll();
	
	List<Message> getMessageList(String mto, int status);
	
	int getMessageSize(String mto, int status);
	
	void insertMessage(Message message);
	
	void updateMessage(Message message);
	
	void deleteMessage(int mid);
	
}
