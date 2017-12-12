package com.apcompany.api.service.teachcourse;

import com.apcompany.api.model.schema.teachcourse.CourseDO;
import com.apcompany.api.service.IBaseService;

import java.util.List;

public interface ICourseService extends IBaseService<CourseDO>{

    boolean createCourse(String courseName);

    List<CourseDO> getAllList();
}
