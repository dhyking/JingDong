package com.jszf.jingdong.market.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.jszf.jingdong.Contants;
import com.jszf.jingdong.R;
import com.jszf.jingdong.market.adapter.HomePagerAdapter;
import com.jszf.jingdong.market.bean.GoodsInfo;
import com.jszf.jingdong.market.listener.HoPageChangeListener;
import com.jszf.jingdong.market.util.MD5Utils;
import com.jszf.jingdong.market.util.ParaUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/7.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
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
    @Bind(R.id.top_logo)
    ImageView mTopLogo;
    @Bind(R.id.img_indicator04)
    ImageView mImgIndicator04;
    @Bind(R.id.img_cover)
    ImageView mImgCover;
    @Bind(R.id.iv_home_viewpager1)
    ImageView mIvHomeViewpager1;
    @Bind(R.id.iv_home_viewpager2)
    ImageView mIvHomeViewpager2;
    @Bind(R.id.iv_home_viewpager3)
    ImageView mIvHomeViewpager3;
    @Bind(R.id.iv_home_viewpager4)
    ImageView mIvHomeViewpager4;
    @Bind(R.id.img_indicator01)
    ImageView mImgIndicator01;
    @Bind(R.id.img_indicator02)
    ImageView mImgIndicator02;
    private View mView;
    private List<ImageView> list;

    private int[] drawableArr = {R.drawable.home_center_menu_wdgz, R.drawable.home_center_menu_wlcx,
            R.drawable.home_center_menu_cz, R.drawable.home_center_menu_dyp, R.drawable.home_center_menu_yxcz,
            R.drawable.home_center_menu_xjk, R.drawable.home_center_menu_ljd, R.drawable.home_center_menu_gd};
    private String[] textNameArr = {"我的关注", "物流查询", "充值", "电影票", "游戏充值", "小金库", "领金豆", "更多"};
    private boolean isFoucusRight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView != null) {
            return mView;
        } else {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        ButterKnife.bind(this, mView);
        setGridView();
        setViewPager();
        autoScroll();
        getMovie();
        getData();
        Log.d("TAG", "3:" + 5);
        return mView;
    }

    private void getData() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        long startTime = System.currentTimeMillis();
        String currentTime = sf.format(startTime);
        HashMap<String,String> map = new HashMap<>();
        map.put("service", "offBatchSearchOrder");
        map.put("version", "V1.0");
        map.put("merchantNo", "TX0000039");
        map.put("page", "" + 1);
//        map.put("merchantNo", "TX0000034");
        map.put("payType", "1,2,3,9");
        map.put("payMethod", "1");
        map.put("status", "1");
        map.put("startDate", currentTime);
        map.put("endDate", currentTime);
        map.put("sign", MD5Utils.MD5(ParaUtils.createLinkString(map) + Contants.MD5_KEY));
        OkHttpUtils.post()
                .url(Contants.PATH)
                .params(map)
                .build()
                .connTimeOut(5000)
                .readTimeOut(5000)
                .execute(new com.zhy.http.okhttp.callback.Callback() {
                    @Override
                    public Object parseNetworkResponse(okhttp3.Response mResponse) throws Exception {
                        return mResponse.body().string();
                    }

                    @Override
                    public void onError(Call mCall, Exception e) {

                    }

                    @Override
                    public void onResponse(Call mCall, Object o) {
                        Log.d(TAG, "---《：" + o.toString());
                    }
                });
    }

    /**
     * 网络请求获取电影信息
     */
    private void getMovie() {
        String baseUrl = "https://api.douban.com/v2/movie/top250?start=0&count=10";
        OkHttpUtils.get()
                .url(baseUrl)
                .build()
                .connTimeOut(5000)
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response mResponse) throws Exception {
                        return mResponse.body().string();
                    }

                    @Override
                    public void onError(Call mCall, Exception e) {
                        Log.d(TAG, "error:"+e.getMessage());
                    }

                    @Override
                    public void onResponse(Call mCall, Object o) {
                        Log.d(TAG, "结果："+o.toString());

                    }
                });
