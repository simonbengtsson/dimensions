package se.chalmers.tda367.vt13.dimensions.controller;

//import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.model.*;
import se.chalmers.tda367.vt13.dimensions.model.levels.*;
import se.chalmers.tda367.vt13.dimensions.view.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;

import org.lwjgl.opengl.Display;

/**
 * . Game controller.
 * 
 * @author Carl Fredriksson
 */
public class GameController implements ApplicationListener, SoundObserver {

	// Instance variables
	GameModel model;
	GameView view;
	private long ticks;
	private long lastUpdate = System.currentTimeMillis();
	List<GameObject> ls;
	Map<String, Sound> files;

	// Public methods
	@Override
	public void create() {

		// Reads a level, either by creating one or read from a file.

		Level lv = new Level("Level1");
		ls = lv.getList();

		// ReadLevel rl = new ReadLevel();
		// ls = rl.readLevelName("Level2"+".dat");

		// Creates a player
		Player player = new Player(new Vector3(10, 150, 0), new Vector3(50, 50,
				0), new Vector3(4, 0, 0), -0.75f, 15f, false);

		// Load all soundfiles & add Controller as observer
		files = new HashMap<String, Sound>();
		for (GameObject g : ls) {
			g.addObserver(this);
			String file = g.getSoundFileAsString();

			if (!files.containsKey(file) && !file.equals("")) {
				Sound sound = Gdx.audio.newSound(Gdx.files.internal(file));
				files.put(file, sound);
			}
		}

		model = new GameModel(ls, player);
		view = new GameView(model);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// Display.sync(200);
		long currentTime = System.currentTimeMillis();
		long delta = currentTime - lastUpdate;
		long before = System.currentTimeMillis();
		checkCollisions();
		getInput();
		model.updateModel();
		view.draw();
		lastUpdate = currentTime;
		long after = System.currentTimeMillis();

		// System.out.println("Delta=" + delta);
		// System.out.println("loop took: " + after-before);
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
	 * Get input from the user, do different stuff depending on what input was
	 * given.
	 */
	private void getInput() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			model.getPlayer().jump();
		}
		if (Gdx.input.isTouched()) {
			model.getPlayer().jump();
		}
	}

	/**
	 * Method for checking collisions between GameObjects.
	 */
	private void checkCollisions() {
		// COLLISION TEST CLASS WILL TAKE CARE OF MOST OF THIS LATER
		// Player Collider
		Player player = model.getPlayer();
		float playerColliderXSize = player.getSize().getX()
				+ player.getSpeed().getX();
		float playerColliderYSize = player.getSize().getY()
				+ player.getSpeed().getY();
		Collider playerCollider = new Collider(model.getPlayer().getPosition()
				.getX(), model.getPlayer().getPosition().getY(),
				playerColliderXSize, playerColliderYSize);

		// Collision testing
		boolean platformCollision = false;
		Iterator<GameObject> iterator = model.getGameObjects().iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			Collider gameObjectCollider = new Collider(gameObject.getPosition()
					.getX(), gameObject.getPosition().getY(), gameObject
					.getSize().getX(), gameObject.getSize().getY());
			if (playerCollider.botHit(gameObjectCollider)) {
				if (gameObject instanceof Platform) {
					if (model.getPlayer().getSpeed().getY() <= 0) {
						model.getPlayer().setIsGrounded(true);
						platformCollision = true;
					}
				}
			} else if (playerCollider.hit(gameObjectCollider)) {
				if (gameObject instanceof PowerUp) {
					PowerUp powerUp = (PowerUp) gameObject;
					powerUp.use(model);
					iterator.remove();
				}
			}
		}
		if (!platformCollision) {
			model.getPlayer().setIsGrounded(false);
		}
	}

	/**
	 * Pretty obvious :)
	 * 
	 * @return
	 */
	public GameModel getGameModel() {
		return model;
	}

	@Override
	public void playSound(String s) {
		files.get(s).play();
	}

}
