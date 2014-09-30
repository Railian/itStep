package com.example.shakethepicture;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.shakethepicture.GalleryGridView.ViewMode;
import com.example.shakethepicture.GalleryImageFullview.OnShowListener;

public class GalleryFragment extends Fragment implements OnItemClickListener,
		OnShowListener {

	public static final String TAG = GalleryFragment.class.getSimpleName();
	private GalleryGridView ggvGaleryGridView;
	private View rootView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater
				.inflate(R.layout.fragment_gallery, container, false);

		ggvGaleryGridView = (GalleryGridView) rootView
				.findViewById(R.id.fragment_gallery_ggvGaleryGridView);
		ggvGaleryGridView.setAdapter(GalleryAdapter
				.getDemoAdapter(getActivity()));
		ggvGaleryGridView.setOnItemClickListener(this);

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.gallery, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_scale1:
			ggvGaleryGridView.setViewMode(ViewMode.MODE1);
			return true;
		case R.id.action_scale2:
			ggvGaleryGridView.setViewMode(ViewMode.MODE2);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private int getRelativeLeft(View view, View rootView) {
		if (view.getParent() == rootView)
			return view.getLeft();
		else
			return view.getLeft()
					+ getRelativeLeft((View) view.getParent(), rootView);
	}

	private int getRelativeTop(View view, View rootView) {
		if (view.getParent() == rootView)
			return view.getTop();
		else
			return view.getTop()
					+ getRelativeTop((View) view.getParent(), rootView);
	}

	View mSelectedPreview;

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mSelectedPreview = view;

		getFragmentManager()
				.beginTransaction()
				.add(R.id.activity_main_container,
						FullscreenFragment.newInstance((int) parent
								.getAdapter().getItem(position),
								getRelativeLeft(view, rootView),
								getRelativeTop(view, rootView),
								view.getWidth(), view.getHeight()),
						FullscreenFragment.TAG)
				.addToBackStack(FullscreenFragment.TAG).commit();
	}

	public static interface OnImageSelectedListener {
		public void OnImageSelected(int resId);
	}

	@Override
	public void onOpening() {
		mSelectedPreview.setVisibility(View.INVISIBLE);

		setHasOptionsMenu(false);}

	@Override
	public void onOpened() {
		// TODO
	}

	@Override
	public void onClosing() {
		setHasOptionsMenu(true);
	}

	@Override
	public void onClosed() {
		mSelectedPreview.setVisibility(View.VISIBLE);
		getFragmentManager().popBackStack();
	}

}
