package com.apcompany.user.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

	public static String ObjectToJson(Object json) {
		if(json==null){
			return "";
		}
		String jsonText = JSON.toJSONString(json);
		return jsonText;
	}

}
