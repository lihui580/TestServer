package com.itaoniu.testserver.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 格林时间 GMT + 8:00
 *
 */
public class GMTTimeUtils {

	/**
	 * 当前时间
	 */
	public static String now(){
		Date curDate = new Date();
		DateFormat format=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		return format.format(curDate);
	}
	
	/**
	 * date到GMT字符串
	 */
	public static String toGMT(Date date){
		DateFormat format=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		return format.format(date);
	}
	
	/**
	 * 时间
	 */
	public static Date toDate(String gmt) throws ParseException{
		DateFormat format=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.ENGLISH);
		Date date = format.parse(gmt);
		return date;
	}
	
	public static Date calc(Date date,int field,int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
	
}
