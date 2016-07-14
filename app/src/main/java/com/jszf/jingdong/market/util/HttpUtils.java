package com.jszf.jingdong.market.util;

import android.util.Log;

import com.jszf.jingdong.Contants;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/7/6.
 */
public class HttpUtils {
    private volatile static HttpUtils mInstace;
    private OkHttpClient mOkHttpClient;


    /**
     * 创建唯一OkHttpClient对象
     * @param okHttpClient
     */
    public HttpUtils(OkHttpClient okHttpClient) {
        if (mOkHttpClient == null)
        {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }
    }

    /**
     * 创建唯一HttpUtils工具类对象并生成唯一个网络OkHttpClient请求对象
     * @param okHttpClient
     * @return HttpUtils
     */
    public static HttpUtils initHttpUtils(OkHttpClient okHttpClient){
        if (mInstace == null){
            synchronized (HttpUtils.class) {
                if (mInstace == null) {
                    mInstace = new HttpUtils(okHttpClient);
                }
            }
        }
        return mInstace;
    }

    /**
     * @param url  访问地址
     * @param map 数据源
     * @param mCallback
     * @throws IOException
     */
    public  void requestServerByRSA(String url, HashMap<String,String> map,Callback mCallback) throws IOException {
        FormEncodingBuilder mFormEncodingBuilder = new FormEncodingBuilder();
        String param = ParaUtils.createLinkString(map);
        String beforeSign  = param+ Contants.MD5_KEY;
        Log.d("HttpUtils", "before:"+beforeSign);
        String sign = MD5Utils.MD5(beforeSign);
        Log.d("HttpUtils", sign);
        for (HashMap.Entry<String,String> entry : map.entrySet()){
            mFormEncodingBuilder.add(entry.getKey(),entry.getValue());
        }
        mFormEncodingBuilder.add("sign",sign);
        RequestBody forBody = mFormEncodingBuilder.build();
//        RequestBody formBody = new FormEncodingBuilder()
//                .add("platform", "android")
//                .add("name", "bug")
//                .add("subject", "XXXXXXXXXXXXXXX")
//                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(forBody)
                .build();

     mOkHttpClient.newCall(request).enqueue(mCallback);
    }
}
