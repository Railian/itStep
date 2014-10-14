public class LockManager {

	public static void openDoor(final Motor lockMotor) {
		lockMotor.rotateCW();
		new Thread() {
			public void run() {
				try {
					sleep(3_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lockMotor.stop();
			};
		}.start();
	}

	public static void closeDoor(final Motor lockMotor) {
		lockMotor.rotateCCW();
		new Thread() {
			public void run() {
				try {
					sleep(3_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lockMotor.stop();
			};
		}.start();
	}

	public static void closeDoors(Motor... lockMotors) {
		for (Motor lockMotor : lockMotors)
			closeDoor(lockMotor);
	}

	public static void openDoors(Motor... lockMotors) {
		for (Motor lockMotor : lockMotors)
			openDoor(lockMotor);
	}

}
