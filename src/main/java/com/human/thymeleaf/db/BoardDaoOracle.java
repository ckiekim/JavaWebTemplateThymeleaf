package com.human.thymeleaf.db;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.thymeleaf.entity.Board;

@Mapper
public interface BoardDaoOracle {

	@Select("select b.*, u.nickname suName from board b"
			+ "  join securityUser u on b.suid=u.suid"
			+ "  where b.bid=#{bid}")
	Board getBoard(int bid);
	
	@Select("select b.*, u.nickname suName from board b"
			+ "  join securityUser u on b.suid=u.suid"
			+ "  where b.isDeleted=0 and ${field} like #{query}"
			+ "  order by b.bid desc")
	List<Board> getBoardList(String field, String query);
	
	@Insert("insert into board values (default, #{suid}, #{title}, #{content}, default,"
			+ "  default, default, default, #{files}, default)")
	void insertBoard(Board board);
	
	@Update("update board set title=#{title}, content=#{content}, modTime=SYSDATE,"
			+ "  files=${files} where bid=#{bid}")
	void updateBoard(Board board);
	
	@Update("update board set isDeleted=1 where bid=#{bid}")
	void deleteBoard(int bid);
	
	@Update("update board set ${field}=${field}+1 where bid=#{bid}")
	void increaseCount(int bid, String field);
	
}
