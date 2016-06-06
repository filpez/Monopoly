package logic;

import logic.effects.PayRent;

/**
 * Represents the stations
 */
public class Stations extends Propriety {
	private static int baseCost;
	
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

	@Override
	public int getRent(int diceValue) {
		if (getOwner() == null)
			return 0;
		int n = getOwner().getNumberOfProprieties(getGroup()) - 1;
		int cost = baseCost;
		while (n > 0){
			cost *= 2;
			n--;
		}
		return cost;
	}

}
