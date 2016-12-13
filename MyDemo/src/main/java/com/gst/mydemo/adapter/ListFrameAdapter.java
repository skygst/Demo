package com.gst.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.base.Student;
import com.gst.mydemo.ui.controls.ListViewFrameActivity;

import java.util.List;

/**
 * Created by 善同 on 2016/2/16.
 */
public class ListFrameAdapter extends BaseAdapter {
    private List<Student> mListStudent;
    private Context mContext;
    private LayoutInflater mInflater;
    private ImageView currentImage;
    private int num = 0;

    public ListFrameAdapter(Context context, List<Student> listStudent) {
        mListStudent = listStudent;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListStudent.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mListStudent.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        final ViewHolder holder;
        if (null == arg1) {
            holder = new ViewHolder();
            arg1 = mInflater.inflate(R.layout.list_frame_item, null);
            holder.rlLayout = (RelativeLayout) arg1.findViewById(R.id.rl_layout);
            holder.ivPic = (ImageView) arg1.findViewById(R.id.iv_pic);
            holder.tvId = (TextView) arg1.findViewById(R.id.tv_id);
            holder.tvAge = (TextView) arg1.findViewById(R.id.tv_age);
            holder.tvName = (TextView) arg1.findViewById(R.id.tv_name);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }
        if (arg0 == num) {
            holder.ivPic.setImageResource(R.drawable.sms_type_bg);
            currentImage = holder.ivPic;
        } else {
            holder.ivPic.setImageResource(0);
            currentImage = holder.ivPic;
        }
        Student stu = mListStudent.get(arg0);
        holder.tvId.setText(stu.getId());
        holder.tvAge.setText(stu.getAge());
        holder.tvName.setText(stu.getName());
//		holder.ivPic.performClick();
        holder.ivPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                num = arg0;
                handleImageView((ImageView) v);
//                holder.rlLayout.performClick();
            }
        });
        return arg1;
    }

    /**
     * 给点击选中的ImageView加边框，并将之前的ImageView边框清除
     *
     * @param imageView 要添加边框的ImageView
     */
    public void handleImageView(ImageView imageView) {
        currentImage.setImageDrawable(null);
        imageView.setImageResource(R.drawable.sms_type_bg);
        currentImage = imageView;
//		new TestAdapter(mContext, mListStudent).notifyDataSetChanged();
        // TODO可能存在内存问题， 不能够及时释放
        ListViewFrameActivity.adapter.notifyDataSetChanged();
    }

    class ViewHolder {
        RelativeLayout rlLayout;
        ImageView ivPic;
        TextView tvId, tvAge, tvName;
    }

}
