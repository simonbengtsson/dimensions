package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.CollisionHandler;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;
import se.chalmers.tda367.vt13.dimensions.model.Level;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;
import se.chalmers.tda367.vt13.dimensions.model.SoundObserver;
import se.chalmers.tda367.vt13.dimensions.model.WorldListener;
import se.chalmers.tda367.vt13.dimensions.util.Assets;
import se.chalmers.tda367.vt13.dimensions.util.TiledMapHandler;
import se.chalmers.tda367.vt13.dimensions.view.GameLayerView;
import se.chalmers.tda367.vt13.dimensions.view.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;

public class GameScreen implements Screen, SoundObserver, WorldListener {
	private GameWorld world;
	private GameView gameView;
	private GameLayerView gameLayerView;
	private Dimensions game;
	private boolean escapeWasPressed;
	private boolean enterWasPressed;
	private Level nextLevel;

	public GameScreen(Dimensions game, Level level) {
		this.game = game;
		this.nextLevel = level;
		this.escapeWasPressed = false;
		this.enterWasPressed = false;
	}

	@Override
	public void show() {

		TiledMapHandler tiledMapHandler = new TiledMapHandler();
		CollisionHandler collisionHandler = new CollisionHandler(
				tiledMapHandler);
		world = new GameWorld(nextLevel, collisionHandler);
		world.addWorldListener(this);
		gameView = new GameView(world, tiledMapHandler.getMap(nextLevel
				.getMapXYPath()), tiledMapHandler.getMap(nextLevel
				.getMapXZPath()));
		gameLayerView = new GameLayerView(world);
		tiledMapHandler.setGameView(gameView);
		loadSoundFiles();
	}

	@Override
	public void resize(int width, int height) {
	}

	private FPSLogger fl = new FPSLogger();

	@Override
	public void render(float delta) {
		getInput();
		world.update();

		switch (world.getCurrentState()) {
		case GAME_PAUSED:
			gameLayerView.drawPaused();
			break;
		default:
			gameView.draw();
			gameLayerView.draw();
			break;
		}
		fl.log();
		sleep(delta);
	}

	public void sleep(float delta) {
		int frameTime = 16;
		try {
			if (delta * 1000 < frameTime) {
				Thread.sleep((long) (16 - delta * 1000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void getInput() {
		getGameInput();
		getSpecialInput();
	}

	/**
	 * Handles game input.
	 */
	private void getGameInput() {
		if (Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isTouched()) {
			// Different actions depending on what dimension is active
			if (world.getDimension() == Dimension.XY) {
				world.getPlayer().jump();
			} else if (world.getDimension() == Dimension.XZ) {
				world.getPlayer().changeDirection();
			}
		}
	}

	/**
	 * Handles all input that isn't player navigation
	 */
	private void getSpecialInput() {
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			if (!enterWasPressed) {
				world.resetToCheckPoint();
				enterWasPressed = true;
			}
		} else {
			enterWasPressed = false;
		}

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			if (!escapeWasPressed) {
				togglePause();
				escapeWasPressed = true;
			}
		} else {
			escapeWasPressed = false;
		}
	}

	/**
	 * Called when the game should be paused.
	 */
	private void togglePause() {
		if (world.getCurrentState() == State.GAME_RUNNING) {
			world.setCurrentState(State.GAME_PAUSED);
		} else {
			world.setCurrentState(State.GAME_RUNNING);
		}
	}

	public void nextLevel(Level l) {
		this.nextLevel = l;
	}

	public GameWorld getGameModel() {
		return world;
	}

	@Override
	public void playSound(String s) {
		Assets.playSound(s);
	}

	private void loadSoundFiles() {
		for (GameObject g : world.getGameObjects()) {
			g.addObserver(this);
			Assets.registerSound(g.getSoundFileAsString());
		}
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {

	}

	/**
	 * Performs updates if world state changes
	 */
	@Override
	public void worldChanged(State worldState) {
		switch (worldState) {
		case GAME_OVER:
			game.setScreen(new GameOverScreen(game));
			LevelHandler.getInstance().gameFinished(world.getLevel(),
					world.getScore(), false);
		case DIMENSION_CHANGED:
			gameView.changeMap(world.getDimension());
			gameView.setBatchColor(Color.WHITE);
			gameLayerView.setDimensionChange(false);
		case DIMENSION_CHANGING:
			Random rand = new Random();
			gameView.setBatchColor(new Color((int) (rand.nextFloat() + 0.5f),
					(int) (rand.nextFloat() + 0.5f),
					(int) (rand.nextFloat() + 0.5f), 1));
			gameView.shakeCamera();
			gameLayerView.setDimensionChange(true);
		case LEVEL_FINISHED:
			game.setScreen(new WinScreen(game));
			LevelHandler.getInstance().gameFinished(world.getLevel(),
					world.getScore(), true);
		default:
			break;
		}
	}
}
