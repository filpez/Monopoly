package logic.controller;

import logic.Board;
import logic.states.ThrowingDice;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardControllerClient extends logic.controller.BoardController {

    public BoardControllerClient(Board board) {
        super();
        this.setBoard(board);
        this.state = new ThrowingDice();
    }


}
