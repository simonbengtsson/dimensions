package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;

public class TileCollisionHandler {

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
			if(checkCollision(point)){
				setPlayerOnTile(point.y);
				return true;
			}
				 
		}
		return false;
	}

	public boolean isGroundLeft() {
		player.setStuck(false);
		for (Point point : getTiles(getTileAreaLeft(), 1)) {
			if(checkCollision(point)){
				setPlayerBeforeTile(point.x);
				return true;
			}
		}
		return false;
	}

	private Rectangle getTileAreaBottom() {
		Rectangle area = new Rectangle();
		area.height = getAreaHeight();
		area.width = (int) Math.ceil(player.getSize().getX());
		area.y = (int) playerPos.getY();
		area.x = (int) playerPos.getX();
		return area;
	}

	private Rectangle getTileAreaLeft() {
		Rectangle area = new Rectangle();
		area.height = (int) Math.ceil(playerSize.getY());
		area.width = getAreaWidth();
		area.y = (int) playerPos.getY() + area.height;
		area.x = (int) Math.ceil(playerPos.getX()
				+ (int) Math.ceil(player.getSize().getX()));
		return area;
	}

	private void setPlayerOnTile(int tilePosY) {
		playerPos.setY(tilePosY);
		player.setGrounded(true);
		player.getSpeed().setY(0);
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
	public ArrayList<Point> getTiles(Rectangle area, int order) {
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

	private void addPoint(int x, int y, ArrayList<Point> tiles) {
		if (x >= 0 && y >= 0) {
			tiles.add(new Point(x, y));
		}
	}
}
