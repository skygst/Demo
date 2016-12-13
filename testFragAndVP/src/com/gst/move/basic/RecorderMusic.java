package com.gst.move.basic;

import android.content.Context;

import com.ebodoo.raz.server.CommonAdressConstant;
import com.ebodoo.raz.server.CommonInterfaces;
import com.ebodoo.raz.server.InteractingWithServer;
import com.example.location.biz.StringBiz;
import com.google.gson.Gson;
import com.gst.move.utils.CacheSp;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecorderMusic {

	private String errCode;
	private String msg;
	private String next_cursor;
	private String next_cursor_str;
	private String previous_cursor;
	private String previous_cursor_str;
	private List<Recorder> list;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Recorder> getList() {
		return list;
	}

	public void setList(List<Recorder> list) {
		this.list = list;
	}

	public String getNext_cursor() {
		return next_cursor;
	}

	public void setNext_cursor(String next_cursor) {
		this.next_cursor = next_cursor;
	}

	public String getNext_cursor_str() {
		return next_cursor_str;
	}

	public void setNext_cursor_str(String next_cursor_str) {
		this.next_cursor_str = next_cursor_str;
	}

	public String getPrevious_cursor() {
		return previous_cursor;
	}

	public void setPrevious_cursor(String previous_cursor) {
		this.previous_cursor = previous_cursor;
	}

	public String getPrevious_cursor_str() {
		return previous_cursor_str;
	}

	public void setPrevious_cursor_str(String previous_cursor_str) {
		this.previous_cursor_str = previous_cursor_str;
	}

	public RecorderMusic getAudioMusicData(Context context, String filter, String cursor) {
		String result = InteractingWithServer.getData(new CommonInterfaces()
				.getUrlForGet(context, CommonAdressConstant.audioList, "filter=" + filter + "&cursor=" + cursor));
		System.out.println("getAudioMusicData result :" + result);
		return parseListAudioMusic(context, result);
	}

	// 获得单本书的数据
	public RecorderMusic getOnceBookAudioMusicData(Context context, String filter, String bookType, String bookId, String cursor) {
		String result = InteractingWithServer.getData(new CommonInterfaces()
				.getUrlForGet(context, CommonAdressConstant.audiosBook, "filter=" + filter + "&book_type=" + bookType
						+ "&book_id=" + bookId + "&cursor=" + cursor));
		System.out.println("getAudioMusicData result :" + result);
		if(result == null) {
			result = new CacheSp().spGetAutoData(context);
			return parseListAudioMusic(context, result);
		} else {
			new CacheSp().spSetAutoData(context, result);
		}
		return parseListAudioMusic(context, result);
	}

	public String getAudioDel(Context context, String audioId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_audio_ids", audioId));
		String result = new CommonInterfaces().getDataFromUrlByPost(context,
				CommonAdressConstant.audiosDelete, params);
		System.out.println("-----------getAudioDel result :" + result);
		return parseAudioDel(result);
	}

	// 提交点评
	public RecorderMusic getAudioApplyReview(Context context, String audioId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_audio_id", audioId));
		String result = new CommonInterfaces().getDataFromUrlByPost(context,
				CommonAdressConstant.audiosApplyReview, params);
		System.out.println("-----------getAudioApplyReview result :" + result);
		return parseComment(context, result);
	}

	// 取消点评
	public RecorderMusic getAudioApplyCancel(Context context, String audioId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_audio_id", audioId));
		String result = new CommonInterfaces().getDataFromUrlByPost(context,
				CommonAdressConstant.audiosApplyCancel, params);
		System.out.println("-----------getAudioApplyCancel result :" + result);
		// return parseAudioDel(result);
		return parseComment(context, result);
	}

	private String parseAudioDel(String result) {
		if (result == null || result.equals("")) {
			return null;
		}
		try {
			JSONObject data = new JSONObject(result);
			String errorCode = data.optString("error_code");
			if(!StringBiz.isEmpty(errorCode)) {
				return data.optString("error");
			} else {
				return data.optString("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private RecorderMusic parseComment(Context context, String result) {
		if (result == null || result.equals("")) {
			return null;
		}
		RecorderMusic audioMusic = new RecorderMusic();
		try {
			JSONObject data = new JSONObject(result);
			String errorCode = data.optString("error_code");
			if(!StringBiz.isEmpty(errorCode)) {
				audioMusic.setMsg(data.optString("error"));
				audioMusic.setErrCode(errorCode);
				return audioMusic;
			} else {
				List<Recorder> listRecorder = new ArrayList<Recorder>();
				Gson gson = new Gson();
				Recorder recorder = gson.fromJson(data.toString(), Recorder.class);
				listRecorder.add(recorder);
				audioMusic.setList(listRecorder);
				return audioMusic;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private RecorderMusic parseListAudioMusic(Context context, String result) {
		if (result == null || result.equals("")) {
			return null;
		}
		RecorderMusic audioMusic = new RecorderMusic();
		try {
			JSONObject data = new JSONObject(result);
			String errorCode = data.optString("error_code");
			if(!StringBiz.isEmpty(errorCode)) {
				audioMusic.setMsg(data.optString("error"));
				audioMusic.setErrCode(errorCode);
				return audioMusic;
			} else {
				JSONArray listArray = data.optJSONArray("list");
				if (listArray != null && listArray.length() > 0) {
					List<Recorder> listRecorder = new ArrayList<Recorder>();
					Gson gson = new Gson();
					for (int i = 0; i < listArray.length(); i++) {
						JSONObject msgs = listArray.optJSONObject(i);
						Recorder recorder = gson.fromJson(msgs.toString(), Recorder.class);
						listRecorder.add(recorder);
					}
					audioMusic.setList(listRecorder);
				}
				audioMusic.setNext_cursor(data.optString("next_cursor"));
				audioMusic.setNext_cursor_str(data.optString("next_cursor_str"));
				audioMusic.setPrevious_cursor(data.optString("previous_cursor"));
				audioMusic.setPrevious_cursor_str(data.optString("previous_cursor_str"));
				return audioMusic;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}


}
