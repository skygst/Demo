package com.ebodoo.raz.utils;

import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;

public class ConstantLetters {

	public static int[][] lettersNode(int index) {
		int[][] letters = null;
		if (index == 0) { // a
			letters = letters_a_position;
		} else if (index == 1) { // b
			letters = letters_b_position;
		} else if (index == 2) { // c
			letters = letters_c_position;
		} else if (index == 3) { // d
			letters = letters_d_position;
		} else if (index == 4) { // e
			letters = letters_e_position;
		} else if (index == 5) { // f
			letters = letters_f_position;
		} else if (index == 6) { // g
			letters = letters_g_position;
		} else if (index == 7) { // h
			letters = letters_h_position;
		} else if (index == 8) { // i
			letters = letters_i_position;
		} else if (index == 9) { // j
			letters = letters_j_position;
		} else if (index == 10) { // k
			letters = letters_k_position;
		} else if (index == 11) { // l
			letters = letters_l_position;
		} else if (index == 12) { // m
			letters = letters_m_position;
		} else if (index == 13) { // n
			letters = letters_n_position;
		} else if (index == 14) { // o
			letters = letters_o_position;
		} else if (index == 15) { // p
			letters = letters_p_position;
		} else if (index == 16) { // q
			letters = letters_q_position;
		} else if (index == 17) { // r
			letters = letters_r_position;
		} else if (index == 18) { // s
			letters = letters_s_position;
		} else if (index == 19) { // t
			letters = letters_t_position;
		} else if (index == 20) { // u
			letters = letters_u_position;
		} else if (index == 21) { // v
			letters = letters_v_position;
		} else if (index == 22) { // w
			letters = letters_w_position;
		} else if (index == 23) { // x
			letters = letters_x_position;
		} else if (index == 24) { // y
			letters = letters_y_position;
		} else if (index == 25) { // z
			letters = letters_z_position;
		}
		return letters;
	}

