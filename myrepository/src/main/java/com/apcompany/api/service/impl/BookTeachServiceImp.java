package com.apcompany.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apcompany.api.constrant.BookDayStatusEnum;
import com.apcompany.api.constrant.Constrant;
import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.dao.IBookTeachDao;
import com.apcompany.api.dao.ITeachCourseDao;
import com.apcompany.api.model.schema.BookDayTeachDO;
import com.apcompany.api.model.schema.BookTimeTeachDO;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.schema.TeachOrderDO;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.api.util.DateUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

@Service
public class BookTeachServiceImp implements IBookTeachService  {
	
	private Logger logger= Logger.getLogger(BookTeachServiceImp.class);
	
	@Autowired private IBookTeachDao bookDao;
	@Autowired private ITeachCourseDao teachCourseDao;
	
	@Autowired private ITeachOrderService teachOrderService;
	
	private static ExecutorService executorService = new ThreadPoolExecutor(
            5, 5, 1L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(30),
            new ThreadFactoryBuilder().setNameFormat("ActivityMovieStatController-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

	@Override
	public boolean initOneYearBookDayList(int teachCourseId) {
		final int teachCourseIdF= teachCourseId;
		try {
			executorService.execute(new Runnable() {			
				@Override
				public void run() {
					Date date = new Date();
					for(int i=0;i<365;i++){	
						BookDayStatusEnum bookDayStatus=BookDayStatusEnum.NOT_BOOK;
						if(i<30){
							bookDayStatus=BookDayStatusEnum.NORMAL_BOOK;
						}
						BookDayTeachDO bookDayTeachDO =new BookDayTeachDO(DateUtil.formateDateToYMDIntDay(date),teachCourseIdF,bookDayStatus);
						bookDao.addBookDay(bookDayTeachDO);
						date =DateUtil.addDays(date, 1);
					}				
				}
			});
		} catch (Exception e) {
			logger.error(e);
			return false;
		}	
		return true;
	}
	
	
	@Override
	public List<BookDayTeachDO> getBookDayListByMonth(int teachCourseId,int year,int month) {	
		int firstDay = DateUtil.getFristDayOfMonth(year, month);
		int lastDay = DateUtil.getLastDayOfMonth(year, month);
		return bookDao.getBookDayListByMonth(teachCourseId,firstDay,lastDay);
	}
	
	@Override
	public List<BookTimeTeachDO> getBookTimeListByBookDayId(int bookDayId) {
		
		return bookDao.getBookTimeListByBookDay(bookDayId);
	}
	
	@Transactional
	@Override
	public TeachOrderDO addBookTime(int studentId,int bookDayId,int startHour,int endHour){
        if(checkBookDayCanBookAndRefresh(bookDayId, startHour, endHour)==false){
        	System.out.println("bookTimeTeachDO is exit");
        	return null;
        }
		BookTimeTeachDO bookTimeDO =new BookTimeTeachDO(studentId, bookDayId, startHour, endHour);		
		bookDao.addBookTime(bookTimeDO);	
		return teachOrderService.createBookOrder(bookTimeDO);
	}
	
	@Override
	public List<BookTimeTeachDO> getStudentBookingList(int studentId) {
		return bookDao.getStudentBookList(studentId);
	}
	
	@Override
	public BookTimeTeachDO getBookTimeById(int id) {
		return bookDao.getBookTimeById(id);	
	}

	@Override
	public TeachOrderDO cancelBook(int studentId, int bookId) {
		BookTimeTeachDO bookTimeTeachDO = getBookTimeById(bookId);
		
		if(bookTimeTeachDO==null || bookTimeTeachDO.getStudentId()!=studentId){
			return null;
		}
		BookDayTeachDO bookDayTeachDO= getBookDayById(bookTimeTeachDO.getBookDayId());
		if(bookDayTeachDO==null){
			return null;
		}
		Date startTime= DateUtil.createDate(bookDayTeachDO.getBookDay(), bookTimeTeachDO.getStartHour());
		if(new Date().compareTo(startTime)>0){
			return null;
		}
		this.releseBook(bookTimeTeachDO);
		return teachOrderService.cancelBookOrder(bookId);
	}

	@Override
	public boolean updateBookDayStatusByTeacher(int teacherId,int bookDayId, int status) {
		BookDayTeachDO bookDayDO = getBookDayById(bookDayId);
		if(bookDayDO==null){
			return false;
		}
		TeachCourseDO teachCourseDO = teachCourseDao.getTCById(bookDayDO.getTeachCourseId());
		if(teachCourseDO==null || teachCourseDO.getTeacherId()!= teacherId){
			return false;
		}
		bookDayDO.setStatus(status);
		bookDao.updateBookDayStatus(bookDayDO);
		return true;
	}


	@Override
	public BookDayTeachDO getBookDayById(int id) {
		return bookDao.getBookDayDOById(id);
	}

	private boolean checkBookDayCanBookAndRefresh(int bookDayId,int startHour,int endHour){
		int bookHour= endHour - startHour;
		if(startHour<Constrant.EARLY_BOOK_HOUR || endHour>Constrant.LAST_BOOK_HOUR || bookHour<=0){
			//不合法
			return false;
		}
		//check bookday is full or not for book
		BookDayTeachDO bookDayDO= getBookDayById(bookDayId);
		if(bookDayDO==null || bookDayDO.getStatus()==BookDayStatusEnum.NOT_BOOK.getKey()||
				bookDayDO.getStatus()==BookDayStatusEnum.FULL_BOOK.getKey()){
			return false;
		}
		//check teach_course is normal
		TeachCourseDO teachCourseDO=teachCourseDao.getTCById(bookDayDO.getTeachCourseId());
		if(teachCourseDO==null|| teachCourseDO.getStatus()==TeachCourseStatusEnum.CLOSE.getKey()){
			return false;
		}
		Integer totalBookHour= bookDao.getTotalHoursByBookDay(bookDayId);
		totalBookHour=totalBookHour==null?0:totalBookHour;
		if((totalBookHour+bookHour)>Constrant.MAX_BOOK_HOURS_PER_DAY){
			return false;
		}
		BookTimeTeachDO bookTimeDO =bookDao.checkBookTimeIsExit(bookDayId,startHour,endHour);
		if(bookTimeDO != null){
			return false;
		}
		//booktime reach full
		if((bookHour+totalBookHour) ==10){
			bookDayDO.setStatus(BookDayStatusEnum.FULL_BOOK.getKey());
			bookDao.updateBookDayStatus(bookDayDO);
		}
		return true;
	}

	//release booking
	private void releseBook(BookTimeTeachDO bookTimeTeachDO){
		if(bookTimeTeachDO==null){
			return;
		}
		bookTimeTeachDO.setStatus(2);
		BookDayTeachDO bookDayTeachDO= getBookDayById(bookTimeTeachDO.getBookDayId());
		bookDayTeachDO.setStatus(0);
		bookDao.updateBookDayStatus(bookDayTeachDO);
		bookDao.updateBookTimeStatus(bookTimeTeachDO);
	}
	
	
	

	

}
