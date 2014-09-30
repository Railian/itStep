package com.example.abtest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;


	public class ABListView extends Button implements IABView {

		public ABListView(Context context) {
			this(context, null);
		}

		public ABListView(Context context, AttributeSet attrs) {
			this(context, attrs, 0);
		}

		public ABListView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}

		@Override
		public Dirrection getDirrection() {
			return null;
		}


}
