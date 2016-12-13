package com.gst.move.basic;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.Profile;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonSharePreferences;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.MyToast;
import com.gst.move.R;

public class SuccessActivity extends BaseActivity  implements OnClickListener {
	private Context context;
	private MyVideoView video;
	private String path;
	private ImageView bt_replay, bt_next,iv_guoguan;
	private ImageView iv_level,iv_gongxi,iv_box,bt_home;
	private TextView tv_guanka,tv_jinbi;
	private int startTime = 0,startGuanka = 0;
	private int endTime = 0;
//	private int width, height;
	private boolean isCycle = false;
	private int clickNum = 0;
	private float scaleQP = 1.0f;
//	private float density;
	
	private int preservation=0;   //按home键保存播放帧数
	
	private MediaPlayer mMediaPlayer = null;  
	private int inexttime = 0;
	
	private int level,island_level = 0;
	private boolean mv_play_finish = false;		//MV是否播放完毕
	private boolean show_time_finish = false;
	private boolean baoxiang_finish = false;
	private int videoindex = 0;
	
	private VideoBiz mVideoBiz;
	private float scaleQPW = 1.0f,scaleQPH = 1.0f;
	private boolean isLogin = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.success);
		//mp3path = this.getResources().openRawResource(R.raw.fly);
		
		setData();
		setView();
	}
	
	private void setData(){
		context = SuccessActivity.this;
		scaleQPW = (width/1280.0f);
		scaleQPH = (height/720.0f);
		scaleQP = (width/800.0f);
		inexttime = 0;
//		isLogin = User.isLogin(context);
		isLogin = true;
		
		level = this.getIntent().getExtras().getInt("level");
//		level = 12;
		
		// 判断关卡，大于8加60
		if(level > 8) {
			CommonSharePreferences.setAddMyJinbi(context, 60);
			//已经登陆过的用户，网络不在线，增加的金币备份到本地端
			if(isLogin && !(new MyToast().hasInternetConnection(context))){
				CommonSharePreferences.setAddCoin(context, 60);
			}
		} else {
			CommonSharePreferences.setAddMyJinbi(context, 30);
			if(isLogin && !(new MyToast().hasInternetConnection(context))){
				CommonSharePreferences.setAddCoin(context, 30);
			}
		}
		
		/*int finishLevel = new BaseCommon().getSelectLevel(context);
		if(finishLevel<level){
			new BaseCommon().spSelectLevel(context, level);			
		}*/
		
		CommonSharePreferences.setIslandByLevel(context, level);
		
		if(isLogin && new MyToast().hasInternetConnection(context)){
			getProfile();
		}
		/*path = "android.resource://" + getPackageName() + "/"
				+ R.raw.show_time;*/
		
	}

	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		
		tv_guanka = (TextView) findViewById(R.id.tv_guanka);
		tv_jinbi = (TextView) findViewById(R.id.tv_jinbi);
		iv_guoguan = (ImageView) findViewById(R.id.iv_guoguan);
