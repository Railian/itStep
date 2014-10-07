package com.example.viewme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BuyActivity extends Activity implements OnClickListener {

	public static final String ACTION_BUY = "ACTION_BUY";
	
	public static final String EXTRA_LAPTOP = "EXTRA_LAPTOP";
	public static final String EXTRA_TITLE = "EXTRA_TITLE";
	public static final String EXTRA_PRICE = "EXTRA_PRICE";
	public static final String EXTRA_MANUFACTURER = "EXTRA_MANUFACTURER";
	public static final String EXTRA_SCREEN_SIZE = "EXTRA_SCREEN_SIZE";
	public static final String EXTRA_SCREEN_RESOLUTION = "EXTRA_SCREEN_RESOLUTION";

	private static final String EXTRA_TXN_ID = "EXTRA_TXN_ID";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);

		TextView tvTitle = (TextView) findViewById(R.id.fragment_laptop_tvTitle);
		TextView tvPrice = (TextView) findViewById(R.id.fragment_laptop_tvPrice);
		TextView tvManufacturer = (TextView) findViewById(R.id.fragment_laptop_tvManufacturer);
		TextView tvScreenSize = (TextView) findViewById(R.id.fragment_laptop_tvScreenSize);
		TextView tvScreenResolution = (TextView) findViewById(R.id.fragment_laptop_tvScreenResolution);

		tvPrice.setOnClickListener(this);

		tvTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
		tvPrice.setText(getIntent().getStringExtra(EXTRA_PRICE));
		tvManufacturer.setText(getIntent().getStringExtra(EXTRA_MANUFACTURER));
		tvScreenSize.setText(getIntent().getStringExtra(EXTRA_SCREEN_SIZE));
		tvScreenResolution.setText(getIntent().getStringExtra(
				EXTRA_SCREEN_RESOLUTION));
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra(EXTRA_TXN_ID, 132614);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}

	@Override
	public void onBackPressed() {
		setResult(Activity.RESULT_CANCELED);
		super.onBackPressed();
	}

}
