package com.ebodoo.raz.utils;

/**
 *  欧洲帧节点
 * @author 
 *
 */
public class ConstantEp {
	
	public static String path_pinyipin = Constant.sdcard_path
			+ "/raz_english/pinyipin/";
	
	public static String path_reading01 = Constant.sdcard_path
			+ "/raz_english/reading01/"; // 欧洲
	
	public static String path_reading02 = Constant.sdcard_path
			+ "/raz_english/reading02/"; // 非洲
	
	public static String path_reading03 = Constant.sdcard_path
			+ "/raz_english/reading03/"; // 亚洲
	
	public static String path_reading04 = Constant.sdcard_path
			+ "/raz_english/reading04/"; // 美洲
	
	public static String path_level_a_game = Constant.sdcard_path
			+ "/raz_english/level_a_game/"; // levelA游戏
	
	
	public static String path_reading01_images = Constant.sdcard_path
			+ "/raz_english/reading01/images/";
	public static String path_reading02_images = Constant.sdcard_path
			+ "/raz_english/reading02/images/";
	public static String path_reading03_images = Constant.sdcard_path
			+ "/raz_english/reading03/images/";
	public static String path_reading04_images = Constant.sdcard_path
			+ "/raz_english/reading04/images/";
	
	public static String path_ebook_africa_images = Constant.sdcard_path
			+ "/raz_english/reaEbook02/images/";
	
	public static String path_ebook_levelc = Constant.sdcard_path
			+ "/raz_english/reaEbook04/";

	public static String path_ebook_images = Constant.sdcard_path
			+ "/raz_english/reaEbook01/images/";

	
	public static String download_reading01 = "http://download.bbpapp.com/raz_maps/Europe_android.zip";
	public static String download_ebook01 = "http://download.bbpapp.com/raz_maps/ebook1_android.zip";
	public static String download_ebook_images = "http://download.bbpapp.com/raz_maps/reaebook_images.zip";
	
	public static String download_ebook02 = "http://download.bbpapp.com/raz_maps/reaEbook02.zip";
	public static String download_ebook03 = "http://download.bbpapp.com/raz_maps/reaEbook03.zip";
	//高频字下载
	public static String download_sightword01 = "http://download.bbpapp.com/raz_maps/ebook1_sightword_android.zip";
	public static String download_sightword01_2 = "http://download.bbpapp.com/raz_maps/ebook1_sightword_android2.zip";
	public static String download_sightword01_all = "http://download.bbpapp.com/raz_maps/ebook1_sightword_android_all.zip";
	
	// 下载每一个国家资源
	public static String download_common = "http://download.bbpapp.com/raz_maps/";
	public static String download_country_england = download_common + "ad_country_1.zip";
	public static String download_country_spain = download_common + "ad_country_2.zip";
	public static String download_country_france = download_common + "ad_country_3.zip";
	public static String download_country_netherlands = download_common + "ad_country_4.zip";
	public static String download_country_italy = download_common + "ad_country_5.zip";
	
	public static String download_country_Egypt = download_common + "ad_country_6.zip";
	public static String download_country_Kenya = download_common + "ad_country_7.zip";
	public static String download_country_Namibia = download_common + "ad_country_8.zip";
	public static String download_country_Madagascar = download_common + "ad_country_9.zip";
	public static String download_country_Mauritius = download_common + "ad_country_10.zip";
	
	public static String download_country_Xjp = download_common + "ad_country_11.zip";
	public static String download_country_thailand = download_common + "ad_country_12.zip";
	public static String download_country_india = download_common + "ad_country_13.zip";
	public static String download_country_china = download_common + "ad_country_14.zip";
	public static String download_country_japan = download_common + "ad_country_15.zip";
	
	
	// 26个字母资源下载
	public static String download_letters_res = download_common + "letters.zip";
	
	public static int timeEpLevel1_0[][] = // 荷兰学习
	{
		{42 ,1000 ,0,1,0},//停桢1s
		{1042 ,32542 ,1,3,0},//剧情
		{32583 ,33458 ,2,3,0},//停桢1s
		{33500 ,35500 ,3,5,1},//循环*
		{35542 ,36417 ,4,5,0},//停桢1s
		{36458 ,38458 ,5,7,0},//剧情
		{38500 ,39417 ,6,7,0},//停桢1s
		{39458 ,41458 ,7,9,1},//循环*
		{41500 ,42417 ,8,9,0},//停桢1s
		{42458 ,44458 ,9,11,0},//剧情
		{44500 ,45417 ,10,11,0},//停桢1s
		{45458 ,47458 ,11,13,1},//循环*
		{47500 ,48417 ,12,13,0},//停桢1s
		{48458 ,50458 ,13,15,0},//剧情
		{50500 ,51417 ,14,15,0},//停桢1s
		{51458 ,53458 ,15,17,1},//循环*
		{53500 ,54417 ,16,17,0},//停桢1s
		{54458 ,56458 ,17,19,0},//剧情
		{56500 ,57417 ,18,19,0},//停桢1s
		{57458 ,59458 ,19,21,1},//循环*
		{59500 ,60417 ,20,21,0},//停桢1s
		{60458 ,62458 ,21,23,0},//剧情
		{62500 ,63417 ,22,23,0},//停桢1s
		{63458 ,65458 ,23,25,1},//循环*
		{65500 ,66417 ,24,25,0},//停桢1s
		{66458 ,68458 ,25,27,0},//剧情
		{68500 ,69417 ,26,27,0},//停桢1s
		{69458 ,71458 ,27,29,1},//循环*
		{71500 ,72417 ,28,29,0},//停桢1s
		{72458 ,74458 ,29,31,0},//剧情
		{74500 ,75417 ,30,31,0},//停桢1s
		{75458 ,77458 ,31,33,1},//循环*
		{77500 ,78417 ,32,33,0},//停桢1s
		{78458 ,80458 ,33,35,0},//剧情
		{80500 ,81417 ,34,35,0},//停桢1s
		{81458 ,83458 ,35,37,1},//循环*
		{83500 ,84417 ,36,37,0},//停桢1s
		{84458 ,86458 ,37,39,0},//剧情
		{86500 ,87417 ,38,39,0},//停桢1s
		{87458 ,89458 ,39,41,1},//循环*
		{89500 ,90417 ,40,41,0},//停桢1s
		{90458 ,108625 ,41,44,0},//剧情
		{108667 ,109583 ,42,44,0},//停桢1s
		{109625 ,110542 ,43,44,0},//停桢1s
		{110583 ,111500 ,44,47,1},//循环*
		{111542 ,112458 ,45,47,0},//停桢1s
		{112500 ,113417 ,46,47,0},//停桢1s
		{113458 ,117250 ,47,49,0},//读音一     infant 粉红色box
		{117292 ,118167 ,48,49,0},//停桢1s
		{118208 ,119083 ,49,51,1},//循环*
		{119125 ,120000 ,50,51,0},//停桢1s
		{120042 ,126542 ,51,54,0},//读音二
		{126583 ,127500 ,52,54,0},//停桢1s
		{127542 ,128458 ,53,54,0},//停桢1s
		{110583 ,111500 ,54,56,1},//循环*
		{111542 ,112458 ,55,56,0},//停桢1s
		{128500 ,132292 ,56,58,0},//读音一  ink 黄色box
		{132333 ,133250 ,57,58,0},//停桢1s
		{133292 ,134208 ,58,60,1},//循环*
		{134250 ,135167 ,59,60,0},//停桢1s
		{135208 ,141708 ,60,63,0},//读音二
		{141750 ,142667 ,61,63,0},//停桢1s
		{142708 ,143625 ,62,63,0},//停桢1s
		{110583 ,111500 ,63,65,1},//循环*
		{111542 ,112458 ,64,65,0},//停桢1s
		{143667 ,147458 ,65,67,0},//读音一  incenct 绿色box
		{147500 ,148417 ,66,67,0},//停桢1s
		{148458 ,149375 ,67,69,1},//循环*
		{149417 ,150333 ,68,69,0},//停桢1s
		{150375 ,156875 ,69,72,0},//读音二
		{156917 ,157833 ,70,72,0},//停桢1s
		{157875 ,158792 ,71,72,0},//停桢1s
		{110583 ,111500 ,72,74,1},//循环*
		{111542 ,112458 ,73,74,0},//停桢1s
		{158833 ,162625 ,74,76,0},//读音一 igloo 淡蓝色box
		{162667 ,163583 ,75,76,0},//停桢1s
		{163625 ,164542 ,76,78,1},//循环*
		{164583 ,165500 ,77,78,0},//停桢1s
		/*{165542 ,175042 ,78,78,0},//读音二
		{175083 ,176000 ,79,78,0}//停桢1s
*/		
		{165542 ,172083 ,78,80,0},//读音二
		{172125 ,173042 ,79,80,0},//停桢1s
		{110583 ,111500 ,80,82,1},//循环*
		{111542 ,112458 ,81,82,0},//停桢1s
		{173083 ,176000 ,82,82,0},//剧情
		{176042 ,176958 ,83,82,0},//停桢1s

	};

