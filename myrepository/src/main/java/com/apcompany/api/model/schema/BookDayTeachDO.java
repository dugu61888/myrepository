package com.apcompany.api.model.schema;

import com.apcompany.api.constrant.BookDayStatusEnum;

public class BookDayTeachDO {
	
	private int id;
	
	// sample: 20171101
	private int bookDay;
	//0,正常可预约 1.满，不可预约 2.不可预约。
	private int status;
	
	private int teachCourseId;
	
	private String created;
	
	private String modified;
	
	public BookDayTeachDO(){
		
	}
	
	public BookDayTeachDO(int bookDay,int teachCouseId,BookDayStatusEnum status){
		this.bookDay=bookDay;
		this.teachCourseId=teachCouseId;
		this.status=status.getKey();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookDay() {
		return bookDay;
	}
	public void setBookDay(int bookDay) {
		this.bookDay = bookDay;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public int getTeachCourseId() {
		return teachCourseId;
	}

	public void setTeachCourseId(int teachCourseId) {
		this.teachCourseId = teachCourseId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	
	

}
