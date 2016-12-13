package com.gst.move.base;

import android.content.Context;

import com.ebodoo.raz.server.CommonAdressConstant;
import com.ebodoo.raz.server.CommonInterfaces;
import com.example.location.biz.StringBiz;
import com.google.gson.Gson;
import com.payment.SignUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayMent implements Serializable {

	private static final long serialVersionUID = -262275901350522092L;

	private String msg;
	private String errCode;
	private String price;
	private String out_trade_no;
	private String subject;
	private String it_b_pay;
	private String body;
	private String partner;
	private String seller_email;
	private String notify_url;
	private String private_key;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	public PayMent checkOrder(Context context, String orderId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("order_id", orderId)); // json{id:num}
		params.add(new BasicNameValuePair("platform", "alipay"));
		String result = new CommonInterfaces().getDataFromUrlByPost(context,
				CommonAdressConstant.storeCheckOrder, params);
		System.out.println("result :" + result);
		return parsePayment(result);
	}

	public PayMent paymentCreateOrder(Context context, String id, String num,
			String addressId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String json = "{\"" + id + "\":" + num + "}";
		params.add(new BasicNameValuePair("products", json)); // json{id:num}
		params.add(new BasicNameValuePair("platform", "alipay"));
		// TODO 测试1分钱
		params.add(new BasicNameValuePair("amount", "0.01"));
		params.add(new BasicNameValuePair("debug", "1"));
		if(!StringBiz.isEmpty(addressId)) {
			params.add(new BasicNameValuePair("address_id", addressId));
		}
		String result = new CommonInterfaces().getDataFromUrlByPost(context,
				CommonAdressConstant.payment_create_order, params);
		System.out.println("-----paymentCreateOrder--------result :" + result);
		return parsePayment(result);
	}
	
	public PayMent paymentCreateOrder(Context context, String[] idArray, String[] numArray,
			String addressId, String couponId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String json = StringBiz.spliceJson(idArray, numArray);
		params.add(new BasicNameValuePair("products", json)); // json{id:num}
		params.add(new BasicNameValuePair("platform", "alipay"));
		if(!StringBiz.isEmpty(couponId)) {
			params.add(new BasicNameValuePair("coupon_id", couponId));
		}
		// TODO 测试1分钱
//		params.add(new BasicNameValuePair("amount", "0.01"));
//		params.add(new BasicNameValuePair("debug", "1"));
		if(!StringBiz.isEmpty(addressId)) {
			params.add(new BasicNameValuePair("address_id", addressId));
		}
		String result = new CommonInterfaces().getDataFromUrlByPost(context,
				CommonAdressConstant.payment_create_order, params);
		System.out.println("result :" + result);
		return parsePayment(result);
	}

	// {
	//
	// "error_code":22602,
	// "error":"不能重复购买已有的产品"
	//
	// }
	private PayMent parsePayment(String result) {
		if (result == null || result.equals("")) {
			return null;
		}
		try {
			PayMent payment;
			JSONObject data = new JSONObject(result);
			String errorCode = data.optString("error_code");
			if (!StringBiz.isEmpty(errorCode)) {
				payment = new PayMent();
				payment.setMsg(data.optString("error"));
				payment.setErrCode(errorCode);
				return payment;
			} else {
				JSONObject extra = data.optJSONObject("extra");
				JSONObject order = data.optJSONObject("order");
				String price = "0";
				String outTradeNo = "";
				String subject = "";
				if (order != null) {
					price = order.optString("amount");
					outTradeNo = order.optString("id");
					subject = order.optString("subject");
				}
				if (extra != null) {
					Gson gson = new Gson();
					payment = gson.fromJson(extra.toString(), PayMent.class);
					payment.setPrice(price);
					payment.setOut_trade_no(outTradeNo);
					payment.setSubject(subject);
					return payment;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(String partner, String seller_id,
			String out_trade_no, String notify_url, String it_b_pay,
			String subject, String body, String price) {
//		price = "0.01";
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + partner + "\"";
		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + seller_id + "\"";
		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + out_trade_no + "\"";
		// orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";
		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";
		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";
		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + notify_url + "\"";
		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";
		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";
		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=" + "\"" + it_b_pay + "\"";
		// orderInfo += "&it_b_pay=\"30m\"";
		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";
		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";
		System.out.println("orderInfo :" + orderInfo);
		return orderInfo;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content, String privateKey) {
		return SignUtils.sign(content, privateKey);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

}
