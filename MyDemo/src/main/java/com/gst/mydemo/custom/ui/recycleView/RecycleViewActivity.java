package com.gst.mydemo.custom.ui.recycleView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.gst.mydemo.R;
import com.gst.mydemo.adapter.PersonAdapter;
import com.gst.mydemo.adapter.RecyclerViewHorizonAdapter;
import com.gst.mydemo.base.Student;
import com.gst.mydemo.custom.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RecycleView 替换 ListVIew
 * Created by 善同 on 2016/1/7.
 */
public class RecycleViewActivity extends Activity {

    private RecyclerView recyclerView;
    private Button btnChange;
    private PersonAdapter adapter;
    private RecyclerViewHorizonAdapter hAdapter;
    private Context mContext;
    private List<Student> mListStu;
    private List<Integer> mDatas;
    private boolean isShowHorizon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        init();
        setView();
    }

    private void init() {
        mContext = RecycleViewActivity.this;
        mListStu = new ArrayList<Student>();

        initDatas();
    }

    private void setView() {
        btnChange = (Button) findViewById(R.id.btn_change);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
//        recyclerView.setLayoutManager(layoutManager);

        changeStatus();
        initData();
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowHorizon = !isShowHorizon;
                changeStatus();
            }
        });
    }

    private void changeStatus() {
        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(mContext);
        if (isShowHorizon) {
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            manager.setSmoothScrollbarEnabled(true);
            recyclerView.setLayoutManager(manager);
        } else {
            manager.setOrientation(LinearLayoutManager.VERTICAL);
        }
        manager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(manager);

        if (!isShowHorizon) {
            adapter = new PersonAdapter(mListStu);
            adapter.setOnRecyclerViewListener(new PersonAdapter.OnRecyclerViewListener() {

                @Override
                public void onItemClick(int position) {
                    System.out.println("-----**************recyclerView******** position " + position);
                }

                @Override
                public boolean onItemLongClick(int position) {
                    return false;
                }
            });
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.removeAllViews();
            hAdapter = new RecyclerViewHorizonAdapter(mContext, mDatas);
            hAdapter.setOnRecyclerViewListener(new RecyclerViewHorizonAdapter.OnRecyclerViewListener() {

                @Override
                public void onItemClick(int position) {
                    System.out.println("-----**************recyclerView******** position " + position);
                }

            });
            recyclerView.setAdapter(hAdapter);
        }

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Student stu = new Student();
            stu.setName("学生姓名：XXX" + i);
            stu.setAge("年龄：" + i);
            mListStu.add(stu);
        }
    }

    private void initDatas() {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.mipmap.little_car_1,
                R.mipmap.little_car_2, R.mipmap.little_car_3,
                R.mipmap.little_car_4, R.mipmap.little_car_5,
                R.mipmap.little_car_1, R.mipmap.little_car_2,
                R.mipmap.little_car_3, R.mipmap.little_car_4, R.mipmap.little_car_5));
    }

}
