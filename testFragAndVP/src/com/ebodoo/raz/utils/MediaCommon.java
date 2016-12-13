package com.ebodoo.raz.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.gst.move.R;

import java.util.Random;

public class MediaCommon {
	static MediaPlayer mMediaPlayer;
	public static void playMusicOnly(String path)   
    {      
		final MediaPlayer mMediaPlayer2 = new MediaPlayer();    	
        try   
        {   
        	     
            /* 重置MediaPlayer */   
            mMediaPlayer2.reset();  
        }catch(Exception e)
        {
            e.printStackTrace();
        } 	    
        
        try   
        {
        	/* 设置要播放的文件的路径 */   
            mMediaPlayer2.setDataSource(path);   
            mMediaPlayer2.setLooping(false);
        }catch(Exception e)
        {
            e.printStackTrace();
        } 
        
        try   
        {
        	/* 准备播放 */   
            mMediaPlayer2.prepare();  
        }catch(Exception e)
        {
            e.printStackTrace();
        } 
        
        try   
        {
        	  
            /* 开始播放 */   
            mMediaPlayer2.start();   
            mMediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener()    
            {
                public void onCompletion(MediaPlayer arg0)   
                {   
                    if(mMediaPlayer2 != null) {
                    	mMediaPlayer2.release(); 
                    }
                }   
            });
            mMediaPlayer2.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					if(mMediaPlayer2 != null) {
						mMediaPlayer2.release();
                    }
					return false;
				}
			});
        }catch(Exception e)
        {
            e.printStackTrace();
        } 
    }  
	public static void PlayMusic(MediaPlayer mMMediaPlayer3) {  		
		try { 
			mMMediaPlayer3.setLooping(false);
        } catch (Exception ex) {  
        } 
//		try { 
//			mMMediaPlayer3.prepare();
//        } catch (Exception ex) {  
//        }  
		try {
			mMMediaPlayer3.start();// 开始播放 
			mMMediaPlayer3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					// 播放结束后的动作
					if(mp != null){
						mp.release();
						mp = null;
					}
				}
			});
			
			mMMediaPlayer3.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					if(mp != null) {
						mp.release();  
						mp = null;
                    }
					return false;
				}
			});
		}catch (Exception ex) {  
        } 
    } 
	public static void PlayMusic2() {  		
		try { 
			mMediaPlayer.setLooping(false);
        } catch (Exception ex) {  
        } 
//		try { 
//			mMMediaPlayer3.prepare();
//        } catch (Exception ex) {  
//        }  
		try {
			mMediaPlayer.start();// 开始播放 
			mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					// 播放结束后的动作
					if(mp != null){
						mp.release();
						mp = null;
					}
				}
			});
			
			mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					if(mp != null) {
						mp.release();  
						mp = null;
                    }
					return false;
				}
			});
		}catch (Exception ex) {  
        } 
    } 
	
	private static int randData1_4() {
		Random random = new Random();
		return (random.nextInt(4) + 1);
	}
	
	//第3 new 关基本音乐路径
	public static MediaPlayer getLevel03newMp3(int index, Context mContext) {

		MediaPlayer mMediaPlayer = null;
		if (index == 0) { // 背景音乐
			mMediaPlayer = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_surprise"));
		} else if (index == 1) {// 点击这里有惊喜哦
			mMediaPlayer = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_surprise"));
		} else if (index == 2) {
			mMediaPlayer = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_surprise"));
		} else if (index == 3) {
			mMediaPlayer = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_surprise"));
		} else if (index == 4) {
			mMediaPlayer = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_surprise"));
		} else if (index == 5) {
			mMediaPlayer = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_surprise"));
		} else if (index == 6) {
			mMediaPlayer = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_surprise"));
		}
		return mMediaPlayer;
	}
	
	//sayit
	public static void playSayit(Context context) {
		/*
		 * MediaCommon.PlayMusic(MediaPlayer.create(context, R.raw.en_sayit));
		 */
		mMediaPlayer = MediaPlayer.create(context, R.raw.en_sayit);
		PlayMusic2();
	}
	
	//quiz 01配音
	public String getQuiz01Mp3(Context mContext, int index) {
//		MediaPlayer mMediaPlayerGj = null;
		String music_path = "";
		if (index == 0) {// 背景音
//			mMediaPlayerGj = MediaPlayer
//					.create(mContext, R.raw.bgmusic_quizi01);
			music_path = Constant.path_raz01 + "bgmusic_quizi01.mp3";
		} else if (index == 1) {// 闯关点击提示
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				music_path = Constant.path_raz01 + "ppg_jieshao.mp3";
			} else {
				music_path = Constant.path_raz01 + "ppg_jieshao_ch.mp3";
			}
		} else if (index == 2) { // n
			music_path = Constant.path_raz01 + "sound_n.mp3";
		} else if (index == 3) { // a
			music_path = Constant.path_raz01 + "sound_a.mp3";
		} else if (index == 4) { // p
			music_path = Constant.path_raz01 + "sound_p.mp3";
		} else if (index == 6) {
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				music_path = Constant.path_raz01 + "ppg_click_the_bubble.mp3";
			} else {
				music_path = Constant.path_raz01 + "ppg_click_the_bubble_ch.mp3";
			}
		} else if (index == 7) { // 买服饰，钱不够
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				music_path = Constant.path_raz01 + "en_not_enough_money.mp3";
			} else {
				music_path = Constant.path_raz01 + "ch_not_enough_money.mp3";
			}
		} else if (index == 8) { // 买服饰，钱够了
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				music_path = Constant.path_raz01 + "en_has_enough_money.mp3";
			} else {
				music_path = Constant.path_raz01 + "ch_has_enough_money.mp3";
			}
		} else if (index == 9) { // 买服饰，钱够了
			music_path = Constant.path_raz01 + "bg_music_syj.mp3";
		}
		return music_path;
	}
	
	public static String getLeve04Mp3(boolean isEg, int index) {
		String music_path = "";
		if (index == 0) {		//背景音乐
			music_path = Constant.path_raz02 + "bgmusic_level04.mp3";
		} else if (index == 1) {//画外音1
			if(isEg) {
				music_path = Constant.path_raz02 + "eg_level04_start.mp3";
			} else {
				music_path = Constant.path_raz02 + "ch_level04_start.mp3";
			}
		} else if (index == 2) {// 画外音2
			if(isEg) {
				music_path = Constant.path_raz02 + "eg_level04_tips_1.mp3";
			} else {
				music_path = Constant.path_raz02 + "ch_level04_tips_1.mp3";
			}
		} else if (index == 3) {//画外音3
			if(isEg) {
				music_path = Constant.path_raz02 + "eg_level04_tips_2.mp3";
			} else {
				music_path = Constant.path_raz02 + "ch_level04_tips_2.mp3";
			}
		} else if (index == 4) { // sound
			music_path = Constant.path_raz02 + "level_04_sound.mp3";
		} else if (index == 5) { // monkey
			music_path = Constant.path_raz02 + "level_04_monkey.mp3";
		} else if (index == 6) { // milk
			music_path = Constant.path_raz02 + "level_04_milk.mp3";
		}else if (index == 7) { // mouse
			music_path = Constant.path_raz02 + "level_04_mouse.mp3";
		}else if (index == 8) { // moon
			music_path = Constant.path_raz02 + "level_04_moon.mp3";
		}else if (index == 9) { // letter
			music_path = Constant.path_raz02 + "level_04_letter.mp3";
		}else if (index == 10) { // say_it
			music_path = Constant.path_raz02 + "eg_say_it.mp3";
		}
		return music_path;
	}
	
	//第五关基本音乐路径
	public static String getLevel05Mp3(int index) {
		String music_path = "";
		if (index == 0) {		//s
			music_path = Constant.path_raz02 + "flash_s01_letter_he.mp3";
			
		} else if (index == 1) {//s
			music_path = Constant.path_raz02 + "flash_s02_sound.mp3";
		} else if (index == 2) {// 蜘蛛
			music_path = Constant.path_raz02 + "flash_s03_spider.mp3";

		} else if (index == 3) {//沙发
			music_path = Constant.path_raz02 + "flash_s04_sofa.mp3";
		} else if (index == 4) { // 蛇
			music_path = Constant.path_raz02 + "flash_s05_snake.mp3";
		} else if (index == 5) { // 袜子
			music_path = Constant.path_raz02 + "flash_s06_socks.mp3";
		} else if (index == 6) { // 背景音乐
			music_path = Constant.path_raz02 + "bg_music_level5.mp3";
		}else if (index == 7) { // 背景音乐
			music_path = Constant.path_raz02 + "bg_sayit_level5.mp3";
		}else if (index == 8) { // 背景音乐
			music_path = Constant.path_raz02 + "flash_level5_good.mp3";
		}else if (index == 9) { // 游戏开始
			music_path = Constant.path_raz02 + BaseCommon.getMp3Path("level5_start");
		}else if (index == 10) { // 第一次点击气球
			music_path = Constant.path_raz02 + BaseCommon.getMp3Path("click_qiqiu_first");
		}else if (index == 11) { // 第二三四次点击气球
			music_path = Constant.path_raz02 + BaseCommon.getMp3Path("click_qiqiu");
		}else if (index == 12) { // sayit
			music_path = Constant.path_raz02 + "en_sayit.mp3";//BaseCommon.getMp3Path("sayit");
		}else if (index == 13) { // 欢呼
			music_path = Constant.path_raz02 + "huanhu.mp3";
		}
		return music_path;
	}
	
	
	//第6 new 关基本音乐路径
	public static String getLevel06newMp3(int index) {
		String music_path = "";
		if (index == 0) { // Tt
			music_path = Constant.path_raz02 + "flash_t01_letter.mp3";
		} else if (index == 1) {// Tt
			music_path = Constant.path_raz02 + "flash_t02_sound.mp3";
		} else if (index == 2) {// 桌子
			music_path = Constant.path_raz02 + "flash_t03_table.mp3";
		} else if (index == 3) {// 西红柿
			music_path = Constant.path_raz02 + "flash_t04_tomato.mp3";
		} else if (index == 4) { // 老虎
			music_path = Constant.path_raz02 + "flash_t05_tiger.mp3";
		} else if (index == 5) { // 乌龟
			music_path = Constant.path_raz02 + "flash_t06_turtle.mp3";
		} else if (index == 6) { // 背景
			music_path = Constant.path_raz02 + "bg_music_level06.mp3";
		} else if (index == 8) { // 英文开场白
			music_path = Constant.path_raz02
					+ BaseCommon.getMp3Path2("game_guide_t06");
		} else if (index == 9) { // sayit
			music_path = Constant.path_raz02 + "en_sayit.mp3";// +
																// BaseCommon.getMp3Path("sayit");//
		} else if (index == 10) { // 欢呼
			music_path = Constant.path_raz02 + "huanhu.mp3";
		} else if (index == 11) {
			music_path = Constant.path_raz03
					+ BaseCommon.getMp3Path("clickhere");
		}
		return music_path;
	}
	
	public static String getLevel07Mp3(boolean isEg, int index) {
		String music_path = "";
		if (index == 0) {		//背景音乐
			music_path = Constant.path_raz03 + "bgmusic_level07.mp3";
		} else if(index == 1) { // Oo读音
			music_path = Constant.path_raz03 + "o_merger_letter.mp3";
		} else if(index == 2) { // 合--ox
			music_path = Constant.path_raz03 + "o_merger_ox.mp3";
		} else if(index == 3) { // 合--otter
			music_path = Constant.path_raz03 + "o_merger_otter.mp3";
		} else if(index == 4) { // 合--osterich
			music_path = Constant.path_raz03 + "o_merger_osterich.mp3";
		} else if(index == 5) { // 合--officer
			music_path = Constant.path_raz03 + "o_merger_officer.mp3";
		} else if(index == 6) { // 旁白
			if(isEg) {
				music_path = Constant.path_raz03 + "eg_click_candy.mp3";
			} else {
				music_path = Constant.path_raz03 + "ch_click_candy.mp3";
			}
		} else if(index == 7) { // say it
			music_path = Constant.path_raz03 + "eg_say_it.mp3";
		} else if(index == 8) { // 单--ox
			music_path = Constant.path_raz03 + "o_ox.mp3";
		} else if(index == 9) { // 单--otter
			music_path = Constant.path_raz03 + "o_otter.mp3";
		} else if(index == 10) { // 单--osterich
			music_path = Constant.path_raz03 + "o_osterich.mp3";
		} else if(index == 11) { // 单--officer
			music_path = Constant.path_raz03 + "o_officer.mp3";
		} else if(index == 12) { // o 单音
			music_path = Constant.path_raz03 + "o_letter.mp3";
		}
		return music_path;
	}
	
	//第8关基本音乐路径
		public static String getLevel08Mp3(int index) {
			String music_path = "";
			if (index == 0) {		//背景音乐
				music_path = Constant.path_raz03 + "bg_music_level8.mp3";
			} else if (index == 1) {//游戏开始第一个画外音
				music_path = Constant.path_raz03 + BaseCommon.getMp3Path("level8_start1");
			} else if (index == 2) {// sayit
				music_path = Constant.path_raz03 + "en_sayit.mp3";//BaseCommon.getMp3Path("sayit");
			} else if (index == 3) {//欢呼
				music_path = Constant.path_raz03 + "huanhu.mp3";
			} else if (index == 4) { // D
				music_path = Constant.path_raz03 + "flash_d01_letter.mp3";
			} else if (index == 5) { // d
				music_path = Constant.path_raz03 + "flash_d02_sound.mp3";
			} else if (index == 6) { // Dd
				music_path = Constant.path_raz03 + "flash_d01_letters.mp3";
			}else if (index == 7) { // dog
				music_path = Constant.path_raz03 + "flash_d03_dog.mp3";
			}else if (index == 8) { // d dog
				music_path = Constant.path_raz03 + "flash_d03_dogs.mp3";
			}else if (index == 9) { // duck
				music_path = Constant.path_raz03 + "flash_d05_duck.mp3";
			}else if (index == 10) { // d duck
				music_path = Constant.path_raz03 + "flash_d05_ducks.mp3";
			}else if (index == 11) { // deer
				music_path = Constant.path_raz03 + "flash_d07_deer.mp3";
			}else if (index == 12) { // d deer 
				music_path = Constant.path_raz03 + "flash_d07_deers.mp3";
			}else if (index == 13) { // desk
				music_path = Constant.path_raz03 + "flash_d09_desk.mp3";
			}else if (index == 14) { // d desk
				music_path = Constant.path_raz03 + "flash_d09_desks.mp3";
			}else if (index == 15){  //第二遍画外音
				music_path = Constant.path_raz03 + BaseCommon.getMp3Path("level8_start2");		
			}
					
			return music_path;
		}
		
		//第五关基本音乐路径
		public static String getLevel09Mp3(int index) {
			String music_path = "";
			if (index == 0) {		//背景音乐
				music_path = Constant.path_raz03 + "bg_music_level9.mp3";
			} else if (index == 1) {//游戏开始
				music_path = Constant.path_raz03 + BaseCommon.getMp3Path("level9_start");
			} else if (index == 2) {// sayit
				music_path = Constant.path_raz03 + "en_sayit.mp3";//BaseCommon.getMp3Path("sayit");
			} else if (index == 3) {//欢呼
				music_path = Constant.path_raz03 + "huanhu.mp3";
			} else if (index == 4) { // ad
				music_path = Constant.path_raz03 + "flash_ad_02_sound.mp3";
			} else if (index == 5) { // an
				music_path = Constant.path_raz03 + "flash_an_02_sound.mp3";
			} else if (index == 6) { // f
				music_path = Constant.path_raz03 + "flash_09_f_sound.mp3";
			}else if (index == 7) { // v
				music_path = Constant.path_raz03 + "flash_09_v_sound.mp3";
			}else if (index == 8) { // p
				music_path = Constant.path_raz03 + "flash_09_p_sound.mp3";
			}else if (index == 9) { // m
				music_path = Constant.path_raz03 + "flash_09_m_sound.mp3";
			}else if (index == 10) { // fan
				music_path = Constant.path_raz03 + "flash_an_01_fan.mp3";
			}else if (index == 11) { // van
				music_path = Constant.path_raz03 + "flash_an_02_van.mp3";
			}else if (index == 12) { // pan
				music_path = Constant.path_raz03 + "flash_an_03_pan.mp3";
			}else if (index == 13) { // man
				music_path = Constant.path_raz03 + "flash_an_04_man.mp3";
			}else if (index == 14) { // mad
				music_path = Constant.path_raz03 + "flash_ad_01_mad.mp3";
			}else if (index == 15) { // sad
				music_path = Constant.path_raz03 + "flash_ad_02_sad.mp3";
			}else if (index == 16) { // pad
				music_path = Constant.path_raz03 + "flash_ad_03_pad.mp3";
			}else if (index == 17) { // dad
				music_path = Constant.path_raz03 + "flash_ad_04_dad.mp3";
			}
					
			return music_path;
		}
		
		//第五关基本音乐路径
		public static String getLevel10Mp3(int index) {
			String music_path = "";
					
			if (index == 0) {		//背景音乐
				music_path = Constant.path_raz03 + "bg_music_level10.mp3";
			} else if (index == 1) {//游戏开始
				music_path = Constant.path_raz03 + BaseCommon.getMp3Path("level10_start");
			} else if (index == 2) {// e
				music_path = Constant.path_raz03 + "flash_e02_sound.mp3";//BaseCommon.getMp3Path("sayit");
			} else if (index == 3) {//e 合成
				music_path = Constant.path_raz03 + "flash_e01_letter_he.mp3";
			} else if (index == 4) { // egg
				music_path = Constant.path_raz03 + "flash_e03_egg.mp3";
			} else if (index == 5) { // egg 合成
				music_path = Constant.path_raz03 + "flash_e03_egg_he.mp3";
			} else if (index == 6) { // elephant
				music_path = Constant.path_raz03 + "flash_e05_elephant.mp3";
			}else if (index == 7) { // elephant 合成
				music_path = Constant.path_raz03 + "flash_e05_elephant_he.mp3";
			}else if (index == 8) { // elk
				music_path = Constant.path_raz03 + "flash_e11_elk.mp3";
			}else if (index == 9) { // elk 合成
				music_path = Constant.path_raz03 + "flash_e11_elk_he.mp3";
			}else if (index == 10) { // elevator
				music_path = Constant.path_raz03 + "flash_e07_elevator.mp3";
			}else if (index == 11) { // elevator 合成
				music_path = Constant.path_raz03 + "flash_e07_elevator_he.mp3";
			}	
			return music_path;
		}
		
	// 第11关基本音乐路径
	public static String getLevel11Mp3(int index) {
		String music_path = "";
		if (index == 0) { // 背景音乐
			music_path = Constant.path_raz04 + "bg_music_level8.mp3";
		} else if (index == 1) {// 游戏开始第一个画外音
			music_path = Constant.path_raz04
					+ BaseCommon.getMp3Path("level11_start");
		} else if (index == 2) {// sayit
			music_path = Constant.path_raz04 + "en_sayit.mp3";// BaseCommon.getMp3Path("sayit");
		} else if (index == 3) {// 欢呼
			music_path = Constant.path_raz04 + "huanhu.mp3";
		} else if (index == 4) { // H
			music_path = Constant.path_raz04 + "flash_h01_letter.mp3";
		} else if (index == 5) { // h
			music_path = Constant.path_raz04 + "flash_h02_sound.mp3";
		} else if (index == 6) { // Hh
			music_path = Constant.path_raz04 + "flash_h01_letters.mp3";
		} else if (index == 7) { // hat (1)
			music_path = Constant.path_raz04 + "flash_h05_hat.mp3";
		} else if (index == 8) { // h hat (1)
			music_path = Constant.path_raz04 + "flash_h05_hats.mp3";
		} else if (index == 9) { // hen (2)
			music_path = Constant.path_raz04 + "flash_h03_hen.mp3";
		} else if (index == 10) { // h hens (2)
			music_path = Constant.path_raz04 + "flash_h03_hens.mp3";
		} else if (index == 11) { // horse (3)
			music_path = Constant.path_raz04 + "flash_h07_horse.mp3";
		} else if (index == 12) { // h horses (3)
			music_path = Constant.path_raz04 + "flash_h07_horses.mp3";
		} else if (index == 13) { // hammer (4)
			music_path = Constant.path_raz04 + "flash_h13_hammer.mp3";
		} else if (index == 14) { // h hammer (4)
			music_path = Constant.path_raz04 + "flash_h13_hammers.mp3";
		} else if (index == 15) {
			music_path = Constant.path_raz04
					+ BaseCommon.getMp3Path("clickhere");
		}

		return music_path;
	}
	
	public static String getLeve12Mp3(boolean isEg, int index) {
		String music_path = "";
		if (index == 0) {		//背景音乐
		} else if (index == 1) {//画外音1
			if(isEg) {
				music_path = Constant.path_raz04 + "eg_level12_start.mp3";
			} else {
				music_path = Constant.path_raz04 + "ch_level12_start.mp3";
			}
		} else if (index == 2) {// 合成 op
			music_path = Constant.path_raz04 + "op_letter_merge.mp3";
		} else if (index == 3) {//字母 op
			music_path = Constant.path_raz04 + "op_letter.mp3";
		} else if (index == 4) { // 读音op
			music_path = Constant.path_raz04 + "op_sound.mp3";
		} else if (index == 5) { // hop
			music_path = Constant.path_raz04 + "op_hop.mp3";
		} else if (index == 6) { // top
			music_path = Constant.path_raz04 + "op_top.mp3";
		} else if (index == 7) { // cop
			music_path = Constant.path_raz04 + "op_cop.mp3";
		} else if (index == 8) { // pop
			music_path = Constant.path_raz04 + "op_pop.mp3";
		} else if (index == 9) { // 合成ot
			music_path = Constant.path_raz04 + "ot_letter_merge.mp3";
		} else if (index == 10) { // 字母ot
			music_path = Constant.path_raz04 + "ot_letter.mp3";
		} else if (index == 11) { // 读音ot
			music_path = Constant.path_raz04 + "ot_sound.mp3";
		} else if (index == 12) { // hot
			music_path = Constant.path_raz04 + "ot_hot.mp3";
		} else if (index == 13) { // tot
			music_path = Constant.path_raz04 + "ot_tot.mp3";
		} else if (index == 14) { // dot
			music_path = Constant.path_raz04 + "ot_dot.mp3";
		} else if (index == 15) { // pot
			music_path = Constant.path_raz04 + "ot_pot.mp3";
		}
		return music_path;
	}
	
	
	//第五关基本音乐路径
	public static String getLevel13Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = Constant.path_raz02 + "bg_music_level5.mp3";
		} else if (index == 1) {//游戏开始
			music_path = Constant.path_raz04 + BaseCommon.getMp3Path("level13_start");
		} else if (index == 2) {// f
			music_path = Constant.path_raz04 + "flash_f02_sound.mp3";//BaseCommon.getMp3Path("sayit");
		} else if (index == 3) {//f 合成
			music_path = Constant.path_raz04 + "flash_f01_letter_he.mp3";
		} else if (index == 4) { // fan
			music_path = Constant.path_raz04 + "flash_f03_fan.mp3";
		} else if (index == 5) { // fan 合成
			music_path = Constant.path_raz04 + "flash_f03_fan_he.mp3";
		} else if (index == 6) { // fish
			music_path = Constant.path_raz04 + "flash_f05_fish.mp3";
		}else if (index == 7) { // fish 合成
			music_path = Constant.path_raz04 + "flash_f05_fish_he.mp3";
		}else if (index == 8) { // fork
			music_path = Constant.path_raz04 + "flash_f09_fork.mp3";
		}else if (index == 9) { // fork 合成
			music_path = Constant.path_raz04 + "flash_f09_fork_he.mp3";
		}else if (index == 10) { // four
			music_path = Constant.path_raz04 + "flash_f11_four.mp3";
		}else if (index == 11) { // four 合成
			music_path = Constant.path_raz04 + "flash_f11_four_he.mp3";
		}	
		return music_path;
	}
	
	
	//第14关基本音乐路径
	public static String getLevel14Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = Constant.path_raz04 + "bg_music_level14.mp3";
		} else if (index == 1) {//游戏开始
			music_path = Constant.path_raz04 + BaseCommon.getMp3Path("level14_start");
		} else if (index == 2) {// g
			music_path = Constant.path_raz04 + "flash_g02_sound.mp3";//BaseCommon.getMp3Path("sayit");
		} else if (index == 3) {//g 合成
			music_path = Constant.path_raz04 + "flash_g01_letter.mp3";
		} else if (index == 4) { // gate
			music_path = Constant.path_raz04 + "flash_g03_gate.mp3";
		} else if (index == 5) { // gate 合成
			music_path = Constant.path_raz04 + "flash_g03_gate_he.mp3";
		} else if (index == 6) { // gift
			music_path = Constant.path_raz04 + "flash_g09_gift.mp3";
		}else if (index == 7) { // gift 合成
			music_path = Constant.path_raz04 + "flash_g09_gift_he.mp3";
		}else if (index == 8) { // goose
			music_path = Constant.path_raz04 + "flash_g11_goose.mp3";
		}else if (index == 9) { // goose 合成
			music_path = Constant.path_raz04 + "flash_g11_goose_he.mp3";
		}else if (index == 10) { // garden
			music_path = Constant.path_raz04 + "flash_g13_garden.mp3";
		}else if (index == 11) { // garden 合成
			music_path = Constant.path_raz04 + "flash_g13_garden_he.mp3";
		}else if (index == 12) { // gift之前的旁白
			music_path = Constant.path_raz04 + BaseCommon.getMp3Path("level14_pangbai_word2");
		}else if (index == 13) { // goose之前的旁白
			music_path = Constant.path_raz04 + BaseCommon.getMp3Path("level14_pangbai_word3");
		}	
		return music_path;
	}	

	// 第15关基本音乐路径
	public static String getLevel15Mp3(int index) {
		String music_path = "";
		if (index == 0) { // 背景音乐
			music_path = Constant.path_raz03 + "bg_music_level8.mp3";
		} else if (index == 1) {// 游戏开始第一个画外音
			music_path = Constant.path_raz04
					+ BaseCommon.getMp3Path("level15_prologue");
		} else if (index == 2) {
			music_path = Constant.path_raz04 + "flash_et_01_net.mp3";
		} else if (index == 3) {
			music_path = Constant.path_raz04 + "flash_et_02_jet.mp3";
		} else if (index == 4) {
			music_path = Constant.path_raz04 + "flash_et_03_wet.mp3";
		} else if (index == 5) {
			music_path = Constant.path_raz04 + "flash_et_04_vet.mp3";
		} else if (index == 6) {
			music_path = Constant.path_raz04 + "flash_en_05_hen.mp3";
		} else if (index == 7) {
			music_path = Constant.path_raz04 + "flash_en_06_ten.mp3";
		} else if (index == 8) {
			music_path = Constant.path_raz04 + "flash_en_07_pen.mp3";
		} else if (index == 9) {
			music_path = Constant.path_raz04 + "flash_en_08_men.mp3";
		}
		return music_path;
	}
	
	//欧洲 第1 关 复习2 基本音乐路径
	public static String getEp1_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading01 + "bg_music2_x.mp3";
		} else if (index == 1) {//游戏开始
//			music_path = ConstantEp.path_reading01 + BaseCommon.getMp3Path("quiz04_start");
		} else if (index == 2) {// i
			music_path = ConstantEp.path_reading01 + "flash_i02_sound.mp3";
		} else if (index == 3) {//a
			music_path = ConstantEp.path_reading01 + "flash_a02_sound.mp3";
		} else if (index == 4) { //n
			music_path = ConstantEp.path_reading01 + "flash_n02_sound.mp3";
		} else if (index == 5) { //p
			music_path = ConstantEp.path_reading01 + "flash_p02_sound.mp3";
		} 
		return music_path;
	}		
	
	//欧洲 第2 关 复习2 基本音乐路径
	public static String getEp2_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading01 + "bg_music2_2.mp3";
		} else if (index == 1) {//游戏开始
			music_path = ConstantEp.path_reading01 + BaseCommon.getMp3Path("ep_level2_2_start");
		} else if (index == 2) {// b
			music_path = ConstantEp.path_reading01 + "flash_b02_sound.mp3";
		} else if (index == 3) {//水花 +b
			music_path = ConstantEp.path_reading01 + "music_shuihua_b.mp3";
		} else if (index == 4) {//水花
			music_path = ConstantEp.path_reading01 + "music_shuihua.mp3";
		} else if (index == 5) {//飞行棋背景音乐
			music_path = ConstantEp.path_reading01 + "flightchess/" + "qi_bg_music.mp3";
		} else if (index == 6) {// i
			music_path = ConstantEp.path_reading01 + "flash_i02_sound.mp3";
		} else if (index == 7) {// m
			music_path = ConstantEp.path_reading01 + "flash_m02_sound.mp3";
		} else if (index == 8) {// t
			music_path = ConstantEp.path_reading01 + "flash_t02_sound.mp3";
		}
		
		
		return music_path;
	}
	//欧洲 第3 关 复习1 基本音乐路径
	public static String getEp3_1Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading01 + "bg_music3_1.mp3";
		} else if (index == 1) {//游戏开始
			music_path = ConstantEp.path_reading01 + BaseCommon.getMp3Path("level3_1_start");
		} else if (index == 2) {// hit
			music_path = ConstantEp.path_reading01 + "flash_it_hit.mp3";
		} else if (index == 3) {//pit
			music_path = ConstantEp.path_reading01 + "flash_it_pit.mp3";
		} else if (index == 4) {//sit
			music_path =ConstantEp.path_reading01 + "flash_it_sit.mp3";
		} else if (index == 5) {//kit
			music_path = ConstantEp.path_reading01 + "flash_it_kit.mp3";
		} else if (index == 6) {//tin 
			music_path = ConstantEp.path_reading01 + "flash_in_tin.mp3";
		} else if (index == 7) {//pin
			music_path = ConstantEp.path_reading01 + "flash_in_pin.mp3";
		} else if (index == 8) {//win
			music_path = ConstantEp.path_reading01 + "flash_in_win.mp3";
		}  else if (index == 9) {//fin
			music_path = ConstantEp.path_reading01 + "flash_in_fin.mp3";
		} 
		
		
		return music_path;
	}
	//欧洲 第3 关 复习2 基本音乐路径
	public static String getEp3_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading01 + "bg_music2_x.mp3";
		} else if (index == 1) {//游戏开始
			music_path = ConstantEp.path_reading01 + BaseCommon.getMp3Path("ep_level3_2_start");
		} else if (index == 2) {// in
			music_path = ConstantEp.path_reading01 + "flash_in_02_sound.mp3";
		} else if (index == 3) {//it
			music_path = ConstantEp.path_reading01 + "flash_it_02_sound.mp3";
		} else if (index == 4) {//ad
			music_path = ConstantEp.path_reading01 + "flash_ad_02_sound.mp3";
		} else if (index == 5) {//an
			music_path = ConstantEp.path_reading01 + "flash_an_02_sound.mp3";
		} 
		
		return music_path;
	}
	//欧洲 第3 关 复习2 基本音乐路径
	public static String getEp4_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading01 + "bg_music2_x.mp3";
		} else if (index == 1) {//游戏开始
			music_path = ConstantEp.path_reading01 + BaseCommon.getMp3Path("ep_level4_2_start");
		} else if (index == 2) {// c
			music_path = ConstantEp.path_reading01 + "flash_c02_sound.mp3";
		} else if (index == 3) {// f
			music_path = ConstantEp.path_reading01 + "flash_f02_sound.mp3";
		} else if (index == 4) {// h
			music_path = ConstantEp.path_reading01 + "flash_h02_sound.mp3";
		} else if (index == 5) {// s
			music_path = ConstantEp.path_reading01 + "flash_s02_sound.mp3";
		} else if (index == 6) {// 图书馆说明
			music_path = ConstantEp.path_reading01 + "library_shuoming.mp3";
		} 
		
		return music_path;
	}
	
	//欧洲 第3 关 复习2 基本音乐路径
	public static String getEbookMp3(int index) {
		String music_path = "";
		
		if (index == 1) {// 图书馆说明
//			music_path = ConstantEbook.path_reaEbook01 + "library_shuoming.mp3";
		} 
		
		return music_path;
	}
		
	//欧洲 第3 关 复习2 基本音乐路径
	public static String getEp5_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading01 + "bg_music5_2.mp3";
		} else if (index == 1) {//游戏开始
			music_path = ConstantEp.path_reading01 + BaseCommon.getMp3Path("ep_level5_2_start2");
		} else if (index == 2) {// r
			music_path = ConstantEp.path_reading01 + "flash_r02_sound.mp3";
		}  else if (index == 3) {//游戏开始
			music_path = ConstantEp.path_reading01 + BaseCommon.getMp3Path("ep_level5_2_start1");
		} else if (index == 4) {//c
			music_path = ConstantEp.path_reading01 + "flash_c02_sound.mp3";
		} else if (index == 5) {//g
			music_path = ConstantEp.path_reading01 + "flash_g02_sound.mp3";
		} else if (index == 6) {//o
			music_path = ConstantEp.path_reading01 + "flash_o02_sound.mp3";
		}
		return music_path;
	}
	
	//非洲第1 关 复习2 基本音乐路径
	public static String getAfrica1_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading02 + "bg_music_africa1_2.mp3";
		} else if (index == 1) {//游戏开始
				music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa1_2_1");
		} else if (index == 2) {// u
			music_path = ConstantEp.path_reading02 + "flash_u02_sound.mp3";
		} else if (index == 3) {//b
			music_path = ConstantEp.path_reading02 + "flash_b02_sound.mp3";
		} else if (index == 4) { //c
			music_path = ConstantEp.path_reading02 + "flash_c02_sound.mp3";
		} else if (index == 5) { //i
			music_path = ConstantEp.path_reading02 + "flash_i02_sound.mp3";
		} else if (index == 6) { //开始玩提示
			music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa1_2_2");
		}
		return music_path;
	}		
	//非洲第2 关 复习2 基本音乐路径
	public static String getAfrica2_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading02 + "bg_music_africa2_2.mp3";
		} else if (index == 1) {//游戏开始
//				music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa2_2_1");
		} else if (index == 2) {// un
			music_path = ConstantEp.path_reading02 + "flash_un_02_sound.mp3";
		} else if (index == 3) {//ug
			music_path = ConstantEp.path_reading02 + "flash_ug_02_sound.mp3";
		} else if (index == 4) { //in
			music_path = ConstantEp.path_reading02 + "flash_in_02_sound.mp3";
		} else if (index == 5) { //it
			music_path = ConstantEp.path_reading02 + "flash_it_02_sound.mp3";
		} else if (index == 6) { //开始玩提示
			music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa2_2_1");
		}
		return music_path;
	}		
	//非洲第3关 复习2 基本音乐路径
	public static String getAfrica3_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading02 + "bg_music_africa3_2.mp3";
		} else if (index == 1) {//游戏开始
//				music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa1_2_1");
		} else if (index == 2) {// ap
			music_path = ConstantEp.path_reading02 + "flash_ap_02_sound.mp3";
		} else if (index == 3) {//at
			music_path = ConstantEp.path_reading02 + "flash_at_02_sound.mp3";
		} else if (index == 4) { //ug
			music_path = ConstantEp.path_reading02 + "flash_ug_02_sound.mp3";
		} else if (index == 5) { //un
			music_path = ConstantEp.path_reading02 + "flash_un_02_sound.mp3";
		} else if (index == 6) { //开始玩提示
			music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa3_2_1");
		}
		return music_path;
	}		
	//非洲第4 关 复习2 基本音乐路径
	public static String getAfrica4_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading02 + "bg_music_africa4_2.mp3";
		} else if (index == 1) {//游戏开始
//				music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa4_2_1");
		} else if (index == 2) {// l
			music_path = ConstantEp.path_reading02 + "flash_l02_sound.mp3";
		} else if (index == 3) {//d
			music_path = ConstantEp.path_reading02 + "flash_d02_sound.mp3";
		} else if (index == 4) { //e
			music_path = ConstantEp.path_reading02 + "flash_e02_sound.mp3";
		} else if (index == 5) { //i
			music_path = ConstantEp.path_reading02 + "flash_i02_sound.mp3";
		} else if (index == 6) { //开始玩提示
			music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa4_2_1");
		}
		return music_path;
	}		
	//非洲第5 关 复习2 基本音乐路径
	public static String getAfrica5_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading02 + "bg_music_africa5_2.mp3";
		} else if (index == 1) {//游戏开始
//				music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa1_2_1");
		} else if (index == 2) {// w
			music_path = ConstantEp.path_reading02 + "flash_w02_sound.mp3";
		} else if (index == 3) {//c
			music_path = ConstantEp.path_reading02 + "flash_c02_sound.mp3";
		} else if (index == 4) { //a
			music_path = ConstantEp.path_reading02 + "flash_a02_sound.mp3";
		} else if (index == 5) { //u
			music_path = ConstantEp.path_reading02 + "flash_u02_sound.mp3";
		} else if (index == 6) { //开始玩提示
			music_path = ConstantEp.path_reading02 + BaseCommon.getMp3Path("voiceover_africa5_2_1");
		}
		return music_path;
	}		
		
	//亚洲新加坡第1 关 学习 基本音乐路径
	public static String getAsia1_0Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading03 + "bg_music_asia1_1.mp3";
		} else if (index == 1) {//游戏开始提示1
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_1");
		} else if (index == 2) {// 提示2
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_2");
		} else if (index == 3) {// 提示3
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_3");
		} else if (index == 4) { //ill
			music_path = ConstantEp.path_reading03 + "flash_ip_02_sound.mp3";
		} else if (index == 5) { //ip
			music_path = ConstantEp.path_reading03 + "flash_ill_02_sound.mp3";
		} else if (index == 6) { //开始玩提示
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_10");
		}
		return music_path;
	}	
	//亚洲新加坡第1 关复习1 基本音乐路径
	public static String getAsia1_1Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading03 + "bg_music_asia1_2.mp3";
		} else if (index == 1) {//游戏开始 来到新加坡动物园voiceover1_4
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_4");
		} else if (index == 2) {// 提示1 根据提示音选择单词 喂犀牛
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_5");
		} else if (index == 3) {// 提示2 选择正确的单词到横线，喂犀牛吧
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_6");
		} else if (index == 4) {// 提示1 根据提示音选择单词 与鹦鹉一起做游戏
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_7");
		} else if (index == 5) {// 提示2 选择正确的单词到横线，看看发生什么有趣的事情
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_8");
		} else if (index == 6) { //ill
			music_path = ConstantEp.path_reading03 + "flash_ip_02_sound.mp3";
		} else if (index == 7) { //ip
			music_path = ConstantEp.path_reading03 + "flash_ill_02_sound.mp3";
		} else if (index == 8) { //开始玩提示
			
		}
		return music_path;
	}	
	//亚洲第1 关 复习2 基本音乐路径
	public static String getAsia1_2Mp3(int index) {
		String music_path = "";
				
		if (index == 0) {		//背景音乐
			music_path = ConstantEp.path_reading03 + "bg_music_asia1_2.mp3";
		} else if (index == 1) {//游戏开始
//					music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover_africa4_2_1");
		} else if (index == 2) {// ill
			music_path = ConstantEp.path_reading03 + "flash_ill_02_sound.mp3";
		} else if (index == 3) {//ip
			music_path = ConstantEp.path_reading03 + "flash_ip_02_sound.mp3";
		} else if (index == 4) { //ot
			music_path = ConstantEp.path_reading03 + "flash_ot_02_sound.mp3";
		} else if (index == 5) { //en
			music_path = ConstantEp.path_reading03 + "flash_en_02_sound.mp3";
		} else if (index == 6) { //开始玩提示
			music_path = ConstantEp.path_reading03 + BaseCommon.getMp3Path("voiceover1_9");
		}
		return music_path;
	}
	
	//亚洲印度第1 关 学习 基本音乐路径
	public static String getAsia3_0Mp3(int index) {
		String music_path = "";

		if (index == 0) { // 学习部分背景音乐
			music_path = ConstantEp.path_reading03 + "bg_music_asia3_1.mp3";
		} else if (index == 1) {// 游戏开始提示1
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("voiceover3_1");
		}else if (index == 2) {// 提示2
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("voiceover3_2");
		} else if (index == 3) {// 提示3
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("voiceover3_2_1");
		}else if (index == 4) {// 提示 4
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("voiceover3_3");
		} else if (index == 5) {// 提示 5
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("voiceover3_3_1");
		} else if(index == 6) { // 复习1背景音
			music_path = ConstantEp.path_reading03 + "bg_music_asia3_2.mp3";
		} else if(index == 7) { // 复习2背景音
			music_path = ConstantEp.path_reading03 + "bg_music_asia3_3.mp3";
		}
		return music_path;
	}
	
	//亚洲日本第1 关 学习 基本音乐路径
	public static String getAsia5_0Mp3(int index) {
		String music_path = "";

		if (index == 0) { // 学习部分背景音乐
			music_path = ConstantEp.path_reading03 + "bg_music_asia5_1.mp3";
		} else if (index == 1) {// 复习1 - 1
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_1_0");
		} else if (index == 2) {// 复习1 - 2
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_1_1");
		} else if (index == 3) {// 复习1 - 3
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_1_2");
		} else if (index == 4) {// 复习1 - 4
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_1_3");
		} else if (index == 5) {// 学习1 - 1
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_0_0");
		} else if (index == 6) {// 学习1 - 2
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_0_1");
		} else if (index == 7) {// 学习1 - 3
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_0_2");
		} else if (index == 8) {// 学习1 - 4
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_0_3");
		} else if (index == 9) {// 学习1 - 5
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_0_4");
		} else if (index == 10) {// 复习2 - 1
			music_path = ConstantEp.path_reading03
					+ BaseCommon.getMp3Path("japan_voiceover_2_0");
		} else if(index == 11) { // 复习1背景音乐
			music_path = ConstantEp.path_reading03 + "bg_music_asia5_2.mp3";
		} else if(index == 12) { // 复习2背景音乐
			music_path = ConstantEp.path_reading03 + "bg_music_asia5_3.mp3";
		}
		return music_path;
	}
	
	//欧洲荷兰复习1基本音乐路径
	public static String getEp1_1Mp3(int index){
		String music_path="";
		if(index == 0){
			music_path = ConstantEp.path_reading01+"flash_i01_ink.mp3";
		}else if (index == 1) {
			music_path = ConstantEp.path_reading01+"flash_i04_infant.mp3";
		}else if (index == 2) {
			music_path = ConstantEp.path_reading01+"flash_i02_insect.mp3";
		}else if (index == 3) {
			music_path = ConstantEp.path_reading01+"flash_i03_igloo.mp3";
		}else if (index == 4) {
			music_path = ConstantEp.path_reading01+BaseCommon.getMp3Path("c1_2");
		}else if (index == 5) {
			music_path = ConstantEp.path_reading01+"ep_bg1_1.mp3";
		}
		return music_path;
	}
	
	//欧洲西班牙复习1基本音乐路径
	public static String getEp2_1Mp3(int index){
		String music_path="";
		if(index == 0){
			music_path = ConstantEp.path_reading01+"flash_b01_bag.mp3";
		}else if (index == 1) {
			music_path = ConstantEp.path_reading01+"flash_b02_banana.mp3";
		}else if (index == 2) {
			music_path = ConstantEp.path_reading01+"flash_b03_big.mp3";
		}else if (index == 3) {
			music_path = ConstantEp.path_reading01+"flash_b04_bus.mp3";
		}else if (index == 4) {
			music_path = ConstantEp.path_reading01+BaseCommon.getMp3Path("2_1_prologue");
		}else if (index == 5) {
			music_path = ConstantEp.path_reading01+"ep_bg2_1.mp3";
		}
		return music_path;
	}
	
	//欧洲意大利复习1基本音乐路径
	public static String getEp5_1Mp3(int index){
		String music_path="";
		if(index == 0){
			music_path = ConstantEp.path_reading01+"flash_r03_rabbit1.mp3";
		}else if (index == 1) {
			music_path = ConstantEp.path_reading01+"flash_r09_rat2.mp3";
		}else if (index == 2) {
			music_path = ConstantEp.path_reading01+"flash_r07_rock3.mp3";
		}else if (index == 3) {
			music_path = ConstantEp.path_reading01+"flash_r05_rose4.mp3";
		}else if (index == 4) {
			music_path = ConstantEp.path_reading01+BaseCommon.getMp3Path("5_1_prologue");
		}else if (index == 5) {
			music_path = ConstantEp.path_reading01+"ep_bg5_1.mp3";
		}
		return music_path;
	}
	
	//欧洲英国复习1基本音乐路径
	public static String getEp4_1Mp3(int index){
		String music_path="";
		if(index == 0){
			music_path = ConstantEp.path_reading01+"flash_c03_cake.mp3";
		}else if (index == 1) {
			music_path = ConstantEp.path_reading01+"flash_c11_car.mp3";
		}else if (index == 2) {
			music_path = ConstantEp.path_reading01+"flash_c05_cat.mp3";
		}else if (index == 3) {
			music_path = ConstantEp.path_reading01+"flash_c09_clock.mp3";
		}else if (index == 4) {
			music_path = ConstantEp.path_reading01+BaseCommon.getMp3Path("4_1_prologue");
		}else if (index == 5) {
			music_path = ConstantEp.path_reading01+"ep_bg4_1.mp3";
		}
		return music_path;
	}
	
	//可以共用的基本配音
		public MediaPlayer getPeiyinMp3(Context mContext, int index) {
			MediaPlayer mMediaPlayerGj = null;
			if (index == 0) {//跟我读 提示音
			} else if (index == 1) {// 闯关点击提示
			} else if (index == 3) { // 答对了
				mMediaPlayerGj = MediaPlayer.create(mContext,
						BaseCommon.getMp3Id(mContext, "good_" + randData1_4()));
			} else if (index == 4) { // 答错了
				mMediaPlayerGj = MediaPlayer.create(mContext,
						BaseCommon.getMp3Id(mContext, "error_sorry"));
			} else if (index == 5) { // 再试一次
				mMediaPlayerGj = MediaPlayer.create(mContext,
						BaseCommon.getMp3Id(mContext, "error_try_again"));
			}  else if (index == 6) {
				mMediaPlayerGj = MediaPlayer.create(mContext,
						BaseCommon.getMp3Id(mContext, "ppg_click_the_bubble"));
			} else if (index == 7) { //买服饰，钱不够
				mMediaPlayerGj = MediaPlayer.create(mContext,
						BaseCommon.getMp3Id2(mContext, "not_enough_money"));
			} else if (index == 8) { //买服饰，钱够了
				mMediaPlayerGj = MediaPlayer.create(mContext,
						BaseCommon.getMp3Id2(mContext, "has_enough_money"));
			} else if (index == 9) { //买服饰，钱够了
//				mMediaPlayerGj = MediaPlayer.create(mContext,
//						R.raw.bg_music_syj);
			}
			return mMediaPlayerGj;
		}
	
	public static MediaPlayer getCommonMedia(Context mContext, int index) {
		MediaPlayer mMediaPlayerGj = null;
		if (index == 0) {//跟我读 提示音
		} else if (index == 1) {// 闯关点击泡泡提示

		} else if (index == 3) { // 答对了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "good_" + randData1_4()));
		} else if (index == 4) { // 答错了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "error_sorry"));
		} else if (index == 5) { // 再试一次
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "error_try_again"));
		}  else if (index == 6) {
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "ppg_click_the_bubble"));
		}else if(index == 7){	//点点这里
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				mMediaPlayerGj = MediaPlayer.create(mContext,
						R.raw.en_clickhere);
			} else if (Constant.languageType == Constant.LANGUAGE_CHINESE) {
				mMediaPlayerGj = MediaPlayer.create(mContext,
						R.raw.ch_clickhere);
			}
		}else if(index == 8){    //读看到的单词
			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
				mMediaPlayerGj = MediaPlayer.create(mContext,
						R.raw.read_look_word_en);
			} else if (Constant.languageType == Constant.LANGUAGE_CHINESE) {
				mMediaPlayerGj = MediaPlayer.create(mContext,
						R.raw.read_look_word_ch);
			}
		} 
		else if (index == 9) { // 错误太多，重学习——继续努力哦的提示
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "music_fail_note"));
		}else if(index == 10){
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_hear"));
			
		}else if (index == 11) { // 答错了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id4(mContext, "error_sorry"));
		}else if (index == 12) { // sayit
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "en_sayit"));
		}
		
		else if (index == 13) { // 选择你听到的字母
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "select_hear_zm"));
		}else if (index == 14) { 
			
		}
		
		else if (index == 15) { // 答对了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getFuxiGoodMp3Id(mContext));
		} else if (index == 16) { // 答错了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getFuxiErrorMp3Id(mContext));
		}
		
		else if (index == 17) { // 继续加油
//			mMediaPlayerGj = MediaPlayer.create(mContext,
//					R.raw.ch_guzhang0);
		}
		else if (index == 18) { // 了不起
//			mMediaPlayerGj = MediaPlayer.create(mContext,
//					R.raw.ch_guzhang1);
		}
		else if (index == 19) { // 太棒了
//			mMediaPlayerGj = MediaPlayer.create(mContext,
//					R.raw.ch_guzhang2);
		}

		return mMediaPlayerGj;
	}
	
	/*public String getCommonMediaPath(Context mContext, int index){
		String music_path = "";
		if (index == 1){  //点点这里
			music_path = Constant.path_raz02 + BaseCommon.getMp3Path("click_hear");
		}
		return music_path;
	}*/
	//游戏14 基本音乐路径
