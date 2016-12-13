package com.gst.mydemo.ui.controls;

import android.content.Context;
import android.os.Bundle;

import com.gst.mydemo.R;
import com.gst.mydemo.adapter.MyListAdapter;
import com.gst.mydemo.base.Student;
import com.gst.mydemo.custom.widget.ListViewForScrollView;
import com.gst.mydemo.ui.TopActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *  ScrollView嵌套ListView
 * Created by 善同 on 2016/1/14.
 */
public class ScrollViewNestingListViewActivity extends TopActivity {

    private Context mContext;
    private ListViewForScrollView listView;
    private MyListAdapter adapter;
    private List<Student> listStu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_to_list);

        init();
        setView();
    }

    private void init() {
        mContext = ScrollViewNestingListViewActivity.this;
        listStu = new ArrayList<Student>();
        getData();
    }

    private void setView() {
        listView = (ListViewForScrollView) findViewById(R.id.list_view);
        adapter = new MyListAdapter(mContext, listStu, 0);
        listView.setAdapter(adapter);
    }


    private void getData() {
        int page = 0;
        String type1 = "-----当前状态是---领取";
        String type2 = "age";
        for(int i=0; i<50; i++) {
            Student stu = new Student();
            stu.setName(page + i + type1);
            stu.setName(page + i + type2 + "---------" + (page * 10 + i));
            listStu.add(stu);
        }
    }
}
