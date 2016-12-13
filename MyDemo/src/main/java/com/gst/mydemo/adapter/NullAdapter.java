package com.gst.mydemo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 用于下拉刷新的ListView的Adapter，什么都不显示
 * 
 * @author Liujing
 * 
 */
public class NullAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}
}
