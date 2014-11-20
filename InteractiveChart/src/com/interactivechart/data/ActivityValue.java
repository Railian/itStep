package com.interactivechart.data;

public class ActivityValue {

	private float mActivity;
	private long mTimestamp;

	public ActivityValue(float activity, long timestamp) {
		mActivity = activity;
		mTimestamp = timestamp;
	}

	public float getActivity() {
		return mActivity;
	}

	public long getTimestamp() {
		return mTimestamp;
	}
}