	public static int timeEpLevel1_1[][] = // 荷兰复习1
	{
		{ 42 , 2000 , 0 , 1 , 0 } , //	停桢1s
		{ 1042 , 3000 , 1 , 3 , 1 } , //	循环*	貌似有旁白
		{ 3042 , 4000 , 2 , 3 , 0 } , //	停桢1s
		{ 4042 , 8042 , 3 , 5 , 0 } , //	剧情
		{ 8083 , 9042 , 4 , 5 , 0 } , //	停桢1s
		{ 1042 , 3000 , 5 , 7 , 1 } , //	循环*	貌似有旁白
		{ 3042 , 4000 , 6 , 7 , 0 } , //	停桢1s
		{ 4042 , 8042 , 7 , 9 , 0 } , //	剧情
		{ 8083 , 9042 , 8 , 9 , 0 } , //	停桢1s
		{ 1042 , 3000 , 9 , 11 , 1 } , //	循环*	貌似有旁白
		{ 3042 , 4000 , 10 , 11 , 0 } , //	停桢1s
		{ 4042 , 8042 , 11 , 13 , 0 } , //	剧情
		{ 8083 , 9042 , 12 , 13 , 0 } , //	停桢1s
		{ 1042 , 3000 , 13 , 15 , 1 } , //	循环*	貌似有旁白
		{ 3042 , 4000 , 14 , 15 , 0 } , //	停桢1s
		{ 4042 , 8042 , 15 , 17 , 0 } , //	剧情
		{ 8083 , 9042 , 16 , 17 , 0 } , //	停桢1s
		{ 1042 , 3000 , 17 , 19 , 1 } , //	循环*	貌似有旁白
		{ 3042 , 4000 , 18 , 19 , 0 } , //	停桢1s
		{ 4042 , 8042 , 19 , 21 , 0 } , //	剧情
		{ 8083 , 9042 , 20 , 21 , 0 } , //	停桢1s
		{ 1042 , 3000 , 21 , 23 , 1 } , //	循环*	貌似有旁白
		{ 3042 , 4000 , 22 , 23 , 0 } , //	停桢1s
		{ 4042 , 8042 , 23 , 25 , 0 } , //	剧情
		{ 8083 , 9042 , 24 , 25 , 0 } , //	停桢1s
		{ 1042 , 3000 , 25 , 27 , 1 } , //	循环*	貌似有旁白
		{ 3042 , 4000 , 26 , 27 , 0 } , //	停桢1s
		{ 4042 , 8042 , 27 , 29 , 0 } , //	剧情
		{ 8083 , 9042 , 28 , 29 , 0 } , //	停桢1s
		{ 1042 , 3000 , 29 , 31 , 1 } , //	循环*	貌似有旁白
		{ 3042 , 4000 , 30 , 31 , 0 } , //	停桢1s
		{ 4042 , 8042 , 31 , 32 , 0 } , //	剧情
		{ 1042 , 2000 , 32 , 33 , 0 } , //	停桢1s
		{ 1042 , 2000 , 33 , 33 , 0 } , //	停桢1s
	};

	public static int timeEpLevel1_2[][] = // 荷兰复习2
	{
		{42,1000,0,1,0},//停桢1s…………0
		{1042,4208,1,3,0},//剧情…………1
		{4250,5208,2,3,0},//停桢1s…………2
		{5250,6208,3,5,1},//循环*…………3
		{6250,7208,4,5,0},//停桢1s…………4
		{1042,4208,5,7,0},//剧情…………5
		{4250,5208,6,7,0},//停桢1s…………6
		{5250,6208,7,9,1},//循环*…………7
		{6250,7208,8,9,0},//停桢1s…………8
		{7250,11208,9,11,0},//剧情…………9
		{6250,7208,10,11,0},//停桢1s…………10
		{1042,4208,11,13,0},//剧情…………11
		{11250,12167,12,13,0},//停桢1s…………12
		{5250,6208,13,15,1},//循环*…………13
		{6250,7208,14,15,0},//停桢1s…………14
		{1042,4208,15,17,0},//剧情…………15
		{4250,5208,16,17,0},//停桢1s…………16
		{5250,6208,17,19,1},//循环*…………17
		{6250,7208,18,19,0},//停桢1s…………18
		{7250,11208,19,21,0},//剧情…………19
		{6250,7208,20,21,0},//停桢1s…………20
		{1042,4208,21,23,0},//剧情…………21
		{11250,12167,22,23,0},//停桢1s…………22
		{5250,6208,23,25,1},//循环*…………23
		{6250,7208,24,25,0},//停桢1s…………24
		{1042,4208,25,27,0},//剧情…………25
		{4250,5208,26,27,0},//停桢1s…………26
		{5250,6208,27,29,1},//循环*…………27
		{6250,7208,28,29,0},//停桢1s…………28
		{7250,11208,29,31,0},//剧情…………29
		{6250,7208,30,31,0},//停桢1s…………30
		{1042,4208,31,33,0},//剧情…………31
		{11250,12167,32,33,0},//停桢1s…………32
		{5250,6208,33,35,1},//循环*…………33
		{6250,7208,34,35,0},//停桢1s…………34
		{1042,4208,35,37,0},//剧情…………35
		{4250,5208,36,37,0},//停桢1s…………36
		{5250,6208,37,39,1},//循环*…………37
		{6250,7208,38,39,0},//停桢1s…………38
		{7250,11208,39,40,0},//剧情…………39
		{11250,12167,40,40,0},//停桢1s…………40

	};
	
