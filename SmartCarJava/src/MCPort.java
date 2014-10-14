
public class MCPort {

	public static final int PIN_0 = 1 << 0;
	public static final int PIN_1 = 1 << 1;
	public static final int PIN_2 = 1 << 2;
	public static final int PIN_3 = 1 << 3;
	public static final int PIN_4 = 1 << 4;
	public static final int PIN_5 = 1 << 5;
	public static final int PIN_6 = 1 << 6;
	public static final int PIN_7 = 1 << 7;

	private int mValue;
	private String mName;

	public MCPort(String name) {
		mName = name;
	}

	public void setPins(int pins) {
		mValue |= pins;
	}

	public void resetPins(int pins) {
		mValue &= ~pins;
	}

	public void switchPins(int pins) {
		mValue ^= pins;
	}

	public boolean checkPin(int pin) {
		return (mValue & pin) != 0;
	}

	public void setPortValue(int value) {
		mValue = value;
	}

	public int getPortValue() {
		return mValue;
	}

	public String toString() {
		return String.format("port %s:   %d %d %d %d %d %d %d %d", mName,
				(mValue >> 7) % 2, (mValue >> 6) % 2, (mValue >> 5) % 2,
				(mValue >> 4) % 2, (mValue >> 3) % 2, (mValue >> 2) % 2,
				(mValue >> 1) % 2, (mValue >> 0) % 2);
	}

}
