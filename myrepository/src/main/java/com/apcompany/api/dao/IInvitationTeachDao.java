package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import com.apcompany.api.model.schema.InvitationTeachDO;

public interface IInvitationTeachDao {
	
	@Select("select * from invitation_teach where student_id=#{studentId} and status in (1,3) limit 1")
	InvitationTeachDO getHandleInvitationByStudent(int studentId);
	
	@Insert("INSERT INTO "
			+ " invitation_teach( "
			+ "   student_id,"
			+ "   teacher_id,"
			+ "   teach_course_id,"
			+ "   status,"
			+ "   begin_time,"
			+ "   end_time,"
			+ "   created,"
			+ "   modified"
			+ " ) "
			+ " values( "
			+ "   #{studentId},"
			+ "   #{teacherId},"
			+ "   #{teachCourseId},"
			+ "   #{status},"
			+ "   #{beginTime},"
			+ "   #{endTime},"
			+ "   now(),"
			+ "   now()) "
			+ " ON DUPLICATE KEY UPDATE " +
			"   status = #{status}," +
			"   begin_time = #{beginTime}," +
			"   end_time = #{endTime}," +
			"   created = NOW()," +
			"   modified = NOW() ")
	void add(InvitationTeachDO invitationTeachDO);
	
	@Update("update invitation_teach "
			+ " set begin_time=#{beginTime} , end_time=#{endTime} "
			+ " , status=#{status} , modified= now() where id=#{id}")
	void update(InvitationTeachDO invitationTeachDO);
	
	@Select("<script>"
			+ " SELECT * FROM invitation_teach "
			+ " WHERE teacher_id=#{teacherId} "
			+ " and status in (1,3) limit 1"
			+ "</script>")
	InvitationTeachDO getHandleInvitationByTeacher(
			@Param("teacherId") Integer teacherId);
	

}
