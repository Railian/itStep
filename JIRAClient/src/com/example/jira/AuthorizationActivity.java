package com.example.jira;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AuthorizationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_authorization);
		findViewById(R.id.buttonLogin).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(AuthorizationActivity.this,
								MainActivity.class));
					}
				});
	}

}
