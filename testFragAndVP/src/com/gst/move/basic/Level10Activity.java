package com.gst.move.basic;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;

public class Level10Activity  extends BaseActivity implements OnClickListener {
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
	
	/*-----------------左侧4个泡泡专用----------------------*/
	private boolean have4Treasure = false;
	private ImageView iv1,iv2,iv3,iv4,iv1_11,iv1_21,iv1_31,iv1_41,paopao_selected;
	private int clickPaoPao = 0;		//泡泡被点击的次数
	private int iPpCircle[] = {60,84,110,130};
	private boolean shouIsPaopao = true;
	private int papapStartIndex = 38;
	
	/*-----------------不共用 特例部分----------------------*/
	/*-----------------二选一----------------------*/
	
	private boolean haveSelectLeft = false;
	private boolean haveSelectRight = false;
	private int type_shou = 0;
	
	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_10);

		initializationData();
		offset_time = Tools.delayTime();
		setView();
	}
	/*-----------------初始化----------------------*/
	/*数据初始化*/
	private void initializationData() {		
		mContext = Level10Activity.this;
		scaleQPW = (width/1280.0f);
		scaleQPH = (height/720.0f);
		
		biz = new VideoBiz();
		path = Constant.path_raz03 + "level10.mp4/";
		currentIndex = 0;//5
		startTime = Constant.timeLevel10[currentIndex][0];
		endTime = Constant.timeLevel10[currentIndex][1];
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
		bt_after = (ImageView)findViewById(R.id.bt_after);
		bt_after.setOnClickListener(this);
		ivBack.setOnClickListener(this);
		btn_shou.setOnClickListener(this);
		btn_shou.setVisibility(View.GONE);
		setPaopaoView();
//		playMusic(MediaCommon.getLevel10Mp3(0));   //背景音乐播放
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz03 + "bg_music_level10.mp3");
		videoCycle(clickNum);
		biz.playVideo(video, path);		
