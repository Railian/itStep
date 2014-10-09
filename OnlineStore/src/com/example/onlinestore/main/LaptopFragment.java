package com.example.onlinestore.main;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.example.onlinestore.R;
import com.example.onlinestore.laptop.Laptop;

public class LaptopFragment extends Fragment implements OnTabChangeListener {

	public static final String TAG = LaptopFragment.class.getSimpleName();

	private static final String ARGS_LAPTOP = "ARGS_LAPTOP";

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

		TextView tvTitle = (TextView) rootView
				.findViewById(R.id.fragment_laptop_tvTitle);
		TextView tvPrice = (TextView) rootView
				.findViewById(R.id.fragment_laptop_tvPrice);
		TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);

		Laptop laptop = getArguments().getParcelable(ARGS_LAPTOP);

		tvTitle.setText(laptop.getTitle());
		tvPrice.setText(String.format("%d грн", laptop.getPrice()));

		tabHost.setup();
		TabHost.TabSpec tabSpec;

		tabSpec = tabHost.newTabSpec("tag1");
		tabSpec.setIndicator("Характеристики");
		tabSpec.setContent(new LaptopSpecificationsTab(getActivity(), laptop));
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tag2");
		tabSpec.setIndicator("Описание");
		tabSpec.setContent(new LaptopDescriptionTab(getActivity(), laptop));
		tabHost.addTab(tabSpec);

		tabHost.setOnTabChangedListener(this);
		return rootView;
	}

	@Override
	public void onTabChanged(String tabId) {
		
	}

}
