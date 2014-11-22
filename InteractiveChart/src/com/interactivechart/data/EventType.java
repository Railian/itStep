package com.interactivechart.data;

import com.interactivechart.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public enum EventType {
	FALL, ROLL_OVER;

	private static Bitmap mBitmap;

	public static void loadBitmaps(Resources res) {
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
	}

	public Bitmap getBitmap() {
		return mBitmap;
	}
};