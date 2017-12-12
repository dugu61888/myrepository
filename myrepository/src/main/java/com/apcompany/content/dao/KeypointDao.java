package com.apcompany.content.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.content.pojo.Keypoint;

public interface KeypointDao {
	@Insert("insert into keypoint (name, parentId,catalogId) values (#{name},#{parentId},#{catalogId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Keypoint keypoint);

	@Update("update keypoint set name=#{name},parentId=#{parentId},catalogId=#{catalogId} where id=#{id}")
	public int update(Keypoint keypoint);

	@Delete(value = { "delete from keypoint where id=#{id}" })
	public int delete(int id);

	@Select(" select * from keypoint where catalogId=#{catalogId}")
	public List<Keypoint> selectByCatalogId(int catalogId);
}
