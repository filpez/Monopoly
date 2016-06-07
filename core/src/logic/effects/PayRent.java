package logic.effects;

import logic.Board;
import logic.Player;
import logic.Property;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class PayRent extends Effect {
    /**
     * Creates a new PayRent effect
     * @param value - rent value
     */
    public PayRent(int value) {
        super(value);
    }

    /**
     * Applies effect that makes currentPlayer pay a rent
     * @param board - board to which the effect will be applied
     * @return true if rent was paid, false if not
     */
    @Override
    public boolean apply(Board board) {
        Player currentPlayer = board.getCurrentPlayer();
        Property currentSpace = (Property)board.getSpace(currentPlayer.getPosition());
        Player owner = currentSpace.getOwner();
        if(owner != null && owner != currentPlayer){
            int rent = currentSpace.getRent(board.getLastMovement());
            currentPlayer.pay(rent);
            owner.receive(rent);
            board.addActionToLog(" paid " + getValue() + " to " + owner.getName() +"!\n");
            return true;
        }
        return false;
    }
}
