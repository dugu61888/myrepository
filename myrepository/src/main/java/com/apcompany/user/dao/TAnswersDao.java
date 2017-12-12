package com.apcompany.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TAnswers;

public interface TAnswersDao {

	@Select("select * from answers where id=#{id}")
	public TAnswers getAnswer(int id);
	
	@Select("select * from answers where questionid=#{questionid}")
	public TAnswers getAnswerByQuestionId(int questionid);

	@Insert("insert into answers (questionid, answer) values (#{questionid},#{answer})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TAnswers answers);

	@Update("update answers set answer=#{answer} where id=#{id}")
	public int update(TAnswers answers);

	@Update("update answers set answer=#{answer} where questionid=#{questionid}")
	public int updateByQuestionId(TAnswers answers);

	@Update("update answers set status=1 where id=#{id}")
	public int delete(int id);

	@Update("update answers set status=1 where questionid=#{id}")
	public int deleteByQuestionId(int id);
}
