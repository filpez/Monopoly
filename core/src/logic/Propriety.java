package logic;

public abstract class Propriety extends Space{
	//public enum Group{UtilitiesOne, UtilitiesTwo, Stations, Brown, LightBlue, Pink, Orange, Red, Yellow, Green, DarkBlue};
	
	private int price;
	private boolean mortaged;
	private Player owner;
	private Group group;
	
	public Propriety(String name, Group group, int price) {
		super(name);
		this.price = price;
		this.mortaged = false;
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

	public boolean isMortaged() {
		return mortaged;
	}

	public void setMortaged(boolean mortaged) {
		this.mortaged = mortaged;
	}
	
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

	public boolean canAddHouse() {
		return false;
	}
}
