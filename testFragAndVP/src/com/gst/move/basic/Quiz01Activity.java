package com.gst.move.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.data.MusicPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.CommonStatistical;
import com.ebodoo.raz.utils.CommonYuyin;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.ConstantAsia;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;
import com.gst.move.model.CommonUtil;

public class Quiz01Activity extends BaseActivity implements OnClickListener {
	/*-----------------基础----------------------*/
	private Context mContext;
	private float scaleQPW = 1.0f,scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer = null,mMediaPlayer2 = null, mMediaPlayerSound = null; 
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
	private int first = 0;
	private boolean gotoActivity = false;
	/*-----------------不同设备的时间差----------------------*/
	/*private int time_offset = 0;
	private int time_offset2 = 0;*/
	private boolean video_pause = false;
	/*-----------------视图----------------------*/
	private ImageView bt_home,btn_shou,bt_after;
	
	/*-----------------语音识别----------------------*/
	private String thisChar = "/o/";	//当前语音识别的字母
	private boolean mInitialized = false;
	private final String TAG = "Quiz01Activity";
	Thread recThread;
	private int numYuyin = 0;
	
	/*-----------------闯关----------------------*/
	private int whichChuangguanType = 0;	//闯关为随机出来的闯关字母，quiz3 有3种随机类型
	private boolean haveSpeakTishi = false;	//第一次需要提示
	private int gjErrorNum = 0; // 错误次数
	
	private int errorSpeek = 0; // 说错误了几次	
	private int whereError = 0; // 闯关哪里没有过，重新学习之后，再回到哪里继续？
	// 1——听读音选读音未过；2——语音识别没有过
	
	/*-----------------不共用 特例部分----------------------*/
	/*-----------------二选一----------------------*/
	private ImageView iv_left,iv_right,iv_center,iv_readagain,bt_replay;
	private boolean mIsVrRuning = false;

