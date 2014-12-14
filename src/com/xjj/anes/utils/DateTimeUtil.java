package com.xjj.anes.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @description:
 * @date:
 */

public class DateTimeUtil
{
	public final static String SDFyyyyMM = "yyyy-MM";
	public final static String SDFyyyyMMdd = "yyyy-MM-dd";
	public final static String SDFyyyyMMddHH = "yyyy-MM-dd HH";
	public final static String SDFyyyyMMddHHmm = "yyyy-MM-dd HH:mm";
	public final static String SDFyyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	public final static String SDFyyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";

	public final static long millisecondsPerMinute = 60L * 1000L;
	public final static long millisecondsPerHour = 60L * millisecondsPerMinute;
	public final static long millisecondsPerDay = 24L * millisecondsPerHour;
	public final static long millisecondsPerWeek = 7L * millisecondsPerDay;

	//private static Calendar cal = Calendar.getInstance();

	public static String dateFormat(Timestamp d)
	{
		return d == null ? null : new SimpleDateFormat(SDFyyyyMMdd).format(d);
	}

	public static String DateTimeFormat(Date d)
	{
		return d == null ? null : new SimpleDateFormat(SDFyyyyMMddHHmmss).format(d);
	}

	public static String dateToString(Date date, String format)
	{
		if (date == null || format == null)
		{
			return null;
		}
		return new SimpleDateFormat(format).format(date);
	}

	public static Date stringToDate(String date, String format)
	{
		if (date == null || format == null || date == "")
		{
			return null;
		}
		try
		{
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException ex)
		{
			return null;
		}
	}

	public static Date parseStandardDate(String value)
	{
		Date date = null;
		int n = value.length();
		try
		{
			if (n >= "yyyy-MM-dd HH:mm:ss.S".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(value);
			} else if (n == "yyyy-MM-dd HH:mm:ss".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
			} else if (n == "yyyy-MM-dd HH:mm".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(value);
			} else if (n == "yyyy-MM-dd HH".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH").parse(value);
			} else if (n == "yyyy-MM-dd".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
			} else if (n == "yyyy-MM".length())
			{
				date = new SimpleDateFormat("yyyy-MM").parse(value);
			}
		} catch (ParseException e)
		{
			Log log = LogFactory.getLog(DateTimeUtil.class.getName());
			log.warn("Can not parse [" + value + "] to java.util.Date");
		}
		return date;
	}

	/**
	 * 获取当前时间戳 描述:
	 * 
	 * @return
	 */
	public static String getCurrentTimestamp()
	{
		final Calendar cal = Calendar.getInstance();
		final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		return df.format(cal.getTime());
	}

	/**
	 * 
	 * 描述: 获取当前日期
	 * 
	 * @date 2014年4月10日
	 * @return
	 */
	public static Date getCurrentDate()
	{
		return new Date();
		//return cal.getTime();
	}

}
