public class DoorUnit extends ATmega32 {

	private Motor mWindowMotor;
	private Sensor mWindowUpSensor;
	private Sensor mWindowDownSensor;
	private Motor mLockMotor;

	public DoorUnit() {
		mWindowMotor = new Motor(this, PORT_A, MCPort.PIN_0, PORT_A, MCPort.PIN_1);
		mWindowUpSensor = new Sensor(this, PORT_B, MCPort.PIN_0);
		mWindowDownSensor = new Sensor(this, PORT_B, MCPort.PIN_1);
		mLockMotor = new Motor(this, PORT_A, MCPort.PIN_2, PORT_A, MCPort.PIN_3);
	}

	public Motor getWindowMotor() {
		return mWindowMotor;
	}

	public Sensor getWindowUpSensor() {
		return mWindowUpSensor;
	}

	public Sensor getWindowDownSensor() {
		return mWindowDownSensor;
	}

	public Motor getLockMotor() {
		return mLockMotor;
	}

}