	public final static int letters_a_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 958, 0, 1, 0 },// 停桢1s
			{ 1000, 3042, 1, 3, 0 },// 剧情
			{ 3083, 4083, 2, 3, 0 },// 停桢1s
			{ 4125, 6125, 3, 5, 1 },// 循环*
			{ 6167, 7167, 4, 5, 0 },// 停桢1s
			{ 7208, 11583, 5, 7, 0 },// 剧情
			{ 11625, 12625, 6, 7, 0 },// 停桢1s
			{ 12667, 14667, 7, 9, 1 },// 循环*
			{ 14708, 15708, 8, 9, 0 },// 停桢1s
			{ 15750, 20833, 9, 11, 0 },// 剧情
			{ 20875, 21875, 10, 11, 0 },// 停桢1s
			{ 21917, 23917, 11, 13, 1 },// 循环*
			{ 23958, 24958, 12, 13, 0 },// 停桢1s
			{ 25000, 36458, 13, 14, 0 },// 剧情
			{ 36500, 37500, 14, 14, 0 },// 停桢1s
	};
	public final static int letters_b_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 6208, 1, 3, 0 },// 剧情
			{ 6250, 7250, 2, 3, 0 },// 停桢1s
			{ 7292, 9792, 3, 5, 1 },// 循环*
			{ 9833, 10833, 4, 5, 0 },// 停桢1s
			{ 10875, 14583, 5, 7, 0 },// 剧情
			{ 14625, 15625, 6, 7, 0 },// 停桢1s
			{ 15667, 18167, 7, 9, 1 },// 循环*
			{ 18208, 19208, 8, 9, 0 },// 停桢1s
			{ 19250, 23750, 9, 11, 0 },// 剧情
			{ 23792, 24792, 10, 11, 0 },// 停桢1s
			{ 24833, 27333, 11, 13, 1 },// 循环*
			{ 27375, 28375, 12, 13, 0 },// 停桢1s
			{ 28417, 40333, 13, 14, 0 },// 剧情
			{ 40375, 41375, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_c_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 4667, 1, 3, 0 },// 剧情
			{ 4708, 5667, 2, 3, 0 },// 停桢1s
			{ 5708, 7667, 3, 5, 1 },// 循环*
			{ 7708, 8667, 4, 5, 0 },// 停桢1s
			{ 8708, 13958, 5, 7, 0 },// 剧情
			{ 14000, 14958, 6, 7, 0 },// 停桢1s
			{ 15000, 16958, 7, 9, 1 },// 循环*
			{ 17000, 17958, 8, 9, 0 },// 停桢1s
			{ 18000, 23208, 9, 11, 0 },// 剧情
			{ 23250, 24208, 10, 11, 0 },// 停桢1s
			{ 24250, 26208, 11, 13, 1 },// 循环*
			{ 26250, 27292, 12, 13, 0 },// 停桢1s
			{ 27333, 37083, 13, 14, 0 },// 剧情
			{ 37125, 38083, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_d_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 3875, 1, 3, 0 },// 剧情
			{ 3917, 4917, 2, 3, 0 },// 停桢1s
			{ 4958, 6917, 3, 5, 1 },// 循环*
			{ 6958, 7958, 4, 5, 0 },// 停桢1s
			{ 8000, 13000, 5, 7, 0 },// 剧情
			{ 13042, 14042, 6, 7, 0 },// 停桢1s
			{ 14083, 16042, 7, 9, 1 },// 循环*
			{ 16083, 17083, 8, 9, 0 },// 停桢1s
			{ 17125, 19083, 9, 11, 0 },// 剧情
			{ 19125, 20125, 10, 11, 0 },// 停桢1s
			{ 20167, 22125, 11, 13, 1 },// 循环*
			{ 22167, 23167, 12, 13, 0 },// 停桢1s
			{ 23208, 37125, 13, 14, 0 },// 剧情
			{ 37167, 38125, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_e_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 2958, 1, 3, 0 },// 剧情
			{ 3000, 3958, 2, 3, 0 },// 停桢1s
			{ 4000, 5958, 3, 5, 1 },// 循环*
			{ 6000, 6958, 4, 5, 0 },// 停桢1s
			{ 7000, 11833, 5, 7, 0 },// 剧情
			{ 11875, 12833, 6, 7, 0 },// 停桢1s
			{ 12875, 14833, 7, 9, 1 },// 循环*
			{ 14875, 15833, 8, 9, 0 },// 停桢1s
			{ 15875, 22958, 9, 11, 0 },// 剧情
			{ 23000, 23958, 10, 11, 0 },// 停桢1s
			{ 24000, 25958, 11, 13, 1 },// 循环*
			{ 26000, 26958, 12, 13, 0 },// 停桢1s
			{ 27000, 40833, 13, 14, 0 },// 剧情
			{ 40875, 41792, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_f_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 4292, 1, 3, 0 },// 剧情
			{ 4333, 5333, 2, 3, 0 },// 停桢1s
			{ 5375, 7042, 3, 5, 1 },// 循环*
			{ 7083, 8083, 4, 5, 0 },// 停桢1s
			{ 8125, 13750, 5, 7, 0 },// 剧情
			{ 13792, 14792, 6, 7, 0 },// 停桢1s
			{ 14833, 16500, 7, 9, 1 },// 循环*
			{ 16542, 17542, 8, 9, 0 },// 停桢1s
			{ 17583, 23167, 9, 11, 0 },// 剧情
			{ 23208, 24208, 10, 11, 0 },// 停桢1s
			{ 24250, 25917, 11, 13, 1 },// 循环*
			{ 25958, 26958, 12, 13, 0 },// 停桢1s
			{ 27000, 37000, 13, 14, 0 },// 剧情
			{ 37042, 38000, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_g_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 2542, 1, 3, 0 },// 剧情
			{ 2583, 3542, 2, 3, 0 },// 停桢1s
			{ 3583, 5542, 3, 5, 1 },// 循环*
			{ 5583, 6542, 4, 5, 0 },// 停桢1s
			{ 6583, 12625, 5, 7, 0 },// 剧情
			{ 12667, 13625, 6, 7, 0 },// 停桢1s
			{ 13667, 15625, 7, 9, 1 },// 循环*
			{ 15667, 16625, 8, 9, 0 },// 停桢1s
			{ 16667, 20875, 9, 11, 0 },// 剧情
			{ 20917, 21875, 10, 11, 0 },// 停桢1s
			{ 21917, 23875, 11, 13, 1 },// 循环*
			{ 23917, 24875, 12, 13, 0 },// 停桢1s
			{ 24917, 38667, 13, 14, 0 },// 剧情
			{ 38708, 39667, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_h_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 4583, 1, 3, 0 },// 剧情
			{ 4625, 5583, 2, 3, 0 },// 停桢1s
			{ 5625, 7583, 3, 5, 1 },// 循环*
			{ 7625, 8583, 4, 5, 0 },// 停桢1s
			{ 8625, 17208, 5, 7, 0 },// 剧情
			{ 17250, 18208, 6, 7, 0 },// 停桢1s
			{ 18250, 20208, 7, 9, 1 },// 循环*
			{ 20250, 21208, 8, 9, 0 },// 停桢1s
			{ 21250, 29250, 9, 11, 0 },// 剧情
			{ 29292, 30250, 10, 11, 0 },// 停桢1s
			{ 30292, 32250, 11, 13, 1 },// 循环*
			{ 32292, 33250, 12, 13, 0 },// 停桢1s
			{ 33292, 45875, 13, 14, 0 },// 剧情
			{ 45917, 46917, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_i_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 3208, 1, 3, 0 },// 剧情
			{ 3250, 4208, 2, 3, 0 },// 停桢1s
			{ 4250, 6208, 3, 5, 1 },// 循环*
			{ 6250, 7208, 4, 5, 0 },// 停桢1s
			{ 7250, 12250, 5, 7, 0 },// 剧情
			{ 12292, 13250, 6, 7, 0 },// 停桢1s
			{ 13292, 15250, 7, 9, 1 },// 循环*
			{ 15292, 16250, 8, 9, 0 },// 停桢1s
			{ 16292, 18250, 9, 11, 0 },// 剧情
			{ 18292, 19250, 10, 11, 0 },// 停桢1s
			{ 19292, 21250, 11, 13, 1 },// 循环*
			{ 21292, 22250, 12, 13, 0 },// 停桢1s
			{ 22292, 34000, 13, 14, 0 },// 剧情
			{ 34042, 35000, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_j_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 5000, 1, 3, 0 },// 剧情
			{ 5042, 6000, 2, 3, 0 },// 停桢1s
			{ 6042, 7958, 3, 5, 1 },// 循环*
			{ 8000, 8958, 4, 5, 0 },// 停桢1s
			{ 9000, 14958, 5, 7, 0 },// 剧情
			{ 15000, 15958, 6, 7, 0 },// 停桢1s
			{ 16000, 17958, 7, 9, 1 },// 循环*
			{ 18000, 18958, 8, 9, 0 },// 停桢1s
			{ 19000, 22958, 9, 11, 0 },// 剧情
			{ 23000, 23958, 10, 11, 0 },// 停桢1s
			{ 24000, 25958, 11, 13, 1 },// 循环*
			{ 26000, 26958, 12, 13, 0 },// 停桢1s
			{ 27000, 37875, 13, 14, 0 },// 剧情
			{ 37917, 38875, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_k_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 6667, 1, 3, 0 },// 剧情
			{ 6708, 7667, 2, 3, 0 },// 停桢1s
			{ 7708, 9708, 3, 5, 1 },// 循环*
			{ 9750, 10708, 4, 5, 0 },// 停桢1s
			{ 10750, 19167, 5, 7, 0 },// 剧情
			{ 19208, 20167, 6, 7, 0 },// 停桢1s
			{ 20208, 22208, 7, 9, 1 },// 循环*
			{ 22250, 23208, 8, 9, 0 },// 停桢1s
			{ 23250, 27708, 9, 11, 0 },// 剧情
			{ 27750, 28708, 10, 11, 0 },// 停桢1s
			{ 28750, 30750, 11, 13, 1 },// 循环*
			{ 30792, 31750, 12, 13, 0 },// 停桢1s
			{ 31792, 43750, 13, 14, 0 },// 剧情
			{ 43792, 44750, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_l_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 3708, 1, 3, 0 },// 剧情
			{ 3750, 4708, 2, 3, 0 },// 停桢1s
			{ 4750, 6708, 3, 5, 1 },// 循环*
			{ 6750, 7667, 4, 5, 0 },// 停桢1s
			{ 7708, 14917, 5, 7, 0 },// 剧情
			{ 14958, 15917, 6, 7, 0 },// 停桢1s
			{ 15958, 17917, 7, 9, 1 },// 循环*
			{ 17958, 18875, 8, 9, 0 },// 停桢1s
			{ 18917, 23375, 9, 11, 0 },// 剧情
			{ 23417, 24375, 10, 11, 0 },// 停桢1s
			{ 24417, 26375, 11, 13, 1 },// 循环*
			{ 26417, 27375, 12, 13, 0 },// 停桢1s
			{ 27417, 35583, 13, 14, 0 },// 剧情
			{ 35625, 36583, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_m_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 2875, 1, 3, 0 },// 剧情
			{ 2917, 3875, 2, 3, 0 },// 停桢1s
			{ 3917, 5875, 3, 5, 1 },// 循环*
			{ 5917, 6875, 4, 5, 0 },// 停桢1s
			{ 6917, 12083, 5, 7, 0 },// 剧情
			{ 12125, 13083, 6, 7, 0 },// 停桢1s
			{ 13125, 15083, 7, 9, 1 },// 循环*
			{ 15125, 16083, 8, 9, 0 },// 停桢1s
			{ 16125, 21125, 9, 11, 0 },// 剧情
			{ 21167, 22125, 10, 11, 0 },// 停桢1s
			{ 22167, 24125, 11, 13, 1 },// 循环*
			{ 24167, 25125, 12, 13, 0 },// 停桢1s
			{ 25167, 38625, 13, 14, 0 },// 剧情
			{ 38667, 39625, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_n_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 5167, 1, 3, 0 },// 剧情
			{ 5208, 6167, 2, 3, 0 },// 停桢1s
			{ 6208, 8167, 3, 5, 1 },// 循环*
			{ 8208, 9167, 4, 5, 0 },// 停桢1s
			{ 9208, 15250, 5, 7, 0 },// 剧情
			{ 15292, 16250, 6, 7, 0 },// 停桢1s
			{ 16292, 18250, 7, 9, 1 },// 循环*
			{ 18292, 19250, 8, 9, 0 },// 停桢1s
			{ 19292, 24500, 9, 11, 0 },// 剧情
			{ 24542, 25500, 10, 11, 0 },// 停桢1s
			{ 25542, 27500, 11, 13, 1 },// 循环*
			{ 27542, 28500, 12, 13, 0 },// 停桢1s
			{ 28542, 39042, 13, 14, 0 },// 剧情
			{ 39083, 40042, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_o_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 3167, 1, 3, 0 },// 剧情
			{ 3208, 4167, 2, 3, 0 },// 停桢1s
			{ 4208, 6208, 3, 5, 1 },// 循环*
			{ 6250, 7208, 4, 5, 0 },// 停桢1s
			{ 7250, 14875, 5, 7, 0 },// 剧情
			{ 14917, 15875, 6, 7, 0 },// 停桢1s
			{ 15917, 17917, 7, 9, 1 },// 循环*
			{ 17958, 18917, 8, 9, 0 },// 停桢1s
			{ 18958, 23625, 9, 11, 0 },// 剧情
			{ 23667, 24625, 10, 11, 0 },// 停桢1s
			{ 24667, 26667, 11, 13, 1 },// 循环*
			{ 26708, 27667, 12, 13, 0 },// 停桢1s
			{ 27708, 39875, 13, 14, 0 },// 剧情
			{ 39917, 40875, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_p_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 5042, 1, 3, 0 },// 剧情
			{ 5083, 6042, 2, 3, 0 },// 停桢1s
			{ 6083, 8083, 3, 5, 1 },// 循环*
			{ 8125, 9083, 4, 5, 0 },// 停桢1s
			{ 9125, 13667, 5, 7, 0 },// 剧情
			{ 13708, 14667, 6, 7, 0 },// 停桢1s
			{ 14708, 16667, 7, 9, 1 },// 循环*
			{ 16708, 17667, 8, 9, 0 },// 停桢1s
			{ 17708, 19750, 9, 11, 0 },// 剧情
			{ 19792, 20750, 10, 11, 0 },// 停桢1s
			{ 20792, 22750, 11, 13, 1 },// 循环*
			{ 22792, 23750, 12, 13, 0 },// 停桢1s
			{ 23792, 34917, 13, 14, 0 },// 剧情
			{ 34958, 35917, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_q_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 3750, 1, 3, 0 },// 剧情
			{ 3792, 4750, 2, 3, 0 },// 停桢1s
			{ 4792, 7417, 3, 5, 1 },// 循环*
			{ 7458, 8417, 4, 5, 0 },// 停桢1s
			{ 8458, 12417, 5, 7, 0 },// 剧情
			{ 12458, 13417, 6, 7, 0 },// 停桢1s
			{ 13458, 16083, 7, 9, 1 },// 循环*
			{ 16125, 17083, 8, 9, 0 },// 停桢1s
			{ 17125, 21083, 9, 11, 0 },// 剧情
			{ 21125, 22083, 10, 11, 0 },// 停桢1s
			{ 22125, 24750, 11, 13, 1 },// 循环*
			{ 24792, 25750, 12, 13, 0 },// 停桢1s
			{ 25792, 37125, 13, 14, 0 },// 剧情
			{ 37167, 38125, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_r_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 2875, 1, 3, 0 },// 剧情
			{ 2917, 3875, 2, 3, 0 },// 停桢1s
			{ 3917, 5875, 3, 5, 1 },// 循环*
			{ 5917, 6875, 4, 5, 0 },// 停桢1s
			{ 6917, 13125, 5, 7, 0 },// 剧情
			{ 13167, 14125, 6, 7, 0 },// 停桢1s
			{ 14167, 16125, 7, 9, 1 },// 循环*
			{ 16167, 17083, 8, 9, 0 },// 停桢1s
			{ 17125, 23667, 9, 11, 0 },// 剧情
			{ 23708, 24583, 10, 11, 0 },// 停桢1s
			{ 24625, 26583, 11, 13, 1 },// 循环*
			{ 26625, 27542, 12, 13, 0 },// 停桢1s
			{ 27583, 40125, 13, 14, 0 },// 剧情
			{ 40167, 41125, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_s_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 4458, 1, 3, 0 },// 剧情
			{ 4500, 5458, 2, 3, 0 },// 停桢1s
			{ 5500, 7458, 3, 5, 1 },// 循环*
			{ 7500, 8458, 4, 5, 0 },// 停桢1s
			{ 8500, 13500, 5, 7, 0 },// 剧情
			{ 13542, 14500, 6, 7, 0 },// 停桢1s
			{ 14542, 16500, 7, 9, 1 },// 循环*
			{ 16542, 17500, 8, 9, 0 },// 停桢1s
			{ 17542, 20375, 9, 11, 0 },// 剧情
			{ 20417, 21375, 10, 11, 0 },// 停桢1s
			{ 21417, 23375, 11, 13, 1 },// 循环*
			{ 23417, 24375, 12, 13, 0 },// 停桢1s
			{ 24417, 33500, 13, 14, 0 },// 剧情
			{ 33542, 34500, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_t_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 2792, 1, 3, 0 },// 剧情
			{ 2833, 3792, 2, 3, 0 },// 停桢1s
			{ 3833, 5792, 3, 5, 1 },// 循环*
			{ 5833, 6792, 4, 5, 0 },// 停桢1s
			{ 6833, 12042, 5, 7, 0 },// 剧情
			{ 12083, 13042, 6, 7, 0 },// 停桢1s
			{ 13083, 15042, 7, 9, 1 },// 循环*
			{ 15083, 16042, 8, 9, 0 },// 停桢1s
			{ 16083, 21542, 9, 11, 0 },// 剧情
			{ 21583, 22542, 10, 11, 0 },// 停桢1s
			{ 22583, 24542, 11, 13, 1 },// 循环*
			{ 24583, 25542, 12, 13, 0 },// 停桢1s
			{ 25583, 35125, 13, 14, 0 },// 剧情
			{ 35167, 36125, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_u_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 6875, 1, 3, 0 },// 剧情
			{ 6917, 7875, 2, 3, 0 },// 停桢1s
			{ 7917, 9917, 3, 5, 1 },// 循环*
			{ 9958, 10917, 4, 5, 0 },// 停桢1s
			{ 10958, 15292, 5, 7, 0 },// 剧情
			{ 15333, 16292, 6, 7, 0 },// 停桢1s
			{ 16333, 18333, 7, 9, 1 },// 循环*
			{ 18375, 19333, 8, 9, 0 },// 停桢1s
			{ 19375, 23333, 9, 11, 0 },// 剧情
			{ 23375, 24333, 10, 11, 0 },// 停桢1s
			{ 24375, 26375, 11, 13, 1 },// 循环*
			{ 26417, 27375, 12, 13, 0 },// 停桢1s
			{ 27417, 38667, 13, 14, 0 },// 剧情
			{ 38708, 39667, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_v_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 2917, 1, 3, 0 },// 剧情
			{ 2958, 3917, 2, 3, 0 },// 停桢1s
			{ 3958, 5917, 3, 5, 1 },// 循环*
			{ 5958, 6917, 4, 5, 0 },// 停桢1s
			{ 6958, 11333, 5, 7, 0 },// 剧情
			{ 11375, 12333, 6, 7, 0 },// 停桢1s
			{ 12375, 14333, 7, 9, 1 },// 循环*
			{ 14375, 15333, 8, 9, 0 },// 停桢1s
			{ 15375, 19583, 9, 11, 0 },// 剧情
			{ 19625, 20583, 10, 11, 0 },// 停桢1s
			{ 20625, 22583, 11, 13, 1 },// 循环*
			{ 22625, 23583, 12, 13, 0 },// 停桢1s
			{ 23625, 32333, 13, 14, 0 },// 剧情
			{ 32375, 33333, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_w_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 3000, 1, 3, 0 },// 剧情
			{ 3042, 4000, 2, 3, 0 },// 停桢1s
			{ 4042, 6000, 3, 5, 1 },// 循环*
			{ 6042, 7000, 4, 5, 0 },// 停桢1s
			{ 7042, 11000, 5, 7, 0 },// 剧情
			{ 11042, 12000, 6, 7, 0 },// 停桢1s
			{ 12042, 14000, 7, 9, 1 },// 循环*
			{ 14042, 15000, 8, 9, 0 },// 停桢1s
			{ 15042, 19000, 9, 11, 0 },// 剧情
			{ 19042, 20000, 10, 11, 0 },// 停桢1s
			{ 20042, 22000, 11, 13, 1 },// 循环*
			{ 22042, 23000, 12, 13, 0 },// 停桢1s
			{ 23042, 32792, 13, 14, 0 },// 剧情
			{ 32833, 33792, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_x_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 2708, 1, 3, 0 },// 剧情
			{ 2750, 3708, 2, 3, 0 },// 停桢1s
			{ 3750, 5708, 3, 5, 1 },// 循环*
			{ 5750, 6708, 4, 5, 0 },// 停桢1s
			{ 6750, 11042, 5, 7, 0 },// 剧情
			{ 11083, 12042, 6, 7, 0 },// 停桢1s
			{ 12083, 14042, 7, 9, 1 },// 循环*
			{ 14083, 15042, 8, 9, 0 },// 停桢1s
			{ 15083, 19958, 9, 11, 0 },// 剧情
			{ 20000, 20958, 10, 11, 0 },// 停桢1s
			{ 21000, 22958, 11, 13, 1 },// 循环*
			{ 23000, 23958, 12, 13, 0 },// 停桢1s
			{ 24000, 35375, 13, 14, 0 },// 剧情
			{ 35417, 36375, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_y_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 3458, 1, 3, 0 },// 剧情
			{ 3500, 4458, 2, 3, 0 },// 停桢1s
			{ 4500, 6458, 3, 5, 1 },// 循环*
			{ 6500, 7458, 4, 5, 0 },// 停桢1s
			{ 7500, 12500, 5, 7, 0 },// 剧情
			{ 12542, 13500, 6, 7, 0 },// 停桢1s
			{ 13542, 15500, 7, 9, 1 },// 循环*
			{ 15542, 16500, 8, 9, 0 },// 停桢1s
			{ 16542, 19292, 9, 11, 0 },// 剧情
			{ 19333, 20292, 10, 11, 0 },// 停桢1s
			{ 20333, 22292, 11, 13, 1 },// 循环*
			{ 22333, 23292, 12, 13, 0 },// 停桢1s
			{ 23333, 35375, 13, 14, 0 },// 剧情
			{ 35417, 36375, 14, 14, 0 },// 停桢1s

	};
	public final static int letters_z_position[][] = { // 位置 w、h、x、y 1280X720
	{ 42, 1000, 0, 1, 0 },// 停桢1s
			{ 1042, 3583, 1, 3, 0 },// 剧情
			{ 3625, 4583, 2, 3, 0 },// 停桢1s
			{ 4625, 6542, 3, 5, 1 },// 循环*
			{ 6583, 7542, 4, 5, 0 },// 停桢1s
			{ 7583, 12333, 5, 7, 0 },// 剧情
			{ 12375, 13333, 6, 7, 0 },// 停桢1s
			{ 13375, 15292, 7, 9, 1 },// 循环*
			{ 15333, 16292, 8, 9, 0 },// 停桢1s
			{ 16333, 20375, 9, 11, 0 },// 剧情
			{ 20417, 21375, 10, 11, 0 },// 停桢1s
			{ 21417, 23333, 11, 13, 1 },// 循环*
			{ 23375, 24333, 12, 13, 0 },// 停桢1s
			{ 24375, 34417, 13, 14, 0 },// 剧情
			{ 34458, 35417, 14, 14, 0 },// 停桢1s
	};

	public final static int letters_shou_position[][] = { // 位置 w、h、x、y 1280X720
	{ 487, 412, 383, 149 }, { 387, 289, 451, 266 }, { 661, 389, 311, 164 } };

	public final static int letters_b_shou_position[][] = { // 位置 w、h、x、y
															// 1280X720
	{ 341, 336, 386, 155 }, { 300, 305, 659, 180 }, { 437, 258, 337, 326 } };

	public final static int letters_m_shou_position[][] = { // 位置 w、h、x、y
															// 1280X720
	{ 393, 351, 223, 69 }, { 731, 193, 265, 287 }, { 466, 374, 376, 76 } };

	public final static int letters_x_shou_position[][] = { // 位置 w、h、x、y
															// 1280X720
	{ 345, 335, 470, 255 }, { 233, 215, 569, 424 }, { 386, 313, 436, 282 } };

	public static String[] lettersPath = new String[] { "letters_a.mp4",
			"letters_b.mp4", "letters_c.mp4", "letters_d.mp4", "letters_e.mp4",
			"letters_f.mp4", "letters_g.mp4", "letters_h.mp4", "letters_i.mp4",
			"letters_j.mp4", "letters_k.mp4", "letters_l.mp4", "letters_m.mp4",
			"letters_n.mp4", "letters_o.mp4", "letters_p.mp4", "letters_q.mp4",
			"letters_r.mp4", "letters_s.mp4", "letters_t.mp4", "letters_u.mp4",
			"letters_v.mp4", "letters_w.mp4", "letters_x.mp4", "letters_y.mp4",
			"letters_z.mp4" };

	public static String[] physicalBg = new String[] { "a_bg", "b_bg", "c_bg",
			"d_bg", "e_bg", "f_bg", "g_bg", "h_bg", "i_bg", "j_bg", "k_bg",
			"l_bg", "m_bg", "n_bg", "o_bg", "p_bg", "q_bg", "r_bg", "s_bg",
			"t_bg", "u_bg", "v_bg", "w_bg", "x_bg", "y_bg", "z_bg" };

	public static String[] wordBg = new String[] { "ant_bg", "ball_bg",
			"cat_bg", "dog_bg", "egg_bg", "fish_bg", "gate_bg", "hill_bg",
			"igloo_bg", "jet_bg", "kite_bg", "leaf_bg", "milk_bg", "nest_bg",
			"orange_bg", "puppy_bg", "quiet_bg", "rain_bg", "sea_bg", "ten_bg",
			"umbrella_bg", "van_bg", "water_bg", "ray_bg", "yarn_bg",
			"zipper_bg" };

	public static String path_letters = Environment
			.getExternalStorageDirectory() + "/raz_english/letters/";

	public static String path_letters_images = path_letters + "images/";

	public static String[] effectsId = new String[] { "effects_1", "effects_2",
			"effects_3", "effects_4", "effects_5", "effects_6", "effects_7",
			"effects_8", "effects_9", "effects_10", "effects_11", "effects_12" };

	public static String[][] allPhysical = new String[][] {
			{ "physical_big_a", "physical_a", "physical_ant", "physical_apple" }, // a
			{ "physical_big_b", "physical_b", "physical_ball", "physical_boy" }, // b
			{ "physical_big_c", "physical_c", "physical_candy", "physical_cat" }, // c
			{ "physical_big_d", "physical_d", "physical_dog", "physical_doll" }, // d
			{ "physical_big_e", "physical_e", "physical_elephant",
					"physical_egg" }, // e
			{ "physical_big_f", "physical_f", "physical_fish", "physical_fox" }, // f
			{ "physical_big_g", "physical_g", "physical_gate", "physical_goat" }, // g

			{ "physical_big_h", "physical_h", "physical_hill", "physical_hippo" }, // h
			{ "physical_big_i", "physical_i", "physical_igloo",
					"physical_incect" }, // i
			{ "physical_big_j", "physical_j", "physical_jet", "physical_jacket" }, // j
			{ "physical_big_k", "physical_k", "physical_kite",
					"physical_kitten" }, // k
			{ "physical_big_l", "physical_l", "physical_leaf", "physical_lion" }, // l
			{ "physical_big_m", "physical_m", "physical_milk",
					"physical_monkey" }, // m
			{ "physical_big_n", "physical_n", "physical_nest", "physical_net" }, // n

			{ "physical_big_o", "physical_o", "physical_orange",
					"physical_octopus" }, // o
			{ "physical_big_p", "physical_p", "physical_puppy", "physical_pig" }, // p
			{ "physical_big_q", "physical_q", "physical_quiet",
					"physical_quilt" }, // q
			{ "physical_big_r", "physical_r", "physical_rain",
					"physical_rainbow" }, // r
			{ "physical_big_s", "physical_s", "physical_sea", "physical_snow" }, // s
			{ "physical_big_t", "physical_t", "physical_ten", "physical_tea" }, // t

			{ "physical_big_u", "physical_u", "physical_umbrella",
					"physical_sun" }, // u
			{ "physical_big_v", "physical_v", "physical_van", "physical_violin" }, // v
			{ "physical_big_w", "physical_w", "physical_water", "physical_walk" }, // w

			{ "physical_big_x", "physical_x", "physical_ray", "physical_box" }, // x

			{ "physical_big_y", "physical_y", "physical_yarn", "physical_yoyo" }, // y
			{ "physical_big_z", "physical_z", "physical_zipper",
					"physical_zebra" }, // z
	};
	public static String[][] allPhysicalShadow = new String[][] {
			{ "physical_shadow_big_a", "physical_shadow_a",
					"physical_shadow_ant", "physical_shadow_apple" }, // a
			{ "physical_shadow_big_b", "physical_shadow_b",
					"physical_shadow_ball", "physical_shadow_boy" }, // b
			{ "physical_shadow_big_c", "physical_shadow_c",
					"physical_shadow_candy", "physical_shadow_cat" }, // c
			{ "physical_shadow_big_d", "physical_shadow_d",
					"physical_shadow_dog", "physical_shadow_doll" }, // d
			{ "physical_shadow_big_e", "physical_shadow_e",
					"physical_shadow_elephant", "physical_shadow_egg" }, // e
			{ "physical_shadow_big_f", "physical_shadow_f",
					"physical_shadow_fish", "physical_shadow_fox" }, // f
			{ "physical_shadow_big_g", "physical_shadow_g",
					"physical_shadow_gate", "physical_shadow_goat" }, // g

			{ "physical_shadow_big_h", "physical_shadow_h",
					"physical_shadow_hill", "physical_shadow_hippo" }, // h
			{ "physical_shadow_big_i", "physical_shadow_i",
					"physical_shadow_igloo", "physical_shadow_incect" }, // i
			{ "physical_shadow_big_j", "physical_shadow_j",
					"physical_shadow_jet", "physical_shadow_jacket" }, // j
			{ "physical_shadow_big_k", "physical_shadow_k",
					"physical_shadow_kite", "physical_shadow_kitten" }, // k
			{ "physical_shadow_big_l", "physical_shadow_l",
					"physical_shadow_leaf", "physical_shadow_lion" }, // l
			{ "physical_shadow_big_m", "physical_shadow_m",
					"physical_shadow_milk", "physical_shadow_monkey" }, // m
			{ "physical_shadow_big_n", "physical_shadow_n",
					"physical_shadow_nest", "physical_shadow_net" }, // n

			{ "physical_shadow_big_o", "physical_shadow_o",
					"physical_shadow_orange", "physical_shadow_octopus" }, // o
			{ "physical_shadow_big_p", "physical_shadow_p",
					"physical_shadow_puppy", "physical_shadow_pig" }, // p
			{ "physical_shadow_big_q", "physical_shadow_q",
					"physical_shadow_quiet", "physical_shadow_quilt" }, // q
			{ "physical_shadow_big_r", "physical_shadow_r",
					"physical_shadow_rain", "physical_shadow_rainbow" }, // r
			{ "physical_shadow_big_s", "physical_shadow_s",
					"physical_shadow_sea", "physical_shadow_snow" }, // s
			{ "physical_shadow_big_t", "physical_shadow_t",
					"physical_shadow_ten", "physical_shadow_tea" }, // t

			{ "physical_shadow_big_u", "physical_shadow_u",
					"physical_shadow_umbrella", "physical_shadow_sun" }, // u
			{ "physical_shadow_big_v", "physical_shadow_v",
					"physical_shadow_van", "physical_shadow_violin" }, // v
			{ "physical_shadow_big_w", "physical_shadow_w",
					"physical_shadow_water", "physical_shadow_walk" }, // w

			{ "physical_shadow_big_x", "physical_shadow_x",
					"physical_shadow_ray", "physical_shadow_box" }, // x

			{ "physical_shadow_big_y", "physical_shadow_y",
					"physical_shadow_yarn", "physical_shadow_yoyo" }, // y
			{ "physical_shadow_big_z", "physical_shadow_z",
					"physical_shadow_zipper", "physical_shadow_zebra" }, // z
	};

	public static String[] firstPicId = new String[] { "ant_0", "ball_1",
			"candymachine_1", "dog_1", "elephant_7", "fish_1", "gate_1",
			"hill_9", "igloo_shaking_1", "jet_9", "kite_1", "leaf_1",
			"readmilk_1", "nest_1", "orange_1", "puppy_1", "quiet_1", "rain_1",
			"sea_1", "ten_1", "umbrella_1", "van_1", "water_1", "x_ray_1",
			"yarn_1", "zipper_1" };

	public static String[] sencondPicId = new String[] { "apple_18", "boy_23",
			"cat_1", "doll_1", "eggbroken_1", "fox_1", "goat_1", "hippo_1",
			"incect_pronunciation_1", "jacket_1", "kitten_1", "lion_1",
			"monkey_eat_1", "net_1", "octopus_1", "pig_1", "quilt_1",
			"rainbow_1", "snow_1", "tea_1", "sun_1", "violin_1", "walk_1",
			"box_1", "yoyo_1", "zebra_1" };

	public static String[] wordPicId = new String[] { "letters_a", "letters_b",
			"letters_c", "letters_d", "letters_e", "letters_f", "letters_g",
			"letters_h", "letters_i", "letters_j", "letters_k", "letters_l",
			"letters_m", "letters_n", "letters_o", "letters_p", "letters_q",
			"letters_r", "letters_s", "letters_t", "letters_u", "letters_v",
			"letters_w", "letters_x", "letters_y", "letters_z" };

	private static String[] currentPic(int[] pos, String[] bmpId) {
		if (pos != null && pos.length > 0) {
			String[] picArray = new String[pos.length];
			for (int i = 0; i < pos.length; i++) {
				picArray[i] = bmpId[pos[i]];
			}
			return picArray;
		} else {
			return null;
		}
	}

	// 得到当前循环播放的Id
	public static String[] bmpData(int index, int pos) {
		String[] bmp = null;
		if (index == 0) { // a
			bmp = currentPic(picAOrder[pos], bmp_a_Id);
		} else if (index == 1) { // b
			bmp = currentPic(picBOrder[pos], bmp_b_Id);
		} else if (index == 2) { // c
			bmp = currentPic(picCOrder[pos], bmp_c_Id);
		} else if (index == 3) { // d
			bmp = currentPic(picDOrder[pos], bmp_d_Id);
		} else if (index == 4) { // e
			bmp = currentPic(picEOrder[pos], bmp_e_Id);
		} else if (index == 5) { // f
			bmp = currentPic(picFOrder[pos], bmp_f_Id);
		} else if (index == 6) { // g
			bmp = currentPic(picGOrder[pos], bmp_g_Id);
		} else if (index == 7) { // h
			bmp = currentPic(picHOrder[pos], bmp_h_Id);
		} else if (index == 8) { // i
			bmp = currentPic(picIOrder[pos], bmp_i_Id);
		} else if (index == 9) { // j
			bmp = currentPic(picJOrder[pos], bmp_j_Id);
		} else if (index == 10) { // k
			bmp = currentPic(picKOrder[pos], bmp_k_Id);
		} else if (index == 11) { // l
			bmp = currentPic(picLOrder[pos], bmp_l_Id);
		} else if (index == 12) { // m
			bmp = currentPic(picMOrder[pos], bmp_m_Id);
		} else if (index == 13) { // n
			bmp = currentPic(picNOrder[pos], bmp_n_Id);
		} else if (index == 14) { // o
			bmp = currentPic(picOOrder[pos], bmp_o_Id);
		} else if (index == 15) { // p
			bmp = currentPic(picPOrder[pos], bmp_p_Id);
		} else if (index == 16) { // q
			bmp = currentPic(picQOrder[pos], bmp_q_Id);
		} else if (index == 17) { // r
			bmp = currentPic(picROrder[pos], bmp_r_Id);
		} else if (index == 18) { // s
			bmp = currentPic(picSOrder[pos], bmp_s_Id);
		} else if (index == 19) { // t
			bmp = currentPic(picTOrder[pos], bmp_t_Id);
		} else if (index == 20) { // u
			bmp = currentPic(picUOrder[pos], bmp_u_Id);
		} else if (index == 21) { // v
			bmp = currentPic(picVOrder[pos], bmp_v_Id);
		} else if (index == 22) { // w
			bmp = currentPic(picWOrder[pos], bmp_w_Id);
		} else if (index == 23) { // x
			bmp = currentPic(picXOrder[pos], bmp_x_Id);
		} else if (index == 24) { // y
			bmp = currentPic(picYOrder[pos], bmp_y_Id);
		} else if (index == 25) { // z
			bmp = currentPic(picZOrder[pos], bmp_z_Id);
		} else {
			bmp = currentPic(picAOrder[pos], bmp_a_Id);
		}
		return bmp;
	}

	// 得到当前单词数组
	public static String[] currentWordData(int index, int num) {
		String[] words = null;
		if (index == 0) { // a
			words = wordAIndex[num];
		} else if (index == 1) { // b
			words = wordBIndex[num];
		} else if (index == 2) { // c
			words = wordCIndex[num];
		} else if (index == 3) { // d
			words = wordDIndex[num];
		} else if (index == 4) { // e
			words = wordEIndex[num];
		} else if (index == 5) { // f
			words = wordFIndex[num];
		} else if (index == 6) { // g
			words = wordGIndex[num];
		} else if (index == 7) { // h
			words = wordHIndex[num];
		} else if (index == 8) { // i
			words = wordIIndex[num];
		} else if (index == 9) { // j
			words = wordJIndex[num];
		} else if (index == 10) { // k
			words = wordKIndex[num];
		} else if (index == 11) { // l
			words = wordLIndex[num];
		} else if (index == 12) { // m
			words = wordMIndex[num];
		} else if (index == 13) { // n
			words = wordNIndex[num];
		} else if (index == 14) { // o
			words = wordOIndex[num];
		} else if (index == 15) { // p
			words = wordPIndex[num];
		} else if (index == 16) { // q
			words = wordQIndex[num];
		} else if (index == 17) { // r
			words = wordRIndex[num];
		} else if (index == 18) { // s
			words = wordSIndex[num];
		} else if (index == 19) { // t
			words = wordTIndex[num];
		} else if (index == 20) { // u
			words = wordUIndex[num];
		} else if (index == 21) { // v
			words = wordVIndex[num];
		} else if (index == 22) { // w
			words = wordWIndex[num];
		} else if (index == 23) { // x
			words = wordXIndex[num];
		} else if (index == 24) { // y
			words = wordYIndex[num];
		} else if (index == 25) { // z
			words = wordZIndex[num];
		}
		return words;
	};

	// 得到当前单词数组定位索引
	public static int[] currentLetterData(int index, int num) {
		int[] letters = null;
		if (index == 0) { // a
			letters = lettersAIndex[num];
		} else if (index == 1) { // b
			letters = lettersBIndex[num];
		} else if (index == 2) { // c
			letters = lettersCIndex[num];
		} else if (index == 3) { // d
			letters = lettersDIndex[num];
		} else if (index == 4) { // e
			letters = lettersEIndex[num];
		} else if (index == 5) { // f
			letters = lettersFIndex[num];
		} else if (index == 6) { // g
			letters = lettersGIndex[num];
		} else if (index == 7) { // h
			letters = lettersHIndex[num];
		} else if (index == 8) { // i
			letters = lettersIIndex[num];
		} else if (index == 9) { // j
			letters = lettersJIndex[num];
		} else if (index == 10) { // k
			letters = lettersKIndex[num];
		} else if (index == 11) { // l
			letters = lettersLIndex[num];
		} else if (index == 12) { // m
			letters = lettersMIndex[num];
		} else if (index == 13) { // n
			letters = lettersNIndex[num];
		} else if (index == 14) { // o
			letters = lettersOIndex[num];
		} else if (index == 15) { // p
			letters = lettersPIndex[num];
		} else if (index == 16) { // q
			letters = lettersQIndex[num];
		} else if (index == 17) { // r
			letters = lettersRIndex[num];
		} else if (index == 18) { // s
			letters = lettersSIndex[num];
		} else if (index == 19) { // t
			letters = lettersTIndex[num];
		} else if (index == 20) { // u
			letters = lettersUIndex[num];
		} else if (index == 21) { // v
			letters = lettersVIndex[num];
		} else if (index == 22) { // w
			letters = lettersWIndex[num];
		} else if (index == 23) { // x
			letters = lettersXIndex[num];
		} else if (index == 24) { // y
			letters = lettersYIndex[num];
		} else if (index == 25) { // z
			letters = lettersZIndex[num];
		}
		return letters;
	};

	public static int[][] picAOrder = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 10, 11 }, { 11, 12, 13, 14, 15, 16, 17 },
			{ 18, 19, 20, 21, 22 }, { 23, 24, 25, 26, 27 },
			{ 27, 28, 29, 30, 31, 32, 33, 34, 35 } };

	public static int[][] picBOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8 },
			{ 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 }, { 22, 23 },
			{ 22, 23 }, { 22, 23 } };

	public static int[][] picCOrder = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 10, 11 }, { 12, 13, 14, 15, 16 },
			{ 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 },
			{ 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17 },
			{ 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 } };

	public static int[][] picDOrder = new int[][] { { 0, 1, 2, 3, 4 },
			{ 5, 6, 7, 8, 9, 10, 11 }, { 11, 12, 13, 14, 15 },
			{ 16, 17, 18, 19, 20 }, { 16, 17, 18, 19, 20 },
			{ 21, 22, 23, 24, 25, 26, 27, 28 } };

	public static int[][] picEOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8 },
			{ 9, 10, 11, 12, 13, 14, 15, 16, 17 },
			{ 9, 10, 11, 12, 13, 14, 15, 16, 17 },
			{ 9, 10, 11, 12, 13, 14, 15, 16, 17 } };

	public static int[][] picFOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 9, 10, 11, 12, 13, 14, 15, 16 },
			{ 9, 10, 11, 12, 13, 14, 15, 16 },
			{ 9, 10, 11, 12, 13, 14, 15, 16 } };

	public static int[][] picGOrder = new int[][] { { 0, 1, 2, 3 },
			{ 3, 2, 1, 0 }, { 0, 1, 2, 3 },
			{ 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
			{ 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
			{ 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 } };

	public static int[][] picHOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20 } };

	public static int[][] picIOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 10, 11, 12, 13, 14, 15, 16 },
			{ 10, 11, 12, 13, 14, 15, 16 }, { 10, 11, 12, 13, 14, 15, 16 } };

	public static int[][] picJOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19 } };

	public static int[][] picKOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
			{ 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
					26 },
			{ 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
					26 },
			{ 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
					26 } };
	public static int[][] picLOrder = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },
			{ 0, 1, 2, 3, 4, 5, 6 }, { 0, 1, 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 10, 11, 12, 13, 14, 15 },
			{ 7, 8, 9, 10, 11, 12, 13, 14, 15 },
			{ 7, 8, 9, 10, 11, 12, 13, 14, 15 } };

	public static int[][] picMOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 },
			{ 23, 24, 25, 26, 27 }, { 28, 29, 30, 31, 32 },
			{ 33, 34, 35, 36, 37 } };
	public static int[][] picNOrder = new int[][] { { 0, 1, 2, 3, 4, 5 },
			{ 0, 1, 2, 3, 4, 5 }, { 0, 1, 2, 3, 4, 5 },
			{ 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
			{ 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
			{ 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 } };

	public static int[][] picOOrder = new int[][] { { 0, 1, 2, 3, 4, 5 },
			{ 6, 7, 8, 9, 10, 11 }, { 12, 13, 14, 15, 16, 17 },
			{ 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 },
			{ 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 },
			{ 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 } };
	public static int[][] picPOrder = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7 },
			{ 7, 6, 5, 4, 3, 2, 1, 0 }, { 0, 1, 2, 3, 4, 5, 6, 7 },
			{ 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 },
			{ 17, 16, 15, 14, 13, 12, 11, 10, 9, 8 },
			{ 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 } };
	public static int[][] picQOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 },
			{ 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 },
			{ 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 },
			{ 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 } };
	public static int[][] picROrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
					27, 28, 29 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
					27, 28, 29 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
					27, 28, 29 } };
	public static int[][] picSOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 } };
	public static int[][] picTOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 },
			{ 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
	public static int[][] picUOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 },
			{ 14, 15, 16, 17, 18, 19, 20 }, { 14, 15, 16, 17, 18, 19, 20 },
			{ 14, 15, 16, 17, 18, 19, 20 } };
	public static int[][] picVOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
					19 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
					19 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
					19 }, { 20, 21, 22, 23, 24, 25, 26 },
			{ 20, 21, 22, 23, 24, 25, 26 }, { 20, 21, 22, 23, 24, 25, 26 } };
	public static int[][] picWOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20 } };

	public static int[][] picXOrder = new int[][] { { 0, 1, 2, 3, 4, 5 },
			{ 0, 1, 2, 3, 4, 5 }, { 0, 1, 2, 3, 4, 5 },
			{ 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 },
			{ 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 },
			{ 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 } };

	public static int[][] picYOrder = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },
			{ 0, 1, 2, 3, 4, 5, 6 }, { 0, 1, 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 },
			{ 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 },
			{ 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 } };
	public static int[][] picZOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 },
			{ 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 } };

	public static String[] bmp_a_Id = new String[] { "ant_0", "ant_1", "ant_2",
			"ant_3", "ant_4", "ant_5", "ant_6", "ant_7", "ant_8", "ant_9",
			"ant_10", "ant_11", "ant_12", "ant_13", "ant_14", "ant_15",
			"ant_16", "ant_17", "apple_18", "apple_19", "apple_20", "apple_21",
			"apple_22", "apple_23", "apple_24", "apple_25", "apple_26",
			"apple_27", "apple_28", "apple_29", "apple_30", "apple_31",
			"apple_32", "apple_33", "apple_34", "apple_35" };

	public static String[] bmp_b_Id = new String[] { "ball_1", "ball_2",
			"ball_3", "ball_4", "ball_5", "ball_6", "ball_7", "ball_8",
			"ball_9", "ball_10", "ball_11", "ball_12", "ball_13", "ball_14",
			"ball_15", "ball_16", "ball_17", "ball_18", "ball_19", "ball_20",
			"ball_21", "ball_22", "boy_23", "boy_24" };

	public static String[] bmp_c_Id = new String[] { "candymachine_1",
			"candymachine_2", "candymachine_3", "candymachine_4",
			"candymachine_5", "candymachine_6", "candymachine_7",
			"candymachine_8", "candymachine_9", "candymachine_10",
			"candymachine_11", "candymachine_12", "candymachine_13",
			"candymachine_14", "candymachine_15", "candymachine_16",
			"candymachine_17", "cat_1", "cat_2", "cat_3", "cat_4", "cat_5",
			"cat_6", "cat_7", "cat_8", "cat_9", "cat_10", "cat_11" };

	public static String[] bmp_d_Id = new String[] { "dog_1", "dog_2", "dog_3",
			"dog_4", "dog_5", "dog_6", "dog_7", "dog_8", "dog_9", "dog_10",
			"dog_11", "dog_12", "dog_13", "dog_14", "dog_15", "dog_16",
			"doll_1", "doll_2", "doll_3", "doll_4", "doll_5", "doll_6",
			"doll_7", "doll_8", "doll_9", "doll_10", "doll_11", "doll_12",
			"doll_13" };
	public static String[] bmp_e_Id = new String[] {
			"elephant_pronunciation_1", "elephant_pronunciation_2",
			"elephant_pronunciation_3", "elephant_pronunciation_4",
			"elephant_pronunciation_5", "elephant_pronunciation_6",
			"elephant_pronunciation_7", "elephant_pronunciation_8",
			"elephant_pronunciation_1", "eggbroken_1", "eggbroken_2",
			"eggbroken_3", "eggbroken_4", "eggbroken_5", "eggbroken_6",
			"eggbroken_7", "eggbroken_8", "eggbroken_9" };

	public static String[] bmp_f_Id = new String[] { "fish_1", "fish_2",
			"fish_3", "fish_4", "fish_5", "fish_6", "fish_7", "fish_8",
			"fish_1", "fox_1", "fox_2", "fox_3", "fox_4", "fox_5", "fox_6",
			"fox_7", "fox_8" };

	public static String[] bmp_g_Id = new String[] { "gate_1", "gate_2",
			"gate_3", "gate_4", "goat_1", "goat_2", "goat_3", "goat_4",
			"goat_5", "goat_6", "goat_7", "goat_8", "goat_9", "goat_10",
			"goat_11", "goat_12" };

	public static String[] bmp_h_Id = new String[] { "hill_4", "hill_5",
			"hill_6", "hill_7", "hill_8", "hill_9", "hill_10", "hill_11",
			"hill_12", "hill_13", "hill_14", "hill_15", "hippo_1", "hippo_2",
			"hippo_3", "hippo_4", "hippo_5", "hippo_6", "hippo_7", "hippo_8",
			"hippo_9" };

	public static String[] bmp_i_Id = new String[] { "igloo_shaking_1",
			"igloo_shaking_2", "igloo_shaking_3", "igloo_shaking_4",
			"igloo_shaking_5", "igloo_shaking_6", "igloo_shaking_7",
			"igloo_shaking_8", "igloo_shaking_9", "igloo_shaking_10",
			"incect_pronunciation_1", "incect_pronunciation_2",
			"incect_pronunciation_3", "incect_pronunciation_4",
			"incect_pronunciation_5", "incect_pronunciation_6",
			"incect_pronunciation_7" };
	public static String[] bmp_j_Id = new String[] { "jet_1", "jet_2", "jet_3",
			"jet_4", "jet_5", "jet_6", "jet_7", "jet_8", "jet_9", "jet_10",
			"jet_11", "jacket_1", "jacket_2", "jacket_3", "jacket_4",
			"jacket_5", "jacket_6", "jacket_7", "jacket_8", "jacket_9" };

	public static String[] bmp_k_Id = new String[] { "kite_1", "kite_2",
			"kite_3", "kite_4", "kite_5", "kite_6", "kite_7", "kite_8",
			"kite_9", "kite_10", "kitten_1", "kitten_2", "kitten_3",
			"kitten_4", "kitten_5", "kitten_6", "kitten_7", "kitten_8",
			"kitten_9", "kitten_10", "kitten_11", "kitten_12", "kitten_13",
			"kitten_14", "kitten_15", "kitten_16", "kitten_17" };

	public static String[] bmp_l_Id = new String[] { "leaf_1", "leaf_2",
			"leaf_3", "leaf_4", "leaf_5", "leaf_6", "leaf_7", "lion_1",
			"lion_2", "lion_3", "lion_4", "lion_5", "lion_6", "lion_7",
			"lion_8", "lion_9", };

	public static String[] bmp_m_Id = new String[] { "readmilk_1",
			"readmilk_2", "readmilk_3", "readmilk_4", "readmilk_5",
			"readmilk_6", "readmilk_7", "readmilk_8", "readmilk_9",
			"readmilk_10", "readmilk_11", "readmilk_12", "monkey_milk_1",
			"monkey_milk_2", "monkey_milk_3", "monkey_milk_4", "monkey_milk_5",
			"monkey_milk_6", "monkey_milk_7", "monkey_milk_8", "monkey_milk_9",
			"monkey_milk_10", "monkey_milk_11", "monkey_eat_1", "monkey_eat_2",
			"monkey_eat_3", "monkey_eat_4", "monkey_eat_5", "monkey_eat_6",
			"monkey_eat_7", "monkey_eat_8", "monkey_eat_9", "monkey_eat_10",
			"monkey_eat_11", "monkey_eat_12", "monkey_eat_13", "monkey_eat_14",
			"monkey_eat_15" };

	public static String[] bmp_n_Id = new String[] { "nest_1", "nest_2",
			"nest_3", "nest_4", "nest_5", "nest_6", "net_1", "net_2", "net_3",
			"net_4", "net_5", "net_6", "net_7", "net_8", "net_9", "net_10" };

	public static String[] bmp_o_Id = new String[] { "orange_1", "orange_2",
			"orange_3", "orange_4", "orange_5", "orange_6", "orange_7",
			"orange_8", "orange_9", "orange_10", "orange_11", "orange_12",
			"orange_13", "orange_14", "orange_15", "orange_16", "orange_17",
			"orange_18", "octopus_1", "octopus_2", "octopus_3", "octopus_4",
			"octopus_5", "octopus_6", "octopus_7", "octopus_8", "octopus_9",
			"octopus_10", "octopus_11", "octopus_12", "octopus_13",
			"octopus_14", "octopus_15" };
	public static String[] bmp_p_Id = new String[] { "puppy_1", "puppy_2",
			"puppy_3", "puppy_4", "puppy_5", "puppy_6", "puppy_7", "puppy_8",
			"pig_1", "pig_2", "pig_3", "pig_4", "pig_5", "pig_6", "pig_7",
			"pig_8", "pig_9", "pig_10" };
	public static String[] bmp_q_Id = new String[] { "quiet_1", "quiet_2",
			"quiet_3", "quiet_4", "quiet_5", "quiet_6", "quiet_7", "quiet_8",
			"quiet_9", "quiet_10", "quiet_11", "quiet_12", "quiet_13",
			"quiet_14", "quiet_15", "quilt_1", "quilt_2", "quilt_3", "quilt_4",
			"quilt_5", "quilt_6", "quilt_7", "quilt_8", "quilt_9", "quilt_10",
			"quilt_11", "quilt_12" };
	public static String[] bmp_r_Id = new String[] { "rain_1", "rain_2",
			"rain_3", "rain_4", "rain_5", "rain_6", "rain_7", "rain_8",
			"rain_9", "rain_10", "rain_11", "rainbow_1", "rainbow_2",
			"rainbow_3", "rainbow_4", "rainbow_5", "rainbow_6", "rainbow_7",
			"rainbow_8", "rainbow_9", "rainbow_10", "rainbow_11", "rainbow_12",
			"rainbow_13", "rainbow_14", "rainbow_15", "rainbow_16",
			"rainbow_17", "rainbow_18", "rainbow_19" };
	public static String[] bmp_s_Id = new String[] { "sea_1", "sea_2", "sea_3",
			"sea_4", "sea_5", "sea_6", "sea_7", "sea_8", "sea_9", "sea_10",
			"sea_11", "sea_12", "snow_1", "snow_2", "snow_3", "snow_4",
			"snow_5", "snow_6", "snow_7", "snow_8", "snow_9", "snow_10",
			"snow_1" };
	public static String[] bmp_t_Id = new String[] { "ten_1", "ten_2", "ten_3",
			"ten_4", "ten_5", "ten_6", "ten_7", "ten_8", "ten_9", "ten_10",
			"ten_11", "tea_1", "tea_2", "tea_3", "tea_4", "tea_5", "tea_6",
			"tea_7", "tea_9", "tea_10", "tea_11" };
	public static String[] bmp_u_Id = new String[] { "umbrella_1",
			"umbrella_2", "umbrella_3", "umbrella_4", "umbrella_5",
			"umbrella_6", "umbrella_7", "umbrella_8", "umbrella_9",
			"umbrella_10", "umbrella_11", "umbrella_12", "umbrella_13",
			"umbrella_14", "sun_1", "sun_2", "sun_3", "sun_4", "sun_5",
			"sun_6", "sun_7" };
	public static String[] bmp_v_Id = new String[] { "van_1", "van_2", "van_3",
			"van_4", "van_5", "van_6", "van_7", "van_8", "van_9", "van_10",
			"van_11", "van_12", "van_13", "van_14", "van_15", "van_16",
			"van_17", "van_18", "van_19", "van_20", "violin_1", "violin_2",
			"violin_3", "violin_4", "violin_5", "violin_6", "violin_7" };
	public static String[] bmp_w_Id = new String[] { "water_1", "water_2",
			"water_3", "water_4", "water_5", "water_6", "water_7", "water_8",
			"water_9", "water_10", "water_11", "water_12", "walk_1", "walk_2",
			"walk_3", "walk_4", "walk_5", "walk_6", "walk_7", "walk_8",
			"walk_9", };
	public static String[] bmp_x_Id = new String[] { "x_ray_1", "x_ray_2",
			"x_ray_3", "x_ray_4", "x_ray_5", "x_ray_6", "box_1", "box_2",
			"box_3", "box_4", "box_5", "box_6", "box_7", "box_8", "box_9",
			"box_10", "box_11", "box_12", "box_13" };
	public static String[] bmp_y_Id = new String[] { "yarn_1", "yarn_2",
			"yarn_3", "yarn_4", "yarn_5", "yarn_6", "yarn_7", "yoyo_1",
			"yoyo_2", "yoyo_3", "yoyo_4", "yoyo_5", "yoyo_6", "yoyo_7",
			"yoyo_8", "yoyo_9", "yoyo_10", "yoyo_11", "yoyo_12", };
	public static String[] bmp_z_Id = new String[] { "zipper_1", "zipper_2",
			"zipper_3", "zipper_4", "zipper_5", "zipper_6", "zipper_7",
			"zipper_8", "zipper_9", "zipper_10", "zipper_11", "zipper_12",
			"zebra_1", "zebra_2", "zebra_3", "zebra_4", "zebra_5", "zebra_6",
			"zebra_7", "zebra_8", "zebra_9", "zebra_10", "zebra_11",
			"zebra_12", "zebra_13", "zebra_14", "zebra_15", "zebra_16" };

	public static String[][] wordAIndex = new String[][] { // ant apple
	{ "letters_a", "letter_n", "letter_t" },
			{ "letters_a", "letter_p", "letter_p", "letter_l", "letter_e" } };

	public static int[][] lettersAIndex = new int[][] { { 2, 3, 4 },
			{ 5, 6, 7, 8, 9 } };

	public static String[][] wordBIndex = new String[][] { // ball boy
	{ "letters_b", "letter_a", "letter_l", "letter_l" },
			{ "letters_b", "letter_o", "letter_y" } };

	public static int[][] lettersBIndex = new int[][] { { 2, 3, 4, 5 },
			{ 6, 7, 8 } };

	public static String[][] wordCIndex = new String[][] { // candy cat
	{ "letters_c", "letter_a", "letter_n", "letter_d", "letter_y" },
			{ "letters_c", "letter_a", "letter_t" } };

	public static int[][] lettersCIndex = new int[][] { { 2, 3, 4, 5, 6 },
			{ 7, 8, 9 } };

	public static String[][] wordDIndex = new String[][] { // d
	{ "letters_d", "letter_o", "letter_g" },
			{ "letters_d", "letter_o", "letter_l", "letter_l" } };
	public static int[][] lettersDIndex = new int[][] { { 2, 3, 4 },
			{ 5, 6, 7, 8 } };

	public static String[][] wordEIndex = new String[][] { // e
			{ "letters_e", "letter_small_l", "letter_small_e",
					"letter_small_p", "letter_small_h", "letter_small_a",
					"letter_small_n", "letter_small_t" },
			{ "letters_e", "letter_small_g", "letter_small_g" } };
	public static int[][] lettersEIndex = new int[][] {
			{ 2, 3, 4, 5, 6, 7, 8, 9 }, { 10, 11, 12 } };

	public static String[][] wordFIndex = new String[][] { // f
	{ "letters_f", "letter_i", "letter_s", "letter_h" },
			{ "letters_f", "letter_o", "letter_x" } };
	public static int[][] lettersFIndex = new int[][] { { 2, 3, 4, 5 },
			{ 6, 7, 8 } };

	public static String[][] wordGIndex = new String[][] { // g
	{ "letters_g", "letter_a", "letter_t", "letter_e" },
			{ "letters_g", "letter_o", "letter_a", "letter_t" } };
	public static int[][] lettersGIndex = new int[][] { { 2, 3, 4, 5 },
			{ 6, 7, 8, 9 } };

	public static String[][] wordHIndex = new String[][] { // h
	{ "letters_h", "letter_i", "letter_l", "letter_l" },
			{ "letters_h", "letter_i", "letter_p", "letter_p", "letter_o" } };
	public static int[][] lettersHIndex = new int[][] { { 2, 3, 4, 5 },
			{ 6, 7, 8, 9, 10 } };

	public static String[][] wordIIndex = new String[][] { // i
			{ "letters_i", "letter_g", "letter_l", "letter_o", "letter_o" },
			{ "letters_i", "letter_n", "letter_s", "letter_e", "letter_c",
					"letter_t" } };
	public static int[][] lettersIIndex = new int[][] { { 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 10, 11, 12 } };

	public static String[][] wordJIndex = new String[][] { // j
			{ "letters_j", "letter_e", "letter_t" },
			{ "letters_j", "letter_a", "letter_c", "letter_k", "letter_e",
					"letter_t" } };
	public static int[][] lettersJIndex = new int[][] { { 2, 3, 4 },
			{ 5, 6, 7, 8, 9, 10 } };

	public static String[][] wordKIndex = new String[][] { // k
			{ "letters_k", "letter_i", "letter_t", "letter_e" },
			{ "letters_k", "letter_i", "letter_t", "letter_t", "letter_e",
					"letter_n" } };
	public static int[][] lettersKIndex = new int[][] { { 2, 3, 4, 5 },
			{ 6, 7, 8, 9, 10, 11 } };

	public static String[][] wordLIndex = new String[][] { // l
	{ "letters_l", "letter_e", "letter_a", "letter_f" },
			{ "letters_l", "letter_i", "letter_o", "letter_n" } };
	public static int[][] lettersLIndex = new int[][] { { 2, 3, 4, 5 },
			{ 6, 7, 8, 9 } };

	public static String[][] wordMIndex = new String[][] { // m
			{ "letters_m", "letter_i", "letter_l", "letter_k" },
			{ "letters_m", "letter_o", "letter_n", "letter_k", "letter_e",
					"letter_y" } };
	public static int[][] lettersMIndex = new int[][] { { 2, 3, 4, 5 },
			{ 6, 7, 8, 9, 10, 11 } };

	public static String[][] wordNIndex = new String[][] { // n
	{ "letters_n", "letter_e", "letter_s", "letter_t" },
			{ "letters_n", "letter_e", "letter_t" } };
	public static int[][] lettersNIndex = new int[][] { { 3, 4, 5, 6 },
			{ 7, 8, 9 } };

	public static String[][] wordOIndex = new String[][] { // o
			{ "letters_o", "letter_r", "letter_a", "letter_n", "letter_g",
					"letter_e" },
			{ "letters_o", "letter_c", "letter_t", "letter_o", "letter_p",
					"letter_u", "letter_s" } };
	public static int[][] lettersOIndex = new int[][] { { 2, 3, 4, 5, 6, 7 },
			{ 8, 9, 10, 11, 12, 13, 14 } };

	public static String[][] wordPIndex = new String[][] { // p
	{ "letters_p", "letter_u", "letter_p", "letter_p", "letter_y" },
			{ "letters_p", "letter_i", "letter_g" } };
	public static int[][] lettersPIndex = new int[][] { { 2, 3, 4, 5, 6 },
			{ 7, 8, 9 } };

	public static String[][] wordQIndex = new String[][] { // q
	{ "letters_q", "letter_u", "letter_i", "letter_e", "letter_t" },
			{ "letters_q", "letter_u", "letter_i", "letter_l", "letter_t" } };
	public static int[][] lettersQIndex = new int[][] { { 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 10, 11 } };

	public static String[][] wordRIndex = new String[][] { // r
			{ "letters_r", "letter_a", "letter_i", "letter_n" },
			{ "letters_r", "letter_a", "letter_i", "letter_n", "letter_b",
					"letter_o", "letter_w" } };
	public static int[][] lettersRIndex = new int[][] { { 3, 4, 5, 6 },
			{ 7, 8, 9, 10, 11, 12, 13 } };

	public static String[][] wordSIndex = new String[][] { // s
	{ "letters_s", "letter_e", "letter_a" },
			{ "letters_s", "letter_n", "letter_o", "letter_w" } };
	public static int[][] lettersSIndex = new int[][] { { 2, 3, 4 },
			{ 5, 6, 7, 8 } };

	public static String[][] wordTIndex = new String[][] { // t
	{ "letters_t", "letter_e", "letter_n" },
			{ "letters_t", "letter_e", "letter_a" } };
	public static int[][] lettersTIndex = new int[][] { { 2, 3, 4 },
			{ 5, 6, 7 } };

	public static String[][] wordUIndex = new String[][] { // u
			{ "letters_u", "letter_m", "letter_b", "letter_r", "letter_e",
					"letter_l", "letter_l", "letter_a" },
			{ "letter_s", "letters_u", "letter_n" } };
	public static int[][] lettersUIndex = new int[][] {
			{ 2, 3, 4, 5, 6, 7, 8, 9 }, { 10, 11, 12 } };

	public static String[][] wordVIndex = new String[][] { // v
			{ "letters_v", "letter_a", "letter_n" },
			{ "letters_v", "letter_i", "letter_o", "letter_l", "letter_i",
					"letter_n" } };
	public static int[][] lettersVIndex = new int[][] { { 2, 3, 4 },
			{ 5, 6, 7, 8, 9, 10 } };

	public static String[][] wordWIndex = new String[][] { // w
	{ "letters_w", "letter_a", "letter_t", "letter_e", "letter_r" },
			{ "letters_w", "letter_a", "letter_l", "letter_k" } };
	public static int[][] lettersWIndex = new int[][] { { 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 10 } };

	public static String[][] wordXIndex = new String[][] { // x
	{ "letters_x", "horizontal_line", "letter_r", "letter_a", "letter_y" },
			{ "letter_b", "letter_o", "letters_x" } };
	public static int[][] lettersXIndex = new int[][] { { 2, 3, 4, 5, 6 },
			{ 7, 8, 9 } };

	public static String[][] wordYIndex = new String[][] { // y
			{ "letters_y", "letter_a", "letter_r", "letter_n" },
			{ "letters_y", "letter_o", "horizontal_line", "letter_y",
					"letter_o" } };
	public static int[][] lettersYIndex = new int[][] { { 2, 3, 4, 5 },
			{ 6, 7, 8, 9, 10 } };

	public static String[][] wordZIndex = new String[][] { // z
			{ "letters_z", "letter_i", "letter_p", "letter_p", "letter_e",
					"letter_r" },
			{ "letters_z", "letter_e", "letter_b", "letter_r", "letter_a" } };
	public static int[][] lettersZIndex = new int[][] { { 2, 3, 4, 5, 6, 7 },
			{ 8, 9, 10, 11, 12 } };

	// 判断是否有开场动画
	public static boolean judgePrologue(int index) {
		boolean isHavePrologue = false;
		if (index == 4 || index == 8 || index == 12 || index == 13
				|| index == 17 || index == 23 || index == 24) {
			isHavePrologue = true;
		}
		return isHavePrologue;
	}

	// 得到当前循环播放的Id
