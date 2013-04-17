package se.chalmers.tda367.vt13.dimensions.view;

import java.util.Iterator;

import se.chalmers.tda367.vt13.dimensions.controller.Collider;
import se.chalmers.tda367.vt13.dimensions.model.GameModel;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.SpeedPowerUp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Game view.
 * @author Carl Fredriksson
 */
public class GameView {

	// Instance Variables
	private GameModel model;
	private SpriteBatch spriteBatch;
	private Texture platformTexture;
	private Texture playerTexture;
	private Texture speedPowerUpTexture;
	private Texture colliderTestTexture;
	
	// Public Methods
	/**
	 * Constructor.
	 * @param model the GameModel
	 */
	public GameView(GameModel model) {
		this.model = model;
		spriteBatch = new SpriteBatch();
		platformTexture = new Texture(Gdx.files.internal("data/PlatformImg.png"));
		playerTexture = new Texture(Gdx.files.internal("data/PlayerImg.png"));
		speedPowerUpTexture = new Texture(Gdx.files.internal("data/SpeedPowerUpImg.png"));
		colliderTestTexture = new Texture(Gdx.files.internal("data/ColliderImg.png"));
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
		Iterator<GameObject> iterator = model.getGameObjects().iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (gameObject instanceof Platform) {
				spriteBatch.draw(platformTexture, gameObject.getPosition().getX(),
						gameObject.getPosition().getY(), gameObject.getSize().getX(),
						gameObject.getSize().getY());
				
				/*
				Collider gameObjectCollider = new Collider(gameObject.getPosition().getX(),
						gameObject.getPosition().getY(), gameObject.getSize().getX(),
						gameObject.getSize().getY());
				Rectangle boundingRect = gameObjectCollider.getBoundingRect();
				Rectangle topRect = gameObjectCollider.getTopRect();
				//spriteBatch.draw(colliderTestTexture, boundingRect.x, boundingRect.y,boundingRect.width, boundingRect.height);
				spriteBatch.draw(colliderTestTexture, topRect.x, topRect.y, topRect.width, topRect.height);
				System.out.println("topRect: x:" + topRect.x + ", y:" + topRect.y + ", width:" +
					topRect.width + ", height:" + topRect.height);
			*/
			}
			else if (gameObject instanceof SpeedPowerUp) {
				spriteBatch.draw(speedPowerUpTexture, gameObject.getPosition().getX(),
						gameObject.getPosition().getY(), gameObject.getSize().getX(),
						gameObject.getSize().getY());
			}
		}
		Player player = model.getPlayer();
		spriteBatch.draw(playerTexture, player.getPosition().getX(),
				player.getPosition().getY(), player.getSize().getX(),
				player.getSize().getY());
		// COLLIDER TEST
		float playerColliderXPos = player.getPosition().getX();
		float playerColliderYPos = player.getPosition().getY();
		float playerColliderXSize = player.getSize().getX();
		float playerColliderYSize = player.getSize().getY();
		if (player.getSpeed().getX() > 0) {
			playerColliderXSize += player.getSpeed().getX();
		}
		else if (player.getSpeed().getX() < 0) {
			playerColliderXPos += player.getSpeed().getX();
			playerColliderXSize -= player.getSpeed().getX();
		}
		if (player.getSpeed().getY() > 0) {
			playerColliderYSize += player.getSpeed().getY();
		}
		else if (player.getSpeed().getY() < 0) {
			playerColliderYPos += player.getSpeed().getY();
			playerColliderYSize -= player.getSpeed().getY();
		}
		spriteBatch.draw(colliderTestTexture, playerColliderXPos, playerColliderYPos,
				playerColliderXSize, playerColliderYSize);
		
		// COLLIDER TEST
		spriteBatch.end();
	}
	
	// Private Methods
	
}
