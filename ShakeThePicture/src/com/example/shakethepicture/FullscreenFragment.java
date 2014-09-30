package com.example.shakethepicture;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class FullscreenFragment extends Fragment implements OnClickListener {

	public static final String TAG = FullscreenFragment.class.getSimpleName();

	private static final String EXTRA_RES_ID = "EXTRA_RES_ID";

	private GalleryImageFullview gifvFullscreenImage;
	private View vCancelable;
	private AnimatorSet asShake;
	private Animation aApperance;

	public static FullscreenFragment newInstance(int resId, int left, int top,
			int width, int height) {
		FullscreenFragment fragment = new FullscreenFragment(left, top, width,
				height);
		Bundle args = new Bundle();
		args.putInt(EXTRA_RES_ID, resId);
		fragment.setArguments(args);
		return fragment;
	}

	int mLeftFrom;
	int mTopFrom;
	int mWidthFrom;
	int mHeightFrom;

	public FullscreenFragment() {
	}

	public FullscreenFragment(int left, int top, int width, int height) {
		mLeftFrom = left;
		mTopFrom = top;
		mWidthFrom = width;
		mHeightFrom = height;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		asShake = (AnimatorSet) AnimatorInflater.loadAnimator(activity,
				R.animator.shake);
		aApperance = AnimationUtils.loadAnimation(activity, R.anim.apperance);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_fullscreen,
				container, false);

		gifvFullscreenImage = (GalleryImageFullview) rootView
				.findViewById(R.id.fragment_fullscreen_ivFullscreenImage);
		vCancelable = rootView
				.findViewById(R.id.fragment_fullscreen_vCancelable);

		vCancelable.setOnClickListener(this);
		gifvFullscreenImage.setSampledImageResource(getArguments()
				.getInt(EXTRA_RES_ID));
		gifvFullscreenImage.setOnClickListener(this);
		gifvFullscreenImage
				.setOnShowListener((GalleryFragment) getFragmentManager()
						.findFragmentByTag(GalleryFragment.TAG));

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		vCancelable.startAnimation(aApperance);
		gifvFullscreenImage.startOpenAnimation(mLeftFrom, mTopFrom, mWidthFrom,
				mHeightFrom, 600);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_fullscreen_ivFullscreenImage:
			asShake.setTarget(gifvFullscreenImage);
			if (!asShake.isRunning())
				asShake.start();
			break;
		case R.id.fragment_fullscreen_vCancelable:
			onBackPressed();
			break;
		}
	}

	public void onBackPressed() {
		gifvFullscreenImage.startCloseAnimation(600);
		vCancelable.startAnimation(getFadeAnimation());
	}

	private Animation getFadeAnimation() {
		return AnimationUtils.loadAnimation(getActivity(), R.anim.fade);
	}
	
}
