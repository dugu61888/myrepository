package com.apcompany.api.service.impl;
import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.vo.ApiResponse;
import com.apcompany.api.service.teachcourse.ITeachCourseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.constrant.UserStatusEnum;
import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.dao.UserOnlineInfoDao;
import com.apcompany.api.model.schema.UserOnlineInfoDO;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.util.CommonUtil;

@Service
public class UserOnlineInfoServiceImp implements IUserOnlineInfoService {
	
	@Autowired private UserOnlineInfoDao onlineInfoDao;

	@Autowired private ITeachCourseService teachCourseService;

	
	private Logger logger = LoggerFactory.getLogger(UserOnlineInfoServiceImp.class);
	
	@Override
	public boolean checkAccessToken(String token) {
		if(token==null){
			return false;
		}
		UserOnlineInfoDO  userOnlineInfoDO = onlineInfoDao.checkAccessByToken(token);
		if(userOnlineInfoDO == null){
			return false;
		}
		onlineInfoDao.refreshAccessTime(userOnlineInfoDO.getId());
		return true;
	}

	@Override
	public boolean checkTCNormal(int tcId) {
		TeachCourseDO teachCourseDO = teachCourseService.getTCById(tcId);
		if (teachCourseDO == null || teachCourseDO.getStatus()!= TeachCourseStatusEnum.NORMAL.getKey()) {
			return false;
		}
        UserOnlineInfoDO userOnlineInfoDO = onlineInfoDao.getUserInfoByUserId(teachCourseDO.getTeacherId(),UserTypeEnum.Teacher.getKey());
        if ( userOnlineInfoDO == null || userOnlineInfoDO.getStatus()== UserStatusEnum.OFFLINE.getKey()){
        	return false;
		}
		return true;
	}

	@Override
	public boolean quitAccess(int userId, int type) {
		onlineInfoDao.quitAccess(userId, type,UserStatusEnum.OFFLINE.getKey());
		return true;
	}

	@Override
	public String addWithLogin(int userId, UserTypeEnum type,UserStatusEnum status,double lat, double lng) {
		try {
			String token= CommonUtil.createToken(userId, type);
			UserOnlineInfoDO userOnlineInfoDO = new UserOnlineInfoDO(userId, type,status, token, lat, lng);
			onlineInfoDao.addwithLogin(userOnlineInfoDO);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateTeacherStatus(int teacherId, int status) {
		onlineInfoDao.updateTeacherStatus(teacherId, status);
		return true;
	}

	@Override
	public void offlineAuto(String lastAccessTime) {
		onlineInfoDao.offlineAuto(lastAccessTime);		
	}
	
	
	@Override
	public ApiResponse addChannelInfo(Integer studentId, Integer teacherId, String channel) {
		if(studentId==null &&teacherId==null){
			return ApiResponse.buildSuccess("token is valid");
		}
		Integer userId= studentId;
		int type=UserTypeEnum.Student.getKey();
		if(userId == null){
			type=UserTypeEnum.Teacher.getKey();
			userId= teacherId;
		}
		logger.info("parasm is"+userId+" "+type+""+channel);
		onlineInfoDao.addChannel(userId, type, channel);
		return ApiResponse.buildSuccess("add success");
	}
	
	@Override
	public String getChannelByTCID(int tcid){
		int tid= teachCourseService.getTeacherIdById(tcid);
		return getChannel(tid, UserTypeEnum.Teacher);
	}

	@Override
	public String getChannel(int userId, UserTypeEnum userTypeEnum) {
		return onlineInfoDao.getChannelByUser(userId, userTypeEnum.getKey());
	}

}
