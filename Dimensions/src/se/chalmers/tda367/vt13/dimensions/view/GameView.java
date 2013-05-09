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
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Game view.
 * 
 * @author Carl Fredriksson
 */
public class GameView {

	private GameWorld model;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private SpriteBatch spriteBatch;
	private Map<String, Texture> textures;
	private OrthographicCamera camera;
	private Animation walkAnimation;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private float stateTime;
	private double[] stateAlert = new double[10];
	private int thescore = 0;
	private BitmapFont font = new BitmapFont();
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;
	private Texture bg;
	private Sprite bgsprite;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            the GameModel
	 */
	public GameView(GameWorld model) {
		this.model = model;
		spriteBatch = new SpriteBatch();

		loadImageFiles();

		// load the map, set the unit scale to 1/16 (1 unit == 16 pixels)
//		map = new TmxMapLoader()
//				.load("data/lvl1.tmx");
//		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		for (int i = 0; i < stateAlert.length; i++) {
			double tmp = 2.0;
			stateAlert[i] = tmp;
			tmp = tmp + 3.0;
		}

		// //this.rbg = new
		// ParallaxBackground(camera.viewportWidth,camera.viewportHeight);
		// initiateBackground();
		// testing animation
		initWalkAnimation();

		bg = new Texture(Gdx.files.internal("data/bakgrund.png"));
		bg.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		bgsprite = new Sprite(bg, 0, 0, 20000, 512);

	}

	/**
	 * Draw GameObjects on the screen.
	 */
	public void draw() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// updateCameraPosition(3);
		camera.position.x = model.getPlayer().getPosition().getX() + 400;
		camera.update();

		//renderer.setView(camera);
		//renderer.render();

		calculateScore();
		spriteBatch.setProjectionMatrix(camera.combined);

		// Draw GameObjects
		spriteBatch.begin();
		bgsprite.draw(spriteBatch);
		// rbg.render(camera.position.x,camera.position.y,spriteBatch);

		if (model.getDimension() == GameWorld.Dimension.XY) {
			drawGameObjectsXY();
		} else if (model.getDimension() == GameWorld.Dimension.XZ) {
			drawGameObjectsXZ();
		}

		font.setColor(Color.YELLOW);
		font.draw(spriteBatch, "Score: " + thescore, camera.position.x + 330,
				camera.position.y + 230);

		/*
		 * if(Gdx.graphics.getDeltaTime() > this.alertIndex &&
		 * Gdx.graphics.getDeltaTime() < this.alertIndex+1){
		 * while(Gdx.graphics.getDeltaTime() > this.alertIndex &&
		 * Gdx.graphics.getDeltaTime() < this.alertIndex+1){
		 * font.draw(spriteBatch,"WARNING",
		 * camera.position.x,camera.position.y+100); } this.alertIndex = (int)
		 * this.stateAlert[alertIndex+1]; }
		 */

		spriteBatch.end();
	}

	/*
	 * private String dimensionChangeMsg(){ return
	 * "Warning, Dimensions changing"; }
	 */

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
			System.out.println(delta);
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
	 * spriteSheet.findRegion("wold");
	 * 
	 * 
	 * 
	 * 
	 * rbg.addLayer(new ParallaxLayer(bg,2f)); }
	 */

	private void initWalkAnimation() {
		walkSheet = new Texture(Gdx.files.internal("data/animation_sheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
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

		Player p = model.getPlayer();
		spriteBatch.draw(textures.get(p.getImageFileAsString()), p
				.getPosition().getX(), p.getPosition().getZ(), p.getSize()
				.getX(), p.getSize().getZ());
	}

	public OrthographicCamera getCamera() {
		return this.camera;
	}

	public void dispose() {
		spriteBatch.dispose();
	}
}
