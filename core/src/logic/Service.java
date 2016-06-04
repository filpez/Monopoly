package logic;

import logic.effects.PayRent;

/**
 * Representes an Propriety of type service
 */
public class Service extends Propriety {
	private static int singleMultiplier;
	private static int monopolyMultiplier;
	
	public Service(String name, Group group, int price) {
		super(name, group, price);
		this.effect = new PayRent(0);
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

	@Override
	public boolean applyEffect(Board board, int diceValue) {
		effect.setValue(diceValue);
		return super.applyEffect(board, diceValue);
	}
}
