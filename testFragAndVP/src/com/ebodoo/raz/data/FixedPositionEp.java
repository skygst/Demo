package com.ebodoo.raz.data;

import com.ebodoo.raz.utils.BaseCommon;

import android.content.Context;

public class FixedPositionEp {

	// 荷兰学习部分--桥-木头定位
	public final static int ep_level1_0_position[][] = {
		//位置 w、h、x、y	1280X720
		{137, 117, 457, 351},	//桥1---0
		{137, 117, 633, 351},	//桥2
		{137, 117, 302, 459},	//桥3
		{137, 117, 459, 462},	//桥4
		{137, 117, 608, 461},	//桥5
		{137, 117, 775, 462},	//桥6
		{137, 117, 269, 584},	//桥7
		{137, 117, 420, 583},	//桥8
		{137, 117, 598, 584},	//桥9
		{137, 117, 781, 581}, //桥10---9
		{290, 253, 325, 48}, //盒子1-黄色---10
		{290, 253, 664, 48}, //盒子2-绿色
		{310, 270, 184, 216}, //盒子3-粉红色
		{310, 270, 796, 216}, //盒子4-淡蓝色---13
		{550, 658, 368, 35}, // 卡片------14
		
	};
	
	//荷兰复习1   四张卡片的位置
	public final static int ep_level1_1_position[][]={
		//位置W ,H ,X , Y
		{369,350,270,160},
		{369,350,270,600},
		{369,350,1240,160},
		{369,350,1240,600},
//		{229, 105, 175, 607}
		{303, 157, 102, 910},
		{300,180,860,50},
		{250,180,50,50}
	};
	
	//荷兰复习1   单词图片的名字
	public final static String ep_level1_1_imageName[]={
		"ep_1_hollandword1",
		"ep_1_hollandword2",
		"ep_1_hollandword3",
		"ep_1_hollandword4"
	};
	
	public final static int ep_level1_0_index_to_position[][] = {
		// currentindex 和 position的对应关系
		{0,0},// 桥1
		{1,1},
		{2,2},
		{3,3},
		{4,4},
		{5,5},
		{6,6},
		{7,7},
		{8,8},
		{9,9},//桥10
		{10,10},//盒子1
		{11,14},//卡片
		{12,11},//盒子2
		{13,14},
		{14,12},//盒子3
		{15,14},
		{16,13},//盒子4
		{17,14}// 卡片
	}; 
	
	public final static int map_position[][] = {
			// 位置 w、h、x、y 1920X1080
			{ 364, 334, 266, 412 }, // america
			{ 332, 282, 862, 160 }, // europe
			{ 412, 300, 828, 560 }, // africa
			{ 376, 242, 1224, 296 }, // asia
			{ 112, 135, 382, 500 }, // 锁america
			{ 112, 135, 950, 625 }, // 锁asia
			{ 112, 135, 1350, 317 }, // 锁africa
	};
	
	// 荷兰学习部分--豆子定位
	public final static int ep_level1_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{139, 203, 287, 102},	//豆1
		{139, 203, 472, 170},	//豆2
		{139, 203, 665, 173},	//豆3
		{139, 203, 845, 122},	//豆4
		{139, 203, 200, 373},	//豆5
		{139, 203, 371, 442},	//豆6
		{139, 203, 581, 447},	//豆7
		{139, 203, 770, 432},	//豆8
		{139, 203, 958, 336},	//豆9
		
		{139, 203, 248, 96},	//豆1
		{139, 203, 412, 124},	//豆2
		{139, 203, 592, 154},	//豆3
		{139, 203, 782, 129},	//豆4
		{139, 203, 969, 85},	//豆5
		{139, 203, 285, 350},	//豆6
		{139, 203, 505, 407},	//豆7
		{139, 203, 708, 414},	//豆8
		{139, 203, 919, 399},	//豆9
		
