package com.gst.mydemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.fragment.TabAFm;
import com.gst.mydemo.fragment.TabBFm;
import com.gst.mydemo.fragment.TabCFm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 善同 on 2015/11/6.
 */
public class TabMainActivity extends FragmentActivity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    public List<Fragment> fragments;
    private TextView tv1, tv2, tv3;
    private Context mContext;
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab; // 当前Tab页面索引


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);

        mContext = TabMainActivity.this;
        fragments = new ArrayList<Fragment>();
        fragments.add(new TabAFm());
        fragments.add(new TabBFm());
        fragments.add(new TabCFm());

        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        fragmentContentId = R.id.tab_content;
        click(0);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("v.getId() :" + v.getId());
        if(v.getId() == R.id.tv_1) {
            click(0);
        } else if(v.getId() == R.id.tv_2) {
            click(1);
        } else if(v.getId() == R.id.tv_3) {
            click(2);
        }
    }

    private void click(int index) {
        System.out.println("click index :" + index);
        Fragment fragment = fragments.get(index);
        FragmentTransaction ft = obtainFragmentTransaction(index);
        fragments.get(currentTab).onPause();
        if(fragment.isAdded()){
            fragment.onResume(); // 启动目标tab的onResume()
        }else{
            ft.add(fragmentContentId, fragment);
        }
        showTab(index); // 显示目标tab
        ft.commit();
    }

    /**
     * 切换tab
     * @param idx
     */
    private void showTab(int idx){
        for(int i = 0; i < fragments.size(); i++){
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if(idx == i){
                ft.show(fragment);
            }else{
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取一个带动画的FragmentTransaction
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if(index > currentTab){
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        }else{
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return ft;
    }

}