	public static int timeEpLevel2_0[][] = // 英国学习
		{
			{42 ,1000 ,0,1,0},//停桢1s
			{1042 ,37625 ,1,3,0},//剧情
			{37667 ,38625 ,2,3,0},//停桢1s
			{38667 ,40625 ,3,5,1},//循环*
			{40667 ,41625 ,4,5,0},//停桢1s
			{41667 ,43625 ,5,7,0},//剧情
			{43667 ,44625 ,6,7,0},//停桢1s
			{38667 ,40625 ,7,9,1},//循环*第二遍
			{40667 ,41625 ,8,9,0},//停桢1s
			{41667 ,43625 ,9,11,0},//剧情
			{43667 ,44625 ,10,11,0},//停桢1s
			{38667 ,40625 ,11,13,1},//循环*第三遍
			{40667 ,41625 ,12,13,0},//停桢1s
			{41667 ,43625 ,13,15,0},//剧情
			{43667 ,44625 ,14,15,0},//停桢1s
			{38667 ,40625 ,15,17,1},//循环*第四遍
			{40667 ,41625 ,16,17,0},//停桢1s
			{41667 ,43625 ,17,19,0},//剧情
			{43667 ,44625 ,18,19,0},//停桢1s
			{38667 ,40625 ,19,21,1},//循环*第五遍
			{40667 ,41625 ,20,21,0},//停桢1s
			{41667 ,43625 ,21,23,0},//剧情
			{43667 ,44625 ,22,23,0},//停桢1s
			{38667 ,40625 ,23,25,1},//循环*第六遍
			{40667 ,41625 ,24,25,0},//停桢1s
			{41667 ,43625 ,25,27,0},//剧情
			{43667 ,44625 ,26,27,0},//停桢1s
			{38667 ,40625 ,27,29,1},//循环*第七遍
			{40667 ,41625 ,28,29,0},//停桢1s
			{41667 ,43625 ,29,31,0},//剧情
			{43667 ,44625 ,30,31,0},//停桢1s
			{38667 ,40625 ,31,33,1},//循环*第八遍
			{40667 ,41625 ,32,33,0},//停桢1s
			{41667 ,43625 ,33,35,0},//剧情
			{43667 ,44625 ,34,35,0},//停桢1s
			{38667 ,40625 ,35,37,1},//循环*第九遍
			{40667 ,41625 ,36,37,0},//停桢1s
			{41667 ,43625 ,37,39,0},//剧情
			{43667 ,44625 ,38,39,0},//停桢1s
			{38667 ,40625 ,39,41,1},//循环*第九遍
			{40667 ,41625 ,40,41,0},//停桢1s
			{44667 ,62958 ,41,44,0},//last剧情此处包含前面结尾
			{63000 ,63917 ,42,44,0},//停桢1s
			{63958 ,64917 ,43,44,0},//停桢1s
			{64958 ,66917 ,44,47,1},//循环*音符随机点击 共用循环
			{66958 ,67917 ,45,47,0},//停桢1s
			{67958 ,68875 ,46,47,0},//停桢1s
			{68917 ,74500 ,47,49,0},//读音一bus
			{74542 ,75500 ,48,49,0},//停桢1s
			{75542 ,77500 ,49,51,1},//循环*
			{77542 ,78500 ,50,51,0},//停桢1s
			{78542 ,81625 ,51,53,0},//读音二bus
			{81667 ,82625 ,52,53,0},//停桢1s
			{82667 ,84625 ,53,55,1},//循环*
			{84667 ,85625 ,54,55,0},//停桢1s
			{85667 ,90000 ,55,57,0},//读音三bus
			{90042 ,91000 ,56,57,0},//停桢1s
			{64958 ,66917 ,57,59,1},//循环*音符随机点击 共用循环
			{66958 ,67917 ,58,59,0},//停桢1s
			{91042 ,96625 ,59,61,0},//读音一bag
			{96667 ,97625 ,60,61,0},//停桢1s
			{97667 ,99625 ,61,63,1},//循环*
			{99667 ,100625 ,62,63,0},//停桢1s
			{100667 ,103750 ,63,65,0},//读音二bag
			{103792 ,104750 ,64,65,0},//停桢1s
			{104792 ,106750 ,65,67,1},//循环*
			{106792 ,107750 ,66,67,0},//停桢1s
			{107792 ,112417 ,67,69,0},//读音三bag
			{112458 ,113417 ,68,69,0},//停桢1s
			{64958 ,66917 ,69,71,1},//循环*音符随机点击 共用循环
			{66958 ,67917 ,70,71,0},//停桢1s
			{113458 ,119042 ,71,73,0},//读音一bananan
			{119083 ,120042 ,72,73,0},//停桢1s
			{120083 ,122042 ,73,75,1},//循环*
			{122083 ,123042 ,74,75,0},//停桢1s
			{123083 ,126167 ,75,77,0},//读音二bananan
			{126208 ,127167 ,76,77,0},//停桢1s
			{127208 ,129167 ,77,79,1},//循环*
			{129208 ,130167 ,78,79,0},//停桢1s
			{130208 ,134833 ,79,81,0},//读音三bananan
			{134875 ,135833 ,80,81,0},//停桢1s
			{64958 ,66917 ,81,83,1},//循环*音符随机点击 共用循环
			{66958 ,67917 ,82,83,0},//停桢1s
			{135875 ,141458 ,83,85,0},//读音一big
			{141500 ,142458 ,84,85,0},//停桢1s
			{142500 ,144458 ,85,87,1},//循环*
			{144500 ,145458 ,86,87,0},//停桢1s
			{145500 ,148583 ,87,89,0},//读音二big
			{148625 ,149583 ,88,89,0},//停桢1s
			{149625 ,151583 ,89,91,1},//循环*
			{151625 ,152583 ,90,91,0},//停桢1s
			{152625 ,157250 ,91,93,0},//读音三big
			{157292 ,158208 ,92,93,0},//停桢1s
			{64958 ,66917 ,93,94,1},//循环*音符随机点击 共用循环
			{66958 ,67917 ,94,94,0},//停桢1s
		};
	
