package com.gst.move.test;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.custom.CusomSwipeView;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.MyToast;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.adapter.MallAdapter;
import com.gst.move.base.Speak;
import com.gst.move.model.MallBase;

import java.util.ArrayList;
import java.util.List;


/**
 *  商城
 * @author
 *
 */
public class MallActivity extends BaseActivity implements OnClickListener {
	private RelativeLayout layout_bg;
	private Context mContext;
	private ImageView ivBack;
	private ClickImageView ivOrder, ivShoppingCart, ivCoupon;
	private List<MallBase> listMall;
	private MallAdapter adapter;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private CusomSwipeView mRecyclerView;
//	private RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mall);

		init();
		setView();
		if (!new MyToast().hasInternetConnection(mContext)) {
			new MyToast().showTextToast(mContext, "网络异常，请先检查网络是否连接");
		} else {
			new goodsListAsyncTask().execute();
		}
		thread();
	}

	private void thread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				new Speak().getLoudlyList(mContext);
			}
		}).start();
	}

	private void init() {
		mContext = MallActivity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		listMall = new ArrayList<MallBase>();
	}

	private void setView() {
		layout_bg = (RelativeLayout) findViewById(R.id.layout_bg);
		CommonBitmap.changeDrawable(mContext,layout_bg,R.drawable.mall_bg);

		ivBack = (ImageView) findViewById(R.id.iv_back);
		ivOrder = (ClickImageView) findViewById(R.id.iv_order);
		ivShoppingCart = (ClickImageView) findViewById(R.id.iv_shopping_cart);

		mRecyclerView = (CusomSwipeView) findViewById(R.id.id_recyclerview_horizontal);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerView.setLayoutManager(linearLayoutManager);
//		mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
		adapter = new MallAdapter(mContext, listMall, scaleQPW, scaleQPH);
		mRecyclerView.setAdapter(adapter);

		ivCoupon = (ClickImageView) findViewById(R.id.iv_coupon);
		ivCoupon.setOnClickListener(this);

		biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
		biz.setViewPositionNoSuoxiao(ivOrder, 16, FixedPosition.common_position, scaleQPW ,scaleQPH);
		biz.setViewPositionNoSuoxiao(ivShoppingCart, 19, FixedPosition.common_position, scaleQPW ,scaleQPH);
		biz.setViewPositionNoSuoxiao(ivCoupon, 20, FixedPosition.common_position, scaleQPW ,scaleQPH);

		ivBack.setOnClickListener(this);
		ivOrder.setOnClickListener(this);
		ivShoppingCart.setOnClickListener(this);
//		if(isInDefineMall) {
//			ivOrder.setVisibility(View.VISIBLE);
//			ivShoppingCart.setVisibility(View.VISIBLE);
//		} else {
			ivOrder.setVisibility(View.GONE);
			ivShoppingCart.setVisibility(View.GONE);
//		}
	}

	private void listItemClick() {
//		adapter.setOnItemClickLitener(new OnItemClickLitener() {
//
//			@Override
//			public void onItemClick(View view, int position) {
//				MallBase mallBase = listMall.get(position);
//			}
//		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		if(v == ivBack) {
			this.finish();
		} else if(v == ivOrder) { // 我的订单
//			gotoActivity(MyOrderListActivity.class);
		} else if(v == ivShoppingCart) { // 我的购物车
//			gotoActivity(MallShoppingCartActivity.class);
		} else if(v == ivCoupon) { // 进入 优惠券
//			startActivity(new Intent(mContext, MyCouponActivity.class).putExtra("isToSel", false));
		}
	}

//	private void showCachingData() {
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				String cachResult = new CacheSp().spGetMallCachData(mContext);
//				if(!StringBiz.isEmpty(cachResult)) {
//					Object[] obj = new MallBase().parseMall(mContext, cachResult, true);
//					handler.sendMessage(handler.obtainMessage(0, obj));
//				}
//			}
//		}).start();
//	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 0:
					Object[] object = (Object[]) msg.obj;
					fillingData(object);
					break;
				default:
					break;
			}
		};

	};

	private class goodsListAsyncTask extends AsyncTask<String, Void, Object[]> {

		@Override
		protected Object[] doInBackground(String... params) {// type:in/out
			Object[] obj = new MallBase().getMallList(mContext);
			return obj;
		}

		@Override
		protected void onPostExecute(Object[] obj) {
			fillingData(obj);
		}
	}

	private void fillingData(Object[] obj) {
		if(obj != null) {
			@SuppressWarnings("unchecked")
			List<MallBase> listMallBase = (List<MallBase>) obj[0];
			if (listMallBase != null && listMallBase.size() > 0) {
				if(listMall != null && listMall.size() > 0)
					listMall.clear();
				System.out.println("-----listMallBase.size()------" + listMallBase.size());
				listMall.addAll(listMallBase);
				adapter = new MallAdapter(mContext, listMall, scaleQPW, scaleQPH);
				mRecyclerView.setAdapter(adapter);
				listItemClick();
			} else {
				String errorCode = (String) obj[1];
				String msg = (String) obj[2];
				if(!StringBiz.isEmpty(errorCode)) {
					if(errorCode.equals("401")) { // token过期 --- 去登录
					} else {
						new MyToast().showTextToast(mContext, msg);
					}
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		handler.removeCallbacksAndMessages(null);
	}

}
