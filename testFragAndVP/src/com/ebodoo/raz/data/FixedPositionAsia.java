package com.ebodoo.raz.data;

import android.content.Context;

import com.ebodoo.raz.utils.BaseCommon;

public class FixedPositionAsia {
	
	// 新加坡学习部分--定位
	public final static int asia_level1_0_position[][] = {
		//位置 w、h、x、y	1280X720
		{267, 212, 297, 38},	//狮子身子1---0
		{267, 212, 564, -8},	//狮子身子2
		{267, 212, 787, 78},	//狮子身子3
		{267, 212, 839, 273},	//狮子身子4
		{267, 212, 721, 432},	//狮子身子5
		{267, 212, 540, 485},	//狮子身子6
		{267, 212, 335, 441},	//狮子身子7
		{267, 212, 158, 265},	//狮子身子8
		
		
		
		{207, 246, 248, 425},	//狮子头1---0
		{207, 246, 207, 226},	//狮子头2
		{207, 246, 281, 4},		//狮子头3
		{207, 246, 470, 0},		//狮子头4
		{207, 246, 651, -16},	//狮子头5
		{207, 246, 844, 42},	//狮子头6
		{207, 246, 860, 249},	//狮子头7
		{207, 246, 847, 458},	//狮子头8
		
		{162, 241, 244, 108},	//狮子1---0
		{162, 241, 451, 108},	//狮子2
		{162, 241, 655, 108},	//狮子3
		{162, 241, 860, 108},	//狮子4
		{162, 241, 244, 339},	//狮子5
		{162, 241, 451, 339},	//狮子6
		{162, 241, 655, 339},	//狮子7
		{162, 241, 860, 339},	//狮子8
		

		{111, 134, 165, 522}, //24狮子石像		
		{207, 85, 189, 624}, //25计数器背景	
		
	};
	// 新加坡复习1--定位
	public final static int asia_level1_1_position[][] = {
		//位置 w、h、x、y	1280X720
		
		/*--------------1--------------*/
		
		{207, 252, 191, 132},	//4选1	0---0
		{207, 252, 420, 132},	//4选1	1
		{207, 252, 653, 132},	//4选1	2
		{207, 252, 884, 132},	//4选1	3
		
		{126, 117, 509, 551},	//左字母 4
		{126, 117, 632, 551},	//右字母5
		
		{358, 212, 454, 482},	//大香蕉6
		{109, 101, 172, 551},	//小香蕉7
		
		/*--------------2--------------*/
		{159, 159, 328, 480},	//选项8
		{159, 159, 556, 480},	//选项9
		{159, 159, 777, 480},	//选项10
		
		{345, 270, 461, 184},	//单词对应的图片11

		{126, 117, 496, 46},	//左字母 12
		{126, 117, 617, 46},	//右字母13
		
		{363, 174, 459, 0},	//复习1-2横苹果 14
		{88, 93, 179, 564},		//小苹果15		------------16
		
		
		/*--------------3--------------*/
		
		{207, 252, 191, 132},	//4选1	0---0
		{207, 252, 420, 132},	//4选1	1
		{207, 252, 653, 132},	//4选1	2
		{207, 252, 884, 132},	//4选1	3
		
		{124, 116, 491, 545},	//左字母 4
		{124, 116, 616, 549},	//右字母5
		
		{404, 207, 421, 507},	//大花环6
		{147, 134, 166, 518},	//小花环7
		
		/*--------------4--------------*/

		{159, 159, 328, 480},	//选项8
		{159, 159, 556, 480},	//选项9
		{159, 159, 777, 480},	//选项10
		
		{345, 270, 461, 184},	//单词对应的图片11

		{124, 116,  486, 41},	//左字母 12
		{124, 116,  612, 41},	//右字母13
		
		{404, 207, 424, 4},	//大花环 14
		{147, 134, 166, 518},		//小花环15
		
		
		/*--------------共用--------------*/
		{126, 117, 612, 46},	//做字母横线      	32
		{100, 100, 1180, 2},	//重读read 			33
		{207, 85, 189, 624}, //25计数器背景		34
		
	};
		
