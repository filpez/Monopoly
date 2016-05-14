package logic;

import java.util.ArrayList;

/**
 * Represents the gameboard.
 *
 * Has a list of players, the 40 spaces and keeps track of the current player.
 */
public class Board {
	private ArrayList<Player> players;
	private Space[] spaces;
	private Player currentPlayer;
	private String log;
	private BoardController controller;
	
	public Board(ArrayList<Player> players, Space[] spaces) {
		super();
		this.players = players;
		this.spaces = spaces;
		this.log = new String("The game has started!\n");
		this.controller = new BoardController(this);
	}


	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Space[] getSpaces() {
		return spaces;
	}

	public void setSpaces(Space[] spaces) {
		this.spaces = spaces;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public Space getSpace(int i){
		return spaces[i];
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public BoardController getController() {
		return controller;
	}

	public void setController(BoardController controller) {
		this.controller = controller;
	}
}
