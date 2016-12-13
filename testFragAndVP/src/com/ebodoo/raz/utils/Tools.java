package com.ebodoo.raz.utils;

import java.io.File;
import java.util.Locale;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;

/**
 * @author hfy
 * rk30sdk-----步步高的平板
 *判断设备是否延迟了一些时间，小米手机往往延迟播放了
 */

public class Tools {
	
	
	public static boolean isDelay(){
		boolean isDelay = false;
		String iphonetype = android.os.Build.MODEL;
		if(iphonetype.contains("MI") || iphonetype.contains("Coolpad")){
			isDelay = true;
		}
		return isDelay;
	}
	
	public static int delayTime(){
		String iphonetype = android.os.Build.MODEL;
		if(iphonetype.contains("K1")){
			return 400;
		}else if(iphonetype.contains("rk")){
			return 400;
		}
		return 0;
	}
	
	public static String getDeviceType(){
		return android.os.Build.MODEL;
	}
	/**
	 * @author hfy
	 *判断设备使用的是什么语言
	 */	
	
	public static int getIphoneLanguageType(){
		String  str = Locale.getDefault().getLanguage();
		if(str.equals("zh")){
			return Constant.LANGUAGE_CHINESE;
		}else{
			return Constant.LANGUAGE_ENGLISH;
		}
	}
	// SD卡剩余空间
	public static long getSDFreeSize() {
		// 取得SD卡文件路径
		File path = new File(Constant.sdcard_path);
		StatFs sf = new StatFs(path.getPath());
		// 获取单个数据块的大小(Byte)
		long blockSize = sf.getBlockSize();
		// 空闲的数据块的数量
		long freeBlocks = sf.getAvailableBlocks();
		// 返回SD卡空闲大小
		// return freeBlocks * blockSize; //单位Byte
		// return (freeBlocks * blockSize)/1024; //单位KB
		return (freeBlocks * blockSize) / 1024 / 1024; // 单位MB
	}
	// 判断是否有sd卡，并判断SD卡的空间是否有50M，如果没有，都判断为没有SD卡
	public static boolean sdCard() {
		File sdcardDir = new File(Constant.sdcard_path);
		boolean isSDCard;
		if (sdcardDir.exists()) {
			long capacity = getSDFreeSize();
			if (capacity > 400) {
				isSDCard = true;
			} else {
				isSDCard = false;
			}
		} else {
			isSDCard = false;
		}
		return isSDCard;
	}
	
	public void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		} else {
			//文件不存在
		}
	}
	public static String getDeviceId(Context mcontxt) {
		TelephonyManager telephonyManager = (TelephonyManager) mcontxt
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}	
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
}
