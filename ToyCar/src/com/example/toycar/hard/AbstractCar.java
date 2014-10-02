package com.example.toycar.hard;

public abstract class AbstractCar implements Moveable, Turnable, Beepable {

	private boolean clacson;
	private float BatteryLevel;

	public boolean isClacson() {
		return clacson;
	}

	public void setClacson(boolean clacson) {
		this.clacson = clacson;
	}

	public float getBatteryLevel() {
		return BatteryLevel;
	}

	public void setBatteryLevel(float batteryLevel) {
		BatteryLevel = batteryLevel;
	}

}