	private VideoBiz mVideoBiz;
	
	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz01);

		initializationData();
		
		/*if(Tools.getDeviceType().contains("rk30sdk")){
		}*/
		offset_time = Tools.delayTime();
		setView();
	}
	/*-----------------初始化----------------------*/
	/*数据初始化*/
	private void initializationData() {		
		mContext = Quiz01Activity.this;
		scaleQPW = (width/1280.0f);
		scaleQPH = (height/720.0f);
		
		biz = new VideoBiz();
		path = Constant.path_raz01 + "quiz01_paopaoguai.mp4/";
		currentIndex = 0;//5
		startTime = Constant.timeQuizi01[currentIndex][0];
		endTime = Constant.timeQuizi01[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
		first = 0;
		numYuyin = 0;
		
		gjErrorNum = 0; // 错误次数
		errorSpeek = 0; // 说错误了几次	
		whereError = 0; // 闯关哪里没有过，重新学习之后，再回到哪里继续？
		isClicked = true;
		gotoActivity = false;
	}
	/*视图初始化*/
	private void setView() {	
		video = (MyVideoView) findViewById(R.id.video_play);
		bt_home = (ImageView)findViewById(R.id.bt_home);
		
		mVideoBiz = new VideoBiz();
		mVideoBiz.setViewPositionNoSuoxiao(bt_home, 3, FixedPosition.common_position, scaleQPW ,scaleQPH);
		
		btn_shou = (ImageView)findViewById(R.id.btn_shou);
		
		iv_left = (ImageView)findViewById(R.id.iv_left);
		iv_right = (ImageView)findViewById(R.id.iv_right);
		iv_center = (ImageView)findViewById(R.id.iv_center);
		
		bt_after = (ImageView)findViewById(R.id.bt_after);
		
		bt_after.setOnClickListener(this);
		bt_home.setOnClickListener(this);
		btn_shou.setOnClickListener(this);
		iv_left.setOnClickListener(this);
		iv_right.setOnClickListener(this);
		iv_center.setOnClickListener(this);
		
		bt_home.setImageResource(BaseCommon.getImageViewId2(mContext,"home"));
		
		iv_readagain = (ImageView) findViewById(R.id.iv_readagain);
		iv_readagain.setOnClickListener(this);
		iv_readagain.setVisibility(View.INVISIBLE);
		
		bt_replay = (ImageView) findViewById(R.id.bt_replay);
		bt_replay.setVisibility(View.GONE);
		bt_replay.setOnClickListener(this);
		mVideoBiz.setViewPositionNoSuoxiao(iv_readagain, 4, FixedPosition.common_position, scaleQPW ,scaleQPH);
		mVideoBiz.setViewPositionNoSuoxiao(bt_replay, 6, FixedPosition.common_position, scaleQPW ,scaleQPH);
//		bt_after.setVisibility(View.VISIBLE);
		
		btn_shou.setVisibility(View.GONE);
		stopThreeSelectOne();
//		mMediaPlayer = new MediaCommon().getQuiz01Mp3(mContext,0);
//		playMusic();   //背景音乐播放
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz01 + "bgmusic_quizi01.mp3");
		videoCycle(clickNum);
		playVideo(video, path);
//		CommonUtil.quizLoad(mContext, commonPath, 1, Constant.download_level_04, "raz02");
	}
	
	public void playVideo(MyVideoView video, String path) {
		try {
			if (path != null && !path.equals("")) {
				System.out.println("path :" + path);
				video.setVideoURI(Uri.parse(path));
				video.requestFocus();
				video.start();
				video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						if(!gotoActivity)
							goQuizCard();
					}
				});

				video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

					@Override
					public void onPrepared(MediaPlayer arg0) {

					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*设置视图的位置*/	
	private void setViewPosition(ImageView iv,int i){
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(FixedPosition.quiz01_position[i][0],FixedPosition.quiz01_position[i][1], FixedPosition.quiz01_position[i][2], FixedPosition.quiz01_position[i][3], scaleQPW ,scaleQPH,xoffset,yoffset,suoxiao));						
	}
	/*显示手动画*/
	private void showBtnAndShou(){		
		int i = FixedPosition.getPositionIndex(FixedPosition.quiz01_index_to_position, currentIndex);
		showBtnAndShouByPosition(i);		
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
	
	/*消失手动画*/
	private void stopBtnAndShou(){
		btn_shou.setVisibility(View.GONE);
		CommonAnimation.stopShouAnimation(btn_shou);
	}
	
	/*显示btn_after通过位置*/
	private void showBtnAfterByPosition(int i){
		if(i != -1){
			bt_after.setVisibility(View.VISIBLE);
			setViewPosition(bt_after,i);
//			VideoBiz.startAfterAnimation(bt_after);
			
			haveClicked = false;		//可以点击了
		}
		
	}
	/*消失btn_after*/
	private void stopBtnAfter(){
		bt_after.setVisibility(View.GONE);
//		VideoBiz.stopAfterAnimation(bt_after);
	}
	/*显示bt_after动画和手动画*/
	private void showBtnafterAndShou(){
		if(currentIndex == 59 || currentIndex == 62){				//不共用  需要修改
			int i = FixedPosition.getPositionIndex(FixedPosition.quiz01_index_to_position, currentIndex);
			showBtnAfterByPosition(i);
		}
		//showBtnAndShouByPosition(i);
		
		haveClicked = false;		//可以点击了
	}
	
	/*显示bt_after动画和手动画*/
	private void stopBtafterAndShou(){
		stopBtnAfter();
		stopBtnAndShou();
	}
	/*----------------------view 不共用部分----------------------------*/
	/*二选一     0——没有小手  1——左边小手    2——右边小手*/
	private void showThreeSelectOne(){
		iv_readagain.setVisibility(View.VISIBLE);
		haveClicked = false;
		setViewPosition(iv_left,5);				//不共用 需要修改
		setViewPosition(iv_center,6);			//不共用 需要修改
		setViewPosition(iv_right,7);			//不共用 需要修改
		iv_left.setVisibility(View.VISIBLE);
		iv_right.setVisibility(View.VISIBLE);
		iv_center.setVisibility(View.VISIBLE);
	}
	
	private void stopThreeSelectOne(){
		iv_left.setVisibility(View.GONE);
		iv_right.setVisibility(View.GONE);
		iv_center.setVisibility(View.GONE);
		
		iv_readagain.setVisibility(View.GONE);
	}
	
	private void invisibleTryagain(){
		if(MusicPosition.quiz01_chuangguan_end == currentIndex){
			handler.sendMessage(handler.obtainMessage(7));
		}
	}
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
		int currentPos = video.getCurrentPosition();
		int tem_num = num;
		do {
			
			try {
				
				if (currentIndex <= 260) {
					Thread.sleep(50);
				}
				if(tem_num != clickNum) 
					break;
				
				if (!isCycle || video == null)
					break;				
				if (Constant.timeQuizi01[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
					isClicked = false;
					handler.sendMessage(handler.obtainMessage(0, currentIndex));
				}
				currentPos = video.getCurrentPosition(); // 空指针null
				if (currentPos >= endTime) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (currentPos < endTime);
		
		/*if (Constant.timeQuizi01[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
			handler.sendMessage(handler.obtainMessage(0, currentIndex));
		}
		
		try {
			Thread.sleep((Constant.timeQuizi01[currentIndex][1]-Constant.timeQuizi01[currentIndex][0]));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			if (!video.isPlaying()) {// 如果不在播放状态，则停止更新
				return;
			}
			if(tem_num == clickNum){
				
				//第一次闯关提示的处理
				if(currentIndex == MusicPosition.index_quiz01_chuangguan_tishi){
					handler.sendMessage(handler.obtainMessage(5,tem_num));
					return;
				}
				invisibleTryagain();
				//sayit对错后的跳转
				sayit_good();
				sayit_error();
				
				/*if(Constant.timeQuizi01[currentIndex][4] == 1){
					video.pause();
					video_pause = true;
				}*/
				
				if(Constant.timeQuizi01[currentIndex][4] == 0/* && currentIndex != MusicPosition.indexChuangguanTishi*/ ){
					currentIndex = Constant.timeQuizi01[currentIndex][3];
					//符合条件的时间段，开启语音识别
					System.out.println("currentIndex 11 :" + currentIndex);
					System.out.println("currentIndex 11 clickNum :" + clickNum);
					setStartVr();
					if(currentIndex == 62) {
						currentIndex = 65;
					} else if(currentIndex == 69) {
						currentIndex = 72;
					} else if(currentIndex == 76) {
						currentIndex = 136;
					}
				}
				if(currentIndex == 59){
					handler.sendMessage(handler.obtainMessage(8));
				}
				playCurrentIndexMusic();
				//闯关
				playAnswerError();//答错了
				playAnswerGood();//答对了
				//跟我读
				playLookWord(currentIndex);
				
				
				//开场白
				startTime = Constant.timeQuizi01[currentIndex][0] - offset_time;
				endTime = Constant.timeQuizi01[currentIndex][1] - offset_time;
				handler.sendMessage(handler.obtainMessage(2,tem_num));
					
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*闯关 第一次提示音，往哪里跳转*/
	private void chuangguanTishi(){
		int index = 0;
		
		showThreeSelectOne();
		if(haveSpeakTishi){
			index = Constant.timeQuizi01[currentIndex][3];
		}else{
			index = MusicPosition.index_quiz01_chuangguan_tishi_first;
			MediaCommon.playClickWordByHear(mContext);
			haveSpeakTishi = true;
		}
		clickNum++;
		clickExecute(index);
		return;
		
	}
	
	
	/*say it 对的时候，往哪里跳转*/
	private void sayit_good(){
		System.out.println("--------------- sayit_good() ------- currentIndex ----------" + currentIndex);
		int index = 0;
		int i = MusicPosition.getSayitIndex(MusicPosition.quiz01_sayit_good,currentIndex);
		Log.d("-----currentIndex1-----", ""+currentIndex);
		if(i != -1){
			if(numYuyin < (i+1)*3){
				index = currentIndex - 6;				//不共用 需要修改
			}else{
				index = Constant.timeQuizi01[currentIndex][3];
			}
			clickNum++;
			playLookWord(index);				//不共用 需要修改
			clickExecute(index);
			return;
		}
	}
	
	/*say it 错的时候，往哪里跳转*/
	private void sayit_error(){
		int index = 0;
		int i = MusicPosition.getSayitIndex(MusicPosition.quiz01_sayit_error,currentIndex);
		Log.d("-----currentIndex2-----", ""+currentIndex);
		if(i != -1){
			if(numYuyin < (i+1)*3){
				index = currentIndex - 5;				//不共用 需要修改
			}else{
				index = Constant.timeQuizi01[currentIndex][3];
			}
			clickNum++;
			playLookWord(index);					//不共用 需要修改
			clickExecute(index);
			return;
		}
	}
	
	
	/*----------------------闯关  共用部分----------------------------*/
	
	/*播放click事件对应的声音*/
	
	
	
	
	/*----------------------声音  共用部分----------------------------*/
	/*播放闯关点击后currentIndex对应的声音*/
	private int playChuangguanCurrentIndexMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.quiz01_cg_related, currentIndex,whichChuangguanType);
		return i;
	}
	
	/*播放闯关点击后currentIndex对应的声音*/
	private void playChuangguanCurrentIndexWordSound(){		
		int i = MusicPosition.getSoundPositionIndex(MusicPosition.quiz01_cg_related, currentIndex,whichChuangguanType);
		if(i != -1){
//			mMediaPlayer2 = new MediaCommon().getQuiz01Mp3(mContext,i);
//			playMusicOnly();		//去掉偏移量2
//			String path = new MediaCommon().getQuiz01Mp3(mContext,i);
			playOnlineMusic(new MediaCommon().getQuiz01Mp3(mContext,i));
		}
	}
	
	/*播放click事件对应的声音*/
	private void playClickMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.quiz01_click_music_position, currentIndex);
		if(i != -1){
//			mMediaPlayer2 = new MediaCommon().getQuiz01Mp3(mContext,i);
//			playMusicOnly();
			playOnlineMusic(new MediaCommon().getQuiz01Mp3(mContext,i));
		}
	}
	/*播放currentIndex对应的声音*/
	private void playCurrentIndexMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.quiz01_music_position, currentIndex);
		if(i != -1){
//			mMediaPlayer2 = new MediaCommon().getQuiz01Mp3(mContext,i);
//			playMusicOnly();
			playOnlineMusic(new MediaCommon().getQuiz01Mp3(mContext,i));
		}
	}
	/*播放跟我读*/
	private void playLookWord(int index){		
		System.out.println("=========== playLookWord ===== 跟我读 ====index======" + index);
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.quiz01_readlookword, index);
		if(i != -1){
			MediaCommon.playLookword(mContext);
		}
	}
	/*点点这里*/
	private void playClickHere(int index){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.quiz01_clickhere, index);
		if(i != -1){
			MediaCommon.playClickHere(mContext);
		}
	}
	/*答错了*/
	private void playAnswerError(){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.quiz01_answererror, currentIndex);
		if(i != -1){
			MediaCommon.playAnswerError(mContext);
		}
	}
	/*答对了*/
	private void playAnswerGood(){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.quiz01_answergood, currentIndex);
		if(i != -1){
			MediaCommon.playAnswerGood(mContext);
		}
	}
	
	/*声音循环播放*/
	public void playMusic()   
    {      	
        try   
        {   
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
	public void playMusicOnly()   
    {      
        try   
        {   
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
                    if(mMediaPlayer2 != null) {
                    	mMediaPlayer2.release();  
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
	
	//开启sayit startvr——————————————————不重用
	private void setStartVr(){
		thisChar = MusicPosition.getThisChar(MusicPosition.quiz01_sayit_thisChar_position,currentIndex);
		System.out.println("thisChar :" + thisChar);
		if(!thisChar.equals("")){
			handler.sendMessage(handler.obtainMessage(3));
		}		
	}
	
	/*-----------------语音识别——结果处理----------------------*/
	private void playGood(){	//待完成
		numYuyin++;
		MediaCommon.playGuzhang(mContext);	
		clickExecute(currentIndex+3);		//不共用 需要修改		循环跳到成功需要 + 多少
		return;
	}

	private void doSayitError(){
		numYuyin++;
		MediaCommon.playSayitError(mContext);
		errorSpeek++;
		if(errorSpeek > MusicPosition.quiz01_sayit_error_max){
			whereError = 2;
			haveClicked = false;
			bt_replay.setVisibility(View.VISIBLE);
			playReplay();
			clickExecute(MusicPosition.quiz01_second_tryagin);
			return;
		}
		clickNum++;
		clickExecute(Constant.timeQuizi01[currentIndex][3]);//+4		//不共用 需要修改
		return;
	}
	
	// 闯关执行的功能---------------------------------------------------------------------------------
	private void doChuangguanError(){
		
		MediaCommon.playAnswerError(mContext);
		gjErrorNum++;
		if(gjErrorNum > MusicPosition.quiz01_guanka_error_max){
			iv_readagain.setVisibility(View.INVISIBLE);
			whereError = 1;
			haveClicked = false;
			bt_replay.setVisibility(View.VISIBLE);
			playReplay();
			clickExecute(MusicPosition.quiz01_first_tryagain);
			return;
		}
		clickNum++;
		clickExecute(Constant.timeQuizi01[currentIndex][3]);
		return;
	}
	
	private void playReplay(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(900);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendMessage(handler.obtainMessage(6));				
			}
		}).start();;
	}
	/*-----------------Handler onClick——处理事件----------------------*/
	private void returnHome(){
		clearMediaVideo();
//		startActivity(new Intent(mContext, SelectLevelActivity.class));		
		this.finish();
		return;
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
			if(mMediaPlayerSound != null){
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
		}catch(Exception e){
			
		}
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
			if(mMediaPlayerSound != null){
				mMediaPlayerSound.pause();
			}
		}catch(Exception e){
			
		}
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);		
		
		if (first >0 && null != video) {
			video.start();
			//跳到指定的帧数
			video.seekTo(preservation);
			//播放循环
			videoCycle(clickNum); 
		}
		if(null != mMediaPlayer && !mMediaPlayer.isPlaying() && !(currentIndex >=MusicPosition.quiz01_jingyinIndex1 && currentIndex<=MusicPosition.quiz01_jingyinIndex2)){
			mMediaPlayer.start();
		}
		first++;
	}
	@Override
	protected void onPause() {
		super.onPause();
		try {
			//暂停时保存播放帧数
			if(video!=null){
				int currentPos = video.getCurrentPosition();
				preservation=currentPos;			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		//isClicked = true;
		getWindow().getDecorView().setKeepScreenOn(false);
		pauseMediaVideo();
		MediaCommon.pauseMediaplay();
	}

	@Override
	protected void onDestroy() {
		isCycle = false;
		super.onDestroy();
		//帧数清零
		preservation=0;
		clearMediaVideo();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			returnHome();			
			return true;
		}else if(keyCode == KeyEvent.KEYCODE_HOME){
			//isClicked = true;
			pauseMediaVideo();
		}
			
		return super.onKeyDown(keyCode, event);
	}
	
	// 清除 MediaPlayer
	private void clearMediaplayer() {
		/*try {
			if (mMediaPlayer != null) {
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
		} catch (Exception e) {}		
		try {
			if (mMediaPlayer2 != null) {
				mMediaPlayer2.release();
				mMediaPlayer2 = null;
			}
		} catch (Exception e) {}*/

		try {
			if (null != video) {
				video.stopPlayback();
				video = null;
			}
		} catch (Exception e) {}
	}
	
	// 做错两次就重新学习
	private void restartActivity() {
		bt_replay.setVisibility(View.INVISIBLE);
		clearMediaplayer();
		initializationData();
		setView();		
		
//		mMediaPlayer2 = new MediaCommon().getQuiz01Mp3(mContext,1);
//		playMusicOnly();
		playOnlineMusic(new MediaCommon().getQuiz01Mp3(mContext, 1));
		clickExecute(1);
	}
	
	private void goQuizCard(){
		gotoActivity = true;
		
//		startActivity(new Intent(mContext, QuizCardActivity.class).putExtra("level", MusicPosition.levelQuiz01));
		clearMediaplayer();
		finish();
		return;
	}
	// 点击执行的功能---------------------------------------------------------------------------------
	private void clickExecute(int seekTo) {
		try{
			isCycle = false;
			clickNum++;
			startTime = Constant.timeQuizi01[seekTo][0] - offset_time;
			endTime = Constant.timeQuizi01[seekTo][1] - offset_time;
			
			video.seekTo(Constant.timeQuizi01[seekTo][0] - offset_time);
			
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
				int index = (Integer)msg.obj;
				isClicked = false;
				/*------共用------*/
				showBtnAndShou();	//小手
				showBtnafterAndShou();//显示bt_after
				/*------点点这里声音------*/
				playClickHere(index);
				/*------闯关单词的读音------*/
				playChuangguanCurrentIndexWordSound();
				//开启背景音
				if(MusicPosition.endIndexQuiz01 == index){
//					playMusic(MediaCommon.getQuiz01Mp3(0));   //背景音乐播放
					if(!gotoActivity)
						goQuizCard();
				}
				
				
				/*------不共用------*/
				
				
				break;
			case 2:
				int num = (Integer)msg.obj;
				if (video != null && clickNum == num) {
					video.seekTo(startTime);
					videoCycle(num);
				}
				break;
			case 3:
				stopThreeSelectOne();
				pauseMedia();
				Log.d("----------start ------------", "000");
//				testaudio();
				sayit3s();
				mIsVrRuning = true;
				break;
			case 4:
				float score = (Float) msg.obj;
				//if(!mIsVrRuning)return;			//在coolpad机器上有问题
				if (score > 0) {// 回答正确
					playGood();
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
//							TCAgent.onEvent(mContext, CommonStatistical.answer_yes_no,"voice", CommonStatistical.getTalkingData("yes", BaseCommon.getAge(mContext), thisChar));
							
						}
					});
				}else {// 语音识别错误
					doSayitError();
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
//							TCAgent.onEvent(mContext, CommonStatistical.answer_yes_no,"voice", CommonStatistical.getTalkingData("no", BaseCommon.getAge(mContext), thisChar));
							
						}
					});
				}
				mIsVrRuning = false;
				break;
			case 5:
				chuangguanTishi();
				break;
			case 6:
				MediaCommon.playReplay(mContext);
				break;
			case 7:
				iv_readagain.setVisibility(View.INVISIBLE);				
				break;
			case 8:
				setViewPosition(bt_after,8);
				bt_after.setVisibility(View.VISIBLE);	
				stopThreeSelectOne();
				break;
			case 9:
				System.out.println("----------------- case 9 -----------------");
				isClicked = true;
