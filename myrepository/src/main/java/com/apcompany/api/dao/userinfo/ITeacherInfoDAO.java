package com.apcompany.api.dao.userinfo;

import com.apcompany.api.model.schema.teacher.TeacherInfoDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ITeacherInfoDAO {

	@Select("SELECT * FROM teacher_base_info "
			+ " WHERE id=#{id}")
	TeacherInfoDO getById(
			@Param("id") int id);



}
