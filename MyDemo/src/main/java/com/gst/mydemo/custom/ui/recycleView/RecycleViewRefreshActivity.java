package com.gst.mydemo.custom.ui.recycleView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.adapter.MyItemRecyclerViewAdapter;
import com.gst.mydemo.base.DummyContent;
import com.gst.mydemo.custom.ui.LoadMoreRecyclerView;
import com.gst.mydemo.ui.BaseActivity;

/**
 *  上拉刷新 下拉加载更多
 * Created by gst-pc on 2016/9/9.
 */
public class RecycleViewRefreshActivity extends BaseActivity {

    private LoadMoreRecyclerView loadRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    private int page = 0;
    private int mColumnCount = 1;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_refresh);

        init();
        setView();
    }

    private void init() {
        mContext = RecycleViewRefreshActivity.this;
    }

    private void setView() {
        loadRecyclerView = (LoadMoreRecyclerView) findViewById(R.id.load_recyclerView);
        loadRecyclerView.setHasFixedSize(true);
        findViewById(R.id.mode_switch_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (1 == mColumnCount) {
                    mColumnCount = 2;
                    ((TextView) v).setText(R.string.list_mode_stagger);
                    myItemRecyclerViewAdapter.switchMode(true);
                    loadRecyclerView.switchLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
                } else {
                    mColumnCount = 1;
                    ((TextView) v).setText(R.string.list_mode_list);
                    myItemRecyclerViewAdapter.switchMode(false);
                    loadRecyclerView.switchLayoutManager(new LinearLayoutManager(mContext));
                }
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                page = 0;
                myItemRecyclerViewAdapter.setData(DummyContent.generyData(page));
                loadRecyclerView.setAutoLoadMoreEnable(DummyContent.hasMore(page));
                myItemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        if (1 >= mColumnCount) {
            loadRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        } else {
            loadRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
        }
        myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(DummyContent.generyData(page));
        loadRecyclerView.setAdapter(myItemRecyclerViewAdapter);
        loadRecyclerView.setAutoLoadMoreEnable(true);
        loadRecyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        myItemRecyclerViewAdapter.addDatas(DummyContent.generyData(++page));
                        loadRecyclerView.notifyMoreFinish(DummyContent.hasMore(page));
                    }
                }, 1000);
            }
        });
        myItemRecyclerViewAdapter.notifyDataSetChanged();
    }
}
