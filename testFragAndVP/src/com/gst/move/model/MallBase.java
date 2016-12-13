package com.gst.move.model;

import android.content.Context;

import com.ebodoo.raz.server.CommonAdressConstant;
import com.ebodoo.raz.server.CommonInterfaces;
import com.example.location.biz.StringBiz;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MallBase {

	private String id;
	private String type;
	private String name;
	private String original_price;
	private String price;
	private String description;
	private String url;
	private String pic;
	private String limit_count;
	private String store_url;
	private String need_post;
	private String msg;
	private String errCode;

	public String getNeed_post() {
		return need_post;
	}

	public void setNeed_post(String need_post) {
		this.need_post = need_post;
	}

	public String getStore_url() {
		return store_url;
	}

	public void setStore_url(String store_url) {
		this.store_url = store_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLimit_count() {
		return limit_count;
	}

	public void setLimit_count(String limit_count) {
		this.limit_count = limit_count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(String original_price) {
		this.original_price = original_price;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	// 获得商城信息
	public Object[] getMallList(Context context) {
		String result = new CommonInterfaces().getDataFromUrlByGet(context,
				CommonAdressConstant.mallList, "");
		return parseMall(context, result, false);
	}

	// 解析购买的信息
	public Object[] parseMall(Context context, String result, boolean isCache) {
		if (result == null || result.equals("")) {
			return null;
		}
		Object[] obj = new Object[3];
		List<MallBase> mallList = new ArrayList<MallBase>();
		try {
			JSONObject data = new JSONObject(result);
			String errorCode = data.optString("error_code");
			if (errorCode != null && !errorCode.equals("")) {
				String msg = data.optString("error");
				obj[0] = null;
				obj[1] = errorCode;
				obj[2] = msg;
				return obj;
			}
			Gson gson = new Gson();
			JSONArray goods = data.optJSONArray("goods");
			if (goods != null && goods.length() > 0) {
				boolean isParse = true;
				if(isParse) {
					Type listType = new TypeToken<LinkedList<MallBase>>() {
					}.getType();
					LinkedList<MallBase> mall = gson.fromJson(goods.toString(),
							listType);
					for (Iterator<MallBase> iterator = mall.iterator(); iterator
							.hasNext();) {
						MallBase mallBase = iterator.next();
						mallList.add(mallBase);
					}
				}
				obj[0] = mallList;
				obj[1] = "";
				obj[2] = "";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	private MallBase parseListGoodsDetail(Context context, String result) {
		if (result == null || result.equals("")) {
			return null;
		}
		MallBase mBase = new MallBase();
		try {
			JSONObject data = new JSONObject(result);
			String errorCode = data.optString("error_code");
			if(!StringBiz.isEmpty(errorCode)) {
				mBase.setErrCode(errorCode);
				mBase.setMsg(data.optString("error"));
				return mBase;
			} else {
				if (data != null && !data.equals("")) {
					Gson gson = new Gson();
					mBase = gson.fromJson(data.toString(), MallBase.class);
				}
				return mBase;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
