package logic.board;

import logic.effects.GoToJail;

/**
 * Represents a Space that sends the players that land on this space to jail
 * Associated with GoToJail effect
 */
public class GoToJailSpace extends Space {
    /**
     * Creates a new GoToJailSpace object
     * @param name - name of the space
     */
    public GoToJailSpace(String name) {
        super(name);
        this.effect = new GoToJail(0);
    }
}
