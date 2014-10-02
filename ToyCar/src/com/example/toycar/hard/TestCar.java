package com.example.toycar.hard;

import android.content.Context;
import android.widget.Toast;

import com.example.toycar.hard.Moveable.State;
import com.example.toycar.hard.Turnable.Direction;

public class TestCar extends AbstractCar {

	private Context mContext;

	public TestCar(Context context) {
		mContext = context;
	}

	@Override
	public void startBeep() {
			Toast.makeText(mContext, "beep-beep", Toast.LENGTH_SHORT).show();
	}
	@Override
	public void stopBeep() {
			Toast.makeText(mContext, "stop beeping", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void turnTo(Direction direction) {
		Toast.makeText(mContext, "Turn to the " + direction, Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void move(State state) {
		Toast.makeText(mContext, state + " movement", Toast.LENGTH_SHORT)
				.show();
	}
}
