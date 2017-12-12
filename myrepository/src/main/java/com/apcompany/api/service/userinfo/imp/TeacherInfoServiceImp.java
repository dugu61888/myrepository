package com.apcompany.api.service.userinfo.imp;

import com.apcompany.api.dao.userinfo.ITeacherInfoDAO;
import com.apcompany.api.model.schema.teacher.TeacherInfoDO;
import com.apcompany.api.service.userinfo.ITeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherInfoServiceImp implements ITeacherInfoService {

    @Autowired private ITeacherInfoDAO teacherInfoDAO;
    @Override
    public TeacherInfoDO getById(int id) {
        return teacherInfoDAO.getById(id);
    }
}
