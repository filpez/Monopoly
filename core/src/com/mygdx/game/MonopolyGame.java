package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.Splash;


public class MonopolyGame extends Game {
	//SpriteBatch batch;
	//Texture img;
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		setScreen(new Splash(this));
	}

	/*@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
}
