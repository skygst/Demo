package com.gst.mydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.base.Student;

import java.util.List;

/**
 * Created by 善同 on 2016/1/12.
 */
public class MyListNewAdapter extends SimpleBaseAdapter<Student> {

    private Context mContext;
    private List<Student> mListStu;
    private int mIndex;
    private final String TAG = "MyListNewAdapter";

    public MyListNewAdapter(Context context, List<Student> listStu, int index) {
        super(context, listStu);

        mContext = context;
        mListStu = listStu;
        mIndex = index;
    }

    /**
     * 该方法需要子类实现，需要返回item布局的resource id
     *
     * @return
     */
    @Override
    public int getItemResource() {
        return R.layout.list_item;
    }

    /**
     * 使用该getItemView方法替换原来的getView方法，需要子类实现
     *
     * @param position
     * @param convertView
     * @param holder
     * @return
     */
    @Override
    public View getItemView(int position, View convertView, ViewHolder holder) {
        Log.d(TAG, "----------- getItemView -----------");
        TextView tvName = (TextView) holder.getView(R.id.tv_name);
        TextView tvAge = (TextView) holder.getView(R.id.tv_age);

        Student stu = mListStu.get(position);
        tvName.setText(stu.getName());
        tvAge.setText(stu.getAge());
        if(mIndex != position) {
            tvName.setTextColor(Color.BLACK);
        } else {
            tvName.setTextColor(Color.RED);
        }

        return convertView;
    }

}
