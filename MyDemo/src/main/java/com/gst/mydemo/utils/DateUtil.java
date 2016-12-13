package com.gst.mydemo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间
 */
public class DateUtil {
	public static final long DAY_MILLIS = 1000 * 60 * 60 * 24;
	public static final long MONTH_MILLIS = DAY_MILLIS * 30;
	public static final long YEAR_MILLIS = DAY_MILLIS * 365;
	private static long startTime;

	public static String getMysqlDateTimeFormat() {
		return "yyyy-MM-dd kk:mm:ss";
	}

	/**
	 * Extract years of a date
	 * 
	 * @param millis
	 * @return
	 */
	public static int getYears(long millis) {
		return (int) (millis / YEAR_MILLIS);
	}

	/**
	 * Extract month count from a date after divided years
	 * 
	 * @param millis
	 * @return
	 */
	public static int getMonths(long millis) {
		millis %= YEAR_MILLIS;
		return (int) (millis / MONTH_MILLIS);
	}

	/**
	 * Extract day count from a date after divided months
	 * 
	 * @param millis
	 * @return
	 */
	public static int getDays(long millis) {
		millis %= MONTH_MILLIS;
		return (int) (millis / DAY_MILLIS);
	}

	/**
	 * Calculate the total days of a date
	 * 
	 * @param millis
	 * @return
	 */
	public static int getDaysCount(long millis) {
		return (int) (millis / DAY_MILLIS);
	}

	public static class PeriodFormmater {
		private int years;
		private int months;
		private int days;
		private String yearsSuffix = "";
		private String monthsSuffix = "";
		private String daysSuffix = "";

		public PeriodFormmater() {
		}

		public PeriodFormmater(long millis, String[] suffixes) {
			setMillis(millis);
			yearsSuffix = suffixes[0];
			monthsSuffix = suffixes[1];
			daysSuffix = suffixes[2];
		}

		public void setMillis(long millis) {
			years = DateUtil.getYears(millis);
			months = DateUtil.getMonths(millis);
			days = DateUtil.getDays(millis);
		}

		public int getYears() {
			return years;
		}

		public void setYears(int years) {
			this.years = years;
		}

		public int getMonths() {
			return months;
		}

		public void setMonths(int months) {
			this.months = months;
		}

		public int getDays() {
			return days;
		}

		public void setDays(int days) {
			this.days = days;
		}

		public String getYearsSuffix() {
			return yearsSuffix;
		}

		public void setYearsSuffix(String yearsSuffix) {
			this.yearsSuffix = yearsSuffix;
		}

		public String getMonthsSuffix() {
			return monthsSuffix;
		}

		public void setMonthsSuffix(String monthsSuffix) {
			this.monthsSuffix = monthsSuffix;
		}

		public String getDaysSuffix() {
			return daysSuffix;
		}

		public void setDaysSuffix(String daysSuffix) {
			this.daysSuffix = daysSuffix;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (years != 0) {
				sb.append(years + yearsSuffix);
			}
			if (months != 0) {
				sb.append(months + monthsSuffix);
			}
			sb.append(days + daysSuffix);
			return sb.toString();
		}
	}
	
