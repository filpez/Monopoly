package logic;

import java.util.ArrayList;
import java.util.Random;

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
	private int lastMovement = 0;
	
	public Board(ArrayList<Player> players, Space[] spaces) {
		super();
		this.players = players;
		this.spaces = spaces;
		this.log = new String("The game has started!\n");
	}

	public Board(ArrayList<Player> players, Space[] spaces, Deck community, Deck chance) {
		this(players, spaces);
		this.chance = chance;
		//this.chance.shuffle(); TO DO CHECK
		this.community = community;
		//this.community.shuffle();
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

	public void addActionToLog(String log) {
        setLog(getCurrentPlayer().getName() + log + getLog());
    }

	public void addMessageToLog(String log) {
        setLog(log + getLog());
    }

	public int getLastMovement() {
		return lastMovement;
	}

	public void setLastMovement(int lastMovement) {
		this.lastMovement = lastMovement;
	}

	public boolean move(int i, boolean doubles){
        Player currentPlayer = getCurrentPlayer();
        setLastMovement(0);

        if (doubles) {
            currentPlayer.setRemainingArrestedTurns(0);
           addActionToLog(" got doubles!\n");
        }

        if (currentPlayer.isArrested()) {
            currentPlayer.setRemainingArrestedTurns(currentPlayer.getRemainingArrestedTurns() - 1);
            addActionToLog(" is jailed!\n");
            return false;
        }
        else{
            if (currentPlayer.getPosition() + i >= 40)
                currentPlayer.receive(2000);
            int nextPosition = (currentPlayer.getPosition() + i) % 40;
            if(nextPosition < 0)
                nextPosition = 40 + nextPosition;
            setLastMovement(i);
            currentPlayer.setPosition(nextPosition);
           addActionToLog(" moved to " + getSpace(nextPosition).getName() + ".\n");
            return true;
        }
    }

	public boolean applyCurrentSpaceEffect() {
		int currPos = getCurrentPlayer().getPosition();
		return getSpace(currPos).applyEffect(this, getLastMovement());
	}

	public void endTurn() {
		addActionToLog(" turn has ended!\n");
		setCurrentPlayer(nextPlayer());
		addActionToLog(" turn started!\n");
	}

	public int throwDice() {
		Random rand = new Random();
		int value = rand.nextInt(6) + 1;
		String s = new String(" has throwed the dice for " + value +"!\n");
		addActionToLog(s);
		return value;
	}

	public void throwDice(int value) {
		String s = new String(" has throwed the dice for " + value +"!\n");
		addActionToLog(s);
	}

}
