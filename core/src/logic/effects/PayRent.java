package logic.effects;

import logic.Board;
import logic.Player;
import logic.Propriety;
import logic.Space;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class PayRent extends Effect {
    public PayRent(int value) {
        super(value);
    }

    @Override
    public boolean apply(Board board) {
        Player currentPlayer = board.getCurrentPlayer();
        Propriety currentSpace = (Propriety)board.getSpace(currentPlayer.getPosition());
        Player owner = currentSpace.getOwner();
        if(owner != null && owner != currentPlayer){
            int rent = currentSpace.getRent(getValue());
            currentPlayer.pay(rent);
            owner.receive(rent);
            board.getController().addActionToLog(" paid " + getValue() + " to " + owner.getName() +"!\n");
            //return true;
        }
        return true;
    }
}
