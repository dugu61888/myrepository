package com.apcompany.api.controller.teachcourse;

import com.apcompany.api.service.teachcourse.ICourseService;
import com.apcompany.api.service.teachcourse.ITeachCourseService;
import com.apcompany.user.utils.TipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/teachcourse/common")
public class TeachCourseController {

	public TeachCourseController(){
		
	}
	
	@Autowired private ITeachCourseService tService;
	@Autowired private ICourseService courseService;


	@RequestMapping(value="/allcourses.json",method = RequestMethod.GET)
	@ResponseBody
	public Object searchAllCourses(
			@RequestAttribute("studentId") int studentId){		
		return TipUtil.success(courseService.getAllList());
	}

	
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object getTCById(
			@PathVariable int id
			){
		return TipUtil.success(tService.getTCById(id));
	}	
	
	
	
	
	

}
