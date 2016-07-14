package com.jszf.jingdong.market.util;

import android.content.Context;
import android.widget.Toast;

import com.jszf.jingdong.market.interfaces.SubscriberOnNextListener;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ProgressSubscriber<T> extends Subscriber<T> {
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private Context mContext;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(mContext, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {
        mSubscriberOnNextListener.onNext(t);
    }
}
