package logic.effects;

import logic.Board;

public abstract class Effect {
	private static int value = 0;
	
	public abstract void apply(Board board);

	public static int getValue() {
		return value;
	}

	public static void setValue(int value) {
		Effect.value = value;
	}
	
	
}
