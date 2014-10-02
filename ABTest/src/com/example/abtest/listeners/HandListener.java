package com.example.abtest.listeners;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.NumberPicker.OnValueChangeListener;

public class HandListener implements OnTouchListener {

	private static final String TAG = HandListener.class.getSimpleName();

	public static enum RenderMode {
		CLASSIC, SCROLLABLE
	}

	public static enum Hand {
		LEFT, RIGHT, INDEFINITE
	}

	public static enum Zone {
		LEFT_TOP, CENTER_TOP, RIGHT_TOP, LEFT_CENTER, CENTER_CENTER, RIGHT_CENTER, LEFT_BOTTOM, CENTER_BOTTOM, RIGHT_BOTTOM
	};

	public static enum Location {
		LEFT_SIDE, MIDLE, RIGHT_SIDE
	}

	private RenderMode mRenderMode;
	private OnHandScoreListener mListener;

	public HandListener(RenderMode mode, OnHandScoreListener listener) {
		mRenderMode = mode == null ? RenderMode.CLASSIC : mode;
		mListener = listener;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Location viewLocation = determineViewLocation(v);
			Zone touchZone = determineTouchZone(v, event);
			// Log.d(TAG, "viewLocation is " + viewLocation);
			// Log.d(TAG, "touchZone is " + touchZone);
			Hand hand = witchHand(viewLocation, touchZone);
			Log.d(TAG, String.format("You touch with %s hand", hand));
			if (mListener != null)
				mListener.onHandScoreChange(hand);
		}
		return false;
	}

	private Hand witchHand(Location viewLocation, Zone touchZone) {
		switch (mRenderMode) {

		case CLASSIC:
			switch (viewLocation) {
			case LEFT_SIDE:
			case RIGHT_SIDE:
				switch (touchZone) {
				case LEFT_BOTTOM:
				case LEFT_CENTER:
				case LEFT_TOP:
					return Hand.LEFT;
				case RIGHT_BOTTOM:
				case RIGHT_CENTER:
				case RIGHT_TOP:
					return Hand.RIGHT;
				default:
					return Hand.INDEFINITE;
				}
			default:
				return Hand.INDEFINITE;
			}

		case SCROLLABLE:
			switch (touchZone) {
			case LEFT_BOTTOM:
			case LEFT_CENTER:
			case LEFT_TOP:
				return Hand.LEFT;
			case RIGHT_BOTTOM:
			case RIGHT_CENTER:
			case RIGHT_TOP:
				return Hand.RIGHT;
			default:
				return Hand.INDEFINITE;
			}

		default:
			return Hand.INDEFINITE;
		}
	}

	private Zone determineTouchZone(View v, MotionEvent event) {

		float relativeX = event.getX() / v.getWidth();
		float relativeY = event.getY() / v.getHeight();

		StringBuilder zoneBuilder = new StringBuilder();
		zoneBuilder.append(relativeX < 1 / 3f ? "LEFT"
				: relativeX > 2 / 3f ? "RIGHT" : "CENTER");
		zoneBuilder.append("_");
		zoneBuilder.append(relativeY < 1 / 3f ? "TOP"
				: relativeY > 2 / 3f ? "BOTTOM" : "CENTER");

		return Zone.valueOf(zoneBuilder.toString());
	}

	private Location determineViewLocation(View v) {

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

	public static interface OnHandScoreListener {
		public void onHandScoreChange(Hand hand);
	}
}
