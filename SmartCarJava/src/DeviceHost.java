public class DeviceHost {

	private DoorUnit mFrontLeftDoor;
	private DoorUnit mFrontRightDoor;
	private DoorUnit mRearLeftDoor;
	private DoorUnit mRearRightDoor;

	private SecuritySystem mSecuritySystem;

	public DeviceHost() {
		mFrontLeftDoor = new DoorUnit();
		mFrontRightDoor = new DoorUnit();
		mRearLeftDoor = new DoorUnit();
		mRearRightDoor = new DoorUnit();
		
		mSecuritySystem = new SecuritySystem(mFrontLeftDoor, mFrontRightDoor,
				mRearLeftDoor, mRearRightDoor);
	}

}
