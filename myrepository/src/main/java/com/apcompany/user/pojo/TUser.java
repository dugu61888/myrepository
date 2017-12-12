package com.apcompany.user.pojo;

import java.util.Date;

public class TUser {
	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	private String userName;

	/**
	 * 
	 */
	private Date userBirthday;

	/**
	 * 
	 */
	private Double userSalary;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public Double getUserSalary() {
		return userSalary;
	}

	public void setUserSalary(Double userSalary) {
		this.userSalary = userSalary;
	}
}