package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum UserTypeEnum {
	
	Student(0,"studentId"),Teacher(1,"teacherId");
	
	public final int key;
	public final String value;
	
	private static Map<Integer, UserTypeEnum> allUserTypes = Maps.newHashMap();
	
	static{
		allUserTypes.put(0, Student);
		allUserTypes.put(1, Teacher);
	}
	
	UserTypeEnum(int key,String value){
		this.key=key;
		this.value=value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public static UserTypeEnum valueOf(Integer key) {
		if(key==null){
			return null;
		}
		return allUserTypes.get(key);
	}

}