	public static int timeEpLevel2_2[][] = // 英国复习2
	{
		{42,1000,0,1,0},//停桢1s…………0
		{1042,6375,1,3,0},//剧情…………1
		{6417,7375,2,3,0},//停桢1s…………2
		{7417,8375,3,5,1},//循环*…………3
		{6417,7375,4,5,0},//停桢1s…………4
		{7417,8375,5,7,1},//循环*…………5
		{6417,7375,6,7,0},//停桢1s…………6
		{7417,8375,7,9,1},//循环*…………7
		{8417,10375,8,9,0},//停桢1s…………8
		{10417,12125,9,11,0},//剧情…………9
		{6417,7375,10,11,0},//停桢1s…………10
		{7417,8375,11,13,1},//循环*…………11
		{6417,7375,12,13,0},//停桢1s…………12
		{7417,8375,13,15,1},//循环*…………13
		{12167,13125,14,15,0},//停桢1s…………14
		{7417,8375,15,17,1},//循环*…………15
		{8417,10375,16,17,0},//停桢1s…………16
		{13167,15208,17,19,0},//剧情…………17
		{6417,7375,18,19,0},//停桢1s…………18
		{7417,8375,19,21,1},//循环*…………19
		{6417,7375,20,21,0},//停桢1s…………20
		{7417,8375,21,23,1},//循环*…………21
		{15250,16208,22,23,0},//停桢1s…………22
		{7417,8375,23,25,1},//循环*…………23
		{8417,10375,24,25,0},//停桢1s…………24
		{16250,18250,25,27,0},//剧情…………25
		{18292,19250,26,27,0},//停桢1s…………26
		{19292,21333,27,29,0},//剧情…………27
		{21375,22292,28,29,0},//停桢1s…………28
		{7417,8375,29,31,1},//循环*…………29
		{6417,7375,30,31,0},//停桢1s…………30
		{7417,8375,31,33,1},//循环*…………31
		{12167,13125,32,33,0},//停桢1s…………32
		{7417,8375,33,35,1},//循环*…………33
		{8417,10375,34,35,0},//停桢1s…………34
		{22333,30167,35,36,0},//剧情…………35
		{30208,31167,36,36,0},//停桢1s…………36
	};
	
	
	public static int timeEpLevel2_1[][]={          //英国复习1
		{ 42	, 1000	, 0	, 1	, 0 } ,  // 停桢1s
		{ 1042	, 7917	, 1	, 3	, 0 } ,  // 剧情
		{ 7958	, 8958	, 2	, 3	, 0 } ,  // 停桢1s	
		{ 9000	, 9958	, 3	, 6	, 1 } ,  // 循环*	出题 共用循环		C2-4、E2-4
		{ 10000	, 11000	, 4	, 6	, 0 } ,  // 停桢1s	
		{ 11042	, 12000	, 5	, 6	, 0 } ,  // 停桢1s
		{ 12042	, 17708	, 6	, 9	, 0 } ,  // 剧情	第一个
		{ 17750	, 20792	, 7	, 9	, 0 } ,  // 停桢1s	
		{ 7958	, 8958	, 8	, 9	, 0 } ,  // 停桢1s	
		{ 9000	, 9958	, 9	, 12, 1 } ,  // 循环*	出题 共用循环		C2-4、E2-4
		{ 10000	, 11000	, 10, 12, 0 } ,  // 停桢1s	
		{ 20833	, 19708	, 11, 12, 0 } ,  // 停桢1s	
		{ 19750	, 24417	, 12, 15, 0 } ,  // 剧情	第二、三、四。。。个
		{ 24458	, 25417	, 13, 15, 0 } ,  // 停桢1s	
		{ 7958	, 8958	, 14, 15, 0 } ,  // 停桢1s	
		{ 9000	, 9958	, 15, 18, 1 } ,  // 循环*	出题 共用循环		C2-4、E2-4
		{ 10000	, 11000	, 16, 18, 0 } ,  // 停桢1s	
		{ 20833	, 19708	, 17, 18, 0 } ,  // 停桢1s	
		{ 19750	, 24417	, 18, 21, 0 } ,  // 剧情	第二、三、四。。。个
		{ 24458	, 25417	, 19, 21, 0 } ,  // 停桢1s	
		{ 7958	, 8958	, 20, 21, 0 } ,  // 停桢1s	
		{ 9000	, 9958	, 21, 24, 1 } ,  // 循环*	出题 共用循环		C2-4、E2-4
		{ 10000	, 11000	, 22, 24, 0 } ,  // 停桢1s	
		{ 20833	, 19708	, 23, 24, 0 } ,  // 停桢1s	
		{ 19750	, 24417	, 24, 27, 0 } ,  // 剧情	第二、三、四。。。个
		{ 24458	, 25417	, 25, 27, 0 } ,  // 停桢1s	
		{ 7958	, 8958	, 26, 27, 0 } ,  // 停桢1s	
		{ 9000	, 9958	, 27, 30, 1 } ,  // 循环*	出题 共用循环		C2-4、E2-4
		{ 10000	, 11000	, 28, 30, 0 } ,  // 停桢1s	
		{ 20833	, 19708	, 29, 30, 0 } ,  // 停桢1s	
		{ 19750	, 24417	, 30, 33, 0 } ,  // 剧情	第二、三、四。。。个
		{ 24458	, 25417	, 31, 33, 0 } ,  // 停桢1s	
		{ 7958	, 8958	, 32, 33, 0 } ,  // 停桢1s	
		{ 9000	, 9958	, 33, 36, 1 } ,  // 循环*	出题 共用循环		C2-4、E2-4
		{ 10000	, 11000	, 34, 36, 0 } ,  // 停桢1s	
		{ 20833	, 19708	, 35, 36, 0 } ,  // 停桢1s	
		{ 19750	, 24417	, 36, 39, 0 } ,  // 剧情	第二、三、四。。。个
		{ 24458	, 25417	, 37, 39, 0 } ,  // 停桢1s	
		{ 7958	, 8958	, 38, 39, 0 } ,  // 停桢1s	
		{ 9000	, 9958	, 39, 42, 1 } ,  // 循环*	出题 共用循环		C2-4、E2-4
		{ 10000	, 11000	, 40, 42, 0 } ,  // 停桢1s	
		{ 20833	, 19708	, 41, 42, 0 } ,  // 停桢1s	
		{ 19750	, 24417	, 42, 45, 0 } ,  // 剧情	第二、三、四。。。个
		{ 24458	, 25417	, 43, 45, 0 } ,  // 停桢1s	
		{ 7958	, 8958	, 44, 45, 0 } ,  // 停桢1s	
		{ 9000	, 9958	, 45, 48, 1 } ,  // 循环*	出题 共用循环		C2-4、E2-4
		{ 10000	, 11000	, 46, 48, 0 } ,  // 停桢1s	
		{ 25458	, 26417	, 47, 48, 0 } ,  // 停桢1s	
		{ 26458	, 33958	, 48, 49, 0 } ,  // 剧情	最后一个
		{ 34000	, 34958	, 49, 50, 0 } ,  // 停桢1s	
		{ 34000	, 34958	, 50, 50, 0 } ,  // 停桢1s	

	};
	
