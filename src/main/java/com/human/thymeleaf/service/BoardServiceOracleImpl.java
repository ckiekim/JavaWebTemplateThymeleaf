package com.human.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.thymeleaf.db.BoardDaoOracle;
import com.human.thymeleaf.entity.Board;

@Service
public class BoardServiceOracleImpl implements BoardService {
	@Autowired private BoardDaoOracle boardDao;

	@Override
	public Board getBoard(int bid) {
		Board board = boardDao.getBoard(bid);
		return board;
	}

	@Override
	public List<Board> getBoardList(String field, String query) {
		List<Board> list = boardDao.getBoardList(field, "%" + query + "%");
		return list;
	}

	@Override
	public void insertBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}

	@Override
	public void deleteBoard(int bid) {
		boardDao.deleteBoard(bid);
	}

	@Override
	public void increaseViewCount(int bid) {
		boardDao.increaseCount(bid, "viewCount");
	}

	@Override
	public void increaseReplyCount(int bid) {
		boardDao.increaseCount(bid, "replyCount");
	}

	@Override
	public void increaseLikeCount(int bid) {
		boardDao.increaseCount(bid, "likeCount");
	}

}
