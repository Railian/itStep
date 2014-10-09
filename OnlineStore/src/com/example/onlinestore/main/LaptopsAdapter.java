package com.example.onlinestore.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.onlinestore.R;
import com.example.onlinestore.laptop.Laptop;
import com.example.onlinestore.laptop.Laptops;

public class LaptopsAdapter extends BaseAdapter {

	private Laptops mAllLaptops;
	private Laptops mFilteredLaptops;
	private Context mContext;
	private LayoutInflater mInflater;

	public LaptopsAdapter(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mFilteredLaptops = mAllLaptops = Laptops.parseFromXml(context,
				R.xml.laptops);
	}

	@Override
	public int getCount() {
		return mFilteredLaptops.size();
	}

	@Override
	public Laptop getItem(int position) {
		return mFilteredLaptops.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = mInflater
					.inflate(R.layout.item_laptop, parent, false);
		TextView tvTitle = (TextView) convertView
				.findViewById(R.id.item_laptop_tvTitle);
		TextView tvPrice = (TextView) convertView
				.findViewById(R.id.item_laptop_tvPrice);
		
		tvTitle.setText(getItem(position).getTitle());
		tvPrice.setText(getItem(position).getPrice() + "");

		return convertView;
	}

}
