package com.example.abtest;

public interface IABView {
	
	public static enum Dirrection {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT
	};
	
	public Dirrection getDirrection();
	
}
