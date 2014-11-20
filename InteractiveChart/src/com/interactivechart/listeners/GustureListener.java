package com.interactivechart.listeners;

import android.os.Handler;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

import com.interactivechart.tools.Scroller;
import com.interactivechart.tools.Zoomer;

public class GustureListener extends SimpleOnGestureListener {

	private static final int SCROLL_TIMEOUT = 500;
	private static final int SHORT_DOUBLE_TAP_TIMEOUT = 200;
	private static final int LONG_DOUBLE_TAP_TIMEOUT = 1000;

	private Zoomer mZoomer;
	private Scroller mScroller;
	private ScaleListener mScaleListener;
	private Handler mHandler;

	private int mViewWidth;
	private boolean isScrolling;
	private boolean isShortDoubleTap;
	private boolean isLongDoubleTap;

	private Runnable resetScrolling = new Runnable() {
		@Override
		public void run() {
			isScrolling = false;
		}
	};

	private Runnable resetShortDoubleTap = new Runnable() {
		@Override
		public void run() {
			isShortDoubleTap = false;
		}
	};
	private Runnable resetLongDoubleTap = new Runnable() {
		@Override
		public void run() {
			isLongDoubleTap = false;
		}
	};

	public GustureListener(Zoomer zoomer, Scroller scroller,
			ScaleListener scaleListener) {
		mZoomer = zoomer;
		mScroller = scroller;
		mScaleListener = scaleListener;
		mHandler = new Handler();
	}

	public void setViewWidth(int viewWidth) {
		mViewWidth = viewWidth;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		isScrolling = true;
		mHandler.removeCallbacks(resetScrolling);
		mHandler.postDelayed(resetScrolling, SCROLL_TIMEOUT);
		mScroller.scrollImmediately(distanceX / mViewWidth);
		return false;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		mHandler.removeCallbacks(resetScrolling);
		mHandler.post(resetScrolling);
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		mHandler.removeCallbacks(resetScrolling);
		mHandler.post(resetScrolling);
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		if (isScrolling || isLongDoubleTap)
			return;
		if (mScaleListener != null)
			if (mScaleListener.isScaling())
				return;
		mZoomer.softZoom(0.5f);
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		switch (e.getAction()) {
		case MotionEvent.ACTION_UP:
			if (!isShortDoubleTap)
				return false;
			mHandler.removeCallbacks(resetShortDoubleTap);
			mHandler.removeCallbacks(resetLongDoubleTap);
			mHandler.post(resetShortDoubleTap);
			mHandler.post(resetLongDoubleTap);

			if (mScaleListener != null)
				if (mScaleListener.isScaling())
					return false;
			mZoomer.softZoom(1.25f);
			return true;

		case MotionEvent.ACTION_DOWN:
			mHandler.removeCallbacks(resetShortDoubleTap);
			mHandler.removeCallbacks(resetLongDoubleTap);
			mHandler.postDelayed(resetShortDoubleTap, SHORT_DOUBLE_TAP_TIMEOUT);
			mHandler.postDelayed(resetLongDoubleTap, LONG_DOUBLE_TAP_TIMEOUT);
			isShortDoubleTap = true;
			isLongDoubleTap = true;
			break;
		}
		return false;
	}
}
