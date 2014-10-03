package com.example.onlinestore.welcome;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.onlinestore.R;
import com.example.onlinestore.welcome.AccountManager.Result;

public class AuthorizationFragment extends Fragment implements OnClickListener {

	public static final String TAG = AuthorizationFragment.class
			.getSimpleName();

	private static final String EXTRA_MODE_ORDINAL = "EXTRA_MODE_ORDINAL";

	private static enum Mode {
		SING_IN, REGISTRATION
	};

	private AccountManager mAccountManager;

	private EditText etLogin;
	private EditText etPassword;
	private EditText etConfirm;
	private TableRow trConfirm;
	private Button btFirst;
	private Button btSecond;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mAccountManager = new AccountManager(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_authorization,
				container, false);
		etLogin = (EditText) rootView
				.findViewById(R.id.fragment_authorization_etLogin);
		etPassword = (EditText) rootView
				.findViewById(R.id.fragment_authorization_etPassword);
		etConfirm = (EditText) rootView
				.findViewById(R.id.fragment_authorization_etConfirm);
		trConfirm = (TableRow) rootView
				.findViewById(R.id.fragment_authorization_trConfirm);
		btFirst = (Button) rootView
				.findViewById(R.id.fragment_authorization_btFirst);
		btSecond = (Button) rootView
				.findViewById(R.id.fragment_authorization_btSecond);

		btFirst.setOnClickListener(this);
		btSecond.setOnClickListener(this);

		Mode mode = savedInstanceState == null ? Mode.SING_IN
				: Mode.values()[savedInstanceState.getInt(EXTRA_MODE_ORDINAL)];
		switchModeTo(rootView, mode, false);

		return rootView;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		int ordinal = ((Mode) getView().getTag()).ordinal();
		outState.putInt(EXTRA_MODE_ORDINAL, ordinal);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v) {
		switch ((Mode) getView().getTag()) {
		case SING_IN:
			switch (v.getId()) {
			case R.id.fragment_authorization_btFirst:
				tryToSingIn();
				break;
			case R.id.fragment_authorization_btSecond:
				switchModeTo(getView(), Mode.REGISTRATION, true);
				break;
			}
			break;
		case REGISTRATION:
			switch (v.getId()) {
			case R.id.fragment_authorization_btFirst:
				tryToSaveAndSingIn();
				break;
			case R.id.fragment_authorization_btSecond:
				switchModeTo(getView(), Mode.SING_IN, true);
				break;
			}
		}
	}

	private void tryToSingIn() {
		String login = etLogin.getText().toString();
		String password = etPassword.getText().toString();
		Result checkResult = mAccountManager.checkAccount(login, password);
		switch (checkResult) {
		case INVALID_LOGIN:
		case LOGIN_IS_NOT_FOUND:
			etLogin.setError(checkResult.toString(getActivity()));
			break;
		case INVALID_PASSWORD:
		case WRONG_PASSWORD:
			etPassword.setError(checkResult.toString(getActivity()));
			break;
		case SUCCESS:
			enterToOnlineStore(login);
			break;
		default:
		}
	}

	private void tryToSaveAndSingIn() {
		String login = etLogin.getText().toString();
		String password = etPassword.getText().toString();
		String confirm = etConfirm.getText().toString();

		Result confirmResult = mAccountManager.checkConfirmation(password,
				confirm);
		if (confirmResult == Result.PASSWORD_IS_NOT_CONFIRM) {
			etConfirm.setError(confirmResult.toString(getActivity()));
			return;
		}
		Result addResult = mAccountManager.addAccount(login, password);
		switch (addResult) {
		case INVALID_LOGIN:
		case LOGIN_ALREADY_EXISTS:
			etLogin.setError(addResult.toString(getActivity()));
			break;
		case INVALID_PASSWORD:
			etPassword.setError(addResult.toString(getActivity()));
			break;
		case SUCCESS:
			enterToOnlineStore(login);
			break;
		default:
		}
	}

	private void switchModeTo(View view, Mode mode, boolean erase) {
		switch (mode) {
		case REGISTRATION:
			trConfirm.setVisibility(View.VISIBLE);
			btFirst.setText(getResources().getString(
					R.string.fragment_authorization_btSaveAndSingIn));
			btSecond.setText(getResources().getString(
					R.string.fragment_authorization_btCancel));
			break;
		case SING_IN:
			trConfirm.setVisibility(View.GONE);
			btFirst.setText(getResources().getString(
					R.string.fragment_authorization_btSingIn));
			btSecond.setText(getResources().getString(
					R.string.fragment_authorization_btRegistration));
			break;
		}
		if (erase) {
			etLogin.setError(null);
			etPassword.setError(null);
			etConfirm.setError(null);
			etPassword.setText(null);
			etConfirm.setText(null);
		}
		view.setTag(mode);
	}

	private void enterToOnlineStore(String login) {
		Toast.makeText(getActivity(), "Starting main Activity",
				Toast.LENGTH_SHORT).show();
		// TODO enter to onlineStore
	}
}
