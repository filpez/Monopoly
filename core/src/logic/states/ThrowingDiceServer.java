package logic.states;

import java.util.Random;

import logic.board.Board;
import logic.controller.BoardController;
import logic.controller.BoardControllerServer;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ThrowingDiceServer implements State {
    public String getNextActionName(){
        return "Throw Dices";
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
     * Simulates throwing the dices, and calls next on this controller(server) with these values
     * @param boardController
     */
    @Override
    public void next(BoardController boardController) {
        Random rand = new Random();
        int a = rand.nextInt(6) + 1;
        int b = rand.nextInt(6) + 1;

        BoardControllerServer server = (BoardControllerServer)boardController;
        server.next(a, b);
    }

    /**
     * Moves the current player
     * @param boardController
     * @param a - value of dice
     * @param b - value of dice
     */
    @Override
    public void nextEcho(BoardController boardController, int a, int b) {
        Board board =  boardController.getBoard();
        board.throwDice(a);
        board.throwDice(b);
        board.move(a+b, a==b);
        boardController.setState(new ApplyingActionsServer());
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
