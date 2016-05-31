package logic.states;

import logic.Board;
import logic.BoardController;
import logic.Propriety;
import logic.Player;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ApplyingActions implements State{
    @Override
    public void buy(BoardController boardController, int i) {
        Board board = boardController.getBoard();
        Propriety selectedSpace;
        Player currentPlayer = board.getCurrentPlayer();
        if (!(board.getSpace(i) instanceof Propriety)) {
            boardController.getBoard().addMessageToLog("You can't buy " + board.getSpace(i).getName() + "!\n");
            return;
        }
        else if(currentPlayer.getPosition() != i){
            boardController.getBoard().addMessageToLog("You aren't at " + board.getSpace(i).getName() + "!\n");
            return;
        }
        else
            selectedSpace = (Propriety) board.getSpace(i);

        if (selectedSpace.getOwner() != null){
            boardController.getBoard().addMessageToLog(board.getSpace(i).getName() + " already has an owner!\n");
        }
        else if (currentPlayer.canBuy(selectedSpace)){
            boardController.getBoard().addActionToLog(" bought "+ selectedSpace.getName() + "!\n");
            currentPlayer.buy(selectedSpace);
        }
        else{
            boardController.getBoard().addMessageToLog("You don't have enough money to buy " + board.getSpace(i).getName() + "!\n");
        }
    }

    @Override
    public void next(BoardController boardController) {
        Board board = boardController.getBoard();
        int currPos = board.getCurrentPlayer().getPosition();
        if(board.getSpace(currPos).applyEffect(board, boardController.getBoard().getLastMovement()))
            boardController.setState(new WaitingNextTurn());
    }
}
