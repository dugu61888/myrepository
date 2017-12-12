package com.apcompany.user.pojo;

import com.apcompany.user.enums.Difficulty;
import com.apcompany.user.enums.QuestionClass;
import com.apcompany.user.enums.QuestionType;

public class TNonRelCondition {
	// 00科目 + 00出版 + 000000年份 + 0是否真题 + 00题号 + 0难度 + 0题类 + 0题型 + 0计算器 + 0数表 +0图片
	// + 0证明题 + 0部分;
	// 1科目
	private String subject;
	// 2.出版
	private String reference;
	// 3.年份
	private String year;
	// 4.是否真题
	private boolean isRealQuestion;
	// 5.题号
	private String subjectNum;

	// 6.难度
	private Difficulty difficulty;

	// 7.题类
	private QuestionClass questionClass;
	// 8.题型
	private QuestionType questionType;
	// 9.计算器与否
	private boolean isCounter;
	// 10 数表有无
	private boolean isCharm;
	// 11 图片有无
	private boolean isPic;
	// 12 证明题
	private boolean isProof;
	// 13 部分
	private int section;

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public boolean isRealQuestion() {
		return isRealQuestion;
	}

	public void setRealQuestion(boolean isRealQuestion) {
		this.isRealQuestion = isRealQuestion;
	}

	public String getSubjectNum() {
		return subjectNum;
	}

	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public QuestionClass getQuestionClass() {
		return questionClass;
	}

	public void setQuestionClass(QuestionClass questionClass) {
		this.questionClass = questionClass;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public boolean isCounter() {
		return isCounter;
	}

	public void setCounter(boolean isCounter) {
		this.isCounter = isCounter;
	}

	public boolean isCharm() {
		return isCharm;
	}

	public void setCharm(boolean isCharm) {
		this.isCharm = isCharm;
	}

	public boolean isPic() {
		return isPic;
	}

	public void setPic(boolean isPic) {
		this.isPic = isPic;
	}

	public boolean isProof() {
		return isProof;
	}

	public void setProof(boolean isProof) {
		this.isProof = isProof;
	}

}
