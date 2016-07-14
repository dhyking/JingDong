package com.jszf.jingdong.market.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/12.
 */
public class GoodsInfo implements Serializable{
    private String goodsId;
    private String goodsName;
    private String goodsIcon;
    private String goodsType;
    private String goodsPrice;
    private String goodsPercent;
    private int goodsComment;
    private int isPhone;
    private int isFavor;

    public GoodsInfo(String mGoodsId, String mGoodsName, String mGoodsIcon,
                     String mGoodsType, String mGoodsPrice, String mGoodsPercent,
                     int mGoodsComment, int mIsPhone, int mIsFavor) {
        goodsId = mGoodsId;
        goodsName = mGoodsName;
        goodsIcon = mGoodsIcon;
        goodsType = mGoodsType;
        goodsPrice = mGoodsPrice;
        goodsPercent = mGoodsPercent;
        goodsComment = mGoodsComment;
        isPhone = mIsPhone;
        isFavor = mIsFavor;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String mGoodsId) {
        goodsId = mGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String mGoodsName) {
        goodsName = mGoodsName;
    }

    public String getGoodsIcon() {
        return goodsIcon;
    }

    public void setGoodsIcon(String mGoodsIcon) {
        goodsIcon = mGoodsIcon;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String mGoodsType) {
        goodsType = mGoodsType;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String mGoodsPrice) {
        goodsPrice = mGoodsPrice;
    }

    public String getGoodsPercent() {
        return goodsPercent;
    }

    public void setGoodsPercent(String mGoodsPercent) {
        goodsPercent = mGoodsPercent;
    }

    public int getGoodsComment() {
        return goodsComment;
    }

    public void setGoodsComment(int mGoodsComment) {
        goodsComment = mGoodsComment;
    }

    public int getIsPhone() {
        return isPhone;
    }

    public void setIsPhone(int mIsPhone) {
        isPhone = mIsPhone;
    }

    public int getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(int mIsFavor) {
        isFavor = mIsFavor;
    }
}
