package com.apcompany.api.model.schema.teachcourse;

import java.io.Serializable;

public class CourseDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6543901363456924911L;
	private Integer id;
	private String name;
	private String created;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	

}
