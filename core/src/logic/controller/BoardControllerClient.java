package logic.controller;

import logic.Board;
import logic.states.ThrowingDice;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardControllerClient extends BoardController implements ControllerClientInterface {
    private ControllerServerInterface proxy;

    public BoardControllerClient(Board board) {
        super();
        this.setBoard(board);
        this.state = new ThrowingDice();
    }

    @Override
    public void buy() {
        state.buyEcho(this, board.getCurrentPlayer().getPosition());
    }

    @Override
    public void next(int a, int b) {
        state.nextEcho(this, a, b);
    }

    @Override
    public void sell() {
        state.sellEcho(this, board.getCurrentPlayer().getPosition());
    }


    public ControllerServerInterface getProxy() {
        return proxy;
    }

    public void setProxy(ControllerServerInterface proxy) {
        this.proxy = proxy;
    }
}
