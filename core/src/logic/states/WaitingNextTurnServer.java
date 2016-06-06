package logic.states;

import logic.Board;
import logic.BuildingLot;
import logic.Player;
import logic.Propriety;
import logic.controller.BoardController;
import logic.controller.BoardControllerServer;

/**
 * Created by Filipe on 17/05/2016.
 */
public class WaitingNextTurnServer implements State {
    public String getNextActionName(){
        return "End Turn";
    }

    @Override
    public void buy(BoardController boardController, int i) {
        Board board = boardController.getBoard();
        Player currentPlayer = board.getCurrentPlayer();

        //Check if it is a Propriety
        if (!(board.getSpace(i) instanceof Propriety)) {
            board.addMessageToLog("You can't buy " + board.getSpace(i).getName() + "!\n");
            return;
        }
        Propriety selectedSpace = (Propriety) board.getSpace(i);

        //Owner == null
        if (selectedSpace.getOwner() == null){
            if (currentPlayer.getPosition() != i){
                board.addMessageToLog("You aren't at " + board.getSpace(i).getName() + "!\n");
            }
            else if (currentPlayer.canBuy(selectedSpace)){
                BoardControllerServer server = (BoardControllerServer)boardController;
                server.buy();
            }
            else{
                board.addMessageToLog("You don't have enough money to buy " + board.getSpace(i).getName() + "!\n");
            }
        }
        //Owner is different player
        else if (selectedSpace.getOwner() != currentPlayer ){
            board.addMessageToLog(board.getSpace(i).getName() + " already has an owner!\n");
        }
        //Owner is player
        else{
            if (selectedSpace instanceof BuildingLot && currentPlayer.canBuild((BuildingLot)selectedSpace)) {
                BoardControllerServer server = (BoardControllerServer)boardController;
                server.buy();
            }
            else {
                board.addMessageToLog("You can't build at " + board.getSpace(i).getName() + "!\n");
            }
        }
    }

    @Override
    public void buyEcho(BoardController boardController, int i) {
        Board board = boardController.getBoard();
        Propriety selectedSpace = (Propriety) board.getSpace(i);
        Player currentPlayer = board.getCurrentPlayer();
        if (selectedSpace.getOwner() == currentPlayer){
            board.addActionToLog(" built at " + selectedSpace.getName() + "!\n");
            currentPlayer.build((BuildingLot)selectedSpace);
        }
        else {
            board.addActionToLog(" bought " + selectedSpace.getName() + "!\n");
            currentPlayer.buy(selectedSpace);
        }
    }

    @Override
    public void next(BoardController boardController) {
        if (boardController.getBoard().getCurrentPlayer().getFunds() < 0 && !boardController.getBoard().getCurrentPlayer().isBankrupt())
            boardController.getBoard().addMessageToLog("You must sell something!");
        else {
            BoardControllerServer server = (BoardControllerServer)boardController;
            server.next(0, 0);
        }
    }

    @Override
    public void nextEcho(BoardController boardController, int a, int b) {
        Board board = boardController.getBoard();
        if (board.endTurn())
            boardController.setState(new ThrowingDiceClient());
        else
            boardController.setState(new DisplayingResults());
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
        if (selectedSpace instanceof BuildingLot && ((BuildingLot)selectedSpace).getHouses() > 0 ){
            board.addActionToLog(" demolished an house at " + selectedSpace.getName() + "!\n");
            currentPlayer.demolish((BuildingLot)selectedSpace);
        }
        else {
            board.addActionToLog(" sold " + selectedSpace.getName() + "!\n");
            currentPlayer.sell(selectedSpace);
        }
    }
}
