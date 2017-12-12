package com.apcompany.user.utils;

import java.util.HashMap;
import java.util.Map;


public class TipUtil {
	//成功提示
	private static final boolean OK = true;
	//失败提示
	private static final boolean NO = false;
	
	/**
	 * 成功调用接口
	 * @param message -- 返回的提醒信息
	 * @return -- 返回结果
	 */
	public static Map<String,Object> success(String message){
		return success(message,null);
	}
	/**
	 * 成功调用接口
	 * @param data -- 需要带回的数据
	 * @return -- 返回结果
	 */
	public static Map<String,Object> success(Object data){
		return success("操作成功!",data);
	}
	/**
	 * 成功调用接口
	 * @param message -- 成功后提醒信息
	 * @param data -- 需要带回的数据
	 * @return -- 返回结果
	 */
	public static Map<String,Object> success(String message,Object data){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", TipUtil.OK);
		result.put("message", message);
		result.put("data", data);
		
		return result;
	}
	/**
	 * 失败调用的接口
	 * @return -- 返回结果
	 */
	public static Map<String,Object> failed(){
		return failed("操作失败!");
	}
	/**
	 * 失败调用接口
	 * @param message -- 返回的失败提醒信息
	 * @return -- 返回的结果
	 */
	public static Map<String,Object> failed(String message){
		return failed(message,message);
	}
	/**
	 * 失败调用的接口
	 * @param message -- 返回的失败提醒信息
	 * @param data -- 失败的具体异常
	 * @return -- 返回结果
	 */
	public static Map<String,Object> failed(String message,String data){
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("success", TipUtil.NO);
		result.put("message", message);
		result.put("data", data);
		
		return result;
	}
	
}
