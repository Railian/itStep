package com.example.onlinestore.main;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.onlinestore.R;
import com.example.onlinestore.laptop.Laptop;

public class LaptopFragment extends Fragment {

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
		TextView tvManufacturer = (TextView) rootView
				.findViewById(R.id.fragment_laptop_tvManufacturer);
		TextView tvScreenSize = (TextView) rootView
				.findViewById(R.id.fragment_laptop_tvScreenSize);
		TextView tvScreenResolution = (TextView) rootView
				.findViewById(R.id.fragment_laptop_tvScreenResolution);
		WebView wvHtmlDescription = (WebView) rootView
				.findViewById(R.id.fragment_laptop_wvHtmlDescription);

		Laptop laptop = getArguments().getParcelable(ARGS_LAPTOP);
		tvTitle.setText(laptop.getTitle());
		tvPrice.setText(laptop.getPrice() + "");
		tvManufacturer
				.setText(laptop.getManufacturer().toString(getActivity()));
		tvScreenSize.setText(laptop.getScreenSize().toString(getActivity()));
		tvScreenResolution.setText(laptop.getScreenResolution().toString(
				getActivity()));
		wvHtmlDescription.loadDataWithBaseURL(null,
				laptop.getHtmlDescription(), "text/html", "utf-8", null);

		return rootView;
	}

}
