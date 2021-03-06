package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.actors.BoardActor;
import com.mygdx.game.MonopolyGame;
import com.mygdx.game.actors.SpacesActor;

/**
 * Created by Filipe on 07/05/2016.
 */
public class BoardScreen implements Screen {
    private final MonopolyGame game;

    private ScrollPane LogPanel;
    private Stage stage;

    private Actor actor;
    private BoardActor boardActor;
    private SpacesActor spacesActor;
    private TextButton SpacesButton;
    private TextButton NextButton;

    private boolean spacesActorOn = false;

    public BoardScreen(Game game) {
        this.game = (MonopolyGame)game;
        create();
    }

    private void create(){
        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);


        boardActor = new BoardActor(game.controller);
        spacesActor = new SpacesActor(game);
        actor = boardActor;

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        SpacesButton = new TextButton("Spaces", skin);
        SpacesButton.getLabel().setWrap(true);
        SpacesButton.getLabel().setAlignment(Align.center);
        SpacesButton.setPosition(actor.getWidth(),0);
        SpacesButton.setBounds(actor.getWidth(),0, (stage.getWidth()-actor.getWidth())/2, actor.getHeight()/5);
        SpacesButton.getLabel().setFontScale(2.0f);

        SpacesButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                spacesActor.updateFunds();
                if (!spacesActorOn) {
                    SpacesButton.getLabel().setText("Board");
                    spacesActor.setCurrSpaceIndex(game.controller.getBoard().getCurrentPlayer().getPosition());
                    stage.addActor(spacesActor);
                    boardActor.remove();
                    spacesActorOn = true;
                }
                else  {
                    SpacesButton.getLabel().setText("Spaces");
                    stage.addActor(boardActor);
                    spacesActor.remove();
                    spacesActorOn = false;
                }
            }
        });

        NextButton = new TextButton("Next", skin);
        NextButton.getLabel().setWrap(true);
        NextButton.getLabel().setAlignment(Align.center);
        NextButton.setPosition(actor.getWidth(),0);
        NextButton.setBounds(actor.getWidth() + SpacesButton.getWidth(),0, (stage.getWidth()-actor.getWidth())/2, actor.getHeight()/5);
        NextButton.getLabel().setFontScale(2.0f);

        NextButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (game.controller.getState().getNextActionName().equals("Finish"))
                    Gdx.app.exit();
                else {
                    spacesActor.updateFunds();
                    if (!game.online || game.controller.isPlayer()) {
                        game.controller.getState().next(game.controller);
                    }
                }
            }
        });

        Label LogLabel = new Label(game.controller.getBoard().getLog(), skin);
        LogLabel.setFontScale(2.0f);
        LogLabel.setWrap(true);
        LogLabel.setAlignment(Align.bottomLeft);
        LogPanel = new ScrollPane(LogLabel);
        LogPanel.setBounds(actor.getWidth() ,SpacesButton.getHeight(),stage.getWidth()-actor.getWidth(), actor.getHeight()-SpacesButton.getHeight());



        stage.addActor(boardActor);
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Label l = (Label) LogPanel.getWidget();
        l.setText(game.controller.getBoard().getLog());

        spacesActor.updateFunds();
        spacesActor.updateText();

        NextButton.getLabel().setText(game.controller.getState().getNextActionName());

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