	public static int timeEpLevel3_0[][] ={ // 法国学习
		{42 ,1000 ,0,1,0},//停桢1s
		{1042 ,38625 ,1,3,0},//剧情
		{38667 ,39667 ,2,3,0},//停桢1s
		{39708 ,41708 ,3,5,1},//循环------*捡东西 共用循环1
		{41750 ,42750 ,4,5,0},//停桢1s
		{42792 ,45833 ,5,7,0},//剧情------捡东西 共用剧情1
		{45875 ,46875 ,6,7,0},//停桢1s
		{46917 ,48917 ,7,9,1},//循环------*捡东西 共用循环2
		{48958 ,49958 ,8,9,0},//停桢1s
		{50000 ,53042 ,9,12,0},//剧情------捡东西 共用剧情2
		{53083 ,54042 ,10,12,0},//停桢1s
		{54083 ,55042 ,11,12,0},//停桢1s
		{55083 ,57083 ,12,15,0},//剧情------捡到石头了
		{57125 ,58083 ,13,15,0},//停桢1s
		{58125 ,59042 ,14,15,0},//停桢1s
		{59083 ,75083 ,15,17,0},//last剧情与 ------公用循环2 背景相同
		{75125 ,76083 ,16,17,0},//停桢1s
		{76125 ,77125 ,17,20,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,18,20,0},//停桢1s
		{78208 ,79125 ,19,20,0},//停桢1s
		{79367 ,82167 ,20,23,0},//读音一hit
		{82208 ,83125 ,21,23,0},//停桢1s
		{83167 ,84042 ,22,23,0},//停桢1s
		{76125 ,77125 ,23,25,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,24,25,0},//停桢1s
		{83683 ,87042 ,25,28,0},//读音一sit
		{87083 ,88000 ,26,28,0},//停桢1s
		{88042 ,88958 ,27,28,0},//停桢1s
		{76125 ,77125 ,28,30,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,29,30,0},//停桢1s
		{88500 ,91958 ,30,33,0},//读音一kit
		{92000 ,92917 ,31,33,0},//停桢1s
		{92958 ,93875 ,32,33,0},//停桢1s
		{76125 ,77125 ,33,35,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,34,35,0},//停桢1s
		{93417 ,96875 ,35,38,0},//读音一pit
		{96917 ,97833 ,36,38,0},//停桢1s
		{97875 ,98792 ,37,38,0},//停桢1s
		{76125 ,77125 ,38,40,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,39,40,0},//停桢1s
		{98333 ,101792 ,40,43,0},//读音一tin
		{101833 ,102750 ,41,43,0},//停桢1s
		{102792 ,103708 ,42,43,0},//停桢1s
		{76125 ,77125 ,43,45,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,44,45,0},//停桢1s
		{103250 ,106708 ,45,48,0},//读音一fin
		{106750 ,107667 ,46,48,0},//停桢1s
		{107708 ,108625 ,47,48,0},//停桢1s
		{76125 ,77125 ,48,50,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,49,50,0},//停桢1s
		{108167 ,111625 ,50,53,0},//读音一pin
		{111667 ,112583 ,51,53,0},//停桢1s
		{112625 ,113542 ,52,53,0},//停桢1s
		{76125 ,77125 ,53,55,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,54,55,0},//停桢1s
		{113083 ,116542 ,55,58,0},//读音一win
		{116583 ,117500 ,56,58,0},//停桢1s
		{117542 ,118458 ,57,58,0},//停桢1s
		{76125 ,77125 ,58,60,1},//循环------*勋章点击 共用循环
		{77167 ,78167 ,59,60,0},//停桢1s
		{118500 ,121667 ,60,61,0},//剧情
		{121708 ,122583 ,61,61,0},//停桢1s

	};
	public static int timeEpLevel4_2[][] ={ // 西班牙学习
		
		{42,1000,0,1,0},//停桢1s………0
		{1042,3000,1,3,1},//循环*………1
		{3042,4000,2,3,0},//停桢1s………2
		{1042,3000,3,5,1},//循环*………3
		{3042,4000,4,5,0},//停桢1s………4
		{1042,3000,5,7,1},//循环*………5
		{3042,4000,6,7,0},//停桢1s………6
		{4042,7542,7,9,0},//剧情………7
		{7583,8500,8,9,0},//停桢1s………8
		{1042,3000,9,11,1},//循环*………9
		{3042,4000,10,11,0},//停桢1s………10
		{1042,3000,11,13,1},//循环*………11
		{3042,4000,12,13,0},//停桢1s………12
		{1042,3000,13,15,1},//循环*………13
		{3042,4000,14,15,0},//停桢1s………14
		{4042,7542,15,17,0},//剧情………15
		{7583,8500,16,17,0},//停桢1s………16
		{1042,3000,17,19,1},//循环*………17
		{3042,4000,18,19,0},//停桢1s………18
		{1042,3000,19,21,1},//循环*………19
		{3042,4000,20,21,0},//停桢1s………20
		{1042,3000,21,23,1},//循环*………21
		{3042,4000,22,23,0},//停桢1s………22
		{4042,7542,23,25,0},//剧情………23
		{7583,8500,24,25,0},//停桢1s………24
		{1042,3000,25,27,1},//循环*………25
		{3042,4000,26,27,0},//停桢1s………26
		{1042,3000,27,29,1},//循环*………27
		{3042,4000,28,29,0},//停桢1s………28
		{1042,3000,29,31,1},//循环*………29
		{3042,4000,30,31,0},//停桢1s………30
		{4042,7542,31,32,0},//剧情………31
		{7583,8500,32,32,0},//停桢1s………32

	};
	public static int timeEpLevel5_2[][] ={ // 意大利学习
		{42,1000,0,1,0},//停桢1s………0
		{1042,9000,1,3,0},//剧情………1
		{9042,10917,2,3,0},//停桢1s………2
		{10958,11917,3,5,1},//循环*………3
		{11958,13833,4,5,0},//停桢1s………4
		{10958,11917,5,7,1},//循环*………5
		{11958,13833,6,7,0},//停桢1s………6
		{10958,11917,7,9,1},//循环*………7
		{11958,13833,8,9,0},//停桢1s………8
		{13875,22792,9,11,0},//剧情………9
		{22833,23667,10,11,0},//停桢1s………10
		{10958,11917,11,13,1},//循环*………11
		{11958,13833,12,13,0},//停桢1s………12
		{10958,11917,13,15,1},//循环*………13
		{11958,13833,14,15,0},//停桢1s………14
		{10958,11917,15,17,1},//循环*………15
		{11958,13833,16,17,0},//停桢1s………16
		{13875,22792,17,19,0},//剧情………17
		{22833,23667,18,19,0},//停桢1s………18
		{10958,11917,19,21,1},//循环*………19
		{11958,13833,20,21,0},//停桢1s………20
		{10958,11917,21,23,1},//循环*………21
		{11958,13833,22,23,0},//停桢1s………22
		{10958,11917,23,25,1},//循环*………23
		{11958,13833,24,25,0},//停桢1s………24
		{13875,22792,25,27,0},//剧情………25
		{22833,23667,26,27,0},//停桢1s………26
		{10958,11917,27,29,1},//循环*………27
		{11958,13833,28,29,0},//停桢1s………28
		{10958,11917,29,31,1},//循环*………29
		{11958,13833,30,31,0},//停桢1s………30
		{10958,11917,31,33,1},//循环*………31
		{11958,13833,32,33,0},//停桢1s………32
		{13875,22792,33,34,0},//剧情………33
		{22833,23667,34,34,0},//停桢1s………34
	};
	
	public static int timeEpLevel3_2[][] ={ // 法国学习
		{42,1000,0,1,0},//停桢1s…………0
		{1042,2083,1,3,1},//循环*…………1
		{10333,11292,2,3,0},//停桢1s…………2
		{1042,2083,3,5,1},//循环*…………3
		{10333,11292,4,5,0},//停桢1s…………4
		{1042,2083,5,7,1},//循环*…………5
		{2125,4042,6,7,0},//停桢1s…………6
		{4083,10292,7,9,0},//剧情…………7
		{10333,11292,8,9,0},//停桢1s…………8
		{1042,2083,9,11,1},//循环*…………9
		{10333,11292,10,11,0},//停桢1s…………10
		{1042,2083,11,13,1},//循环*…………11
		{10333,11292,12,13,0},//停桢1s…………12
		{1042,2083,13,15,1},//循环*…………13
		{2125,4042,14,15,0},//停桢1s…………14
		{4083,10292,15,17,0},//剧情…………15
		{10333,11292,16,17,0},//停桢1s…………16
		{1042,2083,17,19,1},//循环*…………17
		{10333,11292,18,19,0},//停桢1s…………18
		{1042,2083,19,21,1},//循环*…………19
		{10333,11292,20,21,0},//停桢1s…………20
		{1042,2083,21,23,1},//循环*…………21
		{2125,4042,22,23,0},//停桢1s…………22
		{4083,10292,23,25,0},//剧情…………23
		{10333,11292,24,25,0},//停桢1s…………24
		{1042,2083,25,27,1},//循环*…………25
		{10333,11292,26,27,0},//停桢1s…………26
		{1042,2083,27,29,1},//循环*…………27
		{10333,11292,28,29,0},//停桢1s…………28
		{1042,2083,29,31,1},//循环*…………29
		{2125,4042,30,31,0},//停桢1s…………30
		{4083,10292,31,32,0},//剧情…………31
		{10333,11292,32,32,0},//停桢1s…………32
	};
	
