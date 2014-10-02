package com.example.toycar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.example.toycar.hard.Moveable.State;
import com.example.toycar.hard.TestCar;
import com.example.toycar.hard.Turnable.Direction;

public class MainActivity extends ActionBarActivity implements OnClickListener,
		OnTouchListener {

	private TestCar car = new TestCar(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.activity_main_btLeft).setOnClickListener(this);
		findViewById(R.id.activity_main_btRight).setOnClickListener(this);
		findViewById(R.id.activity_main_btTop).setOnClickListener(this);
		findViewById(R.id.activity_main_btDown).setOnClickListener(this);
		findViewById(R.id.activity_main_btStart).setOnClickListener(this);
		findViewById(R.id.activity_main_btStop).setOnClickListener(this);
		findViewById(R.id.activity_main_btSignal).setOnTouchListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_main_btLeft:
			car.turnTo(Direction.LEFT);
			break;
		case R.id.activity_main_btRight:
			car.turnTo(Direction.RIGHT);
			break;
		case R.id.activity_main_btTop:
			car.turnTo(Direction.TOP);
			break;
		case R.id.activity_main_btDown:
			car.turnTo(Direction.DOWN);
			break;
		case R.id.activity_main_btStart:
			car.move(State.START);
			break;
		case R.id.activity_main_btStop:
			car.move(State.STOP);
			break;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (v.getId() == R.id.activity_main_btSignal)
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				car.startBeep();
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				car.stopBeep();
				break;
			}
		return false;
	}
}
