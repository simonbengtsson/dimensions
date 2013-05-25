package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;

public class TileCollisionHandler {

	public enum CollisionType {
		OBSTACLE, GROUND, NONE;
	}

	private GameWorld world;
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
		playerSize = player.getSize();
		playerSpeed = player.getSpeed();
		playerPos = player.getPosition();
	}

	public boolean isGroundBelow() {
		player.setGrounded(false);
		for (Point point : getTiles(getTileAreaBottom(), 0)) {
			switch (checkCollision(point)) {
			case OBSTACLE:
				world.notifyWorldListeners(State.GAME_OVER);
				return true;
			case GROUND:
				setPlayerOnTile(point.y);
				return true;
			default:
				break;
			}
		}
		return false;
	}

	public boolean isGroundLeft() {
		player.setStuck(false);
		for (Point point : getTiles(getTileAreaLeft(), 1)) {
			switch (checkCollision(point)) {
			case OBSTACLE:
				world.notifyWorldListeners(State.GAME_OVER);
				return true;
			case GROUND:
				player.setStuck(false);
				setPlayerBeforeTile(point.x);
				return true;
			default:
				break;
			}
		}
		return false;
	}

	/**
	 * Doensn't really make sense, area.y = (int) playerPos.getY(); should be
	 * area.y = (int) playerPos.getY() - getAreaHeight();?
	 * 
	 * @return
	 */
	private Rectangle getTileAreaBottom() {
		Rectangle area = new Rectangle();
		area.height = getAreaHeight();
		area.width = (int) Math.ceil(player.getSize().getX());
		area.y = (int) playerPos.getY();
		area.x = (int) playerPos.getX() + 1;
		return area;
	}

	private Rectangle getTileAreaLeft() {
		Rectangle area = new Rectangle();
		area.height = (int) Math.ceil(playerSize.getY());
		area.width = getAreaWidth();
		area.y = (int) playerPos.getY() + area.height;
		area.x = (int) Math.ceil(playerPos.getX()
				+ (int) Math.ceil(player.getSize().getX()));
		System.out.println("Area: " + area);
		return area;
	}

	private void setPlayerOnTile(int tilePosY) {
		playerPos.setY(tilePosY + 1f);
		player.setGrounded(true);
		player.getSpeed().setY(0);
	}
	
	private void setPlayerBeforeTile(int tilePosX) {
		playerPos.setX(tilePosX + 1f);
		player.setStuck(true);
		player.getSpeed().setX(0);
	}

	/**
	 * Collision testing with the tiles.
	 * 
	 * @param world
	 *            the Game World
	 */
	public CollisionType checkCollision(Point point) {
		if (mapHandler.isCellGround(point.x, point.y)) {
			return CollisionType.GROUND;
		} else if (mapHandler.isCellObstacle(point.x, point.y)) {
			return CollisionType.OBSTACLE;
		}
		return CollisionType.NONE;
	}

	/**
	 * 
	 * @param area
	 * @return Tiles in the given area
	 */
	public ArrayList<Point> getTiles(Rectangle area, int order) {
		ArrayList<Point> tiles = new ArrayList<Point>();
		if (order == 0) {
			for (int j = 0; j < area.height; j++) {
				for (int i = 0; i < area.width; i++) {
					addPoint(area.x + i, area.y - j - 1, tiles);
				}
			}
			return tiles;
		} else {
			for (int j = 0; j < area.width; j++) {
				for (int i = 0; i < area.height; i++) {
					addPoint(area.x + j, area.y + i, tiles);
				}
			}
			System.out.println(tiles);
			System.out.println(playerPos.getX());
			return tiles;
		}
	}

	/**
	 * @return The amount of tiles below the player that is going to be passed
	 *         during the next player update
	 */
	private int getAreaHeight() {
		if (playerSpeed.getY() <= 0) {
			return (int) (Math.abs(playerSpeed.getY() + gravity) + 1 + getPositionOnTileY());
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
		return playerPos.getY() - (int) playerPos.getY();
	}

	/**
	 * Adds a rectangle to the temporary tiles list
	 * 
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 */
	private void addPoint(int x, int y, ArrayList<Point> tiles) {
		if (x >= 0 && y >= 0) {
			tiles.add(new Point(x, y));
		}
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
