package com.jszf.jingdong.market.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.jszf.jingdong.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/7.
 */
public class HomeFragment extends Fragment {
    @Bind(R.id.iv_toolbar_scan)
    ImageView mIvToolbarScan;
    @Bind(R.id.iv_toolbar_search)
    ImageView mIvToolbarSearch;
    @Bind(R.id.iv_toolbar_voice)
    ImageView mIvToolbarVoice;
    @Bind(R.id.edt_toolbar_search)
    EditText mEdtToolbarSearch;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.viewpager_home)
    ViewPager mViewpagerHome;
    @Bind(R.id.gridView_home)
    GridView mGridViewHome;
    private View mView;

    private int[] drawableArr  = {R.drawable.home_center_menu_wdgz,R.drawable.home_center_menu_wlcx,
    R.drawable.home_center_menu_cz,R.drawable.home_center_menu_dyp,R.drawable.home_center_menu_yxcz,
    R.drawable.home_center_menu_xjk,R.drawable.home_center_menu_ljd,R.drawable.home_center_menu_gd};
    private String[] textNameArr = {"我的关注","物流查询","充值","电影票","游戏充值","小金库","领金豆","更多"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mView);
        setViewPager();
        setGridView();
        return mView;
    }

    private void setGridView() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap();
        for (int i = 0; i < drawableArr.length; i++) {
            map.put("icon",drawableArr[i]);
            map.put("name",textNameArr[i]);
            list.add(map);
        }
        SimpleAdapter mAdapter = new SimpleAdapter(getActivity().getApplicationContext(),list,
                R.layout.item_home_grid,new String[]{"icon","name"},
                new int[]{R.id.iv_home_grid,R.id.tv_home_name});
        mGridViewHome.setAdapter(mAdapter);
    }

    private void setViewPager() {

    }

    /**
     * 获取唯一homefragment对象
     *
     * @return fragment
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_toolbar_scan, R.id.iv_toolbar_search, R.id.iv_toolbar_voice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_scan:
                break;
            case R.id.iv_toolbar_search:
                break;
            case R.id.iv_toolbar_voice:
                break;
        }
    }
}
