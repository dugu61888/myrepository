package com.apcompany.api.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.config.MessagePushConfig;
import com.apcompany.api.constrant.MessagePushEnum;
import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.model.message.InviteVideoMessgae;
import com.apcompany.api.service.IMessagePushService;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.service.teachcourse.ITeachCourseService;
import com.apcompany.api.util.CommonUtil;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
@Service
public class MessagePushServiceImp implements IMessagePushService {
	
	private Logger logger = LoggerFactory.getLogger(MessagePushServiceImp.class);
	
	@Autowired private BaiduPushClient baiduPushClient;
	
	@Resource private IUserOnlineInfoService userOnlineInfoService;
	@Resource private ITeachCourseService tcService;
	 

	@Override
	public boolean pushMessage(String channelId,InviteVideoMessgae data) {
		if (channelId == null){
			return false;
		}
		PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
                addChannelId(channelId).
                addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                addMessageType(0).              //设置消息类型,0表示透传消息,1表示通知,默认为0.
                addMessage(CommonUtil.toJson(data));
		try {
			PushMsgToSingleDeviceResponse outcome=baiduPushClient.pushMsgToSingleDevice(request);
			System.out.println(outcome);
			logger.info(outcome.toString());
			return true;
		} catch (PushClientException e) {
			logger.error("push client error because of "+e.getMessage());
			throw new RuntimeException(e.getMessage());
		} catch (PushServerException e) {
			logger.error("push Server error because of "+e.getMessage());
			throw new RuntimeException(e.getMessage());
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
	public boolean pushMessageToTeacher(int tId, InviteVideoMessgae data) {
		return pushMessage(tId, UserTypeEnum.Teacher, data);
	}


	public static void main(String[]args){
		MessagePushConfig co= new MessagePushConfig();

		MessagePushServiceImp mess= new MessagePushServiceImp();
		mess.baiduPushClient = co.createBaiduPushClient();
		mess.pushMessage("3739564188844514811",new InviteVideoMessgae(MessagePushEnum.OPEN_VIDEO));

	}

	
	
	

}
