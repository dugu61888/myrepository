package com.apcompany.api.service.impl;

import com.apcompany.api.model.message.InviteVideoMessgae;
import com.apcompany.api.model.vo.ApiResponse;
import com.apcompany.api.service.teachcourse.ITeachCourseService;
import com.apcompany.api.service.teachcourse.ITeacherTeachCourseService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.apcompany.api.config.VideoSecureConfig;
import com.apcompany.api.constrant.InviteVideoStatusEnum;
import com.apcompany.api.constrant.MessagePushEnum;
import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.dao.IInvitationTeachDao;
import com.apcompany.api.model.schema.InvitationTeachDO;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.schema.TeachOrderDO;
import com.apcompany.api.pojo.VideoAccount;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.IInviteVideoService;
import com.apcompany.api.service.IMessagePushService;
import com.apcompany.api.service.IWalletService;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.api.service.IUserOnlineInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class InviteVideoServiceImp implements IInviteVideoService {

	private Logger logger = LoggerFactory.getLogger(InviteVideoServiceImp.class);
	
	@Autowired VideoSecureConfig videoSecureConfig;
	
	@Autowired
	@Qualifier("messageAlibabaPushServiceImp")
	private IMessagePushService messagePushService;
	@Autowired
	private IInvitationTeachDao invitationTeachDao;
	@Autowired
	private ITeachOrderService teachOrderService;
	@Autowired
	private ITeachCourseService tcService;
	@Autowired
	private ITeacherTeachCourseService teacherTCService;
	@Autowired
	private IBookTeachService bookService;
	@Autowired
	private IUserOnlineInfoService userOnlineInfoService;
	
	@Autowired private IWalletService walletService;

	@Transactional
	@Override
	public ApiResponse inviteVideo(int studentId, int teachCourseId) {
		Map<String ,Object> result = Maps.newHashMap();
		try{
			// check 当前是否已经有在进行中的邀请。
			InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
			if (invitationTeachDO != null) {
				return ApiResponse.buildFailure("当前用户已经有邀请");
			}
			//tc is unnormal
			if (!userOnlineInfoService.checkTCNormal(teachCourseId)) {
				return ApiResponse.buildFailure("老师或者课程状态不能被邀请，老师未在线或者课程未开课");
			}
			TeachCourseDO teachCourseDO = tcService.getTCById(teachCourseId);
			if( teachCourseDO == null ){
				return ApiResponse.buildFailure("课程不存在");
			}
			if (getHandleInvitationByTeacher(teachCourseDO.getTeacherId()) != null) {
				return ApiResponse.buildFailure("该老师已经有邀请");
			}
			invitationTeachDao.add(InvitationTeachDO.buildNew(studentId,teachCourseDO.getTeacherId(),
					teachCourseId));
			try{
				
				boolean outcome=messagePushService.pushMessageToTeacher(teachCourseDO.getTeacherId(), new InviteVideoMessgae(MessagePushEnum.OPEN_VIDEO));
				logger.info("push message outcome is "+outcome);
			}catch (Exception e){
                logger.error(e.getMessage());
                result.put("pushResult",e.getMessage());
			}
			int totalMoney = walletService.getStudentMoney(studentId);
			int moneyPerMinute = teachCourseDO.getMoneyPerMinute();

			result.put("total_minute",moneyPerMinute==0?1000:totalMoney/moneyPerMinute);
			return ApiResponse.buildSuccess(result);
		}catch (Exception e){
			return ApiResponse.buildFailure(e.getMessage());
		}
	}

	@Transactional
	@Override
	public ApiResponse closeInvitationByStudent(int studentId) {
		try{
			TeachOrderDO teachOrderDO = null;
			InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
			if (invitationTeachDO == null) {
				return ApiResponse.buildSuccess(teachOrderDO);
			}
			InviteVideoMessgae messgae = null;
			if (invitationTeachDO.getStatus()==InviteVideoStatusEnum.WAIT.getKey()){
				invitationTeachDO.onCut();
				messgae = new InviteVideoMessgae(MessagePushEnum.STUDENT_CUT);
			}else{
				invitationTeachDO.onCommit();
				teachOrderDO = teachOrderService.createTeachOrder(invitationTeachDO);
				logger.info(invitationTeachDO.toString());
				messgae = new InviteVideoMessgae(MessagePushEnum.VIDEO_FINISH);
			}
			messagePushService.pushMessageToTeacher(invitationTeachDO.getTeacherId(),messgae);
			logger.info(invitationTeachDO.toString());
			invitationTeachDao.update(invitationTeachDO);
			//logger.info(teachOrderDO.toString());
			return ApiResponse.buildSuccess(teachOrderDO);
		}catch (Exception e){
			logger.error("closeInvitationByStudent",e);
			return ApiResponse.buildFailure(e.getMessage());
		}
	}
	
	@Override
	public ApiResponse successInvite(int studentId) {
		try{
			InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
			if (invitationTeachDO == null || invitationTeachDO.getStatus()!=InviteVideoStatusEnum.CONN.getKey()) {
				return ApiResponse.buildFailure();
			}
			invitationTeachDao.update(invitationTeachDO.onConnection());
			messagePushService.pushMessageToTeacher(invitationTeachDO.getTeacherId(),
					new InviteVideoMessgae(MessagePushEnum.VIDEO_CONN));
			return ApiResponse.buildSuccess();
		}catch (Exception e){
			logger.error("successInvite",e);
			return ApiResponse.buildFailure(e.getMessage());
		}
	}

	@Transactional
	@Override
	public ApiResponse closeInvitationByTeacher(int teacherId) {
		try{
			TeachOrderDO teachOrderDO = null;
			InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
			if (invitationTeachDO == null) {
				return ApiResponse.buildFailure("不存在");
			}
			if (invitationTeachDO.getStatus()==InviteVideoStatusEnum.WAIT.getKey()){
				invitationTeachDao.update(invitationTeachDO.onCut());
				messagePushService.pushMessageToStudent(invitationTeachDO.getStudentId(),
						new InviteVideoMessgae(MessagePushEnum.TEACHER_CUT));
			}else{
				messagePushService.pushMessageToStudent(invitationTeachDO.getStudentId(),
						new InviteVideoMessgae(MessagePushEnum.VIDEO_FINISH));
			}
			teacherTCService.updateStatus(teacherId, invitationTeachDO.getTeachCourseId(), TeachCourseStatusEnum.NORMAL);
			videoSecureConfig.returnFree(invitationTeachDO.getId());

			return ApiResponse.buildSuccess(teachOrderDO);
		}catch (Exception e){
			logger.error("",e);
			return ApiResponse.buildFailure(e.getMessage());
		}
	}


	@Override
	public ApiResponse getVideoAccount(int teacherId) {
		try{
			InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
			if (invitationTeachDO == null) {
				return null;
			}
			teacherTCService.updateStatus(teacherId, invitationTeachDO.getTeachCourseId(), TeachCourseStatusEnum.BUSY);
			return ApiResponse.buildSuccess(videoSecureConfig.getFreeOne(invitationTeachDO.getId()));
		}
		catch (Exception e){
			logger.error("",e);
			return ApiResponse.buildFailure(e.getMessage());
		}
	}

	@Override
	public ApiResponse pushVideoKey(int teacherId, String key) {
		try{
			InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
			if (invitationTeachDO == null) {
				return ApiResponse.buildFailure();
			}
			messagePushService.pushMessageToStudent(invitationTeachDO.getStudentId(), new InviteVideoMessgae(key));
			return ApiResponse.buildSuccess();
		}catch (Exception e){
			logger.error("",e);
			return ApiResponse.buildFailure(e.getMessage());
		}
	}
	
	
	private InvitationTeachDO getHandleInvitationByStudent(int studentId) {
		return invitationTeachDao.getHandleInvitationByStudent(studentId);
	}

	private InvitationTeachDO getHandleInvitationByTeacher(int teacherId) {
		return invitationTeachDao
				.getHandleInvitationByTeacher(teacherId);
	}

	@Override
	public ApiResponse checkVideoConn(int teacherId) {
		try {
			InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
			if (invitationTeachDO != null && invitationTeachDO.getStatus()==InviteVideoStatusEnum.CONN.getKey()) {
				return ApiResponse.buildSuccess();
			}
			return ApiResponse.buildFailure();
		}catch (Exception e){
			logger.error("",e);
			return ApiResponse.buildFailure(e.getMessage());
		}
	}
	
	
	

	

}
