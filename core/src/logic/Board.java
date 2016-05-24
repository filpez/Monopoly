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
	private Deck community;
	private Deck chance;
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

	public Board(ArrayList<Player> players, Space[] spaces, Deck community, Deck chance) {
		this(players, spaces);
		this.chance = chance;
		this.chance.shuffle();
		this.community = community;
		this.community.shuffle();
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

	public Player nextPlayer(){
		for (int i = 0; i < players.size(); i++){
			if (currentPlayer == players.get(i))
				return players.get((i + 1) %  players.size());
		}
		return null;
	}

	public Deck getCommunity() {
		return community;
	}

	public void setCommunity(Deck community) {
		this.community = community;
	}

	public Deck getChance() {
		return chance;
	}

	public void setChance(Deck chance) {
		this.chance = chance;
	}
}
