package com.apcompany.privilege.pojo;

import java.util.List;

public class Role {
	
	private int id;
	private String rolename;
	
	private List<Permission> permissionslist;
	
	private List<User> userList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public List<Permission> getPermissionslist() {
		return permissionslist;
	}
	public void setPermissionslist(List<Permission> permissionslist) {
		this.permissionslist = permissionslist;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", permissionslist=" + permissionslist + ", userList="
				+ userList + "]";
	}

}
