package com.example.toycar.hard;

public interface Turnable {

	public static enum Direction {
		LEFT, RIGHT, TOP, DOWN
	}

	void turnTo(Direction direction);

}
