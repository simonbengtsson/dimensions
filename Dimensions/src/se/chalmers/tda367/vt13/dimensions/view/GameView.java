package se.chalmers.tda367.vt13.dimensions.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.model.Collider;
import se.chalmers.tda367.vt13.dimensions.model.GameModel;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.LowGravityPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.SlowPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.SpeedPowerUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Game view.
 * 
 * @author Carl Fredriksson
 */
public class GameView {

	// Instance Variables
	private GameModel model;
	private SpriteBatch spriteBatch;
	private Texture platformTexture;
	private Texture playerTexture;
	private Texture speedPowerUpTexture;
	private Texture colliderTexture;
	private Texture colliderTestTexture;
	private Map<String, Texture> textures;

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

		initalizeCalle();
		// initalizeKim();

	}

	/**
	 * Draw GameObjects on the screen.
	 */
	public void draw() {
		// Clear screen with white color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Draw GameObjects
		spriteBatch.begin();

		drawCalle();
		// drawKim();

		spriteBatch.end();
	}

	// Private Methods

	private void initalizeKim() {
		textures = new HashMap<String, Texture>();
		for (GameObject g : model.getGameObjects()) {
			String file = g.getTextureFileAsString();
			if (!textures.containsKey(file) && !file.equals("")) {
				Texture t = new Texture(Gdx.files.internal(file));
				textures.put(file, t);
			}
		}
		String file = model.getPlayer().getTextureFileAsString();
		textures.put(file, new Texture(Gdx.files.internal(file)));
	}

	private void drawKim() {
		Iterator<GameObject> iterator = model.getGameObjects().iterator();
		while (iterator.hasNext()) {
			GameObject g = iterator.next();
			Vector3 pos = g.getPosition();
			Vector3 size = g.getSize();
			spriteBatch.draw(textures.get(g.getTextureFileAsString()),
					pos.getX(), pos.getY(), size.getX(), size.getY());
		}
		Player p = model.getPlayer();
		spriteBatch.draw(textures.get(p.getTextureFileAsString()), p
				.getPosition().getX(), p.getPosition().getY(), p.getSize()
				.getX(), p.getSize().getY());
	}

	private void initalizeCalle() {
		platformTexture = new Texture(
				Gdx.files.internal("data/PlatformImg.png"));
		playerTexture = new Texture(Gdx.files.internal("data/PlayerImg.png"));
		speedPowerUpTexture = new Texture(
				Gdx.files.internal("data/SpeedPowerUpImg.png"));
		colliderTexture = new Texture(
				Gdx.files.internal("data/ColliderImg.png"));
		colliderTestTexture = new Texture(
				Gdx.files.internal("data/SolidColliderImg.png"));
	}

	private void drawCalle() {
		Iterator<GameObject> iterator = model.getGameObjects().iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (gameObject instanceof Platform) {
				spriteBatch.draw(platformTexture, gameObject.getPosition()
						.getX(), gameObject.getPosition().getY(), gameObject
						.getSize().getX(), gameObject.getSize().getY());
			} else if (gameObject instanceof SpeedPowerUp
					|| gameObject instanceof LowGravityPowerUp) {
				spriteBatch.draw(speedPowerUpTexture, gameObject.getPosition()
						.getX(), gameObject.getPosition().getY(), gameObject
						.getSize().getX(), gameObject.getSize().getY());
			}
		}
		spriteBatch.draw(playerTexture, model.getPlayer().getPosition().getX(),
				model.getPlayer().getPosition().getY(), model.getPlayer()
						.getSize().getX(), model.getPlayer().getSize().getY());
	}

}
