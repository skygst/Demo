package com.gst.move.basic;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.ebodoo.raz.BaseActivity;
import com.gst.move.R;


public class SuccessSayitActivity extends BaseActivity{
	private ImageView star;
	private AnimationDrawable animationDrawable;  
	private int level = 0;
	private MediaPlayer mMediaPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.success_sayit);
		
		initData();
		star = (ImageView)findViewById(R.id.star);
		star.setVisibility(View.VISIBLE);
		animationDrawable=(AnimationDrawable) star.getBackground();
		animationDrawable.start();
		playMusic();
		delayFinish();
	}
	private void initData(){
		//保存学习记录
		level = this.getIntent().getIntExtra("level", 0);
		
	}
	private void delayFinish(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3500);
					handler.sendMessage(handler.obtainMessage(0));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
	}

	/*声音循环播放*/
	private void playMusic()   
    {  
		
	 try   
        {   
		 	mMediaPlayer = MediaPlayer.create(this,
					R.raw.qi_jinbi);
		 	mMediaPlayer.setLooping(false);
            mMediaPlayer.start();   
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer arg0)   
                {   
                	 if(mMediaPlayer != null) {
	                    	mMediaPlayer.release();
	                 }
                }   
            });
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					return false;
				}
			});
        }catch(Exception e)
        {
            e.printStackTrace();
        } 
       
    }  
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			switch (msg.what) {
			case 0:
				finish();
				break;
			
			default:
				break;
			}
		}

	};
}
