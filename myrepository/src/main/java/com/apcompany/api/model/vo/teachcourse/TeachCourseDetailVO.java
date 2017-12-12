package com.apcompany.api.model.vo.teachcourse;


import com.apcompany.api.model.schema.teacher.TeacherInfoDO;
import com.apcompany.api.model.vo.teachcourse.TeachCourseVO;

public class TeachCourseDetailVO extends TeachCourseVO {

	private String teacherName;

	public TeachCourseDetailVO(){}

    public TeachCourseDetailVO of(TeacherInfoDO teacherInfoDO){
		this.setTeacherName(teacherInfoDO.getName());
		return this;
	}



	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
