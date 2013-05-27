package se.chalmers.tda367.vt13.dimensions.view;

import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.util.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
	private OrthographicCamera camera;
	private Animation walkAnimation;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private float stateTime;
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;
	private SpriteBatch batch;

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
		batch = renderer.getSpriteBatch();
	}

	public TiledMap getCurrentMap() {
		if (world.getDimension() == Dimension.XY) {
			return mapXY;
		} else if (world.getDimension() == Dimension.XZ) {
			return mapXZ;

		}
		return null;
	}

	public void changeMap(Dimension d) {
		if (d == Dimension.XY) {
			renderer.setMap(mapXY);
		} else if (d == Dimension.XZ) {
			renderer.setMap(mapXZ);
		}
	}

	public void draw() {
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.position.x = world.getPlayer().getPosition().getX() + 12;
		camera.update();
		renderer.setView(camera);
		renderer.render();

		// Draw gameObjects

		batch.begin();
		if (world.getDimension() == Dimension.XY) {
			drawGameObjectsXY(batch);
		} else if (world.getDimension() == Dimension.XZ) {
			drawGameObjectsXZ(batch);
		}
		batch.end();
	}

	private void initWalkAnimation() {
		walkSheet = Assets.getTexture("data/player-xy.png");
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
		walkAnimation = new Animation(0.05f, walkFrames);
		stateTime = 0f;
	}

	/**
	 * Loads the image files from all gameobjects and saves them in a HashMap
	 */
	private void loadImageFiles() {
		for (GameObject g : world.getGameObjects()) {
			Assets.registerTexture(g.getImagePath());
		}
		Assets.registerTexture(world.getPlayer().getImagePath());
	}

	private void drawGameObjectsXY(SpriteBatch spriteBatch) {
		for (GameObject gameObject : world.getGameObjects()) {
			Vector3 pos = gameObject.getPosition();
			Vector3 size = gameObject.getSize();
			spriteBatch.draw(Assets.getTexture(gameObject.getImagePath()),
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
			spriteBatch.draw(Assets.getTexture(gameObject.getImagePath()),
					pos.getX(), pos.getZ(), size.getX(), size.getZ());
		}
		stateTime += Gdx.graphics.getDeltaTime();

		Player p = world.getPlayer();
		spriteBatch.draw(Assets.getTexture(p.getImagePath()), p.getPosition()
				.getX(), p.getPosition().getZ(), p.getSize().getX(), p
				.getSize().getZ());
	}

	public OrthographicCamera getCamera() {
		return this.camera;
	}

	public void dimensionChanging() {
		Random rand = new Random();
		setBatchColor(new Color((int) (rand.nextFloat() + 0.5f),
				(int) (rand.nextFloat() + 0.5f),
				(int) (rand.nextFloat() + 0.5f), 1));
		shakeCamera();
	}

	public void setBatchColor(Color c) {
		batch.setColor(c);
	}

	private boolean wasRight = true;

	private void shakeCamera() {
		if (wasRight) {
			camera.position.y += 0.3f;
			wasRight = false;
		} else {
			camera.position.y -= 0.3f;
			wasRight = true;
		}
	}
}
