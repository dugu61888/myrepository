package com.apcompany.privilege.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.privilege.pojo.User;

public interface UserDao {

	@Select("select * from users where id=#{id}")
	@Results({
			  @Result(property = "id", column = "id", id = true),
			  @Result(property = "name", column = "name"),
			  @Result(property = "password", column = "password"),
			  @Result(property="roleList",column="id",many=@Many(select="com.apcompany.privilege.dao.UserRoleDao.getRoleByuserId"))
			})
	public User getUsers(int id);
	
	
	@Select("select * from users where name=#{name} and password=#{password}")
	@Results({
			  @Result(property = "id", column = "id", id = true),
			  @Result(property = "name", column = "name"),
			  @Result(property = "password", column = "password"),
			  @Result(property="roleList",column="id",many=@Many(select="com.apcompany.privilege.dao.UserRoleDao.getRoleByuserId"))
			})
	public User getUsersByname(@Param("name") String name,@Param("password")String password);

	@Insert("insert into users (name, password) values (#{name},#{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(User user);

	@Update("update users set name=#{name},password=#{password} where id=#{id}")
	public int update(User user);


	@Delete(value = { "delete from users where id=#{id}" })
	public int delete(int id);

}
