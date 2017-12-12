package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.api.pojo.Teacher;

public interface TeacherDao {
	
	final String table="teacher";
	
	@Select("select * from "+table+" where loginname=#{loginname} and password=#{password}")
	public Teacher loginin(@Param("loginname") String loginname,@Param("password") String password);
	
	@Select("select * from "+table+" where phone=#{phone} and password=#{password}")
	public Teacher logininbyPhone(@Param("phone") String phone,@Param("password")  String password);
	
	@Select("select * from "+table+" where phone=#{phone}")
	public Teacher loginByPhone(String phone);
	
	@Select("select * from "+table+" where openid=#{openid}")
	public Teacher loginByWechat(String openid);
	
	@Select("select count(1) from "+table+" where openid=#{openid}")
	public Integer existWechat(String openid);
	
	@Select("select count(1) from "+table+" where phone=#{phone}")
	public Integer existPhone(String phone);
	
	@Select("select count(1) from "+table+" where loginname=#{loginname}")
	public Integer existName(String loginname);
	
	@Insert("insert into "+table+" (name, loginname,phone,password,deviceId,openid,imageurl) "
			+ "values (#{name},#{loginname},#{phone},#{password},#{deviceId},#{openid},#{imageurl})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Teacher teacher);

	@Update("update "+table+" set name=#{name},phone=#{phone},"
			+ "password=#{password},deviceId=#{deviceId},"
			+ "openid=#{openid},imageurl=#{imageurl}"
			+ "where id=#{id}")
	public int update(Teacher teacher);


	@Delete(value = { "delete from "+table+" where id=#{id}" })
	public int delete(int id);
	
	@Select("select id from "+table+" where loginname=#{loginname}")
	public Integer getIdbyname(String loginname);

}
