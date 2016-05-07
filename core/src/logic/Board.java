package logic;

import java.util.ArrayList;

public class Board {
	private ArrayList<Player> player;
	private Space[] spaces;
	private Player currentPlayer;
	
	public Board(ArrayList<Player> player, Space[] spaces) {
		super();
		this.player = player;
		this.spaces = spaces;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public ArrayList<Player> getPlayer() {
		return player;
	}

	public void setPlayer(ArrayList<Player> player) {
		this.player = player;
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
