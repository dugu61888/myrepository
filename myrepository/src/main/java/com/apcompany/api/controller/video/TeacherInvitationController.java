package com.apcompany.api.controller.video;

import com.apcompany.api.service.IInviteVideoService;
import com.apcompany.user.utils.TipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invite/teacher")
public class TeacherInvitationController {

	@Autowired
	private IInviteVideoService invitationService;
	
	
	@RequestMapping(value = "/get/videoaccount.json", method = RequestMethod.GET)
	@ResponseBody
	public Object getCode(
			@RequestAttribute(value = "teacherId", required = true) Integer teacherId) {
		return invitationService.getVideoAccount(teacherId);
	}

	@RequestMapping(value = "/push/videokey", method = RequestMethod.GET)
	@ResponseBody
	public Object pushVideoKey(
			@RequestAttribute(value = "teacherId", required = true) Integer teacherId,
			@RequestParam(value="videoKey",required=true) String videoKey
			) {
		return invitationService.pushVideoKey(teacherId, videoKey);
	}
	
	@RequestMapping(value = "/cancle.do", method = RequestMethod.GET)
	@ResponseBody
	public Object cancleVideo(
			@RequestAttribute(value = "teacherId", required = true) Integer teacherId
			) {
		return invitationService.closeInvitationByTeacher(teacherId);
	}
	
	@RequestMapping(value = "/check.do", method = RequestMethod.GET)
	@ResponseBody
	public Object checkVideoSuccess(
			@RequestAttribute(value = "teacherId", required = true) Integer teacherId
			) {
		return invitationService.checkVideoConn(teacherId);
	}
	
	

}
