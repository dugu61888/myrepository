package com.apcompany.api.service;

import java.util.List;

import com.apcompany.api.model.schema.BookDayTeachDO;
import com.apcompany.api.model.schema.BookTimeTeachDO;
import com.apcompany.api.model.schema.TeachOrderDO;

public interface IBookTeachService {
	
	boolean initOneYearBookDayList(int teachCourseId);
	
	List<BookDayTeachDO> getBookDayListByMonth(int teachCourseId,int year,int month);
	
	List<BookTimeTeachDO> getBookTimeListByBookDayId(int bookDayId);
	
	TeachOrderDO addBookTime(int studentId,int bookDayId,int startHour,int endHour);
	
	BookDayTeachDO getBookDayById(int id);

	/*
	 * Get student booking list
	 */
	List<BookTimeTeachDO> getStudentBookingList(int studentId);
	
	BookTimeTeachDO getBookTimeById(int id);
	
	TeachOrderDO cancelBook(int studentId, int bookId);
	
	// teacher book service
	boolean updateBookDayStatusByTeacher(int teacherId,int bookDayId,int status);
	
	
}
