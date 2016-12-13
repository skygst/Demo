package com.gst.move.level_c_game;

import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.utils.DisplayUtil;

/**
 *  The Woodsy Band Jam
 */
public class EbookLevelCGame10Activity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivStump1, ivStump2, ivStump3, ivStump4, ivLocation1, ivLocation2, ivLocation3, ivLocation4;
    private ImageView ivBack, ivAnswer, ivMusicalInstruments1, ivMusicalInstruments2, ivMusicalInstruments3, ivMusicalInstruments4;
    private ImageView[] ivCard, ivMusicalInstruments, ivLocation;
    private TextView tvSentnce;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "woodchuck", "elk", "bear", "owl", "rabbit", "raccoon", "fox"
    };
    private String[] musicalInstrumentsName = new String[] {
            "baton", "horn", "banjo", "fiddle", "fife", "drum", "sing"
    };
    private String[] questionArray = new String[]{
            "let’s jam! he said.",
            "Out came Elk. I’ll play my horn.",
            "Out came Bear. I’ll play my banjo.",
            "Out came Owl. I’ll play my fiddle.",
            "Out came Rabbit. I’ll play my fife.",
            "ut came Raccoon. I’ll play my drum.",
            "Out came Fox. I’ll sing.",
    };
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;
    private int[] randArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_10);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame10Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        // TODO 播放引导语
        playSentence(path + "level_c_10_guide_language.mp3");
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivAnswer = (ImageView) findViewById(R.id.iv_answer);
        // card
        ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
        ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
        ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
        ivCard4 = (ImageView) findViewById(R.id.iv_card_4);

        ivLocation1 = (ImageView) findViewById(R.id.iv_location_1);
        ivLocation2 = (ImageView) findViewById(R.id.iv_location_2);
        ivLocation3 = (ImageView) findViewById(R.id.iv_location_3);
        ivLocation4 = (ImageView) findViewById(R.id.iv_location_4);

        // 树桩
        ivStump1 = (ImageView) findViewById(R.id.iv_stump_1);
        ivStump2 = (ImageView) findViewById(R.id.iv_stump_2);
        ivStump3 = (ImageView) findViewById(R.id.iv_stump_3);
        ivStump4 = (ImageView) findViewById(R.id.iv_stump_4);

        // 乐器
        ivMusicalInstruments1 = (ImageView) findViewById(R.id.iv_musical_instruments_1);
        ivMusicalInstruments2 = (ImageView) findViewById(R.id.iv_musical_instruments_2);
        ivMusicalInstruments3 = (ImageView) findViewById(R.id.iv_musical_instruments_3);
        ivMusicalInstruments4 = (ImageView) findViewById(R.id.iv_musical_instruments_4);

        tvSentnce = (TextView) findViewById(R.id.tv_sentence);

        setViewPosition(ivStump1, 7);
        setViewPosition(ivStump2, 8);
        setViewPosition(ivStump3, 9);
        setViewPosition(ivStump4, 10);

        ivStump1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_stump"));
        ivStump2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_stump"));
        ivStump3.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_stump"));
        ivStump4.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_stump"));

        ivCard = new ImageView[4];
        ivCard[0] = ivCard1;
        ivCard[1] = ivCard2;
        ivCard[2] = ivCard3;
        ivCard[3] = ivCard4;

        ivMusicalInstruments = new ImageView[4];
        ivMusicalInstruments[0] = ivMusicalInstruments1;
        ivMusicalInstruments[1] = ivMusicalInstruments2;
        ivMusicalInstruments[2] = ivMusicalInstruments3;
        ivMusicalInstruments[3] = ivMusicalInstruments4;

        ivLocation = new ImageView[4];
        ivLocation[0] = ivLocation1;
        ivLocation[1] = ivLocation2;
        ivLocation[2] = ivLocation3;
        ivLocation[3] = ivLocation4;

        for(int i=0; i<4; i++) {
            setViewPosition(ivCard[i], (i+0));
            setViewPosition(ivLocation[i], (i+21));
            setViewPosition(ivMusicalInstruments[i], (i+14));
            ivMusicalInstruments[i].setOnTouchListener(this);
        }

        setViewPosition(ivStump1, 7);
        setViewPosition(ivStump2, 8);
        setViewPosition(ivStump3, 9);
        setViewPosition(ivStump4, 10);

        showPic(0);
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "band_bg"));
        ivBack.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_10_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void showPic(int index) {
        selNum = 0;
        ivCard1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + cardName[index + 0] + "_1"));
        ivCard2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + cardName[index + 1] + "_1"));
        ivCard3.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + cardName[index + 2] + "_1"));

        if(index > 0) {
            for(int i=0; i<3; i++) {
                setViewPosition(ivCard[i], (i+4));
                setViewPosition(ivLocation[i], (i+25));
                setViewPosition(ivMusicalInstruments[i], (i+18));
                ivMusicalInstruments[i].setEnabled(true);

            }
            setViewPosition(ivStump1, 11);
            setViewPosition(ivStump2, 12);
            setViewPosition(ivStump3, 13);
            randArray = BaseCommon.getList(3);
            ivCard4.setVisibility(View.GONE);
            ivStump4.setVisibility(View.GONE);
            ivLocation4.setVisibility(View.GONE);
            ivMusicalInstruments4.setVisibility(View.GONE);
        } else {
            randArray = BaseCommon.getList(4);
            ivCard4.setVisibility(View.VISIBLE);
            ivMusicalInstruments4.setVisibility(View.VISIBLE);
            ivCard4.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + cardName[3] + "_1"));
            ivMusicalInstruments4.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + musicalInstrumentsName[randArray[3]]));
        }
        tvSentnce.setText("");
        ivMusicalInstruments1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + musicalInstrumentsName[index + randArray[0]]));
        ivMusicalInstruments2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + musicalInstrumentsName[index + randArray[1]]));
        ivMusicalInstruments3.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + musicalInstrumentsName[index + randArray[2]]));
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        }
    }

    private void delayToNext(final int caseId, final long time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    handler.sendMessage(handler.obtainMessage(caseId));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    showPic(4);
                    break;
                case 1:
                    View v = (View) msg.obj;
                    showSelectedWord(v);
                    break;
                case 2:
                    finish();
                    break;
            }
        }
    };

    private void playSentence(String sentencePath) {
        try {
            mMediaPlayer = MediaPlayerBiz.playSoundMusic(sentencePath, mMediaPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearMedia() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearMedia();
    }

    private int selectedId = 0;
    private float x, y;
    private int mx, my;
    private int mWidth, mHeight;
    private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个
    private int currentId = -1;

    // 把点击的图片置顶到顶层
    private void ivToFront(View v) {
        selectedId = v.getId();
        indexSelect = -1;
        if (v == ivMusicalInstruments1) {
            currentId= 0;
            ivMusicalInstruments1.bringToFront();
            indexSelect = getRand(0);
        } else if(v == ivMusicalInstruments2) {
            currentId= 1;
            ivMusicalInstruments2.bringToFront();
            indexSelect = getRand(1);
        } else if(v == ivMusicalInstruments3) {
            currentId= 2;
            ivMusicalInstruments3.bringToFront();
            indexSelect = getRand(2);
        } else if(v == ivMusicalInstruments4) {
            currentId= 3;
            ivMusicalInstruments4.bringToFront();
            indexSelect = getRand(3);
        }
    }

    private int getRand(int index) {
        for(int i=0; i<ivCard.length; i++) {
            if(randArray[index] == i) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (selectedId != 0 && selectedId != v.getId())
            return true;
        ivToFront(v);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                mWidth = v.getWidth();
                mHeight = v.getHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                mx = (int) (event.getRawX() - x);
                my = (int) (event.getRawY() - DisplayUtil.dip2px(mContext, 25) - y);
                if (mx + mWidth > width)
                    mx = width - mWidth;
                if (my + mHeight > height)
                    my = height - mHeight;
                v.setLayoutParams(LayoutParameters.setViewPositionParams(mWidth,
                        mHeight, mx, my));
                break;

            case MotionEvent.ACTION_UP:
                // 拿到红色框框在屏幕的Y X 坐标（dp）
                final int[] location = new int[2];
                Rect viewRect = new Rect(); // 定义一个矩形区域
                if (indexSelect == 0) {//
                    // 计算该视图在全局坐标系中的x，y值，（注意这个值是要从屏幕顶端算起，也就是索包括了通知栏的高度）//获取在当前屏幕内的绝对坐标
                    ivLocation1.getLocationOnScreen(location);
                    ivLocation1.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
                    // 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
                } else if(indexSelect == 1) {
                    ivLocation2.getLocationOnScreen(location);
                    ivLocation2.getGlobalVisibleRect(viewRect);
                } else if(indexSelect == 2) {
                    ivLocation3.getLocationOnScreen(location);
                    ivLocation3.getGlobalVisibleRect(viewRect);
                } else if(indexSelect == 3) {
                    ivLocation4.getLocationOnScreen(location);
                    ivLocation4.getGlobalVisibleRect(viewRect);
                }
                // View otherView = v;
                Rect otherViewRect = new Rect();
                v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
                // 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
                // 判断这两个坐标是否重叠 判断这两个对象是否有重叠

                // TODO 添加判断
                if ((indexSelect != -1)
                        && Rect.intersects(viewRect, otherViewRect)) {
                    setBestPosition(v, mx, my);
                } else {
                    int index = 0;
                    if(clickNum > 0) {
                        index = 4;
                    }
                    if (v == ivMusicalInstruments1) {
                        setViewPosition(v, 14 + index);
                    } else if (v == ivMusicalInstruments2) {
                        setViewPosition(v, 15 + index);
                    } else if (v == ivMusicalInstruments3) {
                        setViewPosition(v, 16 + index);
                    } else if (v == ivMusicalInstruments4) {
                        setViewPosition(v, 17 + index);
                    }
                    MediaCommon.playFuxiError(mContext);
                    // 保护的用途
                    selectedId = 0;
                }
                break;
            default:
                break;
        }
        return true;
    }

    // 获得最佳停留位置
    private void setBestPosition(View v, float x, float y) {
        final int[] location = new int[2];
        if (indexSelect >= 0 && indexSelect < 4) {
            ivLocation[indexSelect].getLocationOnScreen(location);
        }
        if (indexSelect != -1) {
            showGoodWord(v);
        }
    }

    private void showGoodWord(final View v) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                handler.sendMessage(handler.obtainMessage(1, v));
            }
        }).start();
    }

    private int selNum;

    private void showSelectedWord(View v) {
        statusImg((ImageView)v);
        selNum++;
        int index = 0;
        if (clickNum < 1 && selNum >= 4) {
            delayToNext(0, 3000);
        } else if(clickNum >= 1 && selNum >= 3) {
            delayToNext(2, 4000);
        }
        if(clickNum >= 1) {
            index = 4;
        }
        System.out.println("------showSelectedWord--indexSelect------------ : " + indexSelect);
        System.out.println("------showSelectedWord--index------------ : " + index);
        if((indexSelect + index) < 7) {
            tvSentnce.setText(questionArray[indexSelect + index]);
        }
        if (clickNum < 1 && selNum >= 4) {
            clickNum = 1;
        }
        // TODO 读音
        playSentence(path + "woodsy_" + cardName[indexSelect + index] + ".mp3");
        // 保护的用途
        selectedId = 0;
    }

    private void statusImg(ImageView ivImg) {
        ivImg.clearAnimation();
        ivImg.setEnabled(false);
        System.out.println("--------currentId------------ : " + currentId);
        System.out.println("--------indexSelect------------ : " + indexSelect);
        System.out.println("--------randArray[indexSelect]------------ : " + randArray[indexSelect]);
        if(indexSelect != -1) {
            int index = 0;
            if(clickNum > 0) {
                index = 4;
            }
            setViewPosition(ivImg, (currentId + 14 + index));
            ivImg.setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + musicalInstrumentsName[randArray[currentId] + index] + "_sel"));
            ivCard[indexSelect].setImageDrawable(CommonBitmap.drawableChange(imgPath, "band_" + cardName[indexSelect + index] + "_2"));
        }
    }

}
