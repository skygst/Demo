package com.gst.move.level_c_game;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.FileBiz;
import com.ebodoo.raz.utils.CommonSharePreferences;
import com.gst.move.R;

/**
 * Created by gst-pc on 2016/10/12.
 */

public class VideoPlayActivity extends BaseActivity {

    private VideoView video;
    private String path;
//    private MediaController mController;
    private Context mContext;
    private int preservation=0;   //按home键保存播放帧数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 监听Home按键消息
        final IntentFilter homeFilter = new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(homePressReceiver, homeFilter);

        setContentView(R.layout.video_play);
        mContext = VideoPlayActivity.this;
        String commonPath = Environment.getExternalStorageDirectory()
                + "/raz_english/";
        path = commonPath + "reading04/antarctica_trailer.mp4";
        System.out.println("----path-----" + path);
        if(FileBiz.isExists(path)) {
            System.out.println("---------- 存在 -----------");
        } else {
            System.out.println("----------  不存在 -----------");
        }
//        path = getIntent().getExtras().getString("path");
//        path = "http://v.youku.com/v_show/id_XMTU3Nzc3OTEyMA==.html?from=y1.7-1.2&qq-pf-to=pcqq.temporaryc2c";
//		path = "http://k.youku.com/player/getFlvPath/sid/8423550739502122c6810_00/st/flv/fileid/030002010054C89752D7282AC1BB7858808DAB-E295-D911-BCFF-4D6B6630B622?K=b716f1af84dac41f24121a7a&ctype=12&ev=1&oip=2095617680&token=9767&ep=eiaWGUyIVskD7Cbfij8bZX%2FkfHYPXP4J9h%2BFg9JjALshTJu3kDnUxem3TPdAZ4oYAFABGO75o9DvHjFmYYVBoG0Qq0epOPqSjYaV5aknwuIGYBo2AMvVtlSXRDb2";
//		path = "http://k.youku.com/player/getFlvPath/sid/8423550739502122c6810_00/st/flv/fileid/030002010054C897D045BF2AC1BB78CC17C422-009B-CDC2-2029-E08C7FE39EE0?K=d309576d39357cee282a3dca&ctype=12&ev=1&oip=2095617680&token=9767&ep=eiaWGUyIVskD7Cbfij8bZX%2FkfHYPXP4J9h%2BFg9JjALshTJu3kDmlx5m1PIlAZ4oYAFABGJiCqteUbkIWYfBDoBoQrDrbO%2Fri%2FvLq5dhTzOMHEGw1e7iitFSXRDb2";
//		path = "http://k.youku.com/player/getFlvPath/sid/8423550739502122c6810_00/st/flv/fileid/030002010054C897D045BF2AC1BB78CC17C422-009B-CDC2-2029-E08C7FE39EE0?K=d309576d39357cee282a3dca&ctype=12&ev=1&oip=2095617680&token=9767&ep=eiaWGUyIVskD7Cbfij8bZX%2FkfHYPXP4J9h%2BFg9JjALshTJu3kDmlx5m1PIlAZ4oYAFABGJiCqteUbkIWYfBDoBoQrDrbO%2Fri%2FvLq5dhTzOMHEGw1e7iitFSXRDb2";


        setView();
    }

    private void setView() {
        video = (VideoView) findViewById(R.id.video_play);
        if (path != null && !path.equals("")) {
            Toast.makeText(mContext, "加载中...",Toast.LENGTH_LONG).show();
//            mController = new MediaController(this);
            video.setVideoURI(Uri.parse(path));
//            video.setMediaController(mController);
            video.requestFocus();
            video.start();
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // 播放结束后的动作
                    finish();
                }
            });

            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer arg0) {
                    if (path.equals(CommonSharePreferences.getVideoPath(mContext))) {
                        int seekToPosition = CommonSharePreferences
                                .getVideoPosition(mContext);
                        if (seekToPosition != 0) {
                            video.seekTo(seekToPosition);
                        }
                    }

                }
            });
            video.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // TODO Auto-generated method stub
                    return true;
                }
            });
        }else{
            Toast.makeText(mContext, "视频不存在",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setKeepScreenOn(true);
        if (null != video) {
//			video.resume();
            video.start();
            //跳到指定的帧数
            video.seekTo(preservation);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().getDecorView().setKeepScreenOn(false);

        try {
            //暂停时保存播放帧数
            if(video!=null){
                int currentPos = video.getCurrentPosition();
                preservation=currentPos;
                video.pause();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //帧数清零
        preservation=0;
        if (null != video) {
            CommonSharePreferences.setVideoPath(mContext, path);
            int seekToPosition = video.getCurrentPosition();
            if (seekToPosition != 0) {
                CommonSharePreferences.setVideoPosition(mContext, seekToPosition);
            }
            video.stopPlayback();
            video = null;
        }

    }


    private final BroadcastReceiver homePressReceiver = new BroadcastReceiver() {
        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (reason != null
                        && reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                    // 自己随意控制程序，关闭...
                    CommonSharePreferences.setVideoPath(mContext, path);
                    if(video != null){
                        int seekToPosition = video.getCurrentPosition();
                        if (seekToPosition != 0) {
                            CommonSharePreferences.setVideoPosition(mContext, seekToPosition);
                        }
                    }

                }
            }
        }
    };

}
