package se.chalmers.tda367.vt13.dimensions.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.*;
import se.chalmers.tda367.vt13.dimensions.view.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;

/**
 * Game controller.
 * @author Carl Fredriksson
 */
public class GameController implements ApplicationListener {

	// Instance variables
	GameModel model;
	GameView view;
	
	// Public methods
	@Override
	public void create() {
		// LEVEL WILL TAKE CARE OF THIS LATER
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new Platform(new Vector3(0, 10, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(50, 10, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(100, 10, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(100, 110, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(230, 50, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(280, 50, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(330, 50, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new SpeedPowerUp(new Vector3(290, 100, 0), new Vector3(25, 25, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(475, 120, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(525, 120, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(750, 20, 0), new Vector3(50, 50, 0), new Vector3()));
		gameObjects.add(new Platform(new Vector3(950, 20, 0), new Vector3(50, 50, 0), new Vector3()));
		Player player = new Player(new Vector3(10,150,0), new Vector3(50, 50, 0), new Vector3(2, 0, 0)
			, 0.75f, 15f, false);
		// LEVEL WILL TAKE CARE OF THIS LATER (Model constructor with level parameter?)
		
		model = new GameModel(gameObjects, player);
		view = new GameView(model);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		checkCollisions();
		getInput();
		model.updateModel();
		view.draw();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	// Private methods
	/**
	 * Get input from the user, do different stuff depending
	 * on what input was given.
	 */
	private void getInput() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			model.getPlayer().jump();
		}
	}
	
	/**
	 * Method for checking collisions between GameObjects.
	 */
	private void checkCollisions() {
		// COLLISION TEST CLASS WILL TAKE CARE OF MOST OF THIS LATER
		// Player Collider
		Collider playerCollider = new Collider(model.getPlayer().getPosition().getX(), 
				model.getPlayer().getPosition().getY(), model.getPlayer().getSize().getX(),
				model.getPlayer().getSize().getY());
		
		// Collision testing
		boolean platformCollision = false;
		Iterator<GameObject> iterator = model.getGameObjects().iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			Collider gameObjectCollider = new Collider(gameObject.getPosition().getX(),
					gameObject.getPosition().getY(), gameObject.getSize().getX(),
					gameObject.getSize().getY());
			if (playerCollider.botHit(gameObjectCollider)) {
				if (gameObject instanceof Platform) {
					if (model.getPlayer().getSpeed().getY() <= 0) {
						model.getPlayer().setIsGrounded(true);
						platformCollision = true;
					}
				}
			}
			else if (playerCollider.hit(gameObjectCollider)) {
				if (gameObject instanceof PowerUp) {
					PowerUp powerUp = (PowerUp) gameObject;
					powerUp.use(model.getPlayer());
					iterator.remove();
				}
			}
		}
		if (!platformCollision) {
			model.getPlayer().setIsGrounded(false);
		}
	}
	
}
