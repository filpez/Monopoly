package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MonopolyGame;


/**
 * Created by Filipe on 05/06/2016.
 */
public class SettingsScreen implements Screen {
    private final MonopolyGame game;
    private Stage stage;

    private TextField usernameField;

    public SettingsScreen(Game game) {
        this.game = (MonopolyGame)game;
        create();
    }

    private void create(){
        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.get(TextField.TextFieldStyle.class).font.getData().setScale(4.0f);

        Label usernameLabel = new Label("Name: ", skin);
        usernameLabel.setWrap(true);
        usernameLabel.setFontScale(4.0f);
        usernameLabel.setAlignment(Align.center);

        usernameField = new TextField(game.username, skin);
        usernameField.setAlignment(Align.center);

        Label rulesLabel = new Label(getRulesText(), skin);
        rulesLabel.setWrap(true);
        rulesLabel.setFontScale(3.0f);
        rulesLabel.setAlignment(Align.topLeft);

        ScrollPane rulesPane = new ScrollPane(rulesLabel);
        rulesPane.setFadeScrollBars(true);

        TextButton backButton = new TextButton("Back to Main Menu", skin);
        backButton.getLabel().setWrap(true);
        backButton.getLabel().setAlignment(Align.center);
        backButton.getLabel().setFontScale(3.0f);
        backButton.setBounds(0,0, Gdx.graphics.getWidth()/3, 0);
        backButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.username = usernameField.getText();
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });


        Table table = new Table();
        table.setFillParent(true);
        table.add(usernameLabel).width(Gdx.graphics.getWidth()/4).height(Gdx.graphics.getHeight()/6);
        table.add(usernameField).width(Gdx.graphics.getWidth()*3/4).height(Gdx.graphics.getHeight()/6).row();
        table.add(rulesPane).width(Gdx.graphics.getWidth()).colspan(2).row();
        table.add(backButton).colspan(2).width(Gdx.graphics.getWidth()/3).height(Gdx.graphics.getHeight()/6);



        stage.addActor(table);
    }

    private String getRulesText() {
        FileHandle file = Gdx.files.internal("data/rules.txt");
        String res = file.readString();
        return res;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}