//	public static String[] extraBmpData(int index, int num) {
//		String[] bmp = null;
//		if (index == 4) { // e
//			bmp = currentPic(picExtraEOrder[num], extra_e_Id);
//		} else if (index == 8) { // i
//			bmp = currentPic(picExtraIOrder[num], extra_i_Id);
//		} else if (index == 12) { // m
//			bmp = currentPic(picExtraMOrder[num], extra_m_Id);
//		} else if (index == 13) { // n
//			bmp = currentPic(picExtraNOrder[num], extra_n_Id);
//		} else if (index == 17) { // r
//			bmp = currentPic(picExtraROrder[num], extra_r_Id);
//		} else if (index == 23) { // x
//			bmp = currentPic(picExtraXOrder[num], extra_x_Id);
//		} else if (index == 24) {
//			bmp = currentPic(picExtraYOrder[num], extra_y_Id);
//		}
//		return bmp;
//	}

	/*public static int[][] picExtraEOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 } };

	// 额外的需要播放的动画
	public static String[] extra_e_Id = new String[] { "elephant_1",
			"elephant_2", "elephant_3", "elephant_4", "elephant_5",
			"elephant_6", "elephant_7", "egg_1", "egg_2", "egg_3", "egg_4",
			"egg_5", "egg_6", "egg_7", "egg_8", "egg_9", "egg_10", "egg_11" };

	
	 * public static int[][] picExtraFOrder = new int[][] { { 0, 1, 2 }, {} };
	 * // 额外的需要播放的动画 public static String[] extra_f_Id = new String[] {
	 * "fish_1", "fish_2", "fish_3" };
	 

	public static int[][] picExtraIOrder = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 10, 11, 12, 13, 14, 15, 16 } };
	// 额外的需要播放的动画
	public static String[] extra_i_Id = new String[] { "igloo_2", "igloo_3",
			"igloo_4", "igloo_5", "igloo_6", "igloo_7", "igloo_8", "igloo_9",
			"igloo_10", "igloo_11", "incect_1", "incect_2", "incect_3",
			"incect_4", "incect_5", "incect_6", "incect_7" };

	public static int[][] picExtraNOrder = new int[][] { { 0, 1, 2, 3, 4, 5 },
			{} };
	// 额外的需要播放的动画
	public static String[] extra_n_Id = new String[] { "nest_in1", "nest_in2",
			"nest_in3", "nest_in4", "nest_in5", "nest_in6" };

	public static int[][] picExtraMOrder = new int[][] { {}, { 0, 1, 2, 3, 4 } };
	// 额外的需要播放的动画
	public static String[] extra_m_Id = new String[] { "monkey_1", "monkey_2",
			"monkey_3", "monkey_4", "monkey_5" };

	public static int[][] picExtraROrder = new int[][] { {},
			{ 0, 1, 2, 3, 4, 5, 6, 7 } };
	// 额外的需要播放的动画
	public static String[] extra_r_Id = new String[] { "rain_rainbow_1",
			"rain_rainbow_2", "rain_rainbow_3", "rain_rainbow_4",
			"rain_rainbow_5", "rain_rainbow_6", "rain_rainbow_7",
			"rain_rainbow_8" };

	public static int[][] picExtraXOrder = new int[][] { {},
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } };
	// 额外的需要播放的动画
	public static String[] extra_x_Id = new String[] { "box_in_1", "box_in_2",
			"box_in_3", "box_in_4", "box_in_5", "box_in_6", "box_in_7",
			"box_in_8", "box_in_9", "box_in_10" };

	public static int[][] picExtraYOrder = new int[][] { {}, { 0, 1, 2, 3 } };
	// 额外的需要播放的动画
	public static String[] extra_y_Id = new String[] { "yoyoin_1", "yoyoin_2",
			"yoyoin_3", "yoyoin_4" };*/

	public static String[] firstPicName = new String[] { "ant_", "ball_",
			"candymachine_", "dog_", "elephant_pronunciation_", "fish_",
			"gate_", "hill_", "igloo_shaking_", "jet_", "kite_", "leaf_",
			"readmilk_", "nest_", "orange_", "puppy_", "quiet_", "rain_",
			"sea_", "ten_", "umbrella_", "van_", "water_", "x_ray_", "yarn_",
			"zipper_" };

	public static String[] sencondPicName = new String[] { "apple_", "boy_",
			"cat_", "doll_", "eggbroken_", "fox_", "goat_", "hippo_",
			"incect_pronunciation_", "jacket_", "kitten_", "lion_",
			"monkey_eat_", "net_", "octopus_", "pig_", "quilt_", "rainbow_",
			"snow_", "tea_", "sun_", "violin_", "walk_", "box_", "yoyo_",
			"zebra_" };

	public static boolean[][] animationStatus = new boolean[][] { // false:和前面的一样，
																	// true:和前面不一样
	{ false, false, false, false }, { true, false, true, true },
			{ false, false, true, true }, { false, false, true, true }, // d
			{ true, true, true, true }, // e
			{ true, true, true, true }, // f
			{ true, true, true, true }, // g

			{ true, true, true, true }, // h
			{ true, true, true, true }, // i
			{ true, true, true, true }, // j
			{ true, true, true, true }, // k
			{ true, true, true, true }, // l
			{ true, false, false, false }, // m
			{ true, true, true, true }, // n

			{ false, false, true, true }, // o
			{ true, true, true, true }, // p
			{ true, true, true, true }, // q
			{ true, true, true, true }, // r
			{ true, true, true, true }, // s
			{ true, true, false, false }, // t

			{ true, true, true, true }, // u
			{ true, true, true, true }, // v
			{ true, true, true, true }, // w
			{ true, true, true, true }, // x
			{ true, true, true, true }, // y
			{ true, true, true, true }, // z

	};

	// 拼接图片名字
	public static String[][] spliceName = new String[][] {
			{ "2", "3", "2", "3" }, { "", "3", "", "" }, { "2", "3", "", "" }, // a
			{ "2", "3", "", "" }, { "", "", "", "" }, { "", "", "", "" }, // d
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, // g
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, // j
			{ "", "3", "", "" }, { "", "", "", "" }, { "2", "3", "", "" }, // m
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, // p
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, // s
			{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, // v
			{ "", "", "", "" }, { "", "", "", "" } }; // y

	public static int[][] firstPicLength = new int[][] { { 7, 5, 7 },
			{ 9, 9, 13 }, { 7, 5, 5 }, // a
			{ 5, 7, 4 }, { 9, 9, 9 }, { 9, 9, 9 },// d
			{ 4, 4, 4 }, { 9, 9, 9 }, { 10, 10, 10 },// g
			{ 11, 11, 11 }, { 10, 10, 10 }, { 7, 7, 7 }, // j
			{ 12, 12, 11 }, { 6, 6, 6 }, { 6, 6, 6 },// m
			{ 8, 8, 8 }, { 15, 15, 15 }, { 11, 11, 11 },// p
			{ 8, 8, 8 }, { 11, 11, 11 }, { 8, 8, 8 }, // s
			{ 11, 11, 11 }, { 12, 12, 12 }, { 5, 5, 5 },// v
			{ 7, 7, 7 }, { 12, 12, 12 } // y
	};

	public static int[][] sencondPicLength = new int[][] { { 5, 5, 8 },
			{ 2, 2, 2 }, { 11, 11, 11 },// a
			{ 13, 13, 13 }, { 9, 9, 9 }, { 8, 8, 8 },// d
			{ 12, 12, 12 }, { 10, 10, 10 }, { 7, 7, 7 },// g
			{ 9, 9, 9 }, { 9, 9, 9 }, { 9, 9, 9 },// j
			{ 5, 5, 5 }, { 10, 10, 10 }, { 11, 11, 11 },// m
			{ 10, 10, 10 }, { 7, 7, 7 }, { 19, 19, 19 },// p
			{ 9, 9, 9 }, { 11, 11, 11 }, { 7, 7, 7 },// s
			{ 7, 7, 7 }, { 9, 9, 9 }, { 13, 13, 13 },// v
			{ 12, 12, 12 }, { 9, 9, 9 } // y
	};

	// 判断是否有开场动画
	public static int prologueIndex(int index) {
		int pos = -1;
		if (index == 4) {
			pos = 0;
		} else if (index == 8) {
			pos = 1;
		} else if (index == 12) {
			pos = 2;
		} else if (index == 13) {
			pos = 3;
		} else if (index == 17) {
			pos = 4;
		} else if (index == 23) {
			pos = 5;
		} else if (index == 24) {
			pos = 6;
		}
		return pos;
	}

	public static String[][] extraName = new String[][] {
			{ "elephant_", "egg_", "7", "11" }, // e
			{ "igloo_", "incect_", "10", "7" }, // i
			{ "", "monkey_", "", "5" }, // m
			{ "nest_in", "", "6", "" }, // n
			{ "", "rain_rainbow_", "", "10" }, // r
			{ "", "box_in_", "", "10" }, // x
			{ "", "yoyoin_", "", "4" } // y
	};

	public static AnimationDrawable setData(String path, String picName,
			int index, int duration) {
		AnimationDrawable frameAnim = new AnimationDrawable();
		for (int i = 1; i <= index; i++) {
			frameAnim.addFrame(
					CommonBitmap.drawableBmp(path, picName + i), duration);
		}
		// 设置为循环播放
		frameAnim.setOneShot(true);
		// 设置ImageView的背景为AnimationDrawable
		return frameAnim;
	}
	
}
