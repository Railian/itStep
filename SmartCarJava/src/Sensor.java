public class Sensor {

	private final int SENSOR_PORT;
	private final int SENSOR_PIN;

	private final MCDevice MC_DEVICE;

	public Sensor(MCDevice device, int port, int pin) {
		MC_DEVICE = device;
		SENSOR_PORT = port;
		SENSOR_PIN = pin;
	}
	
	public boolean check(){
		return MC_DEVICE.checkPin(SENSOR_PORT, SENSOR_PIN);
	}

}
