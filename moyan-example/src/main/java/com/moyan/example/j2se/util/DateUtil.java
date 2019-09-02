package com.moyan.example.j2se.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

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
			logger.error(e.getMessage(),e);
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
		logger.info("" + getDateBeforeN(25));
		logger.info("" + convertDate2Time(dateFormat, getDate(getDateBeforeN(25), dateFormat)));
		logger.info("" + convertTime2Date(dateFormat, getCurTime()));
		logger.info("" + getCurTime());
		
		logger.info(""+ Integer.MAX_VALUE);
		logger.info("" + Integer.MIN_VALUE);


	}
}
