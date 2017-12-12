package com.apcompany.learn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.learn.pojo.Vedio;

public interface VedioDao {
	@Insert("insert into vedio (name, keypointId,vediourl) values (#{name},#{keypointId},#{vediourl})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Vedio  vedio);

	@Update("update vedio set name=#{name},keypointId=#{keypointId},vediourl=#{vediourl}  where id=#{id}")
	public int update(Vedio  vedio);
	
	@Update("update vedio set name=#{name},vediourl=#{vediourl}  where keypointId=#{keypointId}")
	public int updateByKey(Vedio  vedio);

	@Delete(value = { "delete from vedio where id=#{id}" })
	public int delete(int id);
	
	@Select("select * from vedio where keypointId=#{keypointId}")
	public List<Vedio> getAll(int keypointId);
}
