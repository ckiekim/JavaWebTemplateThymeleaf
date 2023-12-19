package com.human.thymeleaf.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.thymeleaf.entity.Message;

@Mapper
public interface MessageDaoOracle {

	@Select("select m.*, u.nickname srcName from message m"
			+ " join securityUser u on m.srcSuid = u.suid"
			+ " where m.mid=#{mid}")
	Message getMessage(int mid);
	
	@Select("select su.nickname dstName, a.* from securityUser su right join"
			+ " (select m.*, u.nickname srcName from message m "
			+ "  join securityUser u on m.srcSuid=u.suid"
			+ "  order by m.mid desc) a on su.suid=a.dstSuid")
	List<Message> getMessageListAll();
	
	@Select("select rownum, a.* from ("
			+ " select m.*, u.nickname srcName, u.imgPath srcProfile from message m"
			+ " join securityUser u on m.srcSuid = u.suid"
			+ " where m.dstSuid=#{dstSuid} and m.status=#{status} order by m.mid desc) a"
			+ " where rownum <= 5")
	List<Message> getMessageList(int dstSuid, int status);
	
	@Select("select count(mid) from message where dstSuid=#{dstSuid} and status=#{status}")
	int getMessageSize(int dstSuid, int status);
	
	@Insert("insert into message values(default, #{srcSuid}, #{dstSuid}, #{content}, default, default)")
	void insertMessage(Message message);
	
	@Update("update message set status=#{status} where mid=#{mid}")
	void updateMessageStatus(Message message);
	
	@Delete("delete from message where mid=#{mid}")
	void deleteMessage(int mid);
	
}
