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
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;

public class Level6Activity  extends BaseActivity implements OnClickListener {
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
	
	/*-----------------视图----------------------*/
	private ImageView ivBack, btn_shou,bt_after;
	
	/*-----------------语音识别----------------------*/
	private String thisChar = "T";	//当前语音识别的字母
	private boolean mInitialized = false;
	private final String TAG = "Level6Activity";
	Thread recThread;
	private boolean mIsVrRuning = false;	//语音识别是否打开了
	/*-----------------左侧4个泡泡专用----------------------*/ 
	private boolean have4Treasure = false;
	private ImageView iv1,iv2,iv3,iv4,iv1_11,iv1_21,iv1_31,iv1_41,paopao_selected;
	private int clickPaoPao = 0;		//泡泡被点击的次数
	private int iPpCircle[] = {25,33,45,53,59};
	private boolean shouIsPaopao = true;
	private int papapStartIndex = 21;
	private boolean secondPlay = false;//播放完一个，接着播放第二个,防止第二个读音重复读取
	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_06);

		initializationData();
		offset_time = Tools.delayTime();
		setView();
	}
	/*-----------------初始化----------------------*/
	/*数据初始化*/
	private void initializationData() {		
		mContext = Level6Activity.this;
		scaleQPW = (width/1920.0f);
		scaleQPH = (height/1080.0f);
		
		biz = new VideoBiz();
		
		path = Constant.path_raz02 + "level06.mp4";
		currentIndex = 0;//5
		startTime = Constant.timeLevel06[currentIndex][0];
		endTime = Constant.timeLevel06[currentIndex][1];
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
		//背景音乐
//	    playMusic(MediaCommon.getLevel06newMp3(6));
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz02 + "bg_music_level06.mp3");
		
		/*----------泡泡专用  不共用----------*/
		setPaopaoView();	
		videoCycle(clickNum);
		biz.playVideo(video, path);		
