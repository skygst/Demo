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

public class VideoShow {

	private String id;
	private String name;
	private String pic_url;
	private String description;
	private List<Items> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	// 获得视频详情
	public static VideoShow getVideoShow(Context mContext, String volumeId) {
		String result = new CommonInterfaces().getDataFromUrlByGet(mContext,
				CommonAdressConstant.videoShow, "volume_id=" + volumeId);
		return parseVideoShow(result);
	}
	
	public static VideoShow parseVideoShow(String result) {
		if (result == null || result.equals("") || result.length() <= 0) {
			return null;
		}
		VideoShow videoShow = new VideoShow();
		try {
			JSONObject data = new JSONObject(result);
			Gson gson = new Gson();
			videoShow = gson.fromJson(data.toString(), VideoShow.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return videoShow;
	}

}
