package com.gst.move.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;

import com.gst.move.basic.Level10Activity;
import com.gst.move.basic.Level11Activity;
import com.gst.move.basic.Level12Activity;
import com.gst.move.basic.Level13Activity;
import com.gst.move.basic.Level14Activity;
import com.gst.move.basic.Level15Activity;
import com.gst.move.basic.Level1Activity;
import com.gst.move.basic.Level2Activity;
import com.gst.move.basic.Level3Activity;
import com.gst.move.basic.Level4Activity;
import com.gst.move.basic.Level5Activity;
import com.gst.move.basic.Level6Activity;
import com.gst.move.basic.Level7Activity;
import com.gst.move.basic.Level8Activity;
import com.gst.move.basic.Level9Activity;
import com.gst.move.basic.Quiz01Activity;

public class CommonUtil {

	public static String[] picUrl = {
		"http://img04.taobaocdn.com/bao/uploaded/i4/14190023170410817/T1IKSEXvFeXXXXXXXX_!!0-item_pic.jpg",
		"http://img03.taobaocdn.com/bao/uploaded/i3/TB1CDRTGFXXXXX0XXXXXXXXXXXX_!!0-item_pic.jpg",
		"http://img03.taobaocdn.com/bao/uploaded/i3/TB17MKPHXXXXXaSXpXXXXXXXXXX_!!0-item_pic.jpg",
		"http://img04.taobaocdn.com/bao/uploaded/i4/T1171OFlhaXXXXXXXX_!!0-item_pic.jpg",
		"http://img02.taobaocdn.com/bao/uploaded/i2/TB10b_UHpXXXXXiXpXXXXXXXXXX_!!0-item_pic.jpg",
		"http://img03.taobaocdn.com/bao/uploaded/i3/TB19eedHXXXXXX9XVXXXXXXXXXX_!!0-item_pic.jpg",
		"http://img01.taobaocdn.com/bao/uploaded/i1/TB1DJsQHpXXXXbpXVXXXXXXXXXX_!!0-item_pic.jpg",
		"http://img02.taobaocdn.com/bao/uploaded/i2/TB15BMBHXXXXXaGXVXXXXXXXXXX_!!0-item_pic.jpg",
		"http://image.taobao.com/bao/uploaded/i2/19309033965556190/T1EL02XBtcXXXXXXXX_!!2-item_pic.png",
		"http://img01.taobaocdn.com/bao/uploaded/i1/TB1dDuHHXXXXXc9XVXXXXXXXXXX_!!0-item_pic.jpg",
		"http://img04.taobaocdn.com/bao/uploaded/i4/TB1wdEdGVXXXXapXFXXXXXXXXXX_!!0-item_pic.jpg"
	};
	
	public static boolean wifiInfo(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (!wifiManager.isWifiEnabled()) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String spGetMvPath(Context context) {
		SharedPreferences sp = context.getSharedPreferences("mv_path", 0);
		String path = sp.getString("mvPath", "");
		return path;
	}

	public static void spSetMvPath(Context context, String path) {
		SharedPreferences sp = context.getSharedPreferences("mv_path", 0);
		Editor et = sp.edit();
		et.putString("mvPath", path);
		et.commit();
	}
	
	public static String mvPath(Context context, int level) {
		String path = "";
		String pathList = spGetMvPath(context);
		// String pathList = mvPathList();
		path = parseMvList(pathList, level);
		return path;
	}
	
	private static String parseMvList(String result, int level) {
		String path = "";
		if (result != null && !result.equals("")) {
			String[] pathList = result.split(",");
			if (level > 0 && level <= pathList.length) {
				path = pathList[level - 1];
			}
		}
		return path;
	}
	
	public static Class<?> classesView(int level) {
		Class<?> classes = null;
		if (level == 1) {
			classes = Level1Activity.class;
		} else if (level == 2) {
			classes = Level2Activity.class;
		} else if (level == 3) {
			classes = Level3Activity.class;
		} else if (level == 4) {
			classes = Level4Activity.class;
		} else if (level == 5) {
			classes = Level5Activity.class;
		} else if (level == 6) {
			classes = Level6Activity.class;
		} else if (level == 7) {
			classes = Level7Activity.class;
		} else if (level == 8) {
			classes = Level8Activity.class;
		} else if (level == 9) {
			classes = Level9Activity.class;
		} else if (level == 10) {
			classes = Level10Activity.class;
		} else if (level == 11) {
			classes = Level11Activity.class;
		} else if (level == 12) {
			classes = Level12Activity.class;
		} else if (level == 13) {
			classes = Level13Activity.class;
		} else if (level == 14) {
			classes = Level14Activity.class;
		} else if (level == 15) {
			classes = Level15Activity.class;
		}
		return classes;
	}

	public static Class<?> classesQuizView(int level) {
		Class<?> classes = null;
		if (level == 1) {
			classes = Quiz01Activity.class;
		} else if (level == 2) {
//			classes = Quiz02Activity.class;
		} else if (level == 3) {
//			classes = Quiz03Activity.class;
		} else if (level == 4) {
//			classes = Quiz04Activity.class;
		}
		return classes;
	}
	
}
