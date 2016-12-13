package com.ebodoo.raz.samples.SampleApplication.utils;


import android.os.Environment;

public class Constant {
	public static boolean willJihuo = false;
	//识别第几组图片——学习卡 = 1、拼读卡 = 2
	public static int studyGroup = 1;
	
	public static boolean isFlychess = false;
	
	//飞行棋 拼读相关记录
	public static String[] flychess_word; 		//拼读的所有的单词
	public static int flychess_word_num = 0;	//拼读播放总数量
	public static int index_play = 0;			//正在播第几个
	public static String isPlayingWord = "";	//正在播放的单词
	public static boolean[] flychess_word_play_status;	//是否播完，false-该视频还没有播完，true-该视频播放完了	

	public static boolean haveErweima = false;
	public static boolean haveUpdate = false;
	
	public static boolean haveMagicCard1 = false;
	
	public static String path_pinyipin = com.ebodoo.raz.utils.Constant.sdcard_path
			+ "/raz_english/pinyipin/";
	
	public static final int REQUESTCODE_MAGEIC_ENGLISH_FLYCHESS = 301;
	//是否是试一试
	//public static boolean isTry = false;
	
	
	//是从assert里面取资源吗？还是从sd卡里面读取
	public static boolean isAssert = false;
	
	public static int currentTarget = 0;
	
	public static int[] mTextureID;
	//从sd卡里面读取资源文件的路径
	public static String path_raz = com.ebodoo.raz.utils.Constant.sdcard_path
			+ "/raz_english/raz_card_video/";
	public static String path_raz_image = com.ebodoo.raz.utils.Constant.sdcard_path
			+ "/raz_english/raz_card_video/images/";
	
	public static String download_raz_card_video1 = "http://download.bbpapp.com/raz_maps/raz_card_video.zip";
	public static String download_raz_card_video0 = "http://download.bbpapp.com/raz_maps/raz_card_video0.zip";
	public static String download_raz_card_video2 = "http://download.bbpapp.com/raz_maps/raz_card_video2.zip";

	public static String download_raz_card_video3 = "http://download.bbpapp.com/raz_maps/raz_card_video3.zip";
	public static String download_raz_card_video4 = "http://download.bbpapp.com/raz_maps/raz_card_video4.zip";
	
	public static String download_raz_card_video5 = "http://download.bbpapp.com/raz_maps/raz_card_video5.zip";
	
	public static String download_raz_card_video_test = "http://download.bbpapp.com/raz_maps/raz_card_video_test.zip";
	
	public static String download_raz_card_video_newadd0 = "http://download.bbpapp.com/raz_maps/raz_card_video_newadd0.zip";
		
	
	public static String commonPath = com.ebodoo.raz.utils.Constant.sdcard_path + "/raz_english/";
	
