package com.jszf.jingdong.market.base;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Administrator on 2016/7/5.
 */
public class BaseDialog extends Dialog {
    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(Context context) {
        super(context);
    }
}
