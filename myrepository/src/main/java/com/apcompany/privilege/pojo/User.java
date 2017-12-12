package com.apcompany.privilege.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class User {
	
	private int id;
	private String name;
	private String password;
	
	private List<Role> roleList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public Set<String> getRoleName(){
		List<Role> list=getRoleList();
		Set<String> set=new HashSet<>();
		for(Role role:list){
			set.add(role.getRolename());
		}
		return set;
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", roleList=" + roleList + "]";
	}

}
