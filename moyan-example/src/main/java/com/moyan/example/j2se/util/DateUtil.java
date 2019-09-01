package com.moyan.example.j2se.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String dateFormat = "yyyy-MM-dd hh:mm:ss";

	public static long getCurTime() {
		return System.currentTimeMillis();
	}

	public static long getTime(Date date) {
		return date.getTime();
	}
	
	public static String getCurDate() {
		return getDate(new Date(),null);
	}

	public static String getDate(Date date,String format) {
		if (format == null || format.isEmpty()) {
			format = dateFormat;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);

	}

	public static Date getDateByDate(String date,String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(date);
	}
	
	public static Date getDateByTime(long time) {
		return new Date(time);
	}
	
	public static Date getDateBeforeN(int dates) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -dates);
		// calendar.add(Calendar.DAY_OF_MONTH, -dates);
		Date curDate = calendar.getTime();
		return curDate;
	}
	
	public static Date getDateBeforeN(int dates,int hours,int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -dates);
//		calendar.add(Calendar.HOUR, -hours);
		calendar.add(Calendar.HOUR_OF_DAY, -hours);
		calendar.add(Calendar.MINUTE, -minutes);
		// calendar.add(Calendar.DAY_OF_MONTH, -dates);
		Date curDate = calendar.getTime();
		return curDate;
	}

	public static long convertDate2Time(String format, String date) {
		if (format == null || format.isEmpty()) {
			format = dateFormat;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1L;
	}

	public static String convertTime2Date(String format, long time) {
		if (format == null || format.isEmpty()) {
			format = dateFormat;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return dateFormat.format(calendar.getTime());
	}

	public static void main(String[] args) {
		System.out.println(getDateBeforeN(25));
		System.out.println(convertDate2Time(dateFormat, getDate(getDateBeforeN(25), dateFormat)));
		System.out.println(convertTime2Date(dateFormat, getCurTime()));
		System.out.println(getCurTime());
		
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);


	}
}