		{139, 203, 203, 213},	//豆1
		{139, 203, 373, 97},	//豆2
		{139, 203, 562, 163},	//豆3
		{139, 203, 777, 111},	//豆4
		{139, 203, 950, 166},	//豆5
		{139, 203, 331, 378},	//豆6
		{139, 203, 518, 453},	//豆7
		{139, 203, 735, 381},	//豆8
		{139, 203, 920, 443},	//豆9
	};
	
	// 复习2 共用的位置
	public final static int fuxi1_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{201, 120, 548, 0},	//在读一遍
		{83, 84, 177, 572},	//豆
		{229, 105, 175, 607},	//计数
	};
	
	//获取复习2对应的图片————参数：玩法、第几张图片
	public static int getFuxi2Pic(Context context,int countryType, int gameType, int index_iv){
		if(countryType == 1){	//荷兰 
			return BaseCommon.getImage(context, fuxi1_2_zm[game1_2_position[index_iv][gameType]]);
		}else if(countryType == 2){
			return BaseCommon.getImage(context, fuxi2_2_zm[game2_2_position[index_iv][gameType]]);
		}else if(countryType == 3){
			return BaseCommon.getImage(context, fuxi3_2_zm[game3_2_position[index_iv][gameType]]);
		}else if(countryType == 4){
			return BaseCommon.getImage(context, fuxi4_2_zm[game4_2_position[index_iv][gameType]]);
		}else if(countryType == 5){
			return BaseCommon.getImage(context, fuxi5_2_zm[game5_2_position[index_iv][gameType]]);
		}else{
			return BaseCommon.getImage(context, fuxi1_2_zm[game1_2_position[index_iv][gameType]]);
		}
		
	}
	
	//获取复习2对应的图片————参数：玩法、第几张图片
	public static int getFuxi1_2Pic2(Context context,int countryType, int gameType, int index_iv,int index){
		if(countryType == 1 && index <= 1){
			return BaseCommon.getImage(context, fuxi1_2_zm[game1_2_position[index_iv][gameType]]);
		}else if(countryType == 1 && index > 1 && index <= 3){
			return BaseCommon.getImage(context, fuxi1_2_zm2[game1_2_position[index_iv][gameType]]);
		}else if(countryType == 1 && index > 3 && index <= 5){
			return BaseCommon.getImage(context, fuxi1_2_zm3[game1_2_position[index_iv][gameType]]);
		}else if(countryType == 1 && index > 5 && index <= 7){
			return BaseCommon.getImage(context, fuxi1_2_zm4[game1_2_position[index_iv][gameType]]);
		}else{
			return BaseCommon.getImage(context, fuxi1_2_zm[game1_2_position[index_iv][gameType]]);
		}
		
	}
	//获取复习2对应的图片————参数：玩法、第几张图片
	public static int getFuxi2_2Pic2(Context context,int countryType, int gameType, int index_iv,int index){
		if(countryType == 2 && index <= 2){
			return BaseCommon.getImage(context, fuxi2_2_zm[game2_2_position[index_iv][gameType]]);
		}else if(countryType == 2 && index > 2 && index <= 5){
			return BaseCommon.getImage(context, fuxi2_2_zm2[game2_2_position[index_iv][gameType]]);
		}else if(countryType == 2 && index > 5 && index <= 8){
			return BaseCommon.getImage(context, fuxi2_2_zm3[game2_2_position[index_iv][gameType]]);
		}else if(countryType == 2 && index > 8 && index <= 11){
			return BaseCommon.getImage(context, fuxi2_2_zm4[game2_2_position[index_iv][gameType]]);
		}else{
			return BaseCommon.getImage(context, fuxi2_2_zm[game2_2_position[index_iv][gameType]]);
		}
		
	}
	//获取复习2对应的图片————参数：玩法、第几张图片
	public static int getFuxi4_2Pic2(Context context,int countryType, int gameType, int index_iv,int index){
		if(countryType == 4 && index <= 2){
			return BaseCommon.getImage(context, fuxi4_2_zm[game4_2_position[index_iv][gameType]]);
		}else if(countryType == 4 && index > 2 && index <= 5){
			return BaseCommon.getImage(context, fuxi4_2_zm2[game4_2_position[index_iv][gameType]]);
		}else if(countryType == 4 && index > 5 && index <= 8){
			return BaseCommon.getImage(context, fuxi4_2_zm3[game4_2_position[index_iv][gameType]]);
		}else if(countryType == 4 && index > 8 && index <= 11){
			return BaseCommon.getImage(context, fuxi4_2_zm4[game4_2_position[index_iv][gameType]]);
		}else{
			return BaseCommon.getImage(context, fuxi4_2_zm[game4_2_position[index_iv][gameType]]);
		}
		
	}
	//获取复习2对应的图片————参数：玩法、第几张图片
	public static int getFuxi5_2Pic2(Context context,int countryType, int gameType, int index_iv,int index){
		if(countryType == 5 && index <= 2){
			return BaseCommon.getImage(context, fuxi5_2_zm[game5_2_position[index_iv][gameType]]);
		}else if(countryType == 5 && index > 2 && index <= 5){
			return BaseCommon.getImage(context, fuxi5_2_zm2[game5_2_position[index_iv][gameType]]);
		}else if(countryType == 5 && index > 5 && index <= 8){
			return BaseCommon.getImage(context, fuxi5_2_zm3[game5_2_position[index_iv][gameType]]);
		}else if(countryType == 5 && index > 8 && index <= 11){
			return BaseCommon.getImage(context, fuxi5_2_zm4[game5_2_position[index_iv][gameType]]);
		}else{
			return BaseCommon.getImage(context, fuxi5_2_zm[game5_2_position[index_iv][gameType]]);
		}
		
	}
	//获取复习2对应的图片————参数：玩法、第几张图片
	public static int getFuxi3_2Pic2(Context context,int countryType, int gameType, int index_iv,int index){
		if(countryType == 3 && index <= 2){
			return BaseCommon.getImage(context, fuxi3_2_zm[game3_2_position[index_iv][gameType]]);
		}else if(countryType == 3 && index > 2 && index <= 5){
			return BaseCommon.getImage(context, fuxi3_2_zm2[game3_2_position[index_iv][gameType]]);
		}else if(countryType == 3 && index > 5 && index <= 8){
			return BaseCommon.getImage(context, fuxi3_2_zm3[game3_2_position[index_iv][gameType]]);
		}else if(countryType == 3 && index > 8 && index <= 11){
			return BaseCommon.getImage(context, fuxi3_2_zm4[game3_2_position[index_iv][gameType]]);
		}else{
			return BaseCommon.getImage(context, fuxi3_2_zm[game3_2_position[index_iv][gameType]]);
		}
		
	}
	//判断选择的结果是否正确————参数：玩法、第几张图片
	public static boolean isGood(int countryType, int gameType, int index_iv){
		if(countryType == 1 && game1_2_position[index_iv][gameType] == 1){
			return true;
		}
		else if(countryType == 2 && game2_2_position[index_iv][gameType] == 1){
			return true;
		}
		else if(countryType == 3 && game3_2_position[index_iv][gameType] == 2){
			return true;
		}
		else if(countryType == 4 && game4_2_position[index_iv][gameType] == 0){
			return true;
		}
		else if(countryType == 5 && game5_2_position[index_iv][gameType] == 0){
			return true;
		}
		else{
			return false;
		}
	}
	//荷兰——复习2对应的字母
	private final static String fuxi1_2_zm[] = {
		"dou_a",
		"dou_i",
		"dou_n",
		"dou_p"
	};
	//
	private final static String fuxi1_2_zm2[] = {
		"dou_i",
		"dou_a",
		"dou_n",
		"dou_p"
	};
	//
	private final static String fuxi1_2_zm3[] = {
		"dou_a",
		"dou_n",
		"dou_i",
		"dou_p"
	};
	//
	private final static String fuxi1_2_zm4[] = {
		"dou_a",
		"dou_p",
		"dou_n",
		"dou_i"
	};
	// 多总玩法 字母位置的随机   a/i/n/p   0 1 2 3 ——————————0~5随机
	private final static int game1_2_position[][] = {
		//玩法1 玩法2 玩法3……  分别对应的字母
		{2, 0, 1, 3 , 1, 2},	//豆1
		{1, 3, 2, 1 , 0, 3},	//豆2
		{0, 1, 0, 3 , 3, 1},	//豆3
		{3, 0, 1, 1 , 2, 0},	//豆4
		{1, 2, 3, 2 , 1, 1},	//豆5
		{0, 1, 2, 1 , 2, 0},	//豆6
		{2, 3, 1, 0 , 0, 1},	//豆7
		{1, 2, 0, 0 , 3, 3},	//豆8
		{3, 1, 3, 3 , 1, 2},	//豆9
	};
	
	// 英国学习部分--雨滴定位
	public final static int ep_level2_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{153, 112, 561, 131},	//水滴0
		{153, 112, 402, 252},	//水滴1
		{153, 112, 566, 252},	//水滴2
		{153, 112, 728, 252},	//水滴3
		{153, 112, 350, 368},	//水滴4
		{153, 112, 563, 377},	//水滴5
		{153, 112, 792, 384},	//水滴6
		{153, 112, 293, 523},	//水滴7
		{153, 112, 564, 526},	//水滴8
		{153, 112, 853, 526},	//水滴9
		
	};
	
	// 英国 复习2 共用的位置
	public final static int fuxi2_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{201, 120, 548, 0},	//在读一遍
		{126, 130, 168, 540},	//水滴
		{229, 105, 175, 607},	//计数
	};
	
	//英国——复习2对应的字母
	private final static String fuxi2_2_zm[] = {
		"san_i",
		"san_b",
		"san_m",
		"san_t"
	};
	//
	private final static String fuxi2_2_zm2[] = {
		"san_b",
		"san_i",
		"san_m",
		"san_t"
	};
	//
	private final static String fuxi2_2_zm3[] = {
		"san_i",
		"san_m",
		"san_b",
		"san_t"
	};
	//
	private final static String fuxi2_2_zm4[] = {
		"san_i",
		"san_t",
		"san_m",
		"san_b"
	};
	// 法国学习部分--雨滴定位
	public final static int ep_level3_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{159, 193, 170, 254},	//葡萄0
		{159, 193, 358, 104},	//葡萄1
		{159, 193, 473, 350},	//葡萄2
		{159, 193, 632, 171},	//葡萄3
		{159, 193, 936, 111},	//葡萄4
		{159, 193, 891, 362},	//葡萄5
		
	};
	//法国复习1卡片的位置
	public final static int fuxi3_1_card_position[][]=  {
		// 位置W ,H ,X , Y
		{ 223, 268, 101, 166 },// card1
		{ 223, 268, 381, 166 },// card2
		{ 223, 268, 657, 166 },// card3
		{223, 268, 936, 166 },//card4
		{ 364, 222, 476, 499 },// word_card
		{ 186, 105, 544, 10 }, //reread
		{ 227, 104, 23, 602 }, //countNum	
		{ 86, 84, 10, 560 }, //balloon	
		{109,90,26,10},//bt_home
		 };//
	// 法国复习2 共用的位置
	public final static int fuxi3_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{201, 120, 548, 0},	//在读一遍
		{63, 77, 194, 550},	//葡萄
		{229, 105, 178, 593},	//计数
	};
	
	//法国——复习2对应的字母 in
	private final static String fuxi3_2_zm[] = {
		"putao_ad",
		"putao_an",
		"putao_in",
		"putao_it"
	};
	//it
	private final static String fuxi3_2_zm2[] = {
		"putao_ad",
		"putao_an",
		"putao_it",
		"putao_in"
	};
	//ad
	private final static String fuxi3_2_zm3[] = {
		"putao_it",
		"putao_an",
		"putao_ad",
		"putao_in"
	};
	//an
	private final static String fuxi3_2_zm4[] = {
		"putao_ad",
		"putao_it",
		"putao_an",
		"putao_in"
	};
	// 法国葡萄多总玩法 字母位置的随机   ad/an/in/it   0 1 2 3 ——————————0~5随机
	private final static int game3_2_position[][] = {
		//玩法1 玩法2 玩法3……  分别对应的字母
		{0, 2, 3, 1 , 1, 2},	//葡萄1
		{1, 3, 2, 2 , 0, 3},	//葡萄2
		{2, 1, 0, 3 , 3, 2},	//葡萄3
		{3, 0, 2, 1 , 2, 0},	//葡萄4
		{1, 2, 3, 2 , 1, 1},	//葡萄5
		{2, 1, 1, 0 , 2, 0},	//葡萄6
	};
	
	// 西班牙学习部分--西红柿定位
	public final static int ep_level4_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{151, 135, 284, 204},	//西红柿0
		{151, 135, 515, 40},	//西红柿1
		{151, 135, 844, 125},	//西红柿2
		{151, 135, 825, 381},	//西红柿3
		{151, 135, 654, 521},	//西红柿4
		{151, 135, 302, 465},	//西红柿5
		
	};
	
	// 西班牙复习2 共用的位置
	public final static int fuxi4_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{201, 120, 930, 0},	//在读一遍
		{74, 66, 164, 606},	//西红柿
		{229, 105, 173, 611},	//计数
	};
	
	//西班牙——复习2对应的字母 c
	private final static String fuxi4_2_zm[] = {
		"tomato_c",
		"tomato_f",
		"tomato_h",
		"tomato_s"
	};
	//f
	private final static String fuxi4_2_zm2[] = {
		"tomato_f",
		"tomato_c",
		"tomato_h",
		"tomato_s"
	};
	//h
	private final static String fuxi4_2_zm3[] = {
		"tomato_h",
		"tomato_f",
		"tomato_c",
		"tomato_s"
	};
	//s
	private final static String fuxi4_2_zm4[] = {
		"tomato_s",
		"tomato_f",
		"tomato_h",
		"tomato_c"
	};
	// 西班牙西红柿多总玩法 字母位置的随机   ad/an/in/it   0 1 2 3 ——————————0~5随机
	private final static int game4_2_position[][] = {
		//玩法1 玩法2 玩法3……  分别对应的字母
		{0, 2, 3, 1 , 1, 2},	//西红柿1
		{1, 3, 2, 0 , 0, 3},	//西红柿2
		{0, 1, 0, 3 , 3, 0},	//西红柿3
		{3, 0, 2, 1 , 2, 1},	//西红柿4
		{1, 2, 0, 2 , 0, 1},	//西红柿5
		{2, 0, 1, 0 , 2, 0},	//西红柿6
	};	

	// 意大利学习部分--火定位
	public final static int ep_level5_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{155, 169, 334, 90},	//火0
		{155, 169, 562, 90},	//火1
		{155, 169, 787, 90},	//火2
		{155, 169, 334, 299},	//火3
		{155, 169, 562, 299},	//火4
		{155, 169, 787, 299},	//火5
		{155, 169, 334, 508},	//火6
		{155, 169, 562, 508},	//火7
		{155, 169, 787, 508},	//火8
		
	};
	
	// 意大利复习2 共用的位置
	public final static int fuxi5_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{186, 105, 547, 0},	//在读一遍
		{116, 124, 140, 543},	//火
		{229, 105, 140, 621},	//计数
	};
	
	//意大利——复习2对应的字母
	private final static String fuxi5_2_zm[] = {
		"huo_r",
		"huo_c",
		"huo_g",
		"huo_o"
	};
	//c
	private final static String fuxi5_2_zm2[] = {
		"huo_c",
		"huo_r",
		"huo_g",
		"huo_o"
	};
	//g
	private final static String fuxi5_2_zm3[] = {
		"huo_g",
		"huo_c",
		"huo_r",
		"huo_o"
	};
	//o
	private final static String fuxi5_2_zm4[] = {
		"huo_o",
		"huo_c",
		"huo_g",
		"huo_r"
	};
	// 意大利火多总玩法 字母位置的随机   c/g/o/r   0 1 2 3 ——————————0~5随机
	private final static int game5_2_position[][] = {
		//玩法1 玩法2 玩法3……  分别对应的字母
		{0, 2, 3, 1 , 1, 2},	//火1
		{1, 3, 2, 0 , 0, 3},	//火2
		{2, 1, 0, 3 , 3, 0},	//火3
		{3, 0, 2, 1 , 2, 1},	//火4
		{0, 2, 3, 2 , 0, 2},	//火5
		{2, 1, 1, 3 , 2, 3},	//火6
		{3, 0, 2, 1 , 3, 1},	//火7
		{1, 2, 0, 2 , 1, 1},	//火8
		{2, 3, 1, 0 , 2, 0},	//火9
	};	
	
	//英国复习1   四张卡片的位置
	public final static int ep_level2_1_position[][]={
		//位置W ,H ,X , Y
		{350,550,200,200},
		{350,550,600,200},
		{350,550,1000,200},
		{350,550,1400,200},
//		{229, 105, 175, 607}
		{403, 157, 102, 910},
		{150,150,100,860},
		{300,180,860,50}
	};
	
	//英国复习1   单词图片的名字
	public final static String ep_level2_1_imageName[]={
		"ep_2_bag1",
		"ep_banana2",
		"ep_big3",
		"ep_bus4"
	};
	
	
	//西班牙复习1   四张卡片的位置
	public final static int ep_level4_1_position[][]={
		//位置W ,H ,X , Y
		{350,550,200,200},
		{350,550,600,200},
		{350,550,1000,200},
		{350,550,1400,200},
//		{229, 105, 175, 607}
		{350, 157, 152, 910},
		{120,120,120,860},
		{300,180,860,50}
	};
	
	//西班牙复习1   单词图片的名字
	public final static String ep_level4_1_imageName[]={
		"ep_board_cake1",
		"ep_board_car2",
		"ep_board_cat3",
		"ep_board_clock4"
	};
	
	
	//意大利复习1   四张卡片的位置
	public final static int ep_level5_1_position[][]={
		//位置W ,H ,X , Y
		{350,550,200,400},     
		{350,550,600,400},
		{350,550,1000,400},
		{350,550,1400,400},
//		{229, 105, 175, 607}
		{350, 157, 152, 910},
		{150,150,130,840},
		{300,180,860,50}
	};
	
	//意大利复习1   单词图片的名字
	public final static String ep_level5_1_imageName[]={
		"ep_rabbit1",
		"ep_rat2",
		"ep_rock3",
		"ep_rose4"
	};
	
	
	// 英国水滴 多总玩法 字母位置的随机   b/i/m/t   0 1 2 3 ——————————0~5随机
	private final static int game2_2_position[][] = {
		//玩法1 玩法2 玩法3……  分别对应的字母
		{0, 0, 3, 2 , 1, 2},	//水滴1
		{2, 0, 1, 3 , 3, 0},	//水滴1
		{1, 3, 2, 1 , 0, 3},	//水滴2
		{0, 1, 0, 3 , 3, 2},	//水滴3
		{3, 0, 2, 1 , 2, 0},	//水滴4
		{1, 2, 3, 2 , 1, 1},	//水滴5
		{0, 1, 2, 0 , 2, 0},	//水滴6
		{2, 3, 1, 0 , 0, 1},	//水滴7
		{0, 2, 0, 0 , 3, 3},	//水滴8
		{3, 2, 3, 3 , 0, 2},	//水滴9
	};
	// 欧洲地图定位
	public final static int ep_map_position[][] = {
		{214, 212, 1034, 178},	//风车------0
		{94, 118, 976, 164},	//船------1
		{134, 276, 764, 80},	//钟
		{88, 114, 430, 252},	//气球------3
		{222, 360, 808, 304},	//铁塔
		{116, 96, 714, 596},	//火车------5
		{308, 254, 320, 630},	//斗牛场------6
		{126, 130, 834, 776},	//飞机
		{182, 372, 1212, 504},	//斜塔------8
		{216, 174, 1476, 514},	//飞行棋
		{232, 242, 1360, 148},	//图书馆------10
		{64, 78, 1002, 336},	//棋1
		{64, 78, 752, 324},	//棋2------12
		{64, 78, 790, 474},	//棋3
		{64, 78, 458, 894},	//棋4------14
		{64, 78, 1378, 840},	//棋5
		{64, 78, 1580, 694},	//棋6p------16
		{64, 78, 1298, 228},	//棋7l------17
		{112, 135, 1082, 218},	//锁1------18
		{112, 135, 780, 146}, //锁2------19
		{112, 135, 868, 442},	//锁3------20
		{112, 135, 1252, 628},	//锁4------21
	};
	
	// 国家游戏定位
	public final static int country_position[][] = {
		{430, 552, 294, 324},	//第一个游戏：学习------0
		{430, 552, 746, 324},	//第二个游戏：复习------1
		{430, 552, 1200, 324},	//第三个游戏：复习------2
		{266, 76, 370, 888},	//GO1------3
		{266, 76, 820, 888},	//GO2------4
		{266, 76, 1274, 888},	//GO3------5
		{250,258,1606, 26}//介绍	------6
	};
	
	public final static int other_position[][] = { // 1280X720
		{91, 73, 374, 294},	//打钩1------0
		{91, 73, 687, 294},	//打钩2------1
		{91, 73, 985, 294},	//打钩3------2
	};
	
	// 国家游戏定位
	public final static int country_introduction_position[][] = {
//			{ 798, 556, 270, 92 }, // 大图pic------0
			{ 1197, 834, 405, 138 }, // 大图pic------0
//			{ 576, 432, 336, 148 }, // pic------1
			{ 850, 648, 504, 222 }, // pic------1
			{ 540, 210, 1126, -40 }, //国家名字------2
			{ 492, 592, 164, 466 }, //荷兰人------3
			{ 540, 676, 164, 382 }, //英国人------4
			{ 428, 690, 212, 354 }, //法国人------5
			{ 390, 580, 244, 474 }, //西班牙人------6
			{ 402, 566, 248, 482 }, //意大利人------7
	};
	
	
	// 英国定位
	public final static int ep_level2_0_position[][] = {
			{ 396, 392, 1066, 538 }, // 来来------0
			{ 1478, 358, 242, 398 }, // 单词------1
			{ 698, 838, 612, 30 }, // 卡片------2
			{ 350, 270, 254, 194 }, // 音符1------3
			{ 350, 270, 600, 76 }, // 音符2------4
			{ 350, 270, 952, 68 }, // 音符3------5
			{ 350, 270, 1304, 180 }, // 音符4------6
			{ 240, 122, 844, 38 }, // 声音按钮------7
			
			{ 172, 152, 1188, 26 }, // 分------8
			{ 118, 156, 1355, 22 }, // 冒号-----9
			{ 172, 152, 1460, 26 }, // 秒------10
			{ 256, 110, 320, 950 }, // 计数器------11
			{ 172, 148, 258, 846 }, // 闹钟------12
	};
	
	public final static int ep_level2_0_index_to_position[][] = {
		// currentindex 和 position的对应关系
		{0,0},// 来来1
		{1,0},
		{2,0},
		{3,0},
		{4,0},
		{5,0},
		{6,0},
		{7,0},
		{8,0},
		{9,0},//来来10
		{10,0},//音符---四个音符显示
		{11,1},//单词
		{12,2},//卡片
		{13,0},//音符
		{14,1},
		{15,2},
		{16,0},//音符
		{17,1},
		{18,2},
		{19,0},//音符
		{20,1},
		{21,2},
	}; 
	
	public final static int map_selceted_position[][] = {
		// 1280*720
		{422, 552, 112, 30}, // ---0基础篇
		{422, 552, 748, 30}, // ---1进阶篇
		{226, 82, 184, 612}, // ---2GO1
		{226, 82, 864, 612}, // ---3GO2
	};
	
	// 法国定位
	public final static int ep_level3_0_position[][] = { // 1920*1080
		{174, 180, 878, 450}, // 宝石1---0
		{174, 180, 1412, 486}, // 宝石2---1
		{174, 180, 1044, 764}, // 宝石3---2
		{142, 84, 282, 856}, // 计数器1---3
		{380, 164, 278, 890}, // 计数器2---4
		{670, 684, 630, 200}, // 勋章---5
		{92, 94, 770, 218}, // 勋章圈1---6
		{92, 94, 1050, 210}, // 勋章圈2---7
		{92, 94, 1194, 354}, // 勋章圈3---8
		{92, 94, 1194, 634}, // 勋章圈4---9
		{92, 94, 1050, 774}, // 勋章圈5---10
		{92, 94, 776, 770}, // 勋章圈6---11
		{92, 94, 632, 632}, // 勋章圈7---12
		{92, 94, 636, 358}, // 勋章圈8---13
		{322, 276, 956, 338}, // 大宝石---14
		{376, 396, 930, 228}, // 字母---15
	};
	
	public final static int ep_level3_0_index_to_position[][] = {
		{0,5},// 来来1
		{1,5},
		{2,5},
		{3,5},
		{4,5},
		{5,5},
		{6,5},
		{7,5},
		{8,5},
		{9,5}
	};
	
	// 西班牙定位
	public final static int ep_level4_0_position[][] = { // 1920*1080
		{388, 944, 416, 66}, // 大C---0
		{182, 178, 296, 812}, // 旗帜---1
//		{310, 128, 302, 936}, // 计数器---2
		{380, 164, 302, 936}, // 计数器---2
		{474, 460, 256, 326}, // 来来---3
		{298, 222, 694, 838}, // 问号---4
		
	};
	
	public final static int ep_level4_0_index_to_position[][] = {
		{0,0},// 来来1
		{1,0},
		{2,0},
		{3,0},
		{4,0},
		{5,3},
		{6,4},
		{7,3},
		{8,3},
		{9,4}, 
		{10,3},
		{11,3},
		{12,4},
		{13,3},
		{14,3},
		{15,4},
		{16,3},
	};
	
	// 意大利定位
	public final static int ep_level5_0_position[][] = { // 1920*1080
		{602, 400, 1072, 352}, // r---0
		{258, 220, 730, 798}, // 宝箱---1
		{308, 368, 286, 216}, // 钱袋1---2
		{308, 368, 638, 132}, // 钱袋2
		{308, 368, 974, 116}, // 钱袋3
		{308, 368, 1316, 198}, // 钱袋4
		{286, 270, 808, 436}, // 石头1---6
		{628, 584, 626, 334}, // 石头2
		{594, 666, 636, 254}, // 石头3
		
		{364, 596, 760, 286}, // 花1---9
		{320, 622, 784, 258}, // 花2
		{592, 634, 676, 328}, // 花3
		
		{348, 676, 728, 282}, // 兔子1---12
		{450, 692, 684, 284}, // 兔子2
		{450, 692, 684, 284}, // 兔子3
		
		{858, 658, 568, 360}, // 老鼠1---15
		{544, 568, 710, 318}, // 老鼠2
		{496, 634, 760, 248}, // 老鼠3
		
		{188, 132, 270, 856}, // 计数-信封---18
		{312, 136, 304, 936}, // 计数-bg
		
	};

	public final static int ep_level5_0_index_to_position[][] = {
		{0,0},// r1
		{1,0},
		{2,0},
		{3,0},
		{4,0},
		{5,0},
		{6,0},
		{7,0},
		{8,0},
		{9,1}, // 宝箱
		{10,3},//------随机点击钱袋
		{11,6},//rock
		{12,7},
		{13,8},
		{14,1},// 宝箱
		{15,3},//------随机点击钱袋
		{16,9},//rose
		{17,10},
		{18,11},
		{19,1},// 宝箱
		{20,3},//------随机点击钱袋
		{21,12},//rabbit
		{22,13},
		{23,14},
		{24,1},// 宝箱
		{25,3},//------随机点击钱袋
		{26,15},//rat
		{27,16},
		{28,17},
	};
	
	public final static int big_position[][] = {
		// 位置 w、h、x、y 1280X720
		{ 182, 184, 194, 540 }, // image1--0
		{ 182, 184, 436, 540 }, // image1
		{ 182, 184, 666, 540 }, // image1
		{ 182, 184, 905, 540 }, // image1
		{ 406, 378, 292, 132 }, // big_bg
		{ 252, 238, 740, 216 }, // little_bg

		{ 202, 201, 331, 257 }, // dx大象--6
		{ 143, 186, 520, 264 }, // kl恐龙
		{ 100, 100, 756, 308 }, // kc昆虫
		{ 100, 103, 862, 306 }, // ls老鼠--9

		{ 143, 186, 327, 276 }, // kl恐龙--10
		{ 202, 201, 469, 269 }, // dx大象
		{ 100, 103, 756, 306 }, // ls老鼠
		{ 100, 100, 862, 307 }, // kc昆虫--13
};

