package logic.effects;

import logic.Board;
import logic.Player;
import logic.Space;
import logic.TransactionSpace;

public class PayTax extends Effect {
	
	@Override
	public void apply(Board board) {
		Player currentPlayer = board.getCurrentPlayer();
		Space currentPosition = board.getSpace(currentPlayer.getPosition());
		if(currentPosition instanceof TransactionSpace){
			int newValue = ((TransactionSpace)currentPosition).getValue();
			setValue(newValue);
		}
		currentPlayer.pay(getValue());
	}
}
