package com.apcompany.api.model.form;

import com.apcompany.api.constrant.OnlineTCOrderTypeEnum;

public class OnlineTCForm {
	
	private int studentId;
	private int courseId;
	private String orderType;
	private int index;
	private int limit;
	
	public OnlineTCForm(){
		
	}
	
    public OnlineTCForm(int studentId,int courseId,int orderType,
    		int index,int limit){
    	this.studentId=studentId;
    	this.courseId=courseId;
    	this.index=index;
    	this.limit=limit;
    	OnlineTCOrderTypeEnum e= OnlineTCOrderTypeEnum.valueOf(orderType);
    	if(e==null){
    		e = OnlineTCOrderTypeEnum.SCORE;
    	}
		this.orderType=e.getValue();
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	

}
