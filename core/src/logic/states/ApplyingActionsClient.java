package logic.states;

import logic.Board;
import logic.controller.BoardController;
import logic.controller.BoardControllerClient;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ApplyingActionsClient implements State {
    public String getNextActionName(){
        return "Apply Effects";
    }

    @Override
    public void buy(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't buy right now.\n");
    }

    @Override
    public void buyEcho(BoardController boardController, int i) {
    }

    @Override
    public void next(BoardController boardController) {
        BoardControllerClient client = (BoardControllerClient)boardController;
        client.getProxy().next(0, 0);
    }

    @Override
    public void nextEcho(BoardController boardController, int a, int b) {
        Board board = boardController.getBoard();
        if(board.applyCurrentSpaceEffect())
            boardController.setState(new WaitingNextTurnClient());
    }

    @Override
    public void sell(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't sell right now.\n");
    }

    @Override
    public void sellEcho(BoardController boardController, int i) {
    }
}
