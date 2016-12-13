package com.ebodoo.raz.base;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.ebodoo.raz.server.CommonAdressConstant;
import com.ebodoo.raz.server.CommonInterfaces;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class VideoBase {

	private String name;
	private String updated_at;
	private List<Volumes> volumes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public List<Volumes> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volumes> volumes) {
		this.volumes = volumes;
	}

	// 获得视频列表
	public static List<VideoBase> getVideoList(Context mContext) {
		String result = new CommonInterfaces().getDataFromUrlByGet(mContext,
				CommonAdressConstant.videoList, "");
		return parseVideoBase(result);
	}

	public static List<VideoBase> parseVideoBase(String result) {
		if (result == null || result.equals("") || result.length() <= 0) {
			return null;
		}
		List<VideoBase> listVideo = new ArrayList<VideoBase>();
		try {
			JSONObject data = new JSONObject(result);
			JSONArray list = data.optJSONArray("list");
			if (list != null && list.length() > 0) {
				Gson gson = new Gson();
				Type listType = new TypeToken<LinkedList<VideoBase>>() {
				}.getType();
				LinkedList<VideoBase> primaryLinked = gson.fromJson(
						list.toString(), listType);
				for (Iterator<VideoBase> iterator = primaryLinked.iterator(); iterator
						.hasNext();) {
					VideoBase videoBase = iterator.next();
					listVideo.add(videoBase);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listVideo;
	}

}
