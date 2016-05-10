package logic;

import logic.effects.Effect;

/**
 * Represents a space on the board.
 *
 * Keeps the name of the Space
 */
public abstract class Space {
	private String name;
	protected Effect effect;

	public Space(String name) {
		super();
		this.name = name;
	}

	public boolean applyEffect(Board board, int diceValue){
		return effect.apply(board);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
