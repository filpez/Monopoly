package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MonopolyGame;

import java.net.InetAddress;

import lipermi.handler.CallHandler;
import lipermi.net.Server;
import logic.controller.BoardControllerServer;
import logic.controller.ControllerServerInterface;

/**
 * Created by Filipe on 07/05/2016.
 */
public class MainMenu  implements Screen {
    private final MonopolyGame game;

    private Stage stage;

    private ImageButton startButton, exitButton, settingsButton;
    private Image heading, background;
    private Dialog dialog;

    private int x = Gdx.graphics.getWidth()/6;
    private int y = Gdx.graphics.getHeight()/6;

    //private SpriteBatch batch;
   // private Texture heading, background, startButton, exitButton, settingsButton;

   /* Skin skin;
    Stage stage;
    public ImageButton startButton, exitButton, settingsButton;*/

    public MainMenu(Game game) {
        this.game = (MonopolyGame)game;
        create();
    }

    private void create(){
       // ScreenViewport viewport = new ScreenViewport();
        //stage = new Stage(viewport);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextureRegionDrawable startButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("img/start_button.png")));
        startButton = new ImageButton(startButtonDrawable);
        startButton.setBounds(x, y/2+y*2, x*4, y);
        startButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                startGame();
            }
        });

        TextureRegionDrawable exitButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("img/exit_button.png")));
        exitButton = new ImageButton(exitButtonDrawable);
        exitButton.setBounds(x, y/2, x*4, y);
        exitButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        TextureRegionDrawable  settingsButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("img/settings_button.png")));
        settingsButton = new ImageButton( settingsButtonDrawable);
        settingsButton.setBounds(x, y/2+y, x*4, y);
        settingsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        background = new Image(new Texture("img/main_menu_background.jpg"));

        heading =new Image( new Texture("img/main_menu_logo.png"));
        heading.setBounds(x/2, y/2+y*3, x*5, y*5/2);;

        stage.addActor(background);
        stage.addActor(startButton);
        stage.addActor(exitButton);
        stage.addActor(settingsButton);
        stage.addActor(heading);
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

    private void cancelDialog(){
        Gdx.input.setCatchBackKey(false);
        game.setScreen(new MainMenu(game));
        dispose();
    }

    private void startGame() {
        Gdx.input.setCatchBackKey(true);
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        dialog = new Dialog("", skin, "default") {
            public void result(Object obj) {
                if (obj.equals("cancel"))
                    cancelDialog();
            }
        };
        Label label = new Label("Do you want to join a server?", skin);
        label.setWrap(true);
        label.setFontScale(5.0f);
        label.setAlignment(Align.center);

        dialog.padTop(50).padBottom(50);
        dialog.getContentTable().add(label).width(x*4).height(y).row();
        dialog.getButtonTable().padTop(50);

        TextButton yesButton = new TextButton("Yes", skin);
        yesButton.getLabel().setFontScale(5.0f);
        yesButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                dialog.hide();
                startGameClient();
                //game.setScreen(new BoardScreen(game));
            }
        });

        TextButton noButton = new TextButton("No", skin);
        noButton.getLabel().setFontScale(5.0f);
        noButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                dialog.hide();
                startGameServer();
            }
        });

        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.getLabel().setFontScale(5.0f);
        cancelButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                cancelDialog();
            }
        });

        Table t = dialog.getButtonTable();
        t.add(cancelButton).width(x).pad(x/10);
        t.add(noButton).width(x).pad(x/10);
        t.add(yesButton).width(x).pad(x/10).row();

        dialog.key(Input.Keys.BACK, "cancel");
        dialog.show(stage);
    }

    private void startGameClient() {
        /*BoardControllerServer server = new BoardControllerServer();
        game.controller = server;
        server.addPlayer("Filipe");
*/
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.get(TextField.TextFieldStyle.class).font.getData().setScale(5.0f);


        dialog = new Dialog("", skin, "default") {
            public void result(Object obj) {
                if (obj.equals("cancel"))
                    game.setScreen(new MainMenu(game));
                dispose();

            }
        };

        String IPaddress = "";
        TextField IPTextField = new TextField(IPaddress, skin);
        IPTextField.setAlignment(Align.center);

        Label label = new Label("Insert IP and click Join", skin);
        label.setWrap(true);
        label.setFontScale(4.0f);
        label.setAlignment(Align.center);

        Label label2 = new Label("IP: ", skin);
        label2.setFontScale(5.0f);
        label2.setAlignment(Align.center);

        dialog.padTop(50).padBottom(50);
        dialog.getContentTable().add(label).width(x*5).height(y).colspan(2).row();
        dialog.getContentTable().add(label2).width(x).height(y);
        dialog.getContentTable().add(IPTextField).width(x*3).height(y).row();
        dialog.getButtonTable().padTop(50);

        TextButton button = new TextButton("Join", skin);
        button.getLabel().setFontScale(5.0f);
        button.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //startGameClient();
                dialog.hide();
                game.setScreen(new BoardScreen(game));
            }
        });


        Table t = dialog.getButtonTable();
        t.add(button).width(x*3).row();

        dialog.key(Input.Keys.BACK, "cancel");
        dialog.show(stage);
    }

    private void startGameServer() {
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));dialog = new Dialog("", skin, "default") {
            public void result(Object obj) {
                if (obj.equals("cancel"))
                    game.setScreen(new MainMenu(game));
                dispose();

            }
        };
        Label label = new Label("Press Start when you are ready!", skin);
        label.setWrap(true);
        label.setFontScale(5.0f);
        label.setAlignment(Align.center);

        String IPaddress = "";
        try {
            BoardControllerServer controllerServer = new BoardControllerServer("Filipe");
            game.controller = controllerServer;
            CallHandler callHandler = new CallHandler();
            callHandler.registerGlobal(ControllerServerInterface.class, controllerServer);
            Server server = new Server();
            int thePortIWantToBind = 4456;
            server.bind(thePortIWantToBind, callHandler);
            InetAddress IP = InetAddress.getLocalHost();
            IPaddress = IP.getHostAddress();
            System.err.println("Server ready at " + IP.getHostAddress() + " port " + 4456);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
            cancelDialog();
        }
        Label label2 = new Label("IP: "+ IPaddress, skin);
        label2.setWrap(true);
        label2.setFontScale(5.0f);
        label2.setAlignment(Align.center);

        dialog.padTop(50).padBottom(50);
        dialog.getContentTable().add(label).width(x*4).height(y).row();
        dialog.getContentTable().add(label2).width(x*4).height(y).row();
        dialog.getButtonTable().padTop(50);

        TextButton button = new TextButton("Start", skin);
        button.getLabel().setFontScale(5.0f);
        button.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                BoardControllerServer controllerServer = (BoardControllerServer)game.controller;
                controllerServer.start();
                dialog.hide();
                game.setScreen(new BoardScreen(game));
            }
        });


        Table t = dialog.getButtonTable();
        t.add(button).width(x*3).row();

        dialog.key(Input.Keys.BACK, "cancel");
        dialog.show(stage);
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
