package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum BookDayStatusEnum {
	
	NOT_BOOK(0,"禁止预约"),NORMAL_BOOK(1,"正常"),FULL_BOOK(2,"已经约满");
	
	public final int key;
	public final String desc;
	
	public static final Map<Integer, BookDayStatusEnum> allBookDayStatusEnum =Maps.newHashMap();
	static{
		allBookDayStatusEnum.put(0, NOT_BOOK);
		allBookDayStatusEnum.put(1, NORMAL_BOOK);
		allBookDayStatusEnum.put(2, FULL_BOOK);
	}
	BookDayStatusEnum(int key,String desc){
		this.key=key;
		this.desc=desc;
	}
	public int getKey() {
		return key;
	}
	public String getDesc() {
		return desc;
	}
	
	public static BookDayStatusEnum valueOf(int key){
		return allBookDayStatusEnum.get(key);
	}

}
