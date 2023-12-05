package com.human.thymeleaf.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.thymeleaf.entity.Member;

@Mapper
public interface MemberDaoOracle {

	@Select("select * from members where mid=#{mid}")
	Member getMember(String mid);
	
	@Select("select * from members")
	List<Member> getMemberList();
	
	@Insert("insert into members values(#{mid}, #{mname}, #{email}, default, #{imgPath})")
	void insertMember(Member member);
	
	@Update("update members set mname=#{mname}, email=#{email}, imgPath=#{imgPath} where mid=#{mid}")
	void updateMember(Member member);
	
	@Delete("delete from members where mid=#{mid}")
	void deleteMember(String mid);
	
}
