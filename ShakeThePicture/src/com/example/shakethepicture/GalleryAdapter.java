package com.example.shakethepicture;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView.ScaleType;

public class GalleryAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Integer> mImageIdList;

	public GalleryAdapter(Context context) {
		this(context, new ArrayList<Integer>());
	}

	public GalleryAdapter(Context context, ArrayList<Integer> imageIdList) {
		mContext = context;
		mImageIdList = imageIdList;
	}

	public static GalleryAdapter getDemoAdapter(Context context) {
		ArrayList<Integer> imageList = new ArrayList<>();
		imageList.add(R.drawable.wallpaper001);
		imageList.add(R.drawable.wallpaper002);
		imageList.add(R.drawable.wallpaper003);
		imageList.add(R.drawable.wallpaper004);
		imageList.add(R.drawable.wallpaper005);
		imageList.add(R.drawable.wallpaper006);
		imageList.add(R.drawable.wallpaper007);
		imageList.add(R.drawable.wallpaper008);
		imageList.add(R.drawable.wallpaper009);
		imageList.add(R.drawable.wallpaper010);
		imageList.add(R.drawable.wallpaper011);
		imageList.add(R.drawable.wallpaper012);
		imageList.add(R.drawable.wallpaper013);
		imageList.add(R.drawable.wallpaper014);
		imageList.add(R.drawable.wallpaper015);
		imageList.add(R.drawable.wallpaper016);
		imageList.add(R.drawable.wallpaper017);
		imageList.add(R.drawable.wallpaper018);
		imageList.add(R.drawable.wallpaper019);
		imageList.add(R.drawable.wallpaper020);
		imageList.add(R.drawable.wallpaper021);
		imageList.add(R.drawable.wallpaper022);
		imageList.add(R.drawable.wallpaper023);
		imageList.add(R.drawable.wallpaper024);
//		imageList.add(R.drawable.wallpaper025);
		imageList.add(R.drawable.wallpaper026);
		imageList.add(R.drawable.wallpaper027);
		imageList.add(R.drawable.wallpaper028);
		imageList.add(R.drawable.wallpaper029);
		imageList.add(R.drawable.wallpaper030);
		return new GalleryAdapter(context, imageList);
	}

	@Override
	public int getCount() {
		return mImageIdList.size();
	}

	@Override
	public Integer getItem(int position) {
		return mImageIdList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final GalleryImagePreview preview;
		if (convertView == null) {
			preview = new GalleryImagePreview(mContext);
			preview.setScaleType(ScaleType.CENTER_CROP);
		} else
			preview = (GalleryImagePreview) convertView;

		preview.setSampleImageResource(getItem(position));

		return preview;
	}

}
