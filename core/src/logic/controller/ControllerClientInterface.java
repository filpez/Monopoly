package logic.controller;

import java.util.ArrayList;

/**
 * Created by Filipe on 02/06/2016.
 */
public interface ControllerClientInterface {
    public void buy();
    public void next(int a, int b);
    public void sell();

    public void start(ArrayList<String> playerNames, int i);
}
