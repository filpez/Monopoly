package logic.controller;

/**
 * Created by Filipe on 02/06/2016.
 */
public interface ControllerServerInterface {
    public boolean join(String name, ControllerClientInterface c);
    public void buy();
    public void next(int i, boolean doubles);
    public void sell();
}
