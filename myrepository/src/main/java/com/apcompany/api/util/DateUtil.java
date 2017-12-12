package com.apcompany.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date parseDateFromYMDHM(String ymdhm){
		SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sf.parse(ymdhm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formateDateToYMDHM(Date date){
		SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parseDateFromYMDHMS(String ymdhms){
		SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sf.parse(ymdhms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formateDateToYMDHMS(Date date){
		SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parseDateFromYMDIntDay(Integer ymdIntDate){
		try {
		SimpleDateFormat sf= new SimpleDateFormat("yyyyMMdd");
		if(ymdIntDate==null){
			return null;
		}		
			return sf.parse(ymdIntDate+"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Integer formateDateToYMDIntDay(Date date){
		SimpleDateFormat sf= new SimpleDateFormat("yyyyMMdd");
		try {
			return Integer.valueOf(sf.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int compareMinuteBetweenDate(Date date1,Date date2){
    	return Integer.valueOf(((date1.getTime()-date2.getTime())/(1000*60))+"");
    }
	
	public static int getFristDayOfMonth(int year,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		return formateDateToYMDIntDay(calendar.getTime());		
	}
	
	public static int getLastDayOfMonth(int year,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return formateDateToYMDIntDay(calendar.getTime());	
	}
	
	public static Date addDays(Date date,int day){
		Calendar c= Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		return c.getTime();
	}
	public static Date addMinutes(Date date,int minute){
    	Calendar c=Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.MINUTE, minute);
    	return c.getTime();
    }
	
	public static Date createDate(int day,int hour){
		Date date = parseDateFromYMDIntDay(day);
		Calendar c= Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, hour);
		return c.getTime();
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println(createDate(20171101, 12));
	}
	
	

}
