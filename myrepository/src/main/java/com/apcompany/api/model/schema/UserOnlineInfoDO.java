package com.apcompany.api.model.schema;

import com.apcompany.api.constrant.UserStatusEnum;
import com.apcompany.api.constrant.UserTypeEnum;

public class UserOnlineInfoDO {

	private int id;

	private int userId;

	// 0-student 1-teacher
	private int type;

	private double lat;
	private double lng;

	// 1 在线 0 离线。2.teaching
	private int status;

	private String token;
	private String created;
	private String accessTime;

	public UserOnlineInfoDO() {

	}

	public UserOnlineInfoDO(int userId, UserTypeEnum type,UserStatusEnum status, String token, double lat,
			double lng) {
		this.userId = userId;
		this.type = type.getKey();
		this.status=status.getKey();
		this.token = token;
		this.lat = lat;
		this.lng = lng;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	

}
