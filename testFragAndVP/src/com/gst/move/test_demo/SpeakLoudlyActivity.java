package com.gst.move.test_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.MyToast;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.adapter.SpeakLoudlyAdapter;
import com.gst.move.base.Speak;
import com.gst.move.base.WearProducts;
import com.gst.move.utils.CacheSp;

import java.util.ArrayList;
import java.util.List;

/**
 *  磨耳大声说
 */
public class SpeakLoudlyActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rlLayout;
    private ImageView ivBack, ivTopPic, ivBible;
    private ListView listView;
    private SpeakLoudlyAdapter adapter;
    private Context mContext;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Speak speak;
    private List<WearProducts> listWear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speak_loudly);

        init();
        setView();
    }

    private void init() {
        mContext = SpeakLoudlyActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        listWear = new ArrayList<WearProducts>();
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivTopPic = (ImageView) findViewById(R.id.iv_top_pic);
        ivBible = (ImageView) findViewById(R.id.iv_wear_bible);
        listView = (ListView) findViewById(R.id.list_view);

        adapter = new SpeakLoudlyAdapter(mContext, listWear, scaleQPW, scaleQPH);
        listView.setAdapter(adapter);

        setViewPosition(ivTopPic, 0);
        setViewPosition(ivBible, 1);
        swingBible(ivBible);
        ivBack.setOnClickListener(this);
        ivBible.setOnClickListener(this);
        cacheData();
        threadSpeak();
    }

    private void cacheData() {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    String cachResult = new CacheSp().spGetSpeakLoudly(mContext);
                    if(!StringBiz.isEmpty(cachResult)) {
                        Speak speak = new Speak().parseSpeak(mContext, cachResult, true);
                        handler.sendMessage(handler.obtainMessage(0, speak));
                    }
                }
            }).start();
    }

    private void threadSpeak() {
        if(!new MyToast().hasInternetConnection(mContext)) {
            new MyToast().showTextToast(mContext, getString(R.string.network_anomaly));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO 添加数据缓存
                Speak speak = new Speak().getLoudlyList(mContext);
                if(speak != null)
                    handler.sendMessage(handler.obtainMessage(0, speak));
            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    speak = (Speak) msg.obj;
                    if(speak != null) {
                        String errCode = speak.getErrorCode();
                        String errMsg = speak.getErrorMsg();
                        if(StringBiz.isEmpty(errMsg)) {
                            List<WearProducts> listWear = speak.getList();
                            if(listWear != null && listWear.size() > 0) {
//                                List<WearProducts.Products> listProducts = speak.getList().get(0).getListProducts();
//                                List<WearProducts.Items> listItems = speak.getList().get(0).getListItems();
//                                String name = speak.getList().get(0).getName();
                                adapter = new SpeakLoudlyAdapter(mContext, listWear, scaleQPW, scaleQPH);
                                listView.setAdapter(adapter);
                            }
                        } else {
                            if(!StringBiz.isEmpty(errCode) && errCode.equals("401")) {
                                // TOOD 去登录

                            } else {
                                new MyToast().showTextToast(mContext, errMsg);
                            }
                        }
                    }
                    break;
            }
        }
    };

    // 左右晃动
    private void swingBible(final ImageView iv) {
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_2);
        iv.startAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(shake);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.speak_loudly_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        } else if(v == ivBible) { // 磨耳宝典
            if(speak != null) {
                String videoUrl = speak.getTop_video_url();
                if(!StringBiz.isEmpty(videoUrl)) {
                    startActivity(new Intent(mContext, VideoPlayActivity.class).putExtra("path", videoUrl));
                }
            } else {
                // TODO 获取数据失败
            }
        }
    }

}
