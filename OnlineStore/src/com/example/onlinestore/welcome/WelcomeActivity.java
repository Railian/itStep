package com.example.onlinestore.welcome;

import com.example.onlinestore.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;

public class WelcomeActivity extends Activity {

	private CountDownTimer mTimer = new CountDownTimer(2_000, 2_000) {

		@Override
		public void onTick(long millisUntilFinished) {
		}

		@Override
		public void onFinish() {
			showAuthorizationFragment();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		if (savedInstanceState == null) {
			showSplashFragment();
			mTimer.start();
		} else {
			if (getFragmentManager().findFragmentByTag(
					AuthorizationFragment.TAG) == null)
				showAuthorizationFragment();
		}
	}

	@Override
	protected void onDestroy() {
		mTimer.cancel();
		super.onDestroy();
	}

	private void showSplashFragment() {
		getFragmentManager()
				.beginTransaction()
				.add(R.id.activity_welcome_container, new SplashFragment(),
						SplashFragment.TAG).commit();
	}

	private void showAuthorizationFragment() {
		getFragmentManager()
				.beginTransaction()
				.setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
				.replace(R.id.activity_welcome_container,
						new AuthorizationFragment(), AuthorizationFragment.TAG)
				.commit();
	}

}
