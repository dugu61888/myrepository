package com.apcompany.api.service.teachcourse;

import com.apcompany.api.model.form.OnlineTCForm;
import com.apcompany.api.model.schema.OnlineTCInfoDO;
import com.apcompany.api.model.schema.StudentSaveDO;

import java.util.List;

public interface IStudentTeachCourseService {

	List<OnlineTCInfoDO> getOnlineTCListByCourse(OnlineTCForm onlineTCForm);

	boolean saveTeachCourse(int studentId, int teachCoueseId);

	List<StudentSaveDO> getSavaTCList(int studentId, int index,
                                      int pageSize);

	boolean deleteById(int id);


}
