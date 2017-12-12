package com.apcompany.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.pojo.TokenModel;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.util.CommonUtil;

public class ValidTokenInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger= LoggerFactory.getLogger(ValidTokenInterceptor.class);
	
	@Autowired private IUserOnlineInfoService userinfoInfoService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {			
			TokenModel tokenModel = CommonUtil.validToken(request.getParameter("token"));
		    if(tokenModel==null){
		    	logger.info("token is not valid");
		    	//CommonUtil.setResponseData(response, "token is null or  invalid");
				TokenModel tokenModel2 =new TokenModel("", 8, UserTypeEnum.Student);
				TokenModel tokenModel3 =new TokenModel("", 3, UserTypeEnum.Teacher);
				setDataToRequest(request, tokenModel2);
				setDataToRequest(request, tokenModel3);
		    	return true;
		    }
//			if(! userinfoInfoService.checkAccessToken(tokenModel.getToken())){
//		    	logger.info("user is offline,pleace login or pust the right token");
//		    	CommonUtil.setResponseData(response, "user token is not exits");
//				if
//				switch (userType){
//					case 0:
//					{
//						TokenModel tokenModel2 =new TokenModel("", 8, UserTypeEnum.Student);
//						setDataToRequest(request, tokenModel2);
//						break;
//					}
//					case 1:
//					{
//						TokenModel tokenModel3 =new TokenModel("", 3, UserTypeEnum.Teacher);
//						setDataToRequest(request, tokenModel3);
//						break;
//					}
//					default:break;
//				}
//				return true;
//		    }
			logger.info("check ok and refresh success");
			setDataToRequest(request, tokenModel);
			logger.info("set data ok");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void setDataToRequest(HttpServletRequest request,TokenModel tokenModel){
		if(tokenModel== null||tokenModel.getUserType()==null||tokenModel.getUserId()==null){
			return;
		}
		if(tokenModel.getUserType()==UserTypeEnum.Student){
			request.setAttribute(UserTypeEnum.Student.getValue(), tokenModel.getUserId());
		}else{
			request.setAttribute(UserTypeEnum.Teacher.getValue(), tokenModel.getUserId());
		}
	}
	
	

	
}
