package com.example.shakethepicture;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class GalleryImageFullview extends ImageView implements
		AnimationListener {

	public static interface OnShowListener {
		public void onOpening();

		public void onOpened();

		public void onClosing();

		public void onClosed();
	}

	public GalleryImageFullview(Context context) {
		this(context, null);
	}

	public GalleryImageFullview(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GalleryImageFullview(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setSampledImageResource(int resId) {
		final int reqSize = 400;
		setImageBitmap(decodeSampledBitmapFromResource(getResources(), resId,
				reqSize, reqSize));
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	private static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	private int mPreviewLeft;
	private int mPreviewTop;
	private int mPreviewWidth;
	private int mPreviewHeight;

	private int mFullviewLeft;
	private int mFullviewTop;
	private int mFullviewWidth;
	private int mFullviewHeight;

	private OpenAnimation mOpenAnimation;
	private CloseAnimation mCloseAnimation;

	private boolean isAnimationBegan;

	private OnShowListener listener;

	public void setOnShowListener(OnShowListener l) {
		listener = l;
	}

	public void startOpenAnimation(int leftFrom, int topFrom, int widthFrom,
			int heightFrom, long durationMillis) {
		mPreviewLeft = leftFrom;
		mPreviewTop = topFrom;
		mPreviewWidth = widthFrom;
		mPreviewHeight = heightFrom;
		mOpenAnimation = new OpenAnimation();
		mOpenAnimation.setDuration(durationMillis);
		setScaleType(ScaleType.CENTER_CROP);
	}

	public void startCloseAnimation(long durationMillis) {
		mCloseAnimation = new CloseAnimation();
		mCloseAnimation.setDuration(durationMillis);
		mCloseAnimation.setAnimationListener(this);
		startAnimation(mCloseAnimation);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (isAnimationBegan)
			super.onDraw(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		if (isAnimationBegan)
			super.onLayout(changed, left, top, right, bottom);
		else {
			View parent = (View) getParent();
			mFullviewLeft = parent.getLeft() + left;
			mFullviewTop = parent.getTop() + top;
			mFullviewWidth = right - left;
			mFullviewHeight = bottom - top;
			isAnimationBegan = true;
			startAnimation(mOpenAnimation);
		}
	}

	private class OpenAnimation extends Animation {

		public OpenAnimation() {
			setAlpha(0f);
			setInterpolator(getContext(),
					android.R.interpolator.accelerate_decelerate);
		}

		int drawingCount;

		@Override
		protected void applyTransformation(float interpolatedTime,
				Transformation t) {
			if (drawingCount++ == 2) {
				setAlpha(1f);
				listener.onOpening();
			}
			int newLeft = mPreviewLeft
					+ (int) ((mFullviewLeft - mPreviewLeft) * interpolatedTime);
			int newTop = mPreviewTop
					+ (int) ((mFullviewTop - mPreviewTop) * interpolatedTime);
			int newWidth = mPreviewWidth
					+ (int) ((mFullviewWidth - mPreviewWidth) * interpolatedTime);
			int newHeight = mPreviewHeight
					+ (int) ((mFullviewHeight - mPreviewHeight) * interpolatedTime);
			View parent = (View) getParent();
			parent.setX(newLeft - getLeft());
			parent.setY(newTop - getTop());
			getLayoutParams().width = newWidth;
			getLayoutParams().height = newHeight;
			requestLayout();

		}
	}

	private class CloseAnimation extends Animation {

		public CloseAnimation() {
			setInterpolator(getContext(),
					android.R.interpolator.accelerate_decelerate);
		}

		@Override
		protected void applyTransformation(float interpolatedTime,
				Transformation t) {
			int newLeft = mFullviewLeft
					+ (int) ((mPreviewLeft - mFullviewLeft) * interpolatedTime);
			int newTop = mFullviewTop
					+ (int) ((mPreviewTop - mFullviewTop) * interpolatedTime);
			int newWidth = mFullviewWidth
					+ (int) ((mPreviewWidth - mFullviewWidth) * interpolatedTime);
			int newHeight = mFullviewHeight
					+ (int) ((mPreviewHeight - mFullviewHeight) * interpolatedTime);
			View parent = (View) getParent();
			parent.setX(newLeft - getLeft());
			parent.setY(newTop - getTop());
			getLayoutParams().width = newWidth;
			getLayoutParams().height = newHeight;
			requestLayout();
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		if (animation == mCloseAnimation)
			listener.onClosing();
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == mOpenAnimation)
			listener.onOpened();
		else if (animation == mCloseAnimation)
			listener.onClosed();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

}
