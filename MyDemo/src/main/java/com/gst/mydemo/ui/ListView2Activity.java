package com.gst.mydemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.adapter.MyListAdapter;
import com.gst.mydemo.base.Student;
import com.gst.mydemo.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 善同 on 2015/11/5.
 */
public class ListView2Activity extends TopActivity implements View.OnClickListener {

    private Context mContext;
    private TextView tvReceivingLines, tvReceivingRecords,
            tvExchangeLines, tvExchangeRecords;
    private ListView listView;
    private List<Student> recordeList;
    private View footerView;
    private TextView txLoading, txLoaded;
    private ProgressBar pbLoading;
    private int REQUEST_CODE = 1;
    private boolean isClicked = false;
    private MyListAdapter adapter;
    private int page = -1;
    private boolean currentIsFirst = true;
    private boolean isNeedPrompt = true; // 需要提示：数据已经取完

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_page);

        initializationData();
        setView();
    }

    private void initializationData() {
        mContext = ListView2Activity.this;
        recordeList = new ArrayList<Student>();
//        refreshData();
    }

    private List<Student> refreshData() {
        page++;
        String type1 = "-----当前状态是---领取";
        String type2 = "age";
        if(!currentIsFirst) {
            type1 = "-----当前状态是---兑换";
        }
        List<Student> listStu = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Student stu = new Student();
            stu.setName(page * 10 + i + type1);
            stu.setName(page * 10 + i + type2 + "---------" + (page * 10 + i));
            listStu.add(stu);
        }
        return listStu;
    }

    private void setView() {
        setTopView();
        tvTitle.setText("List列表");
        tvReceivingLines = (TextView) findViewById(R.id.tv_receiving_records_lines);
        tvReceivingRecords = (TextView) findViewById(R.id.tv_receiving_records);
        tvExchangeLines = (TextView) findViewById(R.id.tv_exchange_records_lines);
        tvExchangeRecords = (TextView) findViewById(R.id.tv_exchange_records);
        listView = (ListView) findViewById(R.id.list_view);

        footerView = View.inflate(mContext, R.layout.footer_loading, null);
        pbLoading = (ProgressBar) footerView.findViewById(R.id.pb_loading);
        txLoading = (TextView) footerView.findViewById(R.id.tv_loading);
        txLoaded = (TextView) footerView.findViewById(R.id.tv_click_to_refresh);
        listView.addFooterView(footerView);

        changeRecordsStatus(View.VISIBLE, View.INVISIBLE);
        tvReceivingRecords.setOnClickListener(this);
        tvExchangeRecords.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        scrollListener();
    }

    // 改变记录的状态
    private void changeRecordsStatus(int leftLineStatus, int rightLineStatus) {
        tvReceivingLines.setVisibility(leftLineStatus);
        tvReceivingRecords.setTextColor(getResources()
                .getColor(R.color.wordColor));
        tvExchangeLines.setVisibility(rightLineStatus);
        tvExchangeRecords.setTextColor(getResources()
                .getColor(R.color.wordColor));
    }

    private void clearData(boolean status) {
        recordeList.clear();
        if (adapter != null)
            adapter.notifyDataSetChanged();
        isNeedPrompt = true;
        currentIsFirst = status;
        page = 0;
        footerView.setPadding(0, footerView.getHeight(), 0, 0);
        footerView.setVisibility(View.VISIBLE);
        loadLogs("0");
    }

    @Override
    public void onClick(View v) {
        if (!isClicked) {
            isClicked = true;
        } else {
            return;
        }
        if (v == tvReceivingRecords) { // 领取记录
            clearData(true);
            changeRecordsStatus(View.VISIBLE, View.INVISIBLE);
        } else if (v == tvExchangeRecords) { // 兑换记录
            clearData(false);
            changeRecordsStatus(View.INVISIBLE, View.VISIBLE);
        }
    }

    private void loadLogs(String nextCursor) {
        new logsAsyncTask().execute(nextCursor);
    }

    private class logsAsyncTask extends AsyncTask<String, Void, List<Student>> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<Student> doInBackground(String... params) {// type:in/out
            List<Student> listStu = refreshData();
            return listStu;
        }

        @Override
        protected void onPostExecute(List<Student> stuList) {
            isClicked = false;
//            if(stu != null) {
//                List<CashRecorde> recordeListValue = (List<CashRecorde>) obj[0];
            if (stuList != null) {
                loaded();
                if (stuList.size() > 0) {
                    recordeList.addAll(stuList);
                    if (page == 0) {
                        adapter = new MyListAdapter(mContext, recordeList, 0);
                        listView.setAdapter(adapter);
                    } else {
                        if (adapter != null)
                            adapter.notifyDataSetChanged();
                    }
                }
            } else {
                if (recordeList == null || recordeList.size() == 0) {
                    return;
                }
                // 数据取完
                if (isNeedPrompt) {
                    new MyToast().showTextToast(mContext, "数据已经取完了哦");
                    isNeedPrompt = false;
                }
                footerView.setPadding(0, -1 * footerView.getHeight(), 0, 0);
                footerView.setVisibility(View.GONE);
            }
//                } else {
//                    String msg = (String) obj[1];
//                    if (msg != null && !msg.equals("")) {
//                        new PublicMethod().showTextToast(mContext, msg);
//                    }
//                }
//            }
        }
    }

    private void scrollListener() {

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int lastItem;// listview当前显示页面的最后一条数据

            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (lastItem == recordeList.size()
                        && scrollState == SCROLL_STATE_IDLE) {
                    loading();
                    String nextCursor = "0";
                    if (recordeList != null && recordeList.size() > 0) {
//                        nextCursor = recordeList.get(recordeList.size() - 1)
//                                .getNext_cursor();
                    }
                    loadLogs(nextCursor);
                    return;
                }
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                lastItem = firstVisibleItem + visibleItemCount - 1;// 计算出lastitem的值
            }
        });
    }

    // 加载结束
    private void loaded() {
        footerView.setVisibility(View.GONE);
    }

    // 正在加载
    private void loading() {
        footerView.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.VISIBLE);
        txLoading.setVisibility(View.VISIBLE);
        txLoaded.setVisibility(View.INVISIBLE);
    }

    private void gotoActivity(Class<?> classes) {
        startActivityForResult(new Intent(mContext, classes), REQUEST_CODE);
    }

}