public final static int gogo_position[][] = {// 1280x720
	{ 750, 215, 250, 505 }, // 底图背景
	
	{ 109, 51, 131, 147 }, // 卡片名字1--1
	{ 109, 51, 434, 147 }, // 卡片名字2
	{ 109, 51, 729, 147 }, // 卡片名字3
	{ 109, 51, 1028, 147 }, // 卡片名字4
	
	{ 207, 249, 77, 210 }, // 大卡片1--5
	{ 207, 249, 385, 210 }, // 大卡片2
	{ 207, 249, 680, 210 }, // 大卡片3
	{ 207, 249, 982, 210 }, // 大卡片4
	
	{ 139, 139, 284, 528 }, // 底部1--9
	{ 139, 139, 465, 528 }, // 底部2
	{ 139, 139, 648, 528 }, // 底部3
	{ 139, 139, 832, 528 }, // 底部4--12
};

public final static int garden_position[][] = {// 1280x720
	{ 146, 578, 22, 132 }, // 背景1--0
	{ 146, 441, 1112, 172 }, // 背景2--1
	{ 100, 99, 44, 151 }, //peas
	{ 100, 99, 44, 291 }, //beans
	{ 100, 99, 44, 423 }, //melons
	{ 100, 99, 44, 561 }, //onions
	{ 100, 99, 1133, 195 }, //potatoes
	{ 100, 99, 1133, 330 }, //tomatoes
	{ 100, 99, 1133, 465 }, //corn
	
	{ 425, 538, 423, 125 }, //大peas--9
	{ 374, 556, 449, 127 }, //大beans
	{ 424, 382, 420, 242 }, //大melons
	{ 386, 464, 428, 147 }, //大onions
	{ 336, 500, 521, 146 }, //大potatoes
	{ 415, 508, 420, 152 }, //大tomatoes
	{ 231, 497, 535, 151 }, //大corn
	
	{ 152, 146, 630, 376 }, //peas--16
	{ 190, 174, 650, 284 }, //beans--
	{ 424, 382, 420, 242 }, //melons
	{ 140, 136, 568, 490 }, //onions--
	{ 274, 196, 496, 418 }, //potatoes--
	{ 214, 116, 606, 346 }, //tomatoes--
	{ 194, 186, 536, 290 }, //corn--
};

