package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum TeachCourseStatusEnum {
	
	APPLY(0,"审核中"),CLOSE(1,"关闭中"),NORMAL(2,"接单中"),BUSY(3,"忙碌");
	
	public final int key;
	public final String value;
	
	private static Map<Integer, TeachCourseStatusEnum> allUserStatusEnum = Maps.newHashMap();
	
	static{
		allUserStatusEnum.put(0, APPLY);
		allUserStatusEnum.put(1,CLOSE);
		allUserStatusEnum.put(2, NORMAL);
		allUserStatusEnum.put(3, BUSY);
	}
	
	TeachCourseStatusEnum(int key,String value){
		this.key=key;
		this.value=value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static TeachCourseStatusEnum valueOf(int key){
		return allUserStatusEnum.get(key);
	}

}
