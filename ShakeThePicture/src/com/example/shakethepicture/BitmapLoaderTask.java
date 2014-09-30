package com.example.shakethepicture;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.shakethepicture.BitmapLoaderTask.Options;

class BitmapLoaderTask extends AsyncTask<Options, Void, Bitmap> {

	private final WeakReference<GalleryImagePreview> imageViewReference;

	public BitmapLoaderTask(GalleryImagePreview imagePreview) {
		imageViewReference = new WeakReference<>(imagePreview);
	}

	@Override
	protected Bitmap doInBackground(Options... params) {
//		Options options = params[0];
//		return GalleryImagePreview.de;
		return null;
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if (imageViewReference != null && bitmap != null) {
			final GalleryImagePreview imagePreview = imageViewReference.get();
			if (imagePreview != null)
				imagePreview.setImageBitmap(bitmap);
		}
	}

	public class Options {
		int resId;
		int qualityDivider;

		public Options(int resId, int qualityDivider) {
			this.resId = resId;
			this.qualityDivider = qualityDivider;
		}
	}

}