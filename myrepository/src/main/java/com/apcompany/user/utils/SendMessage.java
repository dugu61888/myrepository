package com.apcompany.user.utils;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.hazelcast.com.eclipsesource.json.JsonObject;

public class SendMessage {
	/** 用户名常量 */
	public static final String UID = "dugu61888";
	/** 用户密码常量 */
	public static final String PWD = "wp19870618";

	public static final String URL = "http://api.sms.cn/sms/";

	public static HttpClient client = new HttpClient();
	

	public static String sentMessage(String phone,String code,String templateId) throws HttpException, IOException {
		PostMethod post = new PostMethod(URL);
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		JsonObject json = new JsonObject();
		json.add("code", code);
		NameValuePair[] data = { new NameValuePair("ac", "send"), new NameValuePair("uid", UID),
				new NameValuePair("pwd", MD5Util.getStringMD5String(PWD + UID)),
				new NameValuePair("mobile", phone),
				new NameValuePair("encode","utf8"),
				new NameValuePair("content", json.toString()), new NameValuePair("template", templateId) };
		post.setRequestBody(data);
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
		post.releaseConnection();
		return result;		
	}
	
	public static String sentMessage(String phone,String code) throws HttpException, IOException{
		return sentMessage(phone,code,"411481");
	}

	public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://api.sms.cn/sms/");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		JsonObject json = new JsonObject();
		json.add("code", "888888");
		NameValuePair[] data = { new NameValuePair("ac", "send"), new NameValuePair("uid", "dugu61888"),
				new NameValuePair("pwd", MD5Util.getStringMD5String(PWD + UID)),
				new NameValuePair("mobile", "18901051605"),
				new NameValuePair("encode","utf8"),
				new NameValuePair("content", json.toString()), new NameValuePair("template", "411481") };
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		System.out.println(post.getRequestCharSet());
		
		String result = new String(post.getResponseBodyAsString());
		System.out.println(new String(post.getResponseBodyAsString().getBytes(),"utf-8"));
		System.out.println(result);
		post.releaseConnection();
//		PostMethod post = new PostMethod("http://www.baidu.com");
//		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//		client.executeMethod(post);
//		System.out.println(new String(post.getResponseBodyAsString().getBytes("iso-8859-1")));

	}

}