//				setClick();
				sayit_good();
				break;
			default:
				break;
			}
		}

	};
	
	/*-----------------sayit 3秒控制----------------------*/
	private void sayit3s() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					handler.sendMessage(handler.obtainMessage(9));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/*------------共用部分-------------*/
		stopBtnAndShou();
		/*video.start();
		video_pause = false;*/
		
		if(v == bt_home) {			
			returnHome();
 		}else if(v == bt_after){
 			if(currentIndex >= 59){				//不共用  需要修改
 				clickExecute(136);
 			}else if(currentIndex >= 136){
 				//此处跳转到 showtime
 				if(!gotoActivity)
 					goQuizCard();
 			}
 			return;
 		}else if(v == iv_readagain){
 			playChuangguanCurrentIndexWordSound();
 			return;
 		}
		if(haveClicked)return;
		haveClicked = true;
		
		playClickMusic();
		
		if(v == btn_shou){
			/*if(currentIndex == 103){
				stopBtafterAndShou();
				if(whereError == 2){
					stopThreeSelectOne();
					clickExecute(MusicPosition.quiz01_sayit_start);
					return;
				}
			}else if(currentIndex == 229){
				iv_readagain.setVisibility(View.INVISIBLE);
				stopThreeSelectOne();
				stopBtnAndShou();
			}else */if(currentIndex == MusicPosition.quiz01_first_tryagain || currentIndex == MusicPosition.quiz01_second_tryagin){
				restartActivity();
				return;
			}
			clickExecute(Constant.timeQuizi01[currentIndex][3]);
 			
 		}else if(v == iv_left){ 
 			if(playChuangguanCurrentIndexMusic() == 0){
 				MediaCommon.playAnswerGood(mContext);
 				clickExecute(currentIndex+4);		//不共用 需要修改
 								
 			}else{
 				doChuangguanError();
 			}
 		}else if(v == iv_center){
 			if(playChuangguanCurrentIndexMusic() == 1){
 				MediaCommon.playAnswerGood(mContext);
 				clickExecute(currentIndex+4);		//不共用 需要修改
 			}else{
 				doChuangguanError();
 			}
 		}else if(v == iv_right){
 			if(playChuangguanCurrentIndexMusic() == 2){
 				MediaCommon.playAnswerGood(mContext);
 				clickExecute(currentIndex+4);		//不共用 需要修改
 			}else{
 				doChuangguanError();
 			}
 		}else if(v == bt_replay){
			restartActivity();
 		}	
	}  
	
	private void playOnlineMusic(String path) {
		try {
			mMediaPlayerSound = new BaseCommon().playMusic(mMediaPlayerSound, path, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
