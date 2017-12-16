package com.apcompany.api.service.impl;

import com.apcompany.api.service.teachcourse.ITeachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.constrant.TeachOrderStatusEnum;
import com.apcompany.api.dao.ITeachOrderDao;
import com.apcompany.api.dao.TeacherDao;
import com.apcompany.api.model.form.OrderTeacherScoreForm;
import com.apcompany.api.model.schema.BookDayTeachDO;
import com.apcompany.api.model.schema.BookTimeTeachDO;
import com.apcompany.api.model.schema.InvitationTeachDO;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.schema.TeachOrderDO;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.api.service.IWalletService;

@Service
public class TeachOrderServiceImp implements ITeachOrderService{
	
	@Autowired private ITeachOrderDao teachOrderDao;
	@Autowired private TeacherDao teacherDao;	
	@Autowired private ITeachCourseService tcService;
	@Autowired private IBookTeachService bookService;
    @Autowired private IWalletService walletService;
	
	@Override
	public TeachOrderDO createTeachOrder(InvitationTeachDO invitationTeachDO){		
		if(invitationTeachDO==null){
			return null;
		}	
		TeachOrderDO teachOrderDO=new TeachOrderDO(invitationTeachDO);
		TeachCourseDO teachCourseDO = tcService.getTCById(teachOrderDO.getTeachCourseId());		
		teachOrderDO.setCoin(teachOrderDO.getUseMinute()*teachCourseDO.getMoneyPerMinute());
		teachOrderDao.add(teachOrderDO);
		walletService.transMoney(teachOrderDO.getStudentId(), teachOrderDO.getTeacherId(), teachOrderDO.getCoin());
		return teachOrderDO;
	}

	
	@Override
	public boolean markScoreForOrder(int studentId,OrderTeacherScoreForm form) {
		if ( form ==null){
			return false;
		}
		TeachOrderDO order = teachOrderDao.getOrderById(form.getOrderId());
		//如果已经评论过 或者无此订单 ，返回false
		if( order == null || !order.getStudentId().equals(studentId)
				|| order.getStatus() ==TeachOrderStatusEnum.COMMENT_FINISH.getKey()){
			return false;
		}
		order.setTeacherCustomerScore(form.getTeacherCustomerScore());
		order.setTeacherMannerScore(form.getTeacherMannerScore());
		order.setTeacherSkillScore(form.getTeacherSkillScore());
		teachOrderDao.markOrder(order);
		//更新该教师授课的平均评分
		float avgScore = (form.getTeacherCustomerScore()+form.getTeacherMannerScore()
				+form.getTeacherSkillScore())/3;
		tcService.updateScoreOfTeachCourse(order.getTeachCourseId(), avgScore);
		return true;
	}

	@Override
	public TeachOrderDO getById(int id) {
		return teachOrderDao.getOrderById(id);
	}

	
//	private boolean checkTeacherHasHandleInviteOrder(int teachCourseId) {
//		return teachOrderDao.getHandleInviteOrderByTeachCourse(teachCourseId)==null?false:true;
//	}
//
//	@Override
//	public TeachOrderDO commitInviteOrderByTeacher(int teacherId, int orderId) {
//		TeachCourseDO teachCourseDO= teachCourseService.getTCById(teachCourseService.getTCIdByTeacherId(teacherId));
//		if(teachCourseDO==null){
//			return null;
//		}
//		TeachOrderDO teachOrderDO = getById(orderId);
//		if(teachOrderDO == null || teachOrderDO.getType()!=0 || teachOrderDO.getStatus()!=0||
//				teachOrderDO.getTeachCourseId()!=teachCourseDO.getId()){
//			return null;
//		}
//		Date startTime = DateUtil.parseDateFromYMDHM(teachOrderDO.getBeginTime());
//		Date endTime = new Date();
//		int useMinute = DateUtil.compareMinuteBetweenDate(endTime,startTime );
//		teachOrderDO.setEndTime(DateUtil.formateDateToYMDHM(endTime));
//		teachOrderDO.setUseMinute(useMinute);
//		teachOrderDO.setMoney(useMinute*teachCourseDO.getMoneyPerMinute());
//		teachOrderDO.setStatus(1);
//		teachOrderDao.commitOrder(teachOrderDO);
//		userOnlineInfoService.updateTeacherStatus(teacherId, 1);
//		return teachOrderDO;
//	}
//
//	@Override
//	public TeachOrderDO getHandleInviteOrderByTeacher(int teacherId) {
//		Integer teachCourseId = teachCourseService.getTCIdByTeacherId(teacherId);
//		if(teachCourseId==null||teachCourseId<=0){
//			return null;
//		}
//		return teachOrderDao.getHandleInviteOrderByTeachCourse(teachCourseId);		
//	}
//
//	@Override
//	public TeachOrderDO getHandleInviteOrderByStudent(int studentId) {
//		return teachOrderDao.getHandleInviteOrderByStudent(studentId);
//	}
//
//	@Override
	public TeachOrderDO createBookOrder(BookTimeTeachDO bookTimeTeachDO) {		
		if(bookTimeTeachDO == null){
			System.out.println("booktimeTeachDO is null");
			return null;
		}
		BookDayTeachDO bookDayTeachDO= bookService.getBookDayById(bookTimeTeachDO.getBookDayId());
		if(bookDayTeachDO==null){
			System.out.println("bookDayTeachDO is null");
			return null;
		}
		TeachCourseDO teachCourseDO= tcService.getTCById(bookDayTeachDO.getTeachCourseId());
		if(teachCourseDO== null || teachCourseDO.getStatus()==TeachCourseStatusEnum.CLOSE.getKey()){
			System.out.println("teachCourseDO is null or closed");
			return null;
		}
		
		return null;
	}

	@Override
	public TeachOrderDO cancelBookOrder(int bookId) {
		TeachOrderDO teachOrderDO = teachOrderDao.getByBookId(bookId);
		if(teachOrderDO== null){
			return null;
		}
		if(teachOrderDO.getStatus()==2){
			// 计算退款费用
		   teachOrderDO.setStatus(4);
		}
		else if(teachOrderDO.getStatus()==1){
			teachOrderDO.setStatus(5);
		}
		teachOrderDao.updateOrderStatus(teachOrderDO);
		return teachOrderDO;
	}
	

}
