package com.example.onlinestore.settings;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class CreditCardNumberEdit extends EditText {

	long mCardNumber;
	ArrayList<Byte> mCardNumbers;

	public CreditCardNumberEdit(Context context) {
		super(context);
		customConstructor();
	}

	public CreditCardNumberEdit(Context context, AttributeSet attrs) {
		super(context, attrs);
		customConstructor();
	}

	public CreditCardNumberEdit(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		customConstructor();
	}

	private void customConstructor() {
		addTextChangedListener(mTextWatcher);
		mCardNumbers = new ArrayList<>();
	}

	private TextWatcher mTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			System.out.println(s.toString() + " - onTextChanged");

			ArrayList<Character> digits = new ArrayList<>();
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < s.length(); i++)
				if (Character.isDigit(s.charAt(i)))
					digits.add(s.charAt(i));

			int j = 0;
			while (digits.size() > j * 4) {
				if (j == 4)
					break;
				if (j != 0)
					builder.append(" - ");
				for (int i = 0; i < Math.min(digits.size() - 4 * j, 4); i++)
					builder.append(digits.get(4 * j + i));
				j++;
			}
			System.out.println(builder.toString());
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			System.out.println(s.toString() + " - beforeTextChanged");
		}

		@Override
		public void afterTextChanged(Editable s) {

			// ArrayList<Character> digits = new ArrayList<>();
			// StringBuilder builder = new StringBuilder();
			//
			// for (int i = 0; i < s.length(); i++)
			// if (Character.isDigit(s.charAt(i)))
			// digits.add(s.charAt(i));
			//
			// int j = 0;
			// while (digits.size() > j * 4) {
			// if (j == 4)
			// break;
			// if (j != 0)
			// builder.append(" - ");
			// for (int i = 0; i < Math.min(digits.size() - 4 * j, 4); i++)
			// builder.append(digits.get(4 * j + i));
			// j++;
			// }
			// System.out.println(builder.toString());

			removeTextChangedListener(this);
			s.setFilters(new InputFilter[] { new InputFilter() {
				@Override
				public CharSequence filter(CharSequence source, int start,
						int end, Spanned dest, int dstart, int dend) {
					 ArrayList<Character> digits = new ArrayList<>();
					 StringBuilder builder = new StringBuilder();
					
					 for (int i = 0; i < source.length(); i++)
					 if (Character.isDigit(source.charAt(i)))
					 digits.add(source.charAt(i));
					
					 int j = 0;
					 while (digits.size() > j * 4) {
					 if (j == 4)
					 break;
					 if (j != 0)
					 builder.append(" - ");
					 for (int i = 0; i < Math.min(digits.size() - 4 * j, 4);
					 i++)
					 builder.append(digits.get(4 * j + i));
					 j++;
					 }
					return null;
				}
			} });
			addTextChangedListener(this);
		}
	};

}