//	public static String getGame14Mp3(int index) {
//		String music_path = "";
//				
//		if (index == 0) {		//背景音乐
//		} else if (index == 1) {//游戏开始
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("city_start");
//		} else if (index == 2) {// city
//			music_path = ConstantEbook.path_reaEbook01 + "city_1.mp3";
//		}  else if (index == 3) {//car
//			music_path = ConstantEbook.path_reaEbook01 + "city_2.mp3";
//		} else if (index == 4) {//store
//			music_path = ConstantEbook.path_reaEbook01 + "city_3.mp3";
//		} else if (index == 5) {//bus
//			music_path = ConstantEbook.path_reaEbook01 + "city_4.mp3";
//		} else if (index == 6) {//people
//			music_path = ConstantEbook.path_reaEbook01 + "city_5.mp3";
//		} else if (index == 7) {//park
//			music_path = ConstantEbook.path_reaEbook01 + "city_6.mp3";
//		} else if (index == 8) {//big building
//			music_path = ConstantEbook.path_reaEbook01 + "city_7.mp3";
//		} else if (index == 9) {//noise
//			music_path = ConstantEbook.path_reaEbook01 + "city_8.mp3";
//		}
//		return music_path;
//	}
//	public static String getGame003Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg003_music_warning");
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg003_music_flowers.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg003_music_flowers_yes.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg003_music_summer.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_reaEbook01+"eg003_music_summer_no.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_reaEbook01+"eg003_music_sunshine.mp3";
//		}else if (index == 6) {
//			music_path = ConstantEbook.path_reaEbook01+"eg003_music_sunshine_no.mp3";
//		}else if (index == 7) {
//			music_path = ConstantEbook.path_reaEbook01+"eg003_music_swimsuit.mp3";
//		}else if (index == 8) {
//			music_path = ConstantEbook.path_reaEbook01+"eg003_music_swimsuit_no.mp3";
//		}
//		return music_path;
//	}
//	public static String getGame005Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg005_music_warning");
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg005_music_coat.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg005_music_sled.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg005_music_snow.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_reaEbook01+"eg005_music_snowman.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_reaEbook01+"eg005_music_mittens.mp3";
//		}
//		return music_path;
//	}
//	public static String getGame007Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg007_music_warning");
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg007_music_coral.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg007_music_crab.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg007_music_seaweed.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_reaEbook01+"eg007_music_starfish.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_reaEbook01+"eg007_music_fish.mp3";
//		}
//		return music_path;
//	}
//	public static String getGame009Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg009_music_warning");
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg009_music_red.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg009_music_yellow.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg009_music_pink.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_reaEbook01+"eg009_music_purple.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_reaEbook01+"eg009_music_orange.mp3";
//		}else if (index == 6) {
//			music_path = ConstantEbook.path_reaEbook01+"eg009_music_green.mp3";
//		}else if (index == 7) {
//			music_path = ConstantEbook.path_reaEbook01+"eg009_music_blue.mp3";
//		}
//		return music_path;
//	}
//	public static String getGame010Mp3(int index){
//			String music_path="";
//			if(index == 0){
//				music_path = ConstantEbook.path_reaEbook01+"eg010_music_corn.mp3";
//			}else if (index == 1) {
//				music_path = ConstantEbook.path_reaEbook01+"eg010_music_jacket.mp3";
//			}else if (index == 2) {
//				music_path = ConstantEbook.path_reaEbook01+"eg010_music_luge.mp3";
//			}else if (index == 3) {
//				music_path = ConstantEbook.path_reaEbook01+"eg010_music_pumpkin.mp3";
//			}else if (index == 4) {
//				music_path = ConstantEbook.path_reaEbook01+"eg010_music_rake.mp3";
//			}else if(index==5){
//				music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg010_music_warning");
//			}
//			return music_path;
//		}
//	public static String getGame011Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg011_music_warning");
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg011_music_dog.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg011_music_milk.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg011_music_carrot.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_reaEbook01+"eg011_music_cookie.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_reaEbook01+"eg011_music_napkin.mp3";
//		}else if (index == 6) {
//			music_path = ConstantEbook.path_reaEbook01+"eg011_music_school.mp3";
//		}
//		return music_path;
//	}
//	public static String getGame012Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01+"eg012_music_ball.mp3";
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg012_music_bike.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg012_music_birds.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg012_music_bunny.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_reaEbook01+"eg012_music_rain.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_reaEbook01+"eg012_music_rainbow.mp3";
//		}else if (index == 6) {
//			music_path = ConstantEbook.path_reaEbook01+"eg012_music_spring.mp3";
//		}else if(index==7){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg012_music_warning");
//		}
//		return music_path;
//	}
//	public static String getGame013Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg013_music_warning");
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg013_music_beach.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg013_music_clif.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg013_music_lighthouse.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_reaEbook01+"eg013_music_ocean.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_reaEbook01+"eg013_music_rock.mp3";
//		}else if (index == 6) {
//			music_path = ConstantEbook.path_reaEbook01+"eg013_nusic_bird.mp3";
//		}
//		return music_path;
//	}
//	public static String getGame015Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg015_music_warning");
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg015_music_dog.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg015_music_clothes.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg015_music_grandparents.mp3";
//		}
//		return music_path;
//	}
//	public static String getGame017Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_reaEbook01+"eg017_music_cow.mp3";
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_reaEbook01+"eg017_music_duck.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_reaEbook01+"eg017_music_dog.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_reaEbook01+"eg017_music_sheep.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_reaEbook01+"eg017_music_pig.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_reaEbook01+"eg017_music_chicken.mp3";
//		}else if(index==6){
//			music_path = ConstantEbook.path_reaEbook01 + BaseCommon.getMp3Path("eg017_music_warning");
//		}
//		return music_path;
//	}
//	
//	
//	public static String getSightwords01Mp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_sightwords01 + "go.mp3";
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_sightwords01+"thecatsgo.mp3";
//		}else if (index == 2) {
//			music_path = ConstantEbook.path_sightwords01+"a.mp3";
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_sightwords01+"apig.mp3";
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_sightwords01+"the.mp3";
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_sightwords01+"theboy.mp3";
//		}else if (index == 6) {
//			music_path = ConstantEbook.path_sightwords01+"big.mp3";
//		}else if (index == 7) {
//			music_path = ConstantEbook.path_sightwords01+"abigball.mp3";
//		}
//		else if (index == 8) {
//			music_path = ConstantEbook.path_sightwords01+"red.mp3";
//		}else if (index == 9) {
//			music_path = ConstantEbook.path_sightwords01+"aredapple.mp3";
//		}
//		else if (index == 10) {
//			music_path = ConstantEbook.path_sightwords01+"it.mp3";
//		}else if (index == 11) {
//			music_path = ConstantEbook.path_sightwords01+"itisacat.mp3";
//		}else if (index == 12) {
//			music_path = ConstantEbook.path_sightwords01+"my.mp3";
//		}else if (index == 13) {
//			music_path = ConstantEbook.path_sightwords01+"mycookies.mp3";
//		}else if (index == 14) {
//			music_path = ConstantEbook.path_sightwords01+"the.mp3";
//		}else if (index == 15) {
//			music_path = ConstantEbook.path_sightwords01+"thebird.mp3";
//		}else if (index == 16) {
//			music_path = ConstantEbook.path_sightwords01+"car.mp3";
//		}else if (index == 17) {
//			music_path = ConstantEbook.path_sightwords01+"thecar.mp3";
//		}
//		else if (index == 18) {
//			music_path = ConstantEbook.path_sightwords01+"dog.mp3";
//		}else if (index == 19) {
//			music_path = ConstantEbook.path_sightwords01+"itisadog.mp3";
//		}else if (index == 20) {
//			music_path = ConstantEbook.path_sightwords01+"we.mp3";
//		}else if (index == 21) {
//			music_path = ConstantEbook.path_sightwords01+"weareboys.mp3";
//		}else if (index == 22) {
//			music_path = ConstantEbook.path_sightwords01+"duck.mp3";
//		}else if (index == 23) {
//			music_path = ConstantEbook.path_sightwords01+"itisaduck.mp3";
//		}else if (index == 24) {
//			music_path = ConstantEbook.path_sightwords01+"in.mp3";
//		}else if (index == 25) {
//			music_path = ConstantEbook.path_sightwords01+"intheboat.mp3";
//		}else if (index == 26) {
//			music_path = ConstantEbook.path_sightwords01+"out.mp3";
//		}else if (index == 27) {
//			music_path = ConstantEbook.path_sightwords01+"outoftheboat.mp3";
//		}else if (index == 28) {
//			music_path = ConstantEbook.path_sightwords01+"cat.mp3";
//		}else if (index == 29) {
//			music_path = ConstantEbook.path_sightwords01+"aboy.mp3";
//		}else if (index == 30) {
//			music_path = ConstantEbook.path_sightwords01+"itisatree.mp3";
//		}
//		
//		return music_path;
//	}
	
	
	public static String getLettersGameMp3(int index){
		String music_path="";
		if(index == 0){
			music_path = ConstantLetters.path_letters + "flash_a_sound.mp3";
		}else if (index == 1) {
			music_path = ConstantLetters.path_letters + "flash_b_sound.mp3";
		}else if (index == 2) {
			music_path = ConstantLetters.path_letters + "flash_c_sound.mp3";
		}else if (index == 3) {
			music_path = ConstantLetters.path_letters + "flash_d_sound.mp3";
		}else if (index == 4) {
			music_path = ConstantLetters.path_letters + "flash_e_sound.mp3";
		}else if (index == 5) {
			music_path = ConstantLetters.path_letters + "flash_f_sound.mp3";
		}else if (index == 6) {
			music_path = ConstantLetters.path_letters + "flash_g_sound.mp3";
		}else if (index == 7) {
			music_path = ConstantLetters.path_letters + "flash_h_sound.mp3";
		}else if (index == 8) {
			music_path = ConstantLetters.path_letters + "flash_i_sound.mp3";
		}else if (index == 9) {
			music_path = ConstantLetters.path_letters + "flash_j_sound.mp3";
		}else if (index == 10) {
			music_path = ConstantLetters.path_letters + "flash_k_sound.mp3";
		}else if (index == 11) {
			music_path = ConstantLetters.path_letters + "flash_l_sound.mp3";
		}else if (index == 12) {
			music_path = ConstantLetters.path_letters + "flash_m_sound.mp3";
		}else if (index == 13) {
			music_path = ConstantLetters.path_letters + "flash_n_sound.mp3";
		}else if (index == 14) {
			music_path = ConstantLetters.path_letters + "flash_o_sound.mp3";
		}else if (index == 15) {
			music_path = ConstantLetters.path_letters + "flash_p_sound.mp3";
		}else if (index == 16) {
			music_path = ConstantLetters.path_letters + "flash_q_sound.mp3";
		}else if (index == 17) {
			music_path = ConstantLetters.path_letters + "flash_r_sound.mp3";
		}else if (index == 18) {
			music_path = ConstantLetters.path_letters + "flash_s_sound.mp3";
		}else if (index == 19) {
			music_path = ConstantLetters.path_letters + "flash_t_sound.mp3";
		}else if (index == 20) {
			music_path = ConstantLetters.path_letters + "flash_u_sound.mp3";
		}else if (index == 21) {
			music_path = ConstantLetters.path_letters + "flash_v_sound.mp3";
		}else if (index == 22) {
			music_path = ConstantLetters.path_letters + "flash_w_sound.mp3";
		}else if (index == 23) {
			music_path = ConstantLetters.path_letters + "flash_x_sound.mp3";
		}else if (index == 24) {
			music_path = ConstantLetters.path_letters + "flash_y_sound.mp3";
		}else if (index == 25) {
			music_path = ConstantLetters.path_letters + "flash_z_sound.mp3";
		}
		//3个音效
		else if (index == 26) {
			music_path = ConstantLetters.path_letters + "music_dapao.mp3";
		}else if (index == 27) {
			music_path = ConstantLetters.path_letters + "music_jinbi.mp3";
		}else if (index == 28) {
			music_path = ConstantLetters.path_letters + "music_huanhu.mp3";
		}
		return music_path;
	}
	
	//高频字提示
