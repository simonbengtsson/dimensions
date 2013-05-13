 package se.chalmers.tda367.vt13.dimensions.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.levels.Level;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;
import se.chalmers.tda367.vt13.dimensions.util.TiledMapHandler;

/**
 * Game model.
 * 
 * @author Carl Fredriksson
 */
public class GameWorld {

	public enum Dimension {
		XY, XZ, YZ
	}

	/**
	 * I'm thinking there is more events the controller is interested in later,
	 * for example reaching a checkpoint or finish the level so I made this an
	 * enum. Open for suggetions though //Simon
	 */
	public enum WorldEvent {
		GAME_OVER, DIMENSION_CHANGED;
	}

	public enum State {
		GAME_RUNNING, GAME_PAUSED, GAME_OVER, GAME_LEVEL_END;
	}

	private List<GameObject> gameObjects;
	private Player player;
	private CollisionHandler collisionHandler;
	private Dimension currentDimension;
	private float baseGravity;
	private float gravity;
	private List<WorldListener> listeners = new ArrayList<WorldListener>();
	private State currentState;
	private CheckPoint cp;
	private boolean isPaused = false;
	private int score;
	private Level currentLevel;

	/**
	 * New GameWorld with given Level
	 * 
	 * @param gameObjects
	 */
	public GameWorld(Level level, TiledMapHandler tiledMapHandler) {
		this(new Player(), level, tiledMapHandler);
	}

	/**
	 * New GameWorld with given Level
	 * 
	 * @param gameObjects
	 */
	public GameWorld(Player player, Level level, TiledMapHandler tiledMapHandler) {
		this.player = player;
		this.gameObjects = level.getGameObjects();
		this.collisionHandler = new CollisionHandler(tiledMapHandler);
		this.gravity = level.getGravity();
		this.currentDimension = level.getStartingDimension();
		this.baseGravity = gravity;
		this.currentState = State.GAME_RUNNING;
		cp = new CheckPoint(this);
		currentLevel = level;
	}

	public void update() {
		switch (currentState) {
		case GAME_RUNNING:
			updateRunning();
			break;
		case GAME_PAUSED:
			break;
		case GAME_LEVEL_END:
			break;
		case GAME_OVER:
			break;
		}
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public Level getCurrentLevel(){
		return currentLevel;
	}

	public Player getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int i) {
		score = i;
	}

	public void addToScore(int i) {
		score += i;
	}

	/**
	 * Add a game object to the gameObjects list.
	 * 
	 * @param gameObject
	 *            the GameObject to be added to the list
	 */
	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	/**
	 * Update all the GameObjects in the gameObjects list, and update the
	 * player.
	 */
	public void updateRunning() {
		if (!isPaused) {
			player.getPosition().add(player.getSpeed());
			collisionHandler.checkCollisions(this);
			if (currentDimension == Dimension.XY) {
				player.calculateYSpeed(this);
				// Reset the player's speed to MAX_VELOCITY if it's too fast, the reason
				// is to simulate drag
				if (Math.abs(player.getSpeed().getY()) > Player.MAX_VELOCITY) {
					player.getSpeed()
							.setY(Math.signum(player.getSpeed().getY())
									* Player.MAX_VELOCITY);
				}
			} else if (currentDimension == Dimension.XZ) {
				player.setSpeed(new Vector3(player.getSpeed().getX(), 0, 0));
			}
		}
	}

	public void swapDimension() {
		if (!isPaused) {
			if (currentDimension == Dimension.XY) {
				currentDimension = Dimension.XZ;
			} else {
				currentDimension = Dimension.XY;
			}
			notifyWorldListeners(WorldEvent.DIMENSION_CHANGED);
		}

	}

	public void resetToCheckPoint() {
		if (!isPaused) {
			player = cp.getPlayer();
		}
	}

	public void placeCheckPoint() {
		if (!isPaused) {
			cp = new CheckPoint(this);
		}
	}

	public void setDimension(Dimension dimension) {
		if (!isPaused) {
			currentDimension = dimension;
		}
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float g) {
		if (!isPaused) {
			gravity = g;
		}
	}

	public void setIsPaused(boolean b) {
		isPaused = b;
	}

	public void resetGravity() {
		if (!isPaused) {
			gravity = baseGravity;
		}
	}

	public Dimension getDimension() {
		return currentDimension;
	}

	public void notifyWorldListeners(WorldEvent worldEvent) {
		for (WorldListener wordListener : listeners) {
			wordListener.worldChange(worldEvent, this);
		}
	}

	public void addWorldListener(WorldListener newListener) {
		listeners.add(newListener);
	}

}
