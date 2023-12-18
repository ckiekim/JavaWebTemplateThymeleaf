package com.human.thymeleaf.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.thymeleaf.entity.SecurityUser;

@Mapper
public interface SecurityUserDaoOracle {

	@Select("select * from securityUser where suid=#{suid}")
	SecurityUser findById(int suid);
	
	@Select("select * from securityUser where suname=#{suname}")
	SecurityUser findByName(String suname);
	
	@Select("select * from securityUser where email=#{email}")
	SecurityUser findByEmail(String email);
	
	@Select("select * from securityUser order by suid")
	List<SecurityUser> getSecurityUserList();
	
	@Insert("insert into securityUser values(default, #{suname}, #{pwd}, #{email}, "
				+ " #{nickname}, #{provider}, default, default, #{imgPath})")
	void insertSecurityUser(SecurityUser su);
	
	@Update("update securityUser set pwd=#{pwd}, email=#{email}, nickname=#{nickname}, "
				+ " role=#{role}, imgPath=#{imgPath} where suid=#{suid}")
	void updateSecurityUser(SecurityUser su);
	
	@Delete("delete from securityUser where suid=#{suid}")
	void deleteSecurityUser(int suid);
	
}
