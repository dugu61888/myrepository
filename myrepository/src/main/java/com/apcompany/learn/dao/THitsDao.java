package com.apcompany.learn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.learn.pojo.THits;

public interface THitsDao {

	@Select("select * from hits where question_id=#{question_id} and step=#{step}")
	public THits getTHits(@Param(value = "question_id") int question_id,
			@Param(value = "step") int step);

	@Insert("insert into hits (question_id,step,img_string) values (#{question_id},#{step},#{img_string})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(THits tHits);

	@Update("update hits set question_id=#{question_id},step=#{step},img_string=#{img_string} where id=#{id}")
	public int update(THits tHits);

	@Delete("delete from hits where id=#{id}")
	public int delete(int id);

	@Select("select * from hits where question_id=#{question_id}")
	public List<THits> getTHitsByQuestionid(
			@Param(value = "question_id") int question_id);

}