//        Retrofit mRetrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        HttpService mHttpService = mRetrofit.create(HttpService.class);
//        mHttpService.getMovie(0,10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MovieEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(getActivity(), "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(MovieEntity t) {
//                        Log.d(TAG, t.toString());
//                    }
//                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getTestData();
    }

    private void getTestData() {
        String result = "";
        try {
            //读取本地的json数据
            InputStream mInputStream  = getActivity().getResources().getAssets().open("goods.txt");
//            byte[] buffer = new byte[mInputStream.available()];
            InputStreamReader mInputStreamReader = new InputStreamReader(mInputStream);
            BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
            String line = "";
            while ((line = mBufferedReader.readLine()) !=null) {
                result += line;
            }
            Log.d("TAG","result:"+result);
            JSONObject mJSONObject = new JSONObject(result);
            String goods = mJSONObject.getString("goods");
            JSONArray mJSONArray = new JSONArray(goods);
            for (int i = 0; i < mJSONArray.length(); i++) {
                JSONObject mJSONObject1 = (JSONObject) mJSONArray.get(i);
                String goodsId = mJSONObject1.getString("goodsId");
                String goodsName = mJSONObject1.getString("goodsName");
                String goodsIcon = mJSONObject1.getString("goodsIcon");
                String goodsType = mJSONObject1.getString("goodsType");
                String goodsPrice = mJSONObject1.getString("goodsPrice");
                String goodsPercent = mJSONObject1.getString("goodsPercent");
                int goodsComment = mJSONObject1.getInt("goodsComment");
                int isPhone = mJSONObject1.getInt("isPhone");
                int isFavor = mJSONObject1.getInt("isFavor");
                GoodsInfo mGoodsInfo = new GoodsInfo(goodsId,goodsName,goodsIcon,goodsType,
                        goodsPrice,goodsPercent,goodsComment,isPhone,isFavor);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setGridView() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < drawableArr.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("icon", drawableArr[i]);
            map.put("name", textNameArr[i]);
            list.add(map);
        }
        SimpleAdapter mAdapter = new SimpleAdapter(getActivity(), list,
                R.layout.item_home_grid, new String[]{"icon", "name"},
                new int[]{R.id.iv_home_grid, R.id.tv_home_name});
        mGridViewHome.setAdapter(mAdapter);
    }

    private void setViewPager() {
        Log.d("TAG", "1:" + 1);
        list = new ArrayList<ImageView>();
        mIvHomeViewpager1.setImageResource(R.drawable.img_home_banner2);
        mIvHomeViewpager1.setImageResource(R.drawable.img_home_banner3);
        mIvHomeViewpager1.setImageResource(R.drawable.img_home_banner4);
        mIvHomeViewpager1.setImageResource(R.drawable.img_home_banner5);
        list.add(mIvHomeViewpager1);
        list.add(mIvHomeViewpager2);
        list.add(mIvHomeViewpager3);
        list.add(mIvHomeViewpager4);
        HomePagerAdapter adapter = new HomePagerAdapter(list);
        Log.d("TAG", "2:" + 2);
        mViewpagerHome.setCurrentItem(0);
        mViewpagerHome.setOnPageChangeListener(new HoPageChangeListener(getActivity(), mImgIndicator01, 0));
//        Log.d("TAG", "F:" + f);
//        mViewpagerHome.setAdapter(adapter);
        Log.d("TAG", "3:" + 4);
    }

    /**
     * 自动滚动
     */
    private void autoScroll() {
        mViewpagerHome.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!HoPageChangeListener.isDragging) {
                    // 若用户没有拖拽，则自动滚动
                    mViewpagerHome.setCurrentItem(mViewpagerHome.getCurrentItem() + 1);
                }
//                if (isFoucusRight) {
//                    mScrollView.fullScroll(ScrollView.FOCUS_LEFT);
//                } else {
//                    mScrollView.fullScroll(ScrollView.FOCUS_RIGHT);
//                }
                isFoucusRight = !isFoucusRight;
                mViewpagerHome.postDelayed(this, 3000);
            }
        }, 3000);
//        mScrollView2.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                if (isFoucusRight) {
//                    mScrollView2.fullScroll(ScrollView.FOCUS_RIGHT);
//                } else {
//                    mScrollView2.fullScroll(ScrollView.FOCUS_LEFT);
//                }
//                mScrollView2.postDelayed(this, 3000);
//            }
//        }, 4000);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * 获取唯一homefragment对象
     *
     * @return
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
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
//    class HoPageChangeListener implements ViewPager.OnPageChangeListener {
//        private Context mContext;
//        private ImageView mImgCover;
//        public  boolean isDragging;
//        private int mLastPos;// 记录上一次ViewPager的位置
//
//        public HoPageChangeListener(ImageView mImgCover, Context mContext) {
//            this.mImgCover = mImgCover;
//            this.mContext = mContext;
//        }
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            int width = mImgCover.getWidth();
////        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mImgCover
////                .getLayoutParams();
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mImgCover
//                    .getLayoutParams();
//            int rightMargin = layoutParams.rightMargin;
//            int endPos = (width + rightMargin) * (position % 4);
//            int startPos = 0;
//            if (mLastPos < position) {
//                // 图片向右移动
//                startPos = (width + rightMargin) * (position % 4 - 1);
//            } else {
//                // 图片向左移动
//                startPos = (width + rightMargin) * (position % 4 + 1);
//            }
//            ObjectAnimator.ofFloat(mImgCover, "translationX", startPos, endPos)
//                    .setDuration(300).start();
//            mLastPos = position;
//
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//            switch (state) {
//                case ViewPager.SCROLL_STATE_DRAGGING:
//                    // 用户拖拽
//                    isDragging = true;
//                    break;
//                case ViewPager.SCROLL_STATE_IDLE:
//                    // 空闲状态
//                    isDragging = false;
//                    break;
//                case ViewPager.SCROLL_STATE_SETTLING:
//                    // 被释放时
//                    isDragging = false;
//                    break;
//
//                default:
//                    break;
//            }
//
//        }
//    }
}

