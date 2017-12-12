package com.apcompany.privilege.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.privilege.pojo.Role;

public interface RoleDao {
	
	
	@Insert("insert into role (rolename) values (#{rolename})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Role role);

	@Update("update role set rolename=#{rolename} where id=#{id}")
	public int update(Role role);


	@Delete(value = { "delete from role where id=#{id}" })
	public int delete(int id);
	
	@Select("select * from role where id=#{roleId}")
	@Results(
			{
				@Result(column="id",property="id",id=true),
				@Result(column="rolename",property="rolename"),
				@Result(column="id",property="permissionslist",many=@Many(select="com.apcompany.privilege.dao.PermissionDao.getPermissionByRoleId"))
			}
			)
	public List<Role> getRoleById(int roleId);
	
	
	
	@Select("select * from role where id=#{roleId}")
	@Results(
			{
				@Result(column="id",property="id",id=true),
				@Result(column="rolename",property="rolename"),
				@Result(column="id",property="permissionslist",many=@Many(select="com.apcompany.privilege.dao.PermissionDao.getPermissionByRoleId"))
			}
			)
	public List<Role> getRoleUserById(int roleId);
	
}
