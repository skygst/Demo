package com.ebodoo.raz.utils;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gst.move.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.inputmethod.InputMethodManager;

/**
 * 存放公共方法
 * 
 * @author gst
 * 
 */
public class BaseCommon {
	
	public static String pathLevelBGame = Constant.sdcard_path
			+ "/raz_english/reaEbook03/";
	public static String pathLevelBGameImages = Constant.sdcard_path
			+ "/raz_english/reaEbook03/images/";

	// 保存当前选择的语言（中文、英语）
	public static void  spIsEg(Context context, boolean isEg, boolean isSet) {
		SharedPreferences spLanguage = context.getSharedPreferences(
				"is_english", 0); 
		SharedPreferences.Editor editor = spLanguage.edit();
		editor.putBoolean("is_eg", isEg);
		editor.putBoolean("is_set", isSet);
		editor.commit();
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

	public void spBabyInfo(Context context, String babyName, String babyAge, int sex) {
		SharedPreferences spBabyInfo = context.getSharedPreferences(
				"sp_babyInfo", 0);
		SharedPreferences.Editor editor = spBabyInfo.edit();
		editor.putString("baby_name", babyName);
		editor.putString("baby_age", babyAge);
		editor.putInt("baby_sex", sex);
		editor.commit();
	}

	// 获得当前宝宝信息
	public Object[] getBabyInfo(Context context) {
		Object[] obj = new Object[3];
		SharedPreferences spBabyInfo = context.getSharedPreferences(
				"sp_babyInfo", 0);
		String babyName = spBabyInfo.getString("baby_name", "");
		String babyAge = spBabyInfo.getString("baby_age", "");
		int babySex = spBabyInfo.getInt("baby_sex", 0);
		obj[0] = babyName;
		obj[1] = babyAge;
		obj[2] = babySex;
		return obj;
	}
	
	public void spSelectLevel(Context context, int level) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"sp_select_level", 0);
		SharedPreferences.Editor editor = spSelectLevel.edit();
		editor.putInt("select_level", level);
//		if(island == 0) {
//			editor.putInt("island1_level", level);
//		} else if(island == 1) {
//			editor.putInt("island2_level", level);
//		} else if(island == 2) {
//			editor.putInt("island3_level", level);
//		} else if(island == 3) {
//			editor.putInt("island4_level", level);
//		}
		editor.commit();
	}

	// 获得已经玩过的关卡
	public int getSelectLevel(Context context) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"sp_select_level", 0);
		int level = spSelectLevel.getInt("select_level", 0);
//		int level = 0;
//		if(island == 0) {
//			level = spSelectLevel.getInt("island1_level", 0);
//		} else if(island == 1) {
//			level = spSelectLevel.getInt("island2_level", 0);
//		} else if(island == 2) {
//			level = spSelectLevel.getInt("island3_level", 0);
//		} else if(island == 3) {
//			level = spSelectLevel.getInt("island4_level", 0);
//		}
		return level;
	}
	
	
	public void spIslandLevel(Context context, int island, int level) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"sp_finish_level", 0);
		SharedPreferences.Editor editor = spSelectLevel.edit();
		if(island == 0) {
			editor.putInt("island1_level", level);
		} else if(island == 1) {
			editor.putInt("island2_level", level);
		} else if(island == 2) {
			editor.putInt("island3_level", level);
		} else if(island == 3) {
			editor.putInt("island4_level", level);
		}
		editor.commit();
	}
	
	public void spIslandLevel(Context context, int level1,int level2,int level3,int level4) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"sp_finish_level", 0);
		SharedPreferences.Editor editor = spSelectLevel.edit();
		
		editor.putInt("island1_level", level1);
		editor.putInt("island2_level", level2);
		editor.putInt("island3_level", level3);
		editor.putInt("island4_level", level4);
		
		editor.commit();
	}
	// 获得已经玩过的关卡
//	public int getIslandLevel(Context context, int island) {
//		/*SharedPreferences spSelectLevel = context.getSharedPreferences(
//				"sp_finish_level", 0);
//		int level = 0;
//		if (island == 0) {
//			level = spSelectLevel.getInt("island1_level", 0);
//		} else if (island == 1) {
//			level = spSelectLevel.getInt("island2_level", 0);
//		} else if (island == 2) {
//			level = spSelectLevel.getInt("island3_level", 0);
//		} else if (island == 3) {
//			level = spSelectLevel.getInt("island4_level", 0);
//		}
//		return level;*/
//		return CommonSharePreferences.getIslandLevel(context,island);
//	}
	
