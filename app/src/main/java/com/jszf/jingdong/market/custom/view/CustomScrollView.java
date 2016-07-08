package com.jszf.jingdong.market.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * 自定义ScrollView解决与viewpager滑动冲突
 * Created by Administrator on 2016/7/8.
 */
public class CustomScrollView extends ScrollView {
    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context,new MyGestureDetector());
        setFadingEdgeLength(0);
    }

    GestureDetector gestureDetector;
    View.OnTouchListener onTouchListener;
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        // return super.onInterceptTouchEvent(ev);
        return super.onInterceptTouchEvent(ev) && gestureDetector.onTouchEvent(ev);
    }


    /**
     * 手势监听
     */
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            //控制手指滑动的距离
            if (Math.abs(distanceY) >= Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}