	public static int timeEpLevel3_1[][] ={ // 法国学习


















//		{42,1000,0,1,0},//停桢1s
//		{1042,13750,1,3,0},//剧情
//		{13792,14792,2,3,0},//停桢1s
//		{14833,15833,3,5,1},//循环*
//		{15875,17792,4,5,0},//停桢1s
//		{17833,23750,5,7,0},//剧情
//		{23792,24708,6,7,0},//停桢1s
//		{14833,15833,7,9,1},//循环*
//		{15875,17792,8,9,0},//停桢1s
//		{17833,23750,9,11,0},//剧情
//		{23792,24708,10,11,0},//停桢1s
//		{14833,15833,11,13,1},//循环*
//		{15875,17792,12,13,0},//停桢1s
//		{17833,23750,13,15,0},//剧情
//		{23792,24708,14,15,0},//停桢1s
//		{14833,15833,15,17,1},//循环*
//		{15875,17792,16,17,0},//停桢1s
//		{17833,23750,17,19,0},//剧情
//		{23792,24708,18,19,0},//停桢1s
//		{14833,15833,19,21,1},//循环*
//		{15875,17792,20,21,0},//停桢1s
//		{17833,23750,21,23,0},//剧情
//		{23792,24708,22,23,0},//停桢1s
//		{14833,15833,23,25,1},//循环*
//		{15875,17792,24,25,0},//停桢1s
//		{17833,23750,25,27,0},//剧情
//		{23792,24708,26,27,0},//停桢1s
//		{14833,15833,27,29,1},//循环*
//		{15875,17792,28,29,0},//停桢1s
//		{17833,23750,29,31,0},//剧情
//		{23792,24708,30,31,0},//停桢1s
//		{14833,15833,31,33,1},//循环*
//		{15875,17792,32,33,0},//停桢1s
//		{17833,23750,33,34,0},//剧情
//		{23792,24708,34,34,0}//停桢1s
		{42,1000,0,1,0},//停桢1s
		{1042,13750,1,3,0},//剧情
		{13792,14792,2,3,0},//停桢1s
		{14833,15833,3,5,1},//循环*
		{15875,17792,4,5,0},//停桢1s
		{17833,23750,5,7,0},//剧情
		{23792,24708,6,7,0},//停桢1s
		{14833,15833,7,9,1},//循环*
		{15875,17792,8,9,0},//停桢1s
		{17833,23750,9,11,0},//剧情
		{23792,24708,10,11,0},//停桢1s
		{14833,15833,11,13,1},//循环*
		{15875,17792,12,13,0},//停桢1s
		{17833,23750,13,15,0},//剧情
		{23792,24708,14,15,0},//停桢1s
		{14833,15833,15,17,1},//循环*
		{15875,17792,16,17,0},//停桢1s
		{17833,23750,17,19,0},//剧情
		{23792,24708,18,19,0},//停桢1s
		{14833,15833,19,21,1},//循环*
		{15875,17792,20,21,0},//停桢1s
		{17833,23750,21,23,0},//剧情
		{23792,24708,22,23,0},//停桢1s
		{14833,15833,23,25,1},//循环*
		{15875,17792,24,25,0},//停桢1s
		{17833,23750,25,27,0},//剧情
		{23792,24708,26,27,0},//停桢1s
		{14833,15833,27,29,1},//循环*
		{15875,17792,28,29,0},//停桢1s
		{17833,23750,29,31,0},//剧情
		{23792,24708,30,31,0},//停桢1s
		{14833,15833,31,33,1},//循环*
		{15875,17792,32,33,0},//停桢1s
		{17833,23750,33,35,0},//剧情
		{23792,24708,34,35,0},//停桢1s//>33
		{14833,15833,35,37,1},//循环*
		{15875,17792,36,37,0},//停桢1s
		{17833,23750,37,39,0},//剧情
		{23792,24708,38,39,0},//停桢1s
		{14833,15833,39,41,1},//循环*
		{15875,17792,40,41,0},//停桢1s
		{17833,23750,41,43,0},//剧情
		{23792,24708,42,43,0},//停桢1s
		{14833,15833,43,45,1},//循环*
		{15875,17792,44,45,0},//停桢1s
		{17833,23750,45,47,0},//剧情
		{23792,24708,46,47,0},//停桢1s
		{14833,15833,47,49,1},//循环*
		{15875,17792,48,49,0},//停桢1s
		{17833,23750,49,51,0},//剧情
		{23792,24708,50,51,0},//停桢1s
		{14833,15833,51,53,1},//循环*
		{15875,17792,52,53,0},//停桢1s
		{17833,23750,53,55,0},//剧情
		{23792,24708,54,55,0},//停桢1s
		{14833,15833,55,57,1},//循环*
		{15875,17792,56,57,0},//停桢1s
		{17833,23750,57,59,0},//剧情
		{23792,24708,58,59,0},//停桢1s
		{14833,15833,59,61,1},//循环*
		{15875,17792,60,61,0},//停桢1s
		{17833,23750,61,63,0},//剧情
		{23792,24708,62,63,0},//停桢1s
		{14833,15833,63,65,1},//循环*
		{15875,17792,64,65,0},//停桢1s
		{17833,23750,65,66,0},//剧情
		{23792,24708,66,66,0},//停桢1s


	};
	public static int timeEpLevel4_0[][] ={ // 西班牙学习
		{42 ,1000 ,0,1,0},//停桢1s
		{1042 ,19667 ,1,3,0},//剧情
		{19708 ,20625 ,2,3,0},//停桢1s
		{20667 ,26125 ,3,5,0},//剧情1
		{26167 ,27125 ,4,5,0},//停桢1s
		{27167 ,29125 ,5,7,1},//循环*
		{29167 ,30125 ,6,7,0},//停桢1s
		{30167 ,32292 ,7,9,0},//剧情c C闪动缩小
		{32333 ,33250 ,8,9,0},//停桢1s
		{33292 ,38708 ,9,11,0},//剧情2
		{26167 ,27125 ,10,11,0},//停桢1s
		{27167 ,29125 ,11,13,1},//循环*
		{29167 ,30125 ,12,13,0},//停桢1s
		{30167 ,32292 ,13,15,0},//剧情c C闪动缩小
		{32333 ,33250 ,14,15,0},//停桢1s
		{20667 ,26125 ,15,17,0},//剧情1
		{26167 ,27125 ,16,17,0},//停桢1s
		{27167 ,29125 ,17,19,1},//循环*
		{29167 ,30125 ,18,19,0},//停桢1s
		{30167 ,32292 ,19,21,0},//剧情c C闪动缩小 
		{32333 ,33250 ,20,21,0},//停桢1s
		{33292 ,38708 ,21,23,0},//剧情2
		{26167 ,27125 ,22,23,0},//停桢1s
		{27167 ,29125 ,23,25,1},//循环*
		{29167 ,30125 ,24,25,0},//停桢1s
		{30167 ,32292 ,25,27,0},//剧情c  C闪动缩小
		{32333 ,33250 ,26,27,0},//停桢1s
		{20667 ,26125 ,27,29,0},//剧情1
		{26167 ,27125 ,28,29,0},//停桢1s
		{27167 ,29125 ,29,31,1},//循环*
		{29167 ,30125 ,30,31,0},//停桢1s
		{30167 ,33250 ,31,33,0},//剧情c C闪动缩小
		{39750 ,40708 ,32,33,0},//停桢1s
		{40750 ,42708 ,33,35,1},//循环* 点击莱莱 
		{42750 ,43708 ,34,35,0},//停桢1s
		{43750 ,50583 ,35,37,0},//剧情
		{50625 ,51583 ,36,37,0},//停桢1s
		{51625 ,53583 ,37,39,1},//循环* 点击问号
		{53625 ,54583 ,38,39,0},//停桢1s
		{54625 ,58583 ,39,41,0},//读音一  cat
		{58625 ,59583 ,40,41,0},//停桢1s
		{59625 ,61583 ,41,43,1},//循环* 点击莱莱
		{62042 ,62583 ,42,43,0},//停桢1s
		{62625 ,75333 ,43,45,0},//剧情
		{39750 ,40708 ,44,45,0},//停桢1s
		{40750 ,42708 ,45,47,1},//循环* 点击莱莱 
		{42750 ,43708 ,46,47,0},//停桢1s
		{43750 ,50583 ,47,49,0},//剧情
		{50625 ,51583 ,48,49,0},//停桢1s
		{51625 ,53583 ,49,51,1},//循环* 点击问号
		{53625 ,54583 ,50,51,0},//停桢1s
		{77375 ,82333 ,51,53,0},//读音一cake
		{82375 ,83333 ,52,53,0},//停桢1s
		{83375 ,85333 ,53,55,1},//循环* 点击莱莱
		{85375 ,86333 ,54,55,0},//停桢1s
		{86375 ,93458 ,55,57,0},//剧情
		{39750 ,40708 ,56,57,0},//停桢1s
		{40750 ,42708 ,57,59,1},//循环* 点击莱莱 
		{42750 ,43708 ,58,59,0},//停桢1s
		{43750 ,50583 ,59,61,0},//剧情
		{50625 ,51583 ,60,61,0},//停桢1s
		{51625 ,53583 ,61,63,1},//循环* 点击问号
		{53625 ,54583 ,62,63,0},//停桢1s
		{95458 ,100417 ,63,65,0},//读音一car
		{100458 ,101417 ,64,65,0},//停桢1s
		{101458 ,103417 ,65,67,1},//循环* 点击莱莱 
		{103458 ,104417 ,66,67,0},//停桢1s
		{104458 ,119042 ,67,69,0},//剧情
		{39750 ,40708 ,68,69,0},//停桢1s
		{40750 ,42708 ,69,71,1},//循环* 点击莱莱 
		{42750 ,43708 ,70,71,0},//停桢1s
		{43750 ,50583 ,71,73,0},//剧情
		{50625 ,51583 ,72,73,0},//停桢1s
		{51625 ,53583 ,73,75,1},//循环* 点击问号
		{53625 ,54583 ,74,75,0},//停桢1s
		{121042 ,126000 ,75,77,0},//读音一clock
		{126042 ,127000 ,76,77,0},//停桢1s
		{127042 ,129000 ,77,79,1},//循环* 点击莱莱 
		{129042 ,130000 ,78,79,0},//停桢1s
		{130042 ,139000 ,79,80,0},//剧情
		{139042 ,139958 ,80,80,0}//停桢1s
	};
	
