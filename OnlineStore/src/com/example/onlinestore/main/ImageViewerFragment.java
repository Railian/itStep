package com.example.onlinestore.main;

import com.example.onlinestore.R;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ImageViewerFragment extends Fragment {

	public static final String TAG = ImageViewerFragment.class.getSimpleName();
	private static final String ARG_IMAGES_ASSET_FOLDER = "ARG_IMAGES_ASSET_FOLDER";

	public static ImageViewerFragment newInstance(String imagesAssetFolder) {
		ImageViewerFragment fragment = new ImageViewerFragment();
		Bundle args = new Bundle();
		if (imagesAssetFolder != null)
			args.putString(ARG_IMAGES_ASSET_FOLDER, imagesAssetFolder);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_image_viewer,
				container, false);

		ViewPager vpViewer = (ViewPager) rootView
				.findViewById(R.id.fragment_image_viewer_vpViewer);
		if (hasArgument(ARG_IMAGES_ASSET_FOLDER))
			vpViewer.setAdapter(new ImageViewerAdapter(getFragmentManager(),
					getActivity(), getArguments().getString(
							ARG_IMAGES_ASSET_FOLDER)));

		return rootView;
	}

	private boolean hasArgument(String key) {
		if (getArguments() == null)
			return false;
		return getArguments().containsKey(key);
	}
}
