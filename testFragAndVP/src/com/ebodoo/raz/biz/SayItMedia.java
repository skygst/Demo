package com.ebodoo.raz.biz;

import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.ConstantRaz4;

public class SayItMedia {

	// 单词的个数
	public static int mediaSize(int level) {
		int size = 4;
		if (level == 9 || level == 12 || level == 15) {
			size = 8;
		}
		return size;
	}

	public static int[] cycleIndexValue = new int[] { 43, 91, 38, 107, 88, 61,
			120, 87, 139, 132, 73, 129, 95, 243, 179 };

	public static int[][] selTimeLevel(int level) {
		int[][] timeLevel = null;
		if(level == 1) {
			timeLevel = Constant.timeLevel01;
		} else if (level == 2) {
			 timeLevel = Constant.timeLevel02;
		} else if (level == 3) {
			 timeLevel = Constant.timeLevel03;
		} else if (level == 4) {
			timeLevel = Constant.timeLevel04;
		} else if (level == 5) {
			timeLevel = Constant.timeLevel05;
		} else if (level == 6) {
			timeLevel = Constant.timeLevel06;
		} else if (level == 7) {
			timeLevel = Constant.timeLevel07;
		} else if (level == 8) {
			timeLevel = Constant.timeLevel08;
		} else if (level == 9) {
			timeLevel = Constant.timeLevel09;
		} else if (level == 10) {
			timeLevel = Constant.timeLevel10;
		} else if (level == 11) {
			timeLevel = ConstantRaz4.timeLevel11;
		} else if (level == 12) {
			timeLevel = ConstantRaz4.timeLevel12;
		} else if (level == 13) {
			timeLevel = ConstantRaz4.timeLevel13;
		} else if (level == 14) {
			timeLevel = ConstantRaz4.timeLevel14;
		} else if (level == 15) {
			timeLevel = ConstantRaz4.timeLevel15;
		}
		return timeLevel;
	}

	public static String commonMediaPath(int level, int index,
			boolean returnVideoPath) {
		String path = "";
		String videoPath = "";
		if(level == 1) {
			path = sayItLevel1MediaPath(index);
			videoPath = Constant.path_raz01 + "level03_yueqiu_manbu.mp4";
		} else if (level == 2) {
			path = sayItLevel2MediaPath(index);
			videoPath = Constant.path_raz01 + "level03_taikong.mp4";
		} else if (level == 3) {
			path = sayItLevel3MediaPath(index);
			videoPath = Constant.path_raz01 + "level03_bengchuang.mp4";
		} else if (level == 4) {
			path = sayItLevel4MediaPath(index);
			videoPath = Constant.path_raz02 + "level04.mp4";
		} else if (level == 5) {
			path = sayItLevel5MediaPath(index);
			videoPath = Constant.path_raz02 + "level05.mp4";
		} else if (level == 6) {
			path = sayItLevel6MediaPath(index);
			videoPath = Constant.path_raz02 + "level06.mp4";
		} else if (level == 7) {
			path = sayItLevel7MediaPath(index);
			videoPath = Constant.path_raz03 + "level07.mp4";
		} else if (level == 8) {
			path = sayItLevel8MediaPath(index);
			videoPath = Constant.path_raz03 + "level08.mp4";
		} else if (level == 9) {
			path = sayItLevel9MediaPath(index);
			videoPath = Constant.path_raz03 + "level09.mp4";
		} else if (level == 10) {
			path = sayItLevel10MediaPath(index);
			videoPath = Constant.path_raz03 + "level10.mp4";
		} else if (level == 11) {
			path = sayItLevel11MediaPath(index);
			videoPath = Constant.path_raz04 + "level11.mp4";
		} else if (level == 12) {
			path = sayItLevel12MediaPath(index);
			videoPath = Constant.path_raz04 + "level12.mp4";
		} else if (level == 13) {
			path = sayItLevel13MediaPath(index);
			videoPath = Constant.path_raz04 + "level13.mp4";
		} else if (level == 14) {
			path = sayItLevel14MediaPath(index);
			videoPath = Constant.path_raz04 + "level14.mp4";
		} else if (level == 15) {
			path = sayItLevel15MediaPath(index);
			videoPath = Constant.path_raz04 + "level15.mp4";
		}
		if (returnVideoPath) {
			return videoPath;
		} else {
			return path;
		}
	}

