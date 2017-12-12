package com.apcompany.api.pojo;

import java.sql.Date;

public class Student {
	
	private int id;
	//显示用户名称
	private String name="";
	//登录名称
	private String loginname="";
	private int grade;
	private String phone="";
	//注册日期
	private Date cDate;
	//最后一次修改日期
	private Date uDate;
	private String password="";
	//设备号，如果用户登录设备发生改变后，需要更改设备
	private String deviceId="";
	
	private String school="";
	
	private String openid="";
	
	private String imageurl="";
	
	private int isIdentity;
	
	private int type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public Date getuDate() {
		return uDate;
	}
	public void setuDate(Date uDate) {
		this.uDate = uDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public int getIsIdentity() {
		return isIdentity;
	}
	public void setIsIdentity(int isIdentity) {
		this.isIdentity = isIdentity;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
}
