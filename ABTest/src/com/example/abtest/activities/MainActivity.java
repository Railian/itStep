package com.example.abtest.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.abtest.R;
import com.example.abtest.listeners.WitchHandListener;
import com.example.abtest.views.ABButton;
import com.example.abtest.views.ABListView;

public class MainActivity extends Activity {

	private static String[] strings = { "222", "233", "222", "233", "222", "233", "222",
			"233", "222", "233", "222", "233", "222", "233", "222", "233" };

	private ABListView lvListView;
	private ABButton btBack;
	private ABButton btSupport;
	private ABButton btPay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lvListView = (ABListView) findViewById(R.id.activity_main_lvListView);
		btBack = (ABButton) findViewById(R.id.activity_main_btBack);
		btSupport = (ABButton) findViewById(R.id.activity_main_btSupport);
		btPay = (ABButton) findViewById(R.id.activity_main_btPay);

		lvListView.setOnTouchListener(new WitchHandListener());
		btBack.setOnTouchListener(new WitchHandListener());
		btSupport.setOnTouchListener(new WitchHandListener());
		btPay.setOnTouchListener(new WitchHandListener());
		
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
}
