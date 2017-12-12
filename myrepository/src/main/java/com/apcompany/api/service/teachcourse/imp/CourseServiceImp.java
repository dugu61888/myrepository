package com.apcompany.api.service.teachcourse.imp;

import com.apcompany.api.dao.teachcourse.ICourseDAO;
import com.apcompany.api.model.schema.teachcourse.CourseDO;
import com.apcompany.api.service.teachcourse.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImp implements ICourseService {

    @Autowired
    private ICourseDAO courseDAO;

    @Override
    public boolean createCourse(String courseName) {
        courseDAO.addCourse(courseName);
        return true;
    }

    @Override
    public List<CourseDO> getAllList() {
        return courseDAO.getCourseList();
    }

    @Override
    public CourseDO getById(int id) {
        return courseDAO.getById(id);
    }
}
