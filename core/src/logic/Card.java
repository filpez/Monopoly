package logic;

import logic.effects.Effect;

/**
 * Represents an Card.
 *
 * Each card has an effect.
 * value is used to send extra information after selecting the effect.
 * Effects are not fully implemented yet - TO DO
 */
public class Card {
	private String text;
	private Effect effect;
	private int value;
	
	public Card(String text, Effect effect, int value) {
		super();
		this.text = text;
		this.effect = effect;
		this.value = value;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Effect getEffect() {
		return effect;
	}
	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
