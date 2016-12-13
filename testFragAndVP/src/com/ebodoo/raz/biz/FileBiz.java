package com.ebodoo.raz.biz;

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

}
