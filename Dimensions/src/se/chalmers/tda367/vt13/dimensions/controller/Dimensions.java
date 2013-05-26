package se.chalmers.tda367.vt13.dimensions.controller;

import se.chalmers.tda367.vt13.dimensions.controller.screens.MainMenuScreen;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;
import se.chalmers.tda367.vt13.dimensions.util.Assets;
import se.chalmers.tda367.vt13.dimensions.util.Storage;

import com.badlogic.gdx.Game;

public class Dimensions extends Game {

	@Override
	public void create() {
		if (!Storage.loadProgress()) {
			LevelHandler.getInstance().load();
		}
		Assets.loadAllAudioVisual();
		setScreen(new MainMenuScreen(this));
	}
}