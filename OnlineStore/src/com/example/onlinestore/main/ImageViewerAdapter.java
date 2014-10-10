package com.example.onlinestore.main;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;

public class ImageViewerAdapter extends FragmentStatePagerAdapter {

	private ArrayList<String> mImagesPath;

	public ImageViewerAdapter(FragmentManager fm, Context context,
			String imagesAssetPath) {
		super(fm);
		mImagesPath = new ArrayList<>();
		try {
			String[] list = context.getAssets().list(imagesAssetPath);
			for (String string : list)
				if (!string.toLowerCase().contains("icon"))
					mImagesPath.add(imagesAssetPath + '/' + string);
		} catch (IOException e) {
		}
	}

	@Override
	public int getCount() {
		return mImagesPath.size();
	}

	@Override
	public Fragment getItem(int position) {
		return ImageViewerPageFragment.newInstance(mImagesPath.get(position));
	}

}