	public static int timeEpLevel4_1[][] ={
		{ 42	, 1000	, 0	, 1	, 0 }, //	停桢1s		
		{ 1042	, 9250	, 1	, 3	, 0 }, //	剧情			C4-3a、E4-3a
		{ 9292	, 10250	, 2	, 3	, 0 }, //	停桢1s	
		{ 10292	, 12250	, 3	, 5	, 1 }, //	循环*	出题 共用循环		C4-3b、E4-3b
		{ 12292	, 13250	, 4	, 5	, 0 }, //	停桢1s	
		{ 13292	, 19250	, 5	, 8	, 0 }, //	剧情	跳舞向前一步
		{ 19292	, 20208	, 6	, 8	, 0 }, //	停桢1s
		{ 9292	, 10250	, 7	, 8	, 0 }, //	停桢1s	
		{ 10292	, 12250	, 8	, 10, 1 }, //	循环*	出题 共用循环		C4-3b、E4-3b
		{ 12292	, 13250	, 9	, 10, 0 }, //	停桢1s	
		{ 13292	, 19250	, 10, 13, 0 }, //	剧情	跳舞向前一步
		{ 19292	, 20208	, 11, 13, 0 }, //	停桢1s
		{ 9292	, 10250	, 12, 13, 0 }, //	停桢1s	
		{ 10292	, 12250	, 13, 15, 1 }, //	循环*	出题 共用循环		C4-3b、E4-3b
		{ 12292	, 13250	, 14, 15, 0 }, //	停桢1s	
		{ 13292	, 19250	, 15, 18, 0 }, //	剧情	跳舞向前一步
		{ 19292	, 20208	, 16, 18, 0 }, //	停桢1s
		{ 9292	, 10250	, 17, 18, 0 }, //	停桢1s	
		{ 10292	, 12250	, 18, 20, 1 }, //	循环*	出题 共用循环		C4-3b、E4-3b
		{ 12292	, 13250	, 19, 20, 0 }, //	停桢1s	
		{ 13292	, 19250	, 20, 23, 0 }, //	剧情	跳舞向前一步
		{ 19292	, 20208	, 21, 23, 0 }, //	停桢1s
		{ 9292	, 10250	, 22, 23, 0 }, //	停桢1s	
		{ 10292	, 12250	, 23, 25, 1 }, //	循环*	出题 共用循环		C4-3b、E4-3b
		{ 12292	, 13250	, 24, 25, 0 }, //	停桢1s	
		{ 13292	, 19250	, 25, 28, 0 }, //	剧情	跳舞向前一步
		{ 19292	, 20208	, 26, 28, 0 }, //	停桢1s
		{ 9292	, 10250	, 27, 28, 0 }, //	停桢1s	
		{ 10292	, 12250	, 28, 30, 1 }, //	循环*	出题 共用循环		C4-3b、E4-3b
		{ 12292	, 13250	, 29, 30, 0 }, //	停桢1s	
		{ 13292	, 19250	, 30, 33, 0 }, //	剧情	跳舞向前一步
		{ 19292	, 20208	, 31, 33, 0 }, //	停桢1s
		{ 9292	, 10250	, 32, 33, 0 }, //	停桢1s	
		{ 10292	, 12250	, 33, 35, 1 }, //	循环*	出题 共用循环		C4-3b、E4-3b
		{ 12292	, 13250	, 34, 35, 0 }, //	停桢1s	
		{ 13292	, 19250	, 35, 38, 0 }, //	剧情	跳舞向前一步
		{ 19292	, 20208	, 36, 38, 0 }, //	停桢1s
		{ 9292	, 10250	, 37, 38, 0 }, //	停桢1s	
		{ 10292	, 12250	, 38, 40, 1 }, //	循环*	出题 共用循环		C4-3b、E4-3b
		{ 12292	, 13250	, 39, 40, 0 }, //	停桢1s	
		{ 13292	, 19250	, 40, 43, 0 }, //	剧情	跳舞向前一步
		{ 19292	, 20208	, 41, 43, 0 }, //	停桢1s
		{ 19292	, 20208	, 42, 43, 0 }, //	停桢1s
		{ 20250	, 26292	, 43, 44, 0 }, //	剧情	皇冠拍照
		{ 26333	, 27250	, 44, 45, 0 }, //	停桢1s
		{ 26333	, 27250	, 45, 45, 0 }, //	停桢1s	
	};
	
