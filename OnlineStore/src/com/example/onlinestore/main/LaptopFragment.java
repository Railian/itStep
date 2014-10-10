package com.example.onlinestore.main;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.InputSource;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.example.onlinestore.R;
import com.example.onlinestore.laptop.Laptop;

public class LaptopFragment extends Fragment implements OnTabChangeListener,
		OnClickListener {

	public static final String TAG = LaptopFragment.class.getSimpleName();

	private static final String ARGS_LAPTOP = "ARGS_LAPTOP";
	private Laptop mLaptop;

	public static LaptopFragment newInstance(Laptop laptop) {
		LaptopFragment fragment = new LaptopFragment();
		Bundle args = new Bundle();
		if (laptop != null)
			args.putParcelable(ARGS_LAPTOP, laptop);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_laptop, container,
				false);

		ImageView ivIcon = (ImageView) rootView
				.findViewById(R.id.fragment_laptop_ivIcon);
		TextView tvTitle = (TextView) rootView
				.findViewById(R.id.fragment_laptop_tvTitle);
		TextView tvPrice = (TextView) rootView
				.findViewById(R.id.fragment_laptop_tvPrice);
		TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);

		mLaptop = getArguments().getParcelable(ARGS_LAPTOP);

		try {
			// String[] list = getActivity().getAssets().list(
			// laptop.getImagesAssetPath());
			// Toast.makeText(getActivity(),
			// String.format("The number of images is %d", list.length),
			// Toast.LENGTH_SHORT).show();
			// for (String imageName : list)
			// Toast.makeText(getActivity(), imageName, Toast.LENGTH_SHORT)
			// .show();
			InputStream is = getActivity().getAssets().open(
					mLaptop.getImagesAssetPath() + "/icon.jpg");
			Bitmap bm = BitmapFactory.decodeStream(is);
			ivIcon.setImageBitmap(bm);
		} catch (IOException e) {
		}
		ivIcon.setOnClickListener(this);

		tvTitle.setText(mLaptop.getTitle());
		tvPrice.setText(String.format("%d грн", mLaptop.getPrice()));

		tabHost.setup();
		TabHost.TabSpec tabSpec;

		tabSpec = tabHost.newTabSpec("tag1");
		tabSpec.setIndicator("Характеристики");
		tabSpec.setContent(new LaptopSpecificationsTab(getActivity(), mLaptop));
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag2");
		tabSpec.setIndicator("Описание");
		tabSpec.setContent(new LaptopDescriptionTab(getActivity(), mLaptop));
		tabHost.addTab(tabSpec);

		tabHost.setOnTabChangedListener(this);
		return rootView;
	}

	@Override
	public void onTabChanged(String tabId) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_laptop_ivIcon:
			getFragmentManager()
					.beginTransaction()
					.replace(
							R.id.container,
							ImageViewerFragment.newInstance(mLaptop
									.getImagesAssetPath()),
							ImageViewerFragment.TAG).addToBackStack(null)
					.commit();
			break;

		default:
			break;
		}
	}

}
