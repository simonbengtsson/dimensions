package se.chalmers.tda367.vt13.dimensions.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Dimensions extends Game {

	private MainMenuScreen mainMenuScreen;
	private SplashScreen splashScreen;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;

	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		splashScreen = new SplashScreen(this);
		gameOverScreen = new GameOverScreen(this);

		// Which screen do you want to show on start up?
		setScreen(splashScreen);
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