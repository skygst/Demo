package com.example.location.biz;

import java.io.File;

public class FileBiz {

	// 判断文件是否存在
	public static boolean isExists(String path) {
		try {
			File file = new File(path);
			if (file.exists()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 判断文件夹是否为空
	public static boolean folderIsEmpty(String path) {
		try {
			File file = new File(path);
			if (file.exists()) {
				File[] listFiles = file.listFiles();
				if(listFiles.length > 0){ //文件夹下有文件
					return true;
				} else { //文件夹下没有文件
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
