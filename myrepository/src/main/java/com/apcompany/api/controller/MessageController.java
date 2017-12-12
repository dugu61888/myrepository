package com.apcompany.api.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apcompany.api.service.IMessagePushService;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.user.utils.TipUtil;


@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired IUserOnlineInfoService userOnlineInfoService;
	
	@Autowired
	@Qualifier("messageAlibabaPushServiceImp")
	private IMessagePushService iMessagePushService;
	
	@RequestMapping(value="/push/channel",method = RequestMethod.POST)
	@ResponseBody
	public Object pushChannel(
			@RequestAttribute(value="studentId",required=false) Integer studentId,
			@RequestAttribute(value="teacherId",required=false) Integer teacherId,
			@RequestParam(value="channelId",required=true) String channelId
			){
		
		return TipUtil.success(userOnlineInfoService.addChannelInfo(studentId, teacherId, channelId));

	}
	
	
	
	

}
