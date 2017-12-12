package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum VideoAccountStatusEnum {
    USED(1,"使用中"),
    FREE(2,"空闲");
	
	public final int key;
	public final String value;
	
	private static Map<Integer, VideoAccountStatusEnum> allUserTypes = Maps.newHashMap();
	
	static{
		allUserTypes.put(1, USED);
		allUserTypes.put(2, FREE);
	}
	
	VideoAccountStatusEnum(int key,String value){
		this.key=key;
		this.value=value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public static VideoAccountStatusEnum valueOf(Integer key) {
		return allUserTypes.get(key);
		
	}
}