	public static int timeEpLevel5_0[][] ={ // 意大利学习
		{42 ,1000 ,0,1,0},//停桢1s
		{1042 ,30042 ,1,3,0},//剧情
		{30083 ,31000 ,2,3,0},//停桢1s
		{31458 ,33208 ,3,5,1},//循环*
		{33250 ,34167 ,4,5,0},//停桢1s
		{34208 ,37250 ,5,7,0},//剧情
		{37292 ,38208 ,6,7,0},//停桢1s
		{31458 ,33208 ,7,9,1},//循环*
		{33250 ,34167 ,8,9,0},//停桢1s
		{34208 ,37250 ,9,11,0},//剧情
		{37292 ,38208 ,10,11,0},//停桢1s
		{31458 ,33208 ,11,13,1},//循环*
		{33250 ,34167 ,12,13,0},//停桢1s
		{34208 ,37250 ,13,15,0},//剧情
		{37292 ,38208 ,14,15,0},//停桢1s
		{31458 ,33208 ,15,17,1},//循环*
		{33250 ,34167 ,16,17,0},//停桢1s
		{34208 ,37250 ,17,19,0},//剧情
		{37292 ,38208 ,18,19,0},//停桢1s
		{31458 ,33208 ,19,21,1},//循环*
		{33250 ,34167 ,20,21,0},//停桢1s
		{34208 ,37250 ,21,23,0},//剧情
		{37292 ,38208 ,22,23,0},//停桢1s
		{31458 ,33208 ,23,25,1},//循环*
		{33250 ,34167 ,24,25,0},//停桢1s
		{34208 ,37250 ,25,27,0},//剧情
		{37292 ,38208 ,26,27,0},//停桢1s
		{31458 ,33208 ,27,29,1},//循环*
		{33250 ,34167 ,28,29,0},//停桢1s
		{34208 ,37250 ,29,31,0},//剧情
		{37292 ,38208 ,30,31,0},//停桢1s
		{31458 ,33208 ,31,33,1},//循环*
		{33250 ,34167 ,32,33,0},//停桢1s
		{38250 ,66583 ,33,35,0},//last剧情
		{66625 ,67542 ,34,35,0},//停桢1s
		{67583 ,69542 ,35,37,1},//循环*
		{69583 ,70500 ,36,37,0},//停桢1s
		{70542 ,72500 ,37,39,1},//循环*
		{72542 ,73458 ,38,39,0},//停桢1s
		{73500 ,76000 ,39,41,0},//剧情
		{77000 ,77917 ,40,41,0},//停桢1s
		{77958 ,79917 ,41,43,1},//循环*
		{80917 ,81833 ,42,43,0},//停桢1s
		{81875 ,87792 ,43,45,0},//读音一
		{87833 ,88750 ,44,45,0},//停桢1s
		{88792 ,90750 ,45,47,1},//循环*
		{90792 ,91708 ,46,47,0},//停桢1s
		{91750 ,93708 ,47,49,0},//读音二
		{93750 ,94667 ,48,49,0},//停桢1s
		{94708 ,96667 ,49,51,1},//循环*
		{96708 ,97625 ,50,51,0},//停桢1s
		{97667 ,100333 ,51,53,0},//读音三
		{100375 ,101292 ,52,53,0},//停桢1s
		{101333 ,103292 ,53,55,1},//循环*
		{103333 ,104250 ,54,55,0},//停桢1s
		{104292 ,108625 ,55,57,0},//读音四
		{108667 ,109583 ,56,57,0},//停桢1s
		{109625 ,111583 ,57,59,1},//循环*
		{77000 ,77917 ,58,59,0},//停桢1s
		{77958 ,79917 ,59,61,1},//循环*
		{112583 ,113500 ,60,61,0},//停桢1s
		{113542 ,119458 ,61,63,0},//读音一
		{119500 ,120417 ,62,63,0},//停桢1s
		{120458 ,122417 ,63,65,1},//循环*
		{122458 ,123375 ,64,65,0},//停桢1s
		{123417 ,126208 ,65,67,0},//读音二
		{126250 ,127167 ,66,67,0},//停桢1s
		{127208 ,129167 ,67,69,1},//循环*
		{129208 ,130125 ,68,69,0},//停桢1s
		{130167 ,132875 ,69,71,0},//读音三
		{132917 ,133833 ,70,71,0},//停桢1s
		{133875 ,135833 ,71,73,1},//循环*
		{135875 ,136792 ,72,73,0},//停桢1s
		{136833 ,140542 ,73,75,0},//读音四
		{140583 ,141458 ,74,75,0},//停桢1s
		{141500 ,143458 ,75,77,1},//循环*
		{77000 ,77917 ,76,77,0},//停桢1s
		{77958 ,79917 ,77,79,1},//循环*
		{144458 ,145375 ,78,79,0},//停桢1s
		{145417 ,151542 ,79,81,0},//读音一
		{151583 ,152500 ,80,81,0},//停桢1s
		{152542 ,154500 ,81,83,1},//循环*
		{154542 ,155458 ,82,83,0},//停桢1s
		{155500 ,158208 ,83,85,0},//读音二
		{158250 ,159167 ,84,85,0},//停桢1s
		{159208 ,161167 ,85,87,1},//循环*
		{161208 ,162125 ,86,87,0},//停桢1s
		{162167 ,164875 ,87,89,0},//读音三
		{164917 ,165833 ,88,89,0},//停桢1s
		{165875 ,167833 ,89,91,1},//循环*
		{167875 ,168792 ,90,91,0},//停桢1s
		{168833 ,173458 ,91,93,0},//读音四
		{173500 ,174417 ,92,93,0},//停桢1s
		{174458 ,176417 ,93,95,1},//循环*
		{77000 ,77917 ,94,95,0},//停桢1s
		{77958 ,79917 ,95,97,1},//循环*
		{177417 ,178333 ,96,97,0},//停桢1s
		{178375 ,184500 ,97,99,0},//读音一
		{184542 ,185458 ,98,99,0},//停桢1s
		{185500 ,187458 ,99,101,1},//循环*
		{187500 ,188417 ,100,101,0},//停桢1s
		{188458 ,190625 ,101,103,0},//读音二
		{190667 ,191583 ,102,103,0},//停桢1s
		{191625 ,193583 ,103,105,1},//循环*
		{193625 ,194542 ,104,105,0},//停桢1s
		{194583 ,197042 ,105,107,0},//读音三
		{197083 ,198000 ,106,107,0},//停桢1s
		{198042 ,200000 ,107,109,1},//循环*
		{200042 ,200958 ,108,109,0},//停桢1s
		{201000 ,208375 ,109,110,0},//读音四
		{208417 ,209250 ,110,110,0},//停桢1s
	};
	
	public static int timeEpLevel5_1[][]={
		{ 42 , 1000 , 0 , 1 , 0	}, //	停桢1s		
		{ 1042 , 11917 , 1 , 3 , 0	}, //	剧情			C5-3a、E5-3a
		{ 11958 , 12875 , 2 , 3 , 0	}, //	停桢1s	
		{ 12917 , 14875 , 3 , 5 , 1	}, //	循环*	出题 共用循环		C5-3b、E5-3b
		{ 14917 , 15833 , 4 , 5 , 0	}, //	停桢1s	
		{ 15875 , 21083 , 5 , 8 , 0	}, //	剧情
		{ 21125 , 21958 , 6 , 8 , 0	}, //	停桢1s
		{ 11958 , 12875 , 7 , 8 , 0	}, //	停桢1s	
		{ 12917 , 14875 , 8 , 10 , 1	}, //	循环*	出题 共用循环		C5-3b、E5-3b
		{ 14917 , 15833 , 9 , 10 , 0	}, //	停桢1s	
		{ 15875 , 21083 , 10 , 13 , 0	}, //	剧情
		{ 21125 , 21958 , 11 , 13 , 0	}, //	停桢1s
		{ 11958 , 12875 , 12 , 13 , 0	}, //	停桢1s	
		{ 12917 , 14875 , 13 , 15 , 1	}, //	循环*	出题 共用循环		C5-3b、E5-3b
		{ 14917 , 15833 , 14 , 15 , 0	}, //	停桢1s	
		{ 15875 , 21083 , 15 , 18 , 0	}, //	剧情
		{ 21125 , 21958 , 16 , 18 , 0	}, //	停桢1s
		{ 11958 , 12875 , 17 , 18 , 0	}, //	停桢1s	
		{ 12917 , 14875 , 18 , 20 , 1	}, //	循环*	出题 共用循环		C5-3b、E5-3b
		{ 14917 , 15833 , 19 , 20 , 0	}, //	停桢1s	
		{ 15875 , 21083 , 20 , 23 , 0	}, //	剧情
		{ 21125 , 21958 , 21 , 23 , 0	}, //	停桢1s
		{ 11958 , 12875 , 22 , 23 , 0	}, //	停桢1s	
		{ 12917 , 14875 , 23 , 25 , 1	}, //	循环*	出题 共用循环		C5-3b、E5-3b
		{ 14917 , 15833 , 24 , 25 , 0	}, //	停桢1s	
		{ 15875 , 21083 , 25 , 28 , 0	}, //	剧情
		{ 21125 , 21958 , 26 , 28 , 0	}, //	停桢1s
		{ 11958 , 12875 , 27 , 28 , 0	}, //	停桢1s	
		{ 12917 , 14875 , 28 , 30 , 1	}, //	循环*	出题 共用循环		C5-3b、E5-3b
		{ 14917 , 15833 , 29 , 30 , 0	}, //	停桢1s	
		{ 15875 , 21083 , 30 , 33 , 0	}, //	剧情
		{ 21125 , 21958 , 31 , 33 , 0	}, //	停桢1s
		{ 11958 , 12875 , 32 , 33 , 0	}, //	停桢1s	
		{ 12917 , 14875 , 33 , 35 , 1	}, //	循环*	出题 共用循环		C5-3b、E5-3b
		{ 14917 , 15833 , 34 , 35 , 0	}, //	停桢1s	
		{ 15875 , 21083 , 35 , 38 , 0	}, //	剧情
		{ 21125 , 21958 , 36 , 38 , 0	}, //	停桢1s
		{ 11958 , 12875 , 37 , 38 , 0	}, //	停桢1s	
		{ 12917 , 14875 , 38 , 40 , 1	}, //	循环*	出题 共用循环		C5-3b、E5-3b
		{ 14917 , 15833 , 39 , 40 , 0	}, //	停桢1s	
		{ 15875 , 21083 , 40 , 41 , 0	}, //	剧情
		{ 21125 , 21958 , 41 , 41 , 0	}, //	停桢1s
	};
	
}
