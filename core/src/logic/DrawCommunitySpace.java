package logic;

import logic.effects.DrawCommunity;

/**
 * Represents a Space that draws cards from Community Deck
 * Associated with DrawCommunity effect
 */
public class DrawCommunitySpace extends Space {
    /**
     * Creates a new DrawCommunitySpace object
     * @param name - name of the space
     */
    public DrawCommunitySpace(String name) {
        super(name);
        this.effect = new DrawCommunity(0);
    }
}