package com.example.shakethepicture;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GalleryImagePreview extends ImageView {

	protected static final int BITMAP_IS_DECODED = 0;
	private static int mDefaultHeight;

	public GalleryImagePreview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GalleryImagePreview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GalleryImagePreview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public static void setDefaultHeight(int height) {
		mDefaultHeight = height;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(width, mDefaultHeight);
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

	public void setSampleImageResource(final int resId) {
		final int reqSize = Math.max(mDefaultHeight, getMeasuredWidth());
		setImageBitmap(decodeSampledBitmapFromResource(getResources(), resId,
				reqSize / 20, reqSize / 20));
		
		Thread decodeBitmapThread = new Thread(new Runnable() {

			@Override
			public void run() {
				mHandler.obtainMessage(
						BITMAP_IS_DECODED,
						decodeSampledBitmapFromResource(getResources(), resId,
								reqSize / 3, reqSize / 3)).sendToTarget();
			}
		});
		
		decodeBitmapThread.setPriority(Thread.MIN_PRIORITY);
		decodeBitmapThread.start();
	}
	
	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			if (msg.what == BITMAP_IS_DECODED)
				setImageBitmap((Bitmap) msg.obj);
		};
	};
}
