package se.chalmers.tda367.vt13.dimensions.model;

import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

/**
 * Game model.
 * @author Carl Fredriksson
 */
public class GameModel {

	// Instance Variables
	private List<GameObject> gameObjects;
	private Player player;
	
	// Public Methods
	/**
	 * Constructor.
	 * @param gameObjects  the list of GameObjects
	 * @param player the player in the game
	 */
	public GameModel(List<GameObject> gameObjects, Player player) {
		this.gameObjects = gameObjects;
		this.player = player;
	}
	
	/**
	 * Get method for the instance variable gameObjects.
	 * @return list of GameObjects
	 */
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	/**
	 * Get method for the instance variable player.
	 * @return the player in the game
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Add a game object to the gameObjects list.
	 * @param gameObject the GameObject to be added to the list
	 */
	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	/**
	 * Update all the GameObjects in the gameObjects list,
	 * and update the player.
	 */
	public void updateModel() {
		player.calculateSpeed();
		movePlayer();
	}
	
	// Private Methods
	/**
	 * Move the player with its speed. Check for collisions
	 * and adjust player accordingly. Last position of the
	 * player is used to make sure the player is only
	 * getting grounded when falling or going horizontal.
	 */
	private void movePlayer() {
		Vector3 lastPosition = player.getPosition().clone();
		player.getPosition().add(player.getSpeed());
		Iterator<GameObject> iterator = gameObjects.iterator();
		boolean platformCollision = false;
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (collision(player, gameObject)) {
				if (gameObject instanceof Platform && lastPosition.getY() >=
					(gameObject.getPosition().getY() + gameObject.getSize().getY())) {
					player.setIsGrounded(true);
					platformCollision = true;
					adjustPosition(player, gameObject);
				}
				else if (gameObject instanceof PowerUp) {
					((PowerUp) gameObject).use(this);
					iterator.remove();
				}
			}
			
		}
		if (!platformCollision) {
			player.setIsGrounded(false);
		}
	}
	
	private void moveObject(GameObject gameObject) {
		gameObject.getPosition().add(gameObject.getSpeed());
		// COLLISION TESTING
	}
	
	private boolean collision(GameObject object, GameObject otherObject) {
		return !(object.getPosition().getX() > otherObject.getPosition().getX() + otherObject.getSize().getX() ||
				object.getPosition().getX() + object.getSize().getX() < otherObject.getPosition().getX() ||
				object.getPosition().getY() > otherObject.getPosition().getY() + otherObject.getSize().getY() ||
				object.getPosition().getY() + object.getSize().getY() < otherObject.getPosition().getY());
	}
	
	private void adjustPosition(GameObject object, GameObject otherObject) {
		if (object.getSpeed().getY() < 0) {
			float yOverlap = (otherObject.getPosition().getY() + otherObject.getSize().getY()) -
					object.getPosition().getY();
			object.getPosition().setY(object.getPosition().getY() + yOverlap);
		}
	}
	
	public boolean isGameOver() {
		return player.getPosition().getY() + player.getSize().getY() < 0;
	}
	
}
