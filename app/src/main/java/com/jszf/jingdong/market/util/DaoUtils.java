package com.jszf.jingdong.market.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jszf.jingdong.market.bean.DbHelper;
import com.jszf.jingdong.market.bean.GoodsInfo;

/**
 * 数据库工具类，用于数据库的增删改查操作
 * Created by Administrator on 2016/7/12.
 */
public class DaoUtils {
    private DbHelper mDbHelper;
    private SQLiteDatabase mSQLiteDatabase;
    public DaoUtils(DbHelper mDbHelper) {
        this.mDbHelper = mDbHelper;
        mSQLiteDatabase = mDbHelper.getWritableDatabase();
    }

    /**
     * 新增数据
     * @param mGoodsInfo
     */
    public void insertTOGoodsReord(GoodsInfo mGoodsInfo){

       String insertRecordString = "insert into good_record (goodsId,goodsName,goodsIcon,goodsType," +
               "goodsPrice,goodsPercent,isPhone,isFavor) values(?,?,?,?,?,?,?,?)";
        mSQLiteDatabase.execSQL(insertRecordString, new Object[]{mGoodsInfo.getGoodsId(), mGoodsInfo.getGoodsName(),
                mGoodsInfo.getGoodsIcon(), mGoodsInfo.getGoodsType(), mGoodsInfo.getGoodsPrice(), mGoodsInfo.getGoodsPercent(),
                mGoodsInfo.getGoodsComment(), mGoodsInfo.getIsPhone(), mGoodsInfo.getIsFavor()});
    }

    /**
     * 根据商品id删除数据库数据
     * @param goodsId
     */
    public void deleteFromGoodsRecord(String goodsId){
        String deleRecordString = "delete from good_record where goodsId=?";
        mSQLiteDatabase.execSQL(deleRecordString,new Object[]{goodsId});
    }

    /**
     * 修改数据库中的某些数据
     * @param mGoodsInfo
     */
    public void updateToGoodsRecord(GoodsInfo mGoodsInfo){
        String updateRecordString = "update good_record set goodsId=?,goodsName=?,goodsIcon=?," +
                "oodsType=?,goodsPrice=?,goodsPercent=?,isPhone=?,isFavor=?";
        mSQLiteDatabase.execSQL(updateRecordString,new Object[]{mGoodsInfo.getGoodsId(),
                mGoodsInfo.getGoodsName(), mGoodsInfo.getGoodsIcon(), mGoodsInfo.getGoodsType(),
                mGoodsInfo.getGoodsPrice(), mGoodsInfo.getGoodsPercent(), mGoodsInfo.getGoodsComment(),
                mGoodsInfo.getIsPhone(), mGoodsInfo.getIsFavor()});
    }

    /**
     * 根据商品id找到对应商品信息
     * @param goodsId
     * @return Cursor
     */
    public Cursor queryFromGoodsRecord(String goodsId){
        String findRecordString = "select * from good_record where goodsId="+goodsId;
        Cursor mCursor = mSQLiteDatabase.rawQuery(findRecordString, null);
        return mCursor;
    }
}
