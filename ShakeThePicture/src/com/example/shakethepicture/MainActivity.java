package com.example.shakethepicture;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final String DEBUG_TAG = "DEBUG_TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null)
			getFragmentManager()
					.beginTransaction()
					.replace(R.id.activity_main_container,
							new GalleryFragment(), GalleryFragment.TAG)
					.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		FullscreenFragment fullscreenFragment = (FullscreenFragment) getFragmentManager()
				.findFragmentByTag(FullscreenFragment.TAG);
		if (fullscreenFragment == null)
			super.onBackPressed();
		else
			fullscreenFragment.onBackPressed();
	}
}
