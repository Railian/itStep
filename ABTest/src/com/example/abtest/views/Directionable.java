package com.example.abtest.views;

public interface Directionable {
	
	public static enum Dirrection {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT 
	};
	
	public Dirrection getDirrection();
	
}
