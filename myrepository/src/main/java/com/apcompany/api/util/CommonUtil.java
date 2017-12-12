package com.apcompany.api.util;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.pojo.TokenModel;
import com.apcompany.user.utils.TipUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	
	public static Integer parseToInteger(String number){
		try {
			if(number==null){
				return null;
			}
			return Integer.valueOf(number);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String createToken(int userId,UserTypeEnum userType){
		StringBuffer buff =new StringBuffer();
		buff.append(UUID.randomUUID().toString().replaceAll("-", ""));
		buff.append("_u");
		buff.append(userId);
		buff.append("_t");
		buff.append(userType.getKey());
		return buff.toString();
	}
	
	/*
	 * if invalid return null
	 */
	public static TokenModel validToken(String token){
		if(token==null||token.length()<38){
			return null;			
		}
		token= token.replace(" ", "");
		TokenModel model = getTokenModelFromToken(token);
		return model;
	}
	
	public static TokenModel getTokenModelFromToken(String token){		
		if(token==null){
			return null;
		}
		int idIndex=token.lastIndexOf("_u")+2;
		int idEnd= token.lastIndexOf("_t");
		if(idIndex>=token.length()||idIndex>idEnd||idEnd+2>=token.length()){
			return null;
		}		
		String userIds= token.substring(idIndex, idEnd);
		String userType = token.substring(idEnd+2);
		try {
			int id = Integer.valueOf(userIds);
			UserTypeEnum type= UserTypeEnum.valueOf(Integer.valueOf(userType));
			return new TokenModel(token,id, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setResponseData(HttpServletResponse response,String message){
		try {
			if(response==null){
				return ;
			}
			Map map =TipUtil.failed(message);
			response.getWriter().write(new ObjectMapper().writeValueAsString(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(toJson ("xxx"));
	}
	
	public static String toJson(Object obj){
		ObjectMapper mapper=  new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "null";
		}
	}

	

}
