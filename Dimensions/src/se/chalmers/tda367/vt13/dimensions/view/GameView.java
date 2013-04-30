package se.chalmers.tda367.vt13.dimensions.view;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.model.GameModel;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.tiled.SimpleTileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;

/**
 * Game view.
 * 
 * @author Carl Fredriksson
 */
public class GameView {

	private GameModel model;
	private SpriteBatch spriteBatch;
	private Map<String, Texture> textures;
	private OrthographicCamera camera;
	private Animation walkAnimation;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private float stateTime;
	private TiledMap map;
	private TileMapRenderer renderer;
	private SimpleTileAtlas atlas;

	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;

	// Public Methods
	/**
	 * Constructor.
	 * 
	 * @param model
	 *            the GameModel
	 */
	public GameView(GameModel model) {
		this.model = model;
		spriteBatch = new SpriteBatch();

		loadImageFiles();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		// testing animation
		walkSheet = new Texture(Gdx.files.internal("data/animation_sheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS); // #10
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames);
		stateTime = 0f;

		map = new TiledLoader().createMap(Gdx.files.internal("data/lvl1.tmx"));
		// renderer = new TileMapRenderer(map,1f/32f);
		atlas = new SimpleTileAtlas(map, Gdx.files.internal("data/"));
		renderer = new TileMapRenderer(map, atlas, 10, 10, 32, 32);

	}

	/**
	 * Draw GameObjects on the screen.
	 */
	public void draw() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		renderer.render(camera);
		updateCameraPosition(3);
		spriteBatch.setProjectionMatrix(camera.combined);

		// Draw GameObjects
		spriteBatch.begin();
		if (model.getDimension() == GameModel.Dimension.XY) {
			drawGameObjectsXY();
		} else if (model.getDimension() == GameModel.Dimension.XZ) {
			drawGameObjectsXZ();
		}

		spriteBatch.end();
	}

	/**
	 * Makes the camera follow the player
	 * 
	 * @param speed
	 *            How fast the camera is following the player(y-axis)
	 * @param activation
	 *            How many pixels away from the center of the screen the player
	 *            is going to be before the camera starts following
	 */
	private void updateCameraPosition(int speed) {
		// Update camera position X axis
		camera.position.x = model.getPlayer().getPosition().getX() + 400;

		// Update camera position Y axis
		float playerPositionY = model.getPlayer().getPosition().getY();
		float delta = camera.position.y - playerPositionY;

		// If the player's position is close to the camera bottom, just move the
		// camera with the same speed as the player
		if (!(Math.abs(delta) >= 200) || model.getPlayer().getIsGrounded()) {
			// Only move the camera if it's Y position is more then 10 pixel
			// away from the player's Y position
			if (Math.abs(delta) > 10) {
				camera.position.y -= delta / 100 * speed;
			}
		} else {
			camera.position.y = playerPositionY + 200;
		}
	}

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

	private void drawGameObjectsXY() {
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

	private void drawGameObjectsXZ() {
		for (GameObject gameObject : model.getGameObjects()) {
			Vector3 pos = gameObject.getPosition();
			Vector3 size = gameObject.getSize();
			spriteBatch.draw(textures.get(gameObject.getImageFileAsString()),
					pos.getX(), pos.getZ(), size.getX(), size.getZ());
		}
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		Player p = model.getPlayer();

		spriteBatch.draw(textures.get(p.getImageFileAsString()), p
				.getPosition().getX(), p.getPosition().getY(), p.getSize()
				.getX(), p.getSize().getY());
	}

	public void dispose() {
		spriteBatch.dispose();
	}
}
