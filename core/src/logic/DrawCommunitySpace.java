package logic;

import logic.effects.DrawCommunity;

/**
 * Created by up201404493 on 17-05-2016.
 */
public class DrawCommunitySpace extends Space {
    public DrawCommunitySpace(String name) {
        super(name);
        this.effect = new DrawCommunity(0);
    }
}