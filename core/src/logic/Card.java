package logic;

import logic.effects.Effect;
import logic.effects.GoTo;
import logic.effects.GoToJail;
import logic.effects.Move;
import logic.effects.PayRent;
import logic.effects.PayTax;

/**
 * Represents an Card.
 *
 * Each card has an effect.
 * value is used to send extra information after selecting the effect.
 */
public class Card {
	private String text;
	private Effect effect;

	/**
	 * Creates a new card
	 * @param text - describes the purpose of the card
	 * @param effect - name of the card effect
	 * @param value - extra information to the effect of the card
     */
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

	/**
	 * Creates a new Effect object
	 * @param effect - name of the desired effect
	 * @param value - Effect object parameter
     * @return new Effect object
     */
	private Effect createEffect(String effect, int value){
		if(effect.equals("GoTo"))
			return new GoTo(value);
		else if(effect.equals("GoToJail"))
			return new GoToJail(value);
		else if(effect.equals("Move"))
			return new Move(value);
		else if(effect.equals("PayRent"))
			return new PayRent(value);
		else return new PayTax(value);
	}

}
