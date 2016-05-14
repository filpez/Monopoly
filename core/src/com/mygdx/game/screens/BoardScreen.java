package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.BoardActor;
import com.mygdx.game.MonopolyGame;

/**
 * Created by Filipe on 07/05/2016.
 */
public class BoardScreen implements Screen {
    private final MonopolyGame game;

    private ScrollPane LogPanel;
    private String Log;
    private Stage stage;
 int i = 0;
    public BoardScreen(Game game) {
        this.game = (MonopolyGame)game;
        create();
    }

    private void create(){
        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);



        BoardActor actor = new BoardActor(game.board);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));



        TextButton SpacesButton = new TextButton("Spaces", skin);
        SpacesButton.setPosition(actor.getWidth(),0);
        SpacesButton.setBounds(actor.getWidth(),0, (stage.getWidth()-actor.getWidth())/2, actor.getHeight()/5);
        SpacesButton.getLabel().setFontScale(5.0f);

        SpacesButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                i++;
                Log = i + "\n" + Log ;
                Label l = (Label)LogPanel.getWidget();
                l.setText(Log);
            }
        });

        TextButton NextButton = new TextButton("Next", skin);
        NextButton.setPosition(actor.getWidth(),0);
        NextButton.setBounds(actor.getWidth() + SpacesButton.getWidth(),0, (stage.getWidth()-actor.getWidth())/2, actor.getHeight()/5);
        NextButton.getLabel().setFontScale(5.0f);

        Log = new String("The game has started!\n");
        Label LogLabel = new Label(Log, skin);
        LogLabel.setFontScale(2.0f);
        LogLabel.setAlignment(Align.bottomLeft);
        LogPanel = new ScrollPane(LogLabel);
        LogPanel.setBounds(actor.getWidth() ,SpacesButton.getHeight(),stage.getWidth()-actor.getWidth(), actor.getHeight()-SpacesButton.getHeight());



        stage.addActor(actor);
        stage.addActor(SpacesButton);
        stage.addActor(NextButton);
        stage.addActor(LogPanel);

    }


    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }
}
