package com.gst.move.base;

import android.content.Context;

import com.ebodoo.raz.server.CommonAdressConstant;
import com.ebodoo.raz.server.CommonInterfaces;
import com.example.location.biz.StringBiz;
import com.google.gson.Gson;
import com.gst.move.utils.CacheSp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gst-pc on 2016/10/25.
 */

public class Speak {

    private String top_video_url;
    private List<WearProducts> list;
    private String errorCode;
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTop_video_url() {
        return top_video_url;
    }

    public void setTop_video_url(String top_video_url) {
        this.top_video_url = top_video_url;
    }

    public List<WearProducts> getList() {
        return list;
    }

    public void setList(List<WearProducts> list) {
        this.list = list;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    // 获得大声说视频列表
    public Speak getLoudlyList(Context context) {
//        http://oauth.bbpapp.com//raz/videos/loudly_list?access_token=bfd386853f1b1d8f4f711914f4da058962e060bb
        String result = new CommonInterfaces().getDataFromUrlByGet(context,
                CommonAdressConstant.wearLouly, "");
        System.out.println("------getLoudlyList--result-----------" + result);
        return parseSpeak(context, result, false);
    }

    public Speak parseSpeak(Context context, String result, boolean isCache) {
        if (result == null || result.equals("")) {
            return null;
        }
        Speak mSpeak = new Speak();
        try {
            JSONObject data = new JSONObject(result);
            String errorCode = data.optString("error_code");
            if(!StringBiz.isEmpty(errorCode)) {
                mSpeak.setErrorCode(errorCode);
                mSpeak.setErrorMsg(data.optString("error"));
                return mSpeak;
            } else {
                if (data != null && !data.equals("")) {
                    String cachResult = new CacheSp().spGetSpeakLoudly(context);
                    if(!isCache) {
                        if(!StringBiz.isEmpty(cachResult)) {
                            if(cachResult.equals(result)) {
                                return null;
                            } else {
                                new CacheSp().spSetSpeakLoudly(context, result);
                            }
                        } else {
                            new CacheSp().spSetSpeakLoudly(context, result);
                        }
                    }

                    Gson gson = new Gson();
                    mSpeak.setTop_video_url(data.optString("top_video_url"));
                    JSONArray list = data.optJSONArray("list");
                    if(list != null && list.length() > 0) {
                        List<WearProducts> listWearProducts = new ArrayList<WearProducts>();
                        for(int i=0; i<list.length(); i++) {
                            JSONObject jsonObject = list.optJSONObject(i);
                            WearProducts wearProducts = new WearProducts();
                            wearProducts.setId(jsonObject.optString("id"));
                            wearProducts.setName(jsonObject.optString("name"));
                            wearProducts.setPurchased(jsonObject.optString("purchased"));
                            wearProducts.setPic_url(jsonObject.optString("pic_url"));

                            List<WearProducts.Products> listProducts = new ArrayList<WearProducts.Products>();
                            JSONArray productsArray = jsonObject.optJSONArray("products");
                            if(productsArray != null && productsArray.length() > 0) {
                                for(int j=0; j<productsArray.length(); j++) {
                                    WearProducts.Products products = gson.fromJson(productsArray.get(j).toString(), WearProducts.Products.class);
                                    listProducts.add(products);
                                }
                            }
                            wearProducts.setListProducts(listProducts);

                            List<WearProducts.Items> listItems = new ArrayList<WearProducts.Items>();
                            JSONArray itemsArray = jsonObject.optJSONArray("items");
                            if(itemsArray != null && itemsArray.length() > 0) {
                                for(int k=0; k<itemsArray.length(); k++) {
                                    WearProducts.Items items = gson.fromJson(itemsArray.get(k).toString(), WearProducts.Items.class);
                                    listItems.add(items);
                                }
                            }
                            wearProducts.setListItems(listItems);

                            listWearProducts.add(wearProducts);
                        }
                        mSpeak.setList(listWearProducts);
                    }
                }
                return mSpeak;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
