package com.vanstone.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.vanstone.common.MyAssert;

/**
 * Argylesoftware DateUtil 工具类
 * @author shipeng
 * Mail:shipeng@sagacityidea.com
 */
public class CommonDateUtil {

	/**日期时间格式*/
	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

	/**日期格式*/
	public static final String Y_M_D = "yyyy-MM-dd";

	/**时间格式*/
	public static final String H_M_S = "HH:mm:ss";
	
	/**日期时间（无秒）*/
	public static final String Y_M_D_H_M = "yyyy-MM-dd HH:mm";
	
	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = Y_M_D_H_M_S;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(timestamp.getTime()));
	}

	/**
	 * 日期转字符串类型
	 * @param date 日期
	 * @param pattern 转换模式
	 * @return
	 */
	public static String date2String(java.util.Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null || pattern.equals("")) {
			pattern = Y_M_D_H_M_S;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String currentTimestamp2String(String pattern) {
		return timestamp2String(currentTimestamp(), pattern);
	}

	public static Timestamp string2Timestamp(String strDateTime, String pattern) {
		if (strDateTime == null || strDateTime.equals("")) {
			throw new java.lang.IllegalArgumentException("Date Time Null Illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = Y_M_D_H_M_S;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDateTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return new Timestamp(date.getTime());
	}

	public static Date string2Date(String strDate, String pattern) {
		MyAssert.hasText(strDate);
		MyAssert.hasText(pattern);
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}
	
	public static String stringToYear(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, CommonDateUtil.Y_M_D);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return String.valueOf(c.get(Calendar.YEAR));
	}

	public static String stringToMonth(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, CommonDateUtil.Y_M_D);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// return String.valueOf(c.get(Calendar.MONTH));
		int month = c.get(Calendar.MONTH);
		month = month + 1;
		if (month < 10) {
			return "0" + month;
		}
		return String.valueOf(month);
	}

	public static String stringToDay(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, CommonDateUtil.Y_M_D);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			return "0" + day;
		}
		return "" + day;
	}

	public static Date getFirstDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		c.set(year, month, day, 0, 0, 0);
		return c.getTime();
	}

	public static Date getLastDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = 1;
		if (month > 11) {
			month = 0;
			year = year + 1;
		}
		c.set(year, month, day - 1, 0, 0, 0);
		return c.getTime();
	}

	public static String date2GregorianCalendarString(Date date) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("Date is null");
		}
		long tmp = date.getTime();
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTimeInMillis(tmp);
		try {
			XMLGregorianCalendar t_XMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(ca);
			return t_XMLGregorianCalendar.normalize().toString();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw new java.lang.IllegalArgumentException("Date is null");
		}
	}

	public static boolean compareDate(Date firstDate, Date secondDate) {
		if (firstDate == null || secondDate == null) {
			throw new java.lang.RuntimeException();
		}

		String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
		String strSecondDate = date2String(secondDate, "yyyy-MM-dd");
		if (strFirstDate.equals(strSecondDate)) {
			return true;
		}
		return false;
	}

	/**
	 * @param currentDate
	 * @return
	 */
	public static Date getStartTimeOfDate(Date currentDate) {
		MyAssert.notNull(currentDate);
		String strDateTime = date2String(currentDate, "yyyy-MM-dd") + " 00:00:00";
		return string2Date(strDateTime, "yyyy-MM-dd hh:mm:ss");
	}
	
	/**
	 * @param currentDate
	 * @return
	 */
	public static Date getEndTimeOfDate(Date currentDate) {
		MyAssert.notNull(currentDate);
		String strDateTime = date2String(currentDate, "yyyy-MM-dd") + " 23:59:59";
		return string2Date(strDateTime, "yyyy-MM-dd HH:mm:ss");
	}

	public static void main(String[] args) {
		// Date date = DateUtil.string2Date("2012-10-1 10:10:10",
		// "yyyy-MM-dd HH:mm:ss");
		// DateRange dr = getLastDateRange(date, 1);
		// System.out.println(DateUtil.date2String(dr.getStartInsertTime(),
		// "yyyy-MM-dd HH:mm:ss"));
		// System.out.println(DateUtil.date2String(dr.getEndInsertTime(),
		// "yyyy-MM-dd HH:mm:ss"));
	}
}