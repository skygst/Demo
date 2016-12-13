package com.ebodoo.raz.server;

import android.content.Context;

import org.apache.http.NameValuePair;

import java.util.List;

public class CommonInterfaces {
	
	// 获取读取数据的url
	
	public String getUrlForGet(Context context,String objectPath, String paramPath) {
		String path = CommonAdressConstant.share_baseurl + objectPath + "?";
		String url = path + "access_token=" + "bfd386853f1b1d8f4f711914f4da058962e060bb";
		if(paramPath != null && !paramPath.equals("") && paramPath.length() > 0) {
			url = url + "&"+ paramPath;
		}
		System.out.println("---getUrlForGet url :" + url);
		return url;
	}
	
	public String getUrlForPost(Context context,String objectPath) {
		return getUrlForGet(context, objectPath, "");
	}
	
	// 获取JSon数据从url，get方式
	
	public String getDataFromUrlByGet(Context context,String objectPath, String paramPath) {		
		String result = InteractingWithServer.getData(getUrlForGet(context,objectPath,paramPath));//(url, params);
		return result;
	}
	
	//  获取JSon数据从url，post方式
	
	public String getDataFromUrlByPost(Context context,String objectPath, List<NameValuePair> params) {

		String result = new InteractingWithServer().postData(getUrlForPost(context,objectPath),params);//(url, params);
		return result;
	}
	
	//  获取JSon数据从url，post方式 mp3上传
	
//	public String getMp3FromUrlByPost(Context context,String objectPath, ArrayList<NameValuePair> params,File file) {	
//		
//		String result = HttpPostFile.post(getUrlForPost(context,objectPath),params,file);//(url, params);
//		return result;
//	}
	//  获取JSon数据从url，post方式,包含图片
	
	/*public String getDataIncludeImageByPost(Context context,String objectPath,List<NameValuePair> params) {	
		
		String result = new InteractingWithServer().postDataIncludeImage(getUrlForPost(context,objectPath),params);//(url, params);
		return result;
	}*/

	// 获取分享数据的url
	
//	public String getShareUrlForGet(Context context,String paramPath) {
//		String path = CommonAdressConstant.share_baseurl;
//		String url = path + "access_token="
//				+ new AccessToken().getAccessToken(context);
//		if(paramPath != null && !paramPath.equals("") && paramPath.length() > 0) {
//			url = url + paramPath;
//		}
//		return url;
//	}
	//http://article.bbpapp.com/?access_token=bbe4eb5d5bb3e4a2abb5e95b8da7b6b66370d4b9&aid=14591
}
