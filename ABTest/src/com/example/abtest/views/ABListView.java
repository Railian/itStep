package com.example.abtest.views;

import com.example.abtest.R;
import com.example.abtest.views.IABView.Dirrection;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;


	public class ABListView extends ListView implements IABView {

		private Dirrection mDirrection;

		public ABListView(Context context) {
			super(context);
			customConstructor(context, null);
		}

		public ABListView(Context context, AttributeSet attrs) {
			super(context, attrs);
			customConstructor(context, attrs);
		}

		public ABListView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			customConstructor(context, attrs);
		}

		private void customConstructor(Context context, AttributeSet attrs) {
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
