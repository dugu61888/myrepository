package com.apcompany.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.apcompany.api.constrant.UserStatusEnum;
import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.pojo.Student;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.service.StudentLoginService;
import com.apcompany.user.utils.MD5Util;
import com.apcompany.user.utils.SendMessage;
import com.apcompany.user.utils.StringUtil;
import com.apcompany.user.utils.TipUtil;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/student")
public class StudentLoginController {

	private static final String VALIDATE_PHONE_CODE = "VALIDATE_PHONE_CODE";
	private static final String VALIDATE_PHONE = "VALIDATE_PHONE";
	private static final String SEND_CODE_TIME = "SEND_CODE_TIME";

	@Autowired
	private StudentLoginService studentLoginService;
	
	@Autowired private IUserOnlineInfoService infoService;
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(@RequestParam("phone") String phone,
			@RequestParam("password") String password,
			@RequestParam("code") String code,
			@RequestParam("lat") double lat,
			@RequestParam("lng") double lng,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession(); 
		
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
		
        if(validCode==null||validphone==null){
        	return TipUtil.failed("手机号或者验证码为空");
        }
        
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)){
        	Student student=new Student();
        	student.setPhone(phone);
        	student.setPassword(MD5Util.getStringMD5String(password));
        	boolean isSucessful = studentLoginService.register(student);
        	if(isSucessful){
        		Map<String,Object> map=new HashMap<String,Object>();
        		map.put("studentId", student);
        		map.put("token", createToken(student.getId(), lat, lng));
        		return TipUtil.success("注册成功",map);
        	}	
        }
		return TipUtil.failed("注册失败");

	}
	
	@RequestMapping(value ="/login/sendCode",method = RequestMethod.POST)  
    @ResponseBody  
    public Object sendCode(@RequestParam("phone") String phone, HttpServletRequest request) throws HttpException, IOException {  
		String code=String .valueOf((int)((Math.random()*9+1)*100000));
		HttpSession session = request.getSession(); 
		session.setAttribute(VALIDATE_PHONE, phone);
	    session.setAttribute(VALIDATE_PHONE_CODE, code.toString());
	    session.setAttribute(SEND_CODE_TIME, new Date().getTime());
		return SendMessage.sentMessage(phone, code);	
    } 

	@RequestMapping(value = "/login/nomal", method = RequestMethod.POST)
	@ResponseBody
	public Object login(
			@RequestParam(value="loginname",required=false)String loginname, 
			@RequestParam(value="phone",required=false) String phone,
			@RequestParam("password") String password,
			@RequestParam("lat") double lat,
			@RequestParam("lng") double lng
			) {

		if (StringUtil.isEmpty(loginname) && StringUtil.isEmpty(phone)) {
			return TipUtil.failed("loginname and phone is empty, at least one is not empty");
		}

		if (StringUtil.isEmpty(password)) {
			return TipUtil.failed("password is empth!!");
		}
		Student student = new Student();
		student.setLoginname(loginname);
		if (!StringUtil.isEmpty(phone)) {
			student.setPhone(phone);
		}
		student.setPassword(MD5Util.getStringMD5String(password));
		student = studentLoginService.login(student);
		if (student != null) {
			return TipUtil.success("sucessful login", createToken(student.getId(), lat, lng));
		} else {
			return TipUtil.failed("password does not match loginname");
		}

	}

	@RequestMapping(value = "/login/phone", method = RequestMethod.POST)
	@ResponseBody
	public Object loginByPhone(@RequestParam("phone") String phone,
			@RequestParam("code") String code,
			@RequestParam("lat") double lat,
			@RequestParam("lng") double lng,
			HttpServletRequest request) {
		HttpSession session = request.getSession(); 		
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)){
        	Student student = studentLoginService.loginByPhone(phone);
        	return TipUtil.success("sucessful login",createToken(student.getId(), lat, lng));	
        }
		return TipUtil.failed("code do not match phone");
	}

	@RequestMapping(value = "/login/wechat", method = RequestMethod.POST)
	@ResponseBody
	public Object loginByWechat(@RequestParam("code") String code,
			@RequestParam("lat") double lat,
			@RequestParam("lng") double lng) {

		if (StringUtil.isEmpty(code)) {
			return TipUtil.failed("openid is empty");
		}

		JSONObject jsonObject=getAccessToken(code);
		
		String openID = jsonObject.getString("openid");
		
		if(openID==null||"".equals(openID)){
			return TipUtil.failed("wechat failed!!!");
		}
		Student student=studentLoginService.loginByWechat(openID);
		if (student!=null&&student.getPhone()!=null&&!"".equals(student.getPhone())) {
			Map<String,String> map=new HashMap<String,String>();
			map.put("studentId", String.valueOf(student.getId()));
			map.put("token", createToken(student.getId(), lat, lng));
			return TipUtil.success("login",map);
		} else if(student==null) {
			student = new Student();
			student.setOpenid(openID);
			studentLoginService.register(student);
		}
		Map<String,String> map=new HashMap<String,String>();
		map.put("studentId", String.valueOf(student.getId()));
		map.put("token", createToken(student.getId(), lat, lng));
		return TipUtil.success("phone",map);
	}
		
	
	@RequestMapping(value = "/register/wechat", method = RequestMethod.POST)
	@ResponseBody
	public Object registerByWechat(@RequestParam("phone") String phone,@RequestParam("code") String code,
			@RequestParam("openid") String openid,@RequestParam("lat") double lat,
			@RequestParam("lng") double lng,HttpServletRequest request) {
		HttpSession session = request.getSession(); 		
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)&&!"".equals(openid)){
        		Student student=new Student();
        		student.setPhone(phone);
        		student.setOpenid(openid);
        		boolean isSucessful = studentLoginService.register(student);
        		if(isSucessful){
        			Map<String,String> map=new HashMap<String,String>();
        			map.put("studentId", String.valueOf(student.getId()));
        			map.put("token", createToken(student.getId(), lat, lng));
        			return TipUtil.success("register successful",map);
        		}else{
        			return TipUtil.failed("register failed!");
        		}
        		
        }
		return TipUtil.failed("code do not match phone");

	}
	

	@RequestMapping(value = "/login/updateMessage", method = RequestMethod.POST)
	@ResponseBody
	public Object update(Student student) {
		if (studentLoginService.updateStudent(student)) {
			return TipUtil.success("sucessful login", student);
		} else {
			return TipUtil.failed("update failed!!!");
		}
	}

	@RequestMapping(value = "/register/validname/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object validName(@PathVariable String name) {
		boolean out = studentLoginService.nameIsUsed(name);
		if (out) {
			return TipUtil.success(true);
		} else {
			return TipUtil.success(false);
		}
	}

	@RequestMapping(value = "/register/validphone/{phone}", method = RequestMethod.GET)
	@ResponseBody
	public Object validPhone(@PathVariable String phone) {
		boolean out = studentLoginService.phoneIsUsed(phone);
		if (out) {
			return TipUtil.success(true);
		} else {
			return TipUtil.success(false);
		}
	}
	
	/**
	 * 登陆状态修改密码
	 * @param studentId
	 * @param oldPassword
	 * @param newpassword
	 * @return
	 */
	@RequestMapping(value = "/login/resetpassword/{studentId}/{oldPassword}/{newpassword}", method = RequestMethod.GET)
	@ResponseBody
	public Object changeWord(
			@PathVariable("studentId") int studentId,
			@PathVariable("oldPassword") String oldPassword,
			@PathVariable("newpassword") String newpassword){
		if(studentId>0&&oldPassword!=null&&newpassword!=null){
			String outcome=studentLoginService.changePassword(studentId, MD5Util.getStringMD5String(oldPassword), MD5Util.getStringMD5String(newpassword));
			return TipUtil.success(outcome);
		}else{
			return TipUtil.failed("parames is wrong");
		}
	}
	
	@RequestMapping(value="/login/resetphone",method = RequestMethod.GET)
	@ResponseBody
	public Object forgotPassWord(
			@RequestParam("phone") String phone,
			@RequestParam("code") String code,
			@RequestParam("pwd") String newpassword,
			HttpServletRequest request){
		
		HttpSession session = request.getSession(); 	
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
		
        if(validCode==null||validphone==null){
        	return TipUtil.failed("phone or code is empty");
        }
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)){
        	boolean outcome=studentLoginService.changePwdByphone(validphone, MD5Util.getStringMD5String(newpassword));
        	if(outcome){
        		return TipUtil.success("update successful");
        	}else{
        		return TipUtil.success("update error");
        	}
        	
        }
        return TipUtil.failed("phone and code not match");
	}
	
	/**
	 * 绑定手机号
	 * @param studentId
	 * @param phone
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/login/bandphone",method = RequestMethod.GET)
	@ResponseBody
	public Object bandPhone(
			@RequestParam("studentId") int studentId,
			@RequestParam("phone") String phone,
			@RequestParam("code") String code,
			@RequestParam("lat") double lat,
			@RequestParam("lng") double lng,
			HttpServletRequest request){
		HttpSession session = request.getSession(); 		
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
        if(validCode==null||validphone==null){
        	return TipUtil.failed("手机号或者验证码为空");
        }
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)){
        		String outcome=studentLoginService.bandPhone(studentId, phone);
        		if("successful".equals(outcome)){
        			Student student=studentLoginService.loginByPhone(phone);
        			return TipUtil.success(createToken(student.getId(), lat, lng));
        		}
        		return TipUtil.success("resson",outcome);
        }
		return TipUtil.failed("手机号和验证码不匹配");
	}
	
	
	/**
	 * 校验手机号与微信号存在关系
	 * @param phone
	 * @return
	 */
	@RequestMapping(value="/login/validation/wechat/{phone}",method = RequestMethod.GET)
	@ResponseBody
	public Object validPhoneWechat(@PathVariable("phone") String phone){
		
		if(phone==null||phone.equals("")){
			return TipUtil.failed("phone parameter is wrong");
		}
		
		String message=studentLoginService.validWechatPhone(phone);
		
		return TipUtil.success(message);
		
	}
	
	
	
	private  JSONObject getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1cc921938ad2e075"
        		+ "&secret=6940113a75b6b15bd596960e0c6a5dd9&code="+code+"&grant_type=authorization_code";
        URI uri = URI.create(url);
        HttpClient client = new DefaultHttpClient();
        
        HttpGet get = new HttpGet(uri);

        HttpResponse response;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }

//                JSONObject object = new JSONObject(sb.toString().trim());
//                accessToken = object.getString("access_token");
//                openID = object.getString("openid");
//                refreshToken = object.getString("refresh_token");
//                expires_in = object.getLong("expires_in");
                JSONObject object=JSONObject.parseObject(sb.toString().trim());         
                return object;
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
	
	
	
	
	private String createToken(int studentId,double lat,double lng){
		return infoService.addWithLogin(studentId, UserTypeEnum.Student,UserStatusEnum.ONLINE, lat, lng);
	}

}
