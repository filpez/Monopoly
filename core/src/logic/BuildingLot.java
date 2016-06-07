package logic;

import logic.effects.PayRent;

/**
 * Represents a Building Lot
 * A building lot is a propriety so it can be bought or sold.
 * Keeps track of the houses built.
 */
public class BuildingLot extends Property {
	
	private int[] rents;// = new int[5];
	private int houses;

	/**
	 * Creates a new building lot
	 * @param name - name of the building lot
	 * @param group - group to which the building lot belongs
	 * @param price - price of the building lot
     * @param rents - rents of the building lot [base rent, 1 house, 2 houses, 3 houses, 4 houses]
     */
	public BuildingLot(String name, Group group, int price, int[] rents) {
		super(name, group, price);
		this.rents = rents;
		this.houses = 0;
		this.effect = new PayRent(0);
	}

	/**
	 * Determines the rent value. If the building lot has no owner, no rent will be paid.
	 * @param diceValue - the value on dices
	 * @return rent (how much the non-owner players will have to pay on the station)
     */
	@Override
	public int getRent(int diceValue) {
		if (getOwner() == null)
			return 0;
		else if (getOwner().hasMonopoly(getGroup()) && houses == 0)
			return rents[0]*2;
		else
			return rents[houses];	// ACHO QUE É rents[houses+1]
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
	 * Adds an house to the BuildingLot if still possible.
	 */
	public void addHouse(){
		if (houses < 4) {		// ACHO QUE E (houses <= 4)
			houses++;
		}
	}

	/**
	 * Takes away an house to the BuildingLot if still possible.
	 */
	public void removeHouse(){
		if (houses > 0) {
			houses--;
		}
	}

	/**
	 * Checks if it's possible to add any more houses
	 * @return true if less there are less than four houses, false if otherwise
     */
	public boolean canAddHouse(){
		return houses < 4;		// ACHO QUE E (houses < 6)
	}

}
