package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.apcompany.api.service.IBookTeachService;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/book/student")
public class StudentBookController {

	@Autowired
	private IBookTeachService iBookingService;

	// 返回某月该老师课程的所有预约日期列表--纬度 天。
	@RequestMapping(value = "/monthtotal/days.json", method = RequestMethod.GET)
	@ResponseBody
	public Object listBookDayList(
			@RequestParam(value = "teachCourseId", required = true) int teachCourseId,
			@RequestParam(value = "year", required = true) int year,
			@RequestParam(value = "month", required = true) int month) {
		return iBookingService
				.getBookDayListByMonth(teachCourseId, year, month);
	}

	// 返回某天该老师课程的所有详细预约时间列表--纬度 time。
	@RequestMapping(value = "/bookday/books.json", method = RequestMethod.GET)
	@ResponseBody
	public Object listBookTimeList(
			@RequestParam(value = "bookDayId", required = true) int bookDayId) {
		return iBookingService.getBookTimeListByBookDayId(bookDayId);
	}

	// 预约时间
	@RequestMapping(value = "/create/book.do", method = RequestMethod.GET)
	@ResponseBody
	public Object addBook(
			@RequestAttribute(value = "studentId", required = true) Integer studentId,
			@RequestParam(value = "bookDayId", required = true) int bookDayId,
			@RequestParam(value = "startHour", required = true) int startHour,
			@RequestParam(value = "endHour", required = true) int endHour) {
		return iBookingService
				.addBookTime(studentId, bookDayId, startHour, endHour);
	}

	// 查看我的预约
	@RequestMapping(value = "/booklist.json", method = RequestMethod.GET)
	@ResponseBody
	public Object studentBookList(
			@RequestAttribute(value = "studentId", required = true) Integer studentId) {
		return TipUtil.success(iBookingService.getStudentBookingList(studentId));
	}

	// 查看我的某一个预约
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object showBook(
			@SessionAttribute(value = "studentId", required = true) Integer studentId,
			@PathVariable("id") int id) {
		return iBookingService.getBookTimeById(id);
	}

	// 取消book
	@RequestMapping(value = "/cancel.do", method = RequestMethod.GET)
	@ResponseBody
	public Object cancelBookBeforePay(
			@SessionAttribute(value = "studentId", required = true) Integer studentId,
			@RequestParam(value = "bookId", required = true) int bookId) {
		return iBookingService.cancelBook(studentId, bookId);
	}

}
