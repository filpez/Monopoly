package logic;

import logic.effects.GoToJail;

/**
 * Created by Filipe on 17/05/2016.
 */
public class GoToJailSpace extends Space {
    public GoToJailSpace(String name) {
        super(name);
        this.effect = new GoToJail(0);
    }
}
