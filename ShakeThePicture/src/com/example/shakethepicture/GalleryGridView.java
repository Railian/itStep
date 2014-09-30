package com.example.shakethepicture;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.GridView;

public class GalleryGridView extends GridView {

	private static final String EXTRA_SUPER_STATE = "EXTRA_SUPER_STATE";
	private static final String EXTRA_VIEW_MODE = "EXTRA_VIEW_MODE";
	public static final ViewMode DEFAULT_VIEW_MODE = ViewMode.MODE1;

	public static enum ViewMode {
		MODE1, MODE2
	};

	private ViewMode mViewMode;

	public void setViewMode(ViewMode mode) {
		int orientation = getResources().getConfiguration().orientation;
		mViewMode = mode;
		switch (mode) {
		case MODE1:
			if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
				setNumColumns(3);
				setNumRows(2);
			} else {
				setNumColumns(2);
				setNumRows(3);
			}
			break;
		case MODE2:
			if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
				setNumColumns(4);
				setNumRows(3);
			} else {
				setNumColumns(3);
				setNumRows(4);
			}
			break;
		}
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable(EXTRA_SUPER_STATE, super.onSaveInstanceState());
		bundle.putInt(EXTRA_VIEW_MODE, mViewMode.ordinal());
		return bundle;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		Bundle bundle = (Bundle) state;
		setViewMode(ViewMode.values()[bundle.getInt(EXTRA_VIEW_MODE,
				DEFAULT_VIEW_MODE.ordinal())]);
		super.onRestoreInstanceState(bundle.getParcelable(EXTRA_SUPER_STATE));
	}

	public GalleryGridView(Context context) {
		this(context, null);
	}

	public GalleryGridView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GalleryGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setViewMode(DEFAULT_VIEW_MODE);
	}

	private int mNumColumns;
	private int mNumRows;

	@Override
	public void setNumColumns(int numColumns) {
		if (numColumns != mNumColumns) {
			mNumColumns = numColumns;
		}
		super.setNumColumns(numColumns);
	}

	public void setNumRows(int numRows) {
		if (numRows != mNumRows) {
			mNumRows = numRows;
			GalleryImagePreview
					.setDefaultHeight((getMeasuredHeight() - getPaddingBottom()
							- getPaddingTop() - getVerticalSpacing()
							* (mNumRows - 1))
							/ mNumRows);
			invalidate();
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		GalleryImagePreview
				.setDefaultHeight(mNumRows == 0 ? 0
						: (getMeasuredHeight() - getPaddingBottom()
								- getPaddingTop() - getVerticalSpacing()
								* (mNumRows - 1))
								/ mNumRows);
	}

	public ViewMode getViewMode() {
		return mViewMode;
	}

}
