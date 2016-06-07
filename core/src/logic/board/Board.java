package logic.board;

import java.util.ArrayList;

/**
 * Represents the game board.
 *
 * Has a list of players, the 40 spaces, a deck of community cards and a deck of chance cards.
 * Keeps track of the current player.
 */
public class Board {
	private ArrayList<Player> players;
	private Space[] spaces;
	private Deck community;
	private Deck chance;
	private Player currentPlayer;
	private String log;
	private int lastMovement = 0;

	/**
	 * Creates a new board
	 * @param players - players that will participate on the game
	 * @param spaces - array of spaces of the board
     */
	public Board(ArrayList<Player> players, Space[] spaces) {
		super();
		this.players = players;
		this.spaces = spaces;
		this.log = "The game has started!\n";
	}

	/**
	 * Creates a new board
	 * @param players - players that will participate on the game
	 * @param spaces - array of spaces of the board
	 * @param community - deck of community cards
     * @param chance - deck of chance cards
     */
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

	/**
	 * Returns the next player in the array of players
	 * @return next player
     */
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

	/**
	 * Updates log with new action of the player
	 * @param log - message to be added
     */
	public void addActionToLog(String log) {
        setLog(getCurrentPlayer().getName() + log + getLog());
    }

	/**
	 * Updates log with a new message
	 * @param log - message to be added
     */
	public void addMessageToLog(String log) {
        setLog(log + getLog());
    }

	public int getLastMovement() {
		return lastMovement;
	}

	public void setLastMovement(int lastMovement) {
		this.lastMovement = lastMovement;
	}

	/**
	 * Moves current player
	 * @param i - number of spaces to move
	 * @param doubles - true if the value on the two dices was the same
     * @return true if player moved successfully, false if the player is jailed
     */
	public boolean move(int i, boolean doubles){
        Player currentPlayer = getCurrentPlayer();
        setLastMovement(0);

        if (doubles) {			// getting doubles takes the player out of the prison
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

	/**
	 * Applies the effect of current space of current player
	 * @return true if effect was successfully applied, false if otherwise
     */
	public boolean applyCurrentSpaceEffect() {
		int currPos = getCurrentPlayer().getPosition();
		return getSpace(currPos).applyEffect(this, getLastMovement());
	}

	/**
	 * Ends turn of current player.
	 * @return true if there's more that one player still playing, false if there's only one player left
     */
	public boolean endTurn() {
		addActionToLog(" turn has ended!\n");
		Player nextPlayer = nextPlayer();
		if (currentPlayer.isBankrupt()){
			players.remove(currentPlayer);
		}
		setCurrentPlayer(nextPlayer);
		if (players.size() >= 1) {
			addActionToLog(" turn started!\n");
			return true;
		}
		else{
			addActionToLog(" won!\n");
			return false;
		}
	}

	/**
	 * Throws a dice, adding a action to the log
	 * @param value - value of the dice
     */
	public void throwDice(int value) {
		String s = " has throwed the dice for " + value +"!\n";
		addActionToLog(s);
	}

}
