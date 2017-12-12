package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.api.model.schema.UserOnlineInfoDO;

public interface UserOnlineInfoDao {
	
	@Insert("INSERT INTO "
			+" user_online_info(" +
			"    user_id," +
			"    type,token," +
			"    status," +
			"    address," +
			"    access_time," +
			"    created) "
			+" VALUES(" +
			"    #{userId}," +
			"    #{type}," +
			"    #{token}," +
			"    #{status}," +
			"    point(${lat},${lng})," +
			"    NOW()," +
			"    NOW()) " +
			" ON DUPLICATE KEY " +
			" UPDATE " +
			"   token=#{token}," +
			"   status =#{status}," +
			"   address = point(${lat},${lng})," +
			"   access_time = NOW()")
	public void addwithLogin(UserOnlineInfoDO userOnlineInfoDO);
		
	@Select("select *  from user_online_info where type=#{userType} and "
			+ " user_id=#{userId} ")
	public UserOnlineInfoDO getUserInfoByUserId(
			@Param("userId")int userId,
			@Param("userType")int userType);
	
	@Update("update user_online_info set status=#{status} where id=#{id}")
	public void updateStatusByID(int id, int status);
	
	@Select("select * from user_online_info where token=#{token} and status=1")
	public UserOnlineInfoDO checkAccessByToken(@Param("token")String token);
	
	@Update("update user_online_info set access_time=now() where id=#{id}")
	public void refreshAccessTime(int id);
	
	@Update("update user_online_info set status=#{status} where userId=#{userId} and type=#{type}")
	public void quitAccess(@Param("userId")int userId, @Param("type")int type,@Param("status")int status);
	
	@Update("update user_online_info set status=#{status} where user_id=#{teacherId} and type=1")
	public void updateTeacherStatus(int teacherId,int status);
	
	@Update("update user_online_info set status=0 where access_time<#{lastAccessTime}")
	public void offlineAuto(String lastAccessTime);
	
	@Update("update user_online_info set channel=#{channel} where user_id=#{userId} and type=#{type}")
	void addChannel(@Param("userId")int userId, @Param("type")int type,@Param("channel")String channel);
	
	@Select("select channel from user_online_info where user_id=#{userId} and type=#{type}")
	String getChannelByUser(
			@Param("userId") int userId,
			@Param("type")int type);
	
}
