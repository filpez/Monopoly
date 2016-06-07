package logic;

import logic.effects.DrawChance;

/**
 * Represents a Space that draws cards from Chance Deck
 * Associated with DrawChance effect
 */
public class DrawChanceSpace extends Space {
    /**
     * Creates a new DrawChanceSpace object
     * @param name - name of the space
     */
    public DrawChanceSpace(String name) {
        super(name);
        this.effect = new DrawChance(0);
    }
}