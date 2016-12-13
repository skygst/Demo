package com.ebodoo.raz.utils;

import com.ebodoo.raz.data.FixedGameAaPosition;

public class ConstantGame {
	// 1 2 6 8 9 11
	// 3 4 5 7 10

	// 点击操作，无动画
	public final static int locationPosition[][][] = {
			FixedGameAaPosition.go_1_position,
			FixedGameAaPosition.go_2_position,
			FixedGameAaPosition.big_1_position,
			FixedGameAaPosition.supermarket_1_position,
			FixedGameAaPosition.garden_1_position,
			FixedGameAaPosition.color_1_position,
			FixedGameAaPosition.spring_1_position,
			FixedGameAaPosition.trip_1_position,
			FixedGameAaPosition.in_1_position,
			FixedGameAaPosition.in_2_position,
			FixedGameAaPosition.out_1_position, };

	public static String[][] picClickName = new String[][] {
			{ "go_cow_bg", "go_cow_cat", "go_cow_cow", "go_cow_goat", "1" },
			{ "go_dog_bg", "go_dog_cat", "go_dog_dog", "1" },
			{ "big_bg", "big_small_car", "big_car", "1" },
			{ "supermarket_bg", "supermarket_fanqie", "supermarket_qiezi",
					"supermarket_taozi", "supermarket_baicai", "2" },
			{ "garden_bg", "garden_yumi", "garden_fanqie", "garden_car",
					"garden_lajiao", "garden_xigua", "2" },
			{ "colorful_bg", "colorful_1", "colorful_2", "colorful_3",
					"colorful_4", "2" },
			{ "spring_bg", "spring_1", "spring_2", "0" },
			{ "trip_1_bg", "trip_1_dog", "trip_1_cake", "trip_1_cat", "0" },
			{ "in_bg", "in_aquarium_li", "in_aquarium_wai", "0" },
			{ "in_bg", "in_goat_wai", "in_goat_li", "1" },
			{ "out_bg", "out_1", "out_2", "0" }, };
	
	public static String[] clickMp3Name = new String[] {
		"go_1.mp3",
		"go_2.mp3",
		"big_1.mp3",
		"supermarket_1.mp3",
		"garden_1.mp3",
		"colorful_egg1.mp3", // "colorful_egg2.mp3"
		"spring_1.mp3",
		"trip_3.mp3",
		"in_1.mp3",
		"in_2.mp3",
		"out_1.mp3",
	};

	// 点击操作，有动画
	public final static int locationAnimationPosition[][][] = {
			FixedGameAaPosition.little_1_position,
			FixedGameAaPosition.little_2_position,
			FixedGameAaPosition.summer_1_position,
			FixedGameAaPosition.winter_2_position,
			FixedGameAaPosition.garden_2_position, };

	public static String[][] picAnimationName = new String[][] {
			{ "little_bug_bg", "little_car_", "little_bug_", "little_run_dog_",
					"1", "false" },
			{ "little_dog_bg", "little_dog_", "little_big_dog_", "0", "true" },
			{ "summer_bg", "summer_cold_", "summer_heat_", "1", "false" },
			{ "summer_bg", "winter_cold_", "winter_heat_", "0", "false" },
			{ "garden_2_bg", "garden_melon_", "garden_pea_", "0", "true" }, };

	public static int[][] picNum = new int[][] { { 5, 6, 10 }, { 8, 9 },
			{ 2, 4 }, { 2, 5 }, { 10, 10 }, };
	
	public static String[] animationMp3Name = new String[] {
		"little_1.mp3",
		"little_2.mp3",
		"summer_1.mp3", // "summer_2.mp3"
		"winter_3.mp3", // "winter_4.mp3"
		"garden_2.mp3",
	};
	
	 // 点击操作，无动画， 图片切换
	public final static int locationChangePosition[][][] = {
		FixedGameAaPosition.ocean_1_position,
		FixedGameAaPosition.fall_1_position,
	};
	
	public static String[][] picChangeName = new String[][] {
		{"ocean_bg1", "ocean_green", "ocean_yellow", "ocean_red", "ocean_aquatic_del", "ocean_aquatic_sel", "0"},
		{"fall_bg", "fall_color_1", "fall_color_2", "fall_color_3", "fall_pumpkin_del", "fall_pumpkin_sel", "0"},
		};
	
	public static String[] changeMp3Name = new String[] {
		"ocean_1.mp3", // "ocean_2.mp3"
		"fall_1.mp3",
	};

}
