package com.apcompany.api.model.schema;

import java.util.Date;

import com.apcompany.api.constrant.TeachOrderStatusEnum;
import com.apcompany.api.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TeachOrderDO {

		private int id;
		private int teachCourseId;
		private int teacherId;
		private int studentId;
		private Date beginTime;
		private Date endTime;
		private int useMinute;
		private int coin;
		//状态 0.inite进行中， 1.支付中 2.支付成功 3.已经评价 4.已退款。5. 已取消（book类型，未支付）
		private int status;
		private float teacherCustomerScore;
		private float teacherMannerScore;
		private float teacherSkillScore;
		private String created;
		private String modified;

		public TeachOrderDO (){}

		public TeachOrderDO (InvitationTeachDO invitationTeachDO){
			if (invitationTeachDO== null){
				return;
			}
			this.teachCourseId=invitationTeachDO.getTeachCourseId();
			this.teacherId=invitationTeachDO.getTeacherId();
			this.studentId=invitationTeachDO.getStudentId();
			this.beginTime=invitationTeachDO.getBeginTime();
			this.endTime=invitationTeachDO.getEndTime();
			this.useMinute = DateUtil.compareMinuteBetweenDate(endTime,beginTime);
			this.status= TeachOrderStatusEnum.PAY_FINISH.getKey();
		}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTeachCourseId() {
		return teachCourseId;
	}
	public void setTeachCourseId(Integer teachCourseId) {
		this.teachCourseId = teachCourseId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getUseMinute() {
		return useMinute;
	}
	public void setUseMinute(int useMinute) {
		this.useMinute = useMinute;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Float getTeacherCustomerScore() {
		return teacherCustomerScore;
	}
	public void setTeacherCustomerScore(Float teacherCustomerScore) {
		this.teacherCustomerScore = teacherCustomerScore;
	}
	public Float getTeacherMannerScore() {
		return teacherMannerScore;
	}
	public void setTeacherMannerScore(Float teacherMannerScore) {
		this.teacherMannerScore = teacherMannerScore;
	}
	public Float getTeacherSkillScore() {
		return teacherSkillScore;
	}
	public void setTeacherSkillScore(Float teacherSkillScore) {
		this.teacherSkillScore = teacherSkillScore;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "TeachOrderDO{" +
				"id=" + id +
				", teachCourseId=" + teachCourseId +
				", teacherId=" + teacherId +
				", studentId=" + studentId +
				", beginTime=" + beginTime +
				", endTime=" + endTime +
				", useMinute=" + useMinute +
				", coin=" + coin +
				", status=" + status +
				", teacherCustomerScore=" + teacherCustomerScore +
				", teacherMannerScore=" + teacherMannerScore +
				", teacherSkillScore=" + teacherSkillScore +
				", created='" + created + '\'' +
				", modified='" + modified + '\'' +
				'}';
	}

	public static void main(String[]args) throws JsonProcessingException {
		ObjectMapper mapper =new ObjectMapper();
		String str = mapper.writeValueAsString(new TeachOrderDO());
		System.out.println(str);
	}
	
	
}	
