package se.chalmers.tda367.vt13.dimensions.model;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;

public class TileCollisionHandler {
	/**
	 * Collision testing with the tiles.
	 * 
	 * @param world
	 *            the Game World
	 */
	public static void checkTileCollisions(GameWorld world,
			MapHandler mapHandler) {

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
	}
}
