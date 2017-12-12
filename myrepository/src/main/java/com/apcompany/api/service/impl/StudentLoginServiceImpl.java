package com.apcompany.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.dao.StudentDao;
import com.apcompany.api.pojo.Student;
import com.apcompany.api.service.StudentLoginService;

@Service
public class StudentLoginServiceImpl implements StudentLoginService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public boolean register(Student student) {

		if (student == null) {
			return false;
		} else {
			return studentDao.insert(student) > 0 ? true : false;
		}
	}

	@Override
	public Student login(Student student) {
		if (student == null) {
			return null;
		} else if (!"".equals(student.getPhone())) {
			return studentDao.logininbyPhone(student.getPhone(), student.getPassword());
		} else {
			return studentDao.loginin(student.getLoginname(), student.getPassword());
		}

	}

	@Override
	public boolean nameIsUsed(String loginname) {

		return studentDao.existName(loginname) > 0 ? true : false;
	}

	@Override
	public boolean updateStudent(Student student) {

		return studentDao.update(student) > 0 ? true : false;

	}

	@Override
	public boolean delStudent(int id) {

		return studentDao.delete(id) > 0 ? true : false;
	}

	@Override
	public Student loginByPhone(String phone) {

		return studentDao.loginByPhone(phone);
	}

	@Override
	public Student loginByWechat(String openid) {
		return studentDao.loginByWechat(openid);
	}

	@Override
	public boolean phoneIsUsed(String phone) {

		return studentDao.existPhone(phone) > 0 ? true : false;
	}

	@Override
	public boolean wechatIsUsed(String openid) {
		
		return studentDao.existWechat(openid) > 0;
		
	}

	@Override
	public String changePassword(Integer id, String password, String newpassword) {
		
		int exits=studentDao.existpassword(id, password);
		if(exits>0){
			studentDao.updatePassword(id, newpassword);
			return "successfull!";
		}else{
			return "old password is wrong!";
		}

	}

	@Override
	public String bandPhone(Integer id, String phone) {
		
		int exits=studentDao.updateBandPhone(id, phone);
		if(exits>0){
			return "successful";
		}
		return "failed";
	}

	@Override
	public String validWechatPhone(String phone) {
		
		Student student=studentDao.loginByPhone(phone);
		
		if(student==null){
			return "phone not exist";
		}
		if(student.getOpenid()!=null&&!"".equals(student.getOpenid())){
			return "phone has openId";
		}else{
			return "phone regist,not openId";
		}
	}

	@Override
	public boolean changePwdByphone(String phone, String password) {
		int outcome=studentDao.updatePasswordByphone(phone, password);
		return outcome>0;
		
	}

}
