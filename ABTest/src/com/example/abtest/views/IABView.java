package com.example.abtest.views;

public interface IABView {
	
	public static enum Dirrection {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT 
	};
	
	public Dirrection getDirrection();
	
}
