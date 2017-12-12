package com.apcompany.api.constrant;

import java.util.Map;

import com.google.common.collect.Maps;

public enum MessagePushEnum {
    OPEN_VIDEO(1,"open video"),
    STUDENT_CUT(2,"STUDENT CLOSE"),
    TEACHER_CUT(3,"TEACHER CUT"),
    VIDEO_CONN(4,"TEACHER CONN"),
    VIDEO_FINISH(5,"VIDEO FINISH");
	
    private final int key;
    private final String value;
	
	private static Map<Integer, MessagePushEnum> allUserTypes = Maps.newHashMap();
	
	static{
		allUserTypes.put(1, OPEN_VIDEO);
		allUserTypes.put(2, STUDENT_CUT);
		allUserTypes.put(3, TEACHER_CUT);
		allUserTypes.put(4, VIDEO_CONN);
		allUserTypes.put(5, VIDEO_FINISH);
	}
	
	MessagePushEnum(int key,String value){
		this.key=key;
		this.value=value;
	}

	
	
	public int getKey() {
		return key;
	}



	public String getValue() {
		return value;
	}



	public static MessagePushEnum valueOf(Integer key) {
		if(key==null){
			return OPEN_VIDEO;
		}
		MessagePushEnum result =allUserTypes.get(key);
		return result==null?OPEN_VIDEO:result;
	}

}
