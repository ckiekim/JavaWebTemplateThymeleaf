package com.human.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.ReplyDaoOracle;
import com.human.thymeleaf.entity.Reply;

@Service
public class ReplyServiceOracleImpl implements ReplyService {
	@Autowired private ReplyDaoOracle replyDao;

	@Override
	public List<Reply> getReplyList(int bid) {
		List<Reply> list = replyDao.getReplyList(bid);
		return list;
	}

	@Override
	public void insertReply(Reply reply) {
		replyDao.insertReply(reply);
	}

}
