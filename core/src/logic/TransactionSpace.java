package logic;

import logic.effects.PayTax;

/**
 * Represents an Space where only monetary transaction are made. Example: Start, Jail, Taxes Spaces
 */
public class TransactionSpace extends Space {

	public TransactionSpace(String name, int value) {
		super(name);
		this.effect = new PayTax(value);
	}
}
