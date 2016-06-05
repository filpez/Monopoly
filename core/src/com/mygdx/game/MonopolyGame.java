package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.Splash;

import java.util.ArrayList;

import logic.Board;
import logic.BoardCreator;
import logic.controller.BoardController;
import logic.controller.BoardControllerClient;
import logic.Player;
import logic.Space;
import logic.TransactionSpace;


public class MonopolyGame extends Game {
	public BoardController controller;
	public String username;

	@Override
	public void create () {
		username = "Player";
		setScreen(new Splash(this));
	}
}
