package com.interactivechart.listeners;

import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

import com.interactivechart.tools.Zoomer;

public class ScaleListener implements OnScaleGestureListener {

	private Zoomer mZoomer;
	private boolean isScaling;

	public ScaleListener(Zoomer zoomer) {
		mZoomer = zoomer;
	}

	public boolean isScaling() {
		return isScaling;
	}

	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		mZoomer.zoomImmediately(detector.getScaleFactor());
		return isScaling;
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		return isScaling = true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {
		isScaling = false;
	}

}
