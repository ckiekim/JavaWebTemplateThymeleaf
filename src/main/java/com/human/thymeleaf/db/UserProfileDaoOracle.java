package com.human.thymeleaf.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.thymeleaf.entity.UserProfile;

@Mapper
public interface UserProfileDaoOracle {

	@Select("select p.*, s.nickname, s.email, s.imgPath from userProfile p"
			+ " join securityUser s on p.suid=s.suid"
			+ " where s.suname=#{suname}")
	UserProfile getUserProfile(String suname);
	
	@Update("update userProfile set about=#{about}, company=#{company}, job=#{job},"
			+ " country=#{country}, addr=#{addr}, phone=#{phone}, twitter=#{twitter},"
			+ " facebook=#{facebook}, insta=#{insta}, linked=#{linked} where pid=#{pid}")
	void updateUserProfile(UserProfile userProfile);
	
	@Insert("insert into userProfile values(default, #{suid}, '','','','','','','','','','')")
	void insertUserProfile(int suid);
	
}
