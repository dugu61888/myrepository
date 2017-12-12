package com.apcompany.user.pojo;

import java.sql.Timestamp;
import java.util.List;

public class TQuestions {
	private int id;
	private String question;
	private int status;
	private int keypointId;
	private int type;
	private Timestamp ctime;
	private Timestamp utime;
	private List<TChoises> choices;
	private List<TLabelsQuestionRel> labels;
	private TAnswers tAnswers;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
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
	
	public TAnswers gettAnswers() {
		return tAnswers;
	}
	public void settAnswers(TAnswers tAnswers) {
		this.tAnswers = tAnswers;
	}
	public List<TChoises> getChoices() {
		return choices;
	}
	public void setChoices(List<TChoises> choices) {
		this.choices = choices;
	}
	
	public List<TLabelsQuestionRel> getLabels() {
		return labels;
	}
	public void setLabels(List<TLabelsQuestionRel> labels) {
		this.labels = labels;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKeypointId() {
		return keypointId;
	}
	public void setKeypointId(int keypointId) {
		this.keypointId = keypointId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "TQuestions [id=" + id + ", question=" + question + ", status=" + status + ", keypointId=" + keypointId
				+ ", type=" + type + ", ctime=" + ctime + ", utime=" + utime + ", choices=" + choices + ", labels="
				+ labels + ", tAnswers=" + tAnswers + "]";
	}

}
