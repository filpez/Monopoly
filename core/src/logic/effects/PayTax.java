package logic.effects;

import logic.Board;
import logic.Player;

public class PayTax extends Effect {
	public PayTax(int value) {
		super(value);
	}

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
