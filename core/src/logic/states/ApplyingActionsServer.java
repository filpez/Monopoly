package logic.states;

import logic.Board;
import logic.Player;
import logic.Propriety;
import logic.controller.BoardController;
import logic.controller.BoardControllerServer;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ApplyingActionsServer implements State {
    @Override
    public void buy(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't buy right now.");
    }

    @Override
    public void buyEcho(BoardController boardController, int i) {
    }

    @Override
    public void next(BoardController boardController) {
        BoardControllerServer server = (BoardControllerServer)boardController;
        server.next(0, false);
    }

    @Override
    public void nextEcho(BoardController boardController, int i, boolean doubles) {
        Board board = boardController.getBoard();
        if(board.applyCurrentSpaceEffect())
            boardController.setState(new WaitingNextTurnServer());
    }

    @Override
    public void sell(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't sell right now.");
    }

    @Override
    public void sellEcho(BoardController boardController, int i) {
    }
}
