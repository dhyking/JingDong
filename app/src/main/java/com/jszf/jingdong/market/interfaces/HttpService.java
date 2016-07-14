package com.jszf.jingdong.market.interfaces;

import com.jszf.jingdong.market.bean.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/13.
 */
public interface HttpService {
    @GET("250")
    Observable<MovieEntity> getMovie (@Query("start") int start, @Query("count") int count);
}
