package com.example.abtest;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchZoneListener implements OnTouchListener {

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int width = v.getWidth();
		int height = v.getHeight();
		event.getX();
		event.getY();
		return false; 
	}

}
