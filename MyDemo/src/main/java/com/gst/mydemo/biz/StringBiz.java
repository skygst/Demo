package com.gst.mydemo.biz;

public class StringBiz {

	
	/**
	 *  判断字符串是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		boolean isEmpty = true;
		if(value != null && !value.equals("")) {
			isEmpty = false;
		}
		return isEmpty;
	}
	
}
