package com.human.thymeleaf.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.thymeleaf.entity.SchDay;
import com.human.thymeleaf.entity.Schedule;

@Mapper
public interface ScheduleDaoOracle {

	@Select("select * from schedule where sid=#{sid}")
	Schedule getSchedule(int sid);
	
	@Select("select * from schedule"
			+ "  where suid=#{suid} and sdate >= #{startDate} and sdate <= #{endDate}"
			+ "  order by startTime")
	List<Schedule> getSchedList(int suid, String startDate, String endDate);
	
	@Insert("insert into schedule values"
			+ "  (default, #{suid}, #{sdate}, #{title}, #{place},"
			+ "  #{startTime}, #{endTime}, #{isImportant}, #{memo})")
	void insert(Schedule schedule);
	
	@Update("update schedule set suid=#{suid}, sdate=#{sdate}, title=#{title}, place=#{place},"
			+ "  startTime=#{startTime}, endTime=#{endTime}, isImportant=#{isImportant}, memo=#{memo}"
			+ "  where sid=#{sid}")
	void update(Schedule schedule);
	
	@Delete("delete from schedule where sid=#{sid}")
	void delete(int sid);
	
}
