package logic;

import logic.states.State;
import logic.states.ThrowingDice;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardControllerClient extends BoardController{

    public BoardControllerClient(Board board) {
        super();
        this.setBoard(board);
        this.state = new ThrowingDice();
    }


}
