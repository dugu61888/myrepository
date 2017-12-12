package com.apcompany.privilege.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.privilege.pojo.Role;
import com.apcompany.privilege.pojo.UserRole;

public interface UserRoleDao {

	@Insert("insert into user_role (user_id, role_id) values (#{userId},#{role.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(UserRole userRole);

	@Update("update user_role set user_id=#{userId},role_id=#{role.id} where id=#{id}")
	public int update(UserRole userRole);

	@Update("update user_role set role_id=#{role.id} where user_id=#{userId}")
	public int updateByUserId(UserRole userRole);

	@Delete(value = { "delete from user_role where id=#{id}" })
	public int delete(int id);

	@Select("select role_id from user_role where user_id=#{userId}")
	@Results({ 
		@Result(property = "id", column = "role_id"), 
		@Result(property = "rolename", column = "role_id",one=@One(select="com.apcompany.privilege.dao.RoleDao.getRoleById")) 
		})
	public List<Role> getRoleByuserId(int userId);
}
