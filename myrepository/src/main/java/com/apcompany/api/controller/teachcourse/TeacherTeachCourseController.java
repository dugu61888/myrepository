package com.apcompany.api.controller.teachcourse;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.service.teachcourse.ITeacherTeachCourseService;
import com.apcompany.user.utils.TipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachcourse/teacher")
public class TeacherTeachCourseController {
	
	@Autowired private ITeacherTeachCourseService teacherTeachCourseService;

	@RequestMapping(value="/apply.do",method = RequestMethod.POST)
	@ResponseBody
	public Object closeToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestParam("courseId") int teachCourseId,
			@RequestParam("moneyPerMinute") int moneyPerMinute){
		return teacherTeachCourseService.applyTeachCourse(teacherId, teachCourseId,moneyPerMinute);
	}
	
	@RequestMapping(value="/all.json",method = RequestMethod.GET)
	@ResponseBody
	public Object getTCListByTeacher(
			@RequestAttribute("teacherId") int teacherId){
	    return teacherTeachCourseService.getMyTeachCourseList(teacherId);
	}


	
	@RequestMapping(value="/prepared.do",method = RequestMethod.GET)
	@ResponseBody
	public Object preparedToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestParam("teachCourseId") int teachCourseId){
		return teacherTeachCourseService.updateStatus(teacherId, teachCourseId, TeachCourseStatusEnum.NORMAL);
	}
	
	@RequestMapping(value="/close.do",method = RequestMethod.GET)
	@ResponseBody
	public Object closeToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestParam("teachCourseId") int teachCourseId){
		return  teacherTeachCourseService.updateStatus(teacherId, teachCourseId,TeachCourseStatusEnum.CLOSE);
	}

	@RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object deleteTeachCourse(
			@RequestAttribute("teacherId") int teacherId,
			@PathVariable("id") int id){
		return  teacherTeachCourseService.deleteTeachCourse(id, teacherId);
	}

}
