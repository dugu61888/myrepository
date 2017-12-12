package com.apcompany.privilege.pojo;

public class Permission {
	
	private int id;
	private String permissionname;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPermissionname() {
		return permissionname;
	}
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionname=" + permissionname + ", roleId=" + roleId + "]";
	}

	

}
