package com.example.wanttoview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	GoodsAdapter mGoodsAdapter;

	public static final String ACTION_BUY = "com.example.viewme.intent.action.BUY";

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
		ListView rootView = new ListView(this);
		mGoodsAdapter = GoodsAdapter.getRandomAdapter(this);
		rootView.setAdapter(mGoodsAdapter);
		rootView.setOnItemClickListener(this);
		setContentView(rootView);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(ACTION_BUY);
		Good good = (Good) parent.getAdapter().getItem(position);
		intent.putExtra(EXTRA_TITLE, good.getTitle());
		intent.putExtra(EXTRA_PRICE, good.getPrice());
		intent.putExtra(EXTRA_MANUFACTURER, good.getManufacturer());
		intent.putExtra(EXTRA_SCREEN_SIZE, good.getScreenSize());
		intent.putExtra(EXTRA_SCREEN_RESOLUTION, good.getScreenResolution());
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK)
			Toast.makeText(this, "Чек покупки "+data.getStringExtra(EXTRA_TXN_ID),
					Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "Отмена",Toast.LENGTH_SHORT).show();
	}

}
