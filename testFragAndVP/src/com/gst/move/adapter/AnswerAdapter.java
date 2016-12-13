package com.gst.move.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gst.move.R;
import com.gst.move.custom.XButton;
import com.testfragandvp.fragment.FragmentChoiceOne;

public class AnswerAdapter extends BaseAdapter {

	private List<String> mListContent;
	private Context mContext;
	private LayoutInflater mInflater;
	private int[] optionsId = new int[] {
			R.drawable.choose1, R.drawable.choose2, 
			R.drawable.choose3, R.drawable.choose4
	};
	private int num = -1;
	
	private int[] selOptionsId = new int[] {
			R.drawable.sel_choose1, R.drawable.sel_choose2, 
			R.drawable.sel_choose3, R.drawable.sel_choose4
	};
	private Button currentBtn;

	public AnswerAdapter(Context context, List<String> listContent) {
		this.mContext = context;
		this.mListContent = listContent;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mListContent != null ? mListContent.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		return mListContent.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (null == convertView) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.answer_item, null);
			holder.rlLayout = (RelativeLayout) convertView.findViewById(R.id.rl_layout);
			holder.btnRead = (XButton) convertView.findViewById(R.id.btn_read);
			holder.btnOptions = (Button) convertView.findViewById(R.id.btn_options);
			holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(position < optionsId.length) {
			if(position == num) {
				holder.btnOptions.setBackgroundResource(selOptionsId[position]);
			} else {
				holder.btnOptions.setBackgroundResource(optionsId[position]);
			}
		}
		currentBtn = holder.btnOptions;
		String content = mListContent.get(position);
		holder.tvContent.setText(content);
		holder.btnRead.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 读
				System.out.println("------read-----position------" + position);
			}
		});
		
		holder.btnOptions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				num = position;
				handleImageView((Button)v, position);
				holder.rlLayout.performClick();
			}
		});
		
		return convertView;
	}
	
	/**
	 * 给点击选中的ImageView加边框，并将之前的ImageView边框清除
	 * @param imageView 要添加边框的ImageView
	 */
    public void handleImageView(Button btn, int pos){
    	currentBtn.setBackgroundResource(0);
    	btn.setBackgroundResource(selOptionsId[pos]);
    	currentBtn = btn;
		// 可能存在内存问题， 不能够及时释放
    	FragmentChoiceOne.adapter.notifyDataSetChanged();
    }

	class ViewHolder {
		RelativeLayout rlLayout;
		XButton btnRead;
		Button btnOptions;
		TextView tvContent;
	}
	
}
