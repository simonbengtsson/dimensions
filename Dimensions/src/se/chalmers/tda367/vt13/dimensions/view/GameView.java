package se.chalmers.tda367.vt13.dimensions.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

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
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Game view.
 * 
 * @author Carl Fredriksson
 */
public class GameView {

	private GameWorld world;
	private TiledMap mapXY;
	private TiledMap mapXZ;
	private OrthogonalTiledMapRenderer renderer;
	private Map<String, Texture> textures;
	private OrthographicCamera camera;
	private Animation walkAnimation;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private float stateTime;
	private String dimensionWarning = "Warning, Dimension Changing!";
	private BitmapFont font = new BitmapFont();
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;
	private boolean DimensionChange;

	/**
	 * Constructor.
	 * 
	 * @param world
	 *            the Gameworld
	 */
	public GameView(GameWorld world, TiledMap mapXY, TiledMap mapXZ) {
		this.world = world;
		this.mapXY = mapXY;
		this.mapXZ = mapXZ;
		loadImageFiles();
		renderer = new OrthogonalTiledMapRenderer(getCurrentMap(), 1 / 16f);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();
		initWalkAnimation();
		font.setColor(Color.YELLOW);
	}

	public TiledMap getCurrentMap() {
		if (world.getDimension() == Dimension.XY) {
			return mapXY;
		} else if (world.getDimension() == Dimension.XZ) {
			return mapXZ;

		}
		return null;
	}

	public void dimensionWillChange(final SpriteBatch batch) {

		Timer t = new Timer();
		TimerTask red = new TimerTask() {
			@Override
			public void run() {
				batch.setColor(1, 0, 0, 1);
			}
		};
		TimerTask blue = new TimerTask() {
			@Override
			public void run() {
				batch.setColor(0, 0, 1, 1);
			}
		};

		TimerTask green = new TimerTask() {
			@Override
			public void run() {
				batch.setColor(0, 1, 0, 1);
			}
		};

		t.schedule(red, 100);
		t.schedule(blue, 700);
		t.schedule(green, 1400);

	}

	public void setDimensionChange(boolean b) {
		this.DimensionChange = b;
	}

	public void changeMap(Dimension d) {
		if (d == Dimension.XY) {
			renderer.setMap(mapXY);
		} else if (d == Dimension.XZ) {
			renderer.setMap(mapXZ);
		}
	}

	/**
	 * Draw GameObjects on the screen.
	 */
	public void draw() {

		switch (world.getCurrentState()) {
		case GAME_RUNNING:
			drawIsRunning();
			break;
		case GAME_PAUSED:
			drawIsPaused();
			break;
		default:
			break;
		}

	}

	private void drawIsRunning() {
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		font.setScale(font.getScaleX() / 10, font.getScaleY() / 10);
		// Uncomment below for making the camera follow the player on the y-axis
		// updateCameraPosition(3);
		camera.position.x = world.getPlayer().getPosition().getX() + 12;
		camera.update();
		renderer.setView(camera);
		renderer.render();

		// Draw gameObjects
		SpriteBatch batch = renderer.getSpriteBatch();
		batch.begin();
		if (this.DimensionChange == true) {
			dimensionWillChange(batch);

			// Font drawing does not quite work yet
			// font.draw(batch, dimensionWarning,
			// world.getPlayer().getPosition().getX(),
			// world.getPlayer().getPosition().getY()+5);
		}

		if (world.getDimension() == GameWorld.Dimension.XY) {
			drawGameObjectsXY(batch);
		} else if (world.getDimension() == GameWorld.Dimension.XZ) {
			drawGameObjectsXZ(batch);
		}

		batch.end();
	}

	private void drawIsPaused() {
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.position.x = 0;
		camera.update();

		SpriteBatch batch = renderer.getSpriteBatch();
		batch.begin();

		BitmapFont font = new BitmapFont();
		font.setScale(2);
		font.draw(batch, "Test", 20, 20);

		batch.end();
	}

	/**
	 * Makes the camera smoothly follow the player y axis.
	 * 
	 * @param speed
	 *            How fast the camera is following the player(y-axis)
	 */
	private void updateCameraPositionY(int speed) {
		// Update camera position Y axis
		float playerPositionY = world.getPlayer().getPosition().getY();
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
		for (GameObject g : world.getGameObjects()) {
			String file = g.getImagePath();
			if (!textures.containsKey(file) && !file.equals("")) {
				Texture t = new Texture(Gdx.files.internal(file));
				textures.put(file, t);
			}
		}
		String file = world.getPlayer().getImagePath();
		textures.put(file, new Texture(Gdx.files.internal(file)));
	}

	private void drawGameObjectsXY(SpriteBatch spriteBatch) {
		for (GameObject gameObject : world.getGameObjects()) {
			Vector3 pos = gameObject.getPosition();
			Vector3 size = gameObject.getSize();
			spriteBatch.draw(textures.get(gameObject.getImagePath()),
					pos.getX(), pos.getY(), size.getX(), size.getY());
		}
		stateTime += Gdx.graphics.getDeltaTime();

		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		Player p = world.getPlayer();
		spriteBatch.draw(currentFrame, p.getPosition().getX(), p.getPosition()
				.getY(), p.getSize().getX(), p.getSize().getY());
	}

	private void drawGameObjectsXZ(SpriteBatch spriteBatch) {
		for (GameObject gameObject : world.getGameObjects()) {
			Vector3 pos = gameObject.getPosition();
			Vector3 size = gameObject.getSize();
			spriteBatch.draw(textures.get(gameObject.getImagePath()),
					pos.getX(), pos.getY(), size.getX(), size.getY());
		}
		stateTime += Gdx.graphics.getDeltaTime();

		Player p = world.getPlayer();
		spriteBatch.draw(textures.get(p.getImagePath()),
				p.getPosition().getX(), p.getPosition().getZ(), p.getSize()
						.getX(), p.getSize().getZ());
	}

	public OrthographicCamera getCamera() {
		return this.camera;
	}
}
