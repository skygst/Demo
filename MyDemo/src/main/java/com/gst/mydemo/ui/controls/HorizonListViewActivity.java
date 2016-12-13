package com.gst.mydemo.ui.controls;

import android.content.Context;
import android.os.Bundle;

import com.gst.mydemo.R;
import com.gst.mydemo.adapter.MyListAdapter;
import com.gst.mydemo.base.Student;
import com.gst.mydemo.custom.widget.HorizontalListView;
import com.gst.mydemo.ui.TopActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *  水平移动ListView
 * Created by 善同 on 2016/1/14.
 */
public class HorizonListViewActivity extends TopActivity {
    private Context mContext;
    private HorizontalListView listView;
    private MyListAdapter adapter;
    private List<Student> listStu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizon_list);

        init();
        setView();
    }

    private void init() {
        mContext = HorizonListViewActivity.this;
        listStu = new ArrayList<Student>();
//        getData();
    }

    private void setView() {
        listView = (HorizontalListView) findViewById(R.id.list_view);
        adapter = new MyListAdapter(mContext, listStu, 0);
        listView.setAdapter(adapter);
    }
}
