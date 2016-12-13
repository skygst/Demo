package com.gst.move.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.data.MusicPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.CommonYuyin;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Level3Activity  extends BaseActivity implements OnClickListener {
	/*-----------------基础----------------------*/
	private Context mContext;
	private float scaleQPW = 1.0f,scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer = null,mMediaPlayer2 = null,mMediaPlayer3=null; 
	private boolean haveClicked = false;
	private MyVideoView video;
	private String path;
	private VideoBiz biz;
	private boolean isCycle = false;
	private int startTime = 0;
	private int endTime = 0;
	private int clickNum = 0;//用于判断是否是新的视频
	private boolean isClicked = true;
	private int currentIndex = 0;
	private int xoffset = 0,yoffset = 0;
	private float suoxiao = 1.0f;
	
	private int preservation=0;   //按home键保存播放帧数
	
	/*-----------------视图----------------------*/
	private ImageView ivBack, btn_shou, bt_after;
	
	/*-----------------语音识别----------------------*/
	private String thisChar = "";	//当前语音识别的字母
	private boolean mInitialized = false;
	private final String TAG = "Level3Activity";
	Thread recThread;
	private boolean mIsVrRuning = false;	//语音识别是否打开了
	
	
	/*-----------------左侧4个泡泡专用----------------------*/ 
	private boolean have4Treasure = false;
	private ImageView iv1,iv2,iv3,iv4,iv1_11,iv1_21,iv1_31,iv1_41,paopao_selected;
	private int clickPaoPao = 0;		//泡泡被点击的次数
	private int iPpCircle[] = {17,23,29,35};
	private boolean shouIsPaopao = true;
	private int papapStartIndex = 12;
	private ImageView ib_next;
	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_03);

		initializationData();
		offset_time = Tools.delayTime();
		setView();
	}
	
    
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
	
	/*-----------------初始化----------------------*/
	/*数据初始化*/
	private void initializationData() {		
		mContext = Level3Activity.this;
		scaleQPW = (width/1920.0f);
		scaleQPH = (height/1080.0f);

		
		biz = new VideoBiz();
		path = Constant.path_raz01 + "level03_bengchuang.mp4/";
		currentIndex = 0;//5
		startTime = Constant.timeLevel03[currentIndex][0];
		endTime = Constant.timeLevel03[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
	}
	/*视图初始化*/
	private void setView() {	
		video = (MyVideoView) findViewById(R.id.video_play);
		ivBack = (ImageView)findViewById(R.id.iv_back);
		btn_shou = (ImageView)findViewById(R.id.btn_shou);
		bt_after = (ImageView)findViewById(R.id.bt_after);
		bt_after.setOnClickListener(this);
		ivBack.setOnClickListener(this);
		btn_shou.setOnClickListener(this);
		biz.setViewPositionNoSuoxiao(ivBack, 6, FixedPosition.level_common_position, scaleQPW ,scaleQPH);
		btn_shou.setVisibility(View.GONE);
		/*----------泡泡专用  不共用----------*/
		setPaopaoView();	
		
		//背景音乐
//		PlayMusic();
		//playMusic(MediaCommon.getLevel03newMp3(0));
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz01 + "bgmusic_level03.mp3");
		videoCycle(clickNum);
		biz.playVideo(video, path);		
//		CommonUtil.downLoadRes(mContext, 0, 4, Constant.download_quiz_01, commonPath, "raz01");
	}

	
	/*初始化泡泡view*/	
	private void setPaopaoView(){
		iv1_11 = (ImageView) findViewById(R.id.iv1_11);
		iv1_21 = (ImageView) findViewById(R.id.iv1_21);
		iv1_31 = (ImageView) findViewById(R.id.iv1_31);
		iv1_41 = (ImageView) findViewById(R.id.iv1_41);
		iv1_11.setOnClickListener(this);
		iv1_21.setOnClickListener(this);
		iv1_31.setOnClickListener(this);
		iv1_41.setOnClickListener(this);
		iv1 = (ImageView) findViewById(R.id.iv1);
		iv2 = (ImageView) findViewById(R.id.iv2);
		iv3 = (ImageView) findViewById(R.id.iv3);
		iv4 = (ImageView) findViewById(R.id.iv4);
		paopao_selected = (ImageView) findViewById(R.id.paopao_selected);
		paopao_selected.setVisibility(View.INVISIBLE);
		
		setQiPaoVisible(false);
		
		setPaopao();
		
	}
	
	private void setQiPaoVisible(boolean isvisible){
		if(isvisible){
			iv1.setVisibility(View.VISIBLE);
			iv2.setVisibility(View.VISIBLE);
			iv3.setVisibility(View.VISIBLE);
			iv4.setVisibility(View.VISIBLE);
			
			CommonAnimation.setQipaoAnimation(iv1);
			CommonAnimation.setQipaoAnimation(iv2);
			CommonAnimation.setQipaoAnimation(iv3);
			CommonAnimation.setQipaoAnimation(iv4);
			
		}else{
			iv1.setVisibility(View.INVISIBLE);
			iv2.setVisibility(View.INVISIBLE);
			iv3.setVisibility(View.INVISIBLE);
			iv4.setVisibility(View.INVISIBLE);
			CommonAnimation.stopQipiaoAnimation(iv1);
			CommonAnimation.stopQipiaoAnimation(iv2);
			CommonAnimation.stopQipiaoAnimation(iv3);
			CommonAnimation.stopQipiaoAnimation(iv4);
			iv1_11.setVisibility(View.GONE);
			iv1_21.setVisibility(View.GONE);
			iv1_31.setVisibility(View.GONE);
			iv1_41.setVisibility(View.GONE);
			
			paopao_selected.setVisibility(View.INVISIBLE);
		}
	}
	
	/*设置泡泡view*/	
	private void setPaopao(){
		
		setCommonPosition(iv1_11,0);
		setCommonPosition(iv1_21,1);
		setCommonPosition(iv1_31,2);
		setCommonPosition(iv1_41,3);
		
		setCommonPosition(iv1,0);
		setCommonPosition(iv2,1);
		setCommonPosition(iv3,2);
		setCommonPosition(iv4,3);
		iv1.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao"));
		iv2.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao"));
		iv3.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao"));
		iv4.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao"));
		paopao_selected.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao_selected"));
		iv1_11.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "ic_pan"));
		iv1_21.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "ic_pizza"));
		iv1_31.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "ic_pig"));
		iv1_41.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "ic_pen"));
		/*setSelectedPaopao(0);		
		paopao_selected.setVisibility(View.INVISIBLE);*/
	}
	
	/*显示泡泡内的宝物*/	
	private void visibleScripte(int index){
		if(index >= iPpCircle[0]){
			iv1_11.setVisibility(View.VISIBLE);
		}
		if(index >= iPpCircle[1]){
			iv1_21.setVisibility(View.VISIBLE);
		}
		if(index >= iPpCircle[2]){
			iv1_31.setVisibility(View.VISIBLE);
		}
		if(index >= iPpCircle[3]){
			iv1_41.setVisibility(View.VISIBLE);
			shouIsPaopao = true;
		}
	}
	
	/*设置泡泡view------0、1、2、3*/	
	private void setSelectedPaopao(int i){
		paopao_selected.setVisibility(View.VISIBLE);
		setCommonPosition(paopao_selected,(i+4));		
	}
	
	
	/*显示bt_after动画和手动画*/
	private void stopBtafterAndShou(){
		stopBtnAfter();
		stopBtnAndShou();
	}
	
	
	/*消失btn_after*/
	private void stopBtnAfter(){
		bt_after.setVisibility(View.GONE);
		VideoBiz.stopAfterAnimation(bt_after);
	}	
	
	
	
	
	/*设置泡泡的位置*/	
	private void setCommonPosition(ImageView iv,int i){
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(FixedPosition.paopao_position_03new[i][0],FixedPosition.paopao_position_03new[i][1], FixedPosition.paopao_position_03new[i][2], FixedPosition.paopao_position_03new[i][3], scaleQPW ,scaleQPH,xoffset,yoffset,suoxiao));						
	}
	
	
	/*显示btn_after通过位置*/
	private void showBtnAfterByPosition(){
		
		bt_after.setVisibility(View.VISIBLE);
		setCommonPosition(bt_after,8);
		VideoBiz.startAfterAnimation(bt_after);
		
		haveClicked = false;		//可以点击了
		
		
	}

	
	/*显示bt_after动画和手动画*/
	private void showBtnafterAndShouByCurrentIndex(){
		/*-------此处的 数字 不共用 需要根据实际修改-------*/
		if(currentIndex == 36){
			showBtnAfterByPosition();
			if(clickPaoPao >= 4&&(currentIndex==iPpCircle[0]+1||currentIndex==iPpCircle[1]+1||currentIndex==iPpCircle[2]+1||currentIndex==iPpCircle[3]+1)){
				showBtnAndShouByPosition(4);
				if(clickPaoPao >= 4){
					shouIsPaopao = false;
				}
			}
		}
		
		haveClicked = false;		//可以点击了
	}
	
	
	/*显示手动画通过位置*/
	private void showBtnAndShouByPosition(int i){
		if(i != -1){
			btn_shou.setVisibility(View.VISIBLE);
			setViewPosition(btn_shou,i);
			CommonAnimation.startShouAnimation(btn_shou);
			
			haveClicked = false;		//可以点击了
		}
		
	}
	
	/*设置视图的位置*/	
	private void setViewPosition(ImageView iv,int i){
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(FixedPosition.level03new_position[i][0],FixedPosition.level03new_position[i][1], FixedPosition.level03new_position[i][2], FixedPosition.level03new_position[i][3], scaleQPW ,scaleQPH,xoffset,yoffset,suoxiao));						
	}
	/*显示手动画*/
	private void showBtnAndShou(){
		int i = FixedPosition.getPositionIndex(FixedPosition.level03new_index_to_position, currentIndex);
		if(i != -1){
			Log.d("TESTQQ", "显示手指："+i);
			btn_shou.setVisibility(View.VISIBLE);
			setViewPosition(btn_shou,i);
			CommonAnimation.startShouAnimation(btn_shou);
			
			haveClicked = false;		//可以点击了
		}
		
	}
	
	/*显示手动画不共用*/
	private void showBtnAndShou2(int i){		
			Log.d("TESTQQ", "显示手指："+i);
			btn_shou.setVisibility(View.VISIBLE);
			setViewPosition(btn_shou,i);
			CommonAnimation.startShouAnimation(btn_shou);
			
			haveClicked = false;		//可以点击了
		
		
	}
	/*消失手动画*/
	private void stopBtnAndShou(){
		btn_shou.setVisibility(View.GONE);
		CommonAnimation.stopShouAnimation(btn_shou);
	}
	
