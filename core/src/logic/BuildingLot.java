package logic;

import logic.effects.PayRent;

/**
 * Represents a Building Lot
 *
 * Keeps tracks of the Houses and Hotels built.
 */
public class BuildingLot extends Propriety{
	
	private int[] rents;// = new int[5];
	private int houses;
	
	public BuildingLot(String name, Group group, int price, int[] rents) {
		super(name, group, price);
		this.rents = rents;
		this.houses = 0;
		this.effect = new PayRent(0);
	}

	@Override
	public int getRent(int diceValue) {
		if (getOwner() == null)
			return 0;
		else if (getOwner().hasMonopoly(getGroup()) && houses == 0)
			return rents[0]*2;
		else
			return rents[houses];
	}

	public int[] getRents() {
		return rents;
	}

	public void setRents(int[] rents) {
		this.rents = rents;
	}

	public int getHouses() {
		return houses;
	}

	public void setHouses(int houses) {
		this.houses = houses;
	}

	/**
	 * Adds an house/hotel to the BuildingLot if still possible.
	 */
	public void addHouse(){
		if (houses < 4) {
			houses++;
		}
	}

	/**
	 * Takes away an house/hotel to the BuildingLot if still possible.
	 */
	public void removeHouse(){
		if (houses > 0) {
			houses--;
		}
	}

	public boolean canAddHouse(){
		return houses < 4;
	}

}
