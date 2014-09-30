package com.example.abtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

public class ABButton extends Button implements IABView {

	private Dirrection mDirrection;

	public ABButton(Context context) {
		this(context, null);
	}

	public ABButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ABButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray attributes = context.getTheme().obtainStyledAttributes(
				attrs, R.styleable.ABButton, 0, 0);
		try {
			switch (attributes.getInt(R.styleable.ABButton_dirrection, 0)) {
			default:
			case 0:
				setDirrection(Dirrection.LEFT_TO_RIGHT);
				break;
			case 1:
				setDirrection(Dirrection.RIGHT_TO_LEFT);
				break;
			}
		} finally {
			attributes.recycle();
		}
	}

	@Override
	public Dirrection getDirrection() {
		return null;
	}

	public void setDirrection(Dirrection mDirrection) {
		this.mDirrection = mDirrection;
	}

}
