package com.example.onlinestore.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.example.onlinestore.R;
import com.example.onlinestore.laptop.Laptop;

public class LaptopSpecificationsTab implements TabContentFactory {

	private Context mContext;
	private LayoutInflater mInflater;
	private Laptop mLaptop;

	public LaptopSpecificationsTab(Context context, Laptop laptop) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLaptop = laptop;
	}

	@Override
	public View createTabContent(String tag) {
		View rootView = mInflater.inflate(R.layout.tab_laptop_specifications,
				null, false);
		TextView tvManufacturer = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvManufacturer);
		TextView tvScreenSize = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvScreenSize);
		TextView tvScreenResolution = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvScreenResolution);
		TextView tvScreenType = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvScreenType);
		TextView tvScreenCoating = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvScreenCoating);
		TextView tvTouchScreen = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvTouchScreen);
		TextView tvCpu = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvCpu);
		TextView tvRam = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvRam);
		TextView tvVideoCard = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvVideoCard);
		TextView tvVideoCardRam = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvVideoCardRam);
		TextView tvTypeOfDrive = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvTypeOfDrive);
		TextView tvCapacityOfDrive = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvCapacityOfDrive);
		TextView tvOpticalDrive = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvOpticalDrive);
		TextView tvOperationSystem = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvOperationSystem);
		TextView tvWeight = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvWeight);
		TextView tvColor = (TextView) rootView
				.findViewById(R.id.tab_laptop_specifications_tvColor);

		tvManufacturer.setText(mLaptop.getManufacturer() == null ? "---"
				: mLaptop.getManufacturer().toString(mContext));
		tvScreenSize.setText(mLaptop.getScreenSize() == null ? "---" : mLaptop
				.getScreenSize().toString(mContext));
		tvScreenResolution
				.setText(mLaptop.getScreenResolution() == null ? "---"
						: mLaptop.getScreenResolution().toString(mContext));
		tvScreenType.setText(mLaptop.getScreenType() == null ? "---" : mLaptop
				.getScreenType().toString());
		tvScreenCoating.setText(mLaptop.getScreenCoating() == null ? "---"
				: mLaptop.getScreenCoating().toString());
		tvTouchScreen.setText(mLaptop.getTouchScreen() == null ? "---"
				: mLaptop.getTouchScreen().toString());

		tvCpu.setText(mLaptop.getCpu() == null ? "---"
				: mLaptop.getCpu().toString());
		tvRam.setText(mLaptop.getRam() == null ? "---"
				: mLaptop.getRam().toString());
		tvVideoCard.setText(mLaptop.getVideoCard() == null ? "---"
				: mLaptop.getVideoCard().toString());
		tvVideoCardRam.setText(mLaptop.getVideoCardRam() == null ? "---"
				: mLaptop.getVideoCardRam().toString());
		tvTypeOfDrive.setText(mLaptop.getTypeOfDrive() == null ? "---"
				: mLaptop.getTypeOfDrive().toString());
		tvCapacityOfDrive.setText(mLaptop.getCapacityOfDrive() == null ? "---"
				: mLaptop.getCapacityOfDrive().toString());
		tvOpticalDrive.setText(mLaptop.getOpticalDrive() == null ? "---"
				: mLaptop.getOpticalDrive().toString());
		tvOperationSystem.setText(mLaptop.getOperationSystem() == null ? "---"
				: mLaptop.getOperationSystem().toString());
		tvWeight.setText(mLaptop.getWeight() == null ? "---"
				: mLaptop.getWeight().toString());
		tvColor.setText(mLaptop.getColor() == null ? "---"
				: mLaptop.getColor().toString());
		return rootView;
	}

}
