package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.ArrayList;
import java.util.HashMap;
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
import se.chalmers.tda367.vt13.dimensions.model.levels.Level;
import se.chalmers.tda367.vt13.dimensions.model.levels.TiledLevel;
import se.chalmers.tda367.vt13.dimensions.view.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen, SoundObserver, WorldListener {
	private ArrayList<Rectangle> tiles = new ArrayList<Rectangle>();
	private ArrayList<Rectangle> obstacleTiles = new ArrayList<Rectangle>();
	private Rectangle playerRect = new Rectangle();
	GameWorld world;
	GameView view;
	Map<String, Sound> files;
	Dimensions game;

	public GameScreen(Dimensions game) {
		this.game = game;
	}

	@Override
	public void show() {
		Level level = new TiledLevel("Tiled", null);
		world = new GameWorld(level);
		world.addWorldListener(this);
		view = new GameView(world);
		loadSoundFiles();
		// For collision testing with player
		addTiles((TiledMapTileLayer) world.getCurrentMap().getLayers().get(1));
		addObstacleTiles((TiledMapTileLayer) world.getCurrentMap().getLayers()
				.get(2));
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
		if (world.getDimension() == Dimension.XY) {
			checkTileCollisionsXY();
		} else if (world.getDimension() == Dimension.XZ) {
			checkTileCollisionsXZ();
		}
		checkObstacleCollisions();
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

	/**
	 * Performs updates on world events
	 */
	@Override
	public void worldChange(WorldEvent type, Object value) {
		if (type == WorldEvent.GAME_OVER) {
			game.newGame();
			game.setScreen(new GameOverScreen(game));
		} else if (type == WorldEvent.DIMENSION_CHANGED) {
			view.changeMap((Dimension) value);
			// Removes and adds all new tiles
			addTiles((TiledMapTileLayer) world.getCurrentMap().getLayers()
					.get(1));
		}
	}

	/**
	 * Adjust players speed and position on collisions
	 */
	private void checkTileCollisionsXY() {
		Player player = world.getPlayer();
		// Sets grounded to true later if standing on platform or similar
		player.setIsGrounded(false);
		updatePlayerRect();
		for (Rectangle tile : tiles) {
			if (playerRect.overlaps(tile)) {
				// If player is moving upwards, do nothing. Could easily be
				// changed here.
				if (player.getSpeed().getY() <= 0) {
					player.getPosition().setY(tile.y + tile.height);
					player.setIsGrounded(true);
					world.getPlayer().getSpeed().setY(0);
					break;
				}
			}
		}
	}

	/**
	 * Rather than check for collision, checks for none collsions. Not
	 * efficient, but closer to how it is represented. Game over if the player
	 * is of the ground layer in the tiled map
	 */
	private void checkTileCollisionsXZ() {
		boolean aTileCollided = false;
		updatePlayerRect();
		for (Rectangle tile : tiles) {
			if (playerRect.overlaps(tile)) {
				aTileCollided = true;
				break;
			}
		}
		if (!aTileCollided) {
			System.out.println("GameOver");
			worldChange(WorldEvent.GAME_OVER, null);
		}

	}

	// TODO Tmp this works just fine... Might want to integrate it with the
	// other collision testing methods later somehow though
	private void checkObstacleCollisions() {
		updatePlayerRect(); // Probably safe to remove
		for (Rectangle tile : obstacleTiles) {
			if (playerRect.overlaps(tile)) {
				System.out.println("gameoverobstacle");
				worldChange(WorldEvent.GAME_OVER, null);
				break;
			}
		}
	}

	/**
	 * Called before collision testing to make sure the player rectangle is the
	 * same position as the player
	 */
	private void updatePlayerRect() {
		Vector3 position = world.getPlayer().getPosition();
		Vector3 size = world.getPlayer().getSize();
		playerRect.width = size.getX();
		playerRect.x = position.getX();
		if (world.getDimension() == Dimension.XY) {
			playerRect.y = position.getY();
			playerRect.height = size.getY();
		} else if (world.getDimension() == Dimension.XZ) {
			playerRect.y = position.getZ();
			playerRect.height = size.getZ();
		}
	}

	/**
	 * Adds all tiles from the specified layer for collision testing with
	 * player. (OBS only gets tiles to the 300th tile TODO)
	 * 
	 * @param layer
	 *            The layer which to collision test the player with
	 */
	public void addTiles(TiledMapTileLayer layer) {
		tiles.clear();
		for (int y = 0; y <= 20; y++) {
			for (int x = 0; x <= 300; x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = new Rectangle();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}

	/**
	 * Adds all obstacle tiles from specified "Obstacle" layer. (Obs! Only to
	 * the 300th x tile. TODO)
	 * 
	 * @param layer
	 *            The layer holding obstacle tiles
	 */
	public void addObstacleTiles(TiledMapTileLayer layer) {
		obstacleTiles.clear();
		for (int y = 0; y <= 20; y++) {
			for (int x = 0; x <= 300; x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = new Rectangle();
					rect.set(x, y, 1, 1);
					obstacleTiles.add(rect);
				}
			}
		}
	}
}
