package com.example.jira;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public static class PlaceholderFragment extends Fragment implements
			OnTouchListener {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_person,
					container, false);
			rootView.findViewById(R.id.btn_clock).setOnTouchListener(this);
			rootView.findViewById(R.id.btn_tracker).setOnTouchListener(this);
			rootView.findViewById(R.id.btn_support).setOnTouchListener(this);
			rootView.findViewById(R.id.btn_logout).setOnTouchListener(this);
			return rootView;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				((ImageView) v).setColorFilter(getResources().getColor(
						R.color.blue));
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_HOVER_EXIT:
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_OUTSIDE:
				((ImageView) v).setColorFilter(getResources().getColor(
						R.color.blue_light_background));
				break;
			}
			return false;
		}
	}

}
