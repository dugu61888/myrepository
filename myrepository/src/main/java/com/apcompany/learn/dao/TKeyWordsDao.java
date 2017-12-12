package com.apcompany.learn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.learn.pojo.TKeyWords;

public interface TKeyWordsDao {
	@Select("select * from key_words where id=#{id}")
	public TKeyWords getKeyWords(int id);

	@Insert("insert into key_words (name,fname) values (#{name},#{fname})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TKeyWords tKeyWords);

	@Update("update key_words set name=#{name},fname=#{fname} where id=#{id}")
	public int update(TKeyWords tKeyWords);

	@Delete("delete from key_words where id=#{id}")
	public int delete(int id);

	@Select("select * from key_words")
	public List<TKeyWords> searchAll();

	@Select("select name from key_words where fname=#{fname} limit 1")
	public String getKeyWordsByFName(String fname);

}
