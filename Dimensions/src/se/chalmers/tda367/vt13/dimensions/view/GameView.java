package se.chalmers.tda367.vt13.dimensions.view;

import java.util.HashMap;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * Game view.
 * 
 * @author Carl Fredriksson
 */
public class GameView {

	private GameWorld model;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private Map<String, Texture> textures;
	private OrthographicCamera camera;
	private Animation walkAnimation;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private float stateTime;
	private int thescore = 0;
	private BitmapFont font = new BitmapFont();
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;

	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	private Array<Rectangle> tiles = new Array<Rectangle>();

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            the GameModel
	 */
	public GameView(GameWorld model) {
		this.model = model;
		loadImageFiles();

		// load the map, set the unit scale to 1/16 (1 unit == 16 pixels)
		map = new TmxMapLoader().load("data/tiledMaps/level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();

		initWalkAnimation();
	}

	/**
	 * Draw GameObjects on the screen.
	 */
	public void draw() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// updateCameraPosition(3); //Uncomment for follow the player on the
		// y-axis
		camera.position.x = model.getPlayer().getPosition().getX();
		camera.update();

		renderer.setView(camera);
		renderer.render();

		calculateScore();
		checkTiledCollision();

		// Draw gameObjects
		SpriteBatch batch = renderer.getSpriteBatch();
		batch.begin();
		if (model.getDimension() == GameWorld.Dimension.XY) {
			drawGameObjectsXY(batch);
		} else if (model.getDimension() == GameWorld.Dimension.XZ) {
			drawGameObjectsXZ(batch);
		}
		font.setColor(Color.YELLOW);
		font.draw(batch, "Score: " + thescore, camera.position.x + 330,
				camera.position.y + 230);
		batch.end();
	}

	private void calculateScore() {
		thescore = (int) model.getPlayer().getPosition().getX() / 10;
	}

	/**
	 * Makes the camera smoothly follow the player.
	 * 
	 * @param speed
	 *            How fast the camera is following the player(y-axis)
	 */
	private void updateCameraPosition(int speed) {
		// Update camera position X axis
		camera.position.x = model.getPlayer().getPosition().getX() + 400;

		// Update camera position Y axis
		float playerPositionY = model.getPlayer().getPosition().getY();
		float delta = camera.position.y - playerPositionY;

		// If the player's position is close to the camera bottom, just move
		// the camera with the same speed as the player
		if (delta > 200) {
			camera.position.y = playerPositionY + Gdx.graphics.getHeight() / 2;
		} else if (delta < -200) {
			camera.position.y = playerPositionY - Gdx.graphics.getHeight() / 2;
		}
		// Only move the cameras Y position if it's Y position is more then 10
		// pixel away from the player's Y position --> improving performance
		else {

			if (Math.abs(delta) > 10) {
				camera.position.y -= delta / 100 * speed;
			}
		}

	}

	/*
	 * public void initiateBackground(){ TextureAtlas spriteSheet = new
	 * TextureAtlas(Gdx.files.internal("data/world1.txt")); TextureRegion bg =
	 * spriteSheet.findRegion("wold"); rbg.addLayer(new ParallaxLayer(bg,2f)); }
	 */

	private void initWalkAnimation() {
		walkSheet = new Texture(Gdx.files.internal("data/animation_sheet.png"));
		TextureRegion[][] regions = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = regions[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames);
		stateTime = 0f;
	}

	/**
	 * Loads the image files from all gameobjects and saves them in a HashMap
	 */
	private void loadImageFiles() {
		textures = new HashMap<String, Texture>();
		for (GameObject g : model.getGameObjects()) {
			String file = g.getImageFileAsString();
			if (!textures.containsKey(file) && !file.equals("")) {
				Texture t = new Texture(Gdx.files.internal(file));
				textures.put(file, t);
			}
		}
		String file = model.getPlayer().getImageFileAsString();
		textures.put(file, new Texture(Gdx.files.internal(file)));
	}

	private void drawGameObjectsXY(SpriteBatch spriteBatch) {
		for (GameObject gameObject : model.getGameObjects()) {
			Vector3 pos = gameObject.getPosition();
			Vector3 size = gameObject.getSize();
			spriteBatch.draw(textures.get(gameObject.getImageFileAsString()),
					pos.getX(), pos.getY(), size.getX(), size.getY());
		}
		stateTime += Gdx.graphics.getDeltaTime();

		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		Player p = model.getPlayer();
		spriteBatch.draw(currentFrame, p.getPosition().getX(), p.getPosition()
				.getY(), p.getSize().getX(), p.getSize().getY());
	}

