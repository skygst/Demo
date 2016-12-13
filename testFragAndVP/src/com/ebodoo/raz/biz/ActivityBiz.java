package com.ebodoo.raz.biz;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import com.ebodoo.raz.utils.MyToast;
import com.ebodoo.raz.video.WearVideoPlayActivity;
import com.ebodoo.raz.video.WearVideoPlayWebViewActivity;
import com.example.location.biz.StringBiz;

import android.content.Context;
import android.content.Intent;
import android.renderscript.Element;
import android.view.View;

public class ActivityBiz {
	
	 /**
	  * 根据优酷视频id获得具体的m3u播放地址，其中 roomId 就是优酷对应的视频ID，(恕这里不提供如何获得优酷的视频ID。。。太简单了 自己百度)
	          type代表优酷的三个版本（高清版  超清版  普通版）
	  * 
	  * @param roomId
	  * @return
	  * @throws IOException 
	  */
	 public String getYoukuAddressList(Context context, String roomId, int type) throws IOException {
		 System.out.println("-----------------------");
	  StringBuffer sb = new StringBuffer("#EXTM3U\n");
	  // XODk4NDEzNTg0
	  // 普通版
	  // http://www.flvcd.com/parse.php?kw=http%3A%2F%2Fv.youku.com%2Fv_show%2Fid_XODk4NDEzNTg0.html&flag=one&format=
	  // 超清晰
	  // http://www.flvcd.com/parse.php?kw=http%3A%2F%2Fv.youku.com%2Fv_show%2Fid_XODk5MjY0NDg4.html&flag=one&format=super
	  // 高清晰
	  // http://www.flvcd.com/parse.php?kw=http%3A%2F%2Fv.youku.com%2Fv_show%2Fid_XODk4NDEzNTg0.html&flag=one&format=high
	  // http://v.youku.com/v_show/id_XODk5MTIyNjE2.html
	  String centerUrl = "http://www.flvcd.com/parse.php?kw=http%3A%2F%2Fv.youku.com%2Fv_show%2Fid_"
	    + roomId + ".html&flag=one&format=";
//	  if (type == normal) {
//	   centerUrl = centerUrl + "";
//	  } else if (type == high) {
//	   centerUrl = centerUrl + "high";
//	  } else if (type == superhigh) {
//	   centerUrl = centerUrl + "super";
//	  }
	  // 737
	  String content = getContentByUrl(centerUrl);
	  System.out.println("------content :" + content);
	  org.jsoup.nodes.Document doc = Jsoup.parse(content);
	  Elements titleE = doc.select("table>tbody>tr>td.mn.STYLE4");
	  Elements ahref = titleE.get(0).select("a");
	  for (org.jsoup.nodes.Element ele : ahref) {
	   String href = ele.attr("href");
	   sb.append(href).append("\n");
	  }
	  String fileName = System.currentTimeMillis()+".m3u8";
	  System.out.println("------fileName :" + fileName);
//	  FileUtils.write(context, fileName, sb.toString());
	   String FILESPATH = "http://v.youku.com/player/getM3U8/vid/" + roomId + "/type/mp4/";
//	  http://v.youku.com/player/getM3U8/vid/XNDM2NjUwMTg0/type/mp4/v.m3u8
	  return FILESPATH + fileName;
	  
//	  return null;
	 }
	       /**
	       *通过HttpURLConnection 获得网页的内容
	        */
	 public static String getContentByUrl(String url) {
	  StringBuffer strBuffer = new StringBuffer();
	  try {
	   URL baseURL;
	   baseURL = new URL(url);
	   HttpURLConnection uc = (HttpURLConnection) baseURL.openConnection();
	   uc.setRequestProperty(
	     "User-Agent",
	     "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
	   uc.setRequestProperty("Accept-Encoding", "gzip,deflate");
	   InputStream input = uc.getInputStream();
	   BufferedReader br = new BufferedReader(new InputStreamReader(
	     new GZIPInputStream(input), "UTF-8"));
	   String line = null;
	   while ((line = br.readLine()) != null) {
	    strBuffer.append(line);
	   }
	  } catch (MalformedURLException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	  return strBuffer.toString();
	 
	 }
	 
	 /**
	  * android 建立私有文件
	  * 
	  * @param fileName
	  * @return
	  * @throws IOException
	  */
//	 public File creatDataFile(String fileName) throws IOException {
//	  File file = new File(FILESPATH + fileName);
//	  file.createNewFile();
//	  return file;
//	 }
	 
	// 获得控件的高度
	public static int getViewWidth(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int height = view.getMeasuredHeight();
		int width = view.getMeasuredWidth();
		System.out.println("measure width=" + width + " height=" + height);
		return width;
	}
	
	public static void gotoPlayVideo(Context context, String videoUrl, 
			String youkuId, String albumName, String name, String videoId) {
		if(!StringBiz.isEmpty(videoUrl)) {
			context.startActivity(new Intent(context, WearVideoPlayActivity.class)
			.putExtra("path", videoUrl)
			.putExtra("albumName", albumName)
			.putExtra("videoId", videoId)
			.putExtra("name", name));
		} else if(!StringBiz.isEmpty(youkuId)) {
			context.startActivity(new Intent(context, WearVideoPlayWebViewActivity.class)
			.putExtra("youku_id", youkuId)
			.putExtra("albumName", albumName)
			.putExtra("videoId", videoId)
			.putExtra("name", name));
		} else {
			new MyToast().showTextToast(context, "视频链接地址错误");
		}
	}

}
