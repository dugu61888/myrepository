package com.apcompany.privilege.pojo;

public class UserRole {
	
	private int id;
	private int userId;
	private Role role;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", role=" + role + "]";
	}
	

}
