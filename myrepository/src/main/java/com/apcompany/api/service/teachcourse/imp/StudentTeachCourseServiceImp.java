package com.apcompany.api.service.teachcourse.imp;

import com.apcompany.api.dao.IStudentSaveDao;
import com.apcompany.api.dao.ITeachCourseDao;
import com.apcompany.api.model.form.OnlineTCForm;
import com.apcompany.api.model.schema.OnlineTCInfoDO;
import com.apcompany.api.model.schema.StudentSaveDO;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.teachcourse.IStudentTeachCourseService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTeachCourseServiceImp implements IStudentTeachCourseService {
	@Autowired private ITeachCourseDao teachCourseDao;
	
	@Autowired private IStudentSaveDao studentSaveDao;
	
	@Autowired private IBookTeachService bookingTeachService;
	
	@Override
	public List<OnlineTCInfoDO> getOnlineTCListByCourse(OnlineTCForm onlineTCForm) {
		List<OnlineTCInfoDO> result = Lists.newArrayList();
		if(onlineTCForm.getCourseId() ==0){
			return result;
		}
		return teachCourseDao.getOnlineListBySubject(onlineTCForm);
	}
	
	@Override
	public boolean saveTeachCourse(int studentId,int teachCoueseId) {		
		StudentSaveDO studentSaveDO = new StudentSaveDO(studentId,teachCoueseId);
		int n= studentSaveDao.saveTeachCourse(studentSaveDO);
		return n>0?true:false;
	}

	@Override
	public List<StudentSaveDO> getSavaTCList(
			int studentId,int index,int pageSize) {
		return studentSaveDao.getStudentSavingList(studentId, index, pageSize);
	}

	@Override
	public boolean deleteById(int id) {
		try {
			studentSaveDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