//	public static String getSightwordsTishiMp3(int index){
//		String music_path="";
//		if(index == 0){
//			music_path = ConstantEbook.path_sightwords01+BaseCommon.getMp3Path("clickthewordyouhear");
//		}else if (index == 1) {
//			music_path = ConstantEbook.path_sightwords01+BaseCommon.getMp3Path("findandclickon");
//		}
//		else if (index == 2) {
//			music_path = ConstantEbook.path_sightwords01 + "tishi_clickhere.mp3"; //BaseCommon.getMp3Path("findandclickon");
//		}else if (index == 3) {
//			music_path = ConstantEbook.path_sightwords01 + "tishi_fillintheblank.mp3"; //BaseCommon.getMp3Path("findandclickon");
//		}else if (index == 4) {
//			music_path = ConstantEbook.path_sightwords01 + "tishi_findmatchingword.mp3"; //BaseCommon.getMp3Path("findandclickon");
//		}else if (index == 5) {
//			music_path = ConstantEbook.path_sightwords01 + "tishi_peidui.mp3"; //BaseCommon.getMp3Path("findandclickon");
//		}else if (index == 6) {
//			music_path = ConstantEbook.path_sightwords01 + "tishi_pleasefindcorrect.mp3"; //BaseCommon.getMp3Path("findandclickon");
//		}/*else if (index == 7) {
//			music_path = ConstantEbook.path_sightwords01 + ""; //BaseCommon.getMp3Path("findandclickon");
//		}else if (index == 8) {
//			music_path = ConstantEbook.path_sightwords01 + ""; //BaseCommon.getMp3Path("findandclickon");
//		}*/
//		return music_path;
//	}
		
	//魔法游戏提示
	public static String getMagicGameTishiMp3(int index){
		String music_path="", path = Constant.sdcard_path + "/raz_english/magic_game/";
		if(index == 0){
			music_path = path+"cannot_playgame.mp3";
		}else if (index == 1) {
			
		}
		return music_path;
	}
	
	/*-----------共用-----------*/
	//鼓掌
	public static void playGuzhang(Context context){
		if(Constant.languageType == Constant.LANGUAGE_CHINESE){
			MediaCommon.PlayMusic(MediaPlayer.create(context,BaseCommon.getMp3Id2(context, "guzhang"+BaseCommon.randData())));
		}else{
			MediaCommon.PlayMusic(MediaPlayer.create(context,BaseCommon.getMp3Id2(context, "guzhang"+BaseCommon.randData4())));
		}
	}
	//字母游戏配音
	public static void playGuli(Context context, int i){
		mMediaPlayer = getCommonMedia(context,i);
		PlayMusic2();
	}
	
	//跟我读
	public static void playReadAfterMe(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,0));
		mMediaPlayer = getCommonMedia(context,0);
		PlayMusic2();
	}
	
	//点点这里
	public static void playClickHere(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,7));
		mMediaPlayer = getCommonMedia(context,7);
		PlayMusic2();
	}
	
	//sayit
