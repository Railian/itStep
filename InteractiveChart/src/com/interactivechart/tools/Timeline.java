package com.interactivechart.tools;

public interface Timeline {

	public static final int SECOND_IN_MILLISECONDS = 1000;
	public static final int MINUTE_IN_MILLISECONDS = 60 * SECOND_IN_MILLISECONDS;
	public static final int HOUR_IN_MILLISECONDS = 60 * MINUTE_IN_MILLISECONDS;
	public static final int DAY_IN_MILLISECONDS = 24 * HOUR_IN_MILLISECONDS;

	public long getLeftTimestamp();

	public long getRightTimestamp();

	public long getTimeRange();

	public void setLeftTimestamp(long timestamp);

	public void setRightTimestamp(long timestamp);

	public void setTimeRange(long leftTimestamp, long rightTimestamp);

}
