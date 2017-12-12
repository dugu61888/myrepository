package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.api.pojo.Student;

public interface StudentDao {
	
	final String table="student";
	
	@Select("select * from "+table+" where loginname=#{loginname} and password=#{password}")
	
	public Student loginin(@Param("loginname") String loginname,@Param("password") String password);
	
	@Select("select * from "+table+" where phone=#{phone} and password=#{password}")
	public Student logininbyPhone(@Param("phone") String phone,@Param("password")  String password);
	
	@Select("select * from "+table+" where phone=#{phone}")
	public Student loginByPhone(String phone);
	
	@Select("select * from "+table+" where openid=#{openid}")
	public Student loginByWechat(String openid);
	
	@Select("select count(1) from "+table+" where openid=#{openid}")
	public Integer existWechat(String openid);
	
	@Select("select count(1) from "+table+" where phone=#{phone}")
	public Integer existPhone(String phone);
	
	@Select("select count(1) from "+table+" where loginname=#{loginname}")
	public Integer existName(String loginname);
	
	@Select("select count(1) from "+table+" where password=#{password} and id=#{id}")
	public Integer existpassword(Integer id,String password);
	
	@Insert("insert into "+table+" (name, loginname,grade,phone,password,deviceId,school,openid,imageurl,type) "
			+ "values (#{name},#{loginname},#{grade},#{phone},#{password},#{deviceId},#{school},#{openid},#{imageurl},#{type})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Student student);

	@Update("update "+table+" set name=#{name},grade=#{grade},phone=#{phone},"
			+ "password=#{password},deviceId=#{deviceId},"
			+ "school=#{school},openid=#{openid},imageurl=#{imageurl},type=#{type} "
			+ "where id=#{id}")
	public int update(Student student);
	
	@Update("update "+table+" set "
			+ " password=#{password} "
			+ "where id=#{id}")
	public int updatePassword(@Param("id") Integer id,@Param("password") String newpassword);
	
	@Update("update "+table+" set "
			+ " password=#{password} "
			+ "where phone=#{phone}")
	public int updatePasswordByphone(@Param("phone") String phone,@Param("password") String newpassword);
	

	@Update("update "+table+" set "
			+ " phone=#{phone} "
			+ "where id=#{id}")
	public int updateBandPhone(@Param("id") Integer id,@Param("phone") String phone);

	@Delete(value = { "delete from "+table+" where id=#{id}" })
	public int delete(int id);
}
