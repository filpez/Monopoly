package logic.effects;

import logic.Board;
import logic.Player;
import logic.Space;
import logic.TransactionSpace;

public class PayTax extends Effect {
	public PayTax(int value) {
		super(value);
	}

	@Override
	public boolean apply() {
		Board board = Board.getInstance();
		Player currentPlayer = board.getCurrentPlayer();
		currentPlayer.pay(getValue());
		return true;
	}
}