	/**
	 * 获得今天的日期
	 * 
	 */
	public static String getToDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal
				.getTime());
		return yesterday;
	}

	/**
	 * 获得昨天的日期
	 * 
	 * @return 返回的格式为year/+month/day，注意是昨天的日期
	 */
	public static String getYesterDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy/MM/dd ").format(cal
				.getTime());
		return yesterday;
	}

	/**
	 * 获取当前时间格式化后的函数
	 * 
	 * @return 格式为2002/1/1 12:12:12
	 */
	public static String getFormateCreatedDate() {
		Calendar calendar = Calendar.getInstance();
		String created = format(calendar.get(Calendar.YEAR)) + "/"
				+ format(calendar.get(Calendar.MONTH) + 1) + "/"
				+ format(calendar.get(Calendar.DAY_OF_MONTH)) + " "
				+ format(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ format(calendar.get(Calendar.MINUTE));
		return created;
	}

	/**
	 * 
	 * @return 时间格式为 yyyy/mm/dd
	 */
	public static String getFormateCreatedDate2() {
		Calendar calendar = Calendar.getInstance();
		String created = format(calendar.get(Calendar.YEAR)) + "/"
				+ format(calendar.get(Calendar.MONTH) + 1) + "/"
				+ format(calendar.get(Calendar.DAY_OF_MONTH));
		return created;
	}

	/**
	 * @return 时间格式为 yyyy-mm-dd
	 */
	public static String getFormateCreatedDate3() {
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
	private static String format(int x) {
		String s = "" + x;
		if (s.length() == 1)
			s = "0" + s;
		return s;
	}

	/**
	 * 将时间字符串转换成毫秒数 格式为"yyyy-MM-dd"
	 * 
	 * @throws ParseException
	 */
	public static long getDate(String str) throws ParseException {
		// String str = "2012-05-14";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		long ms = sdf.parse(str).getTime();// 毫秒

		return ms;

	}

	// 获得当前时间的 时间戳--10位
	public static String timestamp() {
		Long tsLong = System.currentTimeMillis()/1000;
		String timestamp = tsLong.toString();
		return timestamp;
	}

	public static void setStart() {
		startTime = 0;
		startTime = System.currentTimeMillis();
	}

	public static void calcEnd(String mothodName) {
		long endTime = System.currentTimeMillis(); // 排序后取得当前时间
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(endTime - startTime);
	}

	/**
	 *  获取当前日期  yyyy-MM-dd
	 * */
	public static String getDate() {
		Date myDate = new Date();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sDateFormat.format(myDate);
	}

	/**
	 * 获取当前日期时间  yyyy-MM-dd HH:mm:ss
	 * */
	public static String getDateTime() {
		Date myDate = new Date();
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return sDateFormat.format(myDate);
	}

	/** 根据时间戳毫秒数获取指定日期 */
	public static String getDate(long milliseconds) {
		Date myDate = new Date(milliseconds);
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sDateFormat.format(myDate);
	}

	/** 根据时间戳毫秒数获取指定日期时间 */
	public static String getDateTime(long milliseconds) {
		Date myDate = new Date(milliseconds);
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return sDateFormat.format(myDate);
	}

	/** 根据时间戳秒数获取指定日期 */
	public static String getDateBySecondStr(String secondStr) {
		return DateUtil.getDate(Long.parseLong(secondStr + "000"));
	}

	/** 根据时间戳秒数获取指定日期时间 */
	public static String getDateTimeBySecondStr(String secondStr) {
		return DateUtil.getDate(Long.parseLong(secondStr + "000"));
	}

	public static Calendar addDayToCalendar(String dateStr, int addDay) {
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(5, 7);
		String day = dateStr.substring(8, 10);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month),
				Integer.parseInt(day));
		calendar.add(Calendar.DAY_OF_YEAR, addDay);
		return calendar;
	}

	public static Calendar addMonthToCalendar(String dateStr, int addMonth) {
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(5, 7);
		String day = dateStr.substring(8, 10);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
				Integer.parseInt(day));
		calendar.add(Calendar.MONTH, addMonth);
		return calendar;
	}

	/**
	 *  获取当前是今年的第几天
	 * @return day of this year
	 */
	public static int getDayOfYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * @param dateStr
	 *            ####/##/##
	 * @return day of week
	 */
	public static int getDayOfWeekFromDate(String dateStr) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(convertStringToDate(dateStr));
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * @param dateStr
	 *            ####/##/##
	 * @return date class
	 */
	public static Date convertStringToDate(String dateStr) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将数据格式：2012-04-27-00:00:00 转换成2012-04-27
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String changeDate(String dateStr) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formater.format(formater.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得月和日
	 * 
	 * @return
	 */
	public static String getDateOfMonthAndDay() {
		Date myDate = new Date();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("MM月dd日");
		return sDateFormat.format(myDate);
	}

	/**
	 * 获得当前月份和天数
	 * 
	 * @return
	 */
	public static String getCurrentMthAndDay() {
		Calendar calendar = Calendar.getInstance();
		String created = format(calendar.get(Calendar.MONTH) + 1) + "-"
				+ format(calendar.get(Calendar.DAY_OF_MONTH));
		return created;
	}

	/**
	 * 两个时间之间相差距离多少天
	 * 
	 * @return 相差天数
	 */
	public static long getDistanceDays(String str1) throws Exception {
		str1 = subBirth(str1);
		if (str1 == null)
			return -1;
		String str2 = getCurrentMthAndDay();
		DateFormat df = new SimpleDateFormat("MM-dd");
		Date one;
		Date two;
		long days = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			int day = judgeYear();
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
			if (time1 < time2) {
				days = day - days;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	private static String subBirth(String birthday) {
		if (birthday != null) {
			birthday = birthday.length() > 10 ? birthday.substring(0, 10)
					: birthday;
			birthday = birthday.substring(5, birthday.length());
		}
		return birthday;
	}

	/**
	 * 判断当前年是平年还是闰年
	 * 
	 * @return
	 */
	public static int judgeYear() {
		int day = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		System.out.println("year :" + year);
		// 平年365 闰年366
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) { // 闰年
			day = 366;
		} else { // 平年
			day = 365;
		}
		return day;
	}

	// 日期转换成秒数
	public long timeInMillis(String dateTime) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateTime));
			System.out.println("时间转化后的毫秒数为：" + c.getTimeInMillis());
			long tt = c.getTimeInMillis() / 1000;
			return tt;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String dateFormat(String time) {
		// String s = "Thu, 20 Oct 2016 00:00:00 +0800";
		if (time == null || time.equals(""))
			return null;
		return commonFormat(time, "yyyy-MM-dd HH:mm");
	}
	
	public String dateYmd(String time) {
		if (time == null || time.equals(""))
			return null;
		return commonFormat(time, "yyyy-MM-dd");
	}

//	xinqi[8] = [7, "Sun ", "Mon ", "Tue ", "Wed ", "Thu ", "Fri ", "Sat "] ;星期 0 - 6
//	yue[13] = [12, "Jan ", "Feb ", "Mar ", "Apr ", "May ", "Jun ", "Jul ", "Aug ", "Sep ", "Oct ", "Nov ", "Dec "] ;月分 1 - 12
	// 格林尼标准时间
	private String commonFormat(String time, String type) { // time : Mon, 16 Nov 2015 23:12:43 +0800
		SimpleDateFormat sf = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		try {
			Date date = sf.parse(time);
			SimpleDateFormat sdf2 = new SimpleDateFormat(type);
			String birth = sdf2.format(date);
			return birth;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
