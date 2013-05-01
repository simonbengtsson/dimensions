package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

/**
 * Game model.
 * 
 * @author Carl Fredriksson
 */
public class GameWorld {

	public enum Dimension {
		XY, XZ, YZ
	}

	private List<GameObject> gameObjects;
	private Player player;
	private Dimension currentDimension;
	private List<DimensionChangeListener> dimensionChangeListeners = new ArrayList<DimensionChangeListener>();
	private float baseGravity;
	private float gravity;

	@Deprecated
	/**
	 * Constructor.
	 * 
	 * @param gameObjects
	 *            the list of GameObjects
	 * @param player
	 *            the player in the game
	 */
	public GameWorld(List<GameObject> gameObjects, Player player) {
		this(gameObjects, player, -0.75f);
	}

	public GameWorld(List<GameObject> gameObjects, Player player, float gravity) {
		this.gameObjects = gameObjects;
		this.player = player;
		currentDimension = Dimension.XY;
		this.gravity = gravity;
		this.baseGravity = gravity;
		//createDimensionTimer(3000);
	}

	public void addDimensionChangeListeners() {
		for (GameObject gameObject : gameObjects) {
			dimensionChangeListeners.add(gameObject);
		}
	}

	/**
	 * Changes dimension after specified time. For testing only.
	 * 
	 * @param interval
	 *            How often the dimension should change
	 */
	public void createDimensionTimer(int interval) {
		javax.swing.Timer timer = new Timer(interval, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentDimension == Dimension.XY) {
					currentDimension = Dimension.XZ;
				} else {
					currentDimension = Dimension.XY;
				}
			}
		});
		timer.start();
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
		player.calculateSpeed(this);
		if (currentDimension == Dimension.XY) {
			movePlayerXY();
		} else {
			movePlayerXZ();
		}
	}

	public void setDimension(Dimension dimension) {
		currentDimension = dimension;
		notifyDimensionChangeListeners();
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

	private void notifyDimensionChangeListeners() {
		for (DimensionChangeListener listener : dimensionChangeListeners) {
			listener.dimenensionChange(currentDimension);
		}
	}

	/**
	 * Move the player with its speed. Check for collisions and adjust player
	 * accordingly. Last position of the player is used to make sure the player
	 * is only getting grounded when falling or going horizontal.
	 */
	private void movePlayerXY() {
		Vector3 lastPosition = player.getPosition().clone();
		player.getPosition().add(player.getSpeed());
		boolean platformCollision = false;
		for (GameObject gameObject : gameObjects) {
			if (checkCollisionXY(player, gameObject)) {
				if (gameObject instanceof Platform
						&& lastPosition.getY() >= (gameObject.getPosition()
								.getY() + gameObject.getSize().getY())) {
					player.setIsGrounded(true);
					platformCollision = true;
					adjustPositionXY(player, gameObject);
				} else if (gameObject instanceof PowerUp) {
					((PowerUp) gameObject).use(this);
					//gameObjects.remove(gameObject);
				}
			}
		}

		if (!platformCollision) {
			player.setIsGrounded(false);
		}
	}

	/**
	 * Move the player with its speed. Check for collisions and adjust player
	 * accordingly. Last position of the player is used to make sure the player
	 * is only getting grounded when falling or going horizontal.
	 */
	private void movePlayerXZ() {
		player.getPosition().add(player.getSpeed());
		player.setIsGrounded(true);
	}

	private void moveObject(GameObject gameObject) {
		gameObject.getPosition().add(gameObject.getSpeed());
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
		return false;
	}

	private void adjustPositionXY(GameObject object, GameObject otherObject) {
		if (object.getSpeed().getY() < 0) {
			float yOverlap = (otherObject.getPosition().getY() + otherObject
					.getSize().getY()) - object.getPosition().getY();
			object.getPosition().setY(object.getPosition().getY() + yOverlap);
		}
	}

	private void adjustPositionXZ(GameObject object, GameObject otherObject) {
		
	}

}
