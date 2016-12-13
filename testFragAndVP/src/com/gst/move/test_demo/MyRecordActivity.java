package com.gst.move.test_demo;

import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MyToast;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.adapter.MyRecordAdapter;
import com.gst.move.basic.Recorder;
import com.gst.move.basic.RecorderMusic;
import com.gst.move.mall.ProgressLailaiBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MyRecordActivity extends ProgressLailaiBaseActivity implements View.OnClickListener {
    private Context mContext;
    private ImageView ivBack;
    private RelativeLayout rlTitle;
    private TextView tvAll, tvAlreadyComment, tvDel;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private ListView mListview;
    //    private View headView;
    public MyRecordAdapter adapter;
    private List<Recorder> mListRecorder;
    private boolean isDel = false;
    public MediaPlayer mMediaPlayer;
    private boolean currentIsShowAll = true;
    private boolean isOnceBookData = false;
    private String bookId = "", bookType = "";
    private String filter = "all"; // filter [all, review]
    private View footerView;
    private TextView txLoading, txLoaded;
    private ProgressBar pbLoading;
    private String cursor = "0";
    private boolean isNeedPrompt = true; // 需要提示：数据已经取完
    private boolean isShowAll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_record);

        setData();
        setView();
    }

    private void setData() {
        mContext = MyRecordActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        mListRecorder = new ArrayList<Recorder>();
        refreshProgressStart("正在加载中...");
//        isOnceBookData = getIntent().getExtras().getBoolean("is_once_book");
        isOnceBookData = false;
        isShowAll = true;
//        isShowAll = getIntent().getExtras().getBoolean("is_show_all");
//        if(isOnceBookData) {
//            bookType = getIntent().getExtras().getString("book_type");
//            bookId = getIntent().getExtras().getString("book_id");
//        }
        if(isShowAll) {
            filter = "all";
        } else {
            filter = "review";
        }
        threadData();
    }

    private void setView() {
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        tvAll = (TextView) findViewById(R.id.tv_all);
        tvAlreadyComment = (TextView) findViewById(R.id.tv_already_comment);
        ivBack = (ImageView) findViewById(R.id.bt_home);
        biz.setViewPositionNoSuoxiao(ivBack, 0,
                FixedPosition.common_position, scaleQPW, scaleQPH);
        tvDel = (TextView) findViewById(R.id.tv_del);
        ivBack.setOnClickListener(this);
        mListview = (ListView) findViewById(R.id.listview);
        mListview.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 设置单选模式

        footerView = View.inflate(mContext, R.layout.footer_loading, null);
        pbLoading = (ProgressBar) footerView.findViewById(R.id.pb_loading);
        txLoading = (TextView) footerView.findViewById(R.id.tv_loading);
        txLoaded = (TextView) footerView.findViewById(R.id.tv_click_to_refresh);
        mListview.addFooterView(footerView);

        adapter = new MyRecordAdapter(mContext, mListRecorder,
                isDel);
        mListview.setAdapter(adapter);

        setViewPosition(rlTitle, 0);
        setChangeTitleParams(tvAll, 1, true);
        setChangeTitleParams(tvAlreadyComment, 1, false);

        // 字体加粗
        TextPaint tpAll = tvAll.getPaint();
        tpAll.setFakeBoldText(true);
        TextPaint tpAlComment = tvAlreadyComment.getPaint();
        tpAlComment.setFakeBoldText(true);

        if(isShowAll) {
            showTitle(true);
        } else {
            showTitle(false);
        }

        tvDel.setLayoutParams(LayoutParameters.setViewWidthAndHeightParams4(
                148, 71, scaleQPW, scaleQPH));

        tvAll.setOnClickListener(this);
        tvAlreadyComment.setOnClickListener(this);
        tvDel.setOnClickListener(this);
        scrollListener();
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.record_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void scrollListener() {
        mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int lastItem;// listview当前显示页面的最后一条数据

            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (lastItem == mListRecorder.size()
                        && scrollState == SCROLL_STATE_IDLE) {
                    loading();
                    if (mListRecorder != null && mListRecorder.size() > 0) {
                        threadData();
                    }
                    return;
                }
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                lastItem = firstVisibleItem + visibleItemCount - 1;// 计算出lastitem的值
            }
        });
    }

    // 正在加载
    private void loading() {
        footerView.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.VISIBLE);
        txLoading.setVisibility(View.VISIBLE);
        txLoaded.setVisibility(View.INVISIBLE);
    }

    private void setChangeTitleParams(View view, int i, boolean isLeft) {
        RelativeLayout.LayoutParams params = LayoutParameters.setViewWidthAndHeightParams(FixedPosition.record_position[i][0],
                FixedPosition.record_position[i][1], 10, R.id.view_h_center, isLeft, scaleQPW, scaleQPH);
        view.setLayoutParams(params);
    }

    private boolean isCanClick = true;

    private void threadData() {
        if(!isCanClick)
            return;
        isCanClick = false;
        if (new MyToast().hasInternetConnection(mContext)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    RecorderMusic rdMusic;
                    if(isOnceBookData) { // 某一本书的数据
                        rdMusic = new RecorderMusic()
                                .getOnceBookAudioMusicData(mContext, filter, bookType, bookId, cursor);
                    } else { // 所有数据
                        rdMusic = new RecorderMusic()
                                .getAudioMusicData(mContext, filter, cursor);
                    }
                    handler.sendMessage(handler.obtainMessage(0, rdMusic));
                }
            }).start();
        } else {
            isCanClick = true;
            new MyToast().showTextToast(mContext,
                    "网络异常");
            rlDefineProgress.setVisibility(View.GONE);
        }
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    rlDefineProgress.setVisibility(View.GONE);
                    RecorderMusic rdMusic = (RecorderMusic) msg.obj;
                    isCanClick = true;
                    if (rdMusic != null) {
                        String msgValue = rdMusic.getMsg();
                        if (StringBiz.isEmpty(msgValue)) {
                            List<Recorder> listRecorder = rdMusic.getList();
                            if(listRecorder != null && listRecorder.size() > 0) {
                                cursor = rdMusic.getNext_cursor();
                                mListRecorder.addAll(listRecorder);
                                fillNoticeData();
                                if(listRecorder.size() < 20) {
                                    hideFooter();
                                }
                            } else {
                                hideFooter();
                            }
                        } else {
                            String errCode = rdMusic.getErrCode();
                            if (!StringBiz.isEmpty(errCode)
                                    && errCode.equals("401")) { // 去登录
//                                new DialogBiz().gotoLogin(mContext);
                            } else {
                                new MyToast().showTextToast(mContext, msgValue);
                            }
                        }
                    }
                    break;
                case 1:
                    String msgValue = (String) msg.obj;
                    if(!StringBiz.isEmpty(msgValue)) {
                        new MyToast().showTextToast(mContext, msgValue);
                    }
                    adapter.clearData();
                    click("review", false, true);
                    break;
                default:
                    break;
            }
        };
    };

    private void hideFooter() {
        if (isNeedPrompt) {
            new MyToast().showTextToast(mContext, "数据已经取完了哦");
            isNeedPrompt = false;
        }
        footerView.setPadding(0, -1 * footerView.getHeight(), 0, 0);
        footerView.setVisibility(View.GONE);
    }

    private void fillNoticeData() {
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new MyRecordAdapter(mContext,
                    mListRecorder, isDel);
            mListview.setAdapter(adapter);
        }
    }

    private void clearMedia() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setKeepScreenOn(true);
    }

    private void pauseMedia() {
        try {
            if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        pauseMedia();
        getWindow().getDecorView().setKeepScreenOn(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearMedia();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            clearMedia();
            finish();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if (v == ivBack) {
            finish();
        } else if(v == tvAll) {
            click("all", true, false);
        } else if(v == tvAlreadyComment) {
            click("review", false, false);
        } else if(v == tvDel) {
            if(mListRecorder != null && mListRecorder.size() > 0) {
                isDel = !isDel;
                if(isDel) {
                    tvDel.setText("确定");
                }
                if(isDel) {
                    refreshItem();
                } else { // 去删除
                    delItem();
                }
            }
        }
    }

    public void refreshProgressStart(String des) {
        if(!StringBiz.isEmpty(des)) {
            setTopView(des);
        }
        rlDefineProgress.setVisibility(View.VISIBLE);
        startAnimationView();
    }

    private void delItem() {
        List<Integer> listPos = new ArrayList<Integer>();
        if (adapter == null)
            return;
        listPos = adapter.getPosition();
        if(listPos != null && listPos.size() > 0) {
            int delNum = listPos.size();
            StringBuffer sbf = new StringBuffer();
            for (int i = 0; i < delNum; i++) {
                if(i == (delNum-1)) {
                    sbf.append(mListRecorder.get(listPos.get(i)).getId());
                } else {
                    sbf.append(mListRecorder.get(listPos.get(i)).getId() + ",");
                }
            }
            System.out.println("------sbf-----:" + sbf.toString());
            showDefineDialog(sbf.toString());
        } else {
            tvDel.setText("删除");
            refreshItem();
        }
    }

    private void threadDel(final String audioIds) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgValue = new RecorderMusic().getAudioDel(mContext, audioIds);
                if(!StringBiz.isEmpty(msgValue) && msgValue.equals("true")) {
                    handler.sendMessage(handler.obtainMessage(1, ""));
                } else {
                    handler.sendMessage(handler.obtainMessage(1, msgValue));
                }
            }
        }).start();
    }

    private void refreshItem() {
        if(adapter != null) {
            adapter.setDelete(isDel);
            adapter.notifyDataSetChanged();
        }
        if(rlDefineProgress.getVisibility() == View.VISIBLE) {
            rlDefineProgress.setVisibility(View.GONE);
        }
    }

    private void click(String status, boolean changeBg, boolean isRefresh) {
        tvDel.setText("删除");
        isDel = false;
        cursor = "0";
        isNeedPrompt = true;
        adapter = null;
        mListRecorder.clear();
        if(!isRefresh) {
            filter = status;
            showTitle(changeBg);
        }
        refreshProgressStart("正在加载中...");
        threadData();
    }

    private void showTitle(boolean status) {
        currentIsShowAll = status;
        if(currentIsShowAll) {
            tvAll.setBackgroundResource(R.drawable.title_bg);
            tvAlreadyComment.setBackgroundResource(0);
        } else {
            tvAll.setBackgroundResource(0);
            tvAlreadyComment.setBackgroundResource(R.drawable.title_bg);
        }
    }

    // 显示自定义Dialog
    private void showDefineDialog(final String audioId) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.show();
        Window window = dialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        window.setContentView(R.layout.define_dialog_2);

        RelativeLayout rlCancel = (RelativeLayout) window
                .findViewById(R.id.rl_left);
        RelativeLayout rlBoundPhone = (RelativeLayout) window
                .findViewById(R.id.rl_right);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_dialog_title);
        TextView tvContent = (TextView) window
                .findViewById(R.id.tv_dialog_content);
        TextView tvLeft = (TextView) window.findViewById(R.id.tv_left);
        TextView tvRight = (TextView) window.findViewById(R.id.tv_right);

        tvTitle.setText("删除");
        tvTitle.setVisibility(View.VISIBLE);
        tvContent.setText("您确定要删除吗？");
        tvContent.setTextSize(16.0f);
        tvLeft.setText("确定");
        tvRight.setText("取消");
        rlCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                tvDel.setText("删除");
                refreshProgressStart("正在删除中...");
                threadDel(audioId);
            }
        });

        rlBoundPhone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                isDel = true;
            }
        });
        dialog.setCancelable(false);
    }

}