package com.interactivechart.tools;

import android.view.View;

public class Scroller {

	private Timeline mTimeline;
	private View mView;
	private boolean mCurrentTimeWall;

	public Scroller(Timeline timeline, View view) {
		mTimeline = timeline;
		mView = view;
		mCurrentTimeWall = true;
	}

	public boolean isCurrentTimeWallEnabled() {
		return mCurrentTimeWall;
	}

	public void setCurrentTimeWall(boolean enable) {
		mCurrentTimeWall = enable;
	}

	public void scrollImmediately(long timestamp) {
		long leftTime = mTimeline.getLeftTimestamp();
		long rightTime = mTimeline.getRightTimestamp();
		long timeRange = mTimeline.getTimeRange();
		long now = System.currentTimeMillis();

		if (rightTime + timestamp < now)
			mTimeline.setTimeRange(leftTime + timestamp, rightTime + timestamp);
		else
			mTimeline.setTimeRange(now - timeRange, now);

		mView.invalidate();
	}

	public void scrollImmediately(float scrollFactor) {
		scrollImmediately((long) (mTimeline.getTimeRange() * scrollFactor));
	}

}
