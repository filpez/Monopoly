package logic.board;

import logic.effects.PayTax;

/**
 * Represents an Space where only monetary transaction are made.
 * Examples: Start, Jail, Tax Spaces
 */
public class TransactionSpace extends Space {

	/**
	 * Creates a new transaction space
	 * @param name - name of the space
	 * @param value - monetary transition (positive if it makes the player pay to the bank, negative otherwise)
     */
	public TransactionSpace(String name, int value) {
		super(name);
		this.effect = new PayTax(value);
	}
}
