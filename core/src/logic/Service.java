package logic;

import logic.effects.PayRent;

/**
 * Representes an Propriety of type service
 */
public class Service extends Propriety {
	private final static int singleMultiplier = 4;
	private final static int monopolyMultiplier = 10;
	
	public Service(String name, Group group, int price) {
		super(name, group, price);
		this.effect = new PayRent(0);
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
