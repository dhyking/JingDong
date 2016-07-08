package com.jszf.jingdong.market.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/7/7.
 */
public class ShopCarFragment extends Fragment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static ShopCarFragment newInstance() {

        Bundle args = new Bundle();

        ShopCarFragment fragment = new ShopCarFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
