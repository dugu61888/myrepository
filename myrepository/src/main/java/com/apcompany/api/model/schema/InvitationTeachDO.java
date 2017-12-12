package com.apcompany.api.model.schema;


import java.util.Date;

import com.apcompany.api.constrant.InviteVideoStatusEnum;



public class InvitationTeachDO {
	
	private int id;
	private int studentId;
	private int teacherId;
	private int teachCourseId;
	private int status;
	private Date beginTime;
	private Date endTime;
	private Date created;
	private Date modified;
	
	public static InvitationTeachDO buildNew(int studentId, int teacherId,int teachCourseId) {
		InvitationTeachDO invitationTeachDO= new InvitationTeachDO();
		invitationTeachDO.setStudentId(studentId);
		invitationTeachDO.setTeacherId(teacherId);
		invitationTeachDO.setTeachCourseId(teachCourseId);
		invitationTeachDO.setStatus(InviteVideoStatusEnum.WAIT.getKey());
		return invitationTeachDO;
	}
	
	public InvitationTeachDO onConnection(){
		this.setStatus(InviteVideoStatusEnum.CONN.getKey());
		this.setBeginTime(new Date(System.currentTimeMillis()));
		return this;
	}
	
	public InvitationTeachDO onCommit(){
		this.setStatus(InviteVideoStatusEnum.COMMIT.getKey());
		this.setEndTime(new Date(System.currentTimeMillis()));
		return this;
	}
	
	public InvitationTeachDO onCut(){
		this.setStatus(InviteVideoStatusEnum.CUT.getKey());
		this.setEndTime(new Date(System.currentTimeMillis()));
		return this;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTeachCourseId() {
		return teachCourseId;
	}

	public void setTeachCourseId(int teachCourseId) {
		this.teachCourseId = teachCourseId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "InvitationTeachDO{" +
				"id=" + id +
				", studentId=" + studentId +
				", teacherId=" + teacherId +
				", teachCourseId=" + teachCourseId +
				", status=" + status +
				", beginTime=" + beginTime +
				", endTime=" + endTime +
				", created=" + created +
				", modified=" + modified +
				'}';
	}
}
