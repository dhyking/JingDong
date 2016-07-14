package com.jszf.jingdong.market.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.jszf.jingdong.R;
import com.jszf.jingdong.market.custom.view.ListForScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/7.
 */
public class CategoryFragment extends Fragment {
    @Bind(R.id.vertical_listView)
    ListForScrollView mVerticalListView;
    @Bind(R.id.scroll_category)
    ScrollView mScrollCategory;
    @Bind(R.id.grid_category)
    GridView mGridCategory;
    @Bind(R.id.progress_category)
    ProgressBar mProgressCategory;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView != null) {
            return mView;
        } else {
            mView = inflater.inflate(R.layout.fragment_category, container, false);
        }
        ButterKnife.bind(this, mView);
        return mView;
    }

    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
