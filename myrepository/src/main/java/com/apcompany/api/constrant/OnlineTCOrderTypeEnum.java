package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum OnlineTCOrderTypeEnum {
	
	MONEY(1,"t_c.money_per_minute"),SCORE(2,"t_c.teach_score");
	
	public final int key;
	public final String value;
	
	private static Map<Integer, OnlineTCOrderTypeEnum> allUserTypes = Maps.newHashMap();
	
	static{
		allUserTypes.put(1, MONEY);
		allUserTypes.put(2, SCORE);
	}
	
	OnlineTCOrderTypeEnum(int key,String value){
		this.key=key;
		this.value=value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public static OnlineTCOrderTypeEnum valueOf(Integer key) {
		if(key==null){
			return SCORE;
		}
		OnlineTCOrderTypeEnum result =allUserTypes.get(key);
		return result==null?SCORE:result;
	}


}
