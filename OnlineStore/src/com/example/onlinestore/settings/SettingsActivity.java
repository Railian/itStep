package com.example.onlinestore.settings;

import android.app.Activity;
import android.os.Bundle;

import com.example.onlinestore.R;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.container);

		if (savedInstanceState == null)
			getFragmentManager()
					.beginTransaction()
					.add(R.id.container, new BillingFormFragment(),
							BillingFormFragment.TAG).commit();
		
	}

}
