package com.apcompany.api.pojo;

import java.io.Serializable;

public class TeacherScoreDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2289644521585016158L;
	private Integer id;
	private Integer subject;
	private Float score;
	private Integer teacherId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	

}
