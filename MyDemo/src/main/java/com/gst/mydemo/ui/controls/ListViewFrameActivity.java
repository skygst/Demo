package com.gst.mydemo.ui.controls;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.gst.mydemo.R;
import com.gst.mydemo.adapter.ListFrameAdapter;
import com.gst.mydemo.adapter.NullAdapter;
import com.gst.mydemo.base.Student;
import com.gst.mydemo.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView图片动态添加边框
 * Created by 善同 on 2016/2/16.
 */
public class ListViewFrameActivity extends BaseActivity {

    private Context mContext;
    private ListView listView;
    private List<Student> listStudent;
    public static ListFrameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_frame);

        mContext = ListViewFrameActivity.this;
        listStudent = new ArrayList<Student>();
        for(int i=1; i<=60; i++) {
            Student stu = new Student();
            stu.setId(""+i);
            stu.setAge(i+"岁年龄");
            stu.setName("张三 " + i);
            listStudent.add(stu);
        }
        setView();
    }

    private void setView() {
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ListFrameAdapter(mContext, listStudent);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        listStudent.clear();
        listView.setAdapter(new NullAdapter());
        listView = null;
        mContext = null;
        adapter = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
