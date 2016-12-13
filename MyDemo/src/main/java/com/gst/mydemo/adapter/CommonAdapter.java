package com.gst.mydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.base.Student;

import java.util.List;

/**
 * Created by 善同 on 2016/1/7.
 */
public class CommonAdapter extends RecyclerView.Adapter {

    private List<Student> mListStu;

    public CommonAdapter(List<Student> listStu) {
        mListStu = listStu;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_test_item_person, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        CommonViewHolder holder = (CommonViewHolder) viewHolder;
        holder.position = i;
        Student person = mListStu.get(i);
        holder.nameTv.setText(person.getName());
        holder.ageTv.setText(person.getAge() + "岁");
    }

    @Override
    public int getItemCount() {

        return mListStu.size();
    }

    class CommonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public LinearLayout rootView;
        public TextView nameTv;
        public TextView ageTv;
        public int position;

        public CommonViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);
            ageTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_age_tv);
            rootView = (LinearLayout) itemView.findViewById(R.id.recycler_view_test_item_person_view);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            System.out.println("------------ onClick --------vvv---------");
//            if (null != onRecyclerViewListener) {
//                System.out.println("------------ onClick -----------------");
//                onRecyclerViewListener.onItemClick(position);
//            }
        }

        @Override
        public boolean onLongClick(View v) {
//            if (null != onRecyclerViewListener) {
//                return onRecyclerViewListener.onItemLongClick(position);
//            }
            return false;
        }
    }
}
