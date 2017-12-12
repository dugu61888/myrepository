package com.apcompany.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apcompany.api.dao.TeacherDao;
import com.apcompany.api.pojo.Teacher;
import com.apcompany.api.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;

	@Override
	public boolean register(Teacher teacher) {
		if (teacher == null) {
			return false;
		}

		if (teacherDao.insert(teacher) == 0) {
			return false;
		}
		return true;
	}

	@Override
	public Teacher login(Teacher teacher) {
		if (teacher == null) {
			return null;
		} else if (!"".equals(teacher.getPhone())) {
			return teacherDao.logininbyPhone(teacher.getPhone(), teacher.getPassword());
		} else {
			return teacherDao.loginin(teacher.getLoginname(), teacher.getPassword());
		}
	}

	@Override
	public boolean nameIsUsed(String loginname) {

		return teacherDao.existName(loginname) > 0;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		if (teacher == null) {
			return false;
		}
		return teacherDao.update(teacher) > 0;
	}

	

	@Override
	public Teacher loginByPhone(String phone) {
		return teacherDao.loginByPhone(phone);
	}

	@Override
	public Teacher loginByWechat(String openid) {
		return teacherDao.loginByWechat(openid);
	}

	@Override
	public boolean phoneIsUsed(String phone) {

		return teacherDao.existPhone(phone) > 0;
	}

	@Override
	public boolean wechatIsUsed(String openid) {

		return teacherDao.existWechat(openid) > 0;
	}

	@Override
	public int getIdbyname(String loginname) {

		return teacherDao.getIdbyname(loginname);
	}

}
