package com.interactivechart.tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Vibrator;
import android.view.MotionEvent;

public class Button {

	private static final int VIBRATE_TIME = 10;
	private Vibrator mVibrator;

	private boolean isVibrateEnabled;
	private boolean isToggleEnabled;

	private boolean isPressed;
	private boolean isToggled;

	private Bitmap bmpNormal;
	private Bitmap bmpPressed;
	private Bitmap bmpToggleNormal;
	private Bitmap bmpTogglePressed;

	private int mWidth;
	private int mHeight;
	private int mOffsetHorizontal;
	private int mOffsetVertical;
	private AlignHorizontal mAlignHorizontal = AlignHorizontal.LEFT;
	private AlignVertical mAlignVertical = AlignVertical.TOP;

	private Rect mRect;

	private OnClickListener clickListener;

	public static enum AlignHorizontal {
		LEFT, CENTER, RIGHT
	};

	public static enum AlignVertical {
		TOP, CENTER, BOTTOM
	};

	public Button(Context context, int normalResId, int pressedResId,
			int width, int height) {
		mVibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		Resources res = context.getResources();
		bmpNormal = BitmapFactory.decodeResource(res, normalResId);
		bmpPressed = BitmapFactory.decodeResource(res, pressedResId);
		setSize(width, height);
	}

	public Button(Context context, int normalResId, int pressedResId,
			int toggleNormalResId, int togglePressedResId, int width, int height) {
		this(context, normalResId, pressedResId, width, height);
		setToggleEnabled(true);
		Resources res = context.getResources();
		bmpToggleNormal = BitmapFactory.decodeResource(res, toggleNormalResId);
		bmpTogglePressed = BitmapFactory
				.decodeResource(res, togglePressedResId);
	}

	public void setVibrateEnabled(boolean enable) {
		isVibrateEnabled = enable;
	}

	public void setToggleEnabled(boolean enable) {
		isToggleEnabled = enable;
	}

	public void setToggled(boolean toggled) {
		isToggled = toggled;
	}

	public void setWidth(int width) {
		mWidth = width;
	}

	public void setHeight(int height) {
		mHeight = height;
	}

	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public void setOffsetHorizontal(int offset) {
		mOffsetHorizontal = offset;
	}

	public void setOffsetVertical(int offset) {
		mOffsetVertical = offset;
	}

	public void setOffset(int horizontal, int vertical) {
		setOffsetHorizontal(horizontal);
		setOffsetVertical(vertical);
	}

	public void setAlign(AlignHorizontal horizontal) {
		mAlignHorizontal = horizontal;
	}

	public void setAlign(AlignVertical vertical) {
		mAlignVertical = vertical;
	}

	public void setAlign(AlignHorizontal alignHorizontal,
			AlignVertical alignVertical) {
		setAlign(alignHorizontal);
		setAlign(alignVertical);
	}

	public void setOnClickListener(OnClickListener listener) {
		clickListener = listener;
	}

	public boolean isToggled() {
		return isToggled;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (event.getX() > mRect.left && event.getX() < mRect.right
					&& event.getY() > mRect.top && event.getY() < mRect.bottom)
				isPressed = true;
			return true;
		case MotionEvent.ACTION_UP:
			if (event.getX() > mRect.left && event.getX() < mRect.right
					&& event.getY() > mRect.top && event.getY() < mRect.bottom
					&& isPressed)
				performClick();
			isPressed = false;
			return true;
		default:
			return isPressed;
		}
	}

	private void performClick() {
		if (clickListener != null) {
			if (isVibrateEnabled)
				mVibrator.vibrate(VIBRATE_TIME);
			if (isToggleEnabled)
				isToggled = !isToggled;
			clickListener.onClick(this);
		}
	}

	private int getLeft(int measuredWidth) {
		switch (mAlignHorizontal) {
		default:
		case LEFT:
			return mOffsetHorizontal;
		case CENTER:
			return measuredWidth / 2 - mWidth / 2;
		case RIGHT:
			return measuredWidth - mWidth - mOffsetHorizontal;
		}
	}

	private int getTop(int measuredHeight) {
		switch (mAlignVertical) {
		default:
		case TOP:
			return mOffsetVertical;
		case CENTER:
			return measuredHeight / 2 - mHeight / 2;
		case BOTTOM:
			return measuredHeight - mHeight - mOffsetVertical;
		}
	}

	public void measureCanvas(int measuredWidth, int measuredHeight) {
		int left = getLeft(measuredWidth);
		int top = getTop(measuredHeight);
		mRect = new Rect(left, top, left + mWidth, top + mHeight);
	}

	public void draw(Canvas canvas) {
		if (isToggled)
			canvas.drawBitmap(isPressed ? bmpTogglePressed : bmpToggleNormal,
					null, mRect, null);
		else
			canvas.drawBitmap(isPressed ? bmpPressed : bmpNormal, null, mRect,
					null);
	}

	public static interface OnClickListener {
		public void onClick(Button b);
	}

}