	// 新加坡复习2--定位
	public final static int asia_level1_2_position[][] = {
		//位置 w、h、x、y	1280X720
		{158, 183, 337, 67},	//水桶1---0
		{158, 183, 565, 67},	//水桶2
		{158, 183, 793, 67},	//水桶3
		
		{158, 183, 337, 264},	//水桶4
		{158, 183, 565, 264},	//水桶5
		{158, 183, 793, 264},	//水桶6
		
		{158, 183, 337, 458},	//水桶7
		{158, 183, 565, 458},	//水桶8
		{158, 183, 793, 458},	//水桶9
		
		
		{89, 107, 170, 540},	//小水桶10---9
		
	};
	// 新加坡复习2--定位
	public final static int asia_common_position[][] = {
		{100, 100, 1180, 2},	//重读read 			0
		{207, 85, 189, 624}, //25计数器背景		1
	};
	
	// 获取学习对应的图片————参数：玩法、第几张图片
	public static int getXuexi1_0Pic(Context context,
			int gameType, int index_iv) {
		if (gameType == 0) {
			return BaseCommon.getImage(context,
					xuexi1_0[index_iv]);
		}else if (gameType == 1) {
			return BaseCommon.getImage(context,
					xuexi1_0[index_iv+8]);
		}else if (gameType == 2) {
			return BaseCommon.getImage(context,
					xuexi1_0[16]);
		}else{
			return BaseCommon.getImage(context,
					xuexi1_0[16]);
		}

	}
	// 埃及——复习2对应的字母
	private final static String xuexi1_0[] = { "xjp_shi_1_1", "xjp_shi_1_2",
			"xjp_shi_1_3", "xjp_shi_1_4", "xjp_shi_1_5", "xjp_shi_1_6", "xjp_shi_1_7", "xjp_shi_1_8",
			"xjp_shi_2_1", "xjp_shi_2_2", "xjp_shi_2_3", "xjp_shi_2_4", "xjp_shi_2_5", "xjp_shi_2_6", "xjp_shi_2_7", "xjp_shi_2_8", 
			"xjp_shi" };
	
	
	public static String fuxi_word_1_1[][]={
		//测试顺序对应的卡片，            左字母，右字母，         听到的左字母读音，右字母读音，拼一起的读音, 单词对应的图片
		{"card_hill","word2_h","word2_ill","flash_h02_sound","flash_ill_02_sound","flash_ill_07_hill","pic_hill"},
		{"card_mill","word2_m","word2_ill","flash_m02_sound","flash_ill_02_sound","flash_ill_11_mill","pic_mill"},
		{"card_pill","word2_p","word2_ill","flash_p02_sound","flash_ill_02_sound","flash_ill_13_pill","pic_pill"},
		{"card_sill","word2_s","word2_ill","flash_s02_sound","flash_ill_02_sound","flash_ill_03_sill","pic_sill"},
		
		{"card_dip","word_d","word_ip","flash_d02_sound","flash_ip_02_sound","flash_ip_07_dip","pic_dip"},
		{"card_rip","word_r","word_ip","flash_r02_sound","flash_ip_02_sound","flash_ip_09_rip","pic_rip"},
		{"card_sip","word_s","word_ip","flash_s02_sound","flash_ip_02_sound","flash_ip_05_sip","pic_sip"},
		{"card_zip","word_z","word_ip","flash_z02_sound","flash_ip_02_sound","flash_ip_03_zip","pic_zip"},
		
		
	};
	//复习1中，玩法2，选择的三个选项
	public static String fuxi_select_right_1_1[]={
		"select_ill",
		"select_op",
		"select_at",
		
		"select_ip",
		"select_ill",
		"select_ot",
	};
	
	// 判断选择的结果是否正确————参数：玩法、第几张图片
	public static boolean isGood(int countryType, int gameType, int index_iv) {
		if (countryType == 1 && game1_2_position[index_iv][gameType] == 0) {
			return true;
		} /*else if (countryType == 2
				&& game2_2_position[index_iv][gameType] == 0) {
			return true;
		} else if (countryType == 3
				&& game3_2_position[index_iv][gameType] == 0) {
			return true;
		} else if (countryType == 4
				&& game4_2_position[index_iv][gameType] == 0) {
			return true;
		} else if (countryType == 5
				&& game5_2_position[index_iv][gameType] == 0) {
			return true;
		} */else {
			return false;
		}
	}
	// 获取复习2对应的图片————参数：玩法、第几张图片
	public static int getFuxi1_2Pic2(Context context, int countryType,
			int gameType, int index_iv, int index) {
		if (countryType == 1 && index <= 1) {
			return BaseCommon.getImage(context,
					fuxi1_2_zm[game1_2_position[index_iv][gameType]]);
		} else if (countryType == 1 && index > 1 && index <= 3) {
			return BaseCommon.getImage(context,
					fuxi1_2_zm2[game1_2_position[index_iv][gameType]]);
		} else if (countryType == 1 && index > 3 && index <= 5) {
			return BaseCommon.getImage(context,
					fuxi1_2_zm3[game1_2_position[index_iv][gameType]]);
		} else if (countryType == 1 && index > 5 && index <= 7) {
			return BaseCommon.getImage(context,
					fuxi1_2_zm4[game1_2_position[index_iv][gameType]]);
		} else {
			return BaseCommon.getImage(context,
					fuxi1_2_zm[game1_2_position[index_iv][gameType]]);
		}

	}
	// 新加坡——复习2对应的字母 c
	private final static String fuxi1_2_zm[] = { "xjp_ill", "xjp_ip", "xjp_ot",
			"xjp_en", };
	private final static String fuxi1_2_zm2[] = { "xjp_ip","xjp_ill",  "xjp_ot",
		"xjp_en", };
	private final static String fuxi1_2_zm3[] = { "xjp_ot", "xjp_ill", "xjp_ip", 
		"xjp_en", };
	private final static String fuxi1_2_zm4[] = { "xjp_en","xjp_ill", "xjp_ip", "xjp_ot",
		 };
	
