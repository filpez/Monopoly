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
	
	public Board(ArrayList<Player> players, Space[] spaces) {
		super();
		this.players = players;
		this.spaces = spaces;
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
	
}
