package se.chalmers.tda367.vt13.dimensions.model;

import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

/**
 * Game model.
 * 
 * @author Carl Fredriksson
 */
public class GameModel {

	public enum Dimension {
		XY, XZ, YZ
	}

	private List<GameObject> gameObjects;
	private Player player;
	private Dimension dimension;
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
	public GameModel(List<GameObject> gameObjects, Player player) {
		this.gameObjects = gameObjects;
		this.player = player;
		dimension = Dimension.XZ;
		this.gravity = -0.75f;
		this.baseGravity = -0.75f;
	}

	public GameModel(List<GameObject> gameObjects, Player player, float gravity) {
		this.gameObjects = gameObjects;
		this.player = player;
		dimension = Dimension.XZ;
		this.gravity = gravity;
		this.baseGravity = gravity;
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
		movePlayer();
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

	public void setDimension(Dimension d) {
		dimension = d;
	}

	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * Move the player with its speed. Check for collisions and adjust player
	 * accordingly. Last position of the player is used to make sure the player
	 * is only getting grounded when falling or going horizontal.
	 */
	private void movePlayer() {
		Vector3 lastPosition = player.getPosition().clone();
		player.getPosition().add(player.getSpeed());
		boolean platformCollision = false;
		for (GameObject gameObject : gameObjects) {
			if (collision(player, gameObject)) {
				if (gameObject instanceof Platform
						&& lastPosition.getY() >= (gameObject.getPosition()
								.getY() + gameObject.getSize().getY())) {
					player.setIsGrounded(true);
					platformCollision = true;
					adjustPosition(player, gameObject);
				} else if (gameObject instanceof PowerUp) {
					((PowerUp) gameObject).use(this);
					gameObjects.remove(gameObject);
				}
			}
		}

		if (!platformCollision) {
			player.setIsGrounded(false);
		}
	}

	private void moveObject(GameObject gameObject) {
		gameObject.getPosition().add(gameObject.getSpeed());
	}

	private boolean collision(GameObject object, GameObject otherObject) {
		return !(object.getPosition().getX() > otherObject.getPosition().getX()
				+ otherObject.getSize().getX()
				|| object.getPosition().getX() + object.getSize().getX() < otherObject
						.getPosition().getX()
				|| object.getPosition().getY() > otherObject.getPosition()
						.getY() + otherObject.getSize().getY() || object
				.getPosition().getY() + object.getSize().getY() < otherObject
				.getPosition().getY());
	}

	private void adjustPosition(GameObject object, GameObject otherObject) {
		if (object.getSpeed().getY() < 0) {
			float yOverlap = (otherObject.getPosition().getY() + otherObject
					.getSize().getY()) - object.getPosition().getY();
			object.getPosition().setY(object.getPosition().getY() + yOverlap);
		}
	}

}
