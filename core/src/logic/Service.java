package logic;

import logic.effects.PayRent;

/**
 * Represents an Property of type Service.
 * Since it is a propriety, it can be bought or sold.
 * There are only two services on the Board (Electric Company and Water Works)
 */
public class Service extends Property {
	private final static int singleMultiplier = 4;
	private final static int monopolyMultiplier = 10;

	/**
	 * Creates a new service.
	 * @param name - name of the service
	 * @param group - group to which the service belongs ("Service")
	 * @param price - price of the station
     */
	public Service(String name, Group group, int price) {
		super(name, group, price);
		this.effect = new PayRent(0);
	}

	/**
	 * Determines the rent value. If the service has no owner, no rent will be paid.
	 * @param diceValue - the value on dices
	 * @return rent (how much the non-owner players will have to pay on the station)
     */
	@Override
	public int getRent(int diceValue) {
		if (getOwner() == null)
			return 0;
		else if (getOwner().hasMonopoly(getGroup()))	// if the players owns all services on the board
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
