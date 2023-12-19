package com.human.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.MessageDaoOracle;
import com.human.thymeleaf.entity.Message;

@Service
public class MessageServiceOracleImpl implements MessageService {
	@Autowired private MessageDaoOracle messageDao;
	
	@Override
	public Message getMessage(int mid) {
		Message message = messageDao.getMessage(mid);
		return message;
	}

	@Override
	public List<Message> getMessageListAll() {
		List<Message> list = messageDao.getMessageListAll();
		return list;
	}

	@Override
	public List<Message> getMessageList(int dstSuid, int status) {
		List<Message> list = messageDao.getMessageList(dstSuid, status);
		return list;
	}

	@Override
	public int getMessageSize(int dstSuid, int status) {
		int size = messageDao.getMessageSize(dstSuid, status);
		return size;
	}

	@Override
	public void insertMessage(Message message) {
		messageDao.insertMessage(message);
	}

	@Override
	public void updateMessageStatus(Message message) {
		messageDao.updateMessageStatus(message);
	}

	@Override
	public void deleteMessage(int mid) {
		messageDao.deleteMessage(mid);
	}

}
