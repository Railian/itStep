package com.interactivechart.tools;

public class Scroller {

	private Timeline mTimeline;
	private boolean mCurrentTimeWall;

	public Scroller(Timeline timeline) {
		mTimeline = timeline;
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
	}

	public void scrollImmediately(float scrollFactor) {
		scrollImmediately((long) (mTimeline.getTimeRange() * scrollFactor));
	}

	public void scrollToLeftTimestamp(long timestamp) {
		mTimeline.setTimeRange(timestamp, timestamp - mTimeline.getTimeRange());
	}

	public void scrollToRightTimestamp(long timestamp) {
		mTimeline.setTimeRange(timestamp - mTimeline.getTimeRange(), timestamp);
	}

}
