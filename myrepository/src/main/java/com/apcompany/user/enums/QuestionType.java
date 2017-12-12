package com.apcompany.user.enums;

public enum QuestionType {

	APP(1, "应用题"), CAL(2, "计算题");

	private QuestionType(int num, String name) {
		this.num = num;
		this.name = name;
	}

	private String name;

	private int num;

	public int getIndex() {
		return num;
	}

	public String getName() {
		return name;
	}

	public static QuestionType getSubjectByNum(int index) {

		for (QuestionType c : QuestionType.values()) {
			if (c.getIndex() == index) {
				return c;
			}
		}
		return null;

	}

}
