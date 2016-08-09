package com.jason.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNowDateTime {
	
	static SimpleDateFormat sdf_date_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
	
	static SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
	
	static SimpleDateFormat sdf_month = new SimpleDateFormat("MM");
	
	static SimpleDateFormat sdf_day = new SimpleDateFormat("dd");
	
	public static String getDateTime() {
		Date date = new Date();
		return sdf_date_time.format(date);
	}
	
	public static String getDate() {
		Date date = new Date();
		return sdf_date.format(date);
	}
	
	public static String getYear() {
		Date date = new Date();
		return sdf_year.format(date);
	}
	
	public static String getMonth() {
		Date date = new Date();
		return sdf_month.format(date);
	}
	
	public static String getDay() {
		Date date = new Date();
		return sdf_day.format(date);
	}
	
	
}
