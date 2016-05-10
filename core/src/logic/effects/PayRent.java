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
    public boolean apply() {
        Board board = Board.getInstance();
        Player currentPlayer = board.getCurrentPlayer();
        Propriety currentSpace = (Propriety)board.getSpace(currentPlayer.getPosition());
        Player owner = currentSpace.getOwner();
        if(owner != null && owner != currentPlayer){
            int rent = currentSpace.getRent(0);
            currentPlayer.pay(rent); //TO DO dice value!!!!!!!!!!!!!!!!!
            owner.receive(rent);
            return true;
        }
        return false;
    }
}
