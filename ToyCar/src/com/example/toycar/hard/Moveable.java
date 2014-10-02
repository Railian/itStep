package com.example.toycar.hard;

public interface Moveable {

	public static enum State {
		START, STOP
	}

	void move(State state);
}
