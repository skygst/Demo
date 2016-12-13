package com.testfragandvp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gst.move.R;

/**
 * 这个里就是page页面中的单个页面现实，具体可以根据自己需求，做多个布局不同Fragment然后添加到FragAdapter的List中就ok
 * @author lxlin
 *
 */
public class MyFragment extends Fragment {

    private static final String TEXT_CHAT = "CHAT";
    

    /**
     * Factory method to generate a new instance of the fragment given a string .
     *
     * @param char 主页面要传过来的信息
     * @return A new instance of MyFragment with chat extras
     */
    public static MyFragment newInstance(String chat) {
        final MyFragment f = new MyFragment();

        final Bundle args = new Bundle();
        args.putString(TEXT_CHAT, chat);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment, container, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_fragment_text);
        String str = getArguments() != null ? getArguments().getString(TEXT_CHAT) : null;
        if(str != null){
            tv.setText(str);
        }else{
            tv.setText("获取字段出错了，求指导");
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
