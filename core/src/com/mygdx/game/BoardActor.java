package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by Filipe on 07/05/2016.
 */
public class BoardActor extends Actor{
    private logic.Board board;
    private Sprite boardBackground;

    public BoardActor(logic.Board board){
        this.boardBackground = new Sprite(new Texture("img/board.jpg"));
        float min = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.boardBackground.setBounds(0, 0, min, min);
        setBounds(0, 0, min, min);

        Gdx.app.log("BoardActor", Float.toString(min));
        this.board = board;
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("BoardActor", Integer.toString(BoardActor.this.board.getCurrentPlayer().getPosition()));
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                BoardActor.this.board.getCurrentPlayer().setPosition(BoardActor.this.board.getCurrentPlayer().getPosition()+5);
                Gdx.app.log("BoardActor", Integer.toString(BoardActor.this.board.getCurrentPlayer().getPosition()));
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        int currentPosition = board.getCurrentPlayer().getPosition();
        boardBackground.setRotation(90 * (currentPosition/10));

        boardBackground.draw(batch);
    }

}