	//每张拼图识别图的名称
		public static String scanImagePuzzle[] = {
			"b",
			"d",
			"g",	
			"l",
			
			"c",	
			"f",	
			"h",	
			"j",	
			"k",	
			
			"m",	
			"n",	
			"p",	
			"q",	
			"r",	
			
			"s",	       
			"t",	       
			"v",	       
			"w",	       
			"x",	       
			             
			"y",	       
			"z",	//	1~21
			           
			"ag",	       
			"am",	       
			"eg",	       
			"og",	       
			"ox",  
			
			"ad",	       
			"an",	       
			"ap",	       
			"at",	       
			"ell",       
			             
			"en",	       
			"et",	       
			"ill",       
			"in",	       
			"ip",	       
			             
			"it",	       
			"ob",	       
			"op",	       
			"ot",	       
			"ub",	       
			             
			"ug",	       
			"un",	       
			"ut",	// 21~44
			             
			//—————26个字母
			"aa",	       
			"bb",	       
			"cc",	       
			"dd",	       
			"ee",	       
			             
			"ff",	       
			"gg",	       
			"hh",	       
			"ii",	       
			"jj",	       
			             
			"kk",	       
			"ll",	       
			"mm",	       
			"nn",	       
			"oo",	       
			             
			"pp",	       
			"qq",	       
			"rr",	       
			"ss",	       
			"tt",	       
			             
			"uu",	       
			"vv",	       
			"ww",	       
			"xx",	       
			"yy",	       
			             
			"zz",	    //45~70  
			
			//—————26个字母
			"aa_2",	       
			"bb_2",	       
			"cc_2",	       
			"dd_2",	       
			"ee_2",	       
			             
			"ff_2",	       
			"gg_2",	       
			"hh_2",	       
			"ii_2",	       
			"jj_2",	       
			             
			"kk_2",	       
			"ll_2",	       
			"mm_2",	       
			"nn_2",	       
			"oo_2",	       
			             
			"pp_2",	       
			"qq_2",	       
			"rr_2",	       
			"ss_2",	       
			"tt_2",	       
			             
			"uu_2",	       
			"vv_2",	       
			"ww_2",	       
			"xx_2",	       
			"yy_2",	       
			             
			"zz_2",	    //45~70  
			
			"dilei",	//地雷
			"high",		//原地自high热舞  
//			"jiayi",	//加1
			"jiasan",	//加3
			"baoxiang",	//宝箱
	
	};
	//每张识别图的名称
	public static String scanImage[] = {
		"raz_01",
		"raz_02",
		"raz_03",
		"raz_04",
		"raz_05",
		"raz_06",
		"raz_07",
		"raz_08",
		"raz_09",
		"raz_10",
		"raz_11",
		"raz_12",
		"raz_13",
		"raz_14",
		"raz_15",
		"raz_16",
		"raz_17",
		"raz_18",
		"raz_19",
		"raz_20",
		"raz_21",
		"raz_22",
		"raz_23",
		"raz_24",
		"raz_25",
		"raz_26",
		"raz_27",
		"raz_28",
		"raz_29",
		"raz_30",
		"raz_31",
		"raz_32",
		"raz_33",
		"raz_34",
		"raz_35",
		"raz_36",
		"raz_37",
		"raz_38",
		"raz_39",
		"raz_40",
		"raz_41",
		"raz_42",
		"raz_43",
		"raz_44",
		"raz_45",
		"raz_46",
		"raz_47",
		"raz_48",
		"raz_49",
		"raz_50",
		"raz_51",
		"raz_52",
		"raz_53",
		"raz_54",
		"raz_55",
		"raz_56",
		"raz_57",
		"raz_58",
		"raz_59",
		"raz_60",
		"raz_61",
		"raz_62",
		"raz_63",
		"raz_64",
		"raz_65",
		"raz_66",
		"raz_67",
		"raz_68",
		"raz_69",
		"raz_70",
		"raz_71",
		"raz_72",
		"raz_73",
		"raz_74",
		"raz_75",
		"raz_76",
		"raz_77",
		"raz_78",
		"jihuoka",
		"christmas",
		"zhuanpan_1",
		"zhuanpan_2",
		"zhuanpan_1-2",
		"zhuanpan_2-2",
		"zhuanpan_1-3",
		"zhuanpan_1-4",
		"zhuanpan_2-3",
		"zhuanpan_2-4",
	};
	
