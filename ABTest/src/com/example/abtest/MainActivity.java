package com.example.abtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	String[] strings = { "222", "233", "222", "233", "222", "233", "222",
			"233", "222", "233", "222", "233", "222", "233", "222", "233" };

	private ListView lvListView; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView lvListView = (ListView) findViewById(R.id.activity_layout_lvListView);
		lvListView.setAdapter(new ArrayAdapter(this,
				R.layout.item_main_listview, strings));
		lvListView.setOnTouchListener(new TouchZoneListener());
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
