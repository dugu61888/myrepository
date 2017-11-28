package com.apcompany.api.pojo;

import com.apcompany.api.constrant.VideoAccountStatusEnum;

public class VideoAccount {

	private String key;
	private String password;
	private VideoAccountStatusEnum  status;
	private int invitationId;
	
	
	
	public int getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(int invitationId) {
		this.invitationId = invitationId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public VideoAccountStatusEnum getStatus() {
		return status;
	}
	public void setStatus(VideoAccountStatusEnum status) {
		this.status = status;
	}
	
	
}
