package com.interactivechart.data;

public class EventValue {

	private EventType mEvent;
	private long mTimestamp;

	public EventValue(EventType event, long timestamp) {
		mEvent = event;
		mTimestamp = timestamp;
	}

	public EventType getEvent() {
		return mEvent;
	}

	public long getTimestamp() {
		return mTimestamp;
	}

}
