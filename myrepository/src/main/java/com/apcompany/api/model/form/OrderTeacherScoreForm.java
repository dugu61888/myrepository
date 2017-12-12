package com.apcompany.api.model.form;

public class OrderTeacherScoreForm {
	
	private Integer orderId;
	private Float teacherCustomerScore;
	private Float teacherMannerScore;
	private Float teacherSkillScore;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	

}