//	public static void playSayit(Context context){
//		/*MediaCommon.PlayMusic(MediaPlayer.create(context,
//				R.raw.en_sayit));*/
//		mMediaPlayer = MediaPlayer.create(context,
//				R.raw.en_sayit);
//		PlayMusic2();
//	}
	
	//读看到的单词
	public static void playLookword(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,8));
		mMediaPlayer = getCommonMedia(context,8);
		PlayMusic2();
	}
	//答对了
	public static void playAnswerGood(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,3));
		mMediaPlayer = getCommonMedia(context,3);
		PlayMusic2();
	}
	//答错了
	public static void playAnswerError(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,4));
		mMediaPlayer = getCommonMedia(context,4);
		PlayMusic2();
	}
	//再试一次吧
	public static void playTryAgain(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,5));
		mMediaPlayer = getCommonMedia(context,5);
		PlayMusic2();
	}
	//错误太多，重学习——继续努力哦的提示
	public static void playReplay(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,9));
		mMediaPlayer = getCommonMedia(context,9);
		PlayMusic2();
	}
	//quiz闯关 根据读音，点击对应的字母————————暂时不能共用
	public static void playClickWordByHear(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,10));
		mMediaPlayer = getCommonMedia(context,10);
		PlayMusic2();
	}
	
	//答错了
	public static void playSayitError(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,11));
		mMediaPlayer = getCommonMedia(context,11);
		PlayMusic2();
	}
	
	
	//选择你听到的字母
	public static void playHearZm(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,13));
		mMediaPlayer = getCommonMedia(context,13);
		PlayMusic2();
	}
	//五大洲 选对了
	public static void playFuxiGood(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,15));
		mMediaPlayer = getCommonMedia(context,15);
		PlayMusic2();
	}
	//五大洲 选错了
	public static void playFuxiError(Context context){
//		MediaCommon.PlayMusic(getCommonMedia(context,16));
		mMediaPlayer = getCommonMedia(context,16);
		PlayMusic2();
	}

	// 选对了
	public static void playEgGood(Context context){
		mMediaPlayer = getEgMedia(context, 0);
		PlayMusic2();
	}

	// 选错了
	public static void playEgError(Context context){
		mMediaPlayer = getEgMedia(context, 1);
		PlayMusic2();
	}

	public static MediaPlayer getEgMedia(Context mContext, int index) {
		MediaPlayer mMediaPlayerGj = null;
		if (index == 0) { // 答对了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getEgGoodMp3Id(mContext));
		} else if (index == 1) { // 答错了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getEgErrorMp3Id(mContext));
		}
		return mMediaPlayerGj;
	}
	
	
	public static void pauseMediaplay(){
		try{
			if(mMediaPlayer != null){
				mMediaPlayer.pause();
			}
		}catch(Exception e){
			
		}
		
	}

}
