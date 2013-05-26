package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;

/**
 * 
 * @author Simon Bengtsson
 * 
 */
public class TileCollisionHandler {

	private GameWorld world;
	private Dimension dimension;
	private Player player;
	private Vector3 playerSize;
	private Vector3 playerSpeed;
	private Vector3 playerPos;
	private MapHandler mapHandler;
	private float gravity;

	public TileCollisionHandler(GameWorld world, MapHandler mapHandler) {
		this.world = world;
		this.mapHandler = mapHandler;
		this.gravity = world.getGravity();
		this.player = world.getPlayer();
		this.dimension = world.getDimension();
		playerSize = player.getSize();
		playerSpeed = player.getSpeed();
		playerPos = player.getPosition();
	}

	private float visualY(Vector3 vector3) {
		if (dimension == Dimension.XY) {
			return vector3.getY();
		} else {
			return vector3.getZ();
		}
	}

	public boolean isGroundBelow() {
		player.setGrounded(false);
		for (Point point : getTestTiles(getTestAreaBottom(), 0)) {
			if (checkCollision(point)) {
				if (dimension == Dimension.XY) {
					setPlayerOnTile(point.y);
				}
				return true;
			}

		}
		return false;
	}

	public boolean isGroundRight() {
		player.setStuck(false);
		for (Point point : getTestTiles(getTestAreaRight(), 1)) {
			if (checkCollision(point)) {
				if (dimension == Dimension.XY) {
					setPlayerBeforeTile(point.x);
				}
				return true;
			}
		}
		return false;
	}

	private Rectangle getTestAreaBottom() {
		Rectangle area = new Rectangle();
		area.height = getAreaHeight();
		area.width = (int) Math.ceil(player.getSize().getX());
		area.y = (int) visualY(playerPos);
		area.x = (int) playerPos.getX();
		return area;
	}

	private Rectangle getTestAreaRight() {
		Rectangle area = new Rectangle();
		area.height = (int) Math.ceil(visualY(playerSize));
		area.width = getAreaWidth();
		area.y = (int) visualY(playerPos) + area.height;
		area.x = (int) Math.ceil(playerPos.getX()
				+ (int) Math.ceil(player.getSize().getX()));
		return area;
	}

	private void setPlayerOnTile(int tilePosY) {
		playerPos.setY(tilePosY);
		player.setGrounded(true);
		if (dimension == Dimension.XY) {
			System.out.println("testing");
			player.getSpeed().setY(0);
		}
	}

	private void setPlayerBeforeTile(int tilePosX) {
		playerPos.setX(tilePosX - player.getSize().getX());
		player.setStuck(true);
		player.getSpeed().setX(0);
	}

	/**
	 * Collision testing with the tiles.
	 * 
	 * @param world
	 *            the Game World
	 */
	public boolean checkCollision(Point point) {
		if (mapHandler.isCellGround(point.x, point.y - 1)) {
			return true;
		} else if (mapHandler.isCellObstacle(point.x, point.y - 1)) {
			world.notifyWorldListeners(State.GAME_OVER);
		}
		return false;
	}

	/**
	 * 
	 * @param area
	 * @return Tiles in the given area
	 */
	public ArrayList<Point> getTestTiles(Rectangle area, int order) {
		ArrayList<Point> tiles = new ArrayList<Point>();
		if (order == 0) {
			for (int j = 0; j < area.height; j++) {
				for (int i = 0; i < area.width; i++) {
					addPoint(area.x + i, area.y - j, tiles);
				}
			}
			return tiles;
		} else {
			for (int j = 0; j < area.width; j++) {
				for (int i = 0; i < area.height; i++) {
					addPoint(area.x + j, area.y - i, tiles);
				}
			}
			return tiles;
		}
	}

	/**
	 * @return The amount of tiles below the player that is going to be passed
	 *         during the next player update
	 */
	private int getAreaHeight() {
		if (visualY(playerSpeed) <= 0) {
			return (int) (Math.abs(visualY(playerSpeed) + gravity) + 1 + getPositionOnTileY());
		}
		return 0;
	}

	/**
	 * 
	 * @return The amount of tiles to the left of player that is going to be
	 *         passed during the next player update
	 */
	private int getAreaWidth() {
		return (int) (playerSpeed.getX() + 1 + getPositionOnTileY());
	}

	private float getPositionOnTileY() {
		return visualY(playerPos) - (int) visualY(playerPos);
	}

	private void addPoint(int x, int y, ArrayList<Point> tiles) {
		if (x >= 0 && y >= 0) {
			tiles.add(new Point(x, y));
		}
	}
}
