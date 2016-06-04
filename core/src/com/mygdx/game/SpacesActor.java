package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import logic.DrawChanceSpace;
import logic.DrawCommunitySpace;
import logic.Service;
import logic.controller.BoardController;
import logic.BuildingLot;
import logic.GoToJailSpace;
import logic.Space;
import logic.Stations;
import logic.TransactionSpace;

/**
 * Created by Filipe on 07/05/2016.
 */
public class SpacesActor extends Group{
    private BoardController controller;
    private int currSpaceIndex = 0;
    Label spaceText;
    Label fundsText;

    public SpacesActor(BoardController controller){
        float min = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setBounds(getX(), getY(), min, min);

        setColor(Color.BLACK);

        this.controller = controller;

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        spaceText = new Label("", skin);
        spaceText.setWrap(true);
        spaceText.setFontScale(2.0f);
        spaceText.setAlignment(Align.top);
        spaceText.setBounds(getWidth()/6, getHeight()/6, getWidth()*4/6, getHeight()*5/6);

        fundsText = new Label("", skin);
        fundsText.setWrap(true);
        fundsText.setFontScale(2.0f);
        fundsText.setAlignment(Align.top);
        fundsText.setBounds(getWidth()/3,0, getWidth()/3, getHeight()/6);

        TextButton NextButton = new TextButton("N\ne\nx\nt", skin);
        NextButton.setBounds(getWidth()*5/6,getHeight()/6, getWidth()/6, getHeight()*5/6);
        NextButton.getLabel().setFontScale(3.0f);
        NextButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                currSpaceIndex =  (currSpaceIndex + 1) % 40;
                updateText();
            }
        });

        TextButton PrevButton = new TextButton("P\nr\ne\nv\ni\no\nu\ns", skin);
        PrevButton.setBounds(0,getHeight()/6, getWidth()/6, getHeight()*5/6);
        PrevButton.getLabel().setFontScale(3.0f);
        PrevButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (currSpaceIndex == 0)
                    currSpaceIndex = 39;
                else
                    currSpaceIndex--;
                updateText();
            }
        });

        TextButton BuyButton = new TextButton("Buy", skin);
        BuyButton.setBounds(getWidth()*2/3,0, getWidth()/3, getHeight()/6);
        BuyButton.getLabel().setFontScale(3.0f);
        BuyButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                BoardController controller = SpacesActor.this.controller;
                updateFunds();
                if (controller.isPlayer())
                    controller.getState().buy(controller, currSpaceIndex);
            }
        });

        TextButton SellButton = new TextButton("Sell", skin);
        SellButton.setBounds(0,0, getWidth()/3, getHeight()/6);
        SellButton.getLabel().setFontScale(3.0f);
        SellButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                BoardController controller = SpacesActor.this.controller;
                updateFunds();
                if (controller.isPlayer())
                    controller.getState().sell(controller, currSpaceIndex);
            }
        });


        addActor(spaceText);
        addActor(fundsText);
        addActor(NextButton);
        addActor(PrevButton);
        addActor(BuyButton);
        addActor(SellButton);
    }

    public void setCurrSpaceIndex(int currSpaceIndex) {
        this.currSpaceIndex = currSpaceIndex;
        updateText();
    }

    public void updateFunds(){
        int funds = controller.getPlayer().getFunds();
        String s = "Funds:\n" + Integer.toString(funds);
        fundsText.setText(s);
    }

    public void updateText(){
        Space currSpace = controller.getBoard().getSpace(currSpaceIndex);
        String s;
        if (currSpace instanceof TransactionSpace)
            s = TransactionSpaceText((TransactionSpace)currSpace);
        else if (currSpace instanceof GoToJailSpace)
            s = GoToJailSpaceText();
        else if (currSpace instanceof DrawCommunitySpace)
            s = CommunityText();
        else if (currSpace instanceof DrawChanceSpace)
            s = ChangeText();
        else if (currSpace instanceof BuildingLot)
            s = BuildingLotText((BuildingLot)currSpace);
        else if (currSpace instanceof Stations)
            s = StationsText((Stations)currSpace);
        else if (currSpace instanceof Service)
            s = ServicesText((Service)currSpace);
        else
            s = "";
        spaceText.setText(s);

    }

    private String BuildingLotText(BuildingLot space) {
        String s;
        s =space.getName() + "\n";
        s += "Color: " + space.getGroup().getName() + "\n" + "\n";
        s += "Rent..." + "\n";
        s += "... without houses: " + space.getRents()[0] + "\n";
        s += "... with a house:   " + space.getRents()[1] + "\n";
        s += "... with 2 houses:  " + space.getRents()[2] + "\n";
        s += "... with 3 houses:  " + space.getRents()[3] + "\n";
        s += "... with an hotel:  " + space.getRents()[4] + "\n";
        s += "The rent is double on an empty lot\nif you have all Proprieties of that color\n\n";
        s += "Price: " + space.getPrice() + "\n";

        return s;
    }

    private String StationsText(Stations space) {
        String s;
        s =space.getName() + "\n";
        s += "Type: " + space.getGroup().getName() + "\n" + "\n";
        s += "Rent..." + "\n";
        s += "... with 1 station: " + space.getBaseCost() + "\n";
        s += "... with 2 stations:   " + space.getBaseCost()*2 + "\n";
        s += "... with 3 stations:  " + space.getBaseCost()*4 + "\n";
        s += "... with 4 stations:  " + space.getBaseCost()*8 + "\n";
        s += "Price: " + space.getPrice() + "\n";

        return s;
    }

    private String ServicesText(Service space) {
        String s = space.getName() + "\n";;
        s += "Type: " + space.getGroup().getName() + "\n" + "\n";
        s += "Rent..." + "\n";
        s += "... with 1 service: 4 times the value of dices" + "\n";
        s += "... with 1 service: 10 times the value of dices" + "\n";
        s += "Price: " + space.getPrice() + "\n";
        return s;
    }

    private String TransactionSpaceText(TransactionSpace space) {
        String s;
        s =space.getName() + "\n\n";
        if (space.getEffect().getValue() > 0)
            s += "When you land on this space,\nyou pay " + space.getEffect().getValue() + " to the Bank.\n";
        else if (space.getEffect().getValue() < 0)
            s += "When you land on this space,\nyou receive " + (-space.getEffect().getValue()) + " from the Bank.\n";
        return s;
    }

    private String GoToJailSpaceText() {
        String s;
        s = "When you land on this space,\nyou are directly sent to jail\nwithout passing through the start\nand without collecting any money";
        return s;
    }

    private String CommunityText() {
        String s;
        s = "When you land on this space,\nyou pick a card from Community";
        return s;
    }

    private String ChangeText() {
        String s;
        s = "When you land on this space,\nyou pick a card from Change";
        return s;
    }

}
