package com.jszf.jingdong;

import com.jszf.jingdong.market.fragment.CategoryFragment;
import com.jszf.jingdong.market.fragment.DiscoverFragment;
import com.jszf.jingdong.market.fragment.HomeFragment;
import com.jszf.jingdong.market.fragment.ShopCarFragment;

/**
 * Created by Administrator on 2016/7/7.
 */
public enum MainTab {
    HOME(0,"京东",R.drawable.home_bottom_jd, HomeFragment.class),
    CATEGORY(1,"分类",R.drawable.home_bottom_category, CategoryFragment.class),
    DISCOVER(2,"发现",R.drawable.home_bottom_discover, DiscoverFragment.class),
    SHOP(3,"购物车",R.drawable.home_bottom_shop, ShopCarFragment.class);
//    MYINFO(4,"我的",R.drawable.home_bottom_myinfo, MyInfoFragment.class);

    private int index;
    private String tag;
    private int resId;
    private Class cls;

    MainTab(int mIndex, String mTag, int mResId, Class mCls) {
        index = mIndex;
        tag = mTag;
        resId = mResId;
        cls = mCls;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int mIndex) {
        index = mIndex;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String mTag) {
        tag = mTag;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int mResId) {
        resId = mResId;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> mCls) {
        cls = mCls;
    }
}
