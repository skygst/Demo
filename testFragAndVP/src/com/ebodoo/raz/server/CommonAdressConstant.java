package com.ebodoo.raz.server;

public class CommonAdressConstant {
	
	
	/*------接口 必须跟access_token-------*/
	public final static String raz_profile = "raz/profile";		//获取等级和金币数量			参数-baby_id : Number
	public final static String raz_items = "raz/items";			//获取购买的服装		参数-baby_id : Number
	public final static String raz_modify_coin = "raz/modify_coin";	//增加或减少的金币数			参数-baby_id : Number   coin : Number	金币增量（可为负值） sign 签名
	public final static String raz_save_items = "raz/save_items";		//保存购买的服装		post	参数-baby_id : Number   item_ids : Number物品ID，格式为 ^\d+(,\d+)*$	金币增量（可为负值） sign 签名
	public final static String raz_save_level = "raz/save_level";	//保存等级		
	
	public final static String userInfo = "users/show";	//用户详细信息
	public final static String buyInfo = "raz/purchases";	//购买信息的状态
	public final static String unclock = "giftcodes/exchange";	//购买信息的状态

	public final static String audioList = "raz/user_audios/uploaded_list";	// 音频录音列表

	public final static String payment_create_order = "payment/create_order";	// 我的订单
	public final static String storeCheckOrder = "store/order_check";	// 检查订单
	
	public final static String game_save = "raz/game_save";	//保存国家的状态
	public final static String game_load = "raz/game_load";	//获得国家的状态
	public final static String book_save = "raz/book_save";	//获得图书的状态
	public final static String book_load = "raz/book_load";	//获得图书的状态
	public final static String upload_audio = "raz/upload_audio";	//上传mp3
	public final static String raz_videos = "raz/videos";	//上传mp3
	
	public final static String mallList = "raz/goods_list";	// 获取商城列表
	public final static String bindPhone = "users/bind_phone";	// 用户绑定手机
	public final static String basicVideo = "raz/basic_videos";	// 基础星球视频列表
	public final static String earVideo = "raz/wear_ear_audios";	// 磨耳朵音频列表

	public final static String wearLouly = "/raz/videos/loudly_list";	// 磨耳大声说

	// 阅读星球
	public final static String audiosDelete = "raz/user_audios/delete";	// 我的录音删除
	public final static String audiosBook = "raz/user_audios/uploaded_by_book";	// 某本书的我的录音列表
	public final static String audiosApplyReview = "raz/user_audios/apply_review";	// 提交点评
	public final static String audiosApplyCancel = "raz/user_audios/apply_cancel";	// 取消点评
	
	//首页广告
	public final static String homePush = "raz/home_push";	// 首页广告
	//魔法课堂排行榜
	public final static String ranking = "raz/toplist";	// 排行榜		//filter string 限定 'all' 'week' 分别对应总分与每周

	public final static String card_load = "raz/cardgame_load";	//获取游戏记录
	public final static String card_save = "raz/cardgame_save";	//上传游戏记录 stage int 关卡数量合计 ， star int 星星数量合计
	
	public final static String scancard_load = "raz/scancard_load";	//获取扫卡记录
	public final static String scancard_save = "raz/scancard_save";	//上传扫卡记录 baby_id int  card_id int   state string
	
		
	public final static String myRanking = "raz/my_ranking";	// 我的排名
	
	public final static String payment_products = "payment/products";	// 我的排名
	
	public final static String favList = "raz/wear_ear_fav_list";	// 我的收藏
	public final static String favAdd = "raz/wear_ear_fav_add";	// 添加收藏
	public final static String favDel = "raz/wear_ear_fav_del";	// 取消收藏
	public final static String videoList = "raz/video_list";	// 视频列表
	public final static String videoShow = "raz/video_show";	// 视频详情
	
	
	public final static String share_baseurl = "http://oauth.bbpapp.com/";		//http://oauth.bbpapp.com/raz/profile?baby_id=1301619&access_token=f60bb7cba2bd333fe2b5f2c3f569d722a8447252
}
