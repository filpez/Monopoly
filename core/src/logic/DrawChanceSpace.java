package logic;

import logic.effects.DrawChance;

/**
 * Created by up201404493 on 17-05-2016.
 */
public class DrawChanceSpace extends Space {
    public DrawChanceSpace(String name) {
        super(name);
        this.effect = new DrawChance(0);
    }
}