package com.example.abtest.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abtest.R;
import com.example.abtest.listeners.HandListener;
import com.example.abtest.listeners.HandListener.Hand;
import com.example.abtest.listeners.HandListener.OnHandScoreListener;
import com.example.abtest.listeners.HandListener.RenderMode;
import com.example.abtest.views.ABButton;
import com.example.abtest.views.ABListView;

public class MainActivity extends Activity implements OnHandScoreListener, OnClickListener {

	private static String[] strings = { "222", "233", "222", "233", "222",
			"233", "222", "233", "222", "233", "222", "233", "222", "233",
			"222", "233" };


	private TextView tvLeftCounter;
	private TextView tvRightCounter;
	private ABListView lvListView;
	private ABButton btBack;
	private ABButton btReset;
	private ABButton btPay;

	private int mLeftCounter;
	private int mRightCounter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvLeftCounter = (TextView) findViewById(R.id.activity_main_tvLeftCounter);
		tvRightCounter = (TextView) findViewById(R.id.activity_main_tvRightCounter);
		lvListView = (ABListView) findViewById(R.id.activity_main_lvListView);
		btBack = (ABButton) findViewById(R.id.activity_main_btBack);
		btReset = (ABButton) findViewById(R.id.activity_main_btReset);
		btPay = (ABButton) findViewById(R.id.activity_main_btPay);

		lvListView.setOnTouchListener(new HandListener(RenderMode.SCROLLABLE,
				 this));
		btBack.setOnTouchListener(new HandListener(RenderMode.CLASSIC,
				 this));
		btReset.setOnTouchListener(new HandListener(RenderMode.CLASSIC,
				 this));
		btPay.setOnTouchListener(new HandListener(RenderMode.CLASSIC,
				this));
		btReset.setOnClickListener(this);
		
		lvListView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.item_main_listview, strings));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onHandScoreChange(Hand hand) {
		switch (hand) {
		case LEFT:
			mLeftCounter++;
			break;
		case RIGHT:
			mRightCounter++;
			break;
		default:
			return;
		}
		tvLeftCounter.setText(String.format("%d (Left)", mLeftCounter));
		tvRightCounter.setText(String.format("(Right) %d", mRightCounter));
	}

	@Override
	public void onClick(View v) {
		mLeftCounter = mRightCounter = 0;
		tvLeftCounter.setText(String.format("%d (Left)", mLeftCounter));
		tvRightCounter.setText(String.format("(Right) %d", mRightCounter));
	}

}
