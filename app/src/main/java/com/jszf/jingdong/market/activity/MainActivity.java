package com.jszf.jingdong.market.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jszf.jingdong.R;
import com.jszf.jingdong.market.base.BaseActivity;
import com.jszf.jingdong.market.fragment.CategoryFragment;
import com.jszf.jingdong.market.fragment.DiscoverFragment;
import com.jszf.jingdong.market.fragment.HomeFragment;
import com.jszf.jingdong.market.fragment.MyInfoFragment;
import com.jszf.jingdong.market.fragment.ShopCarFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class MainActivity extends BaseActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.realcontent)
    FrameLayout mRealcontent;
    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabhost;

    private String[] tabTagArr = {"京东", "分类", "发现", "购物车", "我的"};
    private Class<?>[] fragArr = new Class[]{HomeFragment.class, CategoryFragment.class,
            DiscoverFragment.class, ShopCarFragment.class, MyInfoFragment.class};
    private int[] resIdArr = {R.drawable.home_bottom_jd, R.drawable.home_bottom_category,
            R.drawable.home_bottom_discover, R.drawable.home_bottom_shop, R.drawable.home_bottom_myinfo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTabs();

    }

    private void initTabs() {
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realcontent);
        for (int i = 0; i < 5; i++) {
            View tabIndicator = getLayoutInflater().inflate(
                    R.layout.item_home_bottom, null);
            ImageView mImageView1 = (ImageView) tabIndicator.findViewById(R.id.imageView1);
            mImageView1.setImageResource(resIdArr[i]);
            if (i == 3) {
                TextView mTextView1 = (TextView) tabIndicator.findViewById(R.id.textView1);
                mTextView1.setText("1");
            }
            mTabhost.addTab(
                    mTabhost.newTabSpec(tabTagArr[i])
                            .setIndicator(tabIndicator), fragArr[i], null);
        }
    }


}
