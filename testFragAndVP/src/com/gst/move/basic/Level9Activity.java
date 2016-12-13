package com.gst.move.basic;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;

public class Level9Activity  extends BaseActivity implements OnClickListener {
	/*-----------------基础----------------------*/
	private Context mContext;
	private float scaleQPW = 1.0f,scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer = null,mMediaPlayer2 = null; 
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
	
	/*-----------------视图----------------------*/
	private ImageView ivBack, btn_shou,bt_after;
	
	/*-----------------语音识别----------------------*/
	private String thisChar = "S";	//当前语音识别的字母
	private boolean mInitialized = false;
	private final String TAG = "Level9Activity";
	Thread recThread;
	private boolean mIsVrRuning = false;	//语音识别是否打开了
	
	/*-----------------不共用 特例部分----------------------*/
	/*-----------------二选一----------------------*/
	private ImageView iv_twoleft,iv_tworight;
	private boolean haveSelectLeft = false;
	private boolean haveSelectRight = false;
	private int type_shou = 0;
	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_09);

		initializationData();
		offset_time = Tools.delayTime();
		setView();
	}
	/*-----------------初始化----------------------*/
	/*数据初始化*/
	private void initializationData() {		
		mContext = Level9Activity.this;
		scaleQPW = (width/1280.0f);
		scaleQPH = (height/720.0f);
		
		biz = new VideoBiz();
		path = Constant.path_raz03 + "level09.mp4/";
		currentIndex = 0;//5
		startTime = Constant.timeLevel09[currentIndex][0];
		endTime = Constant.timeLevel09[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
		first = 0;
	}
	/*视图初始化*/
	private void setView() {	
		video = (MyVideoView) findViewById(R.id.video_play);
		ivBack = (ImageView)findViewById(R.id.iv_back);
		biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
		btn_shou = (ImageView)findViewById(R.id.btn_shou);
		iv_twoleft = (ImageView)findViewById(R.id.iv_twoleft);
		iv_tworight = (ImageView)findViewById(R.id.iv_tworight);
		bt_after = (ImageView)findViewById(R.id.bt_after);
		bt_after.setOnClickListener(this);
		ivBack.setOnClickListener(this);
		btn_shou.setOnClickListener(this);
		iv_twoleft.setOnClickListener(this);
		iv_tworight.setOnClickListener(this);
		btn_shou.setVisibility(View.GONE);
//		playMusic(MediaCommon.getLevel09Mp3(0));   //背景音乐播放
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz03 + "bg_music_level9.mp3");
		videoCycle(clickNum);
		biz.playVideo(video, path);		
//		CommonUtil.downLoadRes(mContext, 2, 4, Constant.download_level_10, commonPath, "raz03");
	}
	/*设置视图的位置*/	
	private void setViewPosition(ImageView iv,int i){
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(FixedPosition.level09_position[i][0],FixedPosition.level09_position[i][1], FixedPosition.level09_position[i][2], FixedPosition.level09_position[i][3], scaleQPW ,scaleQPH,xoffset,yoffset,suoxiao));						
	}
	/*显示手动画*/
	private void showBtnAndShou(){		
		int i = FixedPosition.getPositionIndex(FixedPosition.level09_index_to_position, currentIndex);
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
			VideoBiz.startAfterAnimation(bt_after);
			
			haveClicked = false;		//可以点击了
		}
		
	}
	/*消失btn_after*/
	private void stopBtnAfter(){
		bt_after.setVisibility(View.GONE);
		VideoBiz.stopAfterAnimation(bt_after);
	}
	/*显示bt_after动画和手动画*/
	private void showBtnafterAndShou(){
		
		showBtnAfterByPosition(19);
		showBtnAndShouByPosition(19);
		
		haveClicked = false;		//可以点击了
	}
	
	/*显示bt_after动画和手动画*/
	private void stopBtafterAndShou(){
		//stopBtnAfter();
		stopBtnAndShou();
	}
	/*----------------------view 不共用部分----------------------------*/
	/*二选一     0——没有小手  1——左边小手    2——右边小手*/
	private void showTwoSelectOne(){
		if(currentIndex != 45) {
			
			return;
		}
		
		haveClicked = false;
		
		iv_twoleft.setVisibility(View.VISIBLE);
		iv_tworight.setVisibility(View.VISIBLE);
		setViewPosition(iv_twoleft,5);
		setViewPosition(iv_tworight,6);
		if(!haveSelectLeft && !haveSelectRight){
			type_shou = 0;
			stopBtnAndShou();
		}else if(!haveSelectLeft && haveSelectRight){
			type_shou = 1;
			showBtnAndShouByPosition(5);
		}else if(haveSelectLeft && !haveSelectRight){
			type_shou = 2;
			showBtnAndShouByPosition(6);
		}else{
			//这里处理bt_after 显示
			type_shou = 3;
			showBtnafterAndShou();
		}
	}
	
	private void stopTwoSelectOne(){
		stopLeftAndRight();
		CommonAnimation.stopShouAnimation(iv_twoleft);
		CommonAnimation.stopShouAnimation(iv_tworight);
		
	}
	
	private void stopLeftAndRight(){
		iv_twoleft.setVisibility(View.GONE);
		iv_tworight.setVisibility(View.GONE);
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
				if(tem_num != clickNum) break;
				
				if (!isCycle || video == null)
					break;
				currentPos = video.getCurrentPosition(); // 空指针null

				if (Constant.timeLevel09[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
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
			if(tem_num == clickNum){
				
				//符合条件的时间段，开启语音识别
//				setStartVr();
				
				if(Constant.timeLevel09[currentIndex][4] == 0){
					currentIndex = Constant.timeLevel09[currentIndex][3];
				}
				//跟我读
				playReadAfterme();
				playCurrentIndexMusic();
				//开场白
				int i = currentIndex;
				if(!MusicPosition.play09Start(i).equals(""))playMusicOnly(MusicPosition.play09Start(i));
				
				startTime = Constant.timeLevel09[currentIndex][0] - offset_time;
				endTime = Constant.timeLevel09[currentIndex][1] - offset_time;
				handler.sendMessage(handler.obtainMessage(2,tem_num));
					
			}
			
		} catch (Exception e) {
			
		}
	}
	
	
	/*----------------------声音  共用部分----------------------------*/
	
	/*播放click事件对应的声音*/
	private void playClickMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.level09_click_music_position, currentIndex);
		Log.d("----------currentIndex----------", currentIndex+"");
		Log.d("----------i----------", i+"");
		if(i != -1){
			playMusicOnly(MediaCommon.getLevel09Mp3(i));
		}
	}
	/*播放currentIndex对应的声音*/
	private void playCurrentIndexMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.level09_music_position, currentIndex);
		if(i != -1){
			playMusicOnly(MediaCommon.getLevel09Mp3(i));
		}
	}
	/*播放跟我读*/
	private void playReadAfterme(){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.level09_readafterme, currentIndex);
		if(i != -1){
			MediaCommon.playReadAfterMe(mContext);
		}
	}
	/*点点这里*/
	private void playClickHere(int index){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.level09_clickhere, index);
		if(i != -1){
			MediaCommon.playClickHere(mContext);
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
        {   if(mMediaPlayer2 != null){
        		mMediaPlayer2.release();
        		mMediaPlayer2 = null;
        	}
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
	
	/*-----------------语音识别——结果处理----------------------*/
	private void playGood(){	//待完成
		MediaCommon.playGuzhang(mContext);	
		clickExecute(Constant.timeLevel09[currentIndex][3]);
	}
	/*-----------------Handler onClick——处理事件----------------------*/
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
		if(null != mMediaPlayer && !mMediaPlayer.isPlaying() && !(currentIndex >=MusicPosition.jingyinIndex1 && currentIndex<=MusicPosition.jingyinIndex2)){
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

	// 点击执行的功能---------------------------------------------------------------------------------
	private void clickExecute(int seekTo) {
		try{
			isCycle = false;
			clickNum++;
			startTime = Constant.timeLevel09[seekTo][0] - offset_time;
			endTime = Constant.timeLevel09[seekTo][1] - offset_time;
			
			video.seekTo(Constant.timeLevel09[seekTo][0] - offset_time);
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
				if(index == MusicPosition.lastIndex09)
					showBtnAfterByPosition(19);
				/*------点点这里声音------*/
				playClickHere(index);
				//开启背景音
				if(MusicPosition.lastIndex09 == index)
					playMusic(MediaCommon.getLevel09Mp3(0));   //背景音乐播放
				/*------不共用------*/
				showTwoSelectOne();	//二选一
				if(index != 45){
					iv_twoleft.setVisibility(View.GONE);
					iv_tworight.setVisibility(View.GONE);
				}
				
				break;
			case 2:
				int num = (Integer)msg.obj;
				if (video != null && clickNum == num) {
					video.seekTo(startTime);
					videoCycle(num);
				}
				break;
			case 3:
				pauseMedia();
//				startVR();
				break;
			case 4:
//				stopVR();
				playGood();
				break;
			
			default:
				break;
			}
		}

	};
	
	private void gotoSuccess(){
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra("level", 11));
		clearMediaVideo();
		finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/*------------共用部分-------------*/
		stopBtnAndShou();
		
		if(v == ivBack) {			
			returnHome();
//			clickExecute(139);
 		}else if(v == bt_after){
 			stopBtnAfter();
 			if(currentIndex <= 45){
 				stopBtafterAndShou();
 	 			clickExecute(139);
 			}else if(currentIndex > 136){
 				//此处跳转到 showtime
 				gotoSuccess();
 			}else if(currentIndex <= 136){
 				/*stopBtafterAndShou();
 	 			clickExecute(139);*/
 				gotoSuccess();
 			}
 		}
		if(haveClicked)return;
		haveClicked = true;
		
		playClickMusic();
		
		if(v == btn_shou){
			stopBtnAfter();
			if(currentIndex == 45){
				stopLeftAndRight();
				if(type_shou == 1){
					haveSelectLeft = true;
					//MediaCommon.getLevel09Mp3(5);
					clickExecute(76);
				}else if(type_shou == 2){
					haveSelectRight = true;
					//MediaCommon.getLevel09Mp3(4);
					clickExecute(49);
				}else if(type_shou == 3){
					gotoSuccess();
					/*stopBtafterAndShou();
					MediaCommon.playSayit(mContext);
		 			clickExecute(139);*/
				}				
			}else if(currentIndex == 189){
				//此处跳转到 showtime
				gotoSuccess();
			}
			else{
				clickExecute(Constant.timeLevel09[currentIndex][3]);
			}
 			
 		}else if(v == iv_twoleft){
 			haveSelectLeft = true;
 			//MediaCommon.getLevel09Mp3(5);
 			clickExecute(76);
 		}else if(v == iv_tworight){
 			haveSelectRight = true;
 			//MediaCommon.getLevel09Mp3(4);
 			clickExecute(49);
 		}
		
		/*------------不共用部分-------------*/
		if(v == iv_twoleft || v == iv_tworight){ 			
 			stopTwoSelectOne();
 		}
	}  
	
	
}
