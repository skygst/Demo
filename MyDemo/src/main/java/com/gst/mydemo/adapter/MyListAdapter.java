package com.gst.mydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.base.Student;

import java.util.List;

/**
 * Created by 善同 on 2015/11/5.
 */
public class MyListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Student> mListString;
    private LayoutInflater mInflater;
    private int mIndex;
    private final String TAG = "MyListAdapter";

    public MyListAdapter(Context context, List<Student> listString, int index) {
        mContext = context;
        mListString = listString;
        mInflater = LayoutInflater.from(mContext);
        mIndex = index;
    }

    @Override
    public int getCount() {
        return mListString.size();
    }

    @Override
    public Object getItem(int position) {
        return mListString.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            Log.d(TAG, "----------- getItemView -----------");
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvAge = (TextView) convertView.findViewById(R.id.tv_age);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d(TAG, "---+++++++++++++++++++++++++++++++++++++");
        Student stu = mListString.get(position);
        holder.tvName.setText(stu.getName());
        holder.tvAge.setText(stu.getAge());
        if(mIndex != position) {
            holder.tvName.setTextColor(Color.BLACK);
        } else {
            holder.tvName.setTextColor(Color.RED);

        }
        return convertView;
    }

    class ViewHolder {
        TextView tvName, tvAge;
    }

}
