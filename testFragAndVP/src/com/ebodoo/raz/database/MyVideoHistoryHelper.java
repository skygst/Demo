package com.ebodoo.raz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库Helper类，必须继承自 SQLiteOpenHelper
 * 当一个继承自 SQLiteOpenHelper 后需要复写两个方法，分别是 onCreate()  和 onUpgrade()
 * onCreate()： onCreate是在数据库创建的时候调用的，主要用来初始化数据表结构和插入数据初始化的记录
 * onUpgrade()：onUpGrade是在数据库版本升级的时候调用的，主要用来改变表结构
 *
 *
 *  数据库帮助类要做的事情特别简单：
 *  1、复写onCreate()  和 onUpgrade()方法
 *  2、在这两个方法里面填写相关的sql语句
 *
 *
 */
public class MyVideoHistoryHelper extends SQLiteOpenHelper{


    /**
     *  数据库名：mintest.db
     *  表名：videoHistory
     *
     * @param context
     */
    public MyVideoHistoryHelper(Context context) {
        /**
         * 参数说明：
         *
         * 第一个参数： 上下文
         * 第二个参数：数据库的名称
         * 第三个参数：null代表的是默认的游标工厂
         * 第四个参数：是数据库的版本号  数据库只能升级,不能降级,版本号只能变大不能变小
         */
        super(context, "video_history.db", null, 2);
    }


    /**
     * onCreate是在数据库创建的时候调用的，主要用来初始化数据表结构和插入数据初始化的记录
     *
     * 当数据库第一次被创建的时候调用的方法,适合在这个方法里面把数据库的表结构定义出来.
     * 所以只有程序第一次运行的时候才会执行
     * 如果想再看到这个函数执行，必须写在程序然后重新安装这个app
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table videoHistory (id integer primary key autoincrement, " +
        		"albumsName varchar(100)," +
        		"videoId varchar(100)," +
        		"name varchar(100)," +
        		"youkeId varchar(100)," +
        		"videoUrl varchar(100)," +
        		"playTime varchar(100))");
    }


    /**
     * 当数据库更新的时候调用的方法
     * 这个要显示出来得在上面的super语句里面版本号发生改变时才会 打印  （super(context, "itheima.db", null, 2); ）
     * 注意，数据库的版本号只可以变大，不能变小，假设我们当前写的版本号是3，运行，然后又改成1，运行则报错。不能变小
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table videoHistory add account varchar(20)");
    }
}
