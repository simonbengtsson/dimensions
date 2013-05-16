package se.chalmers.tda367.vt13.dimensions.controller;

import se.chalmers.tda367.vt13.dimensions.controller.screens.GameOverScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.GameScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.LevelSelectScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.MainMenuScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.MenuScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.OptionScreen;
import se.chalmers.tda367.vt13.dimensions.controller.screens.SplashScreen;
import se.chalmers.tda367.vt13.dimensions.util.LevelUtil;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Dimensions extends Game {

	private MainMenuScreen mainMenuScreen;
	private SplashScreen splashScreen;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;
	private MenuScreen menuScreen;
	private OptionScreen optionScreen;
	private LevelSelectScreen levelSelectScreen;

	@Override
	public void create() {
		LevelUtil.loadAllLevels(); // Loads all Levels into the game
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		splashScreen = new SplashScreen(this);
		gameOverScreen = new GameOverScreen(this);
		menuScreen = new MenuScreen(this);
		optionScreen = new OptionScreen(this);
		levelSelectScreen = new LevelSelectScreen(this);

		// Set startup screen
		setScreen(mainMenuScreen);
	}

	public Screen getGameOverScreen() {
		return gameOverScreen;
	}

	public Screen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public Screen getSplashScreen() {
		return splashScreen;
	}

	/**
	 * Called from the splashscreen when it's done.
	 */
	public void splashScreenDone() {
		setScreen(menuScreen);
		splashScreen.dispose();
	}

	public Screen getOptionScreen(Dimensions game) {
		return optionScreen;
	}
	
	public Screen getLevelSelectScreen(){
		return levelSelectScreen;
	}
}