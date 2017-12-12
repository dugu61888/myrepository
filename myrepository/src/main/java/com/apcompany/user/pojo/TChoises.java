package com.apcompany.user.pojo;

import java.sql.Timestamp;

public class TChoises {
	private int id;
	private int questionid;
	private String choise;
	private int status;
	private Timestamp ctime;
	private Timestamp utime;
	
	
	public String getChoise() {
		return choise;
	}
	public void setChoise(String choise) {
		this.choise = choise;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCtime() {
		return ctime;
	}
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	public Timestamp getUtime() {
		return utime;
	}
	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	@Override
	public String toString() {
		return "TChoises [id=" + id + ", questionid=" + questionid + ", choise=" + choise + ", status=" + status
				+ ", ctime=" + ctime + ", utime=" + utime + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
