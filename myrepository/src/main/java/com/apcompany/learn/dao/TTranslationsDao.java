package com.apcompany.learn.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.learn.pojo.TTranslations;

public interface TTranslationsDao {

	@Select("select * from translations where id=#{id}")
	public TTranslations getTranslations(int id);
	
	@Select("select * from translations where question_id=#{questionId}")
	public TTranslations getTranslationsByQuestionId(int questionId);

	@Insert("insert into translations (translate_lines,question_id) values (#{translateLines},#{questionId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TTranslations tTranslations);

	@Update("update translations set translate_lines=#{translateLines},question_id=#{questionId} where id=#{id}")
	public int update(TTranslations tTranslations);
	
	@Update("update translations set translate_lines=#{translateLines} where question_id=#{questionId}")
	public int updateByQuestionId(TTranslations tTranslations);

	@Delete("delete from translations where id=#{id}")
	public int delete(int id);
}
