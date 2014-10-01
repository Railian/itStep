package com.example.abtest.listeners;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

public class WitchHandListener implements OnTouchListener {

	private static final String TAG = WitchHandListener.class.getSimpleName();

	public static enum Zone {
		TOP_LEFT, TOP_CENTER, TOP_RIGHT, CENTER_LEFT, CENTER_CENTER, CENTER_RIGHT, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT
	};

	public static enum Location {
		LEFT_SIDE, MIDLE, RIGHT_SIDE
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Location viewLocation = determineGlobalLocation(v);
		Zone touchZone = determineTouchZone(v, event);
		Log.d(TAG, "viewLocation is " + viewLocation);
		Log.d(TAG, "touchZone is " + touchZone);
		return false;
	}

	private Zone determineTouchZone(View v, MotionEvent event) {

		float relativeX = event.getX() / v.getWidth();
		float relativeY = event.getY() / v.getHeight();
		Log.d(TAG, "relativeX = " + relativeX);
		Log.d(TAG, "relativeY = " + relativeY);
		
		StringBuilder zoneBuilder = new StringBuilder();
		zoneBuilder.append(relativeY < 1 / 3f ? "TOP"
				: relativeY > 2 / 3f ? "BOTTOM" : "CENTER");
		zoneBuilder.append("_");
		zoneBuilder.append(relativeX < 1 / 3f ? "LEFT"
				: relativeX > 2 / 3f ? "RIGHT" : "CENTER");

		return Zone.valueOf(zoneBuilder.toString());
	}

	private Location determineGlobalLocation(View v) {

		WindowManager windowManager = (WindowManager) v.getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);

		int screenWidth = displayMetrics.widthPixels;
		int viewWidth = v.getWidth();
		int[] location = new int[2];
		v.getLocationInWindow(location);
		float viewHorizontalCenter = location[0] + viewWidth / 2f;

		if (viewHorizontalCenter / screenWidth < 0.4f)
			return Location.LEFT_SIDE;
		else if (viewHorizontalCenter / screenWidth > 0.6f)
			return Location.RIGHT_SIDE;
		else
			return Location.MIDLE;
	}
}
