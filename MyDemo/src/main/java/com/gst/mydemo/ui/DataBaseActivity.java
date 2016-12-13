package com.gst.mydemo.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gst.mydemo.R;
import com.gst.mydemo.db.ContactInjfoDao;
import com.gst.mydemo.db.MyDBHelper;

/**
 * Created by 善同 on 2016/2/22.
 */
public class DataBaseActivity extends BaseActivity {

    private EditText mEtName;
    private EditText mEtPhone;
    private ContactInjfoDao mDao;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data_base);

        mContext = DataBaseActivity.this;
//        MyDBHelper helper = new MyDBHelper(mContext);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        helper.onOpen(db);
        mDao = new ContactInjfoDao(mContext);
        mEtName = (EditText) findViewById(R.id.mEtName);
        mEtPhone = (EditText) findViewById(R.id.mEtPhone);

    }

    public void test(View view) {
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "填写不完整", Toast.LENGTH_SHORT).show();
            return;
        }
        String age = mDao.alterAge(name);
        System.out.println("age :" + age);
    }

    public void add(View view) {

        String name = mEtName.getText().toString().trim();
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "填写不完整", Toast.LENGTH_SHORT).show();
            return;
        } else {
            long addLong = mDao.addDate(name, phone, "2");
            if (addLong == -1) {
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "数据添加在第  " + addLong + "   行", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 根据名字删除 item
    public void delete(View view) {
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "填写不完整", Toast.LENGTH_SHORT).show();
            return;
        } else {
            int deleteDate = mDao.deleteDate(name);
            if (deleteDate == -1) {
                Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "成功删除  " + deleteDate + "   条数据", Toast.LENGTH_SHORT).show();
            }

        }

    }

    // 删除表里所有内容
    public void deleteAll(View view) {
        mDao.clearFeedTable("contactinfo");
    }

    // 删除数据库
    public void deleteDb(View view) {
        String name = mEtName.getText().toString().trim();
        mDao.deleteDatabase(mContext, "mintest.db");
    }

    // 修改
    public void update(View view) {
        String name = mEtName.getText().toString().trim();
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "填写不完整", Toast.LENGTH_SHORT).show();
            return;
        } else {
            int count = mDao.updateData(name, phone);
            if (count == -1) {
                Toast.makeText(this, "更新失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "数据更新了  " + count + "   行", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void query1(View view) {
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "填写不完整", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String phoneResult = mDao.alterDate(name);

            Toast.makeText(this, "手机号码为:    " + phoneResult, Toast.LENGTH_SHORT).show();


        }
    }

    // 查询-显示数据库所有内容
    public void query(View view) {
        MyDBHelper helper = new MyDBHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from contactinfo", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String sbName = cursor.getString(cursor.getColumnIndex("name"));
                String sbPhone = cursor.getString(cursor.getColumnIndex("phone"));
                System.out.println("name--sbName- " + sbName + " --- phone :" + sbPhone);
                String sbId = cursor.getString(cursor.getColumnIndex("id"));
                System.out.println("name--sbId- " + sbId);
            }
        } else {
            System.out.println("name--sb- null---");
        }
        cursor.close();
    }
}
