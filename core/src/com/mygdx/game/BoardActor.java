package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import logic.BoardController;
import logic.Player;

/**
 * Created by Filipe on 07/05/2016.
 */
public class BoardActor extends Actor{
    private logic.Board board;
    private Sprite boardBackground;
    private Sprite[] playerSprites;
    private float pSize;

    public BoardActor(logic.Board board){
        this.boardBackground = new Sprite(new Texture("img/board.jpg"));
        float min = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.boardBackground.setBounds(0, 0, min, min);
        setOrigin(min/2, min/2);
        setBounds(0, 0, min, min);
        this.pSize = min/26;

        this.playerSprites = new Sprite[4];
        this.playerSprites[0] =  new Sprite(new Texture("img/RedPlayer.png"));
        this.playerSprites[1] =  new Sprite(new Texture("img/BluePlayer.png"));
        this.playerSprites[2] =  new Sprite(new Texture("img/GreenPlayer.png"));
        this.playerSprites[3] =  new Sprite(new Texture("img/YellowPlayer.png"));

        for (Sprite s: playerSprites){
            s.setBounds(0,0, pSize, pSize);
        }




        Gdx.app.log("BoardActor", Float.toString(min));
        this.board = board;
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                BoardController.move(BoardActor.this.board, 1);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        boardBackground.draw(batch);
        for (int i = 0; i < board.getPlayers().size(); i++){
            Player p = board.getPlayers().get(i);
            drawPlayer(batch, p, i);
        }
        int currentPosition = board.getCurrentPlayer().getPosition();
        setRotation(90 * (currentPosition/10));


    }

    private void  drawPlayer(Batch batch, Player p, int i){
        Gdx.app.log("BoardActor", Integer.toString(BoardActor.this.board.getCurrentPlayer().getPosition()));
        float boardSize = getHeight();
        float s = pSize*2 + pSize*2*(p.getPosition()%10) +  pSize*(i%2);
        float t = pSize*(i/2);;
        if (p.isArrested())
            t += pSize*2;

        if (p.getPosition() < 10)
            this.playerSprites[i].setPosition(boardSize - s - pSize, t);
        else if (p.getPosition() < 20)
            this.playerSprites[i].setPosition(t, s);
        else if (p.getPosition() < 30)
            this.playerSprites[i].setPosition(s,boardSize -  t - pSize);
        else
            this.playerSprites[i].setPosition(boardSize - t - pSize, boardSize -   s);
        this.playerSprites[i].draw(batch);
    }

}
