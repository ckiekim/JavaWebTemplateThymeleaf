package com.human.thymeleaf.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.thymeleaf.entity.Profile;

@Mapper
public interface ProfileDaoOracle {

	@Select("select p.*, m.mname, m.email, m.imgPath from memberProfile p"
			+ " join members m on p.mid=m.mid"
			+ " where p.mid=#{mid}")
	Profile getProfile(String mid);
	
	@Update("update memberProfile set about=#{about}, company=#{company}, job=#{job},"
			+ " country=#{country}, addr=#{addr}, phone=#{phone}, twitter=#{twitter},"
			+ " facebook=#{facebook}, insta=#{insta}, linked=#{linked} where pid=#{pid}")
	void updateProfile(Profile profile);
	
	@Insert("insert into memberProfile values(default, #{mid}, '','','','','','','','','','')")
	void insertProfile(Profile profile);
	
}
