package com.jszf.jingdong.market.custom.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class AutoScrollViewPager extends ViewPager{
	  private float xDistance, yDistance, xLast, yLast;
	public AutoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		timer = new Timer();
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScollChangeListener != null){
			onScollChangeListener.scroll(l);
		}
	}
	private Timer timer;
	private TimerTask task;
	private Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			setCurrentItem(getCurrentItem()+1);
			return false;
		}
	});

	private OnScollChangeListener onScollChangeListener;
	public void setOnScollChangeListener(OnScollChangeListener onScollChangeListener) {
		this.onScollChangeListener = onScollChangeListener;
	}

	public interface OnScollChangeListener{
		void scroll(int x);
	}
	// 20
	// 5ҳ
	public void setAdapter(AutoSlidingPagerAdapter adapter){
		super.setAdapter(adapter);
		int curPage = Integer.MAX_VALUE/2 - (Integer.MAX_VALUE/2)%adapter.getViews().size();
		setCurrentItem(curPage,false);
		startSliding();
	}
	/**
	 * 开始滑动
	 */
	public void startSliding(){
		if(task == null){
			task = new TimerTask() {

				@Override
				public void run() {
					handler.sendEmptyMessage(0);
				}
			};
			timer.schedule(task, 3000, 3000);
		}
	}

	/**
	 * 停止滑动
	 */
	public void stopSliding(){
		handler.removeMessages(0);
		if(task != null){
			task.cancel();
			task = null;
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			 xDistance = yDistance = 0f;
             xLast = ev.getX();
             yLast = ev.getY();
//			stopSliding();
//			
//			MainActivity aty = (MainActivity) getContext();
//			aty.setIntercept(false);
			break;
		 case MotionEvent.ACTION_MOVE:
             final float curX = ev.getX();
             final float curY = ev.getY();

             xDistance += Math.abs(curX - xLast);
             yDistance += Math.abs(curY - yLast);
             xLast = curX;
             yLast = curY;

             if(xDistance < yDistance){
                 return false;
             }  
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
//			startSliding();
//			aty = (MainActivity) getContext();
//			aty.setIntercept(true);
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			startSliding();
			break;
		}
		return super.onTouchEvent(ev);
	}

	public  class AutoSlidingPagerAdapter extends PagerAdapter{
		private List<ImageView> views;

		public AutoSlidingPagerAdapter() {
			super();
			views = new ArrayList<ImageView>();
			for (int i = 0;i < 4;i++) {
				ImageView img = new ImageView(getContext());
				img.setScaleType(ScaleType.CENTER_CROP);
				img.setTag(i);
				img.setOnClickListener(onClickListener);
				views.add(img);
			}
		}
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = views.get(position%views.size());
			container.removeView(view);
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = views.get(position%views.size());
			container.addView(view);
			return view;
		}
		public List<ImageView> getViews() {
			return views;
		}

		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Integer i = (Integer) v.getTag();
			}
		};
		
	}
}
