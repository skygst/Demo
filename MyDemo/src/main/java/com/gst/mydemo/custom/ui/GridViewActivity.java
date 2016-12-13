package com.gst.mydemo.custom.ui;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;

import com.gst.mydemo.R;
import com.gst.mydemo.adapter.MyApapter;
import com.gst.mydemo.ui.TopActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 善同 on 2015/11/3.
 */
public class GridViewActivity extends TopActivity implements View.OnClickListener {

//    private MyGridView myGridView;
    private MyApapter myApapter;
    private GridView myGridView;
    private int wh;
    private List<String> mListString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_gridview);

        init();
        setView();
    }

    private void init() {
        mListString = new ArrayList<String>();
        String[] strPhotos = new String[] {
                "http://img.bbpapp.com/Mon_1509/11_435730_c5f6681b0a75a84.jpg",
                "http://img.bbpapp.com/Mon_1509/11_435730_ae66a6f5a6af1ab.jpg",
                "http://img.bbpapp.com/Mon_1509/11_435730_bd9eceab6c85a51.jpg",
                "http://img.bbpapp.com/Mon_1509/11_435730_a7faf3ba8efdd14.jpg",
                "http://img.bbpapp.com/Mon_1509/11_435730_a4981530c1d5735.jpg",

                "http://t12.baidu.com/it/u=2092790666,2117584040&fm=56",
                "http://t10.baidu.com/it/u=3404273679,758732817&fm=56",
                "http://t12.baidu.com/it/u=3814525610,3295112608&fm=56",
                "http://t10.baidu.com/it/u=3871294008,3459868414&fm=56",
                "http://t12.baidu.com/it/u=65151302,3779431346&fm=56",
                "http://t11.baidu.com/it/u=2116557139,3379630133&fm=56",
                "http://t10.baidu.com/it/u=485664675,6621956&fm=56",
                "http://t11.baidu.com/it/u=3868211770,2750410727&fm=56",
                "http://img.bbpapp.com/Mon_1509/11_435730_c5f6681b0a75a84.jpg",
                "http://img.bbpapp.com/Mon_1509/11_435730_ae66a6f5a6af1ab.jpg",
                "http://img.bbpapp.com/Mon_1509/11_435730_bd9eceab6c85a51.jpg",
                "http://img.bbpapp.com/Mon_1509/11_435730_a7faf3ba8efdd14.jpg",
                "http://img.bbpapp.com/Mon_1509/11_435730_a4981530c1d5735.jpg",

                "http://t12.baidu.com/it/u=2092790666,2117584040&fm=56",
                "http://t10.baidu.com/it/u=3404273679,758732817&fm=56",
                "http://t12.baidu.com/it/u=3814525610,3295112608&fm=56",
                "http://t10.baidu.com/it/u=3871294008,3459868414&fm=56",
                "http://t12.baidu.com/it/u=65151302,3779431346&fm=56",
                "http://t11.baidu.com/it/u=2116557139,3379630133&fm=56",
                "http://t10.baidu.com/it/u=485664675,6621956&fm=56",
                "http://t11.baidu.com/it/u=3868211770,2750410727&fm=56" };

        for(int i=0; i<strPhotos.length; i++) {
            mListString.add(strPhotos[i]);
        }
    }

    private void setView() {
        setTopView();
        tvTitle.setText("GridView自适应");
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        wh = display.getWidth();// 宽度
//        myGridView = (MyGridView) findViewById(R.id.myGridView);
        myGridView = (GridView) findViewById(R.id.myGridView);

        myApapter = new MyApapter(mListString, this, wh);
        myGridView.setAdapter(myApapter);
    }

    @Override
    public void onClick(View v) {

    }
}
