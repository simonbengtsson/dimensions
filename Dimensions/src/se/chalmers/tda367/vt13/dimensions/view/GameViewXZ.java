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

public class GameViewXZ {

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
	public GameViewXZ(GameModel model) {
		this.model = model;
		spriteBatch = new SpriteBatch();

		loadImageFiles();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		
		//testing
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
		//renderer = new TileMapRenderer(map,1f/32f);
		atlas = new SimpleTileAtlas(map,Gdx.files.internal("data/"));
		renderer = new TileMapRenderer(map, atlas, 10,10,32, 32);
		
	}

	/**
	 * Draw GameObjects on the screen.
	 */
	public void draw() {
		// Clear screen with white color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		renderer.render(camera);
		updateCameraPosition(3, 10, false); // Try changing to parameters to get
											// the
		// right feeling
		spriteBatch.setProjectionMatrix(camera.combined);
		// Draw GameObjects
		spriteBatch.begin();

		drawGameObjects();

		spriteBatch.end();
	}

	/**
	 * Makes the camera follow the player
	 * 
	 * @param speed
	 *            How fast the camera is following the player(y-axis)
	 * @param activation
	 *            How many pixels away from center the player is going to be
	 *            before camera starts following
	 */
	private void updateCameraPosition(int speed, int distance, boolean crazyMode) {
		camera.position.x = model.getPlayer().getPosition().getX() + 400;
		float delta = camera.position.y
				- model.getPlayer().getPosition().getY();
		if (Math.abs(delta) > distance) {
			camera.position.y -= delta / 100 * speed;
		}
		if (crazyMode) {
			if (!model.getPlayer().getIsGrounded()) {
				camera.zoom += 0.01f;
				camera.rotate(1f);
			}
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

	private void drawGameObjects() {
		Iterator<GameObject> iterator = model.getGameObjects().iterator();
		while (iterator.hasNext()) {
			GameObject g = iterator.next();
			Vector3 pos = g.getPosition();
			Vector3 size = g.getSize();
			spriteBatch.draw(textures.get(g.getImageFileAsString()),
					pos.getX(), pos.getY(), size.getX(), size.getY());
		}
		
		stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		Player p = model.getPlayer();
		spriteBatch.draw(currentFrame,p.getPosition().getX(), p.getPosition().getY(), p.getSize()
				.getX(), p.getSize().getY());
		
//		spriteBatch.draw(textures.get(p.getImageFileAsString()), p
//				.getPosition().getX(), p.getPosition().getY(), p.getSize()
//				.getX(), p.getSize().getY());
	}

	public void dispose() {
		// All assets should be disposed, music images etc
		spriteBatch.dispose();
	}

	/*
	 * Gameover if player slips below camera y position
	 */
	public boolean isGameOver() {
		return camera.position.y - 300 > model.getPlayer().getPosition().getY();
	}
}
