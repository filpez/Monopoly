package logic;

/**
 * Represents the stations
 */
public class Stations extends Propriety {
	private static int baseCost;
	
	public Stations(String name, Group group, int price) {
		super(name, group, price);
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
		}
		return cost;
	}

}
