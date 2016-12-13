package com.ebodoo.raz.utils;

import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MyEbookRecorderService extends Service{
	
	private MyBinder myBinder;
	
	public static MediaPlayer mMediaPlayer=null;
	
	public static SeekBar progressBar;
	
	public static TextView recorderTime;
	
	public int mAudioTime=0;   //音频的时间
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return myBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//mMediaPlayer=new MediaPlayer();
		myBinder =new MyBinder();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try{
			mMediaPlayer.stop();
			mMediaPlayer.release();
		}catch(Exception e){
			
		}
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	public class MyBinder extends Binder{
        // 播放
		public void play(String path) {
			MyEbookRecorderService.this.play(path);
		}

		// 停止
		public void cease() {
			MyEbookRecorderService.this.cease();
		}

		// 重播
		public void reset(String path) {
			MyEbookRecorderService.this.reset(path);
		}

		// 暂停
		public void suspend() {
			MyEbookRecorderService.this.suspend();
		}
	}
	
	
	
	// 停止
	public void cease() {
		try{
			if(mMediaPlayer != null){
				// 停止
				mMediaPlayer.stop();
				
				// 释放资源
				mMediaPlayer.release();
				mMediaPlayer=null;
			}
			
			//初始化
			progressBar.setProgress(0);
			recorderTime.setText("00:00/"+updateTime(mAudioTime));
		}catch(Exception e){
			
		}
		
		
	}
	
	// 重播
	public void reset(String path) {
		// 停止
		mMediaPlayer.release();
 	    mMediaPlayer=null;	
		// 调播放方法
		play(path);
	}
	

	// 暂停
	public void suspend() {
		mMediaPlayer.pause();
	}
	
    /* 
     * 获取音频当前播放时间格式化后的字符串 
     */  
    public String updateTime(int t) {  
    	 Date dat=new Date(t);  
         GregorianCalendar gc = new GregorianCalendar();   
         gc.setTime(dat);  
         java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("mm:ss");  
         String sb=format.format(gc.getTime());  
         return sb;
    } 
	
	//播放
	public void play(String path){
		   if(mMediaPlayer==null){
		    	    mMediaPlayer=new MediaPlayer();
		      try   
		        {   
		            mMediaPlayer.reset();  
		            mMediaPlayer.setDataSource(path);   /* 设置要播放的文件的路径 */   
		            mMediaPlayer.setLooping(false);
		            mMediaPlayer.prepare(); 
		            progressBar.setOnSeekBarChangeListener(new myjindu());
	
		        }catch(Exception e){
		        	
		        }
		   }
	        try   
	        {
	            mMediaPlayer.start(); 
	            new Thread(run).start();
	            mAudioTime=mMediaPlayer.getDuration();
	            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()    
	            {
	                public void onCompletion(MediaPlayer arg0)   
	                {   
	                	try{
	                		if(arg0 != null) {
		                    	mMediaPlayer.reset();
		                    	mMediaPlayer.release(); 
		                    	mMediaPlayer=null;
		                    }
	                	}catch(Exception e)
	        	        {
	        	            
	        	        } 
	                }   
	            });
	        }catch(Exception e)
	        {
	            
	        } 
	}
	
	
	// 设置进度
	public class myjindu implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			if (fromUser) {
				System.out.println(progress + "---" + fromUser);
				// 设置播放进度
				mMediaPlayer.seekTo(progress);
				//更新时间
				handler.sendMessage(handler.obtainMessage(1));
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

	}
	
	
	
	Handler progressHandler = new Handler();
	Runnable run = new Runnable() {

		public void run() {
			if (mMediaPlayer == null) {
				//mMediaPlayer = new MediaPlayer();
				return;
			}
			//更新时间
			handler.sendMessage(handler.obtainMessage(1));
			// 设置最大值
			int max = mMediaPlayer.getDuration();
			System.out.println(max);
			progressBar.setMax(max);
			// 设置进度条
			int currentPosition = mMediaPlayer.getCurrentPosition();
			System.out.println(currentPosition);
			progressBar.setProgress(currentPosition);
			// 每隔100 milliseconds更新进度条
			if (mMediaPlayer.isPlaying()) {
				progressHandler.postDelayed(run, 100);
			}
		}
	};
	
	
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				try{
					if(mMediaPlayer!=null){
						recorderTime.setText(updateTime(mMediaPlayer.getCurrentPosition())+"/"+updateTime(mMediaPlayer.getDuration()));
					}
		        }catch(Exception e){
		        	
		        }
				break;

			default:
				break;
			}
		}
		
	};

}
