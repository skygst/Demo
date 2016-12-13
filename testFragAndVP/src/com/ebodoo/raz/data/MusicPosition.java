package com.ebodoo.raz.data;

import com.ebodoo.raz.utils.MediaCommon;

public class MusicPosition {
	
	
	public static int getClickMusicPositionIndex(int arry[][],int currentIndex){
		int size = arry.length;
		for(int i=0;i<size;i++){
			if(currentIndex == arry[i][0]){
				return arry[i][1];
			}
		}
		return -1;
	}
	
	public static int getReadAftermeMusicPositionIndex(int arry[],int currentIndex){
		int size = arry.length;
		for(int i=0;i<size;i++){
			if(currentIndex == arry[i]){
				return arry[i];
			}
		}
		return -1;
	}
	//quiz 闯关 专用 获得点击返回的声音
	public static int getClickMusicPositionIndex(int arry[][],int currentIndex,int type){
		int size = arry.length;
		for(int i=0;i<size;i++){
			if(currentIndex == arry[i][0]){
				if(type == 0){
					return arry[i][2];
				}else if(type == 1){
					return arry[i][4];
				}else if(type == 2){
					return arry[i][6];
				}
				
			}
		}
		return -1;
	}
	//quiz 闯关 专用 获得 单词 读音
	public static int getSoundPositionIndex(int arry[][],int currentIndex,int type){
		int size = arry.length;
		for(int i=0;i<size;i++){
			if(currentIndex == arry[i][0]){
				if(type == 0){
					return arry[i][1];
				}else if(type == 1){
					return arry[i][3];
				}else if(type == 2){
					return arry[i][5];
				}
				
			}
		}
		return -1;
	}
	
	//quiz 获得 sayit thisChar字母
	public static String getThisChar(String arry[][],int currentIndex){
		int size = arry.length;
		for(int i=0;i<size;i++){
			if(arry[i][0].equals(currentIndex+"")){
				return arry[i][1];
			}
		}
		return "";
	}
	/*-------------------------08   level   声音------------------------------*/
	//最后一个循环开启背景音
	public static int lastIndex08 = 117;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int jingyinIndex108 = 89;
	public static int jingyinIndex208 = 117;
	/*-----currentindex 和music position的对应关系------*/
	public static String play08Start(int currentIndex){
		String startpath="";
		if(currentIndex == 9){
//			startpath = MediaCommon.getLevel08Mp3(1);
		}
		return startpath;
	}
	
