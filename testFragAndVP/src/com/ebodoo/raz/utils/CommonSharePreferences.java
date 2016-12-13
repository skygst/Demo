package com.ebodoo.raz.utils;

import com.gst.move.utils.CacheSp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author hfy
 *存取共同数据
 */
public class CommonSharePreferences {
	
	public static void loginOutClearData(Context context){
		SharedPreferences settings = context.getSharedPreferences("my_goods", 0);
		SharedPreferences.Editor localEditor = settings.edit();
		localEditor.clear().commit();
		
		SharedPreferences setting2 = context.getSharedPreferences(
				"my_http", 0);
		SharedPreferences.Editor localEditor2 = setting2.edit();
		localEditor2.clear().commit();
	}
	// 保存总金币
	public static void  setMyJinbi(Context context, int jinbi) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("my_jinbi", jinbi);
		editor.commit();
	}
	
	// 获得金币
	public static int getMyJinbi(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0);
		int myjinbi = setting.getInt("my_jinbi", 0);
		
		return myjinbi;//+200000
	}

	// 保存增加后的总金币
	public static void  setAddMyJinbi(Context context, int jinbi) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("my_jinbi", getMyJinbi(context)+jinbi);
		editor.commit();
	}
	
	//保存减少后的总金币	
	public static void  setReduceMyJinbi(Context context, int jinbi) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("my_jinbi", getMyJinbi(context) - jinbi);
		editor.commit();
	}
		
	//保存购买的道具	 daoju——
	public static void  setBuyClothes(Context context, String name_cloth) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean(name_cloth, true);
		editor.commit();
	}
	
	//保存购买的道具	 daoju——
	public static boolean  getBuyClothes(Context context, String name_cloth) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0);
		boolean havebuy = setting.getBoolean(name_cloth, false);
		return havebuy;
	}
	
	//保存最后购买的道具index	 daoju——
	public static void  setLastBuyClothes(Context context, String name_cloth, int index) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt(name_cloth, index);
		editor.commit();
	}
	
	//保存购买的道具	 daoju——
	public static int  getLastBuyClothes(Context context, String name_cloth) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0);
		int havebuy = setting.getInt(name_cloth, -1);
		return havebuy;
	}

	//保存是否是莱莱 true 是          false——小妹
	public static void  setLailaiOrShixiaomei(Context context, boolean isLailai) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("select_lailai", isLailai);
		editor.commit();
	}
	
	//获取是否是莱莱 true 是          false——小妹
	public static boolean  getLailaiOrShixiaomei(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_goods", 0);
		boolean islailai = setting.getBoolean("select_lailai", true);
		return islailai;
	}
	
	//保存是否上传了level
	public static void  setHaveHttpLevel(Context context, boolean isHttp) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_http", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("have_http_level", isHttp);
		editor.commit();
	}
	
	//获取是否上传了level
	public static boolean  getHaveHttpLevel(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_http", 0);
		boolean islailai = setting.getBoolean("have_http_level", false);
		return islailai;
	}
	//保存是否上传了level
	public static void  setHaveHttpAllCoin(Context context, boolean isAll) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_http", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("have_http_allcoin", isAll);
		editor.commit();
	}
	
	//获取是否上传了level
	public static boolean  getHaveHttpAllCoin(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_http", 0);
		boolean islailai = setting.getBoolean("have_http_allcoin", false);
		return islailai;
	}
	
	//保存是否上传了level
	public static void  setAddCoin(Context context, int coin) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_http", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("add_coin", coin+getAddCoin(context));
		editor.commit();
	}
	
	//获取是否上传了level
	public static int  getAddCoin(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"my_http", 0);
		int coin = setting.getInt("add_coin", 0);
		return coin;
	}
	
	/*//保存环游世界某个国家的学习记录  ——用于和服务器端的同步
	 * 
	 * 国家id  10/20/30/40/50——十位数及以上表示哪一个国家  分别对应 荷兰、英国、法国、西班牙、意大利，其它的州依次增加
	 * value 
	 */	
	/*public static void  setOneCountryLevel(Context context, int country_id, int value) {
		SharedPreferences setting = context.getSharedPreferences(
				"country_level", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt(""+country_id, value|getOneCountryLevel(context,country_id));
		editor.commit();
	}*/
	
	
//	public static void  setOneCountryLevel(Context context, int country_id, int value) {
//		SharedPreferences setting = context.getSharedPreferences(
//				"country_level", 0); 
//		SharedPreferences.Editor editor = setting.edit();
//		editor.putInt(""+country_id, value|getOneCountryLevel(context,country_id));
//		editor.commit();
//	}
	
//	public static void delCountryLevel(Context context) {
//		SharedPreferences setting = context.getSharedPreferences(
//				"country_level", 0); 
//		setting.edit().clear().commit();
//	}
	
	public static void delBookLevel(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"book_level", 0); 
		setting.edit().clear().commit();
	}
	public static void delRecordLevel(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"record", 0); 
		setting.edit().clear().commit();
	}
	
	public static void delFinishLevel(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"island_level", 0); 
