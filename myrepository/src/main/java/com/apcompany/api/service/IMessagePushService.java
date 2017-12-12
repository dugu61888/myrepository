package com.apcompany.api.service;

import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.model.message.InviteVideoMessgae;

public interface IMessagePushService {
	
		
	boolean pushMessage(String channelId,InviteVideoMessgae data);
	
	boolean pushMessage(int userId,UserTypeEnum userType,InviteVideoMessgae data);
	
	boolean pushMessageToStudent(int studentId,InviteVideoMessgae data);
	
	boolean pushMessageToTeacher(int tId,InviteVideoMessgae data);

}
