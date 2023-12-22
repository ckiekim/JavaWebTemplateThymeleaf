package com.human.thymeleaf.db;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.human.thymeleaf.entity.Anniversary;

@Mapper
public interface AnniversaryDaoOracle {

	@Select("SELECT * FROM anniversary"
			+ "  where adate >= #{start} AND adate <= #{end} AND (suid=14 OR suid=#{suid})"
			+ "  ORDER BY adate")
	List<Anniversary> getAnnivList(int suid, String start, String end);

	@Insert("insert into anniversary values"
			+ "  (default, #{suid}, #{aname}, #{adate}, #{isHoliday})")
	void insert(Anniversary anniversary);
	
}
