package se.chalmers.tda367.vt13.dimensions.model;


import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

/**
 * Class handling all types of collisions.
 */
public class CollisionHandler {

	MapHandler mapHandler;
	
	public CollisionHandler(MapHandler mapHandler){
		this.mapHandler = mapHandler;
	}
	
	/**
	 * Collision testing with tiles and game objects.
	 * @param world the Game World
	 */
	public void checkCollisions(GameWorld world) {
		world.getPlayer().setIsGrounded(false);
		world.getPlayer().setIsStuck(false);
		checkTileCollisions(world);
		checkObjectCollisions(world);
	}
	
	/**
	 * Collision testing with the tiles.
	 * @param world the Game World
	 */
	private void checkTileCollisions(GameWorld world) {

		Player player = world.getPlayer();
		Vector3 speed = player.getSpeed();
		Vector3 pos = player.getPosition();
		Vector3 size = player.getSize();
		
		// Check for different things depending on dimension
		int relevantPos = 0;
		int relevantSize = 0;
		if (world.getDimension() == Dimension.XY) {
			relevantPos = (int) pos.getY();
			relevantSize = (int) size.getY();
		} else if (world.getDimension() == Dimension.XZ) {
			relevantPos = (int) pos.getZ();
			relevantSize = (int) size.getZ();
		}

		// Loop through the tiles under the player and check collisions
		for (int y = relevantPos; y <= relevantPos + relevantSize; y++) {
			for (int x = (int) pos.getX(); x <= pos.getX() + (size.getX()); x++) {
				// check if hit the ground / a platform (layer 1)
				if (mapHandler.isCellGround(x, y)) {
					if (speed.getY() <= 0) {
						if (speed.getY() < 0) {
							pos.setY((int) (y + 1)); // adjust position
						}
						else if (speed.getY() == 0) {
							player.setIsStuck(true);
						}
						player.setIsGrounded(true);
					}
					// Fix for not grounded the first frameupdate. Should
					// be possible to do it in another way
					if (world.getDimension() == Dimension.XZ) {
						player.setIsGrounded(true);
					}
				}
				// check if hit an obstacle (layer 2)
				if (mapHandler.isCellObstacle(x, y)) {
					world.notifyWorldListeners(State.GAME_OVER);
				}
			}
		}

		// GameOver if player moves out of bounds (XZ)
		if (world.getDimension() == Dimension.XZ && !player.getIsGrounded()) {
			world.notifyWorldListeners(State.GAME_OVER);
		}
	}

	/**
	 * Collision testing with the gameObjects.
	 * @param world the Game World
	 */
	private void checkObjectCollisions(GameWorld world) {
		if (world.getDimension() == Dimension.XY) {
			checkObjectCollisionsXY(world);
		}
		else if (world.getDimension() == Dimension.XZ) {
			checkObjectCollisionsXZ(world);
		}
	}
	
	private void checkObjectCollisionsXY(GameWorld world) {
		Player player = world.getPlayer();
		List<GameObject> gameObjects = world.getGameObjects();
		Iterator<GameObject> iterator = gameObjects.iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (checkCollisionXY(player, gameObject)) {
				if (gameObject instanceof Platform
						&& player.getSpeed().getY() < 0) {
					player.setIsGrounded(true);
					adjustPosition(player, gameObject);
				} else if (gameObject instanceof PowerUp) {
					((PowerUp) gameObject).use(world);
					iterator.remove();
				}
			}
		}
	}
	
	private void checkObjectCollisionsXZ(GameWorld world) {
		Player player = world.getPlayer();
		List<GameObject> gameObjects = world.getGameObjects();
		Iterator<GameObject> iterator = gameObjects.iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (checkCollisionXZ(player, gameObject)) {
				if (gameObject instanceof PowerUp) {
					((PowerUp) gameObject).use(world);
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
