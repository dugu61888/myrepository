package com.apcompany.privilege.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.privilege.pojo.Permission;

public interface PermissionDao {
	@Insert("insert into permission (permissionname, role_id) values (#{permissionname},#{roleId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Permission  permission);

	@Update("update permission set permissionname=#{permissionname},role_id=#{roleId} where id=#{id}")
	public int update(Permission  permission);


	@Delete(value = { "delete from permission where id=#{id}" })
	public int delete(int id);
	
	@Select("select * from permission where role_id=#{roleId}")
	@Results({
		@Result(column="id",property="id",id=true),
		@Result(column="permissionname",property="permissionname"),
		@Result(column="role_id",property="roleId")
	})
	public List<Permission> getPermissionByRoleId(int roleId);
}
