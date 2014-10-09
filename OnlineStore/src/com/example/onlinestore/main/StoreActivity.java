package com.example.onlinestore.main;

import android.app.Activity;
import android.os.Bundle;

import com.example.onlinestore.R;

public class StoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.container);
		if (savedInstanceState == null)
			getFragmentManager().beginTransaction()
					.add(R.id.container,new LaptopsListFragment(), LaptopsListFragment.TAG)
					.commit();
	}
}
