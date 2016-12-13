package com.ebodoo.raz.base;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ebodoo.raz.server.InteractingWithServer;
import com.ebodoo.raz.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gst.move.model.CommonUtil;

import android.content.Context;

public class MvVideo {

	private String id;
	private String name;
	private String pic_url;
	private String video_url;

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

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	
	public void videoData(Context context) {
		String url = Constant.mvVideo;
		String result = InteractingWithServer.getData(url);
		parseVideoData(context, result);
	}
	
	private void parseVideoData(Context context, String result) {
		if(result == null || result.equals("")) {
			return;
		}
		List<MvVideo> arrayList = new ArrayList<MvVideo>();
		try {
			Type listType = new TypeToken<LinkedList<MvVideo>>() {
			}.getType();
			Gson gson = new Gson();
			JSONObject data = new JSONObject(result);
			JSONArray items = data.getJSONArray("items");
			String jsonTopThreadsDataString = items.toString();
			LinkedList<MvVideo> videoTopList = gson.fromJson(
					jsonTopThreadsDataString, listType);
			for (Iterator<MvVideo> iterator = videoTopList
					.iterator(); iterator.hasNext();) {
				MvVideo forumOrdinaryTopThreadsList = iterator.next();
				arrayList.add(forumOrdinaryTopThreadsList);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(arrayList != null && arrayList.size() > 0) {
			String strUrlPath = "";
			for(int i=0; i<arrayList.size(); i++) {
				strUrlPath += arrayList.get(i).getVideo_url();
				if(i != (arrayList.size() - 1)) {
					strUrlPath += ",";
				}
			}
			if(strUrlPath != null && !strUrlPath.equals("")) {
				CommonUtil.spSetMvPath(context, strUrlPath);
			}
		}
	}

}
