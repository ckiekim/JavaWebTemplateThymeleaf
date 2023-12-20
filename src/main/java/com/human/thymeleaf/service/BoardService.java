package com.human.thymeleaf.service;

import java.util.List;

import com.human.thymeleaf.entity.Board;

public interface BoardService {

	Board getBoard(int bid);
	
	List<Board> getBoardList(String field, String query);
	
	void insertBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(int bid);
	
	void increaseViewCount(int bid);
	
	void increaseReplyCount(int bid);
	
	void increaseLikeCount(int bid);
	
}
