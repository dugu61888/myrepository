package com.apcompany.api.service.teachcourse;

import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.vo.teachcourse.TeachCourseVO;
import java.util.List;

public interface ITeachCourseService {

	TeachCourseVO getById(int id);

	boolean createTeachCourse(int teacherId, int courseId, int moneyPerMinu);
	//******************admin end**************** 


		
	
	//******************common start**************** 

	TeachCourseDO getTCById(int id);
	List<Integer> getTCIdByTeacherId(Integer teacherId);
	void updateScoreOfTeachCourse(int teachCourseID, float teachScore);
	Integer getTeacherIdById(int teachCourseId);
	
	//******************common end****************   

	

}
