package logic;

import logic.effects.Effect;
import logic.effects.Move;

/**
 * Represents an Card.
 *
 * Each card has an effect.
 * value is used to send extra information after selecting the effect.
 */
public class Card {
	private String text;
	private Effect effect;
	
	public Card(String text, String effect, int value) {
		super();
		this.text = text;
		this.effect = createEffect(effect, value);
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
	private Effect createEffect(String effect, int value){
		return new Move(value);
	}


}
