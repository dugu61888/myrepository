package com.apcompany.api.controller.teachcourse;

import com.apcompany.api.model.form.OnlineTCForm;
import com.apcompany.api.model.schema.OnlineTCInfoDO;
import com.apcompany.api.service.teachcourse.IStudentTeachCourseService;
import com.apcompany.user.utils.TipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachcourse/student")
public class StudentTeachCourseController {
	
	@Autowired private IStudentTeachCourseService sTcService;
	
	@RequestMapping(value="/search/list.json",method = RequestMethod.GET)
	@ResponseBody
	public Object searchOnlineTeachers(
			@RequestAttribute("studentId") int studentId,
			@RequestParam(value="courseId",required=true) int courseId,
			@RequestParam(value="orderType",required=false,defaultValue="2") int orderType,
			@RequestParam(value="index",required=false,defaultValue="0") int index,
			@RequestParam(value="limit",required=false,defaultValue="10") int limit){
		List<OnlineTCInfoDO> list = sTcService.getOnlineTCListByCourse(
				new OnlineTCForm(studentId,courseId,orderType,index,limit));
	    return TipUtil.success(list);
	}
	
	@RequestMapping(value="/save/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object saveTeachCourse(
			@RequestAttribute(value="studentId",required=true)  Integer studentId,
			@PathVariable(value="id",required=true) int id){		
		sTcService.saveTeachCourse(studentId, id);
		return TipUtil.success("success");
	}
	
	@RequestMapping(value="/save/delete/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object deleteSaveTeachCourseById(
			@RequestAttribute(value= "studentId",required=true)  int studentId,
			@PathVariable int id
			){
		return TipUtil.success(sTcService.deleteById(id));
	}
	
	@RequestMapping(value="/save/list.json",method = RequestMethod.GET)
	@ResponseBody
	public Object saveList(
			@RequestAttribute(value= "studentId",required=true)  int studentId,
			@RequestParam(value="index",required=false,defaultValue="0") int index,
			@RequestParam(value="limit",required=false,defaultValue="10") int limit){
		return sTcService.getSavaTCList(studentId, index, limit);
	}
	
	

}
