package se.chalmers.tda367.vt13.dimensions.model;

import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

/**
 * Class handling all types of collisions.
 */
public class CollisionHandler {
	private GameWorld world;
	
	public CollisionHandler(GameWorld world){
		this.world = world;
	}
	
	/**
	 * Collision testing with tiles and game objects.
	 * 
	 * @param world
	 *            the Game World
	 */
	public void checkCollisions() {
		checkObjectCollisions();
	}

	/**
	 * Collision testing with the gameObjects.
	 * 
	 * @param world
	 *            the Game World
	 */
	private void checkObjectCollisions() {
		if (world.getDimension() == Dimension.XY) {
			checkObjectCollisionsXY();
		} else if (world.getDimension() == Dimension.XZ) {
			checkObjectCollisionsXZ();
		}
	}

	private void checkObjectCollisionsXY() {
		Player player = world.getPlayer();
		List<GameObject> gameObjects = world.getGameObjects();
		Iterator<GameObject> iterator = gameObjects.iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (checkCollisionXY(player, gameObject)) {
				if (gameObject instanceof Platform
						&& player.getSpeed().getY() < 0) {
					player.setGrounded(true);
					adjustPosition(player, gameObject);
				} else if (gameObject instanceof PowerUp) {
					PowerUpHandler powerUpHandler = new NormalPowerUpHandler(world);
					((PowerUp) gameObject).use(powerUpHandler);
					iterator.remove();
				}
			}
		}
	}

	private void checkObjectCollisionsXZ() {
		Player player = world.getPlayer();
		List<GameObject> gameObjects = world.getGameObjects();
		Iterator<GameObject> iterator = gameObjects.iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (checkCollisionXZ(player, gameObject)) {
				if (gameObject instanceof PowerUp) {
					PowerUpHandler powerUpHandler = new NormalPowerUpHandler(world);
					((PowerUp) gameObject).use(powerUpHandler);
					iterator.remove();
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

}
