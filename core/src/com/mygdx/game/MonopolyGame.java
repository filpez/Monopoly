package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.Splash;

import java.util.Random;

import logic.controller.BoardController;


public class MonopolyGame extends Game {
	public BoardController controller;
	public String username;
	public boolean online;

	@Override
	public void create () {
		Random rand = new Random();
		username = "Player" + Integer.toString(rand.nextInt(1000));
		setScreen(new Splash(this));
	}
}
