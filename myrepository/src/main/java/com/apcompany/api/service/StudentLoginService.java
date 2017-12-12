package com.apcompany.api.service;

import com.apcompany.api.pojo.Student;
import com.hazelcast.com.eclipsesource.json.JsonObject;

public interface StudentLoginService {

	public boolean register(Student student);
	
	public Student login(Student student);
	
	//查看当前用户名是否可以使用
	public boolean nameIsUsed(String loginname);
	
	public boolean updateStudent(Student student);
	
	public boolean delStudent(int id);
	
	public Student loginByPhone(String phone);
	
	public Student loginByWechat(String openid);
	
	public boolean phoneIsUsed(String phone);
	
	public boolean wechatIsUsed(String openid);
	
	public String changePassword(Integer id ,String password,String newpassword);
	
	public String  bandPhone(Integer id,String phone);
	
    public String  validWechatPhone(String phone);
    
    public boolean changePwdByphone(String phone,String password);
	
}
