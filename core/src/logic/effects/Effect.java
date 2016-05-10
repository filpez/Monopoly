package logic.effects;

import logic.Board;

public abstract class Effect {
	private int value = 0;

	public Effect(int value) {
		this.value = value;
	}

	public abstract boolean apply();

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
