package com.example.onlinestore.main;

import java.io.IOException;
import java.io.InputStream;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.onlinestore.R;

public class ImageViewerPageFragment extends Fragment {

	private static final String ARG_IMAGE_ASSET_PATH = "ARG_IMAGE_ASSET_PATH";

	public static ImageViewerPageFragment newInstance(String imageAssetPath) {
		ImageViewerPageFragment fragment = new ImageViewerPageFragment();
		Bundle args = new Bundle();
		if (imageAssetPath != null)
			args.putString(ARG_IMAGE_ASSET_PATH, imageAssetPath);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_image_viewer_page,
				container, false);

		ImageView ivImage = (ImageView) rootView
				.findViewById(R.id.fragment_image_viewer_page_ivImage);
		if (hasArgument(ARG_IMAGE_ASSET_PATH))
			try {
				InputStream is = getActivity().getAssets().open(
						getArguments().getString(ARG_IMAGE_ASSET_PATH));
				Bitmap bm = BitmapFactory.decodeStream(is);
				ivImage.setImageBitmap(bm);
			} catch (IOException e) {
			}

		return rootView;
	}

	private boolean hasArgument(String key) {
		if (getArguments() == null)
			return false;
		return getArguments().containsKey(key);
	}
}