//		"sp_finish_level", 0); 
		setting.edit().clear().commit();
	}
	
	//获取环游世界某个国家的学习记录  
	public static int  getOneCountryLevel(Context context, int country_id) {
		SharedPreferences setting = context.getSharedPreferences(
				"country_level", 0);
		int value = setting.getInt(""+country_id, 0);
		return value;
	}
	
	public static String oldCountryLevel(Context context) {
		int[] countryId = new int[] {20, 40, 30, 10, 50};
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<countryId.length; i++) {
			int value = getOneCountryLevel(context, countryId[i]);
			sb.append(value);
		}
		return sb.toString();
	}
	
	public static void clearOldCountryLevel(Context context) {
		SharedPreferences countryLevel = context.getSharedPreferences(
				"country_level", 0);
		SharedPreferences.Editor editor = countryLevel.edit();
		editor.clear().commit();
	}
	
	/*//保存环游世界某个国家的学习记录  
	 * 
	 * 国家id  10/20/30/40/50——十位数及以上表示哪一个国家  分别对应 荷兰、英国、法国、西班牙、意大利，其它的州依次增加
	 * value 
	 * guanka_id 国家id 荷兰 10 对应的关卡id 为 10、11、12       英国 20 对应的 20、21、22
	 */
//	public static boolean setOneCountryLevel(Context context, int guanka_id) {
//		
//		int country_id = guanka_id/10*10;
//		int guanka = guanka_id%10;
//		int value = 0;
//		
//		SharedPreferences setting = context.getSharedPreferences(
//				"country_level", 0); 
//		SharedPreferences.Editor editor = setting.edit();
//		
//		value = getOneCountryLevel(context,country_id);
//		
//		if(guanka == 0){
//			value = value|1;
//		}else if(guanka == 1){
//			value = value|2;
//		}else if(guanka == 2){
//			value = value|4;
//		}
//		
//		editor.putInt(""+country_id, value);
//		editor.commit();
//		
//		if(value == 7)
//			return true;
//		else
//			return false;
//	}
	//获取某一个国家的某一个关卡是否通关
//	public static boolean getOneCountryGuankaLevel(Context context, int index, int guanka) {
//		int value = 0;
//		String countryLevel = new CacheSp().spGetCountryLevel(context);
//		if(countryLevel != null && !countryLevel.equals("")) {
//			char[] ch = countryLevel.toCharArray();
//			if(index < ch.length) {
//				int chValue = ch[index];
//				if(guanka == 0){
//					value = chValue & 1;
//				}else if(guanka == 1){
//					value = chValue & 2;
//				}else if(guanka == 2){
//					value = chValue & 4;
//				}
//			}
//		}
//		if(value != 0)
//			return true;
//		else
//			return false;
//	}
	
	//判断某一个国家是否通关
