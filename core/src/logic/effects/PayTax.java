package logic.effects;

import logic.board.Board;
import logic.board.Player;

public class PayTax extends Effect {
	/**
	 * Creates a new PayTax effect
	 * @param value - tax value (can be positive or negative)
     */
	public PayTax(int value) {
		super(value);
	}

	/**
	 * Applies effect that makes currentPlayer pay a tax
	 * @param board - board to which the effect will be applied
	 * @return true
     */
	@Override
	public boolean apply(Board board) {
		Player currentPlayer = board.getCurrentPlayer();
		currentPlayer.pay(getValue());
		if (getValue() > 0)
			board.addActionToLog(" paid " + getValue() + " to Bank!\n");
		else if (getValue() < 0)
			board.addActionToLog(" received " + ((-1) * getValue()) + " from Bank!\n");
		return true;
	}
}