	public static String sayItLevel1MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // apple
			music_path = Constant.path_raz01 + "flash_n03_nail.mp3";
		} else if (index == 1) { // ant
			music_path = Constant.path_raz01 + "flash_n04_nest.mp3";
		} else if (index == 2) { // angry
			music_path = Constant.path_raz01 + "flash_n05_noodle.mp3";
		} else if (index == 3) { // ax
			music_path = Constant.path_raz01 + "flash_n06_nut.mp3";
		}
		return music_path;
	}
	
	public static String sayItLevel2MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // apple
			music_path = Constant.path_raz01 + "flash_a03_apple.mp3";
		} else if (index == 1) { // ant
			music_path = Constant.path_raz01 + "flash_a05_ant.mp3";
		} else if (index == 2) { // angry
			music_path = Constant.path_raz01 + "flash_a13_angry.mp3";
		} else if (index == 3) { // ax
			music_path = Constant.path_raz01 + "flash_a07_ax.mp3";
		}
		return music_path;
	}

	public static String sayItLevel3MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // pan
			music_path = Constant.path_raz01 + "flash_p13_pan.mp3";
		} else if (index == 1) { // pizza
			music_path = Constant.path_raz01 + "flash_p09_pizza.mp3";
		} else if (index == 2) { // pig
			music_path = Constant.path_raz01 + "flash_p07_pig.mp3";
		} else if (index == 3) { // pen
			music_path = Constant.path_raz01 + "flash_p04_pen.mp3";
		}
		return music_path;
	}

	public static String sayItLevel4MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // monkey
			music_path = Constant.path_raz02 + "level_04_monkey.mp3";
		} else if (index == 1) { // moon
			music_path = Constant.path_raz02 + "level_04_moon.mp3";
		} else if (index == 2) { // mouse
			music_path = Constant.path_raz02 + "level_04_mouse.mp3";
		} else if (index == 3) { // milk
			music_path = Constant.path_raz02 + "level_04_milk.mp3";
		}
		return music_path;
	}

	public static String sayItLevel5MediaPath(int index) {
		String music_path = "";
		if (index == 0) {// 蜘蛛
			music_path = Constant.path_raz02 + "flash_s03_spider.mp3";
		} else if (index == 1) {// 沙发
			music_path = Constant.path_raz02 + "flash_s04_sofa.mp3";
		} else if (index == 2) { // 蛇
			music_path = Constant.path_raz02 + "flash_s05_snake.mp3";
		} else if (index == 3) { // 袜子
			music_path = Constant.path_raz02 + "flash_s06_socks.mp3";
		}
		return music_path;
	}

	public static String sayItLevel6MediaPath(int index) {
		String music_path = "";
		if (index == 0) {// 桌子
			music_path = Constant.path_raz02 + "flash_t03_table.mp3";
		} else if (index == 1) {// 西红柿
			music_path = Constant.path_raz02 + "flash_t04_tomato.mp3";
		} else if (index == 2) { // 老虎
			music_path = Constant.path_raz02 + "flash_t05_tiger.mp3";
		} else if (index == 3) { // 乌龟
			music_path = Constant.path_raz02 + "flash_t06_turtle.mp3";
		}
		return music_path;
	}

	public static String sayItLevel7MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // 单--ox
			music_path = Constant.path_raz03 + "o_ox.mp3";
		} else if (index == 1) { // 单--otter
			music_path = Constant.path_raz03 + "o_otter.mp3";
		} else if (index == 2) { // 单--osterich
			music_path = Constant.path_raz03 + "o_osterich.mp3";
		} else if (index == 3) { // 单--officer
			music_path = Constant.path_raz03 + "o_officer.mp3";
		}
		return music_path;
	}

	public static String sayItLevel8MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // dog
			music_path = Constant.path_raz03 + "flash_d03_dog.mp3";
		} else if (index == 1) { // duck
			music_path = Constant.path_raz03 + "flash_d05_duck.mp3";
		} else if (index == 2) { // deer
			music_path = Constant.path_raz03 + "flash_d07_deer.mp3";
		} else if (index == 3) { // desk
			music_path = Constant.path_raz03 + "flash_d09_desk.mp3";
		}
		return music_path;
	}

	public static String sayItLevel9MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // mad
			music_path = Constant.path_raz03 + "flash_ad_01_mad.mp3";
		} else if (index == 1) { // sad
			music_path = Constant.path_raz03 + "flash_ad_02_sad.mp3";
		} else if (index == 2) { // pad
			music_path = Constant.path_raz03 + "flash_ad_03_pad.mp3";
		} else if (index == 3) { // dad
			music_path = Constant.path_raz03 + "flash_ad_04_dad.mp3";
		} else if (index == 4) { // fan
			music_path = Constant.path_raz03 + "flash_an_01_fan.mp3";
		} else if (index == 5) { // van
			music_path = Constant.path_raz03 + "flash_an_02_van.mp3";
		} else if (index == 6) { // pan
			music_path = Constant.path_raz03 + "flash_an_03_pan.mp3";
		} else if (index == 7) { // man
			music_path = Constant.path_raz03 + "flash_an_04_man.mp3";
		}
		return music_path;
	}

	public static String sayItLevel10MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // egg
			music_path = Constant.path_raz03 + "flash_e03_egg.mp3";
		} else if (index == 1) { // elephant
			music_path = Constant.path_raz03 + "flash_e05_elephant.mp3";
		} else if (index == 2) { // elk
			music_path = Constant.path_raz03 + "flash_e11_elk.mp3";
		} else if (index == 3) { // elevator
			music_path = Constant.path_raz03 + "flash_e07_elevator.mp3";
		}
		return music_path;
	}

	public static String sayItLevel11MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // hat (1)
			music_path = Constant.path_raz04 + "flash_h05_hat.mp3";
		} else if (index == 1) { // hen (2)
			music_path = Constant.path_raz04 + "flash_h03_hen.mp3";
		} else if (index == 2) { // horse (3)
			music_path = Constant.path_raz04 + "flash_h07_horse.mp3";
		} else if (index == 3) { // hammer (4)
			music_path = Constant.path_raz04 + "flash_h13_hammer.mp3";
		}
		return music_path;
	}

	public static String sayItLevel12MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // hop
			music_path = Constant.path_raz04 + "op_hop.mp3";
		} else if (index == 1) { // top
			music_path = Constant.path_raz04 + "op_top.mp3";
		} else if (index == 2) { // cop
			music_path = Constant.path_raz04 + "op_cop.mp3";
		} else if (index == 3) { // pop
			music_path = Constant.path_raz04 + "op_pop.mp3";
		} else if (index == 4) { // hot
			music_path = Constant.path_raz04 + "ot_hot.mp3";
		} else if (index == 5) { // tot
			music_path = Constant.path_raz04 + "ot_tot.mp3";
		} else if (index == 6) { // dot
			music_path = Constant.path_raz04 + "ot_dot.mp3";
		} else if (index == 7) { // pot
			music_path = Constant.path_raz04 + "ot_pot.mp3";
		}
		return music_path;
	}

	public static String sayItLevel13MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // fan
			music_path = Constant.path_raz04 + "flash_f03_fan.mp3";
		} else if (index == 1) { // fish
			music_path = Constant.path_raz04 + "flash_f05_fish.mp3";
		} else if (index == 2) { // fork
			music_path = Constant.path_raz04 + "flash_f09_fork.mp3";
		} else if (index == 3) { // four
			music_path = Constant.path_raz04 + "flash_f11_four.mp3";
		}
		return music_path;
	}

	public static String sayItLevel14MediaPath(int index) {
		String music_path = "";
		if (index == 0) { // gate
			music_path = Constant.path_raz04 + "flash_g03_gate.mp3";
		} else if (index == 1) { // gift
			music_path = Constant.path_raz04 + "flash_g09_gift.mp3";
		} else if (index == 2) { // goose
			music_path = Constant.path_raz04 + "flash_g11_goose.mp3";
		} else if (index == 3) { // garden
			music_path = Constant.path_raz04 + "flash_g13_garden.mp3";
		}
		return music_path;
	}

	public static String sayItLevel15MediaPath(int index) {
		String music_path = "";
		if (index == 0) {
			music_path = Constant.path_raz04 + "flash_et_01_net.mp3";
		} else if (index == 1) {
			music_path = Constant.path_raz04 + "flash_et_02_jet.mp3";
		} else if (index == 2) {
			music_path = Constant.path_raz04 + "flash_et_03_wet.mp3";
		} else if (index == 3) {
			music_path = Constant.path_raz04 + "flash_et_04_vet.mp3";
		} else if (index == 4) {
			music_path = Constant.path_raz04 + "flash_en_05_hen.mp3";
		} else if (index == 5) {
			music_path = Constant.path_raz04 + "flash_en_06_ten.mp3";
		} else if (index == 6) {
			music_path = Constant.path_raz04 + "flash_en_07_pen.mp3";
		} else if (index == 7) {
			music_path = Constant.path_raz04 + "flash_en_08_men.mp3";
		}
		return music_path;
	}

}
