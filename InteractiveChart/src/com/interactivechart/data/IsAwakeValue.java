package com.interactivechart.data;

public class IsAwakeValue {

	private boolean mIsAwake;
	private long mTimestamp;

	public IsAwakeValue(boolean isAwake, long timestamp) {

		mIsAwake = isAwake;
		mTimestamp = timestamp;
	}

	public boolean isAwake() {
		return mIsAwake;
	}

	public long getTimestamp() {
		return mTimestamp;
	}
}