//	/*----------------------view 不能共用部分----------------------------*/

	

	
	/*-----------------视频播放及节点事件处理----------------------*/
	// 循环播放------------------------------------
	private void videoCycle(final int num) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isCycle = true;
				setCirclePlay(num);
			}
		}).start();
	}
	
	private void setCirclePlay(int num) {
		System.out.println("currentIndex:"+currentIndex+"-------startTime:"+startTime+"--endTime："+endTime);
		Log.d("TESTQQ", "currentIndex:"+currentIndex+"-------startTime:"+startTime+"--endTime："+endTime);
		int currentPos = video.getCurrentPosition();
		
		//playClickMusic();
		
		int tem_num = num;
		do {
			try {
				if (currentIndex <= 260) {
					Thread.sleep(50);
				}
				if(tem_num != clickNum) break;
				
				if (!isCycle || video == null)
					break;
				currentPos = video.getCurrentPosition(); // 空指针null

				if (Constant.timeLevel03[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
					/*-------------该判定泡泡专用 不共用--------------*/
					isClicked=false;
					handler.sendMessage(handler.obtainMessage(0, currentIndex));

				}

				if (currentPos >= endTime) {
					break;
				}
			} catch (Exception e) {
				
			}
		} while (currentPos < endTime);
		try {
			if (!video.isPlaying()) {// 如果不在播放状态，则停止更新
				return;
			}
			
			if(!have4Treasure && (currentIndex == iPpCircle[0] || currentIndex == iPpCircle[1] || currentIndex == iPpCircle[2] || currentIndex == iPpCircle[3])){
				int index = currentIndex;
				handler.sendMessage(handler.obtainMessage(1,index));
			}
			if(!have4Treasure&&currentIndex == iPpCircle[3]){
				have4Treasure=true;
			}	
			
			if(tem_num == clickNum){

				if(Constant.timeLevel03[currentIndex][4] == 0){
					currentIndex = Constant.timeLevel03[currentIndex][3];
				}
				
				
				startTime = Constant.timeLevel03[currentIndex][0] - offset_time;
				endTime = Constant.timeLevel03[currentIndex][1] - offset_time;
				handler.sendMessage(handler.obtainMessage(2,tem_num));
					
			}
			
		} catch (Exception e) {
			
		}
	}
	
	
	
	/*----------------------声音  共用部分----------------------------*/
	
	/*播放click事件对应的声音*/
	private void playClickMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.level03new_click_music_position, currentIndex);
		if(i != -1){
			PlayMusic3(MediaCommon.getLevel03newMp3(i,mContext));
		}
	}
	/*播放currentIndex对应的声音*/
	private void playCurrentIndexMusic(){		
		final int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.level03new_music_position, currentIndex);
		if(i != -1){
			Log.d("TESTQQ", "currentIndex对应的声音:"+currentIndex);
			mMediaPlayer3 = MediaPlayer.create(mContext, BaseCommon.getMp3Id2(mContext, "click_surprise"));
//			String name = "ch_";
//			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
//				name = "en_";
//			}
//			playMusicOnly(Constant.path_raz01 + name + "click_surprise.mp3");
			PlayMusic3(mMediaPlayer3);
		}
	}
	/*播放跟我读*/
	private void playReadAfterme(){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.level03new_readafterme, currentIndex);
		if(i != -1){
			MediaCommon.playReadAfterMe(mContext);
		}
	}
	/*点点这里*/
	private void playClickHere(int index){		
		Log.d("WWWW", "点点这里DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD:"+index);
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.level03new_clickhere, index);
		if(i != -1){			
			MediaCommon.playClickHere(mContext);
		}
	}
	
	//旧level03 背景音乐
