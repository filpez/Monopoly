package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Filipe on 07/05/2016.
 */
public class MainMenunew implements Screen {
    private final Game game;

    private SpriteBatch batch;
    private Texture heading, background, startButton, exitButton, settingsButton;

   /* Skin skin;
    Stage stage;
    public ImageButton startButton, exitButton, settingsButton;*/

    public MainMenunew(Game game) {
        this.game = game;
        create();
    }

    private void create(){
        batch = new SpriteBatch();
        startButton = new Texture("img/start_button.png");
        exitButton = new Texture("img/exit_button.png");
        settingsButton = new Texture("img/settings_button.png");
        background = new Texture("img/main_menu_background.jpg");
        heading = new Texture("img/main_menu_logo.png");
    }


    @Override
    public void dispose() {
        startButton.dispose();
        exitButton.dispose();
        settingsButton.dispose();
        background.dispose();
        heading.dispose();
        batch.dispose();
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

        batch.begin();
        int x = Gdx.graphics.getWidth()/6;
        int y = Gdx.graphics.getHeight()/6;

        batch.draw(background,0,0);
        batch.draw(exitButton, x, y/2, x*4, y);
        batch.draw(settingsButton, x, y/2+y, x*4, y);
        batch.draw(startButton, x, y/2+y*2, x*4, y);
        batch.draw(heading, x/2, y/2+y*3, x*5, y*5/2);

        batch.end();

        if (Gdx.input.getX() > x/2 && Gdx.input.getX() < x/2+x*4 && Gdx.input.isTouched()){
            if(Gdx.input.getY() > y/2+y*4 && Gdx.input.getY() < y/2+y*5){
                Gdx.app.exit();
            }
            else if(Gdx.input.getY() > y/2+y*3 && Gdx.input.getY() < y/2+y*4){
                game.setScreen(new Splash(game));
                dispose();
            }
            else if(Gdx.input.getY() > y/2+y*2 && Gdx.input.getY() < y/2+y*3){
                startGame();
                //dispose();
            }
        }
    }

    private void startGame() {
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        Dialog dialog = new Dialog("Warning", skin, "dialog") {
            public void result(Object obj) {
                if (obj.equals("yes"))
                    game.setScreen(new BoardScreen(game));
                else if (obj.equals(1))
                    game.setScreen(new BoardScreen(game));
                else
                    game.setScreen(new Splash(game));
                dispose();

            }
        };
        dialog.text("Do you want to join a server?");
        dialog.button("Yes", "yes"); //sends "true" as the result
        dialog.button("No", 1);  //sends "false" as the result
        //dialog.button("Cancel", 2);  //sends "false" as the result
        //game.setScreen(new BoardScreen(game));
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