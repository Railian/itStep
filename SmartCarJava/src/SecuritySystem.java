public class SecuritySystem extends ATmega32 {

	private DoorUnit[] mDoorUnits;

	public SecuritySystem(DoorUnit... doorUnits) {
		mDoorUnits = doorUnits;
	}

	public void turnOnAlarm() {
		for (DoorUnit doorUnit : mDoorUnits)
			LockManager.closeDoor(doorUnit.getLockMotor());

		for (DoorUnit doorUnit : mDoorUnits)
			WindowManager.autoCloseWindow(doorUnit.getWindowUpSensor(),
					doorUnit.getWindowMotor());
	}

	public void turnOffAlarm() {
		for (DoorUnit doorUnit : mDoorUnits)
			LockManager.openDoor(doorUnit.getLockMotor());
	}

}
