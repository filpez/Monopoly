package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.Splash;

import java.util.ArrayList;

import logic.Board;
import logic.BoardController;
import logic.Player;
import logic.Space;
import logic.TransactionSpace;


public class MonopolyGame extends Game {
	//SpriteBatch batch;
	//Texture img;

	public Board board;
	public BoardController controller;

	//TO DO move to another class
	public Board generateNewBoard(){
		Player p = new Player("Filipe");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p);
		players.add(p);
		players.add(p);
		Space[] spaces = new Space[40];
		for (int i = 0; i < 40; i++)
			spaces[i] = new TransactionSpace("Lisbon", 0);
		return new Board(players, spaces);
	}

	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");

		board = generateNewBoard();
		board.setCurrentPlayer(board.getPlayers().get(0));
		controller = board.getController();
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