//		CommonUtil.downLoadRes(mContext, 1, 4, Constant.download_quiz_02, commonPath, "raz02");
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
		
		iv1.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao"));
		iv2.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao"));
		iv3.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao"));
		iv4.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao"));
		paopao_selected.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao_selected"));
		iv1_11.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "iv_lv6_1"));
		iv1_21.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "iv_lv6_2"));
		iv1_31.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "iv_lv6_3"));
		iv1_41.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "iv_lv6_4"));
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
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(FixedPosition.paopao_position_06new[i][0],FixedPosition.paopao_position_06new[i][1], FixedPosition.paopao_position_06new[i][2], FixedPosition.paopao_position_06new[i][3], scaleQPW ,scaleQPH,xoffset,yoffset,suoxiao));						
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
		Log.d("TESTQQ", "clickPaoPao:"+clickPaoPao);
		/*-------此处的 数字 不共用 需要根据实际修改-------*/
		if(currentIndex == iPpCircle[4]||currentIndex==95){
			showBtnAfterByPosition();
		}
		if(clickPaoPao > 3&&(currentIndex== 91 ||currentIndex== 92 ||currentIndex== 93 ||currentIndex== 94 ||currentIndex== 59)&&have4Treasure){
			showBtnAndShouByPosition(3);
			if(clickPaoPao > 3){
				shouIsPaopao = false;
			}
		}
		
		if(currentIndex==iPpCircle[4]&&clickPaoPao < 3){
			showBtnAndShouByPosition(9);
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
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(FixedPosition.level06new_position[i][0],FixedPosition.level06new_position[i][1], FixedPosition.level06new_position[i][2], FixedPosition.level06new_position[i][3], scaleQPW ,scaleQPH,xoffset,yoffset,suoxiao));						
	}
	/*显示手动画*/
	private void showBtnAndShou(){
		int i = FixedPosition.getPositionIndex(FixedPosition.level06new_index_to_position, currentIndex);
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
		Log.d("TESTQQ", "have4Treasure:"+have4Treasure);
		playCurrentIndexMusic();    // currentIndex 对应的声音
		playReadAfterme();          //跟我读
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

				if (Constant.timeLevel06[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
					    isClicked=false;
					    handler.sendMessage(handler.obtainMessage(0, currentIndex));
						
					    if(!have4Treasure && currentIndex == iPpCircle[4]){
					    	have4Treasure=true;
					    }
					}
//				}

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

			
				//playCurrentIndexMusic();
			if(tem_num == clickNum){
//				setStartVr();  //打开语音识别
//				/*-------------泡泡专用 不共用--------------*/
				if(!have4Treasure && (currentIndex == iPpCircle[0] || currentIndex == iPpCircle[1] || currentIndex == iPpCircle[2] || currentIndex == iPpCircle[3])){
					int index = currentIndex;
					handler.sendMessage(handler.obtainMessage(1,index));
				}
				if(Constant.timeLevel06[currentIndex][4] == 0){
					if(have4Treasure&&currentIndex==29){
						currentIndex=91;
					}else if(have4Treasure&&currentIndex==41){
						currentIndex=92;
					}else if(have4Treasure&&currentIndex==49){
						currentIndex=93;
					}else if(have4Treasure&&currentIndex==59){
						currentIndex=94;
					}else{
						currentIndex = Constant.timeLevel06[currentIndex][3];
						
					}
				}
//				if(!have4Treasure && (currentIndex == iPpCircle[0] || currentIndex == iPpCircle[1] || currentIndex == iPpCircle[2]||currentIndex == iPpCircle[3])){
//				    if(currentIndex == iPpCircle[3]){
//				    	have4Treasure = true;
//				    }
//					currentIndex = Constant.timeLevel06[currentIndex][3];
//				}
				startTime = Constant.timeLevel06[currentIndex][0] - offset_time;
				endTime = Constant.timeLevel06[currentIndex][1] - offset_time;
				handler.sendMessage(handler.obtainMessage(2,tem_num));
					
			}
			
		} catch (Exception e) {
			
		}
	}
	
	
	
	
	
	/*----------------------声音  共用部分----------------------------*/
	
	/*播放click事件对应的声音*/
	private void playClickMusic(){		
	int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.level06new_click_music_position, currentIndex);
		if(i != -1){
			secondPlay=false;
			Log.d("TESTQQ", "currentIndex-------------对应的声音:"+currentIndex);
			playMusicOnly(MediaCommon.getLevel06newMp3(i));
		}
	}
	/*播放currentIndex对应的声音*/
	private void playCurrentIndexMusic(){		
		int i = MusicPosition.getClickMusicPositionIndex(MusicPosition.level06new_music_position, currentIndex);
		if(i != -1){
			Log.d("TESTQQ", "currentIndex对应的声音:"+currentIndex);
			secondPlay=false;
			playMusicOnly(MediaCommon.getLevel06newMp3(i));
		}
	}
	/*播放跟我读*/
	private void playReadAfterme(){		
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.level06new_readafterme, currentIndex);
		if(i != -1){
			MediaCommon.playReadAfterMe(mContext);
		}
	}
	/*点点这里*/
	private void playClickHere(int index){		
		Log.d("WWWW", "点点这里DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD:"+index);
		int i = MusicPosition.getReadAftermeMusicPositionIndex(MusicPosition.level06new_clickhere, index);
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
                    if(currentIndex==1 ||currentIndex==7 ||currentIndex== 11 || currentIndex ==15 || currentIndex == 23 ||currentIndex == 31 ||currentIndex == 43 ||currentIndex == 51){
                    	if(!secondPlay){
                            secondPlay=true;
                    		handler.sendMessage(handler.obtainMessage(10,currentIndex));
                    	}
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
	private void sayit3s1(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					handler.sendMessage(handler.obtainMessage(3));	
					Thread.sleep(2000);
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
		clickExecute(Constant.timeLevel06[currentIndex][3]);
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
	
	// 点击执行的功能---------------------------------------------------------------------------------
	private void clickExecute(int seekTo) {
		
		isCycle = false;
		clickNum++;
		startTime = Constant.timeLevel06[seekTo][0] - offset_time;
		endTime = Constant.timeLevel06[seekTo][1] - offset_time;
		
		video.seekTo(Constant.timeLevel06[seekTo][0] - offset_time);
		isClicked = true;
		currentIndex = seekTo;
		
		videoCycle(clickNum);
		isCycle = true;
	}
	/*-----------------Handler onClick----------------------*/
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				int index1 = (Integer)msg.obj;
				isClicked = false;
				/*------共用------*/
				
				showBtnAndShou();	//小手
				
				
//				playClickMusic();
				
				playClickHere(index1);
				
				/*------不共用------*/
				//showTwoSelectOne();	//二选一
				showBtnafterAndShouByCurrentIndex();
				break;
			case 1:
				int index = (Integer)msg.obj;
				visibleScripte(index);
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
			case 10:
				int index10 = (Integer)msg.obj;
				if(index10 == 23){
					playMusicOnly(MediaCommon.getLevel06newMp3(2));
				}else if(index10 == 31){
					playMusicOnly(MediaCommon.getLevel06newMp3(3));
				}else if(index10 == 43){
					playMusicOnly(MediaCommon.getLevel06newMp3(4));
				}else if(index10 == 51){
					playMusicOnly(MediaCommon.getLevel06newMp3(5));
				}else if(currentIndex == 1 ||currentIndex == 7 ||currentIndex == 11 || currentIndex == 15){
					playMusicOnly(MediaCommon.getLevel06newMp3(1));
				}
				break;
			default:
				break;
			}
		}

	};
	
	private void gotoSuccess(){
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra("level", 7));
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
 		}/*else if(v == bt_after){
 			
 		}*/
 			
		
		if(v==bt_after){
		  if(currentIndex>MusicPosition.jingyinIndex106new&&currentIndex<=MusicPosition.jingyinIndex206new||currentIndex==95){
			//跳转到MV
			  gotoSuccess();
		  }else{
			  gotoSuccess();
			/*clickExecute(61);	
			//stopBtafterAndShou();
				//不共用 泡泡专用——关闭泡泡
			setQiPaoVisible(false);
			
			stopBtnAfter();
			
			MediaCommon.playSayit(mContext);*/
		  }
		}
		
		if(haveClicked)return;
		haveClicked = true;
		
		
		playClickMusic();
		


		
		
		if(v == btn_shou){
			//clickExecute(Constant.timeLevel06[currentIndex][3]);
			if(currentIndex == 27){
 				//不共用 泡泡专用——显示泡泡
 				setQiPaoVisible(true);
 			}else if(currentIndex == iPpCircle[4]&&!shouIsPaopao){
		
 				//不共用 泡泡专用——关闭泡泡
 				setQiPaoVisible(false);
 			}else if(currentIndex >=117){
 				//此处跳转到 showtime
 				
 			}
			if(have4Treasure&&(currentIndex == 91 || currentIndex == 92 || currentIndex == 93 || currentIndex == 94 ||currentIndex==59)){
				clickPaoPao++;
			}
			if(clickPaoPao<=4 &&currentIndex == iPpCircle[4]){
				setSelectedPaopao(0);
			}else if(currentIndex == 91){ 
				setSelectedPaopao(1);
			}else if(currentIndex == 92){
				setSelectedPaopao(2);
			}else if(currentIndex == 93){
				setSelectedPaopao(3);
			}
			if(shouIsPaopao && currentIndex == iPpCircle[4]){
				clickExecute(papapStartIndex);
			}else if(currentIndex>MusicPosition.jingyinIndex106new&&currentIndex<=MusicPosition.jingyinIndex206new||currentIndex==95){
				//跳转到MV
				gotoSuccess();
			}else if(!shouIsPaopao&&clickPaoPao>=3){
				/*MediaCommon.playSayit(mContext);
				setQiPaoVisible(false);
				clickExecute(61);
				
				stopBtnAfter();*/
				gotoSuccess();
			}
			else{
				clickExecute(Constant.timeLevel06[currentIndex][3]);
			}
		}
		
		//不共用 泡泡专用——
			if(v == iv1_11){
				setSelectedPaopao(0);
				clickExecute(papapStartIndex);
			}else if(v == iv1_21){
				setSelectedPaopao(1);
				clickExecute(31);
			}else if(v == iv1_31){
				setSelectedPaopao(2);
				clickExecute(43);
			}else if(v == iv1_41){
				setSelectedPaopao(3);
				clickExecute(51);
			}
			
			if(v == iv1_11 || v == iv1_21 || v == iv1_31 || v == iv1_41){
				shouIsPaopao=true;
				clickPaoPao++;
				//clickExecute(Constant.timeLevel06[currentIndex][3]);	
			}         

			Log.d("clickPaoPao","06new_clickPaoPao:"+clickPaoPao);
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
		
		if(null != mMediaPlayer && !mMediaPlayer.isPlaying() && !(currentIndex >MusicPosition.jingyinIndex106new && currentIndex<MusicPosition.jingyinIndex206new)){
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
			// TODO: handle exception
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
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
		}catch(Exception e){
			
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
