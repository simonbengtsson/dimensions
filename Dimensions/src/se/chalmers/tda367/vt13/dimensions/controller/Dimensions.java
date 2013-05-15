package se.chalmers.tda367.vt13.dimensions.controller;

import se.chalmers.tda367.vt13.dimensions.controller.screens.GameOverScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.GameScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.MainMenuScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.SplashScreen;
import se.chalmers.tda367.vt13.dimensions.model.levels.LevelHandler;
import se.chalmers.tda367.vt13.dimensions.util.Assets;
import se.chalmers.tda367.vt13.dimensions.util.LevelUtil;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Dimensions extends Game {

	private MainMenuScreen mainMenuScreen;
	private SplashScreen splashScreen;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;

	@Override
	public void create() {
		LevelUtil.loadAllLevels(); // Loads all Levels into the game
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		splashScreen = new SplashScreen(this);
		gameOverScreen = new GameOverScreen(this);

		// Which screen do you want to show on start up?
		setScreen(mainMenuScreen);
	}

	public void newGame() {
		gameScreen = new GameScreen(this);
	}

	public Screen getGameOverScreen() {
		return gameOverScreen;
	}

	public Screen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public Screen getGameScreen() {
		return gameScreen;
	}

	public Screen getSplashScreen() {
		return splashScreen;
	}

	/**
	 * Called from the splashscreen when it's done.
	 */
	public void splashScreenDone() {
		setScreen(new MainMenuScreen(this));
		splashScreen.dispose();
	}
}