	//每张识别图的名称
	public static String scanImageTraining1[] = {
		//—————26个字母
		"a_1",	       
		"b_1",	       
		"c_1",	       
		"d_1",	       
		"e_1",
		"f_1",	       
		"g_1",	       
		"h_1",	       
		"i_1",	       
		"j_1",	       
		             
		"k_1",	       
		"l_1",	       
		"m_1",	       
		"n_1",	       
		"o_1",	       
		             
		"p_1",	       
		"q_1",	       
		"r_1",	       
		"s_1",	       
		"t_1",	       
		             
		"u_1",	       
		"v_1",	       
		"w_1",	       
		"x_1",	       
		"y_1",	       
		             
		"z_1",	
		
		//—————26个字母
		"a_1_2",	       
		"b_1_2",	       
		"c_1_2",	       
		"d_1_2",	       
		"e_1_2",
		"f_1_2",	       
		"g_1_2",	       
		"h_1_2",	       
		"i_1_2",	       
		"j_1_2",	       
		             
		"k_1_2",	       
		"l_1_2",	       
		"m_1_2",	       
		"n_1_2",	       
		"o_1_2",	       
		             
		"p_1_2",	       
		"q_1_2",	       
		"r_1_2",	       
		"s_1_2",	       
		"t_1_2",	       
		             
		"u_1_2",	       
		"v_1_2",	       
		"w_1_2",	       
		"x_1_2",	       
		"y_1_2",	       
		             
		"z_1_2",	
	};
	//每张识别图的名称
	public static String scanImageTraining3[] = {
		
		//—————26个字母
		"aa",	       
		"bb",
		"cc",
		"dd",	       
		"ee",	       
		             
		"ff",	       
		"gg",	       
		"hh",	       
		"ii",	       
		"jj",	       
		             
		"kk",	       
		"ll",	       
		"mm",	       
		"nn",	       
		"oo",	       
		             
		"pp",	       
		"qq",	       
		"rr",	       
		"ss",	       
		"tt",	       
		             
		"uu",	       
		"vv",	       
		"ww",	       
		"xx",	       
		"yy",	       
		             
		"zz",	    //79~104   
		
		
		"bag_1",	       
		"cap_1",	       
		"box_1",	       
		"cap_1",	       
		"cat_1",	       
		             
		"cob_1",	       
		"cop_1",	       
		"cut_1",	       
		"dog_1",	       
		"fan_1",	       
		             
		"fat_1",	       
		"fell_1",	       
		"fen_1",	       
		"fin_1",	       
		"fit_1",	       
		             
		"fox_1",	       
		"fun_1",	       
		
		
		"gun_1",       
		"hen_1",	       
		"hill_1",	       
		"hop_1",	       
		"hot_1",	       
		             
		"hug_1",	       
		"jell_1",	       
		"jet_1",	       
		"kit_1",	       
		"leg_1",	       
		             
		"log_1",	
		
		"mad_1",	       
		"man_1",	      
		"mill_1",	       
		"mop_1",	       
		             
		"nap_1",	       
		"net_1",	       
		"not_1",  
		
		"pad_1",	       
		                 
		"pat_1",	       
		"pen_1",	       
		"pill_1",	       
		"pin_1",	       
		             
		"pot_1",	       
		"rip_1",	       
		"rot_1",	   
		"run_1",	       
		"sad_1",	       
		             
		"sell_1",	       
		"sit_1",	       
		"sob_1",	       
		"sun_1",	       
		             
		"tap_1",
        
		"tell_1",         
		"top_1",	       
		"tot_1",	       
		"tub_1",	       
		             
		"vet_1",	       
		"wap_1",	       
		"wet_1",	       
		"win_1",      
		             
		"yam_1",	       
		"zip_1",  
		"yolk_1",
		"well_1",
		"folk_1",
		
		"hug_1",
		"mug_1",
		"ten_1",
		
	       
		"sub_1",		       
		"sap_1",
		"rug_1",	  
		   
		"pan_1",		       
		"nut_1",		      
		"map_1",	  

		"mill_1",
		"pill_1",
		
		"nut_1",		      
		"map_1",	
		"mill_1",
		"pill_1",
	};	
	
	//高频字识别图的名称
	public static String scanImageGpz[] = {
		"and_apple",
		"away_bird",
		"a_baby",
		"big_bear",
		"blue_ball",
		"can_cow",
		"come_farm",
		"down_sun",
		"find_duck",
		"for_cake",
		"funny_car",
		"go_boy",
		"help_dog",
		"here_water",
		"in_box",
		"is_coat",
		"it_fish",
		"i_door",
		"jump_table",
		"little_girl",
		"look_night",
		"make_toy",
		"me_father",
		"my_feet",
		"not_home",
		"one_watch",
		"play_house",
		"red_girl",
		"run_horse",
		"said_time",
		"see_leg",
		"the_head",
		"three_egg",
		"to_milk",
		"two_eye",
		"up_tree",
		"we_school",
		"where_pig",
		"yellow_shoe",
		"you_snow",
		"citie-1",
		"citie-2",
		"citie-3",
		"citie-4",
		"citie",

	};
	//每张识别图的位置
	public static float videoQuadTextureCoordsTransformedRaz[][] = {
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	0
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	1
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	2
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	3
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	4
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	5
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	6
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	7
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	8
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	9
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	10
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	11
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	12
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	13
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	14
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	15
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	16
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	17
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	18
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	19
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	20
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	21
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	22
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	23
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	24
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	25
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	26
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	27
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	28
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	29
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	30
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	31
//		{ 0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 0.5f, 0.5f, 0.5f, },	//	31
		
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	32
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	33
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	34
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	35
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	36
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	37
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	38
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	39
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	40
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	41
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	42
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	43
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	44
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	45
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	46
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	47
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	48
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	49
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	50
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	51
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	52
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	53
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	54
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	55
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	56
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	57
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	58
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	59
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	60
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	61
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	62
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	63
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	64
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	65
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	66
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	67
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	68
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	69
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	70
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	71
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	72
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	73
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	74
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	75
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	76
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	77
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	78
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	79
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	80
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	81
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	82
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	83
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	84
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	85
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	86
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	87
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	88
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	89
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	90
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	91
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	92
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	93
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	94
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	95
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	96
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	97
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	98
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	99
		{ 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, },	//	100
    };
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
