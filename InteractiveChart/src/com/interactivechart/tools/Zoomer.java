package com.interactivechart.tools;

import android.view.View;

import com.interactivechart.tools.Axis.AxisType;

public class Zoomer {

	private Timeline mTimeline;
	private View mView;

	public Zoomer(Timeline timeline, View view) {
		mTimeline = timeline;
		mView = view;
	}

	public void zoomImmediately(float zoomFactor) {

		long range = mTimeline.getTimeRange();
		long leftTime = mTimeline.getLeftTimestamp();
		long rightTime = mTimeline.getRightTimestamp();
		long now = System.currentTimeMillis();

		long delta = (long) (range * (1 - zoomFactor));

		if (range + 2 * delta < 30 * Timeline.MINUTE_IN_MILLISECONDS)
			delta = (30 * Timeline.MINUTE_IN_MILLISECONDS - range) / 2;
		else if (range + 2 * delta > 14 * Timeline.DAY_IN_MILLISECONDS)
			delta = (14 * Timeline.DAY_IN_MILLISECONDS - range) / 2;

		if (rightTime + delta < now)
			mTimeline.setTimeRange(leftTime - delta, rightTime + delta);
		else
			mTimeline.setTimeRange(now - (range + 2 * delta), now);

		mTimeline.setAxisType(AxisType.determinate(
				mTimeline.getRightTimestamp(), mTimeline.getLeftTimestamp()));

		mView.invalidate();
	}

	public void softZoom(float zoomFactor) {
		zoomImmediately(zoomFactor);
	}

}
