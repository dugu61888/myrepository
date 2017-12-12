package com.apcompany.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TLabels;

public interface TLabelsDao {

	@Select("select * from labels where id=#{id}")
	public TLabels select(int id);

	@Insert("insert into labels (labelname) values (#{labelname})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TLabels tLabels);

	@Update("update labels set labelname=#{labelname} where id=#{id}")
	public int update(TLabels tLabels);

	@Update("update labels set status=1 where id=#{id}")
	public int delete(int id);

	@Select("select * from labels where status = 0 and labelname like \"%\"#{names}\"%\" order by id limit #{pageStart},#{pageSize}")
	public List<TLabels> selectByName(
			@Param(value = "pageStart") Integer pageStart,
			@Param(value = "pageSize") Integer pageSize,
			@Param(value = "names") String names);

	@Select("select count(1) from labels where status = 0 and labelname like \"%\"#{names}\"%\"")
	public int countByName(@Param(value = "names") String names);

	@Select("select * from labels where status = 0 limit #{pageStart},#{pageSize} ")
	public List<TLabels> selectAll(
			@Param(value = "pageStart") Integer pageStart,
			@Param(value = "pageSize") Integer pageSize);

	@Select("select count(1) from labels ")
	public int countAll();

}
