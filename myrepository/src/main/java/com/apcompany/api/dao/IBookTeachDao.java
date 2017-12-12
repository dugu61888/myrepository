package com.apcompany.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.apcompany.api.model.schema.BookDayTeachDO;
import com.apcompany.api.model.schema.BookTimeTeachDO;

public interface IBookTeachDao {
	
	@Select("select * from book_day_teach where id=#{id}")
	public BookDayTeachDO getBookDayDOById(@Param("id") int id);
	
	@Insert("insert into book_day_teach(book_day,status,teach_course_id,created,modified) values(#{bookDay},#{status},#{teachCourseId},NOW(),NOW())")
	public void addBookDay(BookDayTeachDO bookDayDO);
	
	@Select("select id, book_day,status from book_day_teach where book_day>=#{firstDay} and book_day<=#{lastDay} and teach_course_id=#{teachCourseId}")
	public List<BookDayTeachDO> getBookDayListByMonth(
			@Param("teachCourseId")int teachCourseId,
			@Param("firstDay")int firstDay,
			@Param("lastDay")int lastDay);
	
	@Update("update book_day_teach set status=#{status} where id=#{id}")
	public void updateBookDayStatus(BookDayTeachDO bookDayTeachDO);
	
	@Select("select book_day_id,start_hour,end_hour from book_time_teach where book_day_id=#{bookDayId} and status in (0,1) order by start_hour ")
	public List<BookTimeTeachDO> getBookTimeListByBookDay(int bookDayId);
	
	@Select("select * from book_time_teach where book_day_id=#{bookDayId} and "
			+ "(start_hour>=#{startHour} and end_hour<=#{endHour}) or "
			+ "(start_hour<#{startHour}  and end_hour>#{endHour}) or "
			+ "(start_hour>=#{startHour} and start_hour<#{endHour}) or "
			+ "(end_hour<#{endHour} and end_hour>#{startHour}) and status in (0,1) limit 1")
	public BookTimeTeachDO checkBookTimeIsExit(
			@Param("bookDayId")int bookDayId,
			@Param("startHour")int startHour,
			@Param("endHour")int endHour);
	
	@Insert("insert into book_time_teach(student_id,book_day_id,"
			+ "start_hour,end_hour,status,created,modified) "
			+ "values(#{studentId},#{bookDayId},#{startHour},#{endHour},#{status},"
			+ "now(),now())")
	public void addBookTime(BookTimeTeachDO book);
	
	@Select("select sum(start_hour - end_hour) from book_time_teach where book_day_id=#{bookDayId}")
	public Integer getTotalHoursByBookDay(int bookDayId);
	
	@Select("select * from book_time_teach where student_id=#{studentId} order by created")
	public List<BookTimeTeachDO> getStudentBookList(int studentId);
	
	@Select("select * from book_time_teach where id=#{id}")
	public BookTimeTeachDO getBookTimeById(@Param("id") int id);
	
	@Update("update book_time_teach set status =#{status},modified=now() where id=#{id}")
	public void updateBookTimeStatus(BookTimeTeachDO bookTeachOrderDO);
	
	
	
	
	
}
