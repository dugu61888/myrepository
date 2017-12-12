package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apcompany.api.model.form.OrderTeacherScoreForm;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/order/student")
public class StudentTeachOrderController {

	@Autowired
	private ITeachOrderService teachOrderService;

	// 为订单评价
	@RequestMapping(value = "/markscore", method = RequestMethod.POST)
	@ResponseBody
	public Object commentTeacher(
			@RequestAttribute(value = "studentId", required = true) Integer studentId,
			OrderTeacherScoreForm form) {
		return TipUtil.success(teachOrderService.markScoreForOrder(studentId,form));
	}

}
