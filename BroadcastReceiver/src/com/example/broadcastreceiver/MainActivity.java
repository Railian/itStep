package com.example.broadcastreceiver;

import com.example.broadcastreceiver.MessagesService.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lvMessagesList;
	private MessagesService mMessagesService;
	private MessagesAdapter mMessagesAdapter;

	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mMessagesService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mMessagesService = ((LocalBinder) service).getService();
			mMessagesAdapter = mMessagesService.getMessagesAdapter();
			if (lvMessagesList != null)
				lvMessagesList.setAdapter(mMessagesAdapter);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent service = new Intent(this, MessagesService.class);
		setContentView(R.layout.activity_main);
		lvMessagesList = (ListView) findViewById(R.id.activity_main_lvMessagesList);
		bindService(service, mServiceConnection, 0);
	}
}