	// 新加坡水桶多总玩法 字母位置的随机 ad/an/in/it 0 1 2 3 ——————————0~5随机
	private final static int game1_2_position[][] = {
			// 玩法1 玩法2 玩法3…… 分别对应的字母
			{ 2, 0, 1, 3, 3, 0 }, // 水桶1
			{ 1, 3, 2, 1, 0, 3 }, // 水桶2
			{ 0, 1, 0, 3, 3, 2 }, // 水桶3
			{ 3, 0, 2, 1, 2, 0 }, // 水桶4
			{ 0, 0, 3, 2, 1, 2 }, // 水桶5
			{ 0, 1, 2, 0, 2, 0 }, // 水桶6
			{ 2, 3, 1, 0, 0, 1 }, // 水桶7
			{ 1, 2, 0, 0, 3, 3 }, // 水桶8
			{ 3, 2, 0, 3, 0, 2 }, // 水桶9
	};	
	
	// 印度学习部分--定位 1280*720
	public final static int asia_level3_0_position[][] = {
		{ 205, 180, 186, 137}, // 吸尘器1
		{ 205, 180, 414, 137},
		{ 205, 180, 642, 137},
		{ 205, 180, 869, 137},
		{ 205, 180, 186, 374},
		{ 205, 180, 414, 374},
		{ 205, 180, 642, 374},
		{ 205, 180, 869, 374},
		
		{ 315, 245, 271, 125}, // card 1
		{ 315, 245, 674, 125},
		{ 315, 245, 271, 398},
		{ 315, 245, 674, 398},
		{ 220, 220, 165, 103}, // 手1
		{ 220, 220, 310, 125}, // 手2
		{ 207, 85, 186, 620}, // 计数器
		{ 135, 88, 162, 575}, // 小吸尘器
		
	};
	
	// 印度复习1部分--定位 1280*720
	public final static int asia_level3_1_position[][] = { 
		{ 100, 100, 1155, 11 }, // 再读
		{ 207, 253, 174, 204 }, // 卡片
		{ 207, 253, 412, 204 },
		{ 207, 253, 644, 204 },
		{ 207, 253, 887, 204 },
		
		{ 216, 185, 170, 308 }, // answer
		{ 216, 185, 408, 308 },
		{ 216, 185, 639, 308 },
		{ 216, 185, 883, 308 },
		
		{ 207, 85, 187, 616 }, // 计数器
		{ 115, 61, 167, 601 }, // 云
		
		{ 293, 160, 510, 470 }, // word
		
		{ 467, 394, 409, 42 }, // 卡片
		{ 84, 84, 460, 446 }, // 空白卡
		{ 84, 84, 596, 446 },
		{ 84, 84, 731, 446 },
		
		{ 132, 149, 433, 403 }, // top 字母
		{ 132, 149, 571, 403 },
		{ 132, 149, 706, 403 },
		
		{ 132, 149, 433, 517 }, // bottom 字母
		{ 132, 149, 574, 517 },
		{ 132, 149, 711, 517 },
	};
	
