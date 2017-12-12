package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.apcompany.api.service.IBookTeachService;

@Controller
@RequestMapping("/book/teacher")
public class TeacherBookController {
	
	@Autowired
	private IBookTeachService iBookingService;

	// 
	@RequestMapping(value = "/update/bookDaystatus", method = RequestMethod.GET)
	@ResponseBody
	public Object updateBookDayStatus(
			@SessionAttribute(value = "tokenId", required = true) Integer teacherId,
			@RequestParam(value = "bookDayId", required = true) int bookDayId,
			@RequestParam(value = "status", required = true) int status) {
		return iBookingService
				.updateBookDayStatusByTeacher(teacherId, bookDayId, status);
	}
	
	

}
