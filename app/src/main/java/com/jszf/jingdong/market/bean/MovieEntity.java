package com.jszf.jingdong.market.bean;

/**
 * Created by Administrator on 2016/7/13.
 */
public class MovieEntity {
    private String count;
    private String start;
    private String total;
//    private String rating;
//    private String max;
//    private String average;
//    private String starts;

    @Override
    public String toString() {
        return "MovieEntity{" +
                "count='" + count + '\'' +
                ", start='" + start + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
