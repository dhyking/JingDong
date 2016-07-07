package com.jszf.jingdong.market.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.jszf.jingdong.MainTab;
import com.jszf.jingdong.R;
import com.jszf.jingdong.market.base.BaseActivity;
import com.jszf.jingdong.market.fragment.CategoryFragment;
import com.jszf.jingdong.market.fragment.DiscoverFragment;
import com.jszf.jingdong.market.fragment.HomeFragment;
import com.jszf.jingdong.market.fragment.MyInfoFragment;
import com.jszf.jingdong.market.fragment.ShopCarFragment;
import com.kevin.tabindicator.internal.TabIndicatorBase;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class MainActivity extends BaseActivity implements TabIndicatorBase.OnTabSelectedListener, TabHost.OnTabChangeListener, View.OnTouchListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.iv_toolbar_scan)
    ImageView mIvToolbarScan;
    @Bind(R.id.iv_toolbar_search)
    ImageView mIvToolbarSearch;
    @Bind(R.id.iv_toolbar_voice)
    ImageView mIvToolbarVoice;
    @Bind(R.id.edt_toolbar_search)
    EditText mEdtToolbarSearch;
    @Bind(R.id.ll_top_search)
    RelativeLayout mLlTopSearch;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ll_container)
    FrameLayout mLlContainer;
    @Bind(R.id.top_logo)
    ImageView mTopLogo;
    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabhost;


    private Fragment[] fragArr = new Fragment[]{HomeFragment.newInstance(), CategoryFragment.newInstance(),
            DiscoverFragment.newInstance(), ShopCarFragment.newInstance(), MyInfoFragment.newInstance()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initTab();
//        initFragment();
        setClickListerner();


    }

    private void initTab() {
        MainTab[] mTabs = MainTab.values();
        mTabhost.setup(this, getSupportFragmentManager(), R.id.ll_container);
        for (int i = 0; i < mTabs.length; i++) {
            MainTab mTab = mTabs[i];
            TabHost.TabSpec mTabSpec = mTabhost.newTabSpec(mTab.getTag());
            View view = View.inflate(this, R.layout.item_home_bottom, null);
            ImageView icon = (ImageView) view.findViewById(R.id.icon_home_bottom);
            Drawable drawable = this.getResources().getDrawable(mTab.getResId());
            icon.setImageDrawable(drawable);
            mTabSpec.setIndicator(view);
            mTabhost.addTab(mTabSpec, mTab.getCls(), null);
        }
    }

    private void setClickListerner() {
        mIvToolbarSearch.setOnClickListener(this);
        mIvToolbarScan.setOnClickListener(this);
        mIvToolbarVoice.setOnClickListener(this);
    }

    /**
     * 初始化fragment，显示首页
     */
    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_container, fragArr[0]);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_toolbar_search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_toolbar_scan:
                Toast.makeText(this, "scan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_toolbar_voice:
                Toast.makeText(this, "voice", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onTabChanged(String tabId) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouchEvent(event);
        boolean consumed = false;
        // use getTabHost().getCurrentTabView to decide if the current tab is
        // touched again
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && v.equals(mTabhost.getCurrentTabView())) {
            // use getTabHost().getCurrentView() to get a handle to the view
            // which is displayed in the tab - and to get this views context
            Fragment currentFragment = getCurrentFragment();
//            if (currentFragment != null
//                    && currentFragment instanceof OnTabReselectListener) {
//                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
//                listener.onTabReselect();
//                consumed = true;
//            }
        }
        return consumed;

    }

    @Override
    public void onTabSelected(int index) {
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(
                mTabhost.getCurrentTabTag());
    }

}
