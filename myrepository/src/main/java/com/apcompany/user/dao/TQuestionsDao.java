package com.apcompany.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TQuestions;

public interface TQuestionsDao {

	@Select("select * from questions where id=#{id}")
	public TQuestions getQuestions(int id);
	
	@Select("select * from questions where keypointId=#{keypointId} and type=#{type}")
	public TQuestions getExample(@Param("keypointId")int keypointId,@Param("type")int type);
	
	
	@Select("select * from questions where keypointId=#{keypointId} and type=#{type}")
	public Map<String, Object> getExampleNew(@Param("keypointId")int keypointId,@Param("type")int type);

	@Insert("insert into questions (question) values (#{question})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TQuestions questions);

	@Update("update questions set question=#{question},keypointId=#{keypointId} where id=#{id}")
	public int update(TQuestions questions);

	@Delete("delete from questions  where id=#{id}")
	public int delete(int id);

	@Select("select count(1) from questions ")
	public Integer getQuestionsNum();

	public List<TQuestions> searchAll(@Param("questionid") int questionid,
			@Param(value = "pageStart") Integer pageStart, @Param(value = "pageSize") Integer pageSize,@Param("keypointId") int keypointId);

}
