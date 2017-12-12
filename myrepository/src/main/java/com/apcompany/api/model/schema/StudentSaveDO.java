package com.apcompany.api.model.schema;

import java.io.Serializable;

public class StudentSaveDO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer studentId;
	private Integer teachCourseId;
	private String saveTime;
	
	
	public StudentSaveDO(){
		
	}
	
    public StudentSaveDO(int studentId,int teachCourseId){
		this.studentId=studentId;
		this.teachCourseId=teachCourseId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	public Integer getTeachCourseId() {
		return teachCourseId;
	}

	public void setTeachCourseId(Integer teachCourseId) {
		this.teachCourseId = teachCourseId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	
	

}
