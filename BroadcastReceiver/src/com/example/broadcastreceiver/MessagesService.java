package com.example.broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MessagesService extends Service {

	private static final String ACTION_SEND_MESSAGE = "com.example.broadcastsender.intent.action.SEND_MESSAGE";
	private static final String EXTRA_TITLE = "EXTRA_TITLE";
	private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	private static final String EXTRA_TIMESTAMP = "EXTRA_TIMESTAMP";

	MessagesAdapter mMessagesAdapter;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null)
			if (ACTION_SEND_MESSAGE.equals(intent.getAction())) {
				String title = intent.getStringExtra(EXTRA_TITLE);
				String message = intent.getStringExtra(EXTRA_MESSAGE);
				long timestamp = intent.getLongExtra(EXTRA_TIMESTAMP, -1);
				Message msg = new Message(title, message, timestamp);
				if (mMessagesAdapter == null)
					mMessagesAdapter = new MessagesAdapter(this);
				mMessagesAdapter.add(msg);
			}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public class LocalBinder extends Binder {
		public MessagesService getService() {
			return MessagesService.this;
		}
	}

	private final IBinder mBinder = new LocalBinder();

	public MessagesAdapter getMessagesAdapter() {
		return mMessagesAdapter;
	}

}
