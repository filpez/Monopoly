package logic;

import java.util.ArrayList;

/**
 * Represents a Player.
 * Keeps track of the player's funds, owned proprieties and its position on the board.
 * Also keeps track of whether or not the player was imprisoned and if yes, how much turns ago the player was imprisoned.
 */
public class Player {
	private String name;
	private int funds;
	private ArrayList<Propriety> proprieties;
	private int position;
	private int remainingArrestedTurns;

	/**
	 * Creates a new player
	 * @param name - name of the player
     */
	public Player(String name) {
		super();
		this.name = name;
		this.funds = 1500;		// initial funds of player
		this.proprieties = new ArrayList<Propriety>();
		this.position = 0;
		this.remainingArrestedTurns = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFunds() {
		return funds;
	}

	public void setFunds(int funds) {
		this.funds = funds;
	}

	/**
	 * Determines whether or not the player is imprisoned
	 * @return true if the player was arrested, false if not
     */
	public boolean isArrested() {
		return remainingArrestedTurns > 0;
	}

	public int getRemainingArrestedTurns() {
		return remainingArrestedTurns;
	}

	public void setRemainingArrestedTurns(int remainingArrestedTurns) {
		this.remainingArrestedTurns = remainingArrestedTurns;
	}

	/**
	 * Takes away an certain amount of money from funds
	 * @param funds - amount paid
	 */
	public void pay(int funds) {
		this.funds -= funds;
	}

	/**
	 * Adds an certain amount of money from funds
	 * @param funds - amount received
	 */
	public void receive(int funds) {
		this.funds += funds;
	}

	public ArrayList<Propriety> getProprieties() {
		return proprieties;
	}

	public void setProprieties(ArrayList<Propriety> proprieties) {
		this.proprieties = proprieties;
	}

	/**
	 * Checks if the player can buy a given propriety
	 * @param propriety - propriety to be checked
	 * @return true if the price of the propriety is less or equal than players' funds
     */
	public boolean canBuy(Propriety propriety){
		return (propriety.getPrice() <= funds);
	}

	/**
	 * Buy an already owned propriety
	 * @param propriety - propriety to be bought
	 * @param price - price of the propriety
	 * @param player - owner of given propriety
     */
	public void buy(Propriety propriety, int price, Player player){
		propriety.setOwner(this);
		player.proprieties.remove(propriety);
		this.proprieties.add(propriety);
		
		this.pay(price);
		player.receive(price);
	}

	/**
	 * Buy a propriety
	 * @param propriety - propriety to be bought
     */
	public void buy(Propriety propriety){
		int price = propriety.getPrice();
		propriety.setOwner(this);
		this.proprieties.add(propriety);
		this.pay(price);
	}

	/**
	 * Sell a propriety
	 * @param propriety - propriety to be sold
     */
	public void sell(Propriety propriety){
		int price = propriety.getPrice();
		propriety.setOwner(null);
		this.proprieties.remove(propriety);
		this.receive(price/2);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Checks if players own all proprieties in a group
	 * @param group - group of proprieties
	 * @return true if the player owns all proprieties in a group, no if otherwise
     */
	public boolean hasMonopoly(Group group) {
		for (Propriety propriety: group.getProprieties())
			if (propriety.getOwner() != this)
				return false;
		return true;
	}

	/**
	 * Returns the number of proprieties that belong to a given group and that are owned by the player
	 * @param group - given group
	 * @return number of proprieties that belong to group and that are owned by the player
     */
	public int getNumberOfProprieties(Group group) {
		int n = 0;
		for (Propriety propriety: group.getProprieties())
			if (propriety.getOwner() == this)
				n++;
		return n;
	}

	/**
	 * Checks if the player is bankrupt
	 * @return true if the value of its funds, proprieties and houses is less than zero, false if otherwise
     */
	public boolean isBankrupt() {
		int totalMoney = funds;
		for (Propriety propriety: getProprieties()) {
			totalMoney += propriety.getPrice() / 2;		// each propriety can be sold for half of its original price
			if (propriety instanceof BuildingLot){
				totalMoney += ((BuildingLot)propriety).getHouses() * propriety.getGroup().getHouseValue()/2;
				// each house can only be sold for half of its original cost
			}
		}
		return totalMoney < 0;
	}

	/**
	 * Checks if player can build a house in a building lot
 	 * @param buildingLot - building lot to be checked
	 * @return true if house can be built, false if otherwise
     */
	public boolean canBuild(BuildingLot buildingLot){
		return (hasMonopoly(buildingLot.getGroup()) &&		// if player owns all the building lots in the group of buildingLot
				buildingLot.getGroup().getHouseValue() <= funds &&	// if player has enough money
				buildingLot.canAddHouse()					// if the maximum number of houses hasn't been achieved
				);
	}

	/**
	 * Builds a new house in a given building lot
	 * @param buildingLot - building lot in which a new house will be constructed
     */
	public void build(BuildingLot buildingLot){
		int price = buildingLot.getGroup().getHouseValue();
		buildingLot.addHouse();
		this.pay(price);
	}

	/**
	 * Removes a house in a given building lot
	 * @param buildingLot - building lot that will have one of its houses demolished
     */
	public void demolish(BuildingLot buildingLot){
		int price = buildingLot.getGroup().getHouseValue();
		buildingLot.removeHouse();
		this.receive(price/2);
	}
	
	
}
