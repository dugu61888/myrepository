package com.apcompany.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apcompany.api.model.schema.OnlineTCInfoDO;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/order/teacher")
public class TeacherTeachOrderController {
	@Autowired
	private ITeachOrderService teachOrderService;
	
	@RequestMapping(value="/teacher/prepared.do",method = RequestMethod.GET)
	@ResponseBody
	public Object preparedToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestParam(value="courseId",required=true) int courseId,
			@RequestParam(value="orderType",required=false,defaultValue="2") int orderType,
			@RequestParam(value="index",required=false,defaultValue="0") int index,
			@RequestParam(value="limit",required=false,defaultValue="10") int limit){
		List<OnlineTCInfoDO> list = null;
	    return TipUtil.success(list);
	}


}
