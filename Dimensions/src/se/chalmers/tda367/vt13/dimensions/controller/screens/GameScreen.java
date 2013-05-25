package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
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
		init();
	}

	public void init() {
		TiledMapHandler tiledMapHandler = new TiledMapHandler();
		Level playLevel;
		if (nextLevel != null) {
			playLevel = nextLevel;
		} else {
			playLevel = LevelHandler.getInstance().getProgressLevel(0)
					.getLevel();
		}
		world = new GameWorld(playLevel, tiledMapHandler);
		world.addWorldListener(this);
		gameView = new GameView(world, Assets.getTiledMap(playLevel
				.getMapXYPath()), Assets.getTiledMap(playLevel.getMapXZPath()));
		gameLayerView = new GameLayerView(world);
		tiledMapHandler.setGameView(gameView);
		loadSoundFiles();
	}

	private FPSLogger fl = new FPSLogger();

	@Override
	public void render(float delta) {
		updateInput();
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

	/**
	 * If having a computer running faster then 60 fps, slow it down.
	 * 
	 * @param delta
	 *            The time since last frame
	 */
	public void sleep(float delta) {
		int frameTime = 16;
		try {
			if (delta * 1000 < frameTime) {
				Thread.sleep((long) (frameTime - delta * 1000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles all input that isn't player navigation
	 */
	private void updateInput() {
		if (Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isTouched()) {
			// Different actions depending on what dimension is active
			if (world.getDimension() == Dimension.XY) {
				world.getPlayer().jump();
			} else if (world.getDimension() == Dimension.XZ) {
				world.getPlayer().changeDirection();
			}
		}
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

	/**
	 * Performs updates if world state changes
	 */
	@Override
	public void worldChanged(State worldState) {
		switch (worldState) {
		case GAME_OVER:
			LevelHandler.getInstance().gameFinished(world.getLevel(),
					world.getScore(), false);
			LevelHandler.getInstance().setLastPlayed(world.getLevel());
			game.setScreen(new GameOverScreen(game));
			break;
		case DIMENSION_CHANGED:
			gameView.changeMap(world.getDimension());
			gameView.setBatchColor(Color.WHITE);
			gameLayerView.setDimensionChange(false);
			break;
		case DIMENSION_CHANGING:
			Random rand = new Random();
			gameView.setBatchColor(new Color((int) (rand.nextFloat() + 0.5f),
					(int) (rand.nextFloat() + 0.5f),
					(int) (rand.nextFloat() + 0.5f), 1));
			gameView.shakeCamera();
			gameLayerView.setDimensionChange(true);
			break;
		case LEVEL_FINISHED:
			LevelHandler.getInstance().gameFinished(world.getLevel(),
					world.getScore(), true);
			game.setScreen(new WinScreen(game));
			break;
		default:
			break;
		}
	}

	public GameWorld getGameModel() {
		return world;
	}

	@Override
	public void show() {
	}

	@Override
	public void resize(int width, int height) {
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
}
