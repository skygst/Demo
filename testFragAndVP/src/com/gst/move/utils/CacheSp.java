package com.gst.move.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CacheSp {

	// 记录视频刷新状态
	public void spSetVideoStatus(Context context, String id, String time) {
		SharedPreferences spVideoStatus = context.getSharedPreferences(
				"sp_video_status", 0);
		SharedPreferences.Editor editor = spVideoStatus.edit();
		editor.putString(id, time);
		editor.commit();
	}

	public String spGetVideoStatus(Context context, String id) {
		SharedPreferences spVideoStatus = context.getSharedPreferences(
				"sp_video_status", 0);
		String refreshTime = spVideoStatus.getString(id, "");
		return refreshTime;
	}

	// 存储我的录音数据
	public void spSetAutoData(Context context, String result) {
		SharedPreferences spVideoStatus = context.getSharedPreferences(
				"sp_auto_data", 0);
		SharedPreferences.Editor editor = spVideoStatus.edit();
		editor.putString("data", result);
		editor.commit();
	}

	public String spGetAutoData(Context context) {
		SharedPreferences spVideoStatus = context.getSharedPreferences(
				"sp_auto_data", 0);
		String result = spVideoStatus.getString("data", "");
		return result;
	}

	private SharedPreferences commonSp(Context context, String spName) {
		SharedPreferences preferences = context.getSharedPreferences(
				spName, Activity.MODE_PRIVATE);
		return preferences;
	}

	// 磨耳大声说列表缓存
	public void spSetSpeakLoudly(Context context, String result) {
		SharedPreferences preferences = commonSp(context, "speak_loudly");
		SharedPreferences.Editor edit = preferences.edit();
		edit.putString("result", result);
		edit.commit();
	}

	public String spGetSpeakLoudly(Context context) {
		SharedPreferences preferences = commonSp(context, "speak_loudly");
		String result = preferences.getString("result", "");
		return result;
	}
	
}