	private void drawGameObjectsXZ(SpriteBatch spriteBatch) {
		for (GameObject gameObject : model.getGameObjects()) {
			Vector3 pos = gameObject.getPosition();
			Vector3 size = gameObject.getSize();
			spriteBatch.draw(textures.get(gameObject.getImageFileAsString()),
					pos.getX(), pos.getZ(), size.getX(), size.getZ());
		}
		stateTime += Gdx.graphics.getDeltaTime();

		Player p = model.getPlayer();
		spriteBatch.draw(textures.get(p.getImageFileAsString()), p
				.getPosition().getX(), p.getPosition().getZ(), p.getSize()
				.getX(), p.getSize().getZ());
	}

	public OrthographicCamera getCamera() {
		return this.camera;
	}

	public void dispose() {
	}

	public void checkTiledCollision() {
		Player player = model.getPlayer();
		player.getSpeed().setX(0.5f);
		player.getPosition().add(model.getPlayer().getSpeed());
		player.getSpeed().add(new Vector3 (0, -0.1f, 0));
		// perform collision detection & response, on each axis, separately
		// if the koala is moving right, check the tiles to the right of it's
		// right bounding box edge, otherwise check the ones to the left
		Rectangle koalaRect = rectPool.obtain();
		koalaRect.set(model.getPlayer().getPosition().getX(), model.getPlayer()
				.getPosition().getY(), model.getPlayer().getSize().getX(),
				model.getPlayer().getSize().getY());
		int startX, startY, endX, endY;
		if (model.getPlayer().getSpeed().getX() > 0) {
			startX = endX = (int) (model.getPlayer().getPosition().getX()
					+ model.getPlayer().getSize().getX() + model.getPlayer()
					.getSpeed().getX());
		} else {
			startX = endX = (int) (model.getPlayer().getPosition().getX() + model
					.getPlayer().getSpeed().getX());
		}
		startY = (int) (model.getPlayer().getPosition().getY());
		endY = (int) (model.getPlayer().getPosition().getY() + model
				.getPlayer().getSize().getY());
		getTiles(startX, startY, endX, endY, tiles);
		koalaRect.x += model.getPlayer().getSpeed().getX();
		for (Rectangle tile : tiles) {
			if (koalaRect.overlaps(tile)) {
				model.getPlayer().getSpeed().setX(0);
				break;
			}
		}
		koalaRect.x = model.getPlayer().getPosition().getX();

		// if the koala is moving upwards, check the tiles to the top of it's
		// top bounding box edge, otherwise check the ones to the bottom
		if (model.getPlayer().getSpeed().getY() > 0) {
			startY = endY = (int) (model.getPlayer().getPosition().getY()
					+ model.getPlayer().getSize().getY() + model.getPlayer()
					.getSpeed().getY());
		} else {
			startY = endY = (int) (model.getPlayer().getPosition().getY() + model
					.getPlayer().getSpeed().getY());
		}
		startX = (int) (model.getPlayer().getPosition().getX());
		endX = (int) (model.getPlayer().getPosition().getX() + model
				.getPlayer().getSize().getX());
		getTiles(startX, startY, endX, endY, tiles);
		koalaRect.y += model.getPlayer().getSpeed().getY();
		for (Rectangle tile : tiles) {
			if (koalaRect.overlaps(tile)) {
				// we actually reset the koala y-position here
				// so it is just below/above the tile we collided with
				// this removes bouncing :)
				if (model.getPlayer().getSpeed().getY() > 0) {
					model.getPlayer().getPosition()
							.setY(tile.y - model.getPlayer().getSize().getY());
					// we hit a block jumping upwards, let's destroy it!
					TiledMapTileLayer layer = (TiledMapTileLayer) map
							.getLayers().get(1);
					layer.setCell((int) tile.x, (int) tile.y, null);
				} else {
					model.getPlayer().getPosition().setY(tile.y + tile.height);
					// if we hit the ground, mark us as grounded so we can jump
					model.getPlayer().setIsGrounded(true);
				}
				model.getPlayer().getSpeed().setY(0);
				break;
			}
		}
		rectPool.free(koalaRect);
	}

	private void getTiles(int startX, int startY, int endX, int endY,
			Array<Rectangle> tiles) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
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
