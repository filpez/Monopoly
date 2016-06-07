package logic;

/**
 * Represents an purchasable Space.
 * Keeps track of the owner and the group of Proprieties it belongs to.
 * BuildingLot, Service and Stations are proprieties.
 */
public abstract class Property extends Space{
	//public enum Group{UtilitiesOne, UtilitiesTwo, Stations, Brown, LightBlue, Pink, Orange, Red, Yellow, Green, DarkBlue};
	
	private int price;
	private boolean mortgaged;
	private Player owner;
	private Group group;

	/**
	 *
	 * @param name - name of the propriety
	 * @param group - group to which the propriety belongs
	 * @param price - price of the propriety
     */
	public Property(String name, Group group, int price) {
		super(name);
		this.price = price;
		this.mortgaged = false;
		this.owner = null;
		this.group = group;
		this.group.addPropriety(this);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isMortgaged() {
		return mortgaged;
	}

	public void setMortgaged(boolean mortgaged) {
		this.mortgaged = mortgaged;
	}

	/**
	 * Calculates the rent of the purchasable spaces
	 * @param diceValue - the value on dices
	 * @return the rent, calculated differently by different types of proprieties
	 */
	public abstract int getRent(int diceValue);

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


}
