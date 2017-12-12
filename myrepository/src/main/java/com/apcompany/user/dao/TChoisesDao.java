package com.apcompany.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TChoises;

public interface TChoisesDao {
	@Select("select * from choises where id=#{id}")
	public TChoises getChoise(int id);

	@Select("select * from choises where questionid=#{id}")
	public List<TChoises> getChoiseByQuestionId(int id);

	@Insert("insert into choises (questionid,choise) values (#{questionid},#{choise})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TChoises choises);

	@Update("update choises set choise=#{choise} where id=#{id}")
	public int update(TChoises choises);

	@Update("update choises set choise=#{choise} where questionid=#{questionid}")
	public int updateByQuestionId(TChoises choises);

	@Delete("update choises set status=1 where id=#{id}")
	public int delete(int id);

	@Delete("update choises set status=1 where questionid=#{id}")
	public int deleteByQuestionId(int id);
}
