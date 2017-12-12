package com.apcompany.api.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apcompany.api.constrant.UserStatusEnum;
import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.pojo.Teacher;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.service.TeacherService;
import com.apcompany.user.utils.MD5Util;
import com.apcompany.user.utils.SendMessage;
import com.apcompany.user.utils.StringUtil;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/teacher")
public class TeacherLoginController {

	private static final String VALIDATE_PHONE_CODE = "VALIDATE_PHONE_CODE";
	private static final String VALIDATE_PHONE = "VALIDATE_PHONE";
	private static final String SEND_CODE_TIME = "SEND_CODE_TIME";
	
	@Autowired
	private TeacherService teacherService;
	@Autowired private IUserOnlineInfoService infoService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(@RequestParam("phone") String phone,@RequestParam("password") String password,
			@RequestParam("code") String code,HttpServletRequest request){
		
		HttpSession session = request.getSession(); 
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
		
        if(validCode==null||validphone==null){
        	return TipUtil.failed("手机号或者验证码为空");
        }
        
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)){
        	Teacher teacher=new Teacher();
        	teacher.setPhone(phone);
        	teacher.setPassword(MD5Util.getStringMD5String(password));
        	
        	boolean isSucessful = teacherService.register(teacher);
        	if(isSucessful){
        		return TipUtil.success("注册成功",teacher);
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
	
	@RequestMapping(value = "/login/nomal", method = RequestMethod.GET)
	@ResponseBody
	public Object login(
			@RequestParam(value="loginname",required=false)  String loginname, 
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
		Teacher teacher = new Teacher();
		teacher.setLoginname(loginname);
		if (!StringUtil.isEmpty(phone)) {
			teacher.setPhone(phone);
		}
		teacher.setPassword(MD5Util.getStringMD5String(password));
		teacher = teacherService.login(teacher);
		if (teacher != null) {
			return TipUtil.success("sucessful login", createToken(teacher.getId(), lat, lng));
		} else {
			return TipUtil.failed("password does not match loginname");
		}

	}

	@RequestMapping(value = "/login/phone", method = RequestMethod.POST)
	@ResponseBody
	public Object loginByPhone(
			@RequestParam("phone") String phone,
			@RequestParam("code") String code,
			@RequestParam("lat") double lat,
			@RequestParam("lng") double lng,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession(); 		
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)){
        	Teacher teacher = teacherService.loginByPhone(phone);
        	return TipUtil.success("sucessful login",createToken(teacher.getId(), lat, lng));	
        }
		return TipUtil.failed("code do not match phone");

	}
	
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object updateInfo(Teacher teacher) {
		if (teacherService.updateTeacher(teacher)) {
			return TipUtil.success("sucessful login", teacher);
		} else {
			return TipUtil.failed("update failed!!!");
		}
	}
	
	private String createToken(int teacherId,double lat,double lng){
		return infoService.addWithLogin(teacherId, UserTypeEnum.Teacher,UserStatusEnum.ONLINE, lat, lng);
	}	
	
}
