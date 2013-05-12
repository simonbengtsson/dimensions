package se.chalmers.tda367.vt13.dimensions.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.levels.Level;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

import com.badlogic.gdx.maps.tiled.TiledMap;

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

	private List<GameObject> gameObjects;
	private Player player;
	private Dimension currentDimension;
	private float baseGravity;
	private float gravity;
	private List<WorldListener> listeners = new ArrayList<WorldListener>();
	private TiledMap mapXY;
	private TiledMap mapXZ;

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
		this.mapXY = level.getMapXY();
		this.mapXZ = level.getMapXZ();
		this.baseGravity = gravity;

		// TODO Make the below properties of each level (?)
		this.gravity = -0.05f;
		currentDimension = Dimension.XY; // Starting dimension
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public Player getPlayer() {
		return player;
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
	public void updateModel() {
		if (currentDimension == Dimension.XY) {
			player.calculateYSpeed(this);
			movePlayerXY();
		} else if (currentDimension == Dimension.XZ) {
			movePlayerXZ();
		}
	}

	public void swapDimension() {
		if (currentDimension == Dimension.XY) {
			currentDimension = Dimension.XZ;
		} else {
			currentDimension = Dimension.XY;
		}
		notifyWorldListeners(WorldEvent.DIMENSION_CHANGED, currentDimension);
	}

	public void setDimension(Dimension dimension) {
		currentDimension = dimension;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float g) {
		gravity = g;
	}

	public void resetGravity() {
		gravity = baseGravity;
	}

	public Dimension getDimension() {
		return currentDimension;
	}

	private void notifyWorldListeners(WorldEvent worldEvent, Object value) {
		for (WorldListener wordListener : listeners) {
			wordListener.worldChange(worldEvent, value);
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
					notifyWorldListeners(WorldEvent.GAME_OVER, null);
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
					notifyWorldListeners(WorldEvent.GAME_OVER, null);
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
}
