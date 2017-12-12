package com.apcompany.api.controller.video;

import com.apcompany.api.service.IInviteVideoService;
import com.apcompany.user.utils.TipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invitation/student")
public class StudentInvitationController {
	
	@Autowired private IInviteVideoService invitationService;

	// 学生主动发起邀请
	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	@ResponseBody
	public Object invite(
			@RequestAttribute(value = "studentId", required = true) Integer studentId,
			@RequestParam(value = "teachCourseId", required = true) int teachCourseId) {
		return invitationService.inviteVideo(studentId, teachCourseId);
	}
	
	@RequestMapping(value = "/cancle", method = RequestMethod.GET)
	@ResponseBody
	public Object cancleInvitation(
			@RequestAttribute(value = "studentId", required = true) Integer studentId) {
		return invitationService.closeInvitationByStudent(studentId);
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	@ResponseBody
	public Object successVideo(
			@RequestAttribute(value = "studentId", required = true) Integer studentId) {
		return invitationService.successInvite(studentId);
	}

}
