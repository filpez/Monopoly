package logic;

/**
 * Representes an Propriety of type service
 */
public class Service extends Propriety {
	private static int singleMultiplier;
	private static int monopolyMultiplier;
	
	public Service(String name, Group group, int price) {
		super(name, group, price);
	}

	public static int getSingleMultiplier() {
		return singleMultiplier;
	}

	public static void setSingleMultiplier(int singleMultiplier) {
		Service.singleMultiplier = singleMultiplier;
	}

	public static int getMonopolyMultiplier() {
		return monopolyMultiplier;
	}

	public static void setMonopolyMultiplier(int monopolyMultiplier) {
		Service.monopolyMultiplier = monopolyMultiplier;
	}

	@Override
	public int getRent(int diceValue) {
		if (getOwner() == null)
			return 0;
		else if (getOwner().hasMonopoly(getGroup()))
			return diceValue * monopolyMultiplier;
		else
			return diceValue * singleMultiplier;
	}

}
