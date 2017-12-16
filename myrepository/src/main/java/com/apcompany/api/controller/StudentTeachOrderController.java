package com.apcompany.api.controller;

import com.apcompany.api.model.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
			@RequestAttribute(value = "studentId", required = true) int studentId,
			@RequestParam(value = "orderId",required = true) int orderId,
			@RequestParam(value = "teacherMannerScore",required = true) float teacherMannerScore,
			@RequestParam(value = "teacherSkillScore",required = true) float teacherSkillScore,
			@RequestParam(value = "teacherCustomerScore",required = true) float teacherCustomerScore

			) {

		OrderTeacherScoreForm form=new OrderTeacherScoreForm();
		form.setOrderId(orderId);
		form.setTeacherCustomerScore(teacherCustomerScore);
		form.setTeacherMannerScore(teacherMannerScore);
		form.setTeacherSkillScore(teacherSkillScore);

		return ApiResponse.buildSuccess(teachOrderService.markScoreForOrder(studentId,form));
	}

}
