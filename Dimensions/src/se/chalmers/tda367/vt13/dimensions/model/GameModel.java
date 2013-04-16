package se.chalmers.tda367.vt13.dimensions.model;

import java.util.Iterator;
import java.util.List;

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
	public void updateModel(float time) {
		updateGameObjects(time);
		updatePlayer(time);
	}
	
	// Private Methods
	/**
	 * Update all the GameObjects in the gameObject list.
	 */
	private void updateGameObjects(float time) {
		Iterator<GameObject> iterator = gameObjects.iterator();
		while (iterator.hasNext()) {
			iterator.next().update(time);
		}
	}
	
	/**
	 * Update the player.
	 */
	private void updatePlayer(float time) {
		player.update(time);
	}
	
}
