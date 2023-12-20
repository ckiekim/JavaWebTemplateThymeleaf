package com.human.thymeleaf.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.thymeleaf.entity.Notification;

@Mapper
public interface NotificationDaoOracle {

	@Select("select * from notification where nid=#{nid}")
	Notification getNotification(int nid);
	
	@Select("select n.*, u.nickname dstName from notification n"
			+ "  join securityUser u on n.dstSuid=u.suid order by nid desc")
	List<Notification> getNotificationListAll();
	
	@Select("select * from notification where dstSuid=#{dstSuid} and status=#{status}")
	List<Notification> getNotificationList(int dstSuid, int status);
	
	@Insert("insert into notification values(default, #{dstSuid}, #{title}, #{content}, default, default)")
	void insertNotification(Notification notification);
	
	@Update("update notification set status=#{status} where nid=#{nid}")
	void updateNotification(Notification notification);
	
	@Delete("delete from notification where nid=#{nid}")
	void deleteNotification(int nid);
	
}
