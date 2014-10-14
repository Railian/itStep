public class WindowManager {

	public static void autoOpenWindow(Sensor windowDownSensor, Motor windowMotor) {
		startOpeningWindow(windowDownSensor, windowMotor);
		while (!windowDownSensor.check()) {
		}
		stopMovingWindow(windowMotor);
	}

	public static void autoCloseWindow(Sensor windowUpSensor, Motor windowMotor) {
		startClosingWindow(windowUpSensor, windowMotor);
		while (!windowUpSensor.check()) {
		}
		stopMovingWindow(windowMotor);
	}

	public static void startOpeningWindow(Sensor windowDownSensor,
			Motor windowMotor) {
		if (!windowDownSensor.check())
			windowMotor.rotateCW();
	}

	public static void startClosingWindow(Sensor windowUpSensor,
			Motor windowMotor) {
		if (!windowUpSensor.check())
			windowMotor.rotateCCW();
	}

	public static void stopMovingWindow(Motor windowMotor) {
		windowMotor.stop();
	}
}
