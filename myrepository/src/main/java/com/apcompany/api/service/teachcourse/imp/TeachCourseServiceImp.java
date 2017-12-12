package com.apcompany.api.service.teachcourse.imp;

import com.apcompany.api.dao.ITeachCourseDao;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.vo.teachcourse.TeachCourseDetailVO;
import com.apcompany.api.model.vo.teachcourse.TeachCourseVO;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.service.teachcourse.ICourseService;
import com.apcompany.api.service.teachcourse.ITeachCourseService;
import com.apcompany.api.service.userinfo.ITeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TeachCourseServiceImp implements ITeachCourseService {
	
	@Autowired private IUserOnlineInfoService userOnlineInfoService;
	@Autowired private ITeachCourseDao teachCourseDao;
	@Autowired private IBookTeachService bookingTeachService;
    @Autowired private ICourseService courseService;
    @Autowired private ITeacherInfoService teacherInfoService;

	@Override
	public TeachCourseVO getById(int id) {
		TeachCourseDO teachCourseDO =  teachCourseDao.getTCById(id);
		if (null == teachCourseDO){
			return null;
		}
		return new TeachCourseDetailVO()
				.of(teacherInfoService.getById(teachCourseDO.getTeacherId())).of(teachCourseDO).of(teachCourseDO)
				.of(courseService.getById(teachCourseDO.getCourseId()));
	}

	@Transactional
	@Override
	public boolean createTeachCourse(int teacherId, int courseId,
			int moneyPerMinu) {

		//init one year book day data
		return false;//bookingTeachService.initOneYearBookDayList(teachCourseDO.getId());
	}

	@Override
	public void updateScoreOfTeachCourse(int teachCourseID, float teachScore) {
		TeachCourseDO teacherCourseDO = teachCourseDao.getTCById(teachCourseID);
		if( null == teacherCourseDO){
			return;
		}else{
			float oldAvgScore = teacherCourseDO.getTeachScore();
			int oldTeachNum = teacherCourseDO.getTeachStudentNum();
			float avgScore = (oldAvgScore*oldTeachNum + teachScore)/(oldTeachNum+1);
			teacherCourseDO.setTeachScore(avgScore);
			teacherCourseDO.setTeachStudentNum(oldTeachNum+1);
			teachCourseDao.updateScoreOfTeachCourse(teacherCourseDO);
		}
		
	}		
	

	@Override
	public Integer getTeacherIdById(int teachCourseId) {
		return teachCourseDao.getTeacherIdById(teachCourseId);
	}

	@Override
	public TeachCourseDO getTCById(int id) {
		return  teachCourseDao.getTCById(id);
	}

	@Override
	public List<Integer> getTCIdByTeacherId(Integer teacherId) {		
		return teachCourseDao.getTCIdByTeacherId(teacherId);
	}


	

}
