package logic.states;

import logic.Board;
import logic.Player;
import logic.Propriety;
import logic.controller.BoardController;
import logic.controller.BoardControllerServer;

/**
 * Created by Filipe on 17/05/2016.
 */
public class WaitingNextTurnServer implements State {
    @Override
    public void buy(BoardController boardController, int i) {
        Board board = boardController.getBoard();
        Propriety selectedSpace;
        Player currentPlayer = board.getCurrentPlayer();
        if (!(board.getSpace(i) instanceof Propriety)) {
            board.addMessageToLog("You can't buy " + board.getSpace(i).getName() + "!\n");
            return;
        }
        else if(currentPlayer.getPosition() != i){
            board.addMessageToLog("You aren't at " + board.getSpace(i).getName() + "!\n");
            return;
        }
        else
            selectedSpace = (Propriety) board.getSpace(i);

        if (selectedSpace.getOwner() != null){
            board.addMessageToLog(board.getSpace(i).getName() + " already has an owner!\n");
        }
        else if (currentPlayer.canBuy(selectedSpace)){
            BoardControllerServer server = (BoardControllerServer)boardController;
            server.sell();
        }
        else{
            board.addMessageToLog("You don't have enough money to buy " + board.getSpace(i).getName() + "!\n");
        }
    }

    @Override
    public void buyEcho(BoardController boardController, int i) {
        Board board = boardController.getBoard();
        Propriety selectedSpace = (Propriety) board.getSpace(i);
        Player currentPlayer = board.getCurrentPlayer();
        board.addActionToLog(" bought "+ selectedSpace.getName() + "!\n");
        currentPlayer.buy(selectedSpace);
    }

    @Override
    public void next(BoardController boardController) {
        BoardControllerServer server = (BoardControllerServer)boardController;
        server.next(0, false);
    }

    @Override
    public void nextEcho(BoardController boardController, int i, boolean doubles) {
        Board board = boardController.getBoard();
        board.endTurn();
        boardController.setState(new ThrowingDiceServer());
    }

    @Override
    public void sell(BoardController boardController, int i) {
        Board board = boardController.getBoard();
        Propriety selectedSpace;
        Player currentPlayer = board.getCurrentPlayer();
        if (!(board.getSpace(i) instanceof Propriety)) {
            boardController.getBoard().addMessageToLog("You can't sell " + board.getSpace(i).getName() + "!\n");
            return;
        }
        else
            selectedSpace = (Propriety) board.getSpace(i);

        if (selectedSpace.getOwner() != currentPlayer){
            boardController.getBoard().addMessageToLog("You don't own " +board.getSpace(i).getName() + "!\n");
        }
        else{
            BoardControllerServer server = (BoardControllerServer)boardController;
            server.sell();
        }
    }

    @Override
    public void sellEcho(BoardController boardController, int i) {
        Board board = boardController.getBoard();
        Propriety selectedSpace = (Propriety) board.getSpace(i);
        Player currentPlayer = board.getCurrentPlayer();
        boardController.getBoard().addActionToLog(" bought "+ selectedSpace.getName() + "!\n");
        currentPlayer.sell(selectedSpace);
    }
}
