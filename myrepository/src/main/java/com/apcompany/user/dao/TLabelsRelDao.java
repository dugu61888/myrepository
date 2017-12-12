package com.apcompany.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.user.pojo.TLabels;
import com.apcompany.user.pojo.TLabelsRel;

public interface TLabelsRelDao {

	final String TableName = "labels_rel";

	@Select("select * from " + TableName + " where id=#{id}")
	public TLabelsRel select(int id);

	@Select("select * from " + TableName + " where parentsid=#{parentsid}")
	public List<TLabelsRel> selectByParent(int parentsid);

	@Insert("insert into "
			+ TableName
			+ " (labelname,parentsid,depth) values (#{labelname},#{parentsid},#{depth})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(TLabelsRel tLabelsRel);

	@Update("update " + TableName
			+ " set labelname=#{labelname} where id=#{id}")
	public int update(TLabelsRel tLabelsRel);

	@Update("update " + TableName + " set status=1 where id=#{id}")
	public int delete(int id);

	@Select("select * from "
			+ TableName
			+ " where labelname like \"%\"#{names}\"%\" order by id limit #{pageStart},#{pageSize}")
	public List<TLabels> selectByName(
			@Param(value = "pageStart") Integer pageStart,
			@Param(value = "pageSize") Integer pageSize,
			@Param(value = "names") String names);

	@Select("select count(1) from " + TableName
			+ " where labelname like \"%\"#{names}\"%\"")
	public int countByName(@Param(value = "names") String names);

	@Select("select * from " + TableName + " where status=0")
	public List<TLabelsRel> selectAll();

	@Select("select count(1) from " + TableName + " ")
	public int countAll();

}
