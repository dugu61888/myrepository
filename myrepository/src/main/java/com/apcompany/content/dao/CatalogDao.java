package com.apcompany.content.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.content.pojo.Catalog;

public interface CatalogDao {
	@Insert("insert into catalog (name, picture,parentId) values (#{name},#{picture},#{parentId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Catalog  catalog);

	@Update("update catalog set name=#{name},picture=#{picture},parentId=#{parentId}  where id=#{id}")
	public int update(Catalog  catalog);

	@Delete(value = { "delete from catalog where id=#{id}" })
	public int delete(int id);
	
	@Select("select * from catalog")
	public List<Catalog> getAll();
	
}
