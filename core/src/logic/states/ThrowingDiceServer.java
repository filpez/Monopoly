package logic.states;

import java.util.Random;

import logic.Board;
import logic.controller.BoardController;
import logic.controller.BoardControllerServer;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ThrowingDiceServer implements State {
    @Override
    public void buy(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't buy right now.");
    }

    @Override
    public void buyEcho(BoardController boardController, int i) {
    }

    @Override
    public void next(BoardController boardController) {
        Random rand = new Random();
        int a = rand.nextInt(6) + 1;
        int b = rand.nextInt(6) + 1;

        BoardControllerServer server = (BoardControllerServer)boardController;
        server.next(a, b);
    }

    @Override
    public void nextEcho(BoardController boardController, int a, int b) {
        Board board =  boardController.getBoard();
        board.throwDice(a);
        board.throwDice(b);
        board.move(a+b, a==b);
        boardController.setState(new ApplyingActionsServer());
    }

    @Override
    public void sell(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't sell right now.");
    }

    @Override
    public void sellEcho(BoardController boardController, int i) {
    }
}