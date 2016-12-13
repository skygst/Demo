package com.ebodoo.raz.server;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @author gst
 * 
 *         与服务器端交互
 */
public class InteractingWithServer {
//	200请求成功、303重定向、400请求错误、401未授权、403禁止访问、404文件未找到、500服务器错误
	/**
	 *  post获取数据
	 * @param url
	 * @param params
	 * @return
	 */
	public String postData(String url, List<NameValuePair> params) {
		HttpPost httpRequest = new HttpPost(url);
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		try {
			if (params != null) {
				StringBuilder paramsSb = new StringBuilder();
				for (NameValuePair nameValuePair : params) {
					paramsSb.append(nameValuePair.getName() + ":"
							+ nameValuePair.getValue() + " ");
				}
				httpRequest.setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
			}
			defaultHttpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			defaultHttpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 20000);
			HttpResponse httpResponse = defaultHttpClient.execute(httpRequest);
			System.out.println("httpResponse.getStatusLine().getStatusCode() :"+httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				if (result.length() > 3) {
					if(result.substring(0, 1).equals("{")) {
						result = result.substring(result.indexOf("{"),
								result.lastIndexOf("}") + 1);
					}
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放连接资源
			httpRequest.abort();
			defaultHttpClient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	/**
	 *  get获取数据
	 * @param url
	 * @return
	 */
	public static String getData(String url) {
		HttpGet httpRequest = new HttpGet(url);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			
			/* 发送请求并等待响应 */
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/* 读 */
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				
				if (strResult.length() > 1) {
					if(strResult.contains("[")){
						if(strResult.indexOf("{")<strResult.indexOf("[")){
							strResult = strResult.substring(strResult.indexOf("{"),
									strResult.lastIndexOf("}") + 1);
						}else{
							strResult = strResult.substring(strResult.indexOf("["),
									strResult.lastIndexOf("]") + 1);
						}
					}else{
						strResult = strResult.substring(strResult.indexOf("{"),
								strResult.lastIndexOf("}") + 1);
					}
					
					
					return strResult;
				}
				/* 去没有用的字符 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//释放连接资源
			httpRequest.abort();
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	public String postDataNew(String url, List<NameValuePair> params) {
		HttpPost httpRequest = new HttpPost(url);
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		try {
			if (params != null) {
				StringBuilder paramsSb = new StringBuilder();
				for (NameValuePair nameValuePair : params) {
					paramsSb.append(nameValuePair.getName() + ":"
							+ nameValuePair.getValue() + " ");
				}
				httpRequest.setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
			}
			defaultHttpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			defaultHttpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 20000);
			HttpResponse httpResponse = defaultHttpClient.execute(httpRequest);
			System.out.println("httpResponse.getStatusLine().getStatusCode() :"+httpResponse.getStatusLine().getStatusCode());
//			if (httpResponse.getStatusLine().getStatusCode() == 200 || httpResponse.getStatusLine().getStatusCode() == 400) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				if (result.length() > 3) {
					if(result.substring(0, 1).equals("{")) {
						result = result.substring(result.indexOf("{"),
								result.lastIndexOf("}") + 1);
					}
					return result;
				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放连接资源
			httpRequest.abort();
			defaultHttpClient.getConnectionManager().shutdown();
		}
		return null;
	}
	
}
