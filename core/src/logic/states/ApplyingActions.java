package logic.states;

import java.util.Random;

import logic.Board;
import logic.BoardController;
import logic.BuildingLot;
import logic.Propriety;
import logic.Space;
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
            boardController.addMessageToLog("You can't buy " + board.getSpace(i).getName() + "!\n");
            return;
        }
        else if(currentPlayer.getPosition() != i){
            boardController.addMessageToLog("You aren't at " + board.getSpace(i).getName() + "!\n");
            return;
        }
        else
            selectedSpace = (Propriety) board.getSpace(i);

        if (selectedSpace.getOwner() != null){
            boardController.addMessageToLog(board.getSpace(i).getName() + " already has an owner!\n");
        }
        else if (currentPlayer.canBuy(selectedSpace)){
            boardController.addActionToLog(" bought "+ selectedSpace.getName() + "!\n");
            currentPlayer.buy(selectedSpace);
        }
        else{
            boardController.addMessageToLog("You don't have enough money to buy " + board.getSpace(i).getName() + "!\n");
        }
    }

    @Override
    public void next(BoardController boardController) {
        Board board = boardController.getBoard();
        int currPos = board.getCurrentPlayer().getPosition();
        if(board.getSpace(currPos).applyEffect(board, boardController.getLastMovement()))
            boardController.setState(new WaitingNextTurn());
    }
}
