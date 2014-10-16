package com.example.broadcastsender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final String ACTION_SEND_MESSAGE = "com.example.broadcastsender.intent.action.SEND_MESSAGE";
	private static final String EXTRA_TITLE = "EXTRA_TITLE";
	private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	private static final String EXTRA_TIMESTAMP = "EXTRA_TIMESTAMP";

	private EditText etTitle;
	private EditText etMessage;
	private Button btSend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etTitle = (EditText) findViewById(R.id.activity_main_etTitle);
		etMessage = (EditText) findViewById(R.id.activity_main_etMessage);
		btSend = (Button) findViewById(R.id.activity_main_btSend);
		btSend.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String title = etTitle.getText().toString();
		String message = etMessage.getText().toString();
		long timestamp = System.currentTimeMillis();

		if (title.isEmpty() & message.isEmpty())
			Toast.makeText(this, "You have to fill title or message",
					Toast.LENGTH_LONG).show();
		else {
			Intent intent = new Intent(ACTION_SEND_MESSAGE);
			intent.putExtra(EXTRA_TITLE, title);
			intent.putExtra(EXTRA_MESSAGE, message);
			intent.putExtra(EXTRA_TIMESTAMP, timestamp);
			etTitle.setText("");
			etMessage.setText("");
			sendBroadcast(intent);
			Toast.makeText(this, "The message was sent", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
