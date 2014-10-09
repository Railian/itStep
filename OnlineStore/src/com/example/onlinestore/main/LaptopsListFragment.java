package com.example.onlinestore.main;

import com.example.onlinestore.R;
import com.example.onlinestore.laptop.Laptop;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LaptopsListFragment extends Fragment implements
		OnItemClickListener {

	public static final String TAG = LaptopsListFragment.class.getSimpleName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ListView rootView = new ListView(getActivity());
		rootView.setAdapter(new LaptopsAdapter(getActivity()));
		rootView.setOnItemClickListener(this);
		return rootView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		getFragmentManager()
				.beginTransaction()
				.replace(
						R.id.container,
						LaptopFragment.newInstance((Laptop) parent.getAdapter()
								.getItem(position)), LaptopFragment.TAG).addToBackStack(LaptopFragment.TAG)
				.commit();
	}

}
