package com.apcompany.api.controller.teachcourse;

import com.apcompany.api.service.teachcourse.ICourseService;
import com.apcompany.api.service.teachcourse.ITeachCourseService;
import com.apcompany.user.utils.TipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachcourse/admin")
public class AdminTeachCourseController {

    @Autowired
    private ITeachCourseService tService;
    @Autowired private ICourseService courseService;

    @RequestMapping(value="/course/create.do",method = RequestMethod.GET)
    @ResponseBody
    public Object createCourses(
            @RequestAttribute(value= "adminId",required=true)  int adminId,
            @RequestParam(value="courseName",required=true) String courseName
    ){
        return TipUtil.success(courseService.createCourse(courseName));
    }

    @RequestMapping(value="/pass.do",method = RequestMethod.GET)
    @ResponseBody
    public Object passTeachCourse(
            @RequestAttribute(value= "adminId",required=true)  int teacherId,
            @RequestParam(value="teachCourseId",required=true) int teachCourseId
    ){
        return null;//TipUtil.success(tService.createTeachCourse(teacherId,courseId,moneyPerMinu));
    }
}