//		iv_guoguan.setBackgroundResource(BaseCommon.getImageViewId(context, "bg_guoguan"));
		
		bt_replay = (ImageView) findViewById(R.id.bt_replay);
		bt_next = (ImageView) findViewById(R.id.bt_next);
		iv_level = (ImageView) findViewById(R.id.iv_level);
		iv_gongxi = (ImageView) findViewById(R.id.iv_gongxi);
		iv_box = (ImageView) findViewById(R.id.iv_box);
		
		bt_home = (ImageView) findViewById(R.id.bt_home);
		
		mVideoBiz = new VideoBiz();
		mVideoBiz.setViewPositionNoSuoxiao(bt_home, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
		
		bt_home.setOnClickListener(this);
		
		
		showBtnStatus(View.INVISIBLE, View.INVISIBLE,View.INVISIBLE, View.INVISIBLE);
		
		/*int id1,id2,id3,id4;
		id1 =  BaseCommon.getImageViewId(context, "bt_replay");
		id2 = BaseCommon.getImageViewId(context, "bt_next");
		id3 = BaseCommon.getImageViewId(context, "guanka_"+level);
		id4 = BaseCommon.getImageViewId(context, "guanka_success");*/
		
		bt_replay.setImageResource(BaseCommon.getImageViewId(context, "bt_replay"));
		
		iv_gongxi.setImageResource(BaseCommon.getImageViewId(context, "guanka_success"));
		
		iv_level.setLayoutParams(LayoutParameters.getGuankaParams1(height, density, iv_level, R.id.view12,scaleQP));
		iv_gongxi.setLayoutParams(LayoutParameters.getGuankaParams2(height, density, iv_gongxi, R.id.view12,scaleQP));
		
		bt_replay.setLayoutParams(LayoutParameters.getReplayParams(height, density, bt_replay, R.id.view12,scaleQP));
		bt_next.setLayoutParams(LayoutParameters.getNextParams(height, density, bt_next, R.id.view12,scaleQP));
		bt_next.setImageResource(BaseCommon.getImageViewId(context, "bt_home"));
		iv_level.setImageResource(BaseCommon.getImageViewId(context, "guanka_"+level));
		//bt_replay.setImageResource(BaseCommon.getImageViewId(context, "bt_replay"));
		
		
		startTime = Constant.timeSuccess[0][0];
		endTime = Constant.timeSuccess[0][1];
		startGuanka = Constant.timeSuccess[0][1];
		bt_replay.setOnClickListener(this);
		bt_next.setOnClickListener(this);
		iv_box.setOnClickListener(this);
		iv_box.setVisibility(View.GONE);
		
		initVideo();
		
		showGuanka();
		playVideo();
//		PlayMusic();
		//videoCycle();
	}
	
	private void showGuanka(){
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(11000);
					handler.sendMessage(handler.obtainMessage(7));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}
	private void initVideo(){
		if(level == 1){
			path = Constant.path_raz01 + "video_success_level.mp4/";
		}else if(level == 2){
			path = Constant.path_raz01 + "video_success_level_2.mp4/";
		}else if(level == 3){
			path = Constant.path_raz01 + "video_success_level_3.mp4/";
		}else if(level == 5){ //第二个岛
			path = Constant.path_raz02 + "video_success_level_4.mp4/";
		}else if(level == 6){
			path = Constant.path_raz02 + "video_success_level_5.mp4/";
		}else if(level == 7){
			path = Constant.path_raz02 + "video_success_level_6.mp4/";
		} else { //第三个岛及以后
			path = Constant.path_raz01 + "video_success_level.mp4/";
		}  
	}
	private void initGuankaView(){
		iv_guoguan.setBackgroundResource(BaseCommon.getImageViewId(context, "bg_guoguan"));
		
		iv_guoguan.setVisibility(View.VISIBLE);
		tv_jinbi.setVisibility(View.VISIBLE);
		tv_guanka.setVisibility(View.VISIBLE);
		// 大于8显示60
		if(level > 8) {					
			tv_jinbi.setText("60");
			if(level < 14){
				tv_guanka.setText("Level "+ ((level - 2) - (level - 8 )/5));
			}else{
				tv_guanka.setText("Level "+ ((level - 3) - (level - 13 )/6));
			}
			
		} else {
			tv_jinbi.setText("30");
			tv_guanka.setText("Level "+ (level - level/4));
		}
		showBtnStatus(View.GONE, View.GONE,View.VISIBLE, View.VISIBLE);
	}
	
	//上传金币到服务器端
	private void postCoin(final int coin,final boolean isAll){
		new AsyncTask<Object, Object, Profile>() {
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				
			}

			@Override
			protected Profile doInBackground(Object... params) {
				// TODO Auto-generated method stub
				String resultjson;

//				resultjson = CommonHTTP.postRazModifyCoin(context, coin);		
//				if(resultjson == null || resultjson.contains("error_code"))return null;
//				
//				Profile mProfile = new Profile();
//				mProfile = new RazAction()
//						.getProfile(resultjson);
//				
//				return mProfile;
				return null;
			}

			@Override
			protected void onPostExecute(Profile result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if (result != null) {
					if(isAll){
						CommonSharePreferences.setHaveHttpAllCoin(context, true);
					}else{
						CommonSharePreferences.setAddCoin(context, 0);
					}
				} 
			}

		}.execute();
	
	}
	//上传level到服务器端
	private void postLevel(){
		new AsyncTask<Object, Object, Profile>() {
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();

			}

			@Override
			protected Profile doInBackground(Object... params) {
				// TODO Auto-generated method stub
				String resultjson;

				//新增新的随机闯关的岛的level
				island_level = CommonSharePreferences.getIslandAllLevel(context);
				
//				resultjson = CommonHTTP.postRazSaveLevel(
//						context, island_level+"",true);	
//										
//				if(resultjson == null || resultjson.contains("error_code"))return null;
//				
//				Profile mProfile = new Profile();
//				mProfile = new RazAction()
//						.getProfile(resultjson);
//
//				return mProfile;
				return null;
			}

			@Override
			protected void onPostExecute(Profile result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if (result != null) {
					
				} 
			}

		}.execute();
	
	}
	
	//上传level到服务器端
	private void getProfile(){
		new AsyncTask<Object, Object, Profile>() {
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();

			}

			@Override
			protected Profile doInBackground(Object... params) {
				// TODO Auto-generated method stub
				String resultjson;

				//新增新的随机闯关的岛的level
				island_level = CommonSharePreferences.getIslandAllLevel(context);
				
//				resultjson = CommonHTTP.getRazProfile(context);		
//				if(resultjson == null || resultjson.contains("error_code"))return null;
//				
//				Profile mProfile = new Profile();
//				mProfile = new RazAction()
//						.getProfile(resultjson);
//
//				return mProfile;
				return null;
			}

			@Override
			protected void onPostExecute(Profile result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if (result != null) {
//					if(result.coin > 0 || CommonSharePreferences.getHaveHttpAllCoin(context)){
//						if(level>8){
//							postCoin(CommonSharePreferences.getAddCoin(context)+60,false);
//						}else{
//							postCoin(CommonSharePreferences.getAddCoin(context)+30,false);
//						}
//						
//					}else{
//						postCoin(CommonSharePreferences.getMyJinbi(context),true);
//					}
					
					
//					if(result.island_level>0){
//						if(result.island_level != island_level){
//							CommonSharePreferences.addNetAndLocalIslandLevel(context, result.island_level);
//							postLevel();
//							
//						}
//					}else{
//						int level_local = new BaseCommon().getSelectLevel(context);
//						if(result.level >= level_local){
//							CommonSharePreferences.addNetLevelAndIslandLevel(context, result.level);
//							new BaseCommon().spSelectLevel(context, result.level);							
//							postLevel();
//						}else{
//							CommonSharePreferences.addNetLevelAndIslandLevel(context, level_local);
//							postLevel();
//						} 
//						
//					}
				} 
			}

		}.execute();
	
	}
	private void showBtnStatus(int b1, int b2, int b3,int b4) {
		iv_gongxi.setVisibility(b1);
		iv_level.setVisibility(b2);
		bt_next.setVisibility(b3);
		bt_replay.setVisibility(b3);
	}
	
	private void PlayMusic() {  
		mMediaPlayer = MediaPlayer.create(this,BaseCommon.getMp3Id2(context, "showtime"));
//		mMediaPlayer.setLooping(true);
		
		try { 
			mMediaPlayer.prepare();
        } catch (Exception ex) {  
        }  
    	mMediaPlayer.start();// 开始播放 
    }

	// 播放视频
	private void playVideo() {	
		
		if (path != null && !path.equals("")) {
			video.setVideoURI(Uri.parse(path));
			video.requestFocus();		
			/*if(videoindex == 2){
				video.seekTo(startTime);
			}*/
			video.start();
			video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					// 播放结束后的动作
					
				}
			});

			video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer arg0) {

				}
			});
			video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					return true;
				}
			});
			

		}
	}
	/*// 循环播放
	private void videoCycle() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isCycle = true;
				setCirclePlay();
			}
		}).start();
	}*/

	private void setCirclePlay() {
		int currentPos = 0;
		/*if (video != null){
			currentPos = video.getCurrentPosition();
		}*/
		int num = clickNum;
		do {
			
			try {
				//if (clickNum < 3) {					
				Thread.sleep(150);
			//}
				if (!isCycle || video == null)
					break;
				
				currentPos = video.getCurrentPosition();			//空指针null
				if (currentPos >= endTime){				
					break;
				}					
				if (currentPos >= startTime && inexttime == 1) {
					inexttime=2;
					handler.sendMessage(handler.obtainMessage(0));
				}
				if (currentPos >= startGuanka && inexttime >= 2 && inexttime <=3 ) {
					handler.sendMessage(handler.obtainMessage(1));
				}
			} catch (Exception e) {
				
			}
		} while (currentPos < endTime);

		if (currentPos >= endTime && isCycle) {
			try{
				inexttime++;
				if(inexttime > 2)
					handler.sendMessage(handler.obtainMessage(1));
				startTime = Constant.timeSuccess[2][0];
				endTime = Constant.timeSuccess[2][1];
					
				
				video.seekTo(startTime);
				handler.sendMessage(handler.obtainMessage(2));
				handler.sendMessage(handler.obtainMessage(6));
			}catch(Exception e){
				
			}
			//setCirclePlay();
		}
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			Log.d("handle------", "msg--------------");
			//btn5.setVisibility(View.VISIBLE);
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				
				/*showBtnStatus(View.VISIBLE, View.VISIBLE,View.INVISIBLE, View.INVISIBLE);
				iv_box.setVisibility(View.VISIBLE);				
				MediaCommon.PlayMusic(MediaPlayer.create(context,BaseCommon.getMp3Id2(context, "openbox")));*/
				break;
			
			case 4:
				/*if(level == 1){
					path = "android.resource://" + getPackageName() + "/"
							+ R.raw.mv_level_1;
				}else if(level == 2){
					path = "android.resource://" + getPackageName() + "/"
							+ R.raw.mv_level_2;
					path = Constant.path_raz01 + "mv_level_2.mp4/";
				}else if(level == 3){
					path = "android.resource://" + getPackageName() + "/"
							+ R.raw.mv_level_3;
					path = Constant.path_raz01 + "mv_level_3.mp4/";
				}
				else if(level == 5){
					path = Constant.path_raz02 + "mv_level_4.mp4/";
				}else if(level == 6){
					path = Constant.path_raz02 + "mv_level_5.mp4/";
				}else if(level == 7){
					path = Constant.path_raz02 + "mv_level_6.mp4/";
				} else if(level == 9) { // 第三个岛
					path = Constant.path_raz03 + "mv_level_7.mp4/";
				} else if(level == 10) {
					path = Constant.path_raz03 + "mv_level_8.mp4/";
				} else if(level == 11) {
					path = Constant.path_raz03 + "mv_level_9.mp4/";
					
				} else if(level == 12) {
					path = Constant.path_raz03 + "mv_level_10.mp4/";
				}
				else if(level == 14){
					path = Constant.path_raz04 + "mv_level_11.mp4/";
				} else if(level ==15) { // 第三个岛
					path = Constant.path_raz04 + "mv_level_12.mp4/";
				} else if(level == 16) {
					path = Constant.path_raz04 + "mv_level_13.mp4/";
				} else if(level == 17) {
					path = Constant.path_raz04 + "mv_level_14.mp4/";
					
				} else if(level == 18) {
					path = Constant.path_raz04 + "mv_level_15.mp4/";
				}
				playVideo();*/
				break;
			case 6:
				bt_home.setVisibility(View.INVISIBLE);
				break;
			case 7:
				initGuankaView();
				break;
			default:
				break;
			}
		}

	};
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == bt_next){
			
			returnHome();
			finish();
			
		}else if(v == bt_replay){
			if(level == 1){
//				startActivity(new Intent(this, WalkingMoonActivity.class));
				startActivity(new Intent(this, Level1Activity.class));
				
			}else if(level == 2){
				startActivity(new Intent(this, Level2Activity.class));
			}else if(level == 3){
				startActivity(new Intent(this, Level3Activity.class));
			}
			else if(level == 5){
				startActivity(new Intent(this, Level4Activity.class));
			}
			else if(level == 6){
				startActivity(new Intent(this, Level5Activity.class));
			}
			else if(level == 7){
				startActivity(new Intent(this, Level6Activity.class));
			}
			
			else if(level == 9){
				startActivity(new Intent(this, Level7Activity.class));
			}
			else if(level == 10){
				startActivity(new Intent(this, Level8Activity.class));
			}
			else if(level == 11){
				startActivity(new Intent(this, Level9Activity.class));
			}
			else if(level == 12){
				startActivity(new Intent(this, Level10Activity.class));
			}
			
			else if(level == 14){
				startActivity(new Intent(this, Level11Activity.class));
			}
			
			else if(level == 15){
				startActivity(new Intent(this, Level12Activity.class));
			}
			else if(level == 16){
				startActivity(new Intent(this, Level13Activity.class));
			}
			else if(level == 17){
				startActivity(new Intent(this, Level14Activity.class));
			}
			else if(level == 18){
				startActivity(new Intent(this, Level15Activity.class));
			}
			finish();
		}else if(v == iv_box){
			successGoGameBox();
		}else if(v == bt_home){
			returnHome();
		}
		
	} 
	//从这里跳到宝物箱，保存当前的位置
	private void successGoGameBox(){
		int currentPos = video.getCurrentPosition();
		BaseCommon.saveVideoCurrentPosition(context, currentPos, endTime);
		BaseCommon.saveIsSuccessToBox(context, true);
//		startActivity(new Intent(this, GameBoxActivity.class).putExtra("isPlay", false));
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);
		/*if (null != video) {
			if(BaseCommon.getIsSuccessToBox(context)){
				startTime = BaseCommon.getVideoStartTime(context);
				endTime = BaseCommon.getVideoEndTime(context);
				video.seekTo(startTime);
				BaseCommon.saveIsSuccessToBox(context, false);
			}
			video.start();
		}
		if(null != mMediaPlayer && !mMediaPlayer.isPlaying()){
			//mMediaPlayer.start();
		}*/
		
		if (video!=null) {
			video.start();
			//跳到指定的帧数
			video.seekTo(preservation);
		} 

	}
	
	
	private void returnHome(){
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
		}catch(Exception e){
			
		}
		
//		startActivity(new Intent(SuccessActivity.this, SelectLevelActivity.class));
		finish();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		//isClicked = true;
		getWindow().getDecorView().setKeepScreenOn(false);
		try {
			if (null != video) {
				
					if(video!=null){  
						//暂停时保存播放帧数   
						int currentPos = video.getCurrentPosition();
						preservation=currentPos;	
						video.pause();
					}
				
				
			}
			if(mMediaPlayer != null){
				mMediaPlayer.pause();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		MediaCommon.pauseMediaplay();
	}
	
	
	

	@Override
	protected void onDestroy() {
		isCycle = false;
		super.onDestroy();
		try{
			if (null != video) {
				video.stopPlayback();
				video = null;
			}
			if(mMediaPlayer != null){
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
		}catch(Exception e){
			
		}
		
		finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			returnHome();
			finish();
			return true;
		}else if(keyCode == KeyEvent.KEYCODE_HOME){
			//isClicked = true;
			if (null != video) {
				video.pause();
			}
			if(mMediaPlayer != null){
				mMediaPlayer.pause();
			}
		}
			
		return super.onKeyDown(keyCode, event);
	}

}
