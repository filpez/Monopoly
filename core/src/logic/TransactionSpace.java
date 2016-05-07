package logic;

/**
 * Represents an Space where only monetary transaction are made. Example: Start, Jail, Taxes Spaces
 */
public class TransactionSpace extends Space {
	private int value;

	public TransactionSpace(String name, int value) {
		super(name);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
