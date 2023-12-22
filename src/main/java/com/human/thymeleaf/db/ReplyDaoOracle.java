package com.human.thymeleaf.db;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.human.thymeleaf.entity.Reply;

@Mapper
public interface ReplyDaoOracle {

	@Select("select r.*, u.nickname suName from reply r "
			+ "  join securityUser u on r.suid=u.suid "
			+ "  where r.bid=#{bid} order by r.rid")
	List<Reply> getReplyList(int bid);
	
	@Insert("insert into reply values (default, #{suid}, #{bid}, #{content}, default, #{isMine})")
	void insertReply(Reply reply);
	
}
