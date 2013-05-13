package se.chalmers.tda367.vt13.dimensions.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.levels.Level;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

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
	private Dimension currentDimension;
	private float baseGravity;
	private float gravity;
	private List<WorldListener> listeners = new ArrayList<WorldListener>();
	// Just realized these maps classes are apart of Libgdx TODO remove
	private TiledMap mapXY;
	private TiledMap mapXZ;
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
	public GameWorld(Level level) {
		this(new Player(), level);
	}

	/**
	 * New GameWorld with given Level
	 * 
	 * @param gameObjects
	 */
	public GameWorld(Player player, Level level) {
		this.player = player;
		this.gameObjects = level.getGameObjects();
		this.gravity = level.getGravity();
		this.currentDimension = level.getStartingDimension();
		this.mapXY = level.getMapXY();
		this.mapXZ = level.getMapXZ();
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
		checkTileCollisions();
		if (!isPaused) {
			if (currentDimension == Dimension.XY) {
				player.calculateYSpeed(this);
				movePlayerXY();
			} else if (currentDimension == Dimension.XZ) {
				movePlayerXZ();
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

	private void notifyWorldListeners(WorldEvent worldEvent) {
		for (WorldListener wordListener : listeners) {
			wordListener.worldChange(worldEvent, this);
		}
	}

	public void addWorldListener(WorldListener newListener) {
		listeners.add(newListener);
	}

	/**
	 * Move the player with its speed. Check for collisions and adjust player
	 * accordingly. Last position of the player is used to make sure the player
	 * is only getting grounded when falling or going horizontal.
	 */
	private void movePlayerXY() {
		player.getPosition().add(player.getSpeed());
		// Reset the player's speed to MAX_VELOCITY if it's too fast, the reason
		// is to prevent the player to go through platforms and other
		// gameobjects
		if (Math.abs(player.getSpeed().getY()) > Player.MAX_VELOCITY) {
			player.getSpeed()
					.setY(Math.signum(player.getSpeed().getY())
							* Player.MAX_VELOCITY);
		}
		Iterator<GameObject> iterator = gameObjects.iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (checkCollisionXY(player, gameObject)) {
				if (gameObject instanceof Platform
						&& player.getSpeed().getY() < 0) {
					player.setIsGrounded(true);
					adjustPosition(player, gameObject);
				} else if (gameObject instanceof PowerUp) {
					((PowerUp) gameObject).use(this);
					iterator.remove();
				} else if (gameObject instanceof Obstacle) {
					notifyWorldListeners(WorldEvent.GAME_OVER);
				}
			}
		}
	}

	/**
	 * Move the player with its speed. Since the dimension is XZ gravity is not
	 * a factor.
	 */
	private void movePlayerXZ() {
		player.getPosition().add(player.getSpeed());
		player.setSpeed(new Vector3(player.getSpeed().getX(), 0, 0));
		Iterator<GameObject> iterator = gameObjects.iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (checkCollisionXZ(player, gameObject)) {
				if (gameObject instanceof PowerUp) {
					((PowerUp) gameObject).use(this);
					gameObjects.remove(gameObject);
				} else if (gameObject instanceof Obstacle) {
					notifyWorldListeners(WorldEvent.GAME_OVER);
				}
			}
		}
	}

	private boolean checkCollisionXY(GameObject object, GameObject otherObject) {
		return !(object.getPosition().getX() > otherObject.getPosition().getX()
				+ otherObject.getSize().getX()
				|| object.getPosition().getX() + object.getSize().getX() < otherObject
						.getPosition().getX()
				|| object.getPosition().getY() > otherObject.getPosition()
						.getY() + otherObject.getSize().getY() || object
				.getPosition().getY() + object.getSize().getY() < otherObject
				.getPosition().getY());
	}

	private boolean checkCollisionXZ(GameObject object, GameObject otherObject) {
		return !(object.getPosition().getX() > otherObject.getPosition().getX()
				+ otherObject.getSize().getX()
				|| object.getPosition().getX() + object.getSize().getX() < otherObject
						.getPosition().getX()
				|| object.getPosition().getZ() > otherObject.getPosition()
						.getZ() + otherObject.getSize().getZ() || object
				.getPosition().getZ() + object.getSize().getZ() < otherObject
				.getPosition().getZ());
	}

	private void adjustPosition(GameObject object, GameObject otherObject) {
		if (object.getSpeed().getY() < 0) {
			float yOverlap = (otherObject.getPosition().getY() + otherObject
					.getSize().getY()) - object.getPosition().getY();
			object.getPosition().setY(object.getPosition().getY() + yOverlap);
		}
	}

	public TiledMap getCurrentMap() {
		if (currentDimension == Dimension.XY) {
			return mapXY;
		} else if (currentDimension == Dimension.XZ) {
			return mapXZ;
		}
		return null;
	}

	public TiledMap getMapXY() {
		return mapXY;
	}

	public TiledMap getMapXZ() {
		return mapXZ;
	}

	/**
	 * Adjust players speed and position on collisions
	 */
	private void checkTileCollisions() {

		// don't need this if changing to public instance variables
		Vector3 speed = player.getSpeed();
		Vector3 pos = player.getPosition();
		Vector3 size = player.getSize();

		player.setIsGrounded(false);

		// Check for different things depending on dimension
		int posY = 0;
		int height = 0;
		if (currentDimension == Dimension.XY) {
			posY = (int) pos.getY();
			height = (int) size.getY();
		} else if (currentDimension == Dimension.XZ) {
			posY = (int) pos.getZ();
			height = (int) size.getZ();
		}

		// Loop through the tiles under the player and check collisions
		for (int y = posY; y <= posY + height; y++) {
			for (int x = (int) pos.getX(); x <= pos.getX() + (size.getX()); x++) {
				// check if hit the ground / a platform (layer 1)
				if (((TiledMapTileLayer) getCurrentMap().getLayers().get(1))
						.getCell(x, y) != null) {
					if (speed.getY() <= 0) {
						pos.setY((int) (y + 1)); // adjust position
						player.setIsGrounded(true);
						speed.setY(0);
					}
					// Fix for not grounded the first frameupdate. Should
					// be possible to do it in another way
					if (currentDimension == Dimension.XZ) {
						player.setIsGrounded(true);
					}
				}
				// check if hit an obstacle (layer 2)
				if (((TiledMapTileLayer) getCurrentMap().getLayers().get(2))
						.getCell(x, y) != null) {
					notifyWorldListeners(WorldEvent.GAME_OVER);
				}
			}
		}

		// GameOver if player moves out of bounds (XZ)
		if (currentDimension == Dimension.XZ && !player.getIsGrounded()) {
			notifyWorldListeners(WorldEvent.GAME_OVER);
		}
	}
}
