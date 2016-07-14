package com.jszf.jingdong.market.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class HomePagerAdapter extends PagerAdapter {
    private List<ImageView> list;

    public HomePagerAdapter(List<ImageView> mList) {
        list = mList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position % list.size()));
        return list.get(position % list.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView mImageView  = list.get(position % list.size());
        container.removeView(mImageView);
    }
}
