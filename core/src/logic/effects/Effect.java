package logic.effects;

import logic.board.Board;

/**
 * Represents an effect associated with spaces.
 */

public abstract class Effect {
	private int value = 0;

	/**
	 * Creates a new effect
	 * @param value - extra information to the effect (money value, number of positions to move, etc.)
     */
	public Effect(int value) {
		this.value = value;
	}

	/**
	 * Applies an effect
	 * @param board - board to which the effect will be applied
	 * @return true if there are more effects to apply.
     */
	public abstract boolean apply(Board board);

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
