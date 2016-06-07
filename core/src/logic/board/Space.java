package logic.board;

import logic.effects.Effect;

/**
 * Represents every space on the board
 * Every space has a name and some may have a Effect attached to them
 */
public abstract class Space {
	private String name;
	protected Effect effect;

	/**
	 * Creates a new space
	 * @param name - name of the space
     */
	public Space(String name) {
		this.name = name;
	}

	/**
	 * Applies effect attached to this space
	 * @param board - board in which the effect will be applied
	 * @param diceValue - sum of the values on the two dices
     * @return true if successful, false if not
     */
	public boolean applyEffect(Board board, int diceValue){
		return effect.apply(board);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}
}
