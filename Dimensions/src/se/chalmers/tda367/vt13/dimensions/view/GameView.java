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
		// clear the screen
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


		// updateCameraPosition(3); //Uncomment for follow the player on the
		// y-axis
		camera.position.x = model.getPlayer().getPosition().getX();
		camera.update();

		renderer.setView(camera);
		renderer.render();

		calculateScore();

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
	}
	
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
	
	public TiledMap getMap() {
		return map;
	}
}
