package com.apcompany.api.service.impl;

import javax.annotation.Resource;

import com.apcompany.api.config.MessagePushConfig;
import com.baidu.yun.push.client.BaiduPushClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.apcompany.api.constrant.MessagePushEnum;
import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.model.message.InviteVideoMessgae;
import com.apcompany.api.service.IMessagePushService;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.service.teachcourse.ITeachCourseService;
import com.apcompany.api.util.CommonUtil;
@Service
public class MessageAlibabaPushServiceImp implements IMessagePushService {
	
	private Logger logger = LoggerFactory.getLogger(MessageAlibabaPushServiceImp.class);
	
	@Autowired
	private DefaultAcsClient client;

	@Autowired
	private BaiduPushClient baiduPushClient;
	
	@Resource private IUserOnlineInfoService userOnlineInfoService;

	@Resource private ITeachCourseService tcService;
	
	
	@Override
	public boolean pushMessage(String channelId,InviteVideoMessgae data) {
		if (channelId == null){
			return false;
		}
		PushRequest androidRequest = new PushRequest();
		try {
			 androidRequest.setProtocol(ProtocolType.HTTPS);
             //内容较大的请求，使用POST请求
             androidRequest.setMethod(MethodType.POST);
             androidRequest.setAppKey(24726857L);
             androidRequest.setTarget("DEVICE");
             androidRequest.setDeviceType("ALL");
             androidRequest.setPushType("MESSAGE");
             androidRequest.setTargetValue(channelId);
             androidRequest.setTitle("test");
             androidRequest.setBody(CommonUtil.toJson(data));
             PushResponse pushMessageToAndroidResponse;
				pushMessageToAndroidResponse = new MessagePushConfig().createAliPushClient().getAcsResponse(androidRequest);
			logger.info(pushMessageToAndroidResponse.getRequestId());
			logger.info(pushMessageToAndroidResponse.getMessageId());
			logger.info("channalId is "+channelId);
			return true;
		} catch (Exception e) {
			logger.error("pushMessage",e);
			return false;
		}
	}

	@Override
	public boolean pushMessage(int userId, UserTypeEnum userType, InviteVideoMessgae data) {
		String channel = userOnlineInfoService.getChannel(userId, userType);
		return pushMessage(channel, data);
	}

	@Override
	public boolean pushMessageToStudent(int studentId, InviteVideoMessgae data) {
		return pushMessage(studentId, UserTypeEnum.Student, data);
	}

	@Override
	public boolean pushMessageToTeacher(int teacherId, InviteVideoMessgae data) {
		return pushMessage(teacherId, UserTypeEnum.Teacher, data);
	}


	public static void main(String []args){
		MessageAlibabaPushServiceImp co=new MessageAlibabaPushServiceImp();
		co.pushMessage("d2f6e42bc9dc40dba2445c3ae5a20d87",new InviteVideoMessgae(MessagePushEnum.OPEN_VIDEO));
	}

	
	
	

}
