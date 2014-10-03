package com.example.onlinestore.welcome;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.onlinestore.R;

public class AccountManager {

	private static final String PREF_ACCOUNTS = "ACCOUNTS";

	public static enum Result {

		// @formatter:off
		INVALID_LOGIN			(R.string.account_manager_invalid_login), 
		INVALID_PASSWORD		(R.string.account_manager_invalid_password), 
		LOGIN_ALREADY_EXISTS	(R.string.account_manager_login_already_exists), 
		LOGIN_IS_NOT_FOUND		(R.string.account_manager_login_is_not_found), 
		WRONG_PASSWORD			(R.string.account_manager_wrong_password), 
		SUCCESS					(R.string.account_manager_success), 
		PASSWORD_IS_NOT_CONFIRM	(R.string.account_manager_password_is_not_confirm);
		// @formatter:on

		private int stringId;

		private Result(int stringId) {
			this.stringId = stringId;
		}

		public String toString(Context context) {
			return context.getResources().getString(stringId);
		}
	}

	private SharedPreferences mAccounts;

	public AccountManager(Context context) {
		mAccounts = context.getSharedPreferences(PREF_ACCOUNTS,
				Context.MODE_PRIVATE);
	}

	public Result checkLogin(String login) {
		if (login == null)
			return Result.INVALID_LOGIN;
		if (login.length() < 3)
			return Result.INVALID_LOGIN;
		return Result.SUCCESS;
	}

	public Result checkPassword(String password) {
		if (password == null)
			return Result.INVALID_PASSWORD;
		if (password.length() < 4)
			return Result.INVALID_PASSWORD;
		return Result.SUCCESS;
	}

	public Result checkConfirmation(String password, String confirm) {
		return password.equals(confirm) ? Result.SUCCESS
				: Result.PASSWORD_IS_NOT_CONFIRM;
	}

	public Result checkAccount(String login, String password) {
		Result result;
		result = checkLogin(login);
		if (result != Result.SUCCESS)
			return result;
		result = checkPassword(password);
		if (result != Result.SUCCESS)
			return result;
		if (!hasAccount(login))
			return Result.LOGIN_IS_NOT_FOUND;
		if (!password.equals(getPassword(login)))
			return Result.WRONG_PASSWORD;
		return Result.SUCCESS;
	}

	public Result addAccount(String login, String password) {
		Result result;
		result = checkLogin(login);
		if (result != Result.SUCCESS)
			return result;
		result = checkPassword(password);
		if (result != Result.SUCCESS)
			return result;
		if (hasAccount(login))
			return Result.LOGIN_ALREADY_EXISTS;
		mAccounts.edit().putString(login, password).apply();
		return Result.SUCCESS;
	}

	public boolean hasAccount(String login) {
		if (login == null)
			return false;
		return mAccounts.contains(login);
	}

	public Result changePassword(String login, String password) {
		Result result;
		result = checkLogin(login);
		if (result != Result.SUCCESS)
			return result;
		result = checkPassword(password);
		if (result != Result.SUCCESS)
			return result;
		if (!hasAccount(login))
			return Result.LOGIN_IS_NOT_FOUND;
		mAccounts.edit().putString(login, password).apply();
		return Result.SUCCESS;
	}

	private String getPassword(String login) {
		return mAccounts.getString(login, null);
	}

}
