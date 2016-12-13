package com.ebodoo.raz.base;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.ebodoo.raz.server.CommonAdressConstant;
import com.ebodoo.raz.server.CommonInterfaces;
import com.ebodoo.raz.server.InteractingWithServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class WearEarMusic {

	private String url;
	private String name;
	private String id;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static List<List<WearEarMusic>> earMusicData() {
		String url = CommonAdressConstant.share_baseurl + CommonAdressConstant.earVideo;
		String result = InteractingWithServer.getData(url);//(url, params);
		System.out.println("result 列表 :" + result);
		return parseEarMusic(result);
	}
	
	// 获取收藏列表数据
	public static List<WearEarMusic> collectionData(Context context) {
		String result = new CommonInterfaces().getDataFromUrlByGet(context, CommonAdressConstant.favList, "");
//		System.out.println("collectionAddData collectionData :" + result);
		return parseCollectionEarMusic(result);
	}
	
	// 添加收藏 --- 取消收藏
	public static List<WearEarMusic> collectionData(Context context, boolean isAdd, String audioId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("audio_id", audioId));
		String objectPath = CommonAdressConstant.favAdd;
		if(!isAdd) {
			objectPath = CommonAdressConstant.favDel;
		}
		String result = new CommonInterfaces().getDataFromUrlByPost(context, objectPath, params);
//		System.out.println("collectionAddData result :" + result);
		return parseCollectionEarMusic(result);
	}

	public static List<List<WearEarMusic>> parseEarMusic(String result) {
		if (result == null || result.equals("") || result.length() <= 0) {
			return null;
		}
		List<List<WearEarMusic>> listEar = new ArrayList<List<WearEarMusic>>();
		List<WearEarMusic> listPrimary = new ArrayList<WearEarMusic>();
		List<WearEarMusic> listAdvanced = new ArrayList<WearEarMusic>();
		try {
			JSONObject data = new JSONObject(result);
			JSONObject list = data.getJSONObject("list");
			JSONArray primary = list.getJSONArray("primary");
			JSONArray advanced = list.getJSONArray("advanced");
			
			Gson gson = new Gson();
			Type listType = new TypeToken<LinkedList<WearEarMusic>>() {
			}.getType();
			LinkedList<WearEarMusic> primaryLinked = gson.fromJson(primary.toString(),
					listType);
			for (Iterator<WearEarMusic> iterator = primaryLinked.iterator(); iterator
					.hasNext();) {
				WearEarMusic wearEarMusic = iterator.next();
				listPrimary.add(wearEarMusic);
			}
			
			LinkedList<WearEarMusic> advancedLinked = gson.fromJson(advanced.toString(),
					listType);
			for (Iterator<WearEarMusic> iterator = advancedLinked.iterator(); iterator
					.hasNext();) {
				WearEarMusic wearEarMusic = iterator.next();
				listAdvanced.add(wearEarMusic);
			}
			
			listEar.add(listPrimary);
			listEar.add(listAdvanced);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEar;
	}
	
	public static List<WearEarMusic> parseCollectionEarMusic(String result) {
		if (result == null || result.equals("") || result.length() <= 0) {
			return null;
		}
		List<WearEarMusic> listEar = new ArrayList<WearEarMusic>();
		try {
			JSONObject data = new JSONObject(result);
			JSONArray favorites = data.optJSONArray("favorites");
			Gson gson = new Gson();
			Type listType = new TypeToken<LinkedList<WearEarMusic>>() {
			}.getType();
			LinkedList<WearEarMusic> primaryLinked = gson.fromJson(favorites.toString(),
					listType);
			for (Iterator<WearEarMusic> iterator = primaryLinked.iterator(); iterator
					.hasNext();) {
				WearEarMusic wearEarMusic = iterator.next();
				listEar.add(wearEarMusic);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEar;
	}

}
