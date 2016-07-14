package com.jszf.jingdong.market.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ListForScrollView extends ListView {
    public ListForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpc = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }
}