	// 印度复习1部分--定位 1280*720
	public final static int asia_level3_2_position[][] = {
		{ 185, 183, 234, 31 }, // 鼓
		{ 185, 183, 512, 31 },
		{ 185, 183, 799, 31 },
		{ 185, 183, 234, 236 }, // 鼓
		{ 185, 183, 512, 236 },
		{ 185, 183, 799, 236 },
		{ 185, 183, 234, 436 }, // 鼓
		{ 185, 183, 512, 436 },
		{ 185, 183, 799, 436 },
		
		{ 108, 108, 261, 110 }, // answer
		{ 108, 108, 547, 110 },
		{ 108, 108, 834, 110 },
		
		{ 108, 108, 261, 317 },
		{ 108, 108, 547, 317 },
		{ 108, 108, 834, 317 },
		
		{ 108, 108, 261, 518 },
		{ 108, 108, 547, 518 },
		{ 108, 108, 834, 518 },
		
		{ 207, 85, 185, 622 }, // 计数器
		{ 101, 99, 162, 567 }, // 小鼓
	};
	
	public final static int wear_sel_position[][] = {
		{ 830, 720, 0, 0}, // 磨耳大声说背景
		{ 450, 360, 830, 0}, // 儿歌背景
		{ 450, 360, 830, 360}, // 动画背景
		{ 341, 70, 488, 596}, // 磨耳大声说按钮
		{ 277, 251, 929, 16}, // 光盘
		{ 213, 65, 1067, 266}, // 听儿歌
		{ 277, 251, 929, 377}, // 电视
		{ 213, 65, 1067, 636}, // 动画


		/*{ 411, 378, 115, 122 },
		{ 282, 86, 0, 581 }, 
		{ 400, 464, 773, 86 },
		{ 282, 86, 998, 584 },*/
	};
	
	public final static int wear_video_albums_position[][] = { 
		{ 1280, 90, 0, 0 }, // title
		{ 527, 396, 45, 36 }, // 视频列表图片背景
		{ 3, 591, 618, 110 },  // 视频列表 竖线
		
		{ 466, 720, 814, 0 }, // 历史记录背景
		{ 466, 629, 814, 91 }, // 历史记录 ListView的范围
		{ 159, 53, 943, 23 }, // 历史记录 标题的范围
	};
	
	// 日本学习部分--定位 1280*720
	public final static int asia_level5_0_position[][] = {
			{ 177, 177, 292, 68}, // 花瓣
			{ 177, 177, 548, 68},
			{ 177, 177, 808, 68},
			{ 177, 177, 292, 321},
			{ 177, 177, 548, 321},
			{ 177, 177, 808, 321},
			
			{ 250, 269, 333, 70}, // 茶杯
			{ 250, 269, 723, 70},
			{ 250, 269, 333, 385},
			{ 250, 269, 723, 385},
			
			{ 333, 289, 292, 38}, // 篮子
			{ 333, 289, 655, 38},
			{ 333, 289, 292, 341},
			{ 333, 289, 655, 341},

			{ 207, 85, 186, 620 },// 计数器
			{ 104, 87, 169, 581 }, // 小花
			{ 104, 111, 167, 557 }, // 小茶杯
			{ 125, 111, 167, 562 }, // 小篮子
			{ 220, 220, 257, 30}, // 手 1
			{ 220, 220, 333, 62}, // 手 2

	};
	
	// 日本复习1部分--定位 1280*720
	public final static int asia_level5_1_position[][] = {
		{ 395, 127, 432, 18}, // 字母背景
		{ 432, 160, 432, 6}, // 字母
		{ 419, 341, 446, 180}, // 大图
		{ 163, 164, 405, 523}, // ot
		{ 163, 164, 596, 523}, // ob
		{ 163, 164, 788, 523}, // op
		
		{ 433, 161, 431, 6}, // 第二部分  --  字母
		{ 419, 341, 445, 171}, // 第二部分  --  大图
		{ 232, 173, 374, 519}, // 第二部分  --  ell
		{ 232, 173, 666, 519}, // 第二部分  --  ill
		
		{ 207, 85, 186, 620 },// 计数器
		{ 106, 112, 171, 573},// 小竹子
		{ 91, 66, 173, 606},// 小飞镖
		{100, 100, 1180, 2},	//重读read
	};
	
	// 日本复习2部分--定位 1280*720
		public final static int asia_level5_2_position[][] = {
			{ 266, 215, 236, 96},
			{ 266, 215, 502, 110},
			{ 266, 215, 777, 110},
			{ 266, 215, 236, 344},
			{ 266, 215, 514, 344},
			{ 266, 215, 786, 344},
			
			{ 207, 85, 184, 620 },// 计数器
			{ 105, 87, 164, 563 }, 
			{100, 100, 1180, 2},	//重读read
		};
}
