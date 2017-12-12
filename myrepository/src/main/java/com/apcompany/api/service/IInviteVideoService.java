package com.apcompany.api.service;

import com.apcompany.api.model.schema.TeachOrderDO;
import com.apcompany.api.model.vo.ApiResponse;
import com.apcompany.api.pojo.VideoAccount;

public interface IInviteVideoService {
	
	ApiResponse inviteVideo(int studentId, int tcId);

	ApiResponse closeInvitationByStudent(int studentId);

	ApiResponse successInvite(int studentId);

	ApiResponse closeInvitationByTeacher(int studentId);

	ApiResponse getVideoAccount(int teacherId);

	ApiResponse pushVideoKey(int teacherId,String key);

	ApiResponse checkVideoConn(int teacherId);
	
}
