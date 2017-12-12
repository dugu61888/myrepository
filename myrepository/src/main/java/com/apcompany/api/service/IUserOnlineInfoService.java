package com.apcompany.api.service;

import com.apcompany.api.constrant.UserStatusEnum;
import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.model.vo.ApiResponse;


public interface IUserOnlineInfoService {
	
	//after login success,proccessed 
	String addWithLogin(int userId,UserTypeEnum type,UserStatusEnum status,double lat,double lng);
	
	//拦截器中调用此方法check 
	boolean checkAccessToken(String token);
	
	//直接呼叫老师时，check 
	boolean checkTCNormal(int tcId);
	
	//退出登陆
	boolean quitAccess(int userId, int type);
	
	boolean updateTeacherStatus(int teacherId,int status);

	void offlineAuto(String lastAccessTime);
	
	ApiResponse addChannelInfo(Integer studentId, Integer teacherId, String channel);

	String getChannelByTCID(int tcid);
	
	String getChannel(int userId, UserTypeEnum userTypeEnum);
}
