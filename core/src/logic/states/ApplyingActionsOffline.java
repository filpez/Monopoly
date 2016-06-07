package logic.states;

import logic.board.Board;
import logic.controller.BoardController;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ApplyingActionsOffline implements State {
    public String getNextActionName(){
        return "Apply Effects";
    }

    /**
     * Tells that it is impossible to buy
     * @param boardController
     * @param i - current position
     */
    @Override
    public void buy(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't buy right now.\n");
    }

    /**
     * Does nothing
     * @param boardController
     * @param i - current position
     */
    @Override
    public void buyEcho(BoardController boardController, int i) {
    }

    /**
     * Applies the current space effect
     * @param boardController
     */
    @Override
    public void next(BoardController boardController) {
        nextEcho(boardController, 0, 0);
    }

    /**
     * Applies the current space effect
     * @param boardController
     * @param a - not used
     * @param b - not used
     */
    @Override
    public void nextEcho(BoardController boardController, int a, int b) {
        Board board = boardController.getBoard();
        if(board.applyCurrentSpaceEffect())
            boardController.setState(new WaitingNextTurnOffline());
    }

    /**
     * Tells that it is impossible to sell
     * @param boardController
     * @param i - current position
     */
    @Override
    public void sell(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't sell right now.\n");
    }

    /**
     * Does nothing
     * @param boardController
     * @param i - current position
     */
    @Override
    public void sellEcho(BoardController boardController, int i) {
    }
}
