package com.gst.move.level_c_game;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.MyListView;
import com.ebodoo.raz.utils.LayoutParameters;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.adapter.MedalAdapter;
import com.gst.move.base.ListviewData;
import com.gst.move.base.Medal;

import java.util.List;

/**
 *  勋章 界面
 */
public class MedalActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ScrollView slView;
    private MyListView listView;
    private ImageView ivMedal;
    private MedalAdapter adapter;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private int[] mLenArray = new int[] {
            4, 4, 5, 8, 7, 8, 14
    };
    private List<ListviewData> listData;
    private String medalId = "200008";
    private int mItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medal);

        init();
        setView();
    }

    private void init() {
        mContext = MedalActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        listData = new Medal().getData();
        medalId = "200003";
        if(!StringBiz.isEmpty(medalId) && StringBiz.isNumeric(medalId)) {
            String fValue = medalId.substring(0, 1);
            mItemIndex = Integer.valueOf(fValue).intValue();
        }
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        slView = (ScrollView) findViewById(R.id.scroll_view);
        ivMedal = (ImageView) findViewById(R.id.iv_medal);
        listView = (MyListView) findViewById(R.id.list_view);
        adapter = new MedalAdapter(mContext, listData, scaleQPW, scaleQPH, width, medalId);
        listView.setAdapter(adapter);
        threadMedal();
        setViewPosition();

        ivMedal.setOnClickListener(this);
        rlLayout.setOnClickListener(this);
        setListViewHeight(listView);
    }

    private void setListViewHeight(ListView listView){
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int height = 0;
        for (int i = 0; i < 6; i++) {
            View listItemView = adapter.getView(i, null, listView);
            listItemView.measure(0, 0);
            height += listItemView.getMeasuredHeight();
        }
        final int hei = height;
        System.out.println("---------hei :" + hei);
        slView.post(new Runnable() {
            //让scrollview跳转到顶部，必须放在runnable()方法中
            @Override
            public void run() {
                slView.scrollTo(0, hei);
            }
        });
//        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
//        layoutParams.height = height+5 +(adapter.getCount()-1)*(listView.getDividerHeight());
//        listView.setLayoutParams(layoutParams);
    }

    private void setViewPosition() {
        slView.setLayoutParams(LayoutParameters.setViewParentRightParams(456,
                RelativeLayout.LayoutParams.MATCH_PARENT, scaleQPW, scaleQPH));
        ivMedal.setLayoutParams(LayoutParameters.setViewToLeftParams(99, 110, 40, R.id.scroll_view, -2, scaleQPW, scaleQPH));
    }

    private void threadMedal() {
        new Thread(new Runnable() {

            @Override
            public void run() {
            new Medal().getMedalList(mContext);
            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        if(v == ivMedal || v == rlLayout) {
            finish();
        }
    }
}
