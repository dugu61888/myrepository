package com.apcompany.api.model.vo.teachcourse;

import com.apcompany.api.model.schema.teachcourse.CourseDO;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;

public class TeachCourseVO {
	
	private int teachCourseId;
	private int courseId;
	private String courseName;
	private int status;	
	private int startTeachDay;
	private int teachStudentNum;
	private float teachScore;
	private int moneyPerMinute;
	private String created;
	private String modified;

	public TeachCourseVO(){}

	public TeachCourseVO of(TeachCourseDO teachCourseDO){
		if (null == teachCourseDO){
			return this;
		}
		this.setTeachCourseId(teachCourseDO.getId());
		this.setTeachScore(teachCourseDO.getTeachScore());
		this.setCreated(teachCourseDO.getCreated());
		this.setModified(teachCourseDO.getModified());
		this.setMoneyPerMinute(teachCourseDO.getMoneyPerMinute());
		this.setStartTeachDay(teachCourseDO.getStartTeachDay());
		this.setStatus(teachCourseDO.getStatus());
		return this;
	}

	public TeachCourseVO of(CourseDO courseDO){
		if (null == courseDO){
			return this;
		}
		this.setCourseId(courseDO.getId());
		this.setCourseName(courseDO.getName());
		return this;
	}


	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getTeachCourseId() {
		return teachCourseId;
	}
	public void setTeachCourseId(int teachCourseId) {
		this.teachCourseId = teachCourseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	
	

}
