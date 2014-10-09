package com.example.onlinestore.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TabHost.TabContentFactory;

import com.example.onlinestore.R;
import com.example.onlinestore.laptop.Laptop;

public class LaptopDescriptionTab implements TabContentFactory {

	private LayoutInflater mInflater;
	private Laptop mLaptop;

	public LaptopDescriptionTab(Context context, Laptop laptop) {
		mInflater = LayoutInflater.from(context);
		mLaptop = laptop;
	}

	@Override
	public View createTabContent(String tag) {
		View rootView = mInflater.inflate(R.layout.tab_laptop_description,
				null, false);
		WebView wvHtml = (WebView) rootView
				.findViewById(R.id.tab_laptop_description_wvHtml);
		if (mLaptop != null)
			wvHtml.loadDataWithBaseURL(null, mLaptop.getHtmlDescription(),
					"text/html", "utf-8", null);
		return rootView;
	}

}
