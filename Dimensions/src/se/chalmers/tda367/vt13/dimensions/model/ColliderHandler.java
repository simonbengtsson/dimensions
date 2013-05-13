package se.chalmers.tda367.vt13.dimensions.model;


import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.WorldEvent;
import se.chalmers.tda367.vt13.dimensions.util.TileMapHandler;

public class ColliderHandler {

	public ColliderHandler(){
		
	}
	
	public void checkTileCollisions(GameWorld world) {

		Player player = world.getPlayer();
		Vector3 speed = player.getSpeed();
		Vector3 pos = player.getPosition();
		Vector3 size = player.getSize();

		player.setIsGrounded(false);

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
				if (TileMapHandler.isCellGround(world, x, y)) {
					if (speed.getY() <= 0) {
						pos.setY((int) (y + 1)); // adjust position
						player.setIsGrounded(true);
						speed.setY(0);
					}
					// Fix for not grounded the first frameupdate. Should
					// be possible to do it in another way
					if (world.getDimension() == Dimension.XZ) {
						player.setIsGrounded(true);
					}
				}
				// check if hit an obstacle (layer 2)
				if (TileMapHandler.isCellObstacle(world, x, y)) {
					world.notifyWorldListeners(WorldEvent.GAME_OVER);
				}
			}
		}

		// GameOver if player moves out of bounds (XZ)
		if (world.getDimension() == Dimension.XZ && !player.getIsGrounded()) {
			world.notifyWorldListeners(WorldEvent.GAME_OVER);
		}
	}

}