public final static int little_position[][] = {// 1280x720
	{ 420, 528, 200, 145 }, //卡片1
	{ 420, 528, 664, 145 }, //卡片2
	
	{ 216, 174, 295, 341 }, //左对1---2
	{ 170, 184, 320, 341 }, //左错2
	
	{ 216, 174, 765, 341 }, //右对1---4
	{ 170, 184, 791, 341 }, //右错2
};

public final static int supermarket_position[][] = {// 1280x720
	{ 244, 208, 211, 221 }, //卡片1-eggs---0
	{ 249, 200, 559, 138 }, //卡片2
	{ 247, 202, 840, 264 }, //卡片3
	{ 216, 174, 229, 255 }, //卡片状态1---3
	{ 216, 174, 559, 174 }, //卡片状态2
	{ 216, 174, 840, 299 }, //卡片状态3
	{ 388, 319, 460, 307 }, //来来---6
	{ 725, 157, 294, 563 }, //底部背景
	
	{ 127, 127, 318, 582 }, //单词1---8
	{ 127, 127, 456, 582 }, //单词2
	{ 127, 127, 593, 582 }, //单词3
	{ 127, 127, 729, 582 }, //单词4
	{ 127, 127, 865, 582 }, //单词5---12
	
	{ 91, 73, 339, 647 }, //单词状态1---13
	{ 91, 73, 478, 647 }, //单词状态2
	{ 91, 73, 614, 647 }, //单词状态3
	{ 91, 73, 749, 647 }, //单词状态4
	{ 91, 73, 879, 647 }, //单词状态5---17
};
	public final static int  Game_003_CardPosition[][]={
		{231,349,124,210},//card1
		{231,349,390,210},//card2
		{231,349,662,210},//card3
		{231,349,934,210},//card4
		{109,90,26,2},//bt_home

	};
	public final static int  Game_005_CardPosition[][]={
		{224,33,882,188},//clothes_rod
		{301,168,416,436},//sled
		{192,271,346,103},//snow
		{208,292,18,134},//picture//1c
		{205,171,796,185},//coat
		{174,156,930,460},//shoes
		{262,321,169,401},//snowman//2c
		{167,239,959,196},//swimsuit
		{373,209,742,509},//cabinet_a//3c
		{166,214,716,473},//mittens
		{109,90,26,2},//bt_home

	};
	public final static int  Game_010_CardPosition[][]={
		{202,272,335,136},//card1
		{202,272,653,136},//card2
		{202,272,179,430},//card3
		{202,272,491,430},//card4
		{202,272,799,430},//card5
		{109,90,26,2},//bt_home

	};
	public final static int Game_017_CardPosition[][]={

		{127,127,235,587},//card1
		{127,127,373,587},//card2
		{127,127,509,587},//card3
		{127,127,645,587},//card4
		{127,127,783,587},//card5
		{127,127,920,587},//card6
		{151,177,165,383},//dog 6
		{199,163,329,255},//cow  7
		{207,151,536,405},//pig   8
		{172,142,686,251},//sheep  9
		{116,169,894,388},//duck   10
		{124,125,1076,402},//chicken  11
		{862,156,209,564},//broad12
		{109,90,26,2},//bt_home13
		{1280,365,0,355}//14
	};

	public final static int ebookgame007_position[][]={
		//W  H    X  Y
		{127,127,302,586},   
		{127,127,440,586},
		{127,127,577,586},
		{127,127,713,586},
		{127,127,851,586},
		{725,157,278,563},   //选择框  5
		{322,184,7,536},     //大水草 6
		{303,356,64,227},    //珊瑚 7
		{233,187,495,365},  //螃蟹8
		{173,152,631,173},  //小黄鱼9
		{169,119,819,438}, //海星10
		{288,301,938,142}, //海草11
		{109,90,26,2},//bt_home
		
	};
	
	public final static int ebookgame009_position[][]={
		//W  H    X  Y
		{278,121,0,150},   //右边第一个   0
		{278,121,0,290},   //右边第二个   1
		{278,121,0,430},   //右边第三个   2
		{278,121,0,570},   //右边第四个   3
		{278,121,1002,214},   //左边第一个 4
		{278,121,1002,356},   //左边第二个 5
		{278,121,1002,496},   //左边第三个 6
		{338,376,502,227},  // 大蛋 7
		{161,64,551,230},   //颜色第一个条 8
 		{221,100,526,243},   //颜色第二个条 9
		{279,130,509,270},   //颜色第三个条 10
		{301,135,503,327},   //颜色第四个条 11
		{306,149,504,378},   //颜色第五个条 12
		{284,145,525,434},   //颜色第六个条  13
		{203,71,582,522},   //颜色第七个条   14
		{109,90,26,2},//bt_home

	};
	
	public final static int build_position[][] = {// 1280x720
		{ 209, 252, 195, 191 }, //卡片1---0
		{ 209, 252, 419, 191 }, //卡片2
		{ 209, 252, 649, 191 }, //卡片3
		{ 209, 252, 877, 191 }, //卡片4
		{ 497, 39, 385, 300 }, //箭头---4
		{ 153, 185, 208, 491 }, //bottom_bg_1---5
		{ 153, 185, 454, 491 }, //bottom_bg_2
		{ 153, 185, 680, 491 }, //bottom_bg_3
		{ 153, 185, 917, 491 }, //bottom_bg_4
		{ 157, 52, 208, 676 }, //bottom_name_1---9
		{ 157, 52, 454, 676 }, //bottom_name_2
		{ 157, 52, 676, 676 }, //bottom_name_3
		{ 157, 52, 915, 676 }, //bottom_name_4
		
	};
	
	public final static int backyard_position[][] = {// 1280x720
		{ 489, 182, 27, 147 }, //上卡片1---0
		{ 235, 183, 605, 147 }, //上卡片2
		{ 235, 183, 982, 147 }, //上卡片3
		{ 119, 95, 397, 147 }, //上right
		{ 489, 182, 27, 418 }, //下卡片1---4
		{ 246, 169, 605, 418 }, //下卡片2
		{ 246, 169, 982, 418 }, //下卡片3
		{ 119, 95, 397, 418 }, //下right---7
		{ 83, 90, 757, 418 }, //下error1
		{ 83, 90, 1145, 418 }, //下error2
	};
	
	public final static int in_position[][] = {// 1280x720
		{ 127, 494, 35, 177 }, //左边bg
		{ 97, 93, 48, 199 }, //左边star_1
		{ 97, 93, 48, 315 }, //左边star_2
		{ 97, 93, 48, 438 }, //左边star_3
		{ 97, 93, 48, 555 }, //左边star_4
		
		{ 132, 563, 1127, 145 }, //右bg
		{ 133, 133, 1127, 147 }, //右边animal1
		{ 133, 133, 1127, 292 }, //右边animal2
		{ 133, 133, 1127, 432 }, //右边animal3
		{ 133, 133, 1127, 574 }, //右边animal4
		
		{ 514, 286, 369, 348 }, //中间bg1
		{ 491, 529, 392, 177 }, //中间bg2
		{ 88, 86, 567, 496 }, //中间star_1
		{ 88, 86, 642, 475 }, //中间star_2
		{ 88, 86, 714, 455 }, //中间star_3
		{ 88, 86, 789, 429 }, //中间star_4
		
		{ 90, 87, 566, 495 }, //中间star_1bg
		{ 90, 87, 641, 475 }, //中间star_2bg
		{ 90, 87, 713, 454 }, //中间star_3bg
		{ 90, 87, 788, 428 }, //中间star_4bg
		
		{ 315, 217, 412, 285 }, //中间animal_pig
		{ 266, 256, 564, 169 }, //中间animal_goat
		{ 211, 235, 653, 277 }, //中间animal_dog
		{ 145, 203, 532, 315 }, //中间animal_duck
		{ 294, 140, 582, 434 }, //中间star_layout
	};
	
	public final static int out_position[][] = {// 1280x720
		{ 612, 464, 341, 214 }, //浴缸
		{ 243, 232, 485, 214 }, //cat
		{ 157, 153, 528, 214 }, //ball
		{ 161, 185, 541, 214 }, //duck
		{ 255, 218, 491, 214 }, //dog
		{ 1280, 255, 0, 465 }, //拖动框
	};
	
	public final static int ebookgame011_position[][]={
		//W  H  X  Y
		{128,128,233,568},  //小面的第一个小框   0
		{128,128,371,568},  //小面的第二个小框   1
		{128,128,507,568},  //小面的第三个小框   2
		{128,128,644,568},  //小面的第四个小框   3
		{128,128,780,568},  //小面的第五个小框   4
		{128,128,917,568},  //小面的第六个小框   5
		{109,51,347,131},   //字thing  6
		{109,51,817,131},   //字place  7
		{438,325,186,196},  //大框框 thing  8  
		{438,325,650,196},  //大框框 place  9
		{195,249,772,229},  //大框框中的房子  10
		{119,118,228,234},  //大框框中的 第一个方块 11
		{119,118,348,234},  //大框框中的 第二个方块  12
		{119,118,468,234},  //大框框中的 第三个方块  13 
		{119,118,286,354},  //大框框中的 第四个方块  14
		{119,118,415,354},  //大框框中的 第五个方块  15
		{861,174,209,546},  //下面的大框框  16
		{109,90,26,2},//bt_home
	};
	
	public final static int ebookgame013_position[][]={
		//w   h  x  y 
		{127,127,233,586},  //第一个itme 0
		{127,127,371,586},  //第二个itme 1
		{127,127,508,586},  //第三个itme 2
		{127,127,644,586},  //第四个itme 3
		{127,127,781,586},  //第五个itme 4
		{127,127,919,586},  //第六个itme 5
		{862,157,209,563},  //下面的大框框 6
		{769,342,0,378},    //海浪 7
		{69,176,815,158},   //灯塔  8
		{642,409,638,311},  //高地  9
		{397,252,667,407},  //石头  10
		{1239,335,0,385},    //土地  11
		{109,90,26,2},//bt_home
	};
	
	
	public final static int ebookgame014_position[][]={
		//W  H  X  Y 
		{111,111,169,602},  //下面的第一个itme 0
		{111,111,289,602},  //下面的第二个itme 1
		{111,111,408,602},  //下面的第三个itme 2
 		{111,111,527,602},  //下面的第四个itme 3
		{111,111,645,602},  //下面的第五个itme 4
		{111,111,765,602},  //下面的第六个itme 5
		{111,111,883,602},  //下面的第七个itme 6 
		{111,111,1003,602}, //下面的第八个itme 7
		
		{93,85,683,243},   //大框框中的第一个itme 8
		{93,85,794,243},   //大框框中的第二个itme 9
		{93,85,904,243},   //大框框中的第三个itme 10
		{93,85,683,345},   //大框框中的第四个itme 11
		{93,85,794,345},   //大框框中的第五个itme 12
		{93,85,799,446},   //大框框中的第六个itme 13
		{93,85,910,446},   //大框框中的第七个itme 14
		
		{176,165,347,293},//第一个 大框框中的Itme 15
		
		{387,402,243,155}, //第一个大框 16
		{387,402,646,155},  //第二个大框 17
		{975,132,153,588},  //下面的物品栏 18
		
	};
	
	public final static int ebookgame015_position[][]={
		// w  h  x  y
		{112,124,114,323},   //开始的塔                        0 
		{112,124,922,234},   //结束的塔                        1
		{78,35,234,358},     //start 字                       2
		{59,34,872,331},     //end 字                            3
		{106,79,211,397},     //车子的初始位置      4
		{199,119,321,528},    //衣服                               5
		{150,177,591,339},    //狗                                    6
		{216,189,738,136},    //爷爷奶奶                     7
		{41,40,961,358},      //红叉                               8   
		{712,163,287,398},    //路线                               9
		{109,90,26,2},//bt_home
 	};
	
}
