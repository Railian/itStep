package com.example.onlinestore.settings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinestore.R;

public class BillingFormFragment extends Fragment {

	public static final String TAG = BillingFormFragment.class.getSimpleName();

	private CreditCardNumberEdit etCardNumber;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_billing_form,
				container, false);
		etCardNumber = (CreditCardNumberEdit) rootView.findViewById(R.id.card_number);
		

		return rootView;
	}

}
