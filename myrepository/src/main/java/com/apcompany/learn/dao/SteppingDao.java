package com.apcompany.learn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.learn.pojo.Stepping;

public interface SteppingDao {
	
	@Insert("insert into stepping (questionId, step,procedurequestiong,proceduranswer) values (#{questionId},#{step},#{procedurequestiong},#{proceduranswer})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Stepping  stepping);

	@Update("update stepping set questionId=#{questionId},step=#{step},procedurequestiong=#{procedurequestiong},"
			+ "proceduranswer=#{proceduranswer}  where id=#{id}")
	public int update(Stepping  stepping);
	

	@Delete(value = { "delete from stepping where id=#{id}" })
	public int delete(int id);
	
	@Select("select * from stepping where questionId=#{questionId}")
	public List<Stepping> getAllStep(int questionId);
	
	@Select("select * from stepping where questionId=#{questionId} and step=#{step}")
	public Stepping getStep(@Param(value = "questionId") int keypointId,@Param(value = "step")int step);

}
