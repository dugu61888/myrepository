package com.apcompany.user.enums;

public enum QuestionClass {

	CHOISE(1, "选择题"), SAQ(2, "简答题");

	private QuestionClass(int num, String name) {
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

	public static QuestionClass getSubjectByNum(int index) {

		for (QuestionClass c : QuestionClass.values()) {
			if (c.getIndex() == index) {
				return c;
			}
		}
		return null;

	}

}
