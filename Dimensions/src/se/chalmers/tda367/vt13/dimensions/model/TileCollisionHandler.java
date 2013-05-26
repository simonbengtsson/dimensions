package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;

/**
 * 
 * @author Simon Bengtsson
 * 
 */
public class TileCollisionHandler {

	private GameWorld world;
	private MapHandler mapHandler;

	public TileCollisionHandler(GameWorld world, MapHandler mapHandler) {
		this.world = world;
		this.mapHandler = mapHandler;
	}

	private float visualY(Vector3 vector3) {
		if (world.getDimension() == Dimension.XY) {
			return vector3.getY();
		} else {
			return vector3.getZ();
		}
	}

	public boolean isGroundBelow() {
		world.getPlayer().setGrounded(false);
		for (Point point : getTestTiles(getTestAreaBottom(), 0)) {
			if (checkCollision(point)) {
				if (world.getDimension() == Dimension.XY) {
					setPlayerOnTile(point.y + 1);
				}
				return true;
			}
		}
		return false;
	}

	public boolean isGroundRight() {
		world.getPlayer().setStuck(false);
		for (Point point : getTestTiles(getTestAreaRight(), 1)) {
			if (checkCollision(point)) {
				if (world.getDimension() == Dimension.XY) {
					setPlayerBeforeTile(point.x);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * DONE TODO
	 * 
	 * @return
	 */
	private Rectangle getTestAreaBottom() {
		Rectangle area = new Rectangle();
		area.height = getTestAreaHeight();
		area.width = (int) Math.ceil(world.getPlayer().getSize().getX());
		area.y = (int) (visualY(world.getPlayer().getPosition()));
		area.x = (int) world.getPlayer().getPosition().getX();
		return area;
	}

	/**
	 * DONE TODO
	 * 
	 * @return
	 */
	private int getTestAreaHeight() {
		if (visualY(world.getPlayer().getSpeed()) <= 0) {
			return (int) Math.ceil(Math.abs(visualY(world.getPlayer().getSpeed()) + world.getGravity()));
		}
		return 0;
	}

	private Rectangle getTestAreaRight() {
		Rectangle area = new Rectangle();
		area.height = (int) Math.ceil(visualY(world.getPlayer().getSize()));
		area.width = (int) Math.ceil(world.getPlayer().getSpeed().getX());
		area.y = (int) visualY(world.getPlayer().getPosition()) + area.height;
		area.x = (int) (world.getPlayer().getPosition().getX() + world.getPlayer().getSize().getX() + world.getPlayer()
				.getSpeed().getX());
		return area;
	}

	private void setPlayerOnTile(int tilePosY) {
		world.getPlayer().getPosition().setY(tilePosY);
		world.getPlayer().setGrounded(true);
		world.getPlayer().getSpeed().setY(0);
	}

	private void setPlayerBeforeTile(int tilePosX) {
		// playerPos.setX(tilePosX - player.getSize().getX());
		// player.setStuck(true);
	}

	/**
	 * Collision testing with the tiles.
	 * 
	 * @param world
	 *            the Game World
	 */
	public boolean checkCollision(Point point) {
		if (mapHandler.isCellGround(point.x, point.y)) {
			return true;
		} else if (mapHandler.isCellObstacle(point.x, point.y)) {
			world.notifyWorldListeners(State.GAME_OVER);
		}
		return false;
	}

	public ArrayList<Point> getTestTiles(Rectangle area, int order) {
		ArrayList<Point> tiles = new ArrayList<Point>();
		if (order == 0) {
			// DONE TODO
			for (int j = 0; j < area.height; j++) {
				for (int i = 0; i < area.width; i++) {
					addPoint(area.x + i, area.y - j - 1, tiles);
				}
			}
			return tiles;
		} else {
			// DONE TODO
			for (int j = 0; j < area.width; j++) {
				for (int i = 0; i < area.height; i++) {
					addPoint(area.x + j, area.y - i - 1, tiles);
				}
			}
			return tiles;
		}
	}

	private void addPoint(int x, int y, ArrayList<Point> tiles) {
		if (x >= 0 && y >= 0) {
			tiles.add(new Point(x, y));
		}
	}
}
