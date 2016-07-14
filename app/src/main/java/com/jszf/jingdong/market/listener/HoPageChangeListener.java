package com.jszf.jingdong.market.listener;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/7/8.
 */
public class HoPageChangeListener implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private ImageView mImgCover;
    public static boolean isDragging;
    private int mLastPos;// 记录上一次ViewPager的位置
    private int rightMargins;

    public HoPageChangeListener(Context mContext, ImageView mImgCover, int mRightMargin) {
        this.mContext = mContext;
        this.mImgCover = mImgCover;
        rightMargins = mRightMargin;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int width = mImgCover.getWidth();
        Log.d("TAG","WIDTH:"+width);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mImgCover
                .getLayoutParams();
//        LayoutParams layoutParams = (LayoutParams) mImgCover
//                .getLayoutParams();
//        ViewPager.LayoutParams layoutParams = (ViewPager.LayoutParams) mImgCover.getLayoutParams();
//         LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mImgCover.getLayoutParams();
        int rightMargin = layoutParams.rightMargin;
        Log.d("TAG","rightMargin:"+rightMargin);

        int endPos = (width + rightMargin) * (position % 4);
        int startPos = 0;
        if (mLastPos < position) {
            // 图片向右移动
            startPos = (width + rightMargin) * (position % 4 - 1);
        } else {
            // 图片向左移动
            startPos = (width + rightMargin) * (position % 4 + 1);
        }
        ObjectAnimator.ofFloat(mImgCover, "translationX", startPos, endPos)
                .setDuration(300).start();
        mLastPos = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                // 用户拖拽
                isDragging = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                // 空闲状态
                isDragging = false;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                // 被释放时
                isDragging = false;
                break;

            default:
                break;
        }

    }
}
