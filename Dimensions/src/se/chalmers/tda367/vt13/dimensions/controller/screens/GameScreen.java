package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.WorldEvent;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.SoundObserver;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.WorldListener;
import se.chalmers.tda367.vt13.dimensions.model.levels.TiledLevel;
import se.chalmers.tda367.vt13.dimensions.view.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class GameScreen implements Screen, SoundObserver, WorldListener {
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	private Array<Rectangle> tiles = new Array<Rectangle>();
	GameWorld world;
	GameView view;
	Map<String, Sound> files;
	Dimensions game;

	public GameScreen(Dimensions game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		world = new GameWorld(new TiledLevel("Tiled", null, Dimension.XY));
		world.addWorldListener(this);
		view = new GameView(world, Dimension.XY);
		loadSoundFiles();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void render(float delta) {
		if (world.getPlayer().isGameOver()) {
			worldChange(WorldEvent.GAME_OVER, null);
		}
		getInput();
		checkTileCollisions();
		world.updateModel();
		view.draw();
	}

	private void getInput() {
		getInputUp();
		getInputDown();
	}

	/**
	 * Handles all input from upkeys and uptouchareas.
	 */
	private void getInputUp() {
		if (Gdx.input.isKeyPressed(Keys.UP)
				|| (Gdx.input.isTouched() && (Gdx.input.getY() < Gdx.graphics
						.getHeight() / 2))) {
			// Actions when dimension is XY
			if (world.getDimension() == Dimension.XY) {
				world.getPlayer().jump();

				// Actions when dimension is XZ
			} else if (world.getDimension() == Dimension.XZ) {
				world.getPlayer().moveUp();
			}

		}
	}

	/**
	 * Handles all input from downkeys and downtouchareas.
	 */
	private void getInputDown() {
		if (Gdx.input.isKeyPressed(Keys.DOWN)
				|| (Gdx.input.isTouched() && (Gdx.input.getY() >= Gdx.graphics
						.getHeight() / 2))) {
			// Actions when dimension is XY
			if (world.getDimension() == Dimension.XY) {
				world.getPlayer().dash();

				// Actions when dimension is XZ
			} else if (world.getDimension() == Dimension.XZ) {
				world.getPlayer().moveDown();
			}

		}
	}

	public GameWorld getGameModel() {
		return world;
	}

	@Override
	public void playSound(String s) {
		files.get(s).play(0.5f);
	}

	private void loadSoundFiles() {
		files = new HashMap<String, Sound>();
		for (GameObject g : world.getGameObjects()) {
			g.addObserver(this);
			String file = g.getSoundFileAsString();

			if (!files.containsKey(file) && !file.equals("")) {
				Sound sound = Gdx.audio.newSound(Gdx.files.internal(file));
				files.put(file, sound);
			}
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

	@Override
	public void worldChange(WorldEvent type, Object value) {
		if (type == WorldEvent.GAME_OVER) {
			game.newGame();
			game.setScreen(new GameOverScreen(game));
		} else if (type == WorldEvent.DIMENSION_CHANGED) {
			//game.newGame();
			//game.setScreen(new MainMenuScreen(game));
		}
	}

	public void checkTileCollisions() {
		Player player = world.getPlayer();
		player.setIsGrounded(false); // set to true if player is touching object

		// Reset the player's speed to MAX_VELOCITY if it's too fast, the reason
		// is to prevent the player to go through platforms and other gameobjects
		if (Math.abs(player.getSpeed().getY()) > Player.MAX_VELOCITY) {
			player.getSpeed()
					.setY(Math.signum(player.getSpeed().getY())
							* Player.MAX_VELOCITY);
		}

		Rectangle playerRect = rectPool.obtain();
		playerRect.set(world.getPlayer().getPosition().getX(), world
				.getPlayer().getPosition().getY(), world.getPlayer().getSize()
				.getX(), world.getPlayer().getSize().getY());
		int startX, startY, endX, endY;
		playerRect.x = world.getPlayer().getPosition().getX();

		// if the player is moving upwards, check the tiles to the top of it's
		// top bounding box edge, otherwise check the ones to the bottom
		if (world.getPlayer().getSpeed().getY() > 0) {
			startY = endY = (int) (world.getPlayer().getPosition().getY()
					+ world.getPlayer().getSize().getY() + world.getPlayer()
					.getSpeed().getY());
		} else {
			startY = endY = (int) (world.getPlayer().getPosition().getY() + world
					.getPlayer().getSpeed().getY());
		}
		startX = (int) (world.getPlayer().getPosition().getX());
		endX = (int) (world.getPlayer().getPosition().getX() + world
				.getPlayer().getSize().getX());
		getTiles(startX, startY, endX, endY, tiles);
		playerRect.y += world.getPlayer().getSpeed().getY();
		for (Rectangle tile : tiles) {
			if (playerRect.overlaps(tile)) {
				// reset the player y-position here
				// so it is just below/above the tile we collided with
				if (world.getPlayer().getSpeed().getY() > 0) {
					world.getPlayer().getPosition()
							.setY(tile.y - world.getPlayer().getSize().getY());
				} else {
					world.getPlayer().getPosition().setY(tile.y + tile.height);
					world.getPlayer().setIsGrounded(true);
				}
				world.getPlayer().getSpeed().setY(0);
				break;
			}
		}
		rectPool.free(playerRect);
	}

	private void getTiles(int startX, int startY, int endX, int endY,
			Array<Rectangle> tiles) {
		TiledMapTileLayer layer = (TiledMapTileLayer) world.getLevel().getMap().getLayers()
				.get(1);
		rectPool.freeAll(tiles);
		tiles.clear();
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}
}
