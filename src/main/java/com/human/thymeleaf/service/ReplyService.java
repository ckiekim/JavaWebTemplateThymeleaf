package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.Reply;

public interface ReplyService {

	List<Reply> getReplyList(int bid);
	
	void insertReply(Reply reply);
	
}
