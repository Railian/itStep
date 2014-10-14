
public class ATmega32 extends MCDevice {

	public static final int PORT_A = 0;
	public static final int PORT_B = 1;
	public static final int PORT_C = 2;
	public static final int PORT_D = 3;

	public ATmega32() {
		super(new MCPort("A"), new MCPort("B"), new MCPort("C"),
				new MCPort("D"));
	}

}
