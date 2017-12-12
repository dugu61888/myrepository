package com.apcompany.user.enums;

public enum Difficulty {

	EASY(1, "容易"), MEDIUM(2, "中等"), HARD(3, "难");

	private Difficulty(int num, String name) {
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

	public static Difficulty getSubjectByNum(int index) {

		for (Difficulty c : Difficulty.values()) {
			if (c.getIndex() == index) {
				return c;
			}
		}
		return null;

	}

}
