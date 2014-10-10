package com.example.wanttoview;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GoodsAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Good> mGoods = new ArrayList<Good>();

	public GoodsAdapter(Context context) {
		this(context, new ArrayList<Good>());
	}

	public GoodsAdapter(Context context, ArrayList<Good> goods) {
		mInflater = LayoutInflater.from(context);
		mGoods = goods;
	}

	public static GoodsAdapter getRandomAdapter(Context context) {
		ArrayList<Good> goods = new ArrayList<Good>();
		Good good;
		good = new Good();
		good.setTitle("Intel Celeron");
		good.setPrice("4322 uah");
		good.setManufacturer("Acer");
		good.setScreenSize("15.6\"");
		goods.add(good);
		good = new Good();
		good.setTitle("Intel Pentium");
		good.setPrice("5045 uah");
		good.setManufacturer("Apple");
		good.setScreenSize("15.6\"");
		goods.add(good);
		good = new Good();
		good.setTitle("Intel Core i7");
		good.setPrice("11086 uah");
		good.setManufacturer("Asus");
		good.setScreenSize("16\"");
		goods.add(good);
		good = new Good();
		good.setTitle("Intel Core i5");
		good.setPrice("7989 uah");
		good.setManufacturer("Lenovo");
		good.setScreenSize("17\"");
		goods.add(good);
		good = new Good();
		good.setTitle("AMD A2");
		good.setPrice("4567 uah");
		good.setManufacturer("HP");
		good.setScreenSize("13\"");
		goods.add(good);
		return new GoodsAdapter(context, goods);
	}

	@Override
	public int getCount() {
		return mGoods.size();
	}

	@Override
	public Good getItem(int position) {
		return mGoods.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = mInflater.inflate(R.layout.item_good, parent, false);
		TextView tvTitle = (TextView) convertView
				.findViewById(R.id.item_good_tvTitle);
		tvTitle.setText(getItem(position).getTitle());
		return convertView;
	}
}