//	private void PlayMusic() {  
//		mMediaPlayer = MediaPlayer.create(this,R.raw.bgmusic_level03);
//		mMediaPlayer.setLooping(true);  
//		
//		try { 
//			mMediaPlayer.prepare();
//        } catch (Exception ex) {  
//        }  
//    	mMediaPlayer.start();// 开始播放 
//    }  
	
	private void PlayMusic3(MediaPlayer mMMediaPlayer3) {  		
		try { 
			mMMediaPlayer3.setLooping(false);
			mMMediaPlayer3.prepare();
        } catch (Exception ex) {  
        }  
		try {
			mMMediaPlayer3.start();// 开始播放 
			mMMediaPlayer3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					// 播放结束后的动作
//					finish();
					if(mp != null){
						mp.release();
						mp=null;
					}
				}
			});
		}catch (Exception ex) {  
        } 
    } 
	
	/*声音循环播放*/
	public void playMusic(String path)   
    {      	
        try   
        {   
        	mMediaPlayer = new MediaPlayer();  
            mMediaPlayer.reset();   
            mMediaPlayer.setDataSource(path);    /* 设置要播放的文件的路径 */   
            mMediaPlayer.setLooping(true);	//设置是否循环播放
            mMediaPlayer.prepare();   
            mMediaPlayer.start();   
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer arg0)   
                {   
                }   
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        } 
    }  
 	
	/*声音 不循环 播放*/
	public void playMusicOnly(String path)   
    {      
        try   
        {   
        	mMediaPlayer2 = new MediaPlayer(); 
            mMediaPlayer2.reset();  
            mMediaPlayer2.setDataSource(path);   /* 设置要播放的文件的路径 */   
            mMediaPlayer2.setLooping(false);
            mMediaPlayer2.prepare(); 
        }catch(Exception e){}
        try   
        {
            mMediaPlayer2.start();   
            mMediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener()    
            {
                public void onCompletion(MediaPlayer arg0)   
                {   
                    if(arg0 != null) {
                    	arg0.release();  
                    }
                }   
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        } 
    }  
	/*暂停背景音乐*/
	private void pauseMedia(){
		try{
			if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
				mMediaPlayer.pause();
			}
		}catch(Exception e){}
	}
	
	
	
	
	
	
	
	/*-----------------语音识别----------------------*/
	
	/*-----------------sayit 3秒控制----------------------*/
	private void sayit3s(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					handler.sendMessage(handler.obtainMessage(3));	
					Thread.sleep(3000);
					handler.sendMessage(handler.obtainMessage(4));		
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	/*-----------------语音识别--结果处理----------------------*/
	private void playGood(){	//待完成
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(500);
					MediaCommon.playGuzhang(mContext);	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		clickExecute(Constant.timeLevel08[currentIndex][3]);
	}
	/*-----------------Handler onClick--处理事件----------------------*/
	private void returnHome(){
		clearMediaVideo();
//		startActivity(new Intent(mContext, SelectLevelActivity.class));
		this.finish();
	}
	private void clearMediaVideo(){
		try{
			isCycle = false;
			if(mMediaPlayer != null){
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			if (null != video) {
				video.stopPlayback();
				video = null;
			}
			
			if(mMediaPlayer2 != null){
				mMediaPlayer2.release();
				mMediaPlayer2 = null;
			}
		}catch(Exception e){
			
		}
	}
	
	// 点击执行的功能---------------------------------------------------------------------------------
	private void clickExecute(int seekTo) {
		try{
			isCycle = false;
			clickNum++;
			startTime = Constant.timeLevel03[seekTo][0] - offset_time;
			endTime = Constant.timeLevel03[seekTo][1] - offset_time;
			
			video.seekTo(Constant.timeLevel03[seekTo][0] - offset_time);
			isClicked = true;
			currentIndex = seekTo;
			
			videoCycle(clickNum);
			isCycle = true;
		}catch(Exception e){}
	}
	/*-----------------Handler onClick----------------------*/
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				final int index0 = (Integer)msg.obj;
				isClicked = false;
				/*------不共用------*/
				if(clickPaoPao<=3&&index0==iPpCircle[3]+1){
					showBtnAndShou2(5);
				}else if(currentIndex == iPpCircle[0]+1&&have4Treasure){
					showBtnAndShou2(6);
				}else if(currentIndex == iPpCircle[1]+1&&have4Treasure){
					showBtnAndShou2(7);
				}else if(currentIndex == iPpCircle[2]+1&&have4Treasure){
					showBtnAndShou2(8);
				}else{
					showBtnAndShou();	//小手
					playCurrentIndexMusic();
				}
				
				playClickHere(index0);   //点点这里
				/*------不共用------*/
				//showTwoSelectOne();	//二选一0
				showBtnafterAndShouByCurrentIndex();
				//showNext();
				break;
			case 1:
				final int index = (Integer)msg.obj;
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							//宝物显示的时间差
							if(index == iPpCircle[0]){
								Thread.sleep(2500);
							}else if(index == iPpCircle[1]){
								Thread.sleep(6000);
							}else if(index == iPpCircle[2]){
								Thread.sleep(7000);
							}else if(index == iPpCircle[3]){
								Thread.sleep(7000);
							}else {
								Thread.sleep(3000);
							}
							handler.sendMessage(handler.obtainMessage(8,index));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}).start();
				
				break;
			case 2:
				int num = (Integer)msg.obj;
				if (video != null && clickNum == num) {
					video.seekTo(startTime);
					videoCycle(num);
				}
				break;
			case 3 :
				pauseMedia();
