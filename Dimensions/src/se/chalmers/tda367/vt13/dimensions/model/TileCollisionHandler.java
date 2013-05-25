package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.Rectangle;
import java.util.ArrayList;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;

public class TileCollisionHandler {

	public enum CollisionType {
		OBSTACLE, GROUND, NONE;
	}

	private Player player;
	private Vector3 playerSize;
	private Vector3 playerSpeed;
	private Vector3 playerPos;
	private MapHandler mapHandler;

	public TileCollisionHandler(Player player, MapHandler mapHandler) {
		this.mapHandler = mapHandler;
		this.player = player;
		playerSize = player.getSize();
		playerSpeed = player.getSpeed();
		playerPos = player.getPosition();
	}

	public void updatePlayer(Dimension currentDimension, float gravity) {
		for (Rectangle rect : getBottomTiles()) {
			switch (getCollisionType(rect)) {
			case OBSTACLE:
				return;
			case GROUND:
				playerPos.setY(rect.y + 1);
				player.setGrounded(true);
				return;
			default:
				break;
			}
		}
		if(!(player.isGrounded())){
			player.updateY(gravity);
		}

	}

	/**
	 * Collision testing with the tiles.
	 * 
	 * @param world
	 *            the Game World
	 */
	public CollisionType getCollisionType(Rectangle rect) {
		if (mapHandler.isCellGround(rect.x, rect.y)) {
			return CollisionType.GROUND;
		} else if (mapHandler.isCellObstacle(rect.x, rect.y)) {
			return CollisionType.OBSTACLE;
		}
		return CollisionType.NONE;
	}

	/**
	 * Get the tiles that is going to be passed through during the next frame
	 * 
	 * @param world
	 *            The GameWorld
	 * @return An ArrayList containing a Rectangle for each tile that is going
	 *         to be passed
	 */
	public ArrayList<Rectangle> getBottomTiles() {
		ArrayList<Rectangle> tiles = new ArrayList<Rectangle>();
		if (playerSpeed.getY() < 0) {
			System.out.println("test");
			for (int j = 0; j < getPassingTilesCount(); j++) {
				for (int i = 0; i < playerSize.getX(); i++) {
					addRectangle(playerPos.getX() + i, playerPos.getY() - 1,
							tiles);
				}
				// System.out.println("j: " + j);
			}
		}
		return tiles;
	}

	private int getPassingTilesCount() {
		float positionOnTile = Math.abs(playerPos.getY()
				- (int) playerPos.getY());
		return (int) (Math.abs(playerPos.getY()) + 1 + positionOnTile);
	}

	/**
	 * Adds a rectangle to the temporary tiles list
	 * 
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 */
	private void addRectangle(float x, float y, ArrayList<Rectangle> tiles) {
		tiles.add(new Rectangle((int) x, (int) y, 1, 1));
	}

	public float getScreenY(Vector3 vector3, Dimension currentDimension) {
		switch (currentDimension) {
		case XY:
			return vector3.getY();
		case XZ:
			return vector3.getZ();
		default:
			break;
		}
		return 0;
	}
}