//		CommonUtil.downLoadRes(mContext, 2, 5, Constant.download_quiz_03, commonPath, "raz03");
	}
	/*设置视图的位置*/	
	private void setViewPosition(ImageView iv,int i){
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(FixedPosition.level10_position[i][0],FixedPosition.level10_position[i][1], FixedPosition.level10_position[i][2], FixedPosition.level10_position[i][3], scaleQPW ,scaleQPH,xoffset,yoffset,suoxiao));						
	}
	
	/*显示手动画*/
	private void showBtnAndShou(){		
		int i = FixedPosition.getPositionIndex(FixedPosition.level10_index_to_position, currentIndex);
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
	private void showBtnAfterByPosition(){
		
		bt_after.setVisibility(View.VISIBLE);
		setCommonPosition(bt_after,8);
		VideoBiz.startAfterAnimation(bt_after);
		
		haveClicked = false;		//可以点击了
		
		
	}
	
	/*消失btn_after*/
	private void stopBtnAfter(){
		bt_after.setVisibility(View.GONE);
		VideoBiz.stopAfterAnimation(bt_after);
	}	
	
	/*显示bt_after动画和手动画*/
	private void stopBtafterAndShou(){
		stopBtnAfter();
		stopBtnAndShou();
	}
	
	/*显示bt_after动画和手动画*/
	private void showBtnafterAndShouByCurrentIndex(){
		/*-------此处的 数字 不共用 需要根据实际修改-------*/
		if(currentIndex == 34 || currentIndex == iPpCircle[3]){
			showBtnAfterByPosition();
			if(currentIndex == 34 || clickPaoPao >= 4){
				showBtnAndShouByPosition(18);
				if(clickPaoPao >= 4){
					shouIsPaopao = false;
				}
			}
		}
		
		haveClicked = false;		//可以点击了
	}
	
	/*----------------------view 不共用部分 泡泡专用----------------------------*/
	
	/*设置泡泡的位置*/	
	private void setCommonPosition(ImageView iv,int i){
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(FixedPosition.paopao_position[i][0],FixedPosition.paopao_position[i][1], FixedPosition.paopao_position[i][2], FixedPosition.paopao_position[i][3], scaleQPW ,scaleQPH,xoffset,yoffset,suoxiao));						
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
			
		iv1.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao"));
		iv2.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao"));
		iv3.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao"));
		iv4.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao"));
		paopao_selected.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao_selected"));
		iv1_11.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "ic_e_1"));
		iv1_21.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "ic_e_2"));
		iv1_31.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "ic_e_3"));
		iv1_41.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "ic_e_4"));
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
	/*设置泡泡view——————0、1、2、3*/	
	private void setSelectedPaopao(int i){
		paopao_selected.setVisibility(View.VISIBLE);
		setCommonPosition(paopao_selected,(i+4));		
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
				Thread.sleep(50);
				
				if(tem_num != clickNum) break;
				
				if (!isCycle || video == null)
					break;
				currentPos = video.getCurrentPosition(); // 空指针null

				if (Constant.timeLevel10[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
					/*-------------该判定泡泡专用 不共用--------------*/
					if(!have4Treasure && (currentIndex == iPpCircle[0] || currentIndex == iPpCircle[1] || currentIndex == iPpCircle[2])){
						
					}else{
						handler.sendMessage(handler.obtainMessage(0, currentIndex));
					}
					
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
				
				if(Constant.timeLevel10[currentIndex][4] == 0){					
					currentIndex = Constant.timeLevel10[currentIndex][3];
				}
				/*-------------泡泡专用 不共用--------------*/
				if(!have4Treasure && (currentIndex == iPpCircle[0] || currentIndex == iPpCircle[1] || currentIndex == iPpCircle[2] || currentIndex == iPpCircle[3])){
					int index = currentIndex;
					handler.sendMessage(handler.obtainMessage(1,index));
				}
				
				if(!have4Treasure && (currentIndex == iPpCircle[0] || currentIndex == iPpCircle[1] || currentIndex == iPpCircle[2])){
					currentIndex = Constant.timeLevel10[currentIndex][3];
				}
				if(currentIndex == iPpCircle[3]){
					have4Treasure = true;
				}
								//跟我读
				playReadAfterme();
				playCurrentIndexMusic();
				//开场白
				int i = currentIndex;
				if(!MusicPosition.play10Start(i).equals(""))playMusicOnly(MusicPosition.play10Start(i));

				
				startTime = Constant.timeLevel10[currentIndex][0] - offset_time;
				endTime = Constant.timeLevel10[currentIndex][1] - offset_time;
				handler.sendMessage(handler.obtainMessage(2,tem_num));
					
			}
			
		} catch (Exception e) {
			
		}
	}
	
	
	/*----------------------声音  共用部分----------------------------*/
	
	/*播放click事件对应的声音*/
	private void playClickMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.level10_click_music_position, currentIndex);
		if(i != -1){
			playMusicOnly(MediaCommon.getLevel10Mp3(i));
		}
	}
	/*播放currentIndex对应的声音*/
	private void playCurrentIndexMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.level10_music_position, currentIndex);
		if(i != -1){
			playMusicOnly(MediaCommon.getLevel10Mp3(i));
		}
	}
	/*播放跟我读*/
	private void playReadAfterme(){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.level10_readafterme, currentIndex);
		if(i != -1){
			MediaCommon.playReadAfterMe(mContext);
		}
	}
	/*点点这里*/
	private void playClickHere(int index){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.level10_clickhere, index);
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
        {   
        	if(mMediaPlayer2 != null){
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
                    	mMediaPlayer2 = null;
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
	
	private void playGood(){	//待完成
		MediaCommon.playGuzhang(mContext);	
		clickExecute(Constant.timeLevel10[currentIndex][3]);
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
		if(null != mMediaPlayer && !mMediaPlayer.isPlaying() && !(currentIndex >=MusicPosition.jingyin_10_Index1 && currentIndex<=MusicPosition.jingyin_10_Index2)){
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
			startTime = Constant.timeLevel10[seekTo][0] - offset_time;
			endTime = Constant.timeLevel10[seekTo][1] - offset_time;
			
			video.seekTo(Constant.timeLevel10[seekTo][0] - offset_time);
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
				if(index == MusicPosition.lastIndex10)
					showBtnAfterByPosition();
				/*------点点这里声音------*/
				playClickHere(index);
				//开启背景音
				if(MusicPosition.lastIndex10 == index)
					playMusic(MediaCommon.getLevel10Mp3(0));   //背景音乐播放
				
				/*------不共用  btn_after专用------*/
				showBtnafterAndShouByCurrentIndex();
				break;
			case 1:
				int index2 = (Integer)msg.obj;
				visibleScripte(index2);
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
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra("level", 12));
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
 			if(currentIndex > 34 && currentIndex<=iPpCircle[3]){
 				//不共用 泡泡专用——关闭泡泡
 				/*setQiPaoVisible(false);
 				clickExecute(Constant.timeLevel10[iPpCircle[3]][3]);
 				MediaCommon.playSayit(mContext);*/
 				gotoSuccess();
 			}else if(currentIndex == 162){
 				//此处跳转到 showtime
 				gotoSuccess();
 			}
 			
 		}
 			
		if(haveClicked)return;
		haveClicked = true;
		
		playClickMusic();
		
		if(v == btn_shou){
			if(currentIndex == 34){
 				stopBtafterAndShou();
 				//不共用 泡泡专用——显示泡泡
 				setQiPaoVisible(true);
 			}else if(currentIndex == iPpCircle[3]&&!shouIsPaopao){
 				//不共用 泡泡专用——关闭泡泡
 				/*setQiPaoVisible(false);
				MediaCommon.playSayit(mContext);*/
 				gotoSuccess();
 				return;
 			}else if(currentIndex > 136){
 				//此处跳转到 showtime
 				//此处跳转到 showtime
 				gotoSuccess();
				return;
 			}
			if(currentIndex == iPpCircle[0] || currentIndex == iPpCircle[1] || currentIndex == iPpCircle[2] || currentIndex == iPpCircle[3]){
				clickPaoPao++;
			}
			if(clickPaoPao == 0 || currentIndex>130 || (currentIndex == 130 && clickPaoPao>=4)){
				stopBtnAfter();
			}
			if(clickPaoPao<=4 &&currentIndex == iPpCircle[3]){
				setSelectedPaopao(0);
			}else if(currentIndex == iPpCircle[0]){
				setSelectedPaopao(1);
			}else if(currentIndex == iPpCircle[1]){
				setSelectedPaopao(2);
			}else if(currentIndex == iPpCircle[2]){
				setSelectedPaopao(3);
			}
			if(shouIsPaopao && currentIndex == iPpCircle[3]){
				clickExecute(papapStartIndex);
			}else{
				clickExecute(Constant.timeLevel10[currentIndex][3]);
			}
									
 		}
		//不共用 泡泡专用——
		if(v == iv1_11){
			setSelectedPaopao(0);
			clickExecute(FixedPosition.level10_paopao_index[0]);	
		}else if(v == iv1_21){
			setSelectedPaopao(1);
			clickExecute(FixedPosition.level10_paopao_index[1]);	
		}else if(v == iv1_31){
			setSelectedPaopao(2);
			clickExecute(FixedPosition.level10_paopao_index[2]);	
		}else if(v == iv1_41){
			setSelectedPaopao(3);
			clickExecute(FixedPosition.level10_paopao_index[3]);
		}
		
		if(v == iv1_11 || v == iv1_21 || v == iv1_31 || v == iv1_41){
			clickPaoPao++;
		}
	}  
}
