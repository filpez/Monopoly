package logic;

import logic.effects.PayRent;

/**
 * Represents the stations of the board.
 * A station is a propriety so it can be bought or sold.
 * There are four stations on the standard board.
 */
public class Stations extends Propriety {
	private static int baseCost;

	/**
	 * Creates a new station
	 * @param name - name of the station
	 * @param group - group to which the station belongs ("Station")
	 * @param price - price of the station (it will also help determining the baseCost of the rents)
     */
	public Stations(String name, Group group, int price) {
		super(name, group, price);
		this.effect = new PayRent(0);
		this.baseCost = price/8;
	}
	
	public static int getBaseCost() {
		return baseCost;
	}

	public static void setBaseCost(int baseCost) {
		Stations.baseCost = baseCost;
	}

	/**
	 * Determines the rent value. If the station has no owner, no rent will be paid.
	 * @param diceValue - the value on dices
	 * @return rent (how much the non-owner players will have to pay on the station)
     */
	@Override
	public int getRent(int diceValue) {
		if (getOwner() == null)
			return 0;
		int n = getOwner().getNumberOfProprieties(getGroup()) - 1;
		int cost = baseCost;
		while (n > 0){		// if the owner of this station owns more than one station
			cost *= 2;		// the rent cost will multiply by two every additional station owned
			n--;
		}
		return cost;
	}

}
