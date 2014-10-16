package com.example.broadcastreceiver;

public class Message {

	private String mTitle;
	private String mMessage;
	private long mTimestamp;

	public Message(String title, String message, long timestamp) {
		mTitle = title;
		mMessage = message;
		mTimestamp = timestamp;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getMessage() {
		return mMessage;
	}

	public long getTimestamp() {
		return mTimestamp;
	}

}
