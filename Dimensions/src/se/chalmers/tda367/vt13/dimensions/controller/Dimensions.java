package se.chalmers.tda367.vt13.dimensions.controller;

import se.chalmers.tda367.vt13.dimensions.controller.screens.SplashScreen;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;

import com.badlogic.gdx.Game;

public class Dimensions extends Game {

	@Override
	public void create() {
		LevelHandler.getInstance().load(); // Loads all Levels into the game
		// Set startup screen
		setScreen(new SplashScreen(this));
	}
}