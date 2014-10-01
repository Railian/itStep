package com.example.abtest.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;


	public class ABListView extends ListView implements IABView {

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
