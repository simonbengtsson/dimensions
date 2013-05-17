package se.chalmers.tda367.vt13.dimensions.controller;

import se.chalmers.tda367.vt13.dimensions.controller.screens.GameOverScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.GameScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.LevelSelectScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.MainMenuScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.OptionScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.SplashScreen;
import se.chalmers.tda367.vt13.dimensions.util.LevelUtil;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Dimensions extends Game {

	private SplashScreen splashScreen;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;
	private MainMenuScreen menuScreen;
	private OptionScreen optionScreen;
	private LevelSelectScreen levelSelectScreen;

	@Override
	public void create() {
		LevelUtil.loadAllLevels(); // Loads all Levels into the game
		gameScreen = new GameScreen(this);
		splashScreen = new SplashScreen(this);
		gameOverScreen = new GameOverScreen(this);
		menuScreen = new MainMenuScreen(this);
		optionScreen = new OptionScreen(this);
		levelSelectScreen = new LevelSelectScreen(this);

		// Set startup screen
		setScreen(splashScreen);
	}

	public Screen getGameOverScreen() {
		return gameOverScreen;
	}

	public Screen getMenuScreen() {
		return menuScreen;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public Screen getSplashScreen() {
		return splashScreen;
	}

	public Screen getOptionScreen(Dimensions game) {
		return optionScreen;
	}
	
	public Screen getLevelSelectScreen(){
		return levelSelectScreen;
	}
}