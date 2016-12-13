package com.gst.move.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Environment;

public class BaseCommon {

	public static String path_game = Environment.getExternalStorageDirectory()
			+ "/raz_english/reaEbook02/";

	public static String path_gameImages = Environment
			.getExternalStorageDirectory() + "/raz_english/reaEbook02/images/";

	public static String pathLevelBGame = Environment
			.getExternalStorageDirectory() + "/raz_english/reaEbook03/";
	public static String pathLevelBGameImages = Environment
			.getExternalStorageDirectory() + "/raz_english/reaEbook03/images/";
	public static String path_raz_image = com.ebodoo.raz.utils.Constant.sdcard_path
			+ "/raz_english/raz_card_video/images/";
	public static String path_raz_mp3 = com.ebodoo.raz.utils.Constant.sdcard_path
			+ "/raz_english/raz_card_video/mp3/";

	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getMp3Id(Context context, String mp3Name) {
		try {
			/*
			 * Field field; if (Constant.languageType ==
			 * Constant.LANGUAGE_ENGLISH) { field =
			 * Class.forName(context.getPackageName() + ".R$raw")
			 * .getField(mp3Name); } else { field =
			 * Class.forName(context.getPackageName() + ".R$raw")
			 * .getField(mp3Name + "_ch"); } return field.getInt(field);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param animName
	 * @return
	 */
	public static int getAnimViewId(Context context, String animName) {
		try {
			Field field;
			field = Class.forName(context.getPackageName() + ".R$anim")
					.getField(animName);

			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getFuxiGoodMp3Id(Context context) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("good_" + randData1_4());
			} else {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("ts_good_" + randData1_4());
			}
			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getFuxiErrorMp3Id(Context context) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("error_sorry");
			} else {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("ts_error_" + randData1_4());
			}
			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 1/2/3/4
	public static int randData1_4() {
		Random random = new Random();
		return (random.nextInt(4) + 1);
	}

	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getMp3Id2(Context context, String mp3Name) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("en_" + mp3Name);
			} else {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("ch_" + mp3Name);
			}
			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getMp3Id4(Context context, String mp3Name) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField(mp3Name);
			} else {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("ch_" + mp3Name);
			}
			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 随机排序
	 * 
	 * @param list
	 *            要进行随机排序的数据集合
	 * @return 排序结果
	 */
	public static int[] getList(int[] list) {
		// 数组长度
		int count = list.length;
		// 结果集
		int[] resultList = new int[count];
		// 用一个LinkedList作为中介
		LinkedList<Integer> temp = new LinkedList<Integer>();
		// 初始化temp
		for (int i = 0; i < count; i++) {
			temp.add((Integer) list[i]);
		}
		// 取数
		Random rand = new Random();
		for (int i = 0; i < count; i++) {
			int num = rand.nextInt(count - i);
			resultList[i] = (Integer) temp.get(num);
			temp.remove(num);
		}

		return resultList;
	}

	public static Drawable drawableChange(String path, String name) {
		Drawable drawable = Drawable.createFromPath(path + name + ".png");
		return drawable;
	}

	// 获得当前选择的语言（中文、英语）
	public static boolean getIsEg(Context context) {
		SharedPreferences spLanguage = context.getSharedPreferences(
				"is_english", 0);
		boolean isSet = spLanguage.getBoolean("is_set", false);
		boolean isEg = false;
		if (isSet) {
			// 已经设置过
			isEg = spLanguage.getBoolean("is_eg", false);
		} else {
			// 未设置过
			String able = context.getResources().getConfiguration().locale
					.getLanguage();
			if (able != null && !able.equals("") && able.equals("zh")) {
				// 中文
				isEg = false;
			} else {
				// 英文
				isEg = true;
			}
			spIsEg(context, isEg, true);
		}
		return isEg;
	}

	// 保存当前选择的语言（中文、英语）
	public static void spIsEg(Context context, boolean isEg, boolean isSet) {
		SharedPreferences spLanguage = context.getSharedPreferences(
				"is_english", 0);
		SharedPreferences.Editor editor = spLanguage.edit();
		editor.putBoolean("is_eg", isEg);
		editor.putBoolean("is_set", isSet);
		editor.commit();
	}

	public static String mp3Path(Context context, String mp3Name) {
		String path = "";
		if (getIsEg(context)) {
			path = "eg_" + mp3Name;
		} else {
			path = "ch_" + mp3Name;
		}
		return path;
	}

	// 获取0~(num-1)之间的随机数，并且不等于compardNum
	public static int randData(int num, int compardNum) {
		Random random = new Random();
		int data = random.nextInt(num);
		if (data != compardNum) {
			return data;
		} else {
			return randData(num, compardNum);
		}
	}

	public static int randData(int num, int compardNum, int num2) {
		Random random = new Random();
		int data = random.nextInt(num);
		if (data != compardNum && data != num2) {
			return data;
		} else {
			return randData(num, compardNum, num2);
		}
	}

	public static int[][] customRand(int pos) {
		int[] cardPos = null;
		int[] sentencePos = null;
		if (pos == 0) {
			cardPos = new int[] { 0, 3, 2, 1 };
			sentencePos = new int[] { 0, 1, 2, 3 };
		} else if (pos == 1) {
			cardPos = new int[] { 1, 0, 2, 3 };
			sentencePos = new int[] { 1, 2, 0, 3 };
		} else if (pos == 2) {
			cardPos = new int[] { 2, 1, 0, 3 };
			sentencePos = new int[] { 1, 2, 0, 3 };
		} else if (pos == 3) {
			cardPos = new int[] { 3, 1, 2, 0 };
			sentencePos = new int[] { 2, 1, 3, 0 };
		}
		int[][] random = new int[2][4];
		random[0] = cardPos;
		random[1] = sentencePos;
		return random;

	}
	
	public static String minutesAndSeconds(int time) {
	    SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");//初始化Formatter的转换格式。  
	    String ms = formatter.format(time);  
	    return ms;
	}
	
	/**
	 * 判断字符串是否是数字(0.0)
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		char[] p = str.toCharArray();
		for (int i = 0; i < p.length; i++) {
			if (!isNum("" + p[i])) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9.]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