	//跟我读的提示 节点
	public final static int level08_readafterme[]={
		89,95,101,107
	};
	//点点这里
	public final static int level08_clickhere[]={
		5,17,21,85,117
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level08_music_position[][] = {
		//sayit word
		{1,6},
		//d dog
		{25,8},
		{29,8},
		//d duck
		{40,10},
		{44,10},
		//d deer
		{55,12},
		{59,12},
		//d desk
		{70,14},
		{74,14},
		//语言识别
		{113,7},
		{114,9},
		{115,11},
		{116,13}
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level08_click_music_position[][] = {	
		{13,5},        //
		{17,15},       //话外音2
		
//		{42,9},
//		{57,11},
//		{72,13},

		
	};

	
	/*-------------------------09   level   声音------------------------------*/
	//最后一个循环开启背景音
	public static int lastIndex09 = 189;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int jingyinIndex1 = 143;
	public static int jingyinIndex2 = 187;
	
	//闯关第一次提示语
	public static int indexChuangguanTishi = 117;
	public static int indexChuangguanTishiFirst = 300;
	/*-----currentindex 和music position的对应关系------*/
	public static String play09Start(int currentIndex){
		String startpath="";
		if(currentIndex == 3){
//			startpath = MediaCommon.getLevel09Mp3(1);
		}
		return startpath;
	}
	
	//跟我读的提示 节点
	public final static int level09_readafterme[]={
		141,147,153,159,165,171,177,183
	};
	//点点这里
	public final static int level09_clickhere[]={
		5,49,78
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level09_music_position[][] = {
		//sayit word
		{225,14},
		{226,15},
		{227,16},
		{228,17},
		{229,10},
		{230,11},
		{231,12},
		{232,13},
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level09_click_music_position[][] = {
		//冰块点击
		{5,4},
		{9,4},
		{13,5},
		{17,5},
		{21,4},
		{25,4},
		{29,5},
		{33,5},
		{37,4},
		{41,4},
		
		//fvpd
		{78,6},
		{222,7},
		{223,8},
		{224,9},
		
		//fan
		{82,10},
		{86,10},
		{90,10},
		
		//van
		{97,11},
		{101,11},
		{105,11},
		
		//pan
		{112,12},
		{116,12},
		{120,12},
		
		//man
		{127,13},
		{131,13},
		{135,13},			
		
	};
	
	
	
	
	/*-------------------------10   level   声音------------------------------*/
	//最后一个循环开启背景音
	public static int lastIndex10 = 162;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int jingyin_10_Index1 = 135;
	public static int jingyin_10_Index2 = 158;
	/*-----currentindex 和music position的对应关系------*/
	public static String play10Start(int currentIndex){
		String startpath="";
		if(currentIndex == 3){
			startpath = MediaCommon.getLevel10Mp3(1);
		}
		return startpath;
	}
	
	//跟我读的提示 节点
	public final static int level10_readafterme[]={
		134,141,148,155
	};
	//点点这里
	public final static int level10_clickhere[]={
		5,18,34,40,130
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level10_music_position[][] = {
		//sayit word
		{1,3},
		
		{135,4},
		{142,6},
		{149,8},
		{156,10},
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level10_click_music_position[][] = {
		//冰块点击
		{5,3}, //e
		
		{18,3},
		{22,3},
		{26,3},
		{30,3},
		
		//egg
		{40,4},
		{44,5},
		{48,5},
		{52,5},
		{56,5},
		
		//elephant
		{64,6},
		{68,7},
		{72,7},
		{76,7},
		{80,7},
		
		//elk
		{88,8},
		{92,9},
		{96,9},
		{100,9},
		{104,9},
		
		//elevator
		{114,10},
		{118,11},
		{122,11},
		{126,11},				
		
	};
	
	
	
	
	/*-------------------------quiz   03   声音------------------------------*/
	public static int levelQuiz03 = 13;
	//最后一个循环开启背景音
	public static int lastIndexQuiz03 = 297;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int quiz03_jingyinIndex1 = 237;
	public static int quiz03_jingyinIndex2 = 285;
	
	public static int quiz03_guanka_error_max = 9;
	public static int quiz03_sayit_error_max = 12;
	
	//try again
	public static int quiz03_first_tryagain = 225;
	public static int quiz03_second_tryagin = 303;
	
	public static int quiz03_sayit_start = 231;	//出话筒
	
	//闯关第一次提示语
	public static int index_quiz03_chuangguan_tishi = 107;//在这里判断往哪里跳
	public static int index_quiz03_chuangguan_tishi_first = 300;//没有提示就跳到这里
	/*-----currentindex 和music position的对应关系------*/
	public static String playquiz03Start(int currentIndex){
		String startpath="";
		if(currentIndex == 1){
//			startpath = MediaCommon.getQuiz03Mp3(1);
		}
		return startpath;
	}
	
	public static int getSayitIndex(int arry[],int currentIndex){
		int size = arry.length;
		for(int i=0;i<size;i++){
			if(currentIndex == arry[i]){
				return i;
			}
		}
		return -1;
	}
	//sayit 正确的currentIndex
	public final static int quiz03_sayit_good[]={
		239,251,263,275,287
	};
	
	//sayit 错误的currentIndex  这里特殊 实际是 满血复活
	public final static int quiz03_sayit_error[]={
		243,255,267,279,291
	};
	
	
	//读看到的单词 节点
	public final static int quiz03_readlookword[]={
		235,247,259,271,283
	};
	//点点这里
	public final static int quiz03_clickhere[]={
		3,23,43,63,83,103,229
	};
	//答错了
	public final static int quiz03_answererror[]={
		111,119,127,135,143,151,159,167,175,183,191,199,207,215,223
	};
	
	//答对了
	public final static int quiz03_answergood[]={
		113,121,129,137,145,153,161,169,177,185,193,201,209,217,227
	};
	
	//重玩
	public final static int quiz03_replay[]={
		225,303
	};
	
	//bt_after
	public final static int quiz03_bt_after[]={
		103,229
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int quiz03_music_position[][] = {
		//sayit 提示音
		{1,1},//开场白
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int quiz03_click_music_position[][] = {		
		/*-----o-----*/
		{3,2},
		{7,2},
		{11,2},
		{15,2},
		{19,2},
				//d
		{23,3},
		{27,3},
		{31,3},
		{35,3},
		{39,3},
				//e
		{43,4},
		{47,4},
		{51,4},
		{55,4},
		{59,4},
				//ad
		{63,6},
		{67,6},
		{71,6},
		{75,6},
		{79,6},
				//an
		{83,5},
		{87,5},
		{91,5},
		{95,5},
		{99,5},
		
	};
	
	
	/*-----闯关3选1 index节点 和 读音 和 随机字母位置 的对应关系------*/
	public final static int quiz03_cg_related[][] = {
		//数组索引就是位置，另外的参数分别对应 currentIndex、读音1、玩法1的正确位置、读音2、玩法2、读音3、玩法3——读音暂时定位0、1、2、3、4，最后算法里面加偏移量 +2
		{109,0,0,1,1,2,2},
		{117,4,2,3,0,0,1},
		{125,2,1,0,2,4,0},
		{133,3,2,0,0,1,1},//4
		{141,3,0,4,1,0,2},
		{149,4,2,0,1,3,0},
		{157,1,1,2,0,4,2},
		{165,0,1,4,0,2,2},
		{173,2,0,4,1,0,2},
		{181,4,1,2,0,0,2},//10
		{189,0,1,3,0,4,2},
		{197,1,0,2,2,3,1},
		{205,3,1,1,0,3,1},
		{213,1,0,0,1,1,0},
		{221,2,1,3,0,2,1},
	};
	
	/*-----currentindex 和 sayit 字母的对应关系------*/
	public final static String quiz03_sayit_thisChar_position[][] = {		
		//"-AD", "-AN", "-AP", "-AT", "B", "C", "D", "-EN",	"-ET", "F", "G", "H", "-IN", "-IP", "-IT", "J", "K", "L", "M", "N","-OB", "-OP", "-OT", "P", "Q", "R", "S", "/a/", "/e/", "/i/", "/u/","/o/", "T", "-UB", "-UG", "-UN", "-UT", "V", "W", "X", "Y"
		{"237","/o/"},
		{"249","D"},
		{"261","/e/"},
		{"273","-AD"},
		{"285","-AN"}
	};
	
	
	
	
	/*-------------------------03 new   level   声音------------------------------*/
	//最后一个循环开启背景音
	public static int lastIndex03new = 117;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int jingyinIndex103new = 89;
	public static int jingyinIndex203new = 117;
	/*-----currentindex 和music position的对应关系------*/
	public static String play03newStart(int currentIndex){
		String startpath="";
		if(currentIndex == 9){
//			startpath = MediaCommon.getLevel08Mp3(1);
		}
		return startpath;
	}
	
	//跟我读的提示 节点
	public final static int level03new_readafterme[]={
		40,46,52,58
	};
	//点点这里
	public final static int level03new_clickhere[]={
		3,36
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level03new_music_position[][] = {
		//sayit word
		{12,1},
		{18,1},
		{24,1},
		{30,1},
		//语言识别

	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level03new_click_music_position[][] = {	
		{64,2},
		{65,3},
		{66,4},
		{67,5}

		
	};
	
	
	/*-------------------------06 new   level   声音------------------------------*/
	//最后一个循环开启背景音
	public static int lastIndex06new = 90;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int jingyinIndex106new = 61;
	public static int jingyinIndex206new = 90;
	/*-----currentindex 和music position的对应关系------*/
	public static String play06newStart(int currentIndex){
		String startpath="";
		if(currentIndex == 9){
//			startpath = MediaCommon.getLevel08Mp3(1);
		}
		return startpath;
	}
	
	//跟我读的提示 节点
	public final static int level06new_readafterme[]={
		63,69,75,81
	};
	//点点这里
	public final static int level06new_clickhere[]={
		5,27,59,95
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level06new_music_position[][] = {
		//sayit word
		{1,0},
		{3,8},
		{23,1},
		{31,1},
		{43,1},
		{51,1},
		{25,2},
		{33,3},
		{45,4},
		{53,5},
//		//语言识别
		{87,2},
		{88,3},
		{89,4},
		{90,5}
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level06new_click_music_position[][] = {	
		{5,0},        //-
		{9,0},       //话外音2
		{13,0},       
//		{42,9},
//		{57,11},
//		{72,13},

		
	};

	
	
	/*-------------------------quiz   02   声音------------------------------*/
	public static int levelQuiz02 = 8;		//跳转到 quizCardActivity 传递的 level的值
	//最后一个循环开启背景音
	public static int lastIndexQuiz02 = 129;
	public static int endIndexQuiz02 = 130;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int quiz02_jingyinIndex1 = 55;
	public static int quiz02_jingyinIndex2 = 89;
	
	public static int quiz02_guanka_error_max = 4;
	public static int quiz02_sayit_error_max = 6;
	
	//try again
	public static int quiz02_first_tryagain = 131;
	public static int quiz02_second_tryagin = 132;
	
	public static int quiz02_sayit_start = 51;	//出话筒
	
	public static int quiz02_chuangguan_end = 127;	//出话筒
	
	//闯关第一次提示语
	public static int index_quiz02_chuangguan_tishi = 91;//在这里判断往哪里跳
	public static int index_quiz02_chuangguan_tishi_first = 133;//没有提示就跳到这里
	/*-----currentindex 和music position的对应关系------*/
		
	//sayit 正确的currentIndex
	public final static int quiz02_sayit_good[]={
		61,73,85
	};
	
	//sayit 错误的currentIndex  这里特殊 实际是 满血复活
	public final static int quiz02_sayit_error[]={
		59,71,83
	};
	
	
	//读看到的单词 节点
	public final static int quiz02_readlookword[]={
		55,67,79
	};
	//点点这里
	public final static int quiz02_clickhere[]={
		3,19,35
	};
	//闯关-答错了
	public final static int quiz02_answererror[]={
		95,101,107,113,119,125
	};
	
	//闯关-答对了
	public final static int quiz02_answergood[]={
		97,103,109,115,121,127
	};
	
	//重玩
	public final static int quiz02_replay[]={
		131,132
	};
	
	//bt_after
	public final static int quiz02_bt_after[]={
		53
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int quiz02_music_position[][] = {
		//sayit 提示音
		{1,4},//开场白
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int quiz02_click_music_position[][] = {		
		/*-----s-----*/
		{3,1},
		{7,1},
		{11,1},
		{15,1},

		//m
		{19,2},
		{23,2},
		{27,2},
		{31,2},
		
		//t
		{35,3},
		{39,3},
		{43,3},
		{47,3},		
		
	};
	
	
	/*-----闯关3选1 index节点 和 读音 和 随机字母位置 的对应关系------*/
	public final static int quiz02_cg_related[][] = {
		//数组索引就是位置，另外的参数分别对应 currentIndex、读音1、玩法1的正确位置、读音2、玩法2、读音3、玩法3——读音暂时定位0、1、2、3、4，最后算法里面加偏移量 +2
		{93,1,0,1,1,2,2},
		{99,2,1,3,0,0,1},
		{105,3,2,0,2,4,0},
		{111,2,1,0,0,1,1},//4
		{117,3,2,4,1,0,2},
		{123,1,0,0,1,3,0},
		
	};
	
	/*-----currentindex 和 sayit 字母的对应关系------*/
	public final static String quiz02_sayit_thisChar_position[][] = {		
		//"-AD", "-AN", "-AP", "-AT", "B", "C", "D", "-EN",	"-ET", "F", "G", "H", "-IN", "-IP", "-IT", "J", "K", "L", "M", "N","-OB", "-OP", "-OT", "P", "Q", "R", "S", "/a/", "/e/", "/i/", "/u/","/o/", "T", "-UB", "-UG", "-UN", "-UT", "V", "W", "X", "Y"
		{"57","S"},
		{"69","M"},
		{"81","T"}
	};
	
	
	
	
	
	/*-------------------------quiz   01   声音------------------------------*/
	public static int levelQuiz01 = 4;		//跳转到 quizCardActivity 传递的 level的值
	//最后一个循环开启背景音
	public static int lastIndexQuiz01 = 136;
	public static int endIndexQuiz01 = 137;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int quiz01_jingyinIndex1 = 61;
	public static int quiz01_jingyinIndex2 = 79;
	
	public static int quiz01_guanka_error_max = 5;
	public static int quiz01_sayit_error_max = 6;
	
	//try again
	public static int quiz01_first_tryagain = 138;
	public static int quiz01_second_tryagin = 138;
	
	public static int quiz01_sayit_start = 59;	//出话筒
	
	public static int quiz01_chuangguan_end = 135;	//闯关结束
	
	//闯关第一次提示语
	public static int index_quiz01_chuangguan_tishi = 80;//在这里判断往哪里跳
	public static int index_quiz01_chuangguan_tishi_first = 83;//没有提示就跳到这里
	/*-----currentindex 和music position的对应关系------*/
		
	//sayit 正确的currentIndex
	public final static int quiz01_sayit_good[]={
		65,72,79
	};
	
	//sayit 错误的currentIndex  这里特殊 实际是 满血复活
	public final static int quiz01_sayit_error[]={
		64,71,78
	};
	
	
	//读看到的单词 节点
	public final static int quiz01_readlookword[]={
		59,66,73
	};
	//点点这里
	public final static int quiz01_clickhere[]={
		6,24,42
	};
	//闯关-答错了
	public final static int quiz01_answererror[]={
		85,93,101,109,117,125,133
	};
	
	//闯关-答对了
	public final static int quiz01_answergood[]={
		87,95,103,111,119,127,135
	};
	
	//重玩
	public final static int quiz01_replay[]={
		138
	};
	
	//bt_after
	public final static int quiz01_bt_after[]={
		59
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int quiz01_music_position[][] = {
		//sayit 提示音
		{1,1},//开场白
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int quiz01_click_music_position[][] = {		

		/*-----n-----*/
		{6,2},
		{9,2},
		{12,2},
		{15,2},
		{18,2},
		//a
		{24,3},
		{27,3},
		{30,3},
		{33,3},
		{36,3},
		/*-----p-----*/
		{42,4},
		{45,4},
		{48,4},
		{51,4},
		{54,4},
		
	};
	
	
	/*-----闯关3选1 index节点 和 读音 和 随机字母位置 的对应关系------*/
	public final static int quiz01_cg_related[][] = {
		//数组索引就是位置，另外的参数分别对应 currentIndex、读音1、玩法1的正确位置、读音2、玩法2、读音3、玩法3——读音暂时定位0、1、2、3、4，最后算法里面加偏移量 +2
		{83,2,2,3,1,2,2},
		{91,4,0,4,0,3,1},
		{99,3,1,2,2,4,0},
		{107,4,0,4,0,3,1},//4
		{115,2,2,3,1,2,2},
		{123,3,1,3,1,4,0},
		{131,4,0,2,2,4,0},
	};
	
	/*-----currentindex 和 sayit 字母的对应关系------*/
	public final static String quiz01_sayit_thisChar_position[][] = {		
		//"-AD", "-AN", "-AP", "-AT", "B", "C", "D", "-EN",	"-ET", "F", "G", "H", "-IN", "-IP", "-IT", "J", "K", "L", "M", "N","-OB", "-OP", "-OT", "P", "Q", "R", "S", "/a/", "/e/", "/i/", "/u/","/o/", "T", "-UB", "-UG", "-UN", "-UT", "V", "W", "X", "Y"
		{"62","N"},
		{"69","/a/"},
		{"76","P"}
	};
	
	
	
	
	
	
	
	
	
	/*-------------------------1   level 1  声音------------------------------*/
	
	
	//点点这里
	public final static int level1_clickhere[]={
		25,27,29,31,40
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level1_music_position[][] = {
		//sayit word
		{41,1},
		
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level1_click_music_position[][] = {
		/*{25,2},
		{27,2},
		{29,2},*/
	};
	
	/*-------------------------13   level   声音------------------------------*/
	//最后一个循环开启背景音
	public static int lastIndex13 = 130;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int jingyinIndex_13_1 = 99;
	public static int jingyinIndex_13_2 = 127;
	
	//跟我读的提示 节点
	public final static int level13_readafterme[]={
		97,105,113,121
	};
	//点点这里
	public final static int level13_clickhere[]={
		17,93,21,37,57,77	//,53,73,129
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level13_music_position[][] = {
		{3,1},
		//sayit word
		{99,4},
		{107,6},
		{115,8},
		{123,10},
		
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level13_click_music_position[][] = {
		
	};
	
	
	/*-------------------------11   level11   声音------------------------------*/
	//最后一个循环开启背景音
	public static int lastIndex11 = 103;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int jingyinIndex111 = 73;
	public static int jingyinIndex211 = 104;
	/*-----currentindex 和music position的对应关系------*/
	public static String play11Start(int currentIndex){
		String startpath="";
		if(currentIndex == 9){
//			startpath = MediaCommon.getLevel08Mp3(1);
		}
		return startpath;
	}
	
	//跟我读的提示 节点
	public final static int level11_readafterme[]={
		75,81,87,93
	};
	//点点这里
	/*public final static int level11_clickhere[][]={
		//点点这里
		{5,15},
		{9,15},
		{21,15},
		{27,15},
		{71,15},
	};*/
	public final static int level11_clickhere[]={
		5,9,21,27,71,103
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level11_music_position[][] = {
		//sayit word

		{1,6},
		{3,1},
		{25,8},
		{45,10},
		{55,12},
		{65,14},
		{29,8},
		{49,10},
		{59,12},
		{69,14},

		//语言识别
		{99,7},
		{100,9},
		{101,11},
		{102,13}
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level11_click_music_position[][] = {	
		{9,6},        
		{13,6},       
		{17,6},
		{33,7},
		{37,7},
	};

	
	
	/*-------------------------14   level   声音------------------------------*/
	
	//跟我读的提示 节点
	public final static int level14_readafterme[]={
		245,253,261,269
	};
	//跟我读的单词 节点
	public final static int level14_readword[]={
		247,255,263,271
	};
	//点点这里
	public final static int level14_clickhere[]={
		5,9,27,53,129,157,21,241	//,79,105,129,185,213,277
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level14_music_position[][] = {
		{3,1},
		//sayit word
		{247,4},
		{255,6},
		{263,8},
		{271,10},
		
		//画外音
		{77,12},
		{187,12},
		{103,13},
		{215,13},
		
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level14_click_music_position[][] = {
		
	};	

	
	
	/*-------------------------15   level   声音------------------------------*/
	//最后一个循环开启背景音
	public static int lastIndex15 = 240;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int jingyinIndex115 = 179;
	public static int jingyinIndex215 = 240;
	/*-----currentindex 和music position的对应关系------*/
	public static String play15Start(int currentIndex){
		String startpath="";

		return startpath;
	}
	
	//跟我读的提示 节点
	public final static int level15_readafterme[]={
		181,187,193,199,205,211,217,223
	};
	//点点这里
	public final static int level15_clickhere[]={
		5,17,21,113,117,129,177,237
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int level15_music_position[][] = {
		{3,1},
//		{38,10},
//		{53,12},
//		{68,14},
		
//		//语言识别
		{229,2},
		{230,3},
		{231,4},
		{232,5},
		{233,6},
		{234,7},
		{235,8},
		{236,9},
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int level15_click_music_position[][] = {	
		{21,2},        //
		{29,2},       //话外音2
		{37,2},
		
		{45,3},
		{53,3},
		{61,3},
		
		{69,4},
		{77,4},
		{85,4},
		
		{93,5},
		{101,5},
		{109,5},
		

	};

	
	
	
	
	
	
	
	
	/*-------------------------quiz   03   声音------------------------------*/
	public static int levelQuiz04 = 19;
	//最后一个循环开启背景音
	public static int lastIndexQuiz04 = 253;
	//暂停重启的时候，sayit不能唤醒背景音
	public static int quiz04_jingyinIndex1 = 185;
	public static int quiz04_jingyinIndex2 = 253;
	
	public static int quiz04_goto_quizbox = 255;
	
	public static int quiz04_guanka_error_max = 5;
	public static int quiz04_guanka2_error_max = 4;
	public static int quiz04_sayit_error_max = 18;
	
	//try again
	public static int quiz04_first_tryagain = 257;
	public static int quiz04_second_tryagin = 261;
	public static int quiz04_three_tryagin = 265;
	
	public static int quiz04_sayit_start = 181;	//出话筒
	
	//闯关第一次提示语
	public static int index_quiz04_chuangguan_tishi = 63;//在这里判断往哪里跳
	public static int index_quiz04_chuangguan_tishi_first = 269;//没有提示就跳到这里
	/*-----currentindex 和music position的对应关系------*/
	
	//sayit 正确的currentIndex
	public final static int quiz04_sayit_good[]={
		191,201,211,221,231,241,251
	};
	
	//sayit 错误的currentIndex  
	public final static int quiz04_sayit_error[]={
		189,199,209,219,229,239,249
	};
	
	
	//读看到的单词 节点
	public final static int quiz04_readlookword[]={
		185,195,205,215,225,235,245
	};
	//点点这里
	public final static int quiz04_clickhere[]={
		3,59,127,179
	};
	//答错了
	public final static int quiz04_answererror[]={
		67,75,83,91,99,107,115,123,135,143,151,159,167,175
	};
	
	//答对了
	public final static int quiz04_answergood[]={
		69,77,85,93,101,109,117,125,137,145,153,161,169,177
	};
	
	//重玩
	public final static int quiz04_replay[]={
		259,263,267
	};
	
	//bt_after  最后直接跳到成功界面
	public final static int quiz04_bt_after[]={
		59,127,179
	};
	
	/*-----currentindex 和click music的对应关系------*/
	public final static int quiz04_music_position[][] = {
		//sayit 提示音
		{1,1},//开场白
	};
	/*-----currentindex 和click music的对应关系------*/
	public final static int quiz04_click_music_position[][] = {		
		/*-----o-----*/
		{3,6},
		{7,6},
		{11,5},
		{15,5},
		
		{19,2},
		{23,2},
		{27,4},
		{31,4},
		
		{35,3},
		{39,3},
		
		{43,7},
		{47,7},
		{51,8},
		{55,8},
		

	};
	
	
	/*-----闯关3选1 index节点 和 读音 和 随机字母位置 的对应关系------*/
	public final static int quiz04_cg_related[][] = {
		//数组索引就是位置，另外的参数分别对应 currentIndex、读音1、玩法1的正确位置、读音2、玩法2、读音3、玩法3——读音暂时定位0、1、2、3、4，最后算法里面加偏移量 +2
		{65,4,2,1,1,2,2},
		{73,5,0,3,0,0,1},
		{81,6,0,0,2,4,0},
		{89,3,1,0,0,1,1},//4
		{97,4,2,4,1,0,2},
		{105,5,1,0,1,3,0},
		{113,3,2,2,0,4,2},
		{121,6,2,4,0,2,2},
		
		{133,8,2,4,1,0,2},
		{141,7,1,2,0,0,2},//10
		{149,2,0,3,0,4,2},
		{157,7,1,2,2,3,1},
		{165,8,2,1,0,3,1},
		{173,2,2,0,1,1,0}
	};
	
	/*-----currentindex 和 sayit 字母的对应关系------*/
	public final static String quiz04_sayit_thisChar_position[][] = {		
		//"-AD", "-AN", "-AP", "-AT", "B", "C", "D", "-EN",	"-ET", "F", "G", "H", "-IN", "-IP", "-IT", "J", "K", "L", "M", "N","-OB", "-OP", "-OT", "P", "Q", "R", "S", "/a/", "/e/", "/i/", "/u/","/o/", "T", "-UB", "-UG", "-UN", "-UT", "V", "W", "X", "Y"
		{"187","G"},
		{"197","F"},
		{"207","H"},
		{"217","-AT"},
		{"227","-AP"},
		{"237","-EN"},
		{"247","-ET"},
	};	
	
	
	
	
	
	
	
	
}
