package com.ebodoo.raz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *  ContactInjfoDao  数据库操作类  dao后缀的都是数据库操作类
 *
 *  我们这里的每一个 增删改查 的方法都通过getWritableDatabase()去实例化了一个数据库,这里必须这么做
 *  不客气抽取 成为一个成员变量, 否则报错,若是觉得麻烦可以通过定义方法来置为null和重新赋值
 *
 *  —— 其实dao类在这里做得事情特别简单：
 *  1、定义一个构造方法，利用这个方法去实例化一个  数据库帮助类
 *  2、编写dao类的对应的 增删改查 方法。
 *
 */
public class ContactInjfoDao {

    private MyVideoHistoryHelper mMyHistoryDBHelper;

    /**
     * dao类需要实例化数据库Help类,只有得到帮助类的对象我们才可以实例化 SQLiteDatabase
     * @param context
     */
    public ContactInjfoDao(Context context) {
        mMyHistoryDBHelper=new MyVideoHistoryHelper(context);
    }

    // 将数据库打开帮帮助类实例化，然后利用这个对象
    // 调用谷歌的api去进行增删改查

    // 增加的方法吗，返回的的是一个long值
    public long addDate(String albumsName, String videoId, String name, String youkeId, String videoUrl, String playTime){
        // 增删改查每一个方法都要得到数据库，然后操作完成后一定要关闭
        // getWritableDatabase(); 执行后数据库文件才会生成
        // 数据库文件利用DDMS可以查看，在 data/data/包名/databases 目录下即可查看
        SQLiteDatabase sqLiteDatabase =  mMyHistoryDBHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("albumsName", albumsName);
        contentValues.put("name", name);
        contentValues.put("videoId", videoId);
        contentValues.put("youkeId", youkeId);
        contentValues.put("videoUrl", videoUrl);
        contentValues.put("playTime", playTime);
        // 返回,显示数据添加在第几行
        // 加了现在连续添加了3行数据,突然删掉第三行,然后再添加一条数据返回的是4不是3
        // 因为自增长
        long rowid=sqLiteDatabase.insert("videoHistory", null, contentValues);

        sqLiteDatabase.close();
        return rowid;
    }


    // 删除的方法，返回值是int
    public int deleteDate(String videoId){
        SQLiteDatabase sqLiteDatabase = mMyHistoryDBHelper.getWritableDatabase();
        int deleteResult = sqLiteDatabase.delete("videoHistory", "videoId=?", new String[]{videoId});
        sqLiteDatabase.close();
        return deleteResult;
    }

    /**
     * 修改的方法
     * @param name
     * @param newPhone
     * @return
     */
//    public int updateData(String name,String newPhone){
//        SQLiteDatabase sqLiteDatabase = mMyHistoryDBHelper.getWritableDatabase();
//        ContentValues contentValues =new ContentValues();
//        contentValues.put("phone", newPhone);
//        int updateResult = sqLiteDatabase.update("videoHistory", contentValues, "name=?", new String[]{name});
//        sqLiteDatabase.close();
//        return updateResult;
//    }

    /**
     * 查询的方法（查找专辑名）--- 根据ID查找专辑名
     * @param name
     * @return
     */
    public String findAlbumsFromVideoId(String videoId){
        String phone = null;

        SQLiteDatabase readableDatabase = mMyHistoryDBHelper.getReadableDatabase();
        // 查询比较特别,涉及到 cursor 根据名字查电话号码
        Cursor cursor = readableDatabase.query("videoHistory", new String[]{"albumsName"}, "videoId=?", new String[]{videoId}, null, null, null);
        // 根据号码查名字
//        Cursor cursor = readableDatabase.query("videoHistory", new String[]{"name"}, "phone=?", new String[]{phone}, null, null, null);
        if(cursor.moveToNext()){
            phone=cursor.getString(0);
        }
        cursor.close(); // 记得关闭 corsor
        readableDatabase.close(); // 关闭数据库
        return phone;
    }
    
    public String findPlayTimeFromVideoId(String videoId){
    	String phone = null;
    	
    	SQLiteDatabase readableDatabase = mMyHistoryDBHelper.getReadableDatabase();
    	// 查询比较特别,涉及到 cursor 根据名字查电话号码
    	Cursor cursor = readableDatabase.query("videoHistory", new String[]{"playTime"}, "videoId=?", new String[]{videoId}, null, null, null);
    	// 根据号码查名字
//        Cursor cursor = readableDatabase.query("videoHistory", new String[]{"name"}, "phone=?", new String[]{phone}, null, null, null);
    	if(cursor.moveToNext()){
    		phone=cursor.getString(0);
    	}
    	cursor.close(); // 记得关闭 corsor
    	readableDatabase.close(); // 关闭数据库
    	return phone;
    }

    /**
     *  删除数据库
     * @param context
     * @return
     */
    public boolean deleteDatabase(Context context, String name) {
        return context.deleteDatabase(name);
    }

    /**
     *  清楚表里的内容，但不删除数据库
     * @param tableName
     */
    public void clearFeedTable(String tableName){
        String sql = "DELETE FROM " + tableName +";";
        SQLiteDatabase db = mMyHistoryDBHelper.getReadableDatabase();
        db.execSQL(sql);
    }

}