//	public static boolean getOneCountryPassOverByGuankaid(Context context, int guanka_id) {
//		int country_id = guanka_id/10*10;
//		SharedPreferences setting = context.getSharedPreferences(
//				"country_level", 0);
//		int value = setting.getInt(""+country_id, 0);
//		
//		if(value == 7)
//			return true;
//		else
//			return false;
//	}
	
	/*//保存图书馆图书的学习记录    ——用于和服务器端的同步
	 * 
	 * 图书id  10/20/30/40/50——十位数及以上表示哪一本书  分别对应 电子书 1、2、3、4、5，其它的书依次增加
	 * value 
	 */	
	public static void  setOneBookLevel(Context context, int book_id, int value) {
		SharedPreferences setting = context.getSharedPreferences(
				"book_level", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt(""+book_id, value|getOneBookLevel(context,book_id));
		editor.commit();
	}
	
	//获取环游世界某本书的学习记录  
	public static int  getOneBookLevel(Context context, int book_id) {
		SharedPreferences setting = context.getSharedPreferences(
				"book_level", 0);
		int value = setting.getInt(""+book_id, 0);
		return value;
	}
	/*//保存环游世界某本书的学习记录  
	 * 
	 * 图书id  10/20/30/40/50——十位数及以上表示哪一本书  分别对应 电子书 1、2、3、4、5，其它的书依次增加
	 * value 
	 * guanka_id 书id 书 10 对应的关卡id 为 10、11、12       书20 对应的 20、21、22
	 */
	public static void setOneBookLevel(Context context, int guanka_id) {
		
		int book_id = guanka_id/10*10;
		int guanka = guanka_id%10;
		int value = 0;
		
		SharedPreferences setting = context.getSharedPreferences(
				"book_level", 0); 
		SharedPreferences.Editor editor = setting.edit();
		
		value = getOneBookByGuankaid(context,book_id);
		
		if(guanka == 0){
			value = value|1;
		}else if(guanka == 1){
			value = value|2;
		}else if(guanka == 2){
			value = value|4;
		}else if(guanka == 3){
			value = value|8;
		}else if(guanka == 4){
			value = value|16;
		}else if(guanka == 5){
			value = value|32;
		}else if(guanka == 6){
			value = value|64;
		}else if(guanka == 7){
			value = value|128;
		}else if(guanka == 8){
			value = value|256;
		}
		
		editor.putInt(""+book_id, value);
		editor.commit();
		
	}
	
	public static void ClearOneBookLevel(Context context, int guanka_id) {
		
		int book_id = guanka_id/10*10;
		int guanka = guanka_id%10;
		int value = 0;
		
		SharedPreferences setting = context.getSharedPreferences(
				"book_level", 0); 
		SharedPreferences.Editor editor = setting.edit();
		
		value = getOneBookByGuankaid(context,book_id);
		
		if(guanka == 0){
			value = value&(255-1);
		}else if(guanka == 1){
			value = value&(255-2);
		}else if(guanka == 2){
			value = value&(255-4);
		}else if(guanka == 3){
			value = value&(255-8);
		}else if(guanka == 4){
			value = value&(255-16);
		}else if(guanka == 5){
			value = value&(255-32);
		}else if(guanka == 6){
			value = value&(255-64);
		}else if(guanka == 7){
			value = value&(255-128);
		}else if(guanka == 8){
			value = value&(255-256);
		}
		
		editor.putInt(""+book_id, value);
		editor.commit();
		
	}
	//获取某一本书的某一个关卡是否通关  返回 0-没有学；1-学完；2-打开过
	public static int getOneBookGuankaLevel(Context context, int guanka_id) {
		int book_id = guanka_id/10*10;
		int guanka = guanka_id%10;
		SharedPreferences setting = context.getSharedPreferences(
				"book_level", 0);
		int value = setting.getInt(""+book_id, 0);
		int value2 = value;
		if(guanka == 0){
			value = value&1;
		}else if(guanka == 1){
			value = value&2;
		}else if(guanka == 2){
			value = value&4;
		}else if(guanka == 6){
			value = value&64;
		}else if(guanka == 7){
			value = value&128;
		}
		
		if(value != 0){
			return 1;
		}
		
		if(guanka == 0){
			value2 = value2&8;
		}else if(guanka == 1){
			value2 = value2&16;
		}else if(guanka == 2){
			value2 = value2&32;
		}else if(guanka == 6){
			value2 = value2&256;
		}else if(guanka == 7){
			value2 = value2&128;
		}
		
		
		if(value2 != 0)
			return 2;
		else
			return 0;
		
	}
	//判断某一本书是否通关
	public static boolean getOneBookPassOverByGuankaid(Context context, int guanka_id) {
		int book_id = guanka_id/10*10;
		SharedPreferences setting = context.getSharedPreferences(
				"book_level", 0);
		int value = setting.getInt(""+book_id, 0);
		value = value & 7;
		if(value == 7)
			return true;
		else
			return false;
	}
	public static int getOneBookByGuankaid(Context context, int guanka_id) {
		int book_id = guanka_id/10*10;
		SharedPreferences setting = context.getSharedPreferences(
				"book_level", 0);
		int value = setting.getInt(""+book_id, 0);
		
		
		return value;
	}
	
	//保存飞行棋是否提示过点击 go
	public static void  setFlychessGoNote(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_prefer", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("have_note", true);
		editor.commit();
	}
	
	//获取飞行棋是否提示过点击 go
	public static boolean  getFlychessGoNote(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_prefer", 0);
		boolean haveNote = setting.getBoolean("have_note", false);
		return haveNote;
	}
	
	//保存录音的时间和时长
	public static void  setRecordDateAndDuration(Context context,int index,String date,String duration) {
		SharedPreferences setting = context.getSharedPreferences(
				"record", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putString("date_"+index, date);
		editor.putString("duration_"+index, duration);
		editor.commit();
	}
	
	//获取录音的时间
	public static String  getRecordDate(Context context,int index) {
		SharedPreferences setting = context.getSharedPreferences(
				"record", 0);
		String date = setting.getString("date_"+index, "");
		return date;
	}
	//获取录音的时长
	public static String  getRecordDuration(Context context,int index) {
		SharedPreferences setting = context.getSharedPreferences(
				"record", 0);
		String duration = setting.getString("duration_"+index, "");
		return duration;
	}
	
	/**
	 * get 获得视频播放的路径
	 *
	 */
	public static String getVideoPath(Context inContext){
		SharedPreferences settings = inContext.getSharedPreferences("video", 0);
		
		return settings.getString("vedio_path","");
		
	}
	/**
	 * 保存视频播放的路径
	 *
	 */
	public static void setVideoPath(Context inContext,String vedio_path){	
		SharedPreferences settings = inContext.getSharedPreferences("video", 0);
		SharedPreferences.Editor editor = settings.edit();
		
		editor.putString("vedio_path",vedio_path);
	
		editor.commit();
		
	}
	/**
	 * get 获得视频播放的位置
	 *
	 */
	public static int getVideoPosition(Context inContext){
		SharedPreferences settings = inContext.getSharedPreferences("video", 0);
		
		return settings.getInt("vedio_position",0);
		
	}
	/**
	 * 保存视频播放的位置
	 *
	 */
	public static void setVideoPosition(Context inContext,int vedio_position){	
		SharedPreferences settings = inContext.getSharedPreferences("video", 0);
		SharedPreferences.Editor editor = settings.edit();
		
		editor.putInt("vedio_position",vedio_position);
	
		editor.commit();
		
	}
	
	//记录图书馆是否说过说明
	public static void  setLibrarySpeakedNote(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_prefer", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("library_note", true);
		editor.commit();
	}
	
	//图书馆是否说过说明
	public static boolean  getLibrarySpeakedNote(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_prefer", 0);
		boolean havebuy = setting.getBoolean("library_note", false);
		return havebuy;
	}
	//记录录音是否说过3次以上说明
	public static void  setRecordMicphoneNote(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_prefer", 0); 
		SharedPreferences.Editor editor = setting.edit();
		int value = getRecordMicphoneNote(context);
		editor.putInt("micphone_note", value+1);
		editor.commit();
	}
	//录音是否说过说明过3次
	public static int  getRecordMicphoneNote(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_prefer", 0);
		int value = setting.getInt("micphone_note", 0);
		return value;
	}
	
	/*获取全部岛过了多少关
	 * 
	 *  1000  0 00  0 00  00 0  000
	 */
	public static int getIslandAllLevel(Context context) {
		
		SharedPreferences setting = context.getSharedPreferences(
				"island_level", 0);
		int value = setting.getInt("island_level", 0);
		
		return value;
		
	}
	
	
	/*获取某个岛过了多少关
	 * 
	 * 参数：dao_id 岛的id 0、1、2、3；
	 * 
	 * 返回 值：0-没有过，1过了一关，2过了2关…… 
	 * 
	 *  1111  1111  1111  1111
	 */
	public static int getIslandLevel(Context context, int dao_id) {
		
		SharedPreferences setting = context.getSharedPreferences(
				"island_level", 0);
		int value = setting.getInt("island_level", 0);
		
		if(value>0){
			if(dao_id == 0){
			}else if(dao_id == 1){
				value = value >> 4;
			}else if(dao_id == 2){
				value = value >> 8;
			}else if(dao_id == 3){
				value = value >> 12;
			}else{
				return 0;
			}
			value = value & 15;
			return value;
		}else{
			return 0;
		}
	}
	/*保存某个岛过了多少关
	 * 
	 * 参数：level 通过老的level运算；
	 * value 1第一关，2前两关，3前3关
	 *  1111  1111  1111  1111
	 */
	public static void setIslandByLevel(Context context, int level) {
		if(level <= 4){
			setIslandLevel(context,0,level);
		}else if(level > 4 && level <= 8){
			setIslandLevel(context,1,level - 4);
		}else if(level > 8 && level <= 13){
			setIslandLevel(context,2,level - 8);
		}else if(level > 8 && level <= 19){
			setIslandLevel(context,3,level-13);
		}	
	
	}
	
	/*保存某个岛过了多少关
	 * 
	 * 参数：dao_id 岛的id 0、1、2、3；
	 * value 1第一关，2前两关，3前3关
	 *  1111  1111  1111  1111
	 */
	public static void setIslandLevel(Context context, int dao_id ,int value) {
		
		
		SharedPreferences setting = context.getSharedPreferences(
				"island_level", 0); 
		SharedPreferences.Editor editor = setting.edit();
		//获取原来的数据
		int level = setting.getInt("island_level", 0);
				
		
		int value_local=0;
		//获取岛原来的值
		if(dao_id == 0){
			value_local = level & 15;
		}else if(dao_id == 1){
			value_local = (level>>4) & 15;
		}else if(dao_id == 2){
			value_local = (level>>8) & 15;
		}else if(dao_id == 3){
			value_local = (level>>12) & 15;
		}
		
		//原来的值更大，返回
		if(value_local>=value)return;
		
		//新值更大，更新该值，岛的值左移到对应的位置
		if(dao_id == 0){
			level = level & 65520;		//1111111111110000
		}else if(dao_id == 1){
			level = level & 65295;		//1111111100001111
			value = value << 4;
		}else if(dao_id == 2){
			level = level & 61695;		//1111000011111111
			value = value << 8;
		}else if(dao_id == 3){
			level = level & 4095;		//0000111111111111
			value = value << 12;
		}
		//岛的值存到level
		level = level | value;
		
		editor.putInt("island_level", level);
		editor.commit();		
	}
	
	/*本地老level+新的island_level值  ——  用于游客 老的level转化
	 * 
	 * 参数：dao_id 岛的id 0、1、2、3；
	 * value 1第一关，2前两关，3前3关
	 *  1111  1111  1111  1111
	 */
	public static void addLocalLevelAndIslandLevel(Context context) {
		if(getIslandAllLevel(context) > 0)
			return;
		int level = new BaseCommon().getSelectLevel(context);
		addNetLevelAndIslandLevel(context,level);
	}
	
	/*
	 * 老的level+ 新的island_level  ——  用于登录用户的老的level转化
	 * 参数：level是老的level；
	 * 
	 *  1111  1111  1111  1111
	 */
	public static void addNetLevelAndIslandLevel(Context context,int level){		
		
		int level_local = new BaseCommon().getSelectLevel(context);
		
		if(level<level_local){
			level = level_local;
		}
		
		if(level <= 4){
			setIslandLevel(context,0,level);
		}else if(level > 4 && level <= 8){
			setIslandLevel(context,0,4);
			setIslandLevel(context,1,level - 4);
		}else if(level > 8 && level <= 13){
			setIslandLevel(context,0,4);
			setIslandLevel(context,1,4);
			setIslandLevel(context,2,level - 8);
		}else if(level > 8 && level <= 19){
			setIslandLevel(context,0,4);
			setIslandLevel(context,1,4);
			setIslandLevel(context,2,5);
			setIslandLevel(context,3,level-13);
		}		
	}
	
	/*
	 * 服务器island_level + 本地岛level ——  用于登录用户 网络新level 合并 本地新level
	 * 参数：island_level是新的服务器端的island_level；
	 * 
	 *  1111  1111  1111  1111
	 */
	public static void addNetAndLocalIslandLevel(Context context,int island_level){				
		
		int island_level_local = getIslandAllLevel(context);
		
		if(island_level == island_level_local)return;
		
		int[] value = new int[4];
		
		int level;
		//11110000-240  111100000000-3840  1111000000000000-61440
		value[0]=((island_level&15) > (island_level_local&15))?(island_level&15) : (island_level_local&15);
		value[1]=((island_level&240) > (island_level_local&240))?(island_level&240) : (island_level_local&240);
		value[2]=((island_level&3840) > (island_level_local&3840))?(island_level&3840) : (island_level_local&3840);
		value[3]=((island_level&61440) > (island_level_local&61440))?(island_level&61440) : (island_level_local&61440);
		
		//保存为sky需要的level
//		new BaseCommon().spIslandLevel(context,value[0],value[1],value[2],value[3]);
		
		level = value[0]|value[1]|value[2]|value[3];
		
		setAllIslandLevel(context,level);
	}
	/*
	 * 判断服务器island_level 和 本地岛level 是否一样
	 * 
	 * 参数：island_level是新的服务器端的island_level；
	 * 
	 */
	public static boolean isSameNetLocalIslandlevel(Context context,int island_level){
		int island_level_local = getIslandAllLevel(context);
		
		if(island_level == island_level_local)return true;
		
		return false;
	}
	
	/*保存4个岛全部的level
	 * 
	 * 参数：level 岛的全部level；
	 * 
	 */
	public static void setAllIslandLevel(Context context, int level) {
		
		
		SharedPreferences setting = context.getSharedPreferences(
				"island_level", 0); 
		SharedPreferences.Editor editor = setting.edit();		
		
		editor.putInt("island_level", level);
		editor.commit();		
	}
	
	/*保存所有岛过了多少关
	 * 
	 * 参数：dao_id 岛的id 0、1、2、3；
	 * value 1第一关，2前两关，3前3关
	 *  1111  1111  1111  1111
	 */
	private static void saveIslandAllLevel(Context context, int value) {
				
		int value0,value1,value2,value3;
		
		value0 = value;
		value1 = value;
		value2 = value;
		value3 = value;
		
		value0 = value0 & 15;
		value1 = (value1>>4) & 15;
		value2 = (value1>>8) & 15;
		value3 = (value1>>12) & 15;
		
		setIslandLevel(context,0,value0);
		setIslandLevel(context,1,value1);
		setIslandLevel(context,2,value2);
		setIslandLevel(context,3,value3);
		
	}
	/*判断服务器端是否有了新的岛的level
	 * 
	 * 参数：land_level 岛的level值；
	 * 
	 *  返回：true：已经有了，false：还没有
	 */
	public static boolean isNewIslandLevel(int land_level){
		if(land_level > 0){
			return true;
		}else{
			return false;
		}
	}
	
	//保存是否第一次打开魔法时刻
	public static void  setFirstOpenMofa(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("isFirstOpen", false);
		editor.commit();
	}
	
	//获取是否第一次打开魔法时刻
	public static boolean  getFirstOpenMofa(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean isFirstOpen = setting.getBoolean("isFirstOpen", true);
		return isFirstOpen;
	}
	//保存是否购买了魔法时刻 1
	public static void  setBuyMofa1(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("mofa1", true);
		editor.commit();
	}
	
	public static void clearAlreadyCommonBase(Context context) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"common_base", 0);
		SharedPreferences.Editor editor = spSelectLevel.edit();
		editor.clear().commit();
	} 
	
	//获取是否购买了魔法时刻 1
	public static boolean  getBuyMofa1(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean isFirstOpen = setting.getBoolean("mofa1", false);
		return isFirstOpen;
	}	
	//保存是否购买了魔法时刻 2
	public static void  setBuyMofa2(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("mofa2", true);
		editor.commit();
	}
	
	//获取是否购买了魔法时刻 2
	public static boolean  getBuyMofa2(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean isFirstOpen = setting.getBoolean("mofa2", false);
		return isFirstOpen;
	}		
	//保存是否购买了魔法时刻 3
	public static void  setBuyMofa3(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("mofa3", true);
		editor.commit();
	}
	
	//获取是否购买了魔法时刻 3
	public static boolean  getBuyMofa3(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean isFirstOpen = setting.getBoolean("mofa3", false);
		return isFirstOpen;
	}			
	//保存是否购买了魔法时刻 4
	public static void  setBuyMofa4(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("mofa4", true);
		editor.commit();
	}
	
	//获取是否购买了魔法时刻4
	public static boolean  getBuyMofa4(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean isFirstOpen = setting.getBoolean("mofa4", false);
		return isFirstOpen;
	}	
	
	//保存是否购买了高频字 5
	public static void  setBuyMofa5(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("mofa5", true);
		editor.commit();
	}
	
	//获取是否购买了高频字5
	public static boolean  getBuyMofa5(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean isFirstOpen = setting.getBoolean("mofa5", false);
		return isFirstOpen;
	}	
	
	//保存是否购买了电子书 aa
	public static void  setBuyMofa6(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("mofa6", true);
		editor.commit();
	}
	
	//获取是否购买了电子书 aa
	public static boolean  getBuyMofa6(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean isFirstOpen = setting.getBoolean("mofa6", false);
		return isFirstOpen;
	}
	// 保存魔法英语提示扫卡
	public static void  setMofaTishi(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("mofa_tishi", num);
		editor.commit();
	}
	
	// 获得魔法英语提示扫卡
	public static int getMofaTishi(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("mofa_tishi", 0);
		
		return myjinbi;//+200000
	}
	
	// 保存魔法英语提示扫卡3
	public static void  setMofaTishi3(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("mofa_tishi3", num);
		editor.commit();
	}
	
	// 获得魔法英语提示扫卡3
	public static int getMofaTishi3(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("mofa_tishi3", 0);
		
		return myjinbi;//+200000
	}
	
	// 保存魔法英语提示扫二维码
	public static void  setErweimaTishi(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("erweima_tishi", num);
		editor.commit();
	}
	
	// 获得魔法英语提示扫二维码
	public static int getErweimaTishi(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("erweima_tishi", 0);
		
		return myjinbi;//+200000
	}
	
	//保存飞行棋是否提示过魔法卡片拼读
	public static void  setFlyChessMagicTishi(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("flychess_magic_tishi", true);
		editor.commit();
	}
	
	//获取飞行棋是否提示过魔法卡片拼读
	public static boolean  getFlyChessMagicTishi(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean islailai = setting.getBoolean("flychess_magic_tishi", false);
		return islailai;
	}
	
	//保存是否提示过魔法卡片拼读
	public static void  setMagicTishi2(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("flychess_magic_tishi2", true);
		editor.commit();
	}
	
	//获取是否提示过魔法卡片拼读
	public static boolean  getMagicTishi2(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean islailai = setting.getBoolean("flychess_magic_tishi2", false);
		return islailai;
	}
	
	// 保存魔法英语扫出的二维码
	public static void  setErweima(Context context, String str) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putString("my_erweima", str);
		editor.commit();
	}
	
	// 获得魔法英语提示扫二维码
	public static String getErweima(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		String myerweima = setting.getString("my_erweima", "");
		
		return myerweima;//+200000
	}
	
	// 保存魔法英语-1 单词卡level
	public static void  setMagicGameLevel001(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("magic_level001", num);
		editor.commit();
	}
		
	// 获得魔法英语-1 单词卡level
	public static int getMagicGameLevel001(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("magic_level001", 0);
		
		return myjinbi;//+200000
	}
	
	// 保存魔法英语-拼读卡 等级1
	public static void  setMagicGameLevel002(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("magic_level002", num);
		editor.commit();
	}
		
	// 获得魔法英语-拼读卡 等级1
	public static int getMagicGameLevel002(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("magic_level002", 0);
		
		return myjinbi;//+200000
	}
	
	// 保存魔法英语-拼读卡 等级2
	public static void  setMagicGameLevel003(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("magic_level003", num);
		editor.commit();
	}
		
	// 获得魔法英语-拼读卡 等级2
	public static int getMagicGameLevel003(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("magic_level003", 0);
		
		return myjinbi;//+200000
	}
	// 保存魔法英语-拼读卡 等级3
	public static void  setMagicGameLevel004(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("magic_level004", num);
		editor.commit();
	}
		
	// 获得魔法英语-拼读卡 等级3
	public static int getMagicGameLevel004(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("magic_level004", 0);
		
		return myjinbi;//+200000
	}
	// 保存魔法英语-拼读卡 等级4
	public static void  setMagicGameLevel005(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("magic_level005", num);
		editor.commit();
	}
		
	// 获得魔法英语-拼读卡 等级4
	public static int getMagicGameLevel005(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("magic_level005", 0);
		
		return myjinbi;//+200000
	}
	//清除所有数据
	public void clearCommonBase(Context context) {
		SharedPreferences spSelectLevel = context.getSharedPreferences(
				"common_base", 0);
		SharedPreferences.Editor editor = spSelectLevel.edit();
		editor.clear().commit();
	}
	
	
	//保存第一套卡片扫卡记录
	public static void spSetScanCard1(Context context, String cardRecord) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		SharedPreferences.Editor editor = spScanCard1.edit();
		editor.putString("magic_card_record1", cardRecord);
		editor.commit();
	}
	//读取第一套卡片扫卡记录 "00000 00000 00000 00000"  "000000000000000000000000000000000000000000000000000000000000000000000000000000"
	public static String spGeScanCard1(Context context) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		String cardRecord = spScanCard1.getString("magic_card_record1", "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		return cardRecord;
	}
	//保存第一套卡片扫卡记录
	public static void spSetScanCard2(Context context, String cardRecord) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		SharedPreferences.Editor editor = spScanCard1.edit();
		editor.putString("magic_card_record2", cardRecord);
		editor.commit();
	}
	//读取第一套卡片扫卡记录 "00000 00000 00000 00000"  "000000000000000000000000000000000000000000000000000000000000000000000000000000"
	public static String spGeScanCard2(Context context) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		String cardRecord = spScanCard1.getString("magic_card_record2", "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		return cardRecord;
	}
	//保存是否是单词,ture 是，false 自然拼读
	public static void  setMagicGame1Type(Context context,boolean isword) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("magic_game_type", isword);
		editor.commit();
	}
	
	//获取是否是单词,ture 是，false 自然拼读
	public static boolean  getMagicGame1Type(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean islailai = setting.getBoolean("magic_game_type", true);
		return islailai;
	}
	
	//保存是否设置过5级之后，自动设置为拼读
	public static void  setMagicGame1TypeForPindu(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("magic_game_type", true);
		editor.commit();
	}
	
	//获取是否是单词,ture 是，false 自然拼读
	public static boolean  getMagicGame1TypeForPindu(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean islailai = setting.getBoolean("magic_game_type", false);
		return islailai;
	}
	
	// 保存魔法英语-1 单词卡star 数
	public static void  clearStarNum(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();		
		
		editor.putInt("star_num", num);
		editor.commit();
	}
	// 保存魔法英语-1 单词卡star 数
	public static void  setStarNum(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		
		int data = getStarNum(context);
		
		editor.putInt("star_num", num + data);
		editor.commit();
	}
		
	// 获得魔法英语-1 单词卡star 数
	public static int getStarNum(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("star_num", 0);
		
		return myjinbi;//+200000
	}
	
	// 保存魔法英语-1 单词卡star 总数
	public static void  setStarAllNum(Context context, int num) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();				
		
		editor.putInt("star_all_num", num);
		editor.commit();
	}
		
	// 获得魔法英语-1 单词卡star 总数
	public static int getStarAllNum(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		int myjinbi = setting.getInt("star_all_num", 0);
		
		return myjinbi;//+200000
	}
	
	// 获得已经读取过的开屏homepush
	public static int getHomePushId(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base2", 0);
		int myjinbi = setting.getInt("home_push_id", -1);
		
		return myjinbi;//+200000
	}

	// 保存已经读取过的开屏homepush
	public static void  setHomePushId(Context context, int id) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base2", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("home_push_id", id);
		editor.commit();
	}
	
	// 获得已经读取过的开屏homepush 剩余次数
	public static int getHomePushTimes(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base2", 0);
		int mytimes = setting.getInt("home_push_times", -1);
		
		return mytimes;//+200000
	}

	// 保存已经读取过的开屏homepush 剩余次数
	public static void  setHomePushTimes(Context context, int times) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base2", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("home_push_times", times);
		editor.commit();
	}
	
	/*----------------------保存周排行-------------------------*/
	//保存第一套卡片扫卡记录
	public static void setWeekRanking(Context context, String cardRecord) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		SharedPreferences.Editor editor = spScanCard1.edit();
		editor.putString("week_rank", cardRecord);
		editor.commit();
	}
	//读取第一套卡片扫卡记录
	public static String getWeekRanking(Context context) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		String cardRecord = spScanCard1.getString("week_rank", "");
		return cardRecord;
	}
	/*----------------------保存总排行-------------------------*/
	//保存第一套卡片扫卡记录
	public static void setAllRanking(Context context, String cardRecord) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		SharedPreferences.Editor editor = spScanCard1.edit();
		editor.putString("all_rank", cardRecord);
		editor.commit();
	}
	//读取第一套卡片扫卡记录
	public static String getAllRanking(Context context) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		String cardRecord = spScanCard1.getString("all_rank", "");
		return cardRecord;
	}
	
	
	
	//读取 拼读卡闯关 提示1
	public static boolean  getMagicGamePinduTishi1(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean islailai = setting.getBoolean("magic_game_tishi1", false);
		return islailai;
	}
	
	//保存 拼读卡闯关 提示1
	public static void  setMagicGamePinduTishi1(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("magic_game_tishi1", true);
		editor.commit();
	}
		
	//读取 拼读卡闯关 提示2
	public static boolean  getMagicGamePinduTishi2(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean islailai = setting.getBoolean("magic_game_tishi2", false);
		return islailai;
	}
	
	//保存 拼读卡闯关 提示2
	public static void  setMagicGamePinduTishi2(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("magic_game_tishi2", true);
		editor.commit();
	}
	//读取 拼读卡闯关 提示3
	public static boolean  getMagicGamePinduTishi3(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean islailai = setting.getBoolean("magic_game_tishi3", false);
		return islailai;
	}
	
	//保存 拼读卡闯关 提示3
	public static void  setMagicGamePinduTishi3(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("magic_game_tishi3", true);
		editor.commit();
	}
	//读取 拼读卡闯关 提示4
	public static boolean  getMagicGamePinduTishi4(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0);
		boolean islailai = setting.getBoolean("magic_game_tishi4", false);
		return islailai;
	}
	
	//保存 拼读卡闯关 提示4
	public static void  setMagicGamePinduTishi4(Context context) {
		SharedPreferences setting = context.getSharedPreferences(
				"common_base", 0); 
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("magic_game_tishi4", true);
		editor.commit();
	}
	/*----------------------保存外教课堂数据-------------------------*/
	//保存外教课堂数据
	public static void setEbookClassroom(Context context, String result) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		SharedPreferences.Editor editor = spScanCard1.edit();
		editor.putString("ebook_classroom", result);
		editor.commit();
	}
	//读取外教课堂数据
	public static String getEbookClassroom(Context context) {
		SharedPreferences spScanCard1 = context.getSharedPreferences(
				"common_base", 0);
		String cardRecord = spScanCard1.getString("ebook_classroom", "");
		return cardRecord;
	}	
	/*----------------------保存ebook价格-------------------------*/
	//保存ebook level aa/a/b价格  type == 1 aa;    type == 2 a;  type == 3 b;
	public static void setEbookPrice(Context context, int type, String original_all_price, String now_all_price, String original_one_price, String now_one_price) {
		SharedPreferences spLevelPrice = context.getSharedPreferences(
				"common_base", 0);
		SharedPreferences.Editor editor = spLevelPrice.edit();
		if(type == 1){
			editor.putString("3100000", original_all_price);
			editor.putString("3100000_2", now_all_price);
			editor.putString("3100004", original_one_price);
			editor.putString("3100004_2", now_one_price);
		}else if(type == 2){
			editor.putString("3200000", original_all_price);
			editor.putString("3200000_2", now_all_price);
			editor.putString("3200004", original_one_price);
			editor.putString("3200004_2", now_one_price);
		}else if(type == 3){
			editor.putString("3300000", original_all_price);
			editor.putString("3300000_2", now_all_price);
			editor.putString("3300004", original_one_price);
			editor.putString("3300004_2", now_one_price);
		}
		
		editor.commit();
	}
	//读取ebook level aa价格
	public static String[] getEbookPrice(Context context, int type) {
		SharedPreferences spLevelPrice = context.getSharedPreferences(
				"common_base", 0);
		String price[] = new String[4];
		if(type == 1){
			price[0] = spLevelPrice.getString("3100000", "");
			price[1] = spLevelPrice.getString("3100000_2", "");
			price[2] = spLevelPrice.getString("3100004", "");
			price[3] = spLevelPrice.getString("3100004_2", "");
		}else if(type == 2){
			price[0] = spLevelPrice.getString("3200000", "");
			price[1] = spLevelPrice.getString("3200000_2", "");
			price[2] = spLevelPrice.getString("3200004", "");
			price[3] = spLevelPrice.getString("3200004_2", "");
		}else if(type == 3){
			price[0] = spLevelPrice.getString("3300000", "");
			price[1] = spLevelPrice.getString("3300000_2", "");
			price[2] = spLevelPrice.getString("3300004", "");
			price[3] = spLevelPrice.getString("3300004_2", "");
		}
		 
		return price;
	}
	
}
