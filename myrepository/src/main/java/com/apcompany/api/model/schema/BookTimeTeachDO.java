package com.apcompany.api.model.schema;

public class BookTimeTeachDO {
	
	private int id;
	private int studentId;
	// 哪一天的预约ID。--> book_day_teach id
	private int bookDayId;
	
	private int startHour;
	private int endHour;
	
	//预约状态， 0 预约中，1.预约成功，2。取消（pay before) 3.取消（pay after)
	private int status;
	
	private String created;
	private String modified;
	
	public BookTimeTeachDO(){
		
	}
	
	public BookTimeTeachDO(int studentId,int bookDayId,int startHour,int endHour){
		super();
		this.setStudentId(studentId);
		this.setBookDayId(bookDayId);
		this.setStatus(0);
		this.setStartHour(startHour);
		this.setEndHour(endHour);
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getBookDayId() {
		return bookDayId;
	}
	public void setBookDayId(int bookDayId) {
		this.bookDayId = bookDayId;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public int getEndHour() {
		return endHour;
	}
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
