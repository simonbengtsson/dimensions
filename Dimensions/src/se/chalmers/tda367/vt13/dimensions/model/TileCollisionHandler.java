package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.Rectangle;
import java.util.ArrayList;

public class TileCollisionHandler {

	public enum CollisionType {
		OBSTACLE, GROUND, NONE;
	}

	/**
	 * Collision testing with the tiles.
	 * 
	 * @param world
	 *            the Game World
	 */
	public static CollisionType getCollisionType(GameWorld world,
			MapHandler mapHandler) {

		Player player = world.getPlayer();
		Vector3 pos = player.getPosition();
		Vector3 size = player.getSize();
		getBottomTiles(world);
		// Check for different things depending on dimension
		int screenPosY = (int) player.getScreenY(pos, world.getDimension());
		int screenSizeY = (int) player.getScreenY(size, world.getDimension());
		// Loop through the tiles under the player and check collisions
		for (int y = screenPosY; y <= screenPosY + screenSizeY; y++) {
			for (int x = (int) pos.getX(); x <= pos.getX() + (size.getX()); x++) {
				if (mapHandler.isCellGround(x, y)) {
					return CollisionType.GROUND;
				} else if (mapHandler.isCellObstacle(x, y)) {
					return CollisionType.OBSTACLE;
				}
			}
		}
		return CollisionType.NONE;
	}

	private static ArrayList<Rectangle> tmpTiles = new ArrayList<Rectangle>();

	/**
	 * Get the tiles that is going to be passed through during the next frame
	 * 
	 * @param world
	 *            The GameWorld
	 * @return An ArrayList containing a Rectangle for each tile that is
	 *         going to be passed
	 */
	public static ArrayList<Rectangle> getBottomTiles(GameWorld world) {
		tmpTiles.clear();
		Player player = world.getPlayer();
		Vector3 size = player.getSize();
		Vector3 pos = player.getPosition();

		int screenPosY = (int) player.getScreenY(pos, world.getDimension());
		int screenSizeY = (int) player.getScreenY(size, world.getDimension());
		System.out.println(screenSizeY);
		for (int y = screenPosY; y <= screenPosY + screenSizeY; y++) {
			for (int x = (int) pos.getX(); x <= pos.getX() + (size.getX()); x++) {
				addRectangle(player.getPosition().getX(), player.getPosition()
						.getY());
			}
		}
		//System.out.println(tmpTiles.size());
		return tmpTiles;
	}

	/**
	 * Adds a rectangle to the temporary tiles list
	 * 
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 */
	private static void addRectangle(float x, float y) {
		tmpTiles.add(new Rectangle((int) x, (int) y, 1, 1));
	}
}
