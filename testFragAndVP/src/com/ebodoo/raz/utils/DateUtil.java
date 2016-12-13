package com.ebodoo.raz.utils;

import com.example.location.biz.StringBiz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

	/**
	 * @return 时间格式为 yyyy-mm-dd
	 */
	public String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		String created = format(calendar.get(Calendar.YEAR)) + "-"
				+ format(calendar.get(Calendar.MONTH) + 1) + "-"
				+ format(calendar.get(Calendar.DAY_OF_MONTH));
		return created;
	}

	/**
	 * 日期时间从1格式化为01 e.g.从2012/1/1可组合成2012/01/01
	 * 
	 * @param x
	 * @return
	 */
	private String format(int x) {
		String s = "" + x;
		if (s.length() == 1)
			s = "0" + s;
		return s;
	}
	
	/**
	 *  日期转换成秒数
	 * @param dateTime
	 * @return
	 */
	public long timeInMillis(String dateTime) {
		try {
			Calendar c = Calendar.getInstance();  
			c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateTime));
			System.out.println("时间转化后的毫秒数为："+c.getTimeInMillis());  
			long tt = c.getTimeInMillis()/1000;
			return tt;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String dateFormat(String time) {
//		String s = "Thu, 20 Oct 2016 00:00:00 +0800";
		if(time == null || time.equals("")) return null;
        SimpleDateFormat sf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z",Locale.ENGLISH);
        try {
        	Date date = sf.parse(time);
        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        	String birth = sdf2.format(date);
        	return birth;
        } catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 秒转换成00:00:00
	public String changeTime(String strSec) {
		String value = "00:00:00";
		if(!StringBiz.isEmpty(strSec) && StringBiz.isNumeric(strSec)) {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");//初始化Formatter的转换格式。
			long time = Integer.valueOf(strSec) * 1000 - TimeZone.getDefault().getRawOffset();
			value = formatter.format(time);
		}
		return value.substring(3, value.length());
	}
	
}