//	public int getCurrentIsland(Context context) {
//		if(getIslandLevel(context, 0) < 4) {
//			return 0;
//		} else if(getIslandLevel(context, 1) < 4) {
//			return 1;
//		} else if(getIslandLevel(context, 2) < 5) {
//			return 2;
//		} else if(getIslandLevel(context, 3) < 6) {
//			return 3;
//		} else {
//			return 0;
//		}
//	}
	
//	public List<Integer> getFinishLevelPos (Context context) {
//		List<Integer> finishLevel = new ArrayList<Integer>();
//		finishLevel.addAll(cycleTraversal(context, 0, 4));
//		finishLevel.addAll(cycleTraversal(context, 1, 4));
//		finishLevel.addAll(cycleTraversal(context, 2, 5));
//		finishLevel.addAll(cycleTraversal(context, 3, 6));
//		return finishLevel;
//	}
	
	// 循环遍历
//	public List<Integer> cycleTraversal(Context context, int island, int num) {
//		List<Integer> finishLevel = new ArrayList<Integer>();
//		int length = getIslandLevel(context, island);
//		int index = 0;
//		if(island != 3) {
//			index = island * 3;
//		} else {
//			index = 10;
//		}
//		if(length > 0) {
//			if(length < num) {
//				for(int i=1; i<=length; i++) {
//					finishLevel.add(index + i - 1);
//				}
//			} else {
//				for(int i=1; i<num; i++) {
//					finishLevel.add(index + i - 1);
//				}
//			}
//		} 
//		return finishLevel;
//	}

	// $表示内部类的意思
	// 所以cn.testreflect.R$drawable表示:
	// drawable是cn.testreflect.R的内部类
	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public int getImageId(Context context, String imageName) {
		return getId(context, imageName, ".R$drawable");
	}

	public static int getImage(Context context, String imageName) {
		return getId2(context, imageName, ".R$drawable");
	}
	private static int getId2(Context context, String imageName, String type) {
		try {
			Field field = Class.forName(context.getPackageName() + type)
					.getField(imageName);
			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int getId(Context context, String imageName, String type) {
		try {
			Field field = Class.forName(context.getPackageName() + type)
					.getField(imageName);
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
	public static int getMp3Id(Context context, String mp3Name) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField(mp3Name);
			} else {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField(mp3Name + "_ch");
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

	public static int getEgGoodMp3Id(Context context) {
		try {
			Field field;
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("encourage_" + randData2(3));
			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getEgErrorMp3Id(Context context) {
		int resId= 0;
		try {
			int index = randData2(2);
			if(index == 1) {
				resId = R.raw.error_sorry;
			} else {
				resId = R.raw.error_try_again;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resId;
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
	public static int getMp3Id3(Context context, String mp3Name) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$raw")
						.getField("eg_" + mp3Name);
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
	public static String getMp3Path(String mp3Name) {
		String path = "";
		try {
			
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				path = "en_" + mp3Name;
			} else {
				path = "ch_" + mp3Name;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path+".mp3";
	}
	
	public static String getMp3Path2(String mp3Name) {
		String path = "";
		try {
			
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				path = "eg_" + mp3Name;
			} else {
				path = "ch_" + mp3Name;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path+".mp3";
	}
	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getOnlyImageViewId(Context context, String imageName) {
		try {
			Field field;
			field = Class.forName(context.getPackageName() + ".R$drawable")
					.getField(imageName);
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
	public static int getImageViewId(Context context, String imageName) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$drawable")
						.getField(imageName + "_en");
			} else {
				field = Class.forName(context.getPackageName() + ".R$drawable")
						.getField(imageName + "_ch");
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
	public static int getImageViewId2(Context context, String imageName) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$drawable")
						.getField("eg_" + imageName);
			} else {
				field = Class.forName(context.getPackageName() + ".R$drawable")
						.getField("ch_" + imageName);
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
	public static int getImageViewId3(Context context, String imageName) {
		try {
			Field field;
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				field = Class.forName(context.getPackageName() + ".R$drawable")
						.getField("en_" + imageName);
			} else {
				field = Class.forName(context.getPackageName() + ".R$drawable")
						.getField("ch_" + imageName);
			}
			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 */
	public void hideSoftKeyboard(Context context) {
		((InputMethodManager) context.getSystemService("input_method"))
				.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

	// 保存视频播放的当前位置
	public static void saveVideoCurrentPosition(Context context, int startTime,
			int endTime) {
		SharedPreferences spLanguage = context.getSharedPreferences(
				"is_english", 0);
		SharedPreferences.Editor editor = spLanguage.edit();
		editor.putInt("startTime", startTime);
		editor.putInt("endTime", endTime);
		editor.commit();
	}

	// 获得视频开始的时间
	public static int getVideoStartTime(Context context) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"is_english", 0);
		int level = spSelectLevel.getInt("startTime", 0);
		return level;
	}

	// 获得视频结束的时间
	public static int getVideoEndTime(Context context) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"is_english", 0);
		int level = spSelectLevel.getInt("endTime", 0);
		return level;
	}

	// 保存是否是从SuccessActivity跳到宝物箱
	public static void saveIsSuccessToBox(Context context,
			boolean isSuccessToBox) {
		SharedPreferences spLanguage = context.getSharedPreferences(
				"is_english", 0);
		SharedPreferences.Editor editor = spLanguage.edit();
		editor.putBoolean("isSuccessToBox", isSuccessToBox);
		editor.commit();
	}

	// 获得视频结束的时间
	public static boolean getIsSuccessToBox(Context context) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"is_english", 0);
		boolean level = spSelectLevel.getBoolean("isSuccessToBox", false);
		return level;
	}

	// 1/2/3/4
	public static int randData1_4() {
		Random random = new Random();
		return (random.nextInt(4) + 1);
	}

	public static int randData2(int num) {
		Random random = new Random();
		return (random.nextInt(num) + 1);
	}

	//随机 0~num-1之间的随机数据
	public static int randData(int num) {
		Random random = new Random();
		return (random.nextInt(num));
	}
	//随机 0~num之间的随机数据
	public static int randData0ToN(int num) {
		Random random = new Random();
		return (random.nextInt(num+1));
	}
	// 获取0~num-1之间的随机数，并且不等于compardNum
	public static int randData(int num, int compardNum) {
		Random random = new Random();
		int data = random.nextInt(num);
		if (data != compardNum) {
			return data;
		} else {
			return randData(num, compardNum);
		}
	}
	
	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getBaowuMp3Id(Context context, String mp3Name) {
		try {
			Field field;
			field = Class.forName(context.getPackageName() + ".R$raw")
					.getField(mp3Name);

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
	public static int getBaowuImageViewId(Context context, String imageName) {
		try {
			Field field;
			field = Class.forName(context.getPackageName() + ".R$drawable")
					.getField(imageName);

			return field.getInt(field);
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
	public int processValue(float fl) {
		fl = fl * 100;
		int value = Float.valueOf(fl).intValue();
		if (fl >= (value + 0.5)) {
			value++;
		}
		return value;
	}

	// 当前打开宝箱的状态
	public void spOpenBoxStatus(Context context, int openMaxLevel) {
		SharedPreferences sp = context.getSharedPreferences("open_box", 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("open_max_level", openMaxLevel);
		editor.commit();
	}

	public int getOpenBoxStatus(Context context) {
		SharedPreferences sp = context.getSharedPreferences("open_box", 0);
		int maxLevel = sp.getInt("open_max_level", 0);
		return maxLevel;
	}

	// 当前地图是否是第一次滑动
	public void spFirstMove(Context context, boolean isFirstMove) {
		SharedPreferences sp = context.getSharedPreferences("first_move", 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean("is_first_move", isFirstMove);
		editor.commit();
	}

	public boolean getFirstMove(Context context) {
		SharedPreferences sp = context.getSharedPreferences("first_move", 0);
		boolean isFirstMove = sp.getBoolean("is_first_move", true);
		return isFirstMove;
	}

	public boolean isNeedLoad(String land,String path) {
		boolean isNeedsLoad = false;
		String commonPath = Constant.sdcard_path
				+ "/raz_english/" +land + "/" + path;
		File destDir = new File(commonPath);
		if (!destDir.exists()) {
			isNeedsLoad = true;
		} else {
			isNeedsLoad = false;
		}
		return isNeedsLoad;
	}
	
	/**
	 * @param isLeft
	 *            是否是向左滑动
	 * @param currentIsland
	 *            当前岛的index(索引)
	 * @return
	 */
//	public int[] slidePic(int index, int trailerId) {
//		int[] imgIdArray = new int[4]; // { R.drawable.bg_m, Color.TRANSPARENT
//		if(index == 0) {
//			imgIdArray = new int[] { R.drawable.select_interface_1, R.drawable.select_interface_2, R.drawable.select_interface_3, R.drawable.select_interface_4, trailerId };
//		} else if(index == 1) {//第一个岛
//			imgIdArray = new int[] { Color.TRANSPARENT, R.drawable.select_interface_2, R.drawable.select_interface_3, R.drawable.select_interface_4, trailerId };
//		} else if(index == 2) {//第二个岛
//			imgIdArray = new int[] { R.drawable.select_interface_1, Color.TRANSPARENT, R.drawable.select_interface_3, R.drawable.select_interface_4, trailerId };
//		} else if(index == 3) {//第三个岛
//			imgIdArray = new int[] { R.drawable.select_interface_1, R.drawable.select_interface_2, Color.TRANSPARENT, R.drawable.select_interface_4, trailerId};
//		} else if(index == 4) {//第四个岛
//			imgIdArray = new int[] { R.drawable.select_interface_1, R.drawable.select_interface_2, R.drawable.select_interface_3, Color.TRANSPARENT, trailerId};
//		} else if(index == 5) {//预告
//			imgIdArray = new int[] { R.drawable.select_interface_1, R.drawable.select_interface_2, R.drawable.select_interface_3, R.drawable.select_interface_4, Color.TRANSPARENT };
//		}
//		return imgIdArray;
//	}
	
	public Bitmap readBitmap(Context context, int id){
	     BitmapFactory.Options opt = new BitmapFactory.Options();
	     opt.inPreferredConfig=Bitmap.Config.RGB_565;//表示16位位图 565代表对应三原色占的位数
	     opt.inInputShareable=true;
	     opt.inPurgeable=true;//设置图片可以被回收
	     InputStream is = context.getResources().openRawResource(id);
	     return BitmapFactory.decodeStream(is, null, opt);
	}  
	
	public Drawable readDrawable(Context context, int id) {
		Bitmap bmp = readBitmap(context, id);
		if(bmp != null) {
			Drawable drawable =new BitmapDrawable(bmp);
			return drawable;
		}
		return null;
	}

	public static int randData() {
		Random random = new Random();
		return random.nextInt(3)+1;
	}
	
	public static  int randData4() {
		Random random = new Random();
		return (random.nextInt(4))+1;
	}
	
	public String getChannelCode(Context context) {
		String code = getMetaData(context, "UMENG_CHANNEL");
		if (code != null) {
			return code;
		}
		return "C_000";
	}

	private String getMetaData(Context context, String key) {
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			Object value = ai.metaData.get(key);
			if (value != null) {
				return value.toString();
			}
		} catch (Exception e) {
		}
		return null;
	}
	
    /** 
     * 判断日期格式是否正确 
     */  
    public boolean IsDateFormat(String dataStr) {  
    	 boolean state = false;  
         try {  
         	if(dataStr != null && !dataStr.equals("")) {
         		java.text.SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");   
         		dFormat.setLenient(false);   
         		dFormat.parse(dataStr);  
         		state = true;  
         	}
         } catch (ParseException e) {  
             e.printStackTrace();  
             state = false;  
         }   
         return state;   
    }  
    
    // 获得宝宝的年龄(岁)
    public static int getAge(Context context) {
    	Object[] obj = new Object[3];
    	obj = new BaseCommon().getBabyInfo(context);
    	String birthday = obj[1].toString();
    	String currentTime = new DateUtil().getCurrentDate();
		int babyAge = 0;
		int year = 0;
		String[] birth;
		String[] current;
		if(birthday == null || birthday.equals("")) {
			return babyAge;
		}
		if (birthday.indexOf("/") == -1) {
			birth = birthday.split("-");
		} else {
			birth = birthday.split("/");
		}
		if (currentTime.indexOf("/") == -1) {
			current = currentTime.split("-");
		} else {
			current = currentTime.split("/");
		}

		// 将String类型转换成int类型
		int birth_day = Integer.parseInt(birth[2].trim());
		int birth_month = Integer.parseInt(birth[1].trim());
		int birth_year = Integer.parseInt(birth[0].trim());
		int current_day = Integer.parseInt(current[2].trim());
		int current_month = Integer.parseInt(current[1].trim());
		int current_year = Integer.parseInt(current[0].trim());
		if (birth_year > current_year
				|| (birth_year == current_year && birth_month > current_month)
				|| (birth_year == current_year && birth_month == current_month && birth_day > current_day)) {
			babyAge = -1;
		} else {
			if (birth_day <= current_day) {
				// 当前天大于等于出生天
				if (birth_month <= current_month) {
					// 当前年大于等于出生年
					year = current_year - birth_year;
				} else {
					year = current_year - 1 - birth_year;
				}
			} else {
				// 当前天小于出生天
				if (birth_month < current_month) {
					// 当前年大于等于出生年
					year = current_year - birth_year;
				} else {
					year = current_year - 1 - birth_year;
				}
			}
			babyAge = year;
		}
		return babyAge;
	}
    
//    public boolean isCanPlay(Context context, int currentIsland, int currentLevel) {
//		boolean isCanPlay = false;
//		int level = getIslandLevel(context, currentIsland);
//		if(currentLevel > (level + 1)) {
//			isCanPlay = false;
//		} else {
//			isCanPlay = true;
//		}
//		/*if(currentIsland == 3) {
//			level = currentIsland*4 + clickNum + 1;
//		} else {
//			level = currentIsland*4 + clickNum;
//		}
//		if(currentLevel >= level) {
//			//可以播放
//			isCanPlay = true;
//		} else {
//			if((level - currentLevel) == 1) {
//				// 可以播放
//				isCanPlay = true;
//			} else {
//				// 不可以播放
//				isCanPlay = false;
//			}
//		}*/
//		return isCanPlay;
//	}
    
    /**
	 * 通过MD5生成sign(签名)
	 * 
	 * @param input
	 * @return
	 */
	public String MD5HexDigest(String input) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return toHex(md5.digest(input.getBytes()));
		// return toHex(md5.digest(input.getBytes("utf-8")));
	}

	private String toHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (int i = 0; i < a.length; i++) {
			sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(a[i] & 0x0f, 16));
		}
		return sb.toString();
	}
	
	public MediaPlayer playMusic(MediaPlayer mMediaPlayer, String path,
			boolean loop) {
		try {
			if (mMediaPlayer != null) { // 一定要清空播放器资源
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			mMediaPlayer = new MediaPlayer();
			/* 重置MediaPlayer */
			mMediaPlayer.reset();
			/* 设置要播放的文件的路径 */
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.setLooping(loop);
			/* 准备播放 */
			mMediaPlayer.prepare();
			/* 开始播放 */
			mMediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mMediaPlayer;
	}
	
//	public Bitmap getPropThumnail(int id, Context mContext){  
//    	Resources r = mContext.getResources();
//    	Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
//    		    + r.getResourcePackageName(id) + "/"
//    		    + r.getResourceTypeName(id) + "/"
//    		    + r.getResourceEntryName(id));
//        Bitmap b = ImageCacheUtil.getResizedBitmap(null, null,   
//        		mContext, uri, 70, false);  
//        /*int w = mContext.getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);  
//        int h = mContext.getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);  */
//        int w = b.getWidth();
//        int h = b.getHeight();
//        Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);  
//          
//        return thumBitmap;  
//    }  
	
	// 判断字符串是否为数字
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static int getSDKVersionNumber() {  
	    int sdkVersion;  
	    try {  
	        sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);  
	    } catch (NumberFormatException e) {  
	        sdkVersion = 0;  
	    }  
	    return sdkVersion;  
	}  
	
	/**
	 * 随机排序
	 * @param list 要进行随机排序的数据集合
	 * @return 排序结果
	 */
	public static int[] getList(int[] list){
		//数组长度
		int count = list.length;
		//结果集
		int[] resultList = new int[count];
		//用一个LinkedList作为中介
		LinkedList<Integer> temp = new LinkedList<Integer>();
		//初始化temp
		for(int i = 0; i<count; i++){
			temp.add((Integer)list[i]);
		}
		//取数
		Random rand = new Random();
		for(int i = 0;i<count; i++){
			int num = rand.nextInt(count - i);
			resultList[i] = (Integer) temp.get(num);
			temp.remove(num);
		}
		
		return resultList;
	}
	
	public static int[] getList(int numValue){
		int[] list = new int[numValue];
		for(int i=0; i<numValue; i++) {
			list[i] = i;
		}
		return getList(list);
	}

	// 获取0-（numValue-1）并且出去delNum的随机数
	public static int[] getList(int numValue, int delNum) {
		int[] list = new int[numValue - 1];
		for (int i = 0; i < delNum; i++) {
			list[i] = i;
		}
		if (delNum < (numValue - 1)) {
			for (int i = delNum; i < numValue - 1; i++) {
				list[i] = i + 1;
			}
		}
		return getList(list);
	}
	
	// 0-n随机排序
	public static Integer[] getRandomId(int n) {
		Integer[] arryRandom = new Integer[n];
		for (int i = 0; i < n; i++)
			arryRandom[i] = i;
		List list = Arrays.asList(arryRandom);
		Collections.shuffle(list);
		return arryRandom;
	}
	
	// 获得随机排序后的数组的前num个数组
	public static int[] interceptionArray(int arrayLength, int num){
		int[] randArray = getList(arrayLength);
		int[] printArray = new int[num];
		for(int i=0; i<num; i++) {
			printArray[i] = randArray[i];
		}
		return printArray;
	}
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	/**
	 * 获取系统日期
	 */
	public static String getDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");    
		String date=sdf.format(new java.util.Date());   
		return date;
	}
	
//	public static void gotoBuyVip(Context context, boolean isLogin) {
//		if (isLogin) {
//			context.startActivity(new Intent(context, BuyVipActivity.class)
//					.putExtra("status", 0));
//		} else {
//			context.startActivity(new Intent(context, SettingsActivity.class)
//					.putExtra("index", 3).putExtra("basics", false));
//		}
//	}
//	
//	public static boolean vipStatus(Context context, int index) {
//		boolean epIsBuy = new CacheSp().getBuy(context, "" + index);
//		if(!epIsBuy) {
//			epIsBuy = new PaymentInterface().paymentInfo(context, index);
//			if(epIsBuy) {
//				new CacheSp().spBuy(context, "" + index);
//			}
//		}
//		return epIsBuy;
//	}
//	
//	// 友盟在线参数:控制是否可以静默下载
//	public boolean UmConfigSilentDownloadParams(Context context) {
//		boolean isCanDownload = false;
//		// TODO doctorCall是需要重新设定的
//		MobclickAgent.updateOnlineConfig(context);  
//		String downloadStatus = MobclickAgent.getConfigParams(context,
//				"enableSilentDownload");
//		System.out.println("downloadStatus :" + downloadStatus);
//		if (downloadStatus != null && !downloadStatus.equals("")
//				&& downloadStatus.equals("YES")) { // 可以使用静默下载
//			isCanDownload = true;
//		}
//		return isCanDownload;
//	}
	public static SpannableString getSpanString(String str,Context mContext,int start,int end){
		SpannableString spanContent = new SpannableString(str); 
		spanContent.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.red)),start,end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); 
		return spanContent;
	}
	
	// 判断当前手机系统版本是否是5.0.2
	public static boolean versionRelease() {
		boolean versionHigh = false;
		String versionRelease = Build.VERSION.RELEASE; // 设备的系统版本
		int version = Build.VERSION.SDK_INT;
		if (versionRelease != null && !versionRelease.equals("")
				&& versionRelease.equals("5.0.2") || version > 21) {
			versionHigh = true;
		}
		return versionHigh;
	}
	
	public static String mp3Path(Context context, String mp3Name) {
		String path = "";
		if(getIsEg(context)) {
			path = "eg_" + mp3Name;
		} else {
			path = "ch_" + mp3Name;
		}
		return path;
	}
	
	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getPicId(Context context, int index) {
		try {
			if(index == 0){
				return selectLetters(context,"zm_a1","zm_a2");
			}else if(index == 1){
				return selectLetters(context,"zm_b1","zm_b2");
			}else if(index == 2){
				return selectLetters(context,"zm_c1","zm_c2");
			}else if(index == 3){
				return selectLetters(context,"zm_d1","zm_d2");
			}else if(index == 4){
				return selectLetters(context,"zm_e1","zm_e2");
			}else if(index == 5){
				return selectLetters(context,"zm_f1","zm_f2");
			}else if(index == 6){
				return selectLetters(context,"zm_g1","zm_g2");
			}else if(index == 7){
				return selectLetters(context,"zm_h1","zm_h2");
			}else if(index == 8){
				return selectLetters(context,"zm_i1","zm_i2");
			}else if(index == 9){
				return selectLetters(context,"zm_j1","zm_j2");
			}else if(index == 10){
				return selectLetters(context,"zm_k1","zm_k2");
			}else if(index == 11){
				return selectLetters(context,"zm_l1","zm_l2");
			}else if(index == 12){
				return selectLetters(context,"zm_m1","zm_m2");
			}else if(index == 13){
				return selectLetters(context,"zm_n1","zm_n2");
			}else if(index == 14){
				return selectLetters(context,"zm_o1","zm_o2");
			}else if(index == 15){
				return selectLetters(context,"zm_p1","zm_p2");
			}else if(index == 16){
				return selectLetters(context,"zm_q1","zm_q2");
			}else if(index == 17){
				return selectLetters(context,"zm_r1","zm_r2");
			}else if(index == 18){
				return selectLetters(context,"zm_s1","zm_s2");
			}else if(index == 19){
				return selectLetters(context,"zm_t1","zm_t2");
			}else if(index == 20){
				return selectLetters(context,"zm_u1","zm_u2");
			}else if(index == 21){
				return selectLetters(context,"zm_v1","zm_v2");
			}else if(index == 22){
				return selectLetters(context,"zm_w1","zm_w2");
			}else if(index == 23){
				return selectLetters(context,"zm_x1","zm_x2");
			}else if(index == 24){
				return selectLetters(context,"zm_y1","zm_y2");
			}else if(index == 25){
				return selectLetters(context,"zm_z1","zm_z2");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private static int selectLetters(Context context,String bigStr, String smallStr){
		Field field;
		int i = randData(2);
		try {
			if(i == 0){
				field = Class.forName(context.getPackageName() + ".R$drawable")
						.getField(bigStr);
			}else{
				field = Class.forName(context.getPackageName() + ".R$drawable")
						.getField(smallStr);
			}
			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 卡片随机
	public static void rand_n(int index[], int len) { // 打乱table中各个元素之间的顺序
		Random rdm = new Random(System.currentTimeMillis());
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < index.length; j++) {
				int random = Math.abs(rdm.nextInt()) % len;
				int c = index[j];
				index[j] = index[random];
				index[random] = c;
			}
		}
	}
	
	public static String countryMp4(int currentIsland, int index, int pos) {
		String mp4 = "";
		if (currentIsland == 0) { // 欧洲
			if (index == 0) {
//				mp4 = "ep_level2_0.mp4";
				if(pos == 1) {
					mp4 = countryDownLoaderMp4("levelEurope", 1, pos);
				} else {
					mp4 = countryDownLoaderMp4("ep_level", 1, pos);
				}
			} else if (index == 1) {
//				mp4 = "ep_level4_0.mp4";
				if(pos == 1) {
					mp4 = countryDownLoaderMp4("levelEurope", 3, pos);
				} else {
					mp4 = countryDownLoaderMp4("ep_level", 3, pos);
				}
			} else if (index == 2) {
//				mp4 = "ep_level3_0.mp4";
				if(pos == 1) {
					mp4 = countryDownLoaderMp4("ep_level_", 2, pos);
				} else {
					mp4 = countryDownLoaderMp4("ep_level", 2, pos);
				}
			} else if (index == 3) {
//				mp4 = "ep_level1_0.mp4";
				if (pos == 1) {
					mp4 = countryDownLoaderMp4("levelEurope", 0, pos);
				} else {
					mp4 = countryDownLoaderMp4("ep_level", 0, pos);
				}
			} else if (index == 4) {
//				mp4 = "ep_level5_0.mp4";
				if (pos == 1) {
					mp4 = countryDownLoaderMp4("levelEurope", 4, pos);
				} else {
					mp4 = countryDownLoaderMp4("ep_level", 4, pos);
				}
			}
		} else if (currentIsland == 1) { // 非洲
			mp4 = countryDownLoaderMp4("africa_level", index, pos);
		} else if(currentIsland == 2) { // 亚洲
			mp4 = countryDownLoaderMp4("asia_level", index, pos);
		} else if(currentIsland == 3) {
//			mp4 = countryDownLoaderMp4("asia_level", index, pos);
		}
		return mp4;
	}
	
	private static String countryDownLoaderMp4(String name, int index,  int pos) {
		String mp4 = name + (index + 1) + "_" + pos + ".mp4";
		return mp4;
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
	
	public static Drawable drawableChange(String path, String name) {
		Drawable drawable = Drawable.createFromPath(path + name + ".png");
		return drawable;
	}
	
}
