package com.apcompany.user.pojo;

import java.util.ArrayList;
import java.util.List;

public class SuperData {
	private TAnswers tAnswers;
	private List<TChoises> tChoises=new ArrayList<>();
	private TQuestions tQuestions;
	public TAnswers gettAnswers() {
		return tAnswers;
	}
	public void settAnswers(TAnswers tAnswers) {
		this.tAnswers = tAnswers;
	}
	public List<TChoises> gettChoises() {
		return tChoises;
	}
	public void settChoises(List<TChoises> tChoises) {
		this.tChoises = tChoises;
	}
	public TQuestions gettQuestions() {
		return tQuestions;
	}
	public void settQuestions(TQuestions tQuestions) {
		this.tQuestions = tQuestions;
	}
	@Override
	public String toString() {
		return "SuperData [tAnswers=" + tAnswers + ", tChoises=" + tChoises + ", tQuestions=" + tQuestions + "]";
	}
}
