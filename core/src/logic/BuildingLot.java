package logic;

import logic.effects.PayRent;

/**
 * Represents a Building Lot
 *
 * Keeps tracks of the Houses and Hotels built.
 */
public class BuildingLot extends Propriety{
	private static int remainingHouses;
	private static int remainingHotels;
	
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

	public static int getRemainingHouses() {
		return remainingHouses;
	}

	public static void setRemainingHouses(int remainingHouses) {
		BuildingLot.remainingHouses = remainingHouses;
	}

	public static int getRemainingHotels() {
		return remainingHotels;
	}

	public static void setRemainingHotels(int remainingHotels) {
		BuildingLot.remainingHotels = remainingHotels;
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
	 * Updates the remainingHouses and remainingHotels.
	 */
	public void addHouse(){
		if (houses < 4)
			remainingHouses--;
		else
			remainingHotels--;
		houses++;
	}

	/**
	 * Takes away an house/hotel to the BuildingLot if still possible.
	 * Updates the remainingHouses and remainingHotels.
	 */
	public void removeHouse(){
		if (houses < 5)
			remainingHouses++;
		else
			remainingHotels++;
		houses--;
	}


}
