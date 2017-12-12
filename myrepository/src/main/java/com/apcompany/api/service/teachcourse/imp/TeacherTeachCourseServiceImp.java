package com.apcompany.api.service.teachcourse.imp;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.dao.ITeachCourseDao;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.schema.teachcourse.CourseDO;
import com.apcompany.api.model.vo.ApiResponse;
import com.apcompany.api.model.vo.teachcourse.TeachCourseVO;
import com.apcompany.api.service.teachcourse.ICourseService;
import com.apcompany.api.service.teachcourse.ITeacherTeachCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherTeachCourseServiceImp implements ITeacherTeachCourseService {

	Logger logger = LoggerFactory.getLogger(TeacherTeachCourseServiceImp.class);
	
	@Autowired private ITeachCourseDao teachCourseDao;
	@Autowired private ICourseService courseService;


	@Override
	public ApiResponse applyTeachCourse(int teacherId, int courseId,int moneyPerMinute) {
		try{
			CourseDO courseDO = courseService.getById(courseId);
			if(courseDO == null){
				return ApiResponse.buildFailure("course id not exists");
			}
			return ApiResponse.buildSuccess(teachCourseDao.addTeachCourse(new TeachCourseDO().apply(teacherId,courseId,moneyPerMinute)));
		}catch (Exception e){
			logger.error("applyTeachCourse",e);
			return ApiResponse.buildFailure("内部服务器错误");
		}
	}

	@Override
	public ApiResponse getMyTeachCourseList(int teacherId) {
		try{
			List<TeachCourseDO> teachCourseDOList =  teachCourseDao.getTCListByTId(teacherId);
			return ApiResponse.buildSuccess(teachCourseDOList.stream().map(teachCourseDO -> new TeachCourseVO()
					.of(teachCourseDO)
					.of(courseService.getById(teachCourseDO.getCourseId()))).collect(Collectors.toList()));
		}catch (Exception e){
			logger.error("getMyTeachCourseList",e);
			return ApiResponse.buildFailure("内部服务器错误");
		}
	}


	@Override
	public ApiResponse updateStatus(int teacherId, int teachCourseId, TeachCourseStatusEnum status) {
		try{
			int id = teachCourseDao.checkTCIsValid(teachCourseId,teacherId);
			if (id == 0 ) {
				logger.error("check tc is valid tid{},tcid{}",teacherId,teachCourseId);
				return ApiResponse.buildFailure("check tc is not valid,because of this teachCourse is not verify");
			}
			return ApiResponse.buildSuccess(teachCourseDao.updateTCStatus(teachCourseId, teacherId, status.getKey()));
		}catch (Exception e){
			logger.error("updateStatus",e);
			return ApiResponse.buildFailure("内部服务器错误");
		}
	}

	@Override
	public ApiResponse deleteTeachCourse(int id, int teacherId) {
		try{
			return ApiResponse.buildSuccess(teachCourseDao.deleteTCByTeacher(id,teacherId));
		}catch (Exception e){
			logger.error("deleteTeachCourse",e);
			return ApiResponse.buildFailure("内部服务器错误");
		}

	}

}
