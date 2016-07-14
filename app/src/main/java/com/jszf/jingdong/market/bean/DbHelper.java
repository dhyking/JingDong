package com.jszf.jingdong.market.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类，新建数据库、表和升级
 * Created by Administrator on 2016/7/12.
 */
public class DbHelper extends SQLiteOpenHelper{
    private final static String DB_NAME = "goods.db";
    private final static int DB_VERSION = 1;


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {

            String sqlForGoodsRecord = "crate table good_record (_id integer primary key autoincrement," +
                    "goodsId integer,goodsName varchar(100),goodsIcon varchar(100),goodsType varchar(20)," +
                    "goodsPrice varchar(15),goodsPercent varchar(10),goodsComment integer,isPhone integer,isFavor integer)";
            db.execSQL(sqlForGoodsRecord);
            db.setTransactionSuccessful();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
