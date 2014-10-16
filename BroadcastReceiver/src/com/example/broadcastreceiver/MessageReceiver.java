package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MessageReceiver extends BroadcastReceiver {

	private static final String ACTION_SEND_MESSAGE = "com.example.broadcastsender.intent.action.SEND_MESSAGE";
	private static final String EXTRA_TITLE = "EXTRA_TITLE";
	private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	private static final String EXTRA_TIMESTAMP = "EXTRA_TIMESTAMP";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION_SEND_MESSAGE)) {
			Intent service = new Intent(context, MessagesService.class);
			service.setAction(ACTION_SEND_MESSAGE);
			service.putExtra(EXTRA_TITLE, intent.getStringExtra(EXTRA_TITLE));
			service.putExtra(EXTRA_MESSAGE,
					intent.getStringExtra(EXTRA_MESSAGE));
			service.putExtra(EXTRA_TIMESTAMP,
					intent.getLongExtra(EXTRA_TIMESTAMP, -1));
			context.startService(service);
		}
	}
}