//				startVR();
				break;
			case 4 :
//				stopVR();
				playGood();
				break;
			case 7:
				playGood();
				break;
			case 8:
				final int index2 = (Integer)msg.obj;
				visibleScripte(index2);
				break;
			default:
				break;
			}
		}

	};
	private void gotoSuccess(){
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra("level", 3));		
		clearMediaVideo();
		finish();
	}
	
	@Override
	public void onClick(View v) {
		/*------------共用部分-------------*/
		stopBtnAndShou();
		if(v == ivBack) {			
			returnHome();
 		}
		if(v == bt_after){
//			if(currentIndex >= 38&&currentIndex<=63||currentIndex==68){
			gotoSuccess();
		}
		if(haveClicked)return;
		haveClicked = true;
		if(v == btn_shou){
			//clickExecute(Constant.timeLevel03[currentIndex][3]);
			if(currentIndex == 12){
 				//不共用 泡泡专用--显示泡泡
 				setQiPaoVisible(true);
 			}else if(currentIndex == iPpCircle[3]&&!shouIsPaopao){
 				//不共用 泡泡专用--关闭泡泡
 				setQiPaoVisible(false);
 			}
			
			if((currentIndex == iPpCircle[0]+1 || currentIndex == iPpCircle[1]+1 || currentIndex == iPpCircle[2]+1 || currentIndex == iPpCircle[3]+1)&&have4Treasure){
				clickPaoPao++;
			}
			if(clickPaoPao<=4 &&currentIndex == iPpCircle[3]+1&&have4Treasure){
				setSelectedPaopao(0);
			}else if(currentIndex == iPpCircle[0]+1&&have4Treasure){
				setSelectedPaopao(1);
			}else if(currentIndex == iPpCircle[1]+1&&have4Treasure){
				setSelectedPaopao(2);
			}else if(currentIndex == iPpCircle[2]+1&&have4Treasure){
				setSelectedPaopao(3);
			}
			if(shouIsPaopao && currentIndex == iPpCircle[3]+1){
				clickExecute(papapStartIndex);
			}else if(!shouIsPaopao&&clickPaoPao>=3&&currentIndex==iPpCircle[3]+1){
				gotoSuccess();
			}else if(currentIndex >= 38&&currentIndex<=63||currentIndex==68){
				gotoSuccess();
			}else{
				clickExecute(Constant.timeLevel03[currentIndex][3]);
			}
		}
		
		
		//不共用 泡泡专用——
			if(v == iv1_11){
				setSelectedPaopao(0);
				clickExecute(papapStartIndex);
			}else if(v == iv1_21){
				setSelectedPaopao(1);
				clickExecute(18);
			}else if(v == iv1_31){
				setSelectedPaopao(2);
				clickExecute(24);
			}else if(v == iv1_41){
				setSelectedPaopao(3);
				clickExecute(30);
			}
			
			if(v == iv1_11 || v == iv1_21 || v == iv1_31 || v == iv1_41){
				shouIsPaopao=true;
				clickPaoPao++;
				//clickExecute(Constant.timeLevel08[currentIndex][3]);	
			}         

		
		Log.d("clickPaoPao","clickPaoPao:"+clickPaoPao);
	}  

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onRestart();
		getWindow().getDecorView().setKeepScreenOn(true);
		if (video!=null) {
			video.start();
			//跳到指定的帧数
			video.seekTo(preservation);
			//播放循环
			videoCycle(clickNum); 
			//显示手指
			showBtnAndShou();	
		
		}
		
		if(null != mMediaPlayer && !mMediaPlayer.isPlaying() && !(currentIndex >MusicPosition.jingyinIndex103new && currentIndex<MusicPosition.jingyinIndex203new)){
			mMediaPlayer.start();
		}
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			if(video!=null){
				//暂停时保存播放帧数
				int currentPos = video.getCurrentPosition();
				preservation=currentPos;			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		CommonAnimation.stopShouAnimation(btn_shou);
		//isClicked = true;
		getWindow().getDecorView().setKeepScreenOn(false);
		pauseMediaVideo();
		MediaCommon.pauseMediaplay();
		
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		try {
			if(video!=null){
				//按Home键或者电源键或者页面崩溃时保存视频播放的帧数
				int currentPos = video.getCurrentPosition();
				outState.putInt("preservation", currentPos);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		try {
			//取的保存的帧数
			preservation= savedInstanceState.getInt("preservation");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		isCycle = false;
		currentIndex=0;
		clearMediaVideo();
	}
	
	
	private void pauseMediaVideo(){
		try{
			if (null != video) {
				video.pause();
			}
			if(mMediaPlayer != null){
				mMediaPlayer.pause();
			}
			if(mMediaPlayer2 != null){
				mMediaPlayer2.pause();
			}
			if(mMediaPlayer3 != null){
				mMediaPlayer3.pause();
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnHome();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}