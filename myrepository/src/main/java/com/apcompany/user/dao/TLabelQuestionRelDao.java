package com.apcompany.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TLabelsQuestionRel;

public interface TLabelQuestionRelDao {
	@Select("select * from labels_question_rel where questionid=#{questionid}")
	public List<TLabelsQuestionRel> select(int questionid);

	@Select("select * from labels_question_rel where labelid like #{labelid}")
	public List<TLabelsQuestionRel> selectByNonRel(String labelid);

	@Select("select * from labels_question_rel where labels_rel_id = #{labels_rel_id}")
	public List<TLabelsQuestionRel> selectByRel(int labels_rel_id);

	@Insert("insert into labels_question_rel (labelid,labelsrelid,questionid) values (#{labelid},#{labelsrelid},#{questionid})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TLabelsQuestionRel tLabelsQuestionRel);

	@Update("update labels_question_rel set labelid=#{labelid},labelsrelid=#{labelsrelid} where questionid=#{questionid}")
	public int update(TLabelsQuestionRel tLabelsQuestionRel);

	@Update("update labels_question_rel set status=1 where id=#{id}")
	public int delete(int id);
}
