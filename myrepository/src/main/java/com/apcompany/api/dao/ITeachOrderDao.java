package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.apcompany.api.model.schema.TeachOrderDO;


public interface ITeachOrderDao {
	
	@Select("select * from video_order where id=#{id}")
	TeachOrderDO getOrderById(@Param("id") int id);
	
	@Update("update video_order set teacher_customer_score =#{teacherCustomerScore}, "
			+ "teacher_manner_score=#{teacherMannerScore},"
			+ "teacher_skill_score=#{teacherSkillScore},modified= now() where id=#{id} ")
	void markOrder(TeachOrderDO order);
	
	@Insert("insert into video_order(student_id,teacher_id,teach_course_id,begin_time,"
			+ "end_time,use_minute,coin,status,teacher_customer_score,"
			+ "teacher_manner_score,teacher_skill_score,created,modified) "
			+ "values(#{studentId},#{teacherId},#{teachCourseId},#{beginTime},#{endTime},"
			+ "#{useMinute},#{coin},#{status},#{teacherCustomerScore},"
			+ "#{teacherMannerScore},#{teacherSkillScore},now(),now() )")
	void add(TeachOrderDO teachOrderDO);
	
	@Update("update video_order set end_time=now(),use_minute=#{useMinute},"
			+ "money=#{money},status=#{status},modifed=now() where id=#{id}")
	public void commitOrder(TeachOrderDO teachOrderDO);

	@Select("select * from video_order where student_id=#{studentId} and teach_course_id=#{teachCourseId} and status=0 limit 1")
	public TeachOrderDO getOrderByStudentCourse(int studentId, int teachCourseId);
	
	@Update("update video_order set status=#{status} where id=#{id}")
	public void updateOrderStatus(TeachOrderDO teachOrderDO);
	
	@Select("select * from video_order where teach_course_id=#{teachCourseId} and type=0 and status=0  limit 1")
	public TeachOrderDO getHandleInviteOrderByTeachCourse(int teachCourseId);
	
	@Select("select * from video_order where student_id=#{studentId} and status=0  limit 1")
	public TeachOrderDO getHandleInviteOrderByStudent(int studentId);
	
	@Select("select * from video_order where type=1 and src_id=#{bookId}")
	public TeachOrderDO getByBookId(int bookId);

}
