package com.apcompany.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.apcompany.api.model.schema.StudentSaveDO;


public interface IStudentSaveDao {
	
	@Select("select * from student_save_teach_course where student_id=#{studentId} limit #{index},#{pageSize}")
	List<StudentSaveDO> getStudentSavingList(@Param("studentId") int studentId,@Param("index") int index,@Param("pageSize") int pageSize);
	
	@Insert("insert ignore into student_save_teach_course("
			+ "student_id,teach_course_id,save_time) "
			+ " values(#{studentId},#{teachCourseId},NOW())")
	int saveTeachCourse(StudentSaveDO studentSaveDO);
	
	
	@Delete("delete from student_save_teach_course where id=#{id}")
	void deleteById(int id);

}
