public class Motor {

	private final int PORT_1;
	private final int PIN_1;

	private final int PORT_2;
	private final int PIN_2;

	private final MCDevice MC_DEVICE;

	public Motor(MCDevice device, int port1, int pin1, int port2, int pin2) {
		MC_DEVICE = device;
		PORT_1 = port1;
		PIN_1 = pin1;
		PORT_2 = port2;
		PIN_2 = pin2;
	}

	public void rotateCW() {
		MC_DEVICE.setPins(PORT_1, PIN_1);
		MC_DEVICE.resetPins(PORT_2, PIN_2);
	}

	public void rotateCCW() {
		MC_DEVICE.resetPins(PORT_1, PIN_1);
		MC_DEVICE.setPins(PORT_2, PIN_2);
	}

	public void stop() {
		MC_DEVICE.resetPins(PORT_1, PIN_1);
		MC_DEVICE.resetPins(PORT_2, PIN_2);
	}

}
