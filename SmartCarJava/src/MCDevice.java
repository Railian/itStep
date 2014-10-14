public class MCDevice {

	private MCPort[] mPorts;

	public MCDevice(MCPort... ports) {
		mPorts = ports;
	}

	public MCPort getPort(int port) {
		return mPorts[port];
	}

	public void setPins(int port, int pins) {
		getPort(port).setPins(pins);
	}

	public void resetPins(int port, int pins) {
		getPort(port).resetPins(pins);
	}

	public void switchPins(int port, int pins) {
		getPort(port).switchPins(pins);
	}

	public boolean checkPin(int port, int pin) {
		return getPort(port).checkPin(pin);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (MCPort port : mPorts)
			builder.append(port).append("      ");
		return builder.toString();
	}
}
