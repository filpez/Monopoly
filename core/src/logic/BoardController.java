package logic;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardController {

    public static int move(Board board, int i){
        Player currentPlayer = board.getCurrentPlayer();
        if (currentPlayer.getPosition() + i >= 40)
            currentPlayer.receive(2000);
        int nextPosition = (currentPlayer.getPosition() + i) % 40;
        currentPlayer.setPosition(nextPosition);
        return nextPosition;
    }
}
