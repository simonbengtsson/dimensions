package se.chalmers.tda367.vt13.dimensions.controller;

import se.chalmers.tda367.vt13.dimensions.controller.screens.SplashScreen;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;
import se.chalmers.tda367.vt13.dimensions.util.Storage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Dimensions extends Game {

	@Override
	public void create() {
		if(!Storage.loadProgress()){
			LevelHandler.getInstance().load(); // Loads all new Levels into the game
		}
		
		// Set startup screen
		setScreen(new SplashScreen(this));
	}
}