package com.apcompany.api.model.schema.teachcourse;

import com.apcompany.api.constrant.TeachCourseStatusEnum;

import java.io.Serializable;

public class TeachCourseDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 891545544059941592L;
	private int id;
	private int teacherId;
	private int courseId;
	private String created;	
	private String modified;
	/*
	 * 不接单：0  接单中：1  忙碌中：2
	 */
	private int status;	
	private int startTeachDay;
	private int teachStudentNum;
	private float teachScore;
	private int moneyPerMinute;
	
	public TeachCourseDO(){
		
	}
	
	
	public TeachCourseDO apply(int teacherId, int courseId, int moneyPerMinute){
		this.setTeacherId(teacherId);
		this.setCourseId(courseId);
		this.setMoneyPerMinute(moneyPerMinute);
		this.setStatus(TeachCourseStatusEnum.APPLY.getKey());
		return this;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
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


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getStartTeachDay() {
		return startTeachDay;
	}


	public void setStartTeachDay(int startTeachDay) {
		this.startTeachDay = startTeachDay;
	}


	public int getTeachStudentNum() {
		return teachStudentNum;
	}


	public void setTeachStudentNum(int teachStudentNum) {
		this.teachStudentNum = teachStudentNum;
	}


	public float getTeachScore() {
		return teachScore;
	}


	public void setTeachScore(float teachScore) {
		this.teachScore = teachScore;
	}


	public int getMoneyPerMinute() {
		return moneyPerMinute;
	}


	public void setMoneyPerMinute(int moneyPerMinute) {
		this.moneyPerMinute = moneyPerMinute;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